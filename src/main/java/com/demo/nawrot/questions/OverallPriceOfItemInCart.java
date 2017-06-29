package com.demo.nawrot.questions;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.regex.Matcher;

import static com.demo.nawrot.pageobjects.CartProductList.ITEM_PRICE_SELECTOR;
import static com.demo.nawrot.pageobjects.CartProductList.PRODUCTS_LIST_IN_CART;
import static com.demo.nawrot.utils.RegExps.TRIMMED_OVERALL_PRICE_REGEXP;
import static com.demo.nawrot.utils.Utils.*;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class OverallPriceOfItemInCart implements Question<Integer> {
    private int itemPosition;

    public OverallPriceOfItemInCart(int itemPosition) {
        this.itemPosition = itemPosition;
    }

    @Override
    public Integer answeredBy(Actor actor) {
        WebElementFacade product = getProductVisibleBy(actor);
        WebElementFacade productPrice = product.find(By.xpath(ITEM_PRICE_SELECTOR));
        Matcher matcher = MatcherWithPriceFromElement
                .matchedWithRegExp(productPrice, TRIMMED_OVERALL_PRICE_REGEXP).answeredBy(actor);
        matcher.find();

        return parseIntFromString(matcher.group(1));
    }

    private WebElementFacade getProductVisibleBy(Actor actor) {
        return PRODUCTS_LIST_IN_CART.resolveAllFor(actor).get(itemPosition - 1);
    }

    public static OverallPriceOfItemInCart onPosition(int itemPosition) {
        return new OverallPriceOfItemInCart(itemPosition);
    }
}
