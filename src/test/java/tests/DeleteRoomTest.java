
package tests;

import base.TestBase;
import utils.JsonDataLoader;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class DeleteRoomTest extends TestBase {

    @Test
    public void testDeleteRoomById() {
        getTest().info("Deleting room with id=101 (if exists)");
        given()
            .accept("application/xml")
        .when()
            .delete("/deleteRoomById/101")
        .then()
            .statusCode(anyOf(equalTo(200), equalTo(204)));
        getTest().pass("deleteRoomById validated");
    }
    
    @Test
    public void testDeleteRoomsFromJson() {
        List<Map<String, Object>> rooms = JsonDataLoader.loadJsonArrayAsListOfMaps("testdata/multipleRoomData.json");

        for (Map<String, Object> room : rooms) {
            int roomId = (int) room.get("roomId");
            getTest().info("Deleting room with id=" + roomId);

            given()
                .accept("application/xml")
            .when()
                .delete("/deleteRoomById/" + roomId)
            .then()
                .statusCode(anyOf(equalTo(200), equalTo(204)));

            getTest().pass("Deleted room with id=" + roomId);
        }
    }
}
