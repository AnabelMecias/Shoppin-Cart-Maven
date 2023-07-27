public class Product {
	private int productID;
	private String productName;
	private float price;
	private float discount;
	private String description;
	private int productQuantity;
	private int productQuantityLimit;

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public int getProductQuantityLimit() {
		return productQuantityLimit;
	}

	public void setProductQuantityLimit(int productQuantityLimit) {
		this.productQuantityLimit = productQuantityLimit;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProductLimitQuantity() {
		return productQuantity;
	}

	public void setProductLimitQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	

	public Product(int productID, String productName, float price, float discount, String description,
			int productQuantity, int productQuantityLimit) {
		this.productID = productID;
		this.productName = productName;
		this.price = price;
		this.discount = discount;
		this.description = description;
		this.productQuantity = productQuantity;
		this.productQuantityLimit = productQuantityLimit;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", price=" + price + ", discount="
				+ discount + ", description=" + description + ", productQuantity=" + productQuantity
				+ ", productQuantityLimit=" + productQuantityLimit + "]";
	}

	
}
