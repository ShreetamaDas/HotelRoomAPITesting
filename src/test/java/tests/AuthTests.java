package tests;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import base.TestBase;
import utils.TokenManager;

public class AuthTests extends TestBase {

    @Test
    public void testGetAuthAndToken() {
        String authCode = TokenManager.getAuthCode();
        getTest().info("Auth code: " + authCode);
        assertNotNull(authCode, "Auth code should not be null");

        String token = TokenManager.getAccessToken();
        getTest().info("Access token: " + token);
        assertNotNull(token, "Access token should not be null");
    }
}
