
package tests;

import base.TestBase;
import utils.JsonDataLoader;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class ViewRoomByIdTest extends TestBase {

    @Test
    public void testViewRoomById() {
        getTest().info("Calling viewRoomById with id=101");
        given()
            .accept("application/json")
        .when()
            .get("/viewRoomById/101")
        .then()
            .statusCode(anyOf(equalTo(200), equalTo(204)))
            .body("roomId", anyOf(equalTo(101), equalTo("101")))
            .body("roomPrice", greaterThan(0f));
        getTest().pass("viewRoomById validated");
    }
    
    
    @DataProvider(name = "roomData")
    public Object[][] getRoomData() {
        List<Map<String, Object>> rooms = JsonDataLoader.loadJsonArrayAsListOfMaps("testdata/multipleRoomData.json");
        return rooms.stream()
                    .map(room -> new Object[]{room.get("roomId")})
                    .toArray(Object[][]::new);
    }

    @Test(dataProvider = "roomData")
    public void testViewRoomByIdFromJson(int roomId) {
        getTest().info("Viewing room by id=" + roomId);

        given()
            .accept("application/json")
        .when()
            .get("/viewRoomById/" + roomId)
        .then()
            .statusCode(anyOf(equalTo(200), equalTo(204)))
            .body("roomId", anyOf(equalTo(roomId), equalTo(String.valueOf(roomId))))
            .body("roomPrice", greaterThan(0f));

        getTest().pass("Viewed room with id=" + roomId);
    }
}
    
