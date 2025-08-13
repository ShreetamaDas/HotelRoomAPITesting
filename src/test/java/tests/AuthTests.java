package tests;

import base.TestBase;
import org.testng.annotations.Test;
import utils.TokenManager;
import static org.testng.Assert.*;

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
