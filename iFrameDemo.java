import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;



public class iFrameDemo {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("file:///C:/Users/ccst/Downloads/iFrameDemo.html");
        driver.manage().window().maximize();

        driver.switchTo().frame(0);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("frame1Btn"))).click();
        Thread.sleep(2000);

        String frame1Result = driver.findElement(By.id("frame1Result")).getText();
        if (frame1Result.equals("Frame 1 button clicked!")) {
            System.out.println("PASS: Frame 1 (by index) handled correctly");
        } else {
            System.out.println("Fail: Frame 1 result mismatch.");
        }

        driver.switchTo().defaultContent();
        Thread.sleep(2000);

        driver.switchTo().frame("frameByName");
        WebElement frame2Input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frame2Input")));
        frame2Input.sendKeys("Selenium Student");

        String enteredValue = frame2Input.getAttribute("value");
        if (enteredValue.equals("Selenium Student")) {
            System.out.println("PASS: Frame 2 by name handled correctly");
        } else {
            System.out.println("Fail: frame 2 input mismatch");
        }
        Thread.sleep(2000);

        driver.switchTo().defaultContent();

        WebElement frame3Element = driver.findElement(By.id("frame3"));
        driver.switchTo().frame(frame3Element);

        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("select")));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(1);
        Thread.sleep(2000);

        driver.switchTo().defaultContent();
        driver.quit();
    }
}
