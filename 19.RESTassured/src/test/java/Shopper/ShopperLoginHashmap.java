package Shopper;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.util.HashMap;


public class ShopperLoginHashmap {
	String token;
	@Test
	public void loginTest() {
		HashMap<String,String> map = new HashMap<>();
		map.put("email", "sayansaha2024.1@gmail.com");
		map.put("password", "Sayan@2004");
		map.put("role", "SHOPPER");
		token = given()
		.contentType("application/json")
		.relaxedHTTPSValidation()
		.body(map)
		.when()
		.post("https://www.shoppersstack.com/shopping/users/login")
		
		.then()
		.statusCode(200)
		.extract().path("data.jwtToken");
	}
	
	@Test(dependsOnMethods="loginTest")
	public void fetchData() {
		given()
		.contentType("application/json")
		.auth().oauth2(token)
		.relaxedHTTPSValidation()
		.when()
		.get("https://www.shoppersstack.com/shopping/shoppers/363289")
		.then()
		.assertThat().statusCode(200)
		.log().all();
	}
}
