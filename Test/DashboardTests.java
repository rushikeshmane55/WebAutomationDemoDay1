package Test;

import Base.BaseTest;
import config.Testconfig;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DashboardTests extends BaseTest {

    @Test
    public void userIsLoggedInWithoutUiLogin() {
        obj_page.navigate(Testconfig.BASE_URL + "/inventory.html");
        assertThat(obj_page).hasURL("https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void userIsLoggedInWithoutUiLoginToCheckout() {
        obj_page.navigate(Testconfig.BASE_URL + "/cart.html");
        assertThat(obj_page).hasURL("https://www.saucedemo.com/cart.html");
    }
}