package ccst.Day6;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginFunctionality {
    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;

    public LoginFunctionality(Page page) {
        this.usernameInput = page.locator("#user-name");
        this.passwordInput = page.locator("#password");
        this.loginButton = page.locator("#login-button");
    }

    public void performLogin(String username, String password) {
        usernameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();
    }
}