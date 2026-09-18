/*import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ImplicitWait {
    public static void main(String args[])
    {
        ChromeDriver driver = new ChromeDriver();
        {
            try {
                driver.get("//path");
                weDriverWait obj_wait = new webDriverWait(driver, Duration.ofSeconds(10))
                webElement message = obj_wait.until(ExpectedCondition.visibilityOfElementLocated(By.id("message"))

                );
                if (message.getText().equals("welcome!!")) {
                    System.out.println("PASS: WELCOME MESSAGE DISPLAYED CORRECTLY");
                } else {
                    System.out.println("FAIL: MESSAGE TEXT MISMATCH. ACTUAL: " + message.getText());
                }
            }
            }
            catch (Exception e){
            e.printStackTrace();}
        finally{
            driver.quit();
    }
    }


        }
    }

}
*/