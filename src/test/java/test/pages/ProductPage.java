package com.kendall.qa.test.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    private By productsLink = By.xpath("//a[contains(@href, '/products')]");
    private By searchInput = By.id("search_product");
    private By submitSearch = By.id("submit_search");
    private By addToCartBtn = By.xpath("(//a[contains(@class, 'add-to-cart')])[1]");
    private By viewCartLink = By.xpath("//u[text()='View Cart']");

    public void goToProducts() {
        try {
            // Intentamos el camino normal: Clic en el menú
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(productsLink));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
            wait.until(ExpectedConditions.urlContains("/products"));
        } catch (Exception e) {
            // Si el servidor está lento, forzamos la navegación directa
            driver.get("https://automationexercise.com/products");
        }
    }

    public void searchFor(String productName) {
        // Doble verificación: si no aparece en 10 seg, la página se refresca
        try {
            wait.withTimeout(Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(searchInput));
        } catch (Exception e) {
            driver.navigate().refresh();
        }

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(productName);
        
        WebElement btnSearch = driver.findElement(submitSearch);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnSearch);
    }

    public boolean areResultsVisible(String productName) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Searched Products')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void addFirstProductToCart() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(addToCartBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        
        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(viewCartLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCart);
    }
}