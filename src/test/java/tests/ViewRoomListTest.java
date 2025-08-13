
package tests;

import base.TestBase;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ViewRoomListTest extends TestBase {

    @Test
    public void testViewRoomList() {
        getTest().info("Calling viewRoomList");
        given()
            .accept("application/json")
        .when()
            .get("/viewRoomList")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("roomType", everyItem(anyOf(equalTo("SINGLE"), equalTo("DOUBLE"), equalTo("DELUXE"))));
        getTest().pass("viewRoomList validated");
    }
}
