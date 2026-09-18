import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetpPageURLviaJS
{

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            driver.get("file:///C:/Users/ccst/Downloads/hiddenElementJavascriptExecutor.html");

            JavascriptExecutor js = (JavascriptExecutor) driver;

            String title = (String) js.executeScript("return document.title;");
            String url = (String) js.executeScript("return document.URL;");
            System.out.println("Page Title: " + title);
            System.out.println("Page URL: " + url);


            WebElement scrollTargetBtn = driver.findElement(By.id("scrollTargetBtn"));
            js.executeScript("arguments[0].scrollIntoView(true);", scrollTargetBtn);
            Thread.sleep(5000);


            boolean isVisible = scrollTargetBtn.isDisplayed();
            System.out.println("Is 'Treasure' button visible: " + isVisible);

            if (isVisible) {

                scrollTargetBtn.click();


                WebElement resultParagraph = driver.findElement(By.id("result"));
                String resultMessage = resultParagraph.getText();
                System.out.println("Result message after click: " + resultMessage);
            } else {
                System.out.println("Button is not visible on the page.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
