package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
    @Test()
    public void incorrectLoginCheck() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(driver.findElement(By.xpath("//*[text()='Products']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("//*[@class='title']")).getText(), "Products");
    }

    @Test
    public void emptyPasswordInputCheck() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(driver.findElement(By.xpath("//h3")).getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void lockedOutUserLoginCheck() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertEquals(driver.findElement(By.xpath("//h3")).getText(),
                "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void problemUserLoginEmptyPasswordCheck() {
        loginPage.open();
        loginPage.login("problem_user", "");
        assertEquals(driver.findElement(By.xpath("//h3")).getText(),
                "Epic sadface: Password is required");
    }
    @Test
    public void emptyUserEmptyPasswordCheck() {
        loginPage.open();
        loginPage.login("", "");
        assertEquals(driver.findElement(By.xpath("//h3")).getText(),
                "Epic sadface: Username is required");
    }
}
