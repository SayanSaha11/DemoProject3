package Shopper;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ShopperLoginResponse {
	String shopperId;
	String jwtToken;
	@Test
	public void loginTest() {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("email", "sayansaha2024.1@gmail.com");
		map.put("password", "Sayan@2004");
		map.put("role", "SHOPPER");
		Response res = 	given()
		.contentType("application/json")
		.relaxedHTTPSValidation()
		.body(map)
		.when()
		.post("https://www.shoppersstack.com/shopping/users/login");
		shopperId = res.jsonPath().getString("data.userId");
		jwtToken = res.jsonPath().getString("data.jwtToken");
		System.out.println("Shopper Id is : "+shopperId);
		System.out.println("JWT Token is : "+jwtToken);
		res.then().log().all();
	}
	
	@Test(dependsOnMethods = "loginTest")
	public void fetchData() {
		Response res = 	given().relaxedHTTPSValidation()
		.contentType("application/json")
		.auth().oauth2(jwtToken)
		.pathParam("shopperId",shopperId)
		.baseUri("https://www.shoppersstack.com/shopping")
		.when()
		.get("/shoppers/{shopperId}");
		System.out.println(res.prettyPrint());
	}
	
}
