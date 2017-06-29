package com.demo.nawrot.tasks.cart;

import com.demo.nawrot.actions.SetSessionVariable;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import org.openqa.selenium.By;

import java.util.List;

import static com.demo.nawrot.pageobjects.CartProductList.PRODUCTS_LIST_IN_CART;
import static com.demo.nawrot.pageobjects.ProductList.PRODUCTS_LIST;
import static com.demo.nawrot.utils.SessionVariables.ADDED_TO_CART_ITEM_NAME;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class AddItemToCartFromTheList implements Task {
    private int position;

    public AddItemToCartFromTheList(int position) {
        this.position = position;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<WebElementFacade> listOfProducts = PRODUCTS_LIST.resolveAllFor(actor);
        WebElementFacade productToAdd = listOfProducts.get(position - 1);
        String nameOfProduct = getNameOfProduct(productToAdd);
        WebElementFacade cartButtonOfProductToBeAdded = getButtonToAddToCartOf(productToAdd);

        actor.attemptsTo(
                SetSessionVariable.namedWithValue(ADDED_TO_CART_ITEM_NAME, nameOfProduct),
                Click.on(cartButtonOfProductToBeAdded));

        PRODUCTS_LIST_IN_CART.resolveFor(actor).waitUntilVisible();
    }

    private WebElementFacade getButtonToAddToCartOf(WebElementFacade productToAdd) {
        return productToAdd.then(By.className("js-add-to-cart"));
    }

    private String getNameOfProduct(WebElementFacade productToAdd) {
        return productToAdd.find(By.className("name")).getText();
    }

    public static AddItemToCartFromTheList onPosition(int position) {
        return instrumented(AddItemToCartFromTheList.class, position);
    }
}
