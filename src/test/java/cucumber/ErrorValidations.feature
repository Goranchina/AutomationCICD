@tag
  Feature: Error validation
  I want to use this template for my feature file

    @ErrorValidation
    Scenario Outline: Positive Test of Submiting the order
      Given I landed on Ecommerce Page
      When Logged in with username <name> and password <password>
      Then "Incorrect email or password." message is displayed

      Examples:
        | name               | password       |
        | goransfq@gmail.com | Someas10       |





