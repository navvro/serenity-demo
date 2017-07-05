package com.demo.navvrot.questions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.demo.navvrot.utils.Utils.getTextWithoutWhiteSpaces;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class MatcherWithPriceFromElement implements Question<Matcher> {
    private WebElementFacade element;
    private String regExp;

    public MatcherWithPriceFromElement(WebElementFacade element, String regExp) {
        this.element = element;
        this.regExp = regExp;
    }

    private static Matcher getMatchedText(String regexp, String priceText) {
        return Pattern.compile(regexp).matcher(priceText);
    }

    public static MatcherWithPriceFromElement matchedWithRegExp(WebElementFacade element, String regExp) {
        return new MatcherWithPriceFromElement(element, regExp);
    }

    @Override
    public Matcher answeredBy(Actor actor) {
        String cartSum = element.getText();
        String priceText = getTextWithoutWhiteSpaces(cartSum);

        return getMatchedText(regExp, priceText);
    }
}
