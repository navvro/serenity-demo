package com.demo.navvrot.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebDriver;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class CartProductList extends PageObject {

    public static final Target PRODUCTS_LIST_IN_CART = Target.the("Product list in cart")
            .locatedBy("//div[@class='basket-item']");

    public static final Target EMPTY_CART_MESSAGE = Target.the("Empty basket element")
            .locatedBy("//div[@id='basketEmpty']");

    public static final Target BASKET_OVERLAY = Target.the("Overlay")
            .locatedBy("//div[@id='basketOverlay']");

    public static final Target RESTORE_CART_BUTTON = Target.the("Restore button")
            .locatedBy("//a[@title='Przywróć koszyk']");

    public static final Target SPINNER = Target.the("Overlay spinner")
            .locatedBy("//div[@class='spinner']");

    public static final Target OVERALL_CART_PRICE = Target.the("Sum of whole cart")
            .locatedBy("//span[contains(@class,'js-basket-price')]");

    public static final String ITEM_PRICE_SELECTOR = "//span[@class='overallprice']";

    public static final String REMOVE_BUTTON_SELECTOR = "//a[contains(@class,'remove-product')]";

    public static final String AMOUNT_OF_ITEM_INPUT_SELECTOR = "//input[contains(@class, 'js-quantity-input')]";

    public CartProductList(WebDriver driver) {
        super(driver);
    }
}
