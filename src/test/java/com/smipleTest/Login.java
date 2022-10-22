package com.smipleTest;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.models.LoginRequestPOJO;
import com.test.models.LoginResponsePOJO;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


import org.hamcrest.Matchers;
import org.testng.annotations.Test;
//import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import static io.restassured.RestAssured;
//import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
//import io.restassured.response.Response;

public class Login {
	@BeforeClass
	
	public static void setUp() {
		RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
	}
	@Test		
	public static void LoginToApi() {
		LoginRequestPOJO Logindetails = new LoginRequestPOJO();
		Logindetails.setUsername("sahanaarun.i@gmail.com");
		Logindetails.setPassword("sahanaarun123");
		
		Response res = RestAssured
				.given()				
			//	.body("{\"username\":\"sahanaarun.i@gmail.com\",\"password\":\"sahanaarun123\"}")
				.body(Logindetails)
				.contentType(ContentType.JSON)
				.when()
				.post("login");
		//res.then().statusCode(200);
		// ValidateStatusCode(res,201);
		//res. then().log().all().statusCode(201).log().all();
		LoginResponsePOJO[]  list= res.as(LoginResponsePOJO[].class);
		String Token1 =list[0].getToken();
		System.out.println("Token1 from response pojo---:" +Token1);
	//	res.then().log().all().statusCode(201).log().all();
		System.out.println("Ststus code :" +res.statusCode());
		String Token = res.jsonPath().get("[0].token");
		String Username = res.jsonPath().get("[0].userid");
		res.prettyPrint();
		res.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("LoginSchema.json"));
		
	}

}
