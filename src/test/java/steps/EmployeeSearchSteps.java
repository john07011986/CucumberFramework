package steps;

import io.cucumber.java.en.*;
import utils.CommonMethods;
import utils.ConfigReader;


public class EmployeeSearchSteps extends CommonMethods {

    @When("user enters valid admin credentials")
    public void user_enters_valid_admin_credentials() {
       sendText(loginPage.usernameBox, ConfigReader.getProperties("username"));
        sendText(loginPage.passwordBox, ConfigReader.getProperties("password"));
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
       click(loginPage.loginButton);
    }

    @When("user navigated to employee list page")
    public void user_navigated_to_employee_list_page() {

        click(employeeSearchPage.pimOptions);
        click(employeeSearchPage.empListOption);
    }

    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {

sendText(employeeSearchPage.searchIDField, "8510142");
    }

    @When("user clicks on search button")
    public void user_clicks_on_search_button() {

        click(employeeSearchPage.searchButton);

    }
    @Then("user is able to see employee information")
    public void user_is_able_to_see_employee_information() {
        System.out.println("verified");
    }

    @When("user enters valid employee name")
    public void user_enters_valid_employee_name() {

sendText(employeeSearchPage.searchNameField, "John");
    }



}
