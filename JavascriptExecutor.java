/*import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;


public class JavascriptExecutor {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            driver.get("https://google.com");
            driver.manage().window().maximize();

            double zoomLevelBefore = Double.parseDouble(js.executeScript("return window.devicePixelRatio;").toString());
            System.out.println("Initial zoom ratio: " + zoomLevelBefore);

            Actions actions = new Actions(driver);
            actions.keyDown(Keys.CONTROL)
                    .sendKeys(Keys.ADD)
                    .keyUp(Keys.CONTROL)
                    .perform();

            Thread.sleep(1000);

            double zoomLevelAfter = Double.parseDouble(js.executeScript("return window.devicePixelRatio;").toString());
            System.out.println("Current zoom ratio: " + zoomLevelAfter);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private Object executeScript(String s) {
    }
}
*/
