package com.kendall.qa.test.tests;

import com.kendall.qa.test.base.BaseTest;
import com.kendall.qa.test.pages.ProductPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductTest extends BaseTest {

    @Test
    public void buscarProductoExitoso() {
        ProductPage productPage = new ProductPage(driver);
        productPage.goToProducts();
        productPage.searchFor("dress");
        assertTrue(productPage.areResultsVisible("dress"));
    }

    @Test
    public void verDetalleDeProducto() {
        ProductPage productPage = new ProductPage(driver);
        productPage.goToProducts();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement viewProductBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[text()='View Product'])[1]")));
        
        // Usamos JavaScript para saltar el anuncio que intercepta el clic
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewProductBtn);
        
        assertTrue(driver.getCurrentUrl().contains("product_details"));
    }
}