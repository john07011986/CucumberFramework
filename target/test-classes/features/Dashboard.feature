Feature: Dashboard tabs verification functionality

  @Dash
  Scenario:   Verify dashboard tabs
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user is successfully logged in
    Then user verifies all the dashboard tabs
    |Admin|PIM|Leave|Time|Recruitment|Performance|Dashboard|Directory|