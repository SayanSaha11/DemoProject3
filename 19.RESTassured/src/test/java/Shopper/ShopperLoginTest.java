package Shopper;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class ShopperLoginTest {
	@Test
	public void loginTest() {
		given()
		.contentType("application/json")
		.relaxedHTTPSValidation()
		.body("{\r\n"
				+ "  \"email\": \"sayansaha2024.1@gmail.com\",\r\n"
				+ "  \"password\": \"Sayan@2004\",\r\n"
				+ "  \"role\": \"SHOPPER\"\r\n"
				+ "}")
		.when()
		.post("https://www.shoppersstack.com/shopping/users/login")
		
		.then()
		.statusCode(200)
		.log().all();
	}
	
	@Test(dependsOnMethods="loginTest")
	public void fetchData() {
		given()
		.contentType("application/json")
		.auth().oauth2("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYXlhbnNhaGEyMDI0LjFAZ21haWwuY29tIFNIT1BQRVIiLCJleHAiOjE3NzMyNDAyNTgsImlhdCI6MTc3MzIwNDI1OH0.SiyQRfiGlG3N3fuFrHn7hfTtpZ7H1NyRTU1J-rdm7Xk")
		.pathParam("shopperId", "363289")
		.relaxedHTTPSValidation()
		.when()
		.get("https://www.shoppersstack.com/shopping/shoppers/{shopperId}")
		.then()
		.assertThat().statusCode(200)
		.log().all();
	}
}
