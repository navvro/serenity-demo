package com.demo.nawrot.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class ElementVisibility implements Question<Boolean> {
    private Target element;

    private ElementVisibility(Target element) {
        this.element = element;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return element.resolveFor(actor).isVisible();
    }

    public static ElementVisibility of(Target element) {
        return new ElementVisibility(element);
    }
}
