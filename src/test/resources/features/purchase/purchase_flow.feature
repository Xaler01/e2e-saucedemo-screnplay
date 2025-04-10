# src/test/resources/features/purchase/purchase_flow.feature
Feature: Saucedemo Purchase Flow
  As a standard user
  I want to complete a purchase
  So I can verify the e-commerce flow

  Scenario: Complete purchase with two products
    Given I login as "standard_user" with password "secret_sauce"
    When I add the first two products to the cart
    And I view my cart
    And I proceed to checkout
    And I enter my checkout information from file "users.json" for user <userIndex>
    Then I should see the order confirmation "Thank you for your order!"

    Examples:
      | userIndex |
      | 0         |
      | 1         |
      | 2         |
      | 3         |