package com.tapfoods.model;

public class CartItem {
    private int itemId;
    private int restaurantId;
    private String itemName;
    private double price;
    private int quantity;
    private double subTotal;

    // Default constructor
    public CartItem() {
    }

    // Parameterized constructor
    public CartItem(int itemId, int restaurantId, String itemName, double price, int quantity) {
        this.itemId = itemId;
        this.restaurantId = restaurantId;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.subTotal = price * quantity; // Calculate subtotal based on price and quantity
    }
    
    

    public CartItem(int restaurantId, String itemName, double price, int quantity, double subTotal) {
		super();
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}

	// Getter and Setter methods
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        calculateSubTotal(); // Recalculate subtotal if price changes
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calculateSubTotal(); // Recalculate subtotal if quantity changes
    }

    public double getSubTotal() {
        return subTotal;
    }

    // Calculate subtotal based on price and quantity
    private void calculateSubTotal() {
        this.subTotal = this.price * this.quantity;
    }

    // Override toString for easy display
    @Override
    public String toString() {
        return "CartItem{" +
                "itemId=" + itemId +
                ", restaurantId=" + restaurantId +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
                '}';
    }

	public int getMenuId() {
		return this.itemId; // Assuming menuId is equivalent to itemId
	}
}
