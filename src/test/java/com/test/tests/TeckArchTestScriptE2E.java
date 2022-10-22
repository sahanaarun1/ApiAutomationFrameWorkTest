package com.test.tests;

import java.io.IOException;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.constants.Endpoints;
import com.test.helpers.ReusableMethods;
import com.test.helpers.UserServiceHelper;
import com.test.models.CreateEmpPOJO;
import com.test.models.UserPOJO;
import com.test.models.UserPOJO1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class TeckArchTestScriptE2E extends UserServiceHelper {
	
	@BeforeMethod
	public static void GetURL() {
		getBaseUri();
	}
	
	@Test(priority=1,enabled=true)
	
	public static void Test01Login() {
		System.out.println("==================");
		LoginToApp();
		
	}

	@Test (priority=2, enabled=true)
	
	public static void Test02CreateEmp()throws IOException {
		System.out.println("before calling create emp method");
	
		CreateEmpPOJO data = ReusableMethods.getDataToAdd();
		
		UserServiceHelper.id=addNewEmp();
	
	}
	@Test (priority=3, enabled=true)
	public static void Test03deleteEmp() {
		
		System.out.println(UserServiceHelper.id);
		System.out.println("before deleting emp ");
		deleteEmpDetails(UserServiceHelper.id);
		
		System.out.println("after delete");
			
	}
	
	@Test(priority=4, enabled=true)
	public static void Test04deleteEmpWith0() {
		deleteWith0Id();
		
		
	}
	@Test(priority = 5,enabled=true)
	public static void Test05GetEmpDetails() {
		getEmpDetails();
	}
}
