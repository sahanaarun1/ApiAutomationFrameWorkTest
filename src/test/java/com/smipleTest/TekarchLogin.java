package com.smipleTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.models.LoginRequestPOJO;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//import static io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class TekarchLogin {

/*	public void get01() {
		Response res=RestAssured
				.given()
				.when()	
				.get("https://reqres.in/api/users?page=2");
	res.then()
			.statusCode(200);
			//.log().all();
	//{"username":"sahanaarun.i@gmail.com","password":"sahanaarun123"}

	}*/
	
//	
	
	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
	}
	@Test
	
	public  static String login() {
		Response res=	RestAssured
				.given()
				.body("{\"username\":\"sahanaarun.i@gmail.com\",\"password\":\"sahanaarun123\"}")
				.contentType(ContentType.JSON)
				.when()
				.post("login");
		String token=res.jsonPath().get("[0].token");
		return token;
		
	}
	@Test(enabled=false)
	public static void LoginToApi() {
	//	LoginRequestPojo Logindetails = new LoginRequestPojo();
		//Logindetails.setUsername("sahanaarun.i@gmail.com");
		//Logindetails.setPassword("sahana123");
		
		Response res = RestAssured
				.given()
				//.get("https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/")
				.body("{\"username\":\"sahanaarun.i@gmail.com\",\"password\":\"sahanaarun123\"}")
				.contentType(ContentType.JSON)
				.when()
				.post("login");
		res.then().statusCode(201);
		System.out.println("Ststus code :" +res.statusCode());
		String Token = res.jsonPath().get("[0].token");
		String Username = res.jsonPath().get("[0].userid");
		res.prettyPrint();
		
		
	}
	
	
	@Test(enabled = true)//(dependsOnMethods= "deleteUserInfo")
	
	
	public static void GetUseDetails() {
		String token1=login();
		System.out.println(token1);
		Header header= new Header("token",token1);
		Response res= RestAssured
				.given()
				.header(header)
				.get("getdata");
		res.then().statusCode(200);
		System.out.println("number of records :"+res.jsonPath().get("size()"));
		System.out.println("userId :"+res.jsonPath().get("[0].userid"));
		System.out.println("Id :"+res.jsonPath().get("[0].id"));
		System.out.println("Account number: "+res.jsonPath().get("[0].accountno"));	
		System.out.println("salary :"+res.jsonPath().get("[0].salary"));
		System.out.println("Department no :"+res.jsonPath().get("[0].departmentno"));
					
	}
	@Test(enabled= false)
	
	public static void CreateUserDetails() {
		//json to string converter
		//http://tools.knowledgewalls.com/string to json
		
		String token1=login();
		System.out.println(token1);
		Header header=new Header("token",token1);
		
				Response res= RestAssured.given()
				.header(header)
				.contentType(ContentType.JSON)
				.body("{\"accountno\":\"TA-AGU2021\",\"departmentno\":\"2\",\"salary\":\"3000\",\"pincode\":\"123456\"}")
				
				.when()
				.post("addData");
				res.then().statusCode(201);
				res.then().body("status",Matchers.equalTo("success"));
	}
	
	@Test(enabled =false)
	
	
	public static void updateUserDetails() {
		//json to string converter
		//http://tools.knowledgewalls.com/string to json
		
		String token1=login();
		System.out.println(token1);
		Header header=new Header("token",token1);
		
				Response res= RestAssured.given()
				.header(header)
				.contentType(ContentType.JSON)
				//change the department no and salary
				.body("{\"accountno\":\"TA-AGU2021\",\"departmentno\":5,\"salary\":1111,\"pincode\":123456,\"userid\":\"mddlQN2Zj76KEXtOVpXf\",\"id\":\"Hh3QTUV6bCuxXyVLezw6\"}")
				
				.when()
				.put("updateData");
				res.then().statusCode(200);
				res.then().body("status",Matchers.equalTo("success"));
	}
	@Test(enabled =false)
	
	public static void deleteUserInfo() {
		//json to string converter
		//http://tools.knowledgewalls.com/string to json
		
		String token1=login();
		System.out.println(token1);
		Header header=new Header("token",token1);
		
				Response res= RestAssured.given()
				.header(header)
				.contentType(ContentType.JSON)
				//change the department no and salary
				.body("{\"accountno\":\"TA-Aug2201\",\"departmentno\":\"2\",\"salary\":\"3000\",\"pincode\":\"123456\"}")
				
				.when()
				.delete("deleteData");
				res.then().statusCode(200);
				res.then().contentType(ContentType.JSON);
				res.then().body("status",Matchers.equalTo("success"));
	}



		}

