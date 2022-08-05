package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HardCodedExamples {
    //to follow HTTP protocol we will use http before the base url
    String baseUTI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer token value";

    static String employee_id;
@Test
    public void acreateEmployee(){
        //first we prepare the request
        //provide content type in header
        //provide autorization token in header
        //provide payload in body
        //We will create an object of requestspecification class
       RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization",token).body("{ \"email\": \"george.bluth@reqres.in\",\n" +
                        "            \"first_name\": \"George\",\n" +
                        "            \"last_name\": \"Bluth\",\n" +
                        "            \"avatar\": \"https://reqres.in/img/faces/1-image.jpg\"}");
//now we need to create a call
        //we will create an object of the response class
       Response response= request.when().post("/createEmployee.php");
   //print out the response in console
   response.prettyPrint();

   //to verify the data we will use then keyword and use assertions

    response.then().assertThat().statusCode(201);

    //HamCrest matchers - it is a framework that helps us create matcher objects.
    response.then().assertThat().body("Message", equalTo("Employee Created"));
    //within the body verificaion we are referencing first name field by calling it on the employee object
    response.then().assertThat().body("Employee.first_name", equalTo("Tom"));


    //we need to capture employee id
    //using jsonPath() method to specify the key in the body so that it returns the value attached to it

   employee_id = response.jsonPath().getString("Employee.emp_Id");
    System.out.println(employee_id);





    }
    @Test
    public void bgetCreatedEmployee(){
    RequestSpecification request =
            given().header("Content-Type", "application/json").header("Authorization",token).queryParam("employee_id"
                    ,employee_id);

    Response response1 = request.when().get("/getOneEmployee.php");
    response1.then().assertThat().statusCode(200);

    String tempId= response1.jsonPath().getString("employee.employee_id");

        Assert.assertEquals(tempId, employee_id);

    }

    @Test
    public void cupdateEmployee(){
    RequestSpecification request = given().header("Content-Type","application/json").header("Authorization",
            token).body("{ \"employee_id\": \""+ employee_id +"\",\n" +
            "            \"first_name\": \"George\",\n" +
            "            \"last_name\": \"Bluth\",\n" +
            "            \"avatar\": \"https://reqres.in/img/faces/1-image.jpg\"}");

   Response respon =  request.when().put("/updateEmployee.php");
   respon.prettyPrint();

   respon.then().assertThat().statusCode(200);



    }

public void dgetTpdatedEmployee(){
    RequestSpecification request =
            given().header("Content-type", "application/json").header("Authoization",token).queryParam("employee_id",
                    employee_id);
    Response response = request.when().get("/getEmployee.php");
    response.then().assertThat().statusCode(200);


}

public void egetAllEmployees(){
    RequestSpecification request =
            given().header("Content-type", "application/json").header("Authoization",token);
    Response response = request.when().get("/getAllEmployee.php");
    //store all the values as a string
    String allEndPoints = response.prettyPrint();
    //jsonpath is a class containing methods to convert values into json object
    //jsonPath() us a method that belongs to jsonPath class

    //creating object of jsonpath object
    JsonPath js = new JsonPath(allEndPoints);

    //retrieving total number of employees

    int count = js.getInt("Employees.size()");
    //to print only employee id
    for (int i = 0; i<count; i++){
       String employeeidString = js.getString("Employees["+i+"].employee_id");
        System.out.println(employeeidString);
    }




}



}
