import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Alerts{

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {

            driver.get("file:///C:/Users/ccst/Downloads/javascriptAlerts.html");


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            driver.findElement(By.xpath("//button[text()='Show Alert']")).click();


            Alert alertBox = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert Message text: " + alertBox.getText());


            alertBox.accept();
            System.out.println("Alert handled successfully.");



            driver.findElement(By.xpath("//button[text()='Show Confirm']")).click();


            Alert confirmBox = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Confirmation Message text: " + confirmBox.getText());

            confirmBox.dismiss();
            System.out.println("Confirmation box dismissed (Clicked Cancel).");



            driver.findElement(By.xpath("//button[text()='Show Prompt']")).click();


            Alert promptBox = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Prompt Message text: " + promptBox.getText());


            promptBox.sendKeys("Divyansh");

            promptBox.accept();
            System.out.println("Prompt field filled and submitted successfully.");

        } catch (Exception e) {
            System.err.println("An automation error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {

            driver.quit();
        }
    }
}
