package ccst.Day6;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Testlogin {
    public static class TestLogin {

        @BeforeMethod
        public void setup() {

        }

        @Test(dataProvider = "loginCredentials", dataProviderClass = LoginDataProvider.class)
        public void testPositiveLoginTestcase(String username, String password) {
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);


        }
    }
}
