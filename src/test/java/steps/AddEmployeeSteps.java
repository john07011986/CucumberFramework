package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        click(employeeSearchPage.pimOptions);
    }

    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        click(addEmployeePage.addEmployeeBtn);
    }

    //passing values in the implementation steps
    @Given("user enters firstname middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        sendText(addEmployeePage.firstName, "Bob");
        sendText(addEmployeePage.middleName, "Bobs");
        sendText(addEmployeePage.lastName, "Burger");
    }

    @Given("user clicks on save button")
    public void user_clicks_on_save_button() {
        click(addEmployeePage.saveBtn);
    }

    //HOMEWORK
    @Then("employee added successfully")
    public void employee_added_successfully() {

    }

    //passing hardcoded values from the feature file
    @When("user enters {string} {string} and {string}")
    public void user_enters_and(String firstName, String middleName, String lastName) {
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);

    }

    //implementation of the steps from Scenario Outline
    @When("user provides {string} {string} and {string}")
    public void user_provides_and(String firstName, String middleName, String lastName) {
        sendText(addEmployeePage.firstName, firstName);
        sendText(addEmployeePage.middleName, middleName);
        sendText(addEmployeePage.lastName, lastName);
    }

    //adding employees using data table
    @When("user provides multiple employee data and verify they are added")
    public void userProvidesMultipleEmployeeDataAndVerifyTheyAreAdded(DataTable dataTable) throws InterruptedException {
        //datatable.asMaps returns each row as map and stores it in the list
        //we got the data from datatable ina  form of key-value pairs(maps). Then we stored all maps in the list.
//        Then we iterated through each map and retrieved values using keys
        List<Map<String, String>> emplloyeeNames = dataTable.asMaps();
        for (Map<String, String> employee : emplloyeeNames) {
            String firstName = employee.get("firstName");
            String middleName = employee.get("middleName");
            String lastName = employee.get("lastName");

            System.out.println(firstName + " " + middleName + " " + lastName);

//            now we will send the data in the fields
            sendText(addEmployeePage.firstName, firstName);
            sendText(addEmployeePage.middleName, middleName);
            sendText(addEmployeePage.lastName, lastName);

            click(addEmployeePage.saveBtn);
            Thread.sleep(3000);
            //homework!!!!
            //verify employee has been added
            click(addEmployeePage.addEmployeeBtn);

        }

    }

    //we are passing sheet name as a parameter
    @When("user add multiple employees from excel file using {string} sheet and verify that user added")
    public void user_add_multiple_employees_from_excel_file_using_sheet_and_verify_that_user_added(String sheetname) throws InterruptedException {
        List<Map<String, String>> newEmployees = ExcelReader.excelIntoMap(Constants.TESTDATA_FILEPATH, sheetname);

//       now we will use Iterator to iterate through all values in the list

//since the list is the list of maps => we are creating iterator for maps
        Iterator<Map<String, String>> iterator = newEmployees.iterator();

        while (iterator.hasNext()) {
            //this map returns key and value for employees
            Map<String, String> mapNewEmployee = iterator.next();

            //filling out fields by the data coming from excel file
            sendText(addEmployeePage.firstName, mapNewEmployee.get("FirstName"));
            sendText(addEmployeePage.middleName, mapNewEmployee.get("MiddleName"));
            sendText(addEmployeePage.lastName, mapNewEmployee.get("LastName"));

            //getting value from the employee id field
            String employeeID = addEmployeePage.employeeIdLocator.getAttribute("value");

            //upload the phone
            sendText(addEmployeePage.chooseFileBtn, mapNewEmployee.get("Photo"));

            if (addEmployeePage.loginCheckbox.isSelected()) {
                System.out.println("selected");
            }else{
                click(addEmployeePage.loginCheckbox);
            }

            sendText(addEmployeePage.username, mapNewEmployee.get("Username"));
            sendText(addEmployeePage.password, mapNewEmployee.get("Password"));
            sendText(addEmployeePage.repeatPassword, mapNewEmployee.get("Password"));
            click(addEmployeePage.saveBtn);
            Thread.sleep(2000);

            //to verify the employee, we will navigate to employee list
            click(employeeSearchPage.empListOption);
            //pass employee id and click search
            sendText(employeeSearchPage.searchIDField, employeeID);
            click(employeeSearchPage.searchButton);

            //it is returning the data from the row in results
            List<WebElement> rowData = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
            for (int i = 0; i < rowData.size(); i++) {
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);
                String expectedData = employeeID + " " + mapNewEmployee.get("FirstName") + " " +
                        mapNewEmployee.get("MiddleName") + " " + mapNewEmployee.get("LastName");
                Assert.assertEquals(expectedData, rowText);
            }
            click(employeeSearchPage.searchButton);
            Thread.sleep(2000);
        }
    }}







