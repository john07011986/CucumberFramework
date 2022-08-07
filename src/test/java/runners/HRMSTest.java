package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //we can specify full features folder or specific feature file
        features = "src/test/resources/features/",
        //we will specify full folder where all steps files are
        glue = "steps",
        dryRun = false,
        monochrome = false,



        //for reports
        plugin={
                //for html report --> we will keep all the reports under the target folder
                //all html reports will be under cucumber.html
                "html:target/cucumber.html", "pretty",
                //pretty will make the output to the consol that reflects only relevant info

                //this plugin is for the jason report, again we will specify destination folder and report file name
                "json:target/cucumber.json",
                //for extent report
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }



)

public class HRMSTest {


}
