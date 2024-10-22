package org.example.pageObject;

import org.example.locators.ItemsLocator;
import org.example.locators.SearchLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.StaleElementReferenceException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class SearchProductPage {
    private WebDriver driver;

    public SearchProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchTerm(String searchTerm) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(SearchLocators.SEARCH_INPUT));
        searchInput.clear();
        searchInput.sendKeys(searchTerm);
    }

    public void clickSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(SearchLocators.SEARCH_BUTTON));
        searchButton.click();
    }

    public void selectRandomItem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ItemsLocator.SEARCH_ITEMS));

        if (!items.isEmpty()) {
            // Seleccionar un elemento aleatorio de la lista
            Random random = new Random();
            int randomIndex = random.nextInt(items.size());
            WebElement randomItem = items.get(randomIndex);
            randomItem.click();
        } else {
            System.out.println("No se encontraron elementos en los resultados de búsqueda.");
        }
    }
    public void selectItemAndAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Guardar el identificador de la ventana original
        String originalWindow = driver.getWindowHandle();

        // Esperar a que se abra una nueva ventana
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Cambiar al contexto de la nueva ventana
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        WebElement quantityInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(ItemsLocator.NO_MORE_ITEMS));
        String actualText = quantityInfo.getText();
        String expectedText = "1 pieza(s) como máximo por cliente";

        if (actualText.contains(expectedText)) {
            WebElement moreItems = null;
        }else{
            WebElement moreItems;
            try{

            moreItems = wait.until(ExpectedConditions.elementToBeClickable(ItemsLocator.MORE_ITEMS));
            }catch (StaleElementReferenceException error){
                System.out.println(error);
                moreItems = null;
            }
            int randomItems = (int) Math.floor(Math.random() * 5);
            for (int i = 0; i < randomItems; i++) {
                try {
                    moreItems.click();
                }catch (StaleElementReferenceException e){
                    System.out.println("El elemento se volvió obsoleto, intentando obtenerlo de nuevo.");

                }
            }
        }

//        if(element != null || noElem !=null) {
//            WebElement moreItems;
//        try{
//
//            moreItems = wait.until(ExpectedConditions.elementToBeClickable(ItemsLocator.MORE_ITEMS));
//        }catch (StaleElementReferenceException error){
//            System.out.println(error);
//            moreItems = null;
//        }
//            int randomItems = (int) Math.floor(Math.random() * 5);
//            for (int i = 0; i < randomItems; i++) {
//                try {
//                    moreItems.click();
//                }catch (StaleElementReferenceException e){
//                    System.out.println("El elemento se volvió obsoleto, intentando obtenerlo de nuevo.");
//
//                }
//            }
//        }

        // Interactuar en la nueva ventana (ejemplo: hacer clic en "Agregar al carrito")
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(ItemsLocator.ADD_ITEM_TO_CAR));
        addToCartButton.click();
        driver.close();
        // Regresar a la ventana original
        driver.switchTo().window(originalWindow);
    }
}
