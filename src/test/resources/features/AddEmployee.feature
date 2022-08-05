
Feature: Adding the employees in HRMS Application

  Background:
    When user enters valid admin credentials
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user clicks on add employee option


  Scenario: Adding one employee from feature file

    And user enters firstname middlename and lastname
    And user clicks on save button
    Then employee added successfully


  Scenario: Adding one employee using cucumber feature file

    And user enters "Santa" "Monica" and "Blvd"
    And user clicks on save button
    Then employee added successfully



# THis is implementation of Data driven testing
#  this scenaroi will execute hooks and background for each employee
  Scenario Outline: Adding multiple employees
# we are providing the keys
    And user provides "<firstName>" "<middleName>" and "<lastName>"
    And user clicks on save button
    Then employee added successfully
    Examples:
      |firstName|middleName|lastName|
      |asel     |MS        |tolga   |
      |yazgul   |MS        |kishan  |
      |tarik    |MS        |mujeeb  |
      |nassir   |MS        |volkan  |


#    this way we can eliminate opening and closing the browser, as well as reexecuting the background
#  this scenario will execute backgrouns once and will execute adding all the employees and only after that it will
#  close the browser
#   only the step where we provide data table will be execute multiple time
  Scenario: Add employee using data table

    When user provides multiple employee data and verify they are added
      |firstName|middleName|lastName|
      |asel     |MS        |tolga   |
      |yazgul   |MS        |kishan  |
      |tarik    |MS        |mujeeb  |


#    adding employees from excel data
# we are specifying the sheet name "sheetname "
  @123
  Scenario: Adding multiple employees from excel file
    When user add multiple employees from excel file using "EmployeeData" sheet and verify that user added
