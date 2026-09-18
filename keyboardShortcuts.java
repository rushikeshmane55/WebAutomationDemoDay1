/*import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class keyboardShortcuts {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("//path");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        WebElement sourceText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("sourceText"))
        );
        WebElement targetText = driver.findElement(By.id("targetText"));
        sourceText.click();
        actions.keyDown(keys.CONTROL)
                .sendkeys("a")
                .keyUp(keys.CONTROL)
                .perform();
        System.out.println("Select All performed on source container.");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.textToBe(By.id("result"),"Text copied succesfully");
    }
}
*/