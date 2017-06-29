package com.demo.nawrot.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebDriver;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class NavigationBar extends PageObject {

    public static final Target CATEGORY_ONE = Target.the("First category").locatedBy("//li[@class='nav-item-1']");

    public NavigationBar(WebDriver driver) {
        super(driver);
    }
}
