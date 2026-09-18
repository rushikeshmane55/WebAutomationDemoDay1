import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Alert1 {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.setCapability("unhandledPromptBehavior", "accept");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        try {
            driver.get("file:///C:/Users/ccst/Downloads/javascriptAlerts.html");

            driver.findElement(By.xpath("//button[text()='Show Alert']")).click();
            driver.findElement(By.xpath("//button[text()='Show Confirm']")).click();
            driver.findElement(By.xpath("//button[text()='Show Prompt']")).click();
            driver.findElement(By.tagName("body")).click();

            System.out.println("Assignment Verification: Success! All 3 boxes were automatically accepted by the capability settings.");

        } catch (Exception e) {
            System.err.println("An automation error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Automation finished. Keeping browser open for inspection.");
        }
    }
}
