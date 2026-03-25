package com.kendall.qa.test.tests;

import com.kendall.qa.test.base.BaseTest;
import com.kendall.qa.test.pages.LoginPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By; 
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {

    @Test
    public void loginValido() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLogin();
        loginPage.enterEmail("keny_test@fidelitas.cr");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();
        assertTrue(loginPage.isLoggedInAs("Keny"), "El login no fue exitoso para el usuario Keny");
    }

    @Test
    public void loginInvalido() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLogin();
        loginPage.enterEmail("error@fidelitas.cr");
        loginPage.enterPassword("000000");
        loginPage.clickLogin();
        String actualError = loginPage.getErrorMessage();
        assertEquals("Your email or password is incorrect!", actualError);
    }

    @Test
    public void logoutExitoso() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goToLogin();
        loginPage.enterEmail("keny_test@fidelitas.cr");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();
        
        driver.findElement(By.linkText("Logout")).click();
        assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Login')]")).isDisplayed());
    }
}