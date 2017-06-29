package com.demo.nawrot.tasks.navigation;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static com.demo.nawrot.pageobjects.EShopMainPage.CONFIRM_COOKIES_BUTTON;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class ConfirmCookies implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(CONFIRM_COOKIES_BUTTON));
    }

    public static ConfirmCookies popUp() {
        return instrumented(ConfirmCookies.class);
    }
}
