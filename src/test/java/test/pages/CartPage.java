package com.kendall.qa.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By cartLink = By.xpath("//a[@href='/view_cart']");
    By cartEmptyMsg = By.id("empty_cart");
    By deleteButton = By.className("cart_quantity_delete");

    public void goToCart() {
        driver.findElement(cartLink).click();
    }

    public void deleteProduct() {
        // Espera a que el botón X aparezca y le da click
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }

    public boolean isCartEmpty() {
        // Espera a que el mensaje de "Cart is empty" sea visible
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartEmptyMsg)).isDisplayed();
    }
}