package ccst.Day6;
import com.microsoft.playwright.*;
import java.nio.file.Paths;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static ccst.Day6.CustomAssertions.assertThat;

public class CustomAssertionUsage {
    @Test
    void testCustomAssertion()
    {
        Playwright obj_playwright = Playwright.create();
        Browser obj_Browser = obj_playwright.chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));;
        BrowserContext obj_Context = obj_Browser.newContext();
        Page obj_page = obj_Context.newPage();

        obj_page.navigate("https://www.saucedemo.com/");
        assertThat(obj_page.locator("#login-button")).hasCssClass("submit");


    }
}
