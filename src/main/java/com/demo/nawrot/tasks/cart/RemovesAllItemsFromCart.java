package com.demo.nawrot.tasks.cart;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import java.util.List;

import static com.demo.nawrot.pageobjects.CartProductList.PRODUCTS_LIST_IN_CART;
import static com.demo.nawrot.tasks.cart.RemoveItemFromCart.getRemoveButtonOf;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class RemovesAllItemsFromCart implements Task {


    @Override
    public <T extends Actor> void performAs(T actor) {
        List<WebElementFacade> itemsInCart = PRODUCTS_LIST_IN_CART.resolveAllFor(actor);

        itemsInCart.forEach(item -> {
            WebElementFacade removeButtonOfItemToRemove = getRemoveButtonOf(item);
            actor.attemptsTo(Click.on(removeButtonOfItemToRemove));
            item.waitUntilNotVisible();
        });
    }

    public static RemovesAllItemsFromCart list() {
        return instrumented(RemovesAllItemsFromCart.class);
    }

}
