package Session;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.nio.file.Paths;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;

public class SessionStorage {
    // Input data
    static String userName = "standard_user";
    static String password = "secret_sauce";

    // Locators
    static String css_loginButton = "#login-button"; // Simplified and fixed broken quote escaping
    static String id_userName = "#user-name";
    static String id_password = "#password";

    public static void main(String[] args) throws InterruptedException {
        try (Playwright obj_playwright = Playwright.create()) {
            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));

            // --- Step 1: Login and save state ---
            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();
            obj_page.navigate("https://www.saucedemo.com");

            Locator obj_userNameLocator = obj_page.locator(id_userName);
            obj_userNameLocator.fill(userName);

            obj_page.locator(id_password).fill(password);
            obj_page.locator(css_loginButton).click();

            assertThat(obj_page).hasURL("https://www.saucedemo.com/inventory.html");

            // Save authentication storage state (cookies & local storage)
            obj_context.storageState(new BrowserContext.StorageStateOptions()
                    .setPath(Paths.get("storageState.json")));

            obj_page.close();
            obj_context.close();

            Thread.sleep(1000);

            // --- Step 2: Reuse saved session state ---
            BrowserContext obj_context1 = obj_browser.newContext(
                    new Browser.NewContextOptions()
                            .setStorageStatePath(Paths.get("storageState.json")));

            // Open a new page to verify logged-in state without re-entering credentials
            Page obj_page1 = obj_context1.newPage();
            obj_page1.navigate("https://www.saucedemo.com/inventory.html");
            assertThat(obj_page1).hasURL("https://www.saucedemo.com/inventory.html");

            obj_page1.close();
            obj_context1.close();
        }
    }
}