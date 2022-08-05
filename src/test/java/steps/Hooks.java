package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks  extends CommonMethods {

    //has to be imported from io.cucumber.java.Before
    @Before
    public void start (){
        openBrowserAndLaunchApplication();
    }

    //has to be imported from io.cucumber.java.After
    @After
    public void end(Scenario scenario){
        //in cucumber scenario class holds the complete information of the execution
        //scenario class = pass fail names etc
        //in order to take the screenshot in cucumber using scenario class.Scenario class accepts only arrey of bytes
        // so we need to write a method that returns arrey of bytes so we can handle tha tportion using scenario class

        //we want to separate pass scrreenshtos in one folder and put all fail screenshots in other folder
        byte[] pic;

        if(scenario.isFailed()){
            pic=takeScreenshot("failed/"+scenario.getName());
        }else{
            pic= takeScreenshot("passed/"+scenario.getName());
        }
        //this will attach picture to the report

        scenario.attach(pic,"image/png", scenario.getName());

        tearDown();}

}
