package com.test.helpers;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;

import com.test.models.AddUserPOJO;
import com.test.models.CreateEmpPOJO;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ReusableMethods {

	public static CreateEmpPOJO user; 
	public static CreateEmpPOJO getDataToAdd() {
	
	user= new CreateEmpPOJO();
	user.setName("test_sahana");
	user.setAge("23");
	user.setSalary("123");	
	
		return user;
	}
	
	
	public int getStatusCode(Response response) {
		return response.getStatusCode();
	}
	
	public static void ValidateStatusCode(Response res, int statuscode) {
		res.then().statusCode(statuscode);
	}
	
	public String getContentType(Response response) {
		return response.getContentType();
	}
	public long getResponseTime(Response response,TimeUnit unit) {
		return response.getTimeIn(unit);
	}
	public String getStatus(Response response, String status) {
		return response.jsonPath().getString("status");
				
	}
	
	
}
