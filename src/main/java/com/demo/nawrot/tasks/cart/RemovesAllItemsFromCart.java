package com.demo.nawrot.tasks.cart;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import org.openqa.selenium.By;

import java.util.List;

import static com.demo.nawrot.pageobjects.CartProductList.PRODUCTS_LIST_IN_CART;
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

    private WebElementFacade getRemoveButtonOf(WebElementFacade itemToRemove) {
        return itemToRemove.find(By.xpath("//a[contains(@class,'remove-product')]"));
    }

    public static RemovesAllItemsFromCart list() {
        return instrumented(RemovesAllItemsFromCart.class);
    }

}
