package com.demo.navvrot.questions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static com.demo.navvrot.pageobjects.CartProductList.PRODUCTS_LIST_IN_CART;
import static com.demo.navvrot.utils.Attributes.PRODUCT_PRICE;
import static com.demo.navvrot.utils.Utils.parseIntFromString;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class UnitPriceOfItemInCart implements Question<Integer> {
    private int itemPosition;

    public UnitPriceOfItemInCart(int itemPosition) {
        this.itemPosition = itemPosition;
    }

    public static UnitPriceOfItemInCart onPosition(int itemPosition) {
        return new UnitPriceOfItemInCart(itemPosition);
    }

    @Override
    public Integer answeredBy(Actor actor) {
        return parseIntFromString(getPriceFromCartItemVisibleBy(actor));
    }

    private String getPriceFromCartItemVisibleBy(Actor actor) {
        WebElementFacade item = PRODUCTS_LIST_IN_CART.resolveAllFor(actor).get(itemPosition - 1);
        return item.getAttribute(PRODUCT_PRICE);
    }
}
