
package tests;

import base.TestBase;
import org.testng.annotations.Test;

import utils.JsonDataLoader;
import utils.PayloadBuilder;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class UpdateRoomPriceTest extends TestBase {

    @Test
    public void testUpdateRoomPrice() {
        Map<String, Object> payload = PayloadBuilder.loadPropertiesAsMap("testdata/updateRoom.properties");
        getTest().info("Updating room price for id=" + payload.get("roomId"));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParams(payload)
        .when()
            .put("/updateRoomPrice")
        .then()
            .statusCode(200);
        getTest().pass("updateRoomPrice validated");
    }
    
    @Test
    public void testUpdateRoomPricesFromJson() {
        List<Map<String, Object>> rooms = JsonDataLoader.loadJsonArrayAsListOfMaps("testdata/multipleRoomData.json");

        for (Map<String, Object> room : rooms) {
            getTest().info("Updating price for room id=" + room.get("roomId"));

            given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(room)
            .when()
                .put("/updateRoomPrice")
            .then()
                .statusCode(200);

            getTest().pass("Updated price for room id=" + room.get("roomId"));
        }
    }
}
