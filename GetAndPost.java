package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.*;



public class GetAndPost {

	@Test
	public void testGet() {
		
		baseURI = "https://reqres.in/api";
		
		given().get("/users?page=2").then().statusCode(200).body("data[4].first_name" 
				,equalToIgnoringCase("George")).body("data.first_name", hasItems("George", "Rachel"));
		
		
	}
	
	@Test
	public void testPost() {
		
		
		
		
		JSONObject request = new JSONObject();
		
		request.put("name" , "Holmes");
		request.put("job", "Cool Guy"); 
		
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/api";

		
		given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON).
		body(request.toJSONString()).when().post("/users)").then().statusCode(201).log().all();
		
		
		
		
	}
}
