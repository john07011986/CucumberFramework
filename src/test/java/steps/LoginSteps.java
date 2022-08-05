package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginSteps extends CommonMethods {

//HOMEWORK
    @Then("user sees error message on the screen")
    public void user_sees_error_message_on_the_screen() {

    }

    @When("user enters valid ess username and password")
    public void user_enters_valid_ess_username_and_password() {
        sendText(loginPage.usernameBox, "tts12345");
        sendText(loginPage.passwordBox, ConfigReader.getProperties("password"));

    }

    @Then("ess user is successfully logged in")
    public void ess_user_is_successfully_logged_in() {
        //leave for verification later

    }

    @Then("admin user is successfully logged in")
    public void admin_user_is_successfully_logged_in() {
        Assert.assertTrue(dashboardPage.welcomeMessage.isDisplayed());

    }

    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
        sendText(loginPage.usernameBox, "tts12345");
        sendText(loginPage.passwordBox, "werwer12");

    }



}
