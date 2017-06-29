package com.demo.nawrot.pageobjects;

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

    public CartProductList(WebDriver driver) {
        super(driver);
    }
}
