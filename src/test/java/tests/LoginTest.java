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
        //assertTrue(driver.findElement(By.xpath("//*[@class='title']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//*[text()='Products']")).isDisplayed());
        //, "Oops, error on page. ZIP code should have 5 digits"
        assertEquals(driver.findElement(By.xpath("//*[@class='title']")).getText(), "Products");
    }

    @Test
    public void emptyPasswordInputCheck() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        //assertTrue(driver.findElement(By.xpath("//*[@class='title']")).isDisplayed());
        //assertTrue(driver.findElement(By.xpath("//*[text()='Products']")).isDisplayed());
        //, "Oops, error on page. ZIP code should have 5 digits"
        assertEquals(driver.findElement(By.xpath("//h3")).getText(),
                "Epic sadface: Username and password do not match any user in this service");
    }
}
