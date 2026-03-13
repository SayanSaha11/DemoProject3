package POJO;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import Shopper.ShopperCartPOJO;
import io.restassured.response.Response;

public class ShopperAddToCart extends BaseClass{
	
	int productId;
	int quantity;
	int itemId;
	
	ShopperCartPOJO cart ;
	
	@Test
	public void fetchAll() throws Exception {
		Response res = given()
				.auth().oauth2(jwtToken)
				.relaxedHTTPSValidation()
				.contentType("application/json")
				.baseUri("https://www.shoppersstack.com/shopping")
				.when().get("/products/alpha");
		List<Integer> products = res.jsonPath().getList("data.productId");
		productId = products.get(0);
		System.out.println("First product ID is : "+productId);
		res.then().log().all();
	}
	
	@Test(dependsOnMethods = "fetchAll")
	public void addToCart() {
		cart = new ShopperCartPOJO(productId,1);
		Response res = given()
				.auth().oauth2(jwtToken)
				.relaxedHTTPSValidation()
				.contentType("application/json")
				.baseUri("https://www.shoppersstack.com/shopping")
				.pathParam("ShopperId", shopperId)
				.body(cart)
				.when().post("/shoppers/{ShopperId}/carts");
		res.then().log().all();
		itemId = res.jsonPath().getInt("data.itemId");

	    res.then().statusCode(201);
	}
	
	@Test(dependsOnMethods = "addToCart")
	public void updateCart() {
		cart.setQuantity(3);
		Response res = given()
				.auth().oauth2(jwtToken)
				.relaxedHTTPSValidation()
				.contentType("application/json")
				.baseUri("https://www.shoppersstack.com/shopping")
				.pathParam("ShopperId", shopperId)
				.pathParam("itemId", itemId)
				.body(cart)
				.when().put("/shoppers/{ShopperId}/carts/{itemId}");
		res.then().log().all();
	}
	
	@Test(dependsOnMethods = "updateCart")
	public void getCart() {
		Response res = given()
				.auth().oauth2(jwtToken)
				.relaxedHTTPSValidation()
				.baseUri("https://www.shoppersstack.com/shopping")
				.pathParam("ShopperId", shopperId)
				.when().get("/shoppers/{ShopperId}/carts");
		res.then().log().all();
	}
	
	@Test(dependsOnMethods = "getCart")
	public void deleteCart() {
		Response res = given()
				.auth().oauth2(jwtToken)
				.relaxedHTTPSValidation()
				.baseUri("https://www.shoppersstack.com/shopping")
				.pathParam("ShopperId", shopperId)
				.pathParam("productId", cart.getProductId())
				.when().delete("/shoppers/{ShopperId}/carts/{productId}");
		res.then().log().all();
	}
	
}
