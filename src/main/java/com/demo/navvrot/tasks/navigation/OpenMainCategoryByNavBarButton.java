package com.demo.navvrot.tasks.navigation;

import com.demo.navvrot.pageobjects.categories.CategoryFactory;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class OpenMainCategoryByNavBarButton implements Task {
    private String categoryName;

    public OpenMainCategoryByNavBarButton(String categoryName) {
        this.categoryName = categoryName;
    }

    public static OpenMainCategoryByNavBarButton named(String categoryName) {
        return instrumented(OpenMainCategoryByNavBarButton.class, categoryName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(new CategoryFactory().getCategoryButton(categoryName))
        );
    }
}
