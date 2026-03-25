package com.kendall.qa.test.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    private By loginLink = By.linkText("Signup / Login");
    private By emailField = By.name("email");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[text()='Login']");
    // Selector para el mensaje de error en login fallido
    private By errorMsgLocator = By.xpath("//p[contains(text(),'incorrect')]");

    public void goToLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }

    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsgLocator)).getText();
        } catch (Exception e) {
            return "Mensaje de error no encontrado";
        }
    }

    public boolean isLoggedInAs(String user) {
        try {
            // Espera a que la página cargue tras el login
            wait.until(ExpectedConditions.urlContains("automationexercise.com"));
            return driver.getPageSource().contains("Logged in as " + user) 
                   || driver.getCurrentUrl().equals("https://automationexercise.com/");
        } catch (Exception e) {
            return false;
        }
    }
}