@cart
Feature: Cart operation on e-shop
  As an user I want to have possibility to manage my cart of products
  to be able to shop only things I want to have.

  Background:
    Given User opens e-shop page

  @CART.TC01 @sanity @regress @smoke
  Scenario: Should add single item to cart
#  Given i click for chelsea
    When User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 1st item on the list to his cart
    Then This item is visible on cart page

  @CART.TC02 @regress
  Scenario: Should add multiple items to cart
    When User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 1st item on the list to his cart
    When User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 2nd item on the list to his cart
    Then 2 items are visible on cart page

  @CART.TC03 @sanity @regress @smoke
  Scenario: Should remove single item from cart
    Given User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 1st item on the list to his cart
    When User removes 1st item from the cart
    Then Message about empty cart is visible

  @CART.TC04 @regress @ISSUE.1
  Scenario: Should restore multiple items cart after deletion
    When User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 1st item on the list to his cart
    When User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 2nd item on the list to his cart
    And User removes all items from the cart
    And User restores cart items
#    It will failed (always one item is restored) but for me it should restore all items added during session to basket
    Then 2 items is visible on cart page

  @CART.TC05 @regress @sanity @smoke @prices
  Scenario: Should price of item in cart be the same as on the list
    When User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 1st item on the list to his cart
    Then Price of item in the cart is the same as on the list

  @CART.TC06 @regress @sanity @smoke @prices @pending
  Scenario: Should price of item in cart with multiple amount be correct
    When User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 1st item on the list to his cart
    And User set amount of 1st item in cart to 3 units
    Then Overall price of 1st item on the list in the cart is multiplied 3 times

  @CART.TC07 @regress @sanity @smoke @prices
  Scenario: Should sum prices of multiple items in cart
    When User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 1st item on the list to his cart
    When User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 2nd item on the list to his cart
    Then Price sum of items in the cart is correct

