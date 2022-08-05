Feature: A;; the API scenarios

  Background:
    Given a JWT is generated

  @api
  Scenario: Adding an employee
    Given a request is prepared to create an employee
    When a post call is made to create an employee
    Then the status code for the created employee is 200



