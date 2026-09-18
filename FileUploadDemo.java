import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FileUploadDemo {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("//path");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement fileInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("fileInput"))
        );
        String filePath = "//path";
        fileInput.sendKeys(filePath);
        Thread.sleep(5000);
        System.out.println("File path send to input field.");
        WebElement fileNameLabel = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("fileName"))
        );
        if (fileNameLabel.getText().contains("actionClass_Menu.html")){
            String filename;
            System.out.println("PASS:File Selected and displayed correctly. Text:"+ fileNameLabel);
        }else{
            System.out.println("FAIL: File name not displayed as expected. Actual:"+ fileNameLabel);
        }
        WebElement uploadBtn=driver.findElement(By.id("uploadBtn"));
        uploadBtn.click();
        Thread.sleep(5000);
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("result"))
        );
        String expectedMessage = "file 'actionClass_Menu.html' upload successfully!";
        if (result.getText().equals(expectedMessage))
        {
            System.out.println("PASS: Upload confirmed. Message: "+ result.getText());
        }
        else{
            System.out.println("FAIL: Upload message mismatch. Actual: "+ result.getText());
        }
        driver.quit();
    }
}
