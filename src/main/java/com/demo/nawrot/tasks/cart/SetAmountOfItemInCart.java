package com.demo.nawrot.tasks.cart;

import com.demo.nawrot.tasks.wait.WaitFor;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;

import java.util.List;

import static com.demo.nawrot.pageobjects.CartProductList.AMOUNT_OF_ITEM_INPUT_SELECTOR;
import static com.demo.nawrot.pageobjects.CartProductList.PRODUCTS_LIST_IN_CART;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class SetAmountOfItemInCart implements Task {
    private int itemPosition;
    private String desiredAmount;

    public SetAmountOfItemInCart(int itemPosition, String desiredAmount) {
        this.itemPosition = itemPosition;
        this.desiredAmount = desiredAmount;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<WebElementFacade> itemsInCart = PRODUCTS_LIST_IN_CART.resolveAllFor(actor);
        WebElementFacade itemToSetAmount = itemsInCart.get(itemPosition - 1);
        WebElementFacade itemToSetAmountInput = itemToSetAmount.find(By.xpath(AMOUNT_OF_ITEM_INPUT_SELECTOR));

        actor.attemptsTo(
                Enter.theValue(desiredAmount).into(itemToSetAmountInput),
                WaitFor.invisibleOverlay());
    }

    public static SetAmountOfItemInCart listedOnPositionToValue(int itemPosition, String desiredAmount) {
        return instrumented(SetAmountOfItemInCart.class, itemPosition, desiredAmount);
    }
}
