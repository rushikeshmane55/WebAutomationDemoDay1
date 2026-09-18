import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class hiddenElementJavascriptExecutor {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {

            driver.get("file:///C:/Users/ccst/Downloads/hiddenElementJavascriptExecutor.html");


            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement scrollTarget = driver.findElement(By.id("scrollTargetBtn"));
            js.executeScript("arguments[0].scrollIntoView(true);", scrollTarget);

            Thread.sleep(5000);


            js.executeScript("document.getElementById('hiddenBtn').click();");
            System.out.println("Successfully clicked the hidden button using JavascriptExecutor!");

            Thread.sleep(500);
            WebElement resultParagraph = driver.findElement(By.id("result"));
            System.out.println("Result text displayed on page: " + resultParagraph.getText());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
