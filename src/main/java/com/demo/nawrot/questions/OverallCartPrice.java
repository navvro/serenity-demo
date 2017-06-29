package com.demo.nawrot.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.regex.Matcher;

import static com.demo.nawrot.pageobjects.CartProductList.OVERALL_CART_PRICE;
import static com.demo.nawrot.utils.RegExps.TRIMMED_OVERALL_PRICE_REGEXP;
import static com.demo.nawrot.utils.Utils.parseIntFromString;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class OverallCartPrice implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        Matcher matcher = MatcherWithPriceFromElement
                .matchedWithRegExp(OVERALL_CART_PRICE.resolveFor(actor), TRIMMED_OVERALL_PRICE_REGEXP).answeredBy(actor);
        matcher.find();

        return parseIntFromString(matcher.group(1));
    }

    public static OverallCartPrice sum() {
        return new OverallCartPrice();
    }
}
