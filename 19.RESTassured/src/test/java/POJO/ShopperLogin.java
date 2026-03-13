package POJO;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ShopperLogin extends BaseClass{
	@Test
	public void loginTest() {
		ShopperLoginPojo data = new ShopperLoginPojo("sayansaha2024.1@gmail.com","Sayan@2004","SHOPPER");
		Response res = given()
				.relaxedHTTPSValidation()
				.contentType("application/json")
				.body(data)
				.when().post("https://www.shoppersstack.com/shopping/users/login");
		res.then().log().all();
		System.out.println(shopperId+" : "+jwtToken);
	}
}
