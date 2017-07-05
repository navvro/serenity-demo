package com.demo.navvrot.pageobjects.categories;

import net.serenitybdd.screenplay.targets.Target;

import static com.demo.navvrot.pageobjects.NavigationBar.CATEGORY_ONE;

/**
 * @author Rafal Nawrocki <rafal.nawrocki0@gmail.com>
 */
public class LaptopsTabletsCategory implements CategoryButton {
    @Override
    public Target getButton() {
        return CATEGORY_ONE;
    }
}
