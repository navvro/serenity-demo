package com.demo.navvrot.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.Dimension;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class ChangeSizeOfWindow implements Interaction {
    private int x;
    private int y;

    public ChangeSizeOfWindow(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static ChangeSizeOfWindow to(int x, int y) {
        return instrumented(ChangeSizeOfWindow.class, x, y);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).getDriver().manage().window().setSize(new Dimension(x, y));
    }
}
