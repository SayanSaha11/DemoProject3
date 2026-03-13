package POJO;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ShopperFetchAll extends BaseClass{
	
	@Test
	public void fetchAll() throws Exception {
		Response res = given()
				.auth().oauth2(jwtToken)
				.relaxedHTTPSValidation()
				.contentType("application/json")
				.baseUri("https://www.shoppersstack.com/shopping")
				.when().get("/products/alpha");
		List<Integer> products = res.jsonPath().getList("data.productId");
		int productIds = products.get(0);
		System.out.println("First product ID is : "+productIds);
		res.then().log().all();
		FileWriter file = new FileWriter("response.json");
		file.write(res.asPrettyString());
		file.close();
	}

}
