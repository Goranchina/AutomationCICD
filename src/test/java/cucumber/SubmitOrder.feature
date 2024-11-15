@tag
  Feature: Purchase the order from Ecommerce website
    I want to use this template for my feature file

  Background: // prerequisite for Test
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submiting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on Confirmation Page

    Examples:
    | name               | password       | productName |
    | goransfq@gmail.com | Somepass10     | ZARA COAT 3 |


