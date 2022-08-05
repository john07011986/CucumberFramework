package utils;

import io.restassured.RestAssured;

public class APIConstants {

    public static final String baseUTI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    public static final String GENERATE_TOKEN_URI = baseUTI+ "/generateToken.php";
    public static final String GET_ONE_EMP_URI = baseUTI+ "/getOneEmployee.php";
    public static final String GET_ALL_EMP_URI = baseUTI+ "/getAllEmployee.php";
    public static final String UPDATE_EMP_URI = baseUTI+ "/updateEmployee.php";

    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_CONTENT_TYPE_VALUE = "application/json";
    public static final String HEADER_AUTHORIZATION = "Authorization";






}
