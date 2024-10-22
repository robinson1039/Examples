package org.example;

import org.example.utils.WebDriverManager;
import org.example.pageObject.SearchProductPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.example.utils.Categories;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class SearchTest {
    private WebDriver driver;
    private SearchProductPage searchProductPage;
    Categories categories = new Categories();
    String[] categoryList = categories.getCategories();
    private ExtentReports extentReports;
    private ExtentTest test;

    @Before
    public void setUp() {
        // Configura el WebDriver utilizando WebDriverManager
        driver = WebDriverManager.driveConfig();
        // Inicializa el Page Object de SearchProductPage
        searchProductPage = new SearchProductPage(driver);
    }

    @Test
    public void searchAndSelectRandomItemTest() {
        for (String category : categoryList) {
            // Ingresa el término de búsqueda
            searchProductPage.enterSearchTerm(category);
            // Haz clic en el botón de búsqueda
            searchProductPage.clickSearchButton();
            // Selecciona un ítem aleatorio de los resultados
            searchProductPage.selectRandomItem();

            searchProductPage.selectItemAndAddToCart();
        }
    }

    @After
    public void tearDown() {
        // Cierra el navegador después de la prueba
        if (driver != null) {
            driver.quit();
        }
    }
}


