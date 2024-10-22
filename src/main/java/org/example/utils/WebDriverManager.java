package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverManager {
    public static WebDriver driveConfig(){
        try {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.EAGER);
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize(); // Maximizar la ventana del navegador
            driver.get("https://es.aliexpress.com/");
            return driver;
        }catch (Exception e){
            System.err.println("Error al inicializar el WebDriver: " + e.getMessage());
            return null; // o lanza una excepción personalizada
        }
    }
}
