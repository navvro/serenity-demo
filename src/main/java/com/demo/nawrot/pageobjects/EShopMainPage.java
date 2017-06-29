package com.demo.nawrot.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
@DefaultUrl("https://www.x-kom.pl")
public class EShopMainPage extends PageObject {

    public static final Target CONFIRM_COOKIES_BUTTON = Target.the("Accept cookies policy confirm button")
            .locatedBy("//button[@class='cookie-confirm button button-gray']");

    public EShopMainPage(WebDriver driver) {
        super(driver);
    }
}
