package com.demo.navvrot.tasks.wait;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static com.demo.navvrot.pageobjects.CartProductList.BASKET_OVERLAY;
import static com.demo.navvrot.pageobjects.CartProductList.SPINNER;
import static com.demo.navvrot.utils.Utils.LOGGER;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class OverlayInvisibility implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            BASKET_OVERLAY.resolveFor(actor).waitUntilNotVisible();
            SPINNER.resolveFor(actor).waitUntilNotVisible();
            BASKET_OVERLAY.resolveFor(actor).waitUntilNotVisible();
            SPINNER.resolveFor(actor).waitUntilNotVisible();
        } catch (Exception e) {
            LOGGER.debug("Exception during waiting for overlay to be invisible", e);
        }
    }
}
