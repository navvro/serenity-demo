package com.demo.nawrot.tasks.navigation;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class OpenSubcategoryOnMainCategoryPage implements Task {
    private String subcategoryName;

    public OpenSubcategoryOnMainCategoryPage(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String subcategoryLinkLocator = String.format("//span[text()='%s']", subcategoryName);
        Target subcategoryLink = Target.the("Chosen subcategory").locatedBy(subcategoryLinkLocator);

        actor.attemptsTo(
                Scroll.to(subcategoryLink),
                Click.on(subcategoryLink));
    }

    public static OpenSubcategoryOnMainCategoryPage named(String subcategoryName) {
        return instrumented(OpenSubcategoryOnMainCategoryPage.class, subcategoryName);
    }
}
