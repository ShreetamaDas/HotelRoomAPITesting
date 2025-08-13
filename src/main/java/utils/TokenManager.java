package utils;

import static io.restassured.RestAssured.*; // ✅ Import RestAssured given()

import io.restassured.response.Response;

public class TokenManager {

    public static String getAuthCode() {
        String authUrl = ConfigLoader.get("authUrl");
        String username = ConfigLoader.get("username");
        String password = ConfigLoader.get("password");

        Response res = given()
                .baseUri(authUrl)
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", username)
                .formParam("password", password)
        .when()
                .post("/login")
        .then()
                .extract().response();

        try {
            return res.jsonPath().getString("auth_code");
        } catch (Exception e) {
            return null;
        }
    }

    public static String getAccessToken() {
        String authCode = getAuthCode();
        if (authCode == null) return ConfigLoader.get("accessToken"); // fallback if set in properties

        String authUrl = ConfigLoader.get("authUrl");
        Response res = given()
                .baseUri(authUrl)
                .contentType("application/x-www-form-urlencoded")
                .formParam("auth_code", authCode)
        .when()
                .post("/token")
        .then()
                .extract().response();

        try {
            return res.jsonPath().getString("access_token");
        } catch (Exception e) {
            return null;
        }
    }
}
