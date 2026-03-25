package com.kendall.qa.test.tests;

import com.kendall.qa.test.base.BaseTest;
import com.kendall.qa.test.pages.LoginPage;
import com.kendall.qa.test.pages.ProductPage;
import com.kendall.qa.test.pages.CartPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {

    @Test
    public void gestionarCarritoExitoso() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        // 1. Iniciar Sesión (Precondición)
        loginPage.goToLogin();
        loginPage.enterEmail("keny_test@fidelitas.cr");
        loginPage.enterPassword("123456");
        loginPage.clickLogin();

        // 2. Agregar producto
        productPage.goToProducts();
        productPage.addFirstProductToCart();

        // 3. Ir al carrito y eliminar
        cartPage.goToCart();
        cartPage.deleteProduct();

        // 4. Validar que esté vacío
        assertTrue(cartPage.isCartEmpty(), "El carrito no quedó vacío después de eliminar el producto.");
    }
}