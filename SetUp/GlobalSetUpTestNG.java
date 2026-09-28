package SetUp;

import config.Testconfig;
import org.testng.annotations.BeforeSuite;

public class GlobalSetUpTestNG {

    @BeforeSuite
    public void globalSetup() {
        GlobalAuthSetup.loginAndSaveState(
                Testconfig.USERNAME,
                Testconfig.PASSWORD,
                Testconfig.USER_STATE
        );
        GlobalAuthSetup.createExpiredState(Testconfig.EXPIRED_STATE);
    }
}
