package com.demo.navvrot.pageobjects.categories;

import net.serenitybdd.screenplay.targets.Target;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class CategoryFactory {
    private static final Map<String, CategoryButton> categories = new HashMap<>();

    static {
        categories.put("Laptopy i tablety", new LaptopsTabletsCategory());
    }

    public Target getCategoryButton(String categoryName) {
        return categories.get(categoryName).getButton();
    }

}
