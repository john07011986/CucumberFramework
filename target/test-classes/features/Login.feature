Feature: Validation of login scenarios

@smoke
  Scenario: Admin login
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user is successfully logged in

@regression
   Scenario: ESS login
     When user enters valid ess username and password
     And user clicks on login button
     Then ess user is successfully logged in

@regression
   Scenario: Invalid login
     When user enters invalid username and password
     And user clicks on login button
     Then user sees error message on the screen