package APISteps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {

    String baseUTI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static String token;

    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification request = given().header("Content-Type", "application/json").
                body("{ \"email\": \"george.bluth@reqres.in\",\n" +
                        "            \"password\": \"George2!\"}");

        Response response= request.when().post("/generateToken.php");

     token= "Bearer " + response.jsonPath().getString("token");

    }




}
