package com.demo.navvrot.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebDriver;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class ProductList extends PageObject {

    public static final Target PRODUCTS_LIST = Target.the("Product list")
            .locatedBy("//div[@class='product-item product-impression']");

    public ProductList(WebDriver driver) {
        super(driver);
    }
}
