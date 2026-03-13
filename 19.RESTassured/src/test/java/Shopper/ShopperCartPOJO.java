package Shopper;

public class ShopperCartPOJO {
	private int productId;
	private int quantity;
	public ShopperCartPOJO(int productId,int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int count) {
		this.quantity = count;
	}
	
	
}
