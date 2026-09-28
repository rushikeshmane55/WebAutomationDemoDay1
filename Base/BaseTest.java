package Base;

import SetUp.GlobalAuthSetup;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import config.Testconfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {
    public Playwright obj_playwright;
    public Browser obj_browser;
    public BrowserContext obj_context;
    public Page obj_page;

    @BeforeMethod
    public void setUp() {
        Path statePath = Paths.get(Testconfig.USER_STATE);

        // Auto-generate auth state file if it doesn't exist yet
        if (!Files.exists(statePath)) {
            GlobalAuthSetup.loginAndSaveState(
                    Testconfig.USERNAME,
                    Testconfig.PASSWORD,
                    Testconfig.USER_STATE
            );
        }

        obj_playwright = Playwright.create();
        obj_browser = obj_playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
        );
        obj_context = obj_browser.newContext(
                new Browser.NewContextOptions().setStorageStatePath(statePath)
        );
        obj_page = obj_context.newPage();
    }

    @AfterMethod
    public void tearDown() {
        if (obj_context != null) {
            obj_context.close();
        }
        if (obj_browser != null) {
            obj_browser.close();
        }
        if (obj_playwright != null) {
            obj_playwright.close();
        }
    }
}