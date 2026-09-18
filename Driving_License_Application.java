import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driving_License_Application {
    private static final String FORM_URL = "file:///C:/Users/ccst/Desktop/HTML%20Form/Driving_License_Form.html";
    private static final String SUCCESS_PAGE = "welcome.html";

    private static final String FULLNAME_ID = "fullname";
    private static final String ADDRESS_ID = "address";
    private static final String AGE_ID = "age";
    private static final String PLACE_OF_BIRTH_ID = "placeofbirth";
    private static final String GENDER_XPATH = "//input[@type='radio' and @value='Male']";
    private static final String LANGUAGE_ID = "languages";

    private static final String FULLNAME = "Divyansh";
    private static final String ADDRESS = "Pashan";
    private static final String AGE = "24";
    private static final String PLACE_OF_BIRTH = "Jabalpur";
    private static final String LANGUAGE = "english";
    private static final int WAIT_TIME = 5;

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            driver.get(FORM_URL);
            fillForm(driver);
            submitForm(driver);
            checkSuccess(driver);
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void fillForm(WebDriver driver) {
        driver.findElement(By.id(FULLNAME_ID)).sendKeys(FULLNAME);
        driver.findElement(By.id(ADDRESS_ID)).sendKeys(ADDRESS);
        driver.findElement(By.id(AGE_ID)).sendKeys(AGE);
        driver.findElement(By.id(PLACE_OF_BIRTH_ID)).sendKeys(PLACE_OF_BIRTH);
        driver.findElement(By.xpath(GENDER_XPATH)).click();

        WebElement languageDropdown = driver.findElement(By.id(LANGUAGE_ID));
        Select multiSelect = new Select(languageDropdown);
        multiSelect.selectByValue(LANGUAGE);
    }

    private static void submitForm(WebDriver driver) {
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));
        wait.until(ExpectedConditions.urlContains(SUCCESS_PAGE));
    }

    private static void checkSuccess(WebDriver driver) {
        if (driver.getCurrentUrl().contains(SUCCESS_PAGE)) {
            System.out.println("✔ Application submitted successfully!");
        } else {
            System.out.println("✘ Application submission failed.");
        }
    }
}

