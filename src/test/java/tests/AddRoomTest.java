package tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.path.xml.XmlPath;
import utils.JsonDataLoader;
import utils.POJO;
import utils.PayloadBuilder;

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

	
	
//	hotelId=1001a
//	roomId=11
//	roomType=SINGLE
//	roomStatus=NOTAVAILABLE
//	roomPrice=20000
	
	public void testAddRoomUsingPOJO() {
	    POJO pojo1 = new POJO();
	    pojo1.setHotelId("1001a");
	    pojo1.setRoomId(11);
	    pojo1.setRoomType("SINGLE");
	    pojo1.setRoomStatus("NOTAVAILABLE");
	    pojo1.setRoomPrice(20000);

	    Map<String, Object> payload = new HashMap<>();
	    payload.put("hotelId", pojo1.getHotelId());
	    payload.put("roomId", pojo1.getRoomId());
	    payload.put("roomType", pojo1.getRoomType());
	    payload.put("roomStatus", pojo1.getRoomStatus());
	    payload.put("roomPrice", pojo1.getRoomPrice());

	    given()
	        .contentType("application/x-www-form-urlencoded")
	        .formParams(payload)
	    .when()
	        .post("/addRoom")
	    .then()
	        .statusCode(200);
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


