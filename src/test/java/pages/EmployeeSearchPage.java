package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EmployeeSearchPage extends CommonMethods {

    @FindBy(id = "menu_pim_viewPimModule")
    public WebElement pimOptions;

@FindBy(id ="menu_pim_viewEmployeeList")
   public WebElement empListOption ;

@FindBy(id ="empsearch_id" )
  public  WebElement searchIDField ;

@FindBy(id = "searchBtn")
   public WebElement searchButton ;

@FindBy(xpath = "//*[@id=\"empsearch_employee_name_empName\"]")
   public WebElement searchNameField;



public EmployeeSearchPage(){
    PageFactory.initElements(driver,this);
}


}
