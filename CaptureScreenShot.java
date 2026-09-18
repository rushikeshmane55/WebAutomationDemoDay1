
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CaptureScreenShot {

    static WebDriver driver;
    static String screenshotDir;

    public static void main(String[] args) {

        screenshotDir = "screenshots";
        new File(screenshotDir).mkdirs();

        try {
            driver = new ChromeDriver();
            driver.get("");
            Thread.sleep(2000);

            String actualtitle = driver.getTitle();
            String expectedtitle = "Driving License Application....";

            if (actualtitle.equals(expectedtitle)) {
                System.out.println("PASS: titles verified. actualtitle: " + actualtitle);
            } else {
                System.out.println("FAIL: title mismatch. expected: " + expectedtitle + " actual: " + actualtitle);
                captureScreenshot(driver, "FAIL_title_mismatch");
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static void captureScreenshot(WebDriver driver, String fileName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path target = Paths.get(screenshotDir, fileName + ".png");
            Files.copy(screenshot.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved to: " + target.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}