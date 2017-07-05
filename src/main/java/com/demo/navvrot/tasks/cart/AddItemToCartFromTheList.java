package com.demo.navvrot.tasks.cart;

import com.demo.navvrot.actions.SetSessionVariable;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import org.openqa.selenium.By;

import java.util.List;

import static com.demo.navvrot.pageobjects.CartProductList.PRODUCTS_LIST_IN_CART;
import static com.demo.navvrot.pageobjects.ProductList.PRODUCTS_LIST;
import static com.demo.navvrot.utils.Attributes.PRODUCT_PRICE;
import static com.demo.navvrot.utils.SessionVariables.ADDED_TO_CART_ITEM_NAME;
import static com.demo.navvrot.utils.SessionVariables.ADDED_TO_CART_ITEM_PRICE;
import static com.demo.navvrot.utils.Utils.parseIntFromString;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class AddItemToCartFromTheList implements Task {
    private int position;

    public AddItemToCartFromTheList(int position) {
        this.position = position;
    }

    public static AddItemToCartFromTheList onPosition(int position) {
        return instrumented(AddItemToCartFromTheList.class, position);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<WebElementFacade> listOfProducts = PRODUCTS_LIST.resolveAllFor(actor);
        WebElementFacade productToAdd = listOfProducts.get(position - 1);
        String nameOfProduct = getNameOfProduct(productToAdd);
        int priceOfProduct = getPriceOfItem(productToAdd);
        WebElementFacade cartButtonOfProductToBeAdded = getButtonToAddToCartOf(productToAdd);

        actor.attemptsTo(
                SetSessionVariable.namedWithValue(ADDED_TO_CART_ITEM_PRICE, priceOfProduct),
                SetSessionVariable.namedWithValue(ADDED_TO_CART_ITEM_NAME, nameOfProduct),
                Click.on(cartButtonOfProductToBeAdded));

        PRODUCTS_LIST_IN_CART.resolveFor(actor).waitUntilVisible();
    }

    private int getPriceOfItem(WebElementFacade productToAdd) {
        String priceText = productToAdd.getAttribute(PRODUCT_PRICE);
        return parseIntFromString(priceText);
    }

    private WebElementFacade getButtonToAddToCartOf(WebElementFacade productToAdd) {
        return productToAdd.then(By.className("js-add-to-cart"));
    }

    private String getNameOfProduct(WebElementFacade productToAdd) {
        return productToAdd.find(By.className("name")).getText();
    }
}
