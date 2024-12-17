@tag
Feature: Purchase the order from E-commerce Website

	Background:
			Given I have landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <username> and password is <password>
    When I add <productName> to Cart 
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message displayed on ConfirmationPage

    Examples: 
      |        username       |  password  | productName  |
      | ramastha159@gmail.com | Akshi@1001 | ZARA COAT 3  |
