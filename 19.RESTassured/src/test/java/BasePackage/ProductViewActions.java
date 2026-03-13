package BasePackage;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;

public class ProductViewActions extends BaseClass{
	@Test
	public void fetchAllProducts() {
		Response res = 	given()
				.auth().oauth2(jwtToken)
				.relaxedHTTPSValidation()
				.contentType("application/json")
				.baseUri("https://www.shoppersstack.com/shopping")
				.when().get("/products/alpha");
		List<Integer> productIds = res.jsonPath().getList("data.productId");
		int productId = productIds.get(0);
		System.out.println("Product ID is : "+productId);
	}
}
