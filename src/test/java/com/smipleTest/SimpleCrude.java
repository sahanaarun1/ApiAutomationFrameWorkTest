package com.smipleTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class SimpleCrude {
	@Test
	
	public static void FirstTest() {
		
	/*	Response res=RestAssured.given().when().get("https://dummy.restapiexample.com/api/v1/employees");
		res.then().statusCode(200);
		res.then().time(Matchers.lessThan(5000L)); 
		------------------------------------------------------
		RestAssured
					.given()
					.when()	
					.get("https://dummy.restapiexample.com/api/v1/employees")
					.then()
					.statusCode(200)
					.time(Matchers.lessThan(5000L));*/
		
		Response res=RestAssured
								.given()
								.when()	
								.get("https://dummy.restapiexample.com/api/v1/employees");
					res.then().statusCode(200);
					res.then().time(Matchers.lessThan(5000L));
					res.then().body("status", Matchers.equalTo("success"));
					
					res.then().body("data[6].id", Matchers.equalTo(7));
					//res.then().body("keySet().size()" );
					
					String respon= res.asString();
					res.prettyPrint();
					System.out.println("Ststus code = "+res.statusCode());
					String sus=res.jsonPath().getString("status");
					System.out.println("Ststus displayed :"+sus);
					
					int s =  res.jsonPath().getInt("data.size()");
					System.out.println("Number of emeployee entries : "+s);
					
					String EmpName= res.jsonPath().getString("data[6].employee_name");
					System.out.println("Emp name :"+EmpName);
					int id= res.jsonPath().getInt("data[6].id");
					System.out.println("emp id :"+id);
					
				
					
					String  name = res.path("data.find { it.id == 7 }.employee_name");
					System.out.println("Name of the employe whose id is 7 :"+name);
					List<String>  Salary= res.path("data.findAll {it.employee_salary >300000 }.id");
					System.out.println(" sallary >300000 "+Salary);
					List<String>  age= res.path("data.findAll {it.employee_age >60 }.employee_name");
					System.out.println(" Emplyoes name whose age is >60 "+age);
					
					
					ArrayList<Map<String,?>> alldetails = res.path("data.findAll {it.employee_salary >300000 }.data");
					System.out.println("all employee details whose salary is >300000 : "+alldetails);
		
	}
	
	

}
