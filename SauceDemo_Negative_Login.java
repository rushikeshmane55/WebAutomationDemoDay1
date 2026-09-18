import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceDemo_Negative_Login {
    private static final String URL = "https://www.saucedemo.com";
    private static final String USERNAME_INPUT_ID = "user-name";
    private static final String PASSWORD_INPUT_ID = "password";
    private static final String LOGIN_BUTTON_ID = "login-button";
    private static final String ERROR_XPATH = "//h3[@data-test='error']";
    private static final String EXPECTED_ERROR = "Epic sadface: Username and password do not match any user in this service";
    private static final String USERNAME = "invalid_user";
    private static final String PASSWORD = "wrong_password";
    private static final int WAIT_TIME = 5;

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            driver.get(URL);
            login(driver);
            validateError(driver);
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void login(WebDriver driver) {
        driver.findElement(By.id(USERNAME_INPUT_ID)).sendKeys(USERNAME);
        driver.findElement(By.id(PASSWORD_INPUT_ID)).sendKeys(PASSWORD);
        driver.findElement(By.id(LOGIN_BUTTON_ID)).click();
    }

    private static void validateError(WebDriver driver) {
        String actualError = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ERROR_XPATH)))
            .getText();

        String result = actualError.equals(EXPECTED_ERROR)
            ? "Test passed: error message is correct"
            : "Test failed: " + actualError;

        System.out.println(result);
    }
}
