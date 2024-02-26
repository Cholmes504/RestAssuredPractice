package tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class CheckStatus {
	
	String baseUri = "https://techfios.com/api-prod/api/product";
	
/*	01. CheckStatus
	HTTP Method: GET
	url=https://techfios.com/api-prod/api/product/read.php
	Header/s:
	Content-Type=application/json; charset=UTF-8
	Authorization:
	basic auth = username,password
	StatusCode= 200 OK
	*/
	
	@Test
	public void readAllProducts() {
		
		/*
		given: all input details(baseURI,Headers,Authorization,Payload/Body,QueryParameters)
		when: submit api requests(Http method,Endpoint/Resource)
		then: validate response(status code, Headers,/ responseTime, Payload/Body)
		*/
		
		
		Response response =	
		
		given()
			.baseUri(baseUri)
			.header("Content-Type","application/json; charset=UTF-8")
			.auth().preemptive().basic("demo@techfios.com", "abc123").
		when()
			.get("/read.php").
		then()
			.extract().response();
		
		int responseCode = response.getStatusCode();
		System.out.println("Response Code:" + responseCode);
		Assert.assertEquals(responseCode, 200);
		
		Long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("Response Time:" + responseTime);
		if(responseTime<=3000) {
			System.out.println("Response Time is within range.");
			
		}else
		System.out.println("Response Time not in range.");
		
		String responseHeader = response.getHeader("Content-Type");
		System.out.println("Response Header:" +responseHeader);
		Assert.assertEquals(responseHeader, "application/json; charset=UTF-8");
		
		String responseBody = response.getBody().asString();
	//	System.out.println(responseBody);


		JsonPath jpath = new JsonPath(responseBody);
		String firstproductId = jpath.get("records[0].id");
		System.out.println("First Product ID:" + firstproductId);
		if(firstproductId != null) {
			System.out.println("Response body contains first product id.");
			
		}else
		System.out.println("Response body does not contain first product id.");
		
	}

}