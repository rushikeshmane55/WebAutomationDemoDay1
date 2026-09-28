package SetUp;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import config.Testconfig;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GlobalAuthSetup {

    public static void loginAndSaveState(String username, String password, String outputPath) {
        try (Playwright obj_playwright = Playwright.create()) {
            Browser obj_browser = obj_playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)
            );

            BrowserContext obj_context = obj_browser.newContext();
            Page obj_page = obj_context.newPage();

            obj_page.navigate(Testconfig.LOGIN_URL);
            obj_page.fill("#user-name", username);
            obj_page.fill("#password", password);
            obj_page.click("#login-button");

            // Wait for successful login redirection
            obj_page.waitForURL("**/inventory.html");

            // Ensure parent directory exists before saving
            Path path = Paths.get(outputPath);
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            // Save storage state
            obj_context.storageState(
                    new BrowserContext.StorageStateOptions().setPath(path)
            );

            obj_browser.close();
        } catch (Exception e) {
            throw new RuntimeException("Login Failed for user: " + username, e);
        }
    }

    // Creates an expired/empty session file for negative testing
    public static void createExpiredState(String outputPath) {
        try {
            Path path = Paths.get(outputPath);
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            Files.writeString(path, "{\"cookies\":[],\"origins\":[]}");
        } catch (Exception e) {
            throw new RuntimeException("Failed to create expired state", e);
        }
    }
}