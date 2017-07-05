package com.demo.navvrot.questions;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class SessionVariableAsString implements Question<String> {
    private String key;

    public SessionVariableAsString(String key) {
        this.key = key;
    }

    public static SessionVariableAsString ofKey(String key) {
        return new SessionVariableAsString(key);
    }

    @Override
    public String answeredBy(Actor actor) {
        return Serenity.getCurrentSession().get(key).toString();
    }
}
