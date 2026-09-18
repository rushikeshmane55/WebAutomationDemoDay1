import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class welcomeExplicitWait {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("file:///C:/Users/ccst/Downloads/welcome.html");
            driver.manage().window().maximize();

            WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
            nameInput.clear();
            nameInput.sendKeys("Divyansh Gupta");
            System.out.println("Name entered: " + nameInput.getAttribute("value"));

            WebElement enterBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("enterBtn")));
            System.out.println("Enter button is clickable; clicking now.");
            enterBtn.click();

            Thread.sleep(1000);
            System.out.println("Action completed.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
