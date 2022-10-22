package com.test.helpers;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.test.utils.CommonUtilities;
import com.test.constants.Endpoints;
import com.test.models.AddUserPOJO;
import com.test.models.CreateEmpPOJO;
import com.test.models.DeleteRequestPOJO;
import com.test.models.EmployeeData;
import com.test.models.GetEmpDetailsPOJO;
import com.test.models.UserPOJO;
import com.test.models.LoginRequestPOJO;
import com.test.models.LoginResponsePOJO;
import com.test.models.UserPOJO1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.http.ContentType;
import io.restassured.http.Header;



public class UserServiceHelper extends ReusableMethods{
	private static Response response;
	public static int id;
	
	
	public static String getBaseUri() {
 CommonUtilities CU=new CommonUtilities();
		Properties applicationPropertiesFile=CU.loadFile("applicationproperties");
		String baseURI=CU.getApplicationProperty("baseURI",applicationPropertiesFile);
		RestAssured.baseURI=baseURI;
		System.out.println(baseURI);
		
		return baseURI;
	}
	
	public static List<EmployeeData> LoginToApp() {
	//	public static Response LoginToApp() {
				
	//	GetEmpDetailsPOJO EmpDetails = new GetEmpDetailsPOJO();
					
		 
		response = RestAssured
				.given()				
			
				//.body(EmpDetails)
				.contentType(ContentType.JSON)
				.when()
				.get(Endpoints.LOGIN);
		
		System.out.println("Status code = "+response.statusCode());
		String sus=response.jsonPath().getString("status");
		System.out.println("Status displayed :"+sus);
		response.then().body("status", Matchers.equalTo("success"));
		
		//response.then().statusCode(200);
		GetEmpDetailsPOJO employeePOJO =response.as(GetEmpDetailsPOJO.class);
		List<EmployeeData> empList = employeePOJO.getData();
		
		System.out.println("Number of emeployee entries : "+empList.size());
		response.prettyPrint();
	
		  return empList;		 	
	}
	


		public static int addNewEmp(){
			
			CreateEmpPOJO data = ReusableMethods.getDataToAdd();
			
			Response res= RestAssured.given()
			
				.contentType(ContentType.JSON)
				.body(data)				
				.when()
				.post(Endpoints.ADD_DATA);
				
				System.out.println("Status from add user==="+res.jsonPath().getString("status"));
				res.prettyPrint();
				int uid= res.jsonPath().get("data.id");
				System.out.println(uid);
				String name  =res.jsonPath().get("data.name");
				String age  =res.jsonPath().get("data.age");
				String salary  =res.jsonPath().get("data.salary");
				//System.out.println("Data = "+resData.toString());
				if(name.equals(data.getName()) &&  age .equals(data.getAge()) && salary.equals(data.getSalary())) {
					System.out.println("Same as input data");
				}else {
					System.out.println("Not Same as input data");
				}
				res.then().statusCode(200);
				res.then().body("status",Matchers.equalTo("success"));

				return uid;
}
			
	

		
	public static Response deleteEmpDetails(int id) {
		EmployeeData DeleteUser = new EmployeeData();

		response = RestAssured.given().contentType(ContentType.JSON)
				.body(DeleteUser)
				.when()
				.delete(Endpoints.DELETE_DATA);
		System.out.println("User details DELETED");
		response.then().statusCode(200);
		response.then().body("status",Matchers.equalTo("sucess"));
		return response;
		
	}
	
	public static Response deleteWith0Id() {
		EmployeeData DeleteUser = new EmployeeData();
		response = RestAssured.given().contentType(ContentType.JSON)
				.body(DeleteUser)
				.when()
				.delete(Endpoints.DELETE_WITH0);
		System.out.println("Error deleteing");
		response.then().statusCode(400);
		response.then().body("status",Matchers.equalTo("error"));
		
		
		return response;
		
	}
	
	public static Response getEmpDetails() {
		response = RestAssured.given().contentType(ContentType.JSON)
				//.body(DeleteUser)
				.when()
				.delete(Endpoints.GET_DETAILS);
		System.out.println("Status code = "+response.statusCode());
		String sus=response.jsonPath().getString("status");
		System.out.println("Status displayed :"+sus);
		response.then().body("status", Matchers.equalTo("success"));
		String EmpName= response.jsonPath().getString("data[1].employee_name");
		System.out.println("Emp name :"+EmpName);
		int salary= response.jsonPath().get("data[1].salary");
		System.out.println("Emp name :"+salary);
		int age= response.jsonPath().get("data[1].age");
		System.out.println("Emp name :"+age);
		
		
		
		return response;
		
	}
}
	
