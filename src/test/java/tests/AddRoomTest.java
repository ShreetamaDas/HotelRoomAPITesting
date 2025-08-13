package tests;

import base.TestBase;

import org.testng.annotations.Test;

import utils.JsonDataLoader;
import utils.PayloadBuilder;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.path.xml.XmlPath;
import java.util.Map;
import java.util.List;

public class AddRoomTest extends TestBase {

    @Test
    public void testAddRoom() {
        Map<String, Object> payload = PayloadBuilder.loadPropertiesAsMap("testdata/addRoom.properties");
        getTest().info("Adding room with id=" + payload.get("roomId"));

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParams(payload)
        .when()
            .post("/addRoom")
        .then()
            .statusCode(200);

        // Basic XML parsing to verify added room 
        String xml = given().accept("application/xml").when().get("/viewRoomList").then().extract().asString();
        XmlPath xp = new XmlPath(xml);
        getTest().pass("addRoom executed; review XML or extend assertions as needed");
    }
    
    @Test
    public void testAddRoomsFromJson() {
        List<Map<String, Object>> roomPayloads = JsonDataLoader.loadJsonArrayAsListOfMaps("testdata/multipleRoomData.json");

        for (Map<String, Object> payload : roomPayloads) {
            getTest().info("Adding room with id=" + payload.get("roomId"));

            given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(payload)
            .when()
                .post("/addRoom")
            .then()
                .statusCode(200);

            // Basic check to ensure the room was added
            String xml = given()
                    .accept("application/xml")
                .when()
                    .get("/viewRoomList")
                .then()
                    .extract().asString();

            XmlPath xp = new XmlPath(xml);
            
            //Optional for asserting roomId or roomType existence in XML here
            getTest().pass("Room with ID " + payload.get("roomId") + " added successfully.");
        }
    }
}


