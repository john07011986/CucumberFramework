package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethods {



    //since our datatable doesn;t have a header ( meaning it doesn't have keys) we only have values now
    //thus we don't need a map --> we can now use it as list
    @Then("user verifies all the dashboard tabs")
    public void user_verifies_all_the_dashboard_tabs(DataTable dataTable) {
        //this data is coming from the feature file
        List<String> expectedTabs = dataTable.asList();


        //this data is coming from web application
        List<String> actualTabs = new ArrayList<>();
        for (WebElement element: dashboardPage.dashboardTabs){
            //while we iterate through the webelements --> we will get tex value as a string and get it to the list
            actualTabs.add(element.getText());
        }
        System.out.println(expectedTabs);//coming from feature file
        System.out.println(actualTabs);//coming from execution

        //assertions
//        Assert.assertEquals(actualTabs,expectedTabs);

        //if assertion is passed it will not give you any information and will execute our code
        //if assertion is failed it will give you an error with comparison
        Assert.assertTrue(expectedTabs.equals(actualTabs));
    }

}
