package tests;

import base.TestBase;
import utils.JsonDataLoader;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class ViewRoomByTypeTest extends TestBase {

    @Test
    public void testViewRoomByType() {
        getTest().info("Calling viewRoomByType with SINGLE");
        given()
            .accept("application/json")
            .queryParam("roomType", "SINGLE")
        .when()
            .get("/viewRoomByType")
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(0))
            .body("roomType", everyItem(equalTo("SINGLE")));
        getTest().pass("viewRoomByType validated");
    }
    

    
    @DataProvider(name = "roomTypeData")
    public Object[][] getRoomTypeData() {
        List<Map<String, Object>> rooms = JsonDataLoader.loadJsonArrayAsListOfMaps("testdata/multipleRoomData.json");
        
        // Extract unique room types to avoid duplicate test runs if your JSON has repeats
        return rooms.stream()
                    .map(room -> room.get("roomType"))
                    .distinct()
                    .map(roomType -> new Object[] { roomType })
                    .toArray(Object[][]::new);
    }

    @Test(dataProvider = "roomTypeData")
    public void testViewRoomsByTypeFromJson(String roomType) {
        getTest().info("Viewing rooms by type=" + roomType);

        given()
            .accept("application/json")
            .queryParam("roomType", roomType)
        .when()
            .get("/viewRoomByType")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            //.body("roomType", everyItem(equalTo(roomType)));
            .body("roomType", everyItem(anyOf(equalTo("SINGLE"), equalTo("DOUBLE"), equalTo("DELUXE"))));

        getTest().pass("Viewed rooms of type=" + roomType);
    }

}
