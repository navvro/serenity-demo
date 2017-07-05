package com.demo.navvrot.actions;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class SetSessionVariable implements Interaction {
    private String key;
    private Object object;

    public SetSessionVariable(String key, Object object) {
        this.key = key;
        this.object = object;
    }

    public static SetSessionVariable namedWithValue(String key, Object object) {
        return instrumented(SetSessionVariable.class, key, object);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Serenity.setSessionVariable(key).to(object);
    }
}
