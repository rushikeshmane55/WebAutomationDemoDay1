import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AjaxBased {

    private static final String FORM_URL = "file:///C:/Users/ccst/Downloads/challenge_AjaxPage.html";
    private static final String SUCCESS_PAGE = "welcome.html";

    private static final String FULLNESS_ID = "fullness";
    private static final String ADDRESS_ID = "address";
    private static final String AGE_ID = "age";
    private static final String PLACE_OF_BIRTH_ID = "place of birth";
    private static final String GENDER_ID = "Male";
    private static final String LANGUAGE_ID = "languages";


    private static final String FULLNESS = "Divyansh";
    private static final String ADDRESS = "Pashen";
    private static final String AGE = "24";
    private static final String PLACE_OF_BIRTH = "Jabalpur";
    private static final String LANGUAGE = "english";
    private static final int WAIT_TIME = 5;

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            driver.get(FORM_URL);


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME));


            WebElement fullNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(FULLNESS_ID)));
            fullNameField.sendKeys(FULLNESS);

            driver.findElement(By.id(ADDRESS_ID)).sendKeys(ADDRESS);
            driver.findElement(By.id(AGE_ID)).sendKeys(AGE);
            driver.findElement(By.id(PLACE_OF_BIRTH_ID)).sendKeys(PLACE_OF_BIRTH);


            WebElement genderRadio = driver.findElement(By.id(GENDER_ID));
            if (!genderRadio.isSelected()) {
                genderRadio.click();
            }


            WebElement languageDropdown = driver.findElement(By.id(LANGUAGE_ID));
            Select selectLanguage = new Select(languageDropdown);
            selectLanguage.selectByValue(LANGUAGE);


            System.out.println("Form details filled successfully for: " + FULLNESS);


        } catch (Exception e) {
            System.err.println("An error occurred during automation execution: " + e.getMessage());
            e.printStackTrace();
        } finally {

            driver.quit();
        }
    }
}

