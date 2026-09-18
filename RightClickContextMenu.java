import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RightClickContextMenu {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        driver.get("file:///C:/Users/ccst/Downloads/rightClickContextMenuInteraction.html");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        WebElement targetBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("targetBox"))
        );
        actions.contextClick(targetBox).perform();

        WebElement contextMenu = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("contextMenu"))
        );

        if (contextMenu.isDisplayed()) {
            System.out.println("PASS: Context menu appeared after right-click.");
        } else {
            System.out.println("FAIL: Context menu did not appear.");
        }


        WebElement deleteOption = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("deleteOption"))
        );
        deleteOption.click();

        String result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("result"))
        ).getText();

        if (result.equals("Your selected: Delete")) {
            System.out.println("PASS: 'Delete' option selected correctly. Result: " + result);
        } else {
            System.out.println("FAIL: Result mismatch. Actual: " + result);
        }


        driver.quit();
    }
}
