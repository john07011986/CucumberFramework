@batch
Feature: Search an employee in HRMS

  Background:
    When user enters valid admin credentials
    And user clicks on login button
    And user navigated to employee list page


@smoke
  Scenario: Search and employee by id
    When user enters valid employee id
    And user clicks on search button
    Then user is able to see employee information

@regression
  Scenario: Search and employee by name
    When user enters valid employee name
    And user clicks on search button
    Then user is able to see employee information