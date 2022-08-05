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
        dryRun = true,
        monochrome = true,
        tags = "@smoke"


)


public class Smoke {
}
