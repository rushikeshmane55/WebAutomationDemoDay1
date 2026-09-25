package saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SauceDemoCapstone extends BaseTest {


    @DataProvider(name = "loginDataProvider")
    public Object[][] getLoginData() {
        return new Object[][] {

                { "standard_user", "secret_sauce", true, "" },

                { "locked_out_user", "secret_sauce", false, "Epic sadface: Sorry, this user has been locked out." },

                { "standard_user", "invalid_pass", false, "Epic sadface: Username and password do not match any user in this service" }
        };
    }

    private void login(String username, String password) {
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }


    @Test(dataProvider = "loginDataProvider", priority = 1)
    public void testLoginScenarios(String username, String password, boolean isSuccess, String expectedMessage) {
        login(username, password);

        if (isSuccess) {
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Assertion Failed: User not redirected to inventory page.");
            WebElement pageTitle = driver.findElement(By.className("title"));
            Assert.assertEquals(pageTitle.getText().toLowerCase(), "products", "Assertion Failed: Page title mismatch.");
        } else {
            WebElement errorMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']"))
            );
            Assert.assertEquals(errorMsg.getText(), expectedMessage, "Assertion Failed: Error message mismatch.");
        }
    }


    @Test(priority = 2)
    public void testAddThreeProductsToCart() {
        login("standard_user", "secret_sauce");

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();


        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(cartBadge.getText(), "3", "Assertion Failed: Cart badge count is not 3.");


        driver.findElement(By.className("shopping_cart_link")).click();
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        Assert.assertEquals(cartItems.size(), 3, "Assertion Failed: Cart page does not have 3 items.");
    }


    @Test(priority = 3)
    public void testRemoveProductFromProductPage() {
        login("standard_user", "secret_sauce");


        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();


        driver.findElement(By.id("remove-sauce-labs-backpack")).click();


        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(cartBadge.getText(), "2", "Assertion Failed: Cart badge count is not 2 after removal.");

        driver.findElement(By.className("shopping_cart_link")).click();
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        Assert.assertEquals(cartItems.size(), 2, "Assertion Failed: Cart contents size is not 2.");
    }


    @Test(priority = 4)
    public void testCheckoutFromCart() {
        login("standard_user", "secret_sauce");


        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();


        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Jane");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("411001");
        driver.findElement(By.id("continue")).click();


        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishBtn.click();


        WebElement successHeader = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("complete-header"))
        );
        Assert.assertEquals(successHeader.getText(), "Thank you for your order!", "Assertion Failed: Checkout not completed.");
    }


    @Test(priority = 5)
    public void testFilterPriceLowToHigh() {
        login("standard_user", "secret_sauce");


        WebElement dropdown = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(dropdown);
        select.selectByValue("lohi");

        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        List<Double> displayedPrices = new ArrayList<>();

        for (WebElement priceElem : priceElements) {
            String priceNum = priceElem.getText().replace("$", "").trim();
            displayedPrices.add(Double.parseDouble(priceNum));
        }


        List<Double> expectedSortedPrices = new ArrayList<>(displayedPrices);
        Collections.sort(expectedSortedPrices);

        Assert.assertEquals(displayedPrices, expectedSortedPrices, "Assertion Failed: Items not sorted from Price: Low to High.");
    }


    @Test(priority = 6)
    public void testLogout() {
        login("standard_user", "secret_sauce");


        driver.findElement(By.id("react-burger-menu-btn")).click();


        WebElement logoutLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))
        );
        logoutLink.click();


        WebElement loginBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("login-button"))
        );
        Assert.assertTrue(loginBtn.isDisplayed(), "Assertion Failed: User not on login page after logout.");
    }
}
