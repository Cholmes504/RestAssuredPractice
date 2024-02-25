package tests;

import static io.restassured.RestAssured.*;


import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutPatchDelete {

	
	@Test
	public void testPut() {
		
		
		
		
		JSONObject request = new JSONObject();
		
		request.put("name" , "Holmes");
		request.put("job", "Cool Guy"); 
		
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/api";

		
		given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON).
		body(request.toJSONString()).when().put("/users/2)").then().statusCode(200).log().all();
		
		
		
		
	}
	
	
	
	@Test
	public void testPatch() {
		
		
		
		
		JSONObject request = new JSONObject();
		
		request.put("name" , "Holmes");
		request.put("job", "Cool Guy"); 
		
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in";

		
		given().header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON).
		body(request.toJSONString()).when().patch("/api/users/2)").then().statusCode(200).log().all();
		
		
		
		
	}
	
	@Test
	public void testDelete() {
		
		
		
		baseURI = "https://reqres.in/api";

		
		when().delete("/users/2)").then().statusCode(204).log().all();
		
		
		
		
	}
}