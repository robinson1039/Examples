package org.example.locators;

import org.openqa.selenium.By;

public class ItemsLocator {
    public static final By SEARCH_ITEMS = By.cssSelector("a[class=\"multi--container--1UZxxHY cards--card--3PJxwBm search-card-item\"]");
    public static final By ADD_ITEM_TO_CAR = By.cssSelector("[class*=\"add-to-cart\"]");
    public static final By MORE_ITEMS = By.cssSelector("[class=\"comet-v2-input-number-btn comet-v2-input-number-btn-increase\"]");
    public static final By NO_MORE_ITEMS = By.cssSelector("div[class=\"quantity--info--jnoo_pD\"]");
}
