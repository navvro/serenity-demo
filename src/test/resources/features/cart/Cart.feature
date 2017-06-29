Feature: Cart operation on e-shop
  As an user I want to have possibility to manage my cart of products
  to be able to shop only things I want to have.

  Background:
    Given User opens e-shop page

  Scenario: Should add single item to cart
    When User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 1st item on the list to his cart
    Then This item is visible on cart page

  Scenario: Should add multiple items to cart
    When User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 1st item on the list to his cart
    When User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 2nd item on the list to his cart
    Then 2 items are visible on cart page

  Scenario: Should remove single item from cart
    Given User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 1st item on the list to his cart
    When User removes 1st item from the cart
    Then Message about empty cart is visible

  Scenario: Should restore multiple items cart after deletion
    When User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 1st item on the list to his cart
    When User opens "Laptopy i tablety" category
    And User opens "Laptopy/Notebooki/Ultrabooki" subcategory
    And User adds 2nd item on the list to his cart
    And User removes all items from the cart
    And User restores cart items
    Then 1 item is visible on cart page
