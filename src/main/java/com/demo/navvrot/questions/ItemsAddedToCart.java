package com.demo.navvrot.questions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.demo.navvrot.pageobjects.CartProductList.PRODUCTS_LIST_IN_CART;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class ItemsAddedToCart implements Question<List<String>> {

    public static ItemsAddedToCart list() {
        return new ItemsAddedToCart();
    }

    @Override
    public List<String> answeredBy(Actor actor) {
        List<WebElementFacade> itemsInCart = PRODUCTS_LIST_IN_CART.resolveAllFor(actor);
        List<String> itemsNamesInCart = new ArrayList<>();
        itemsInCart.forEach(item -> itemsNamesInCart.add(item.find(By.className("name")).getText()));

        return itemsNamesInCart;
    }
}
