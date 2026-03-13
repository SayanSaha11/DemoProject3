package POJO;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;

public class BaseClass{
	public static String shopperId;
	public static String jwtToken;
	public static String productId;
	
	@BeforeClass
	public void loginTest() {
		ShopperLoginPojo data = new ShopperLoginPojo("sayansaha2024.1@gmail.com","Sayan@2004","SHOPPER");
		Response res = 	given()
		.contentType("application/json")
		.relaxedHTTPSValidation()
		.body(data)
		.when()
		.post("https://www.shoppersstack.com/shopping/users/login");
		shopperId = res.jsonPath().getString("data.userId");
		jwtToken = res.jsonPath().getString("data.jwtToken");
		System.out.println("Shopper Id is : "+shopperId);
		System.out.println("JWT Token is : "+jwtToken);
		res.then().log().all();
	}
}
