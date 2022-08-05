package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {
    @FindBy(id="firstName")
    public WebElement firstName;

    @FindBy(id="middleName")
    public WebElement middleName;

    @FindBy(id="lastName")
    public WebElement lastName;

    @FindBy(id="btnSave")
    public WebElement saveBtn;

    @FindBy(id="menu_pim_addEmployee")
    public WebElement addEmployeeBtn;

    @FindBy(id="chkLogin")
    public WebElement loginCheckbox;

    @FindBy(id="user_name")
    public WebElement username;

    @FindBy(id="employeeId")
    public WebElement employeeIdLocator;

    @FindBy(id="photofile")
    public WebElement chooseFileBtn;

    @FindBy(id="user_password")
    public WebElement password;

    @FindBy(id="re_password")
    public WebElement repeatPassword;



    //create the constructor
    public AddEmployeePage(){
        PageFactory.initElements(driver,this);
    }

}
