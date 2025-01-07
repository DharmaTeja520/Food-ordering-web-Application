package com.tapfoods.model;

public class Menu {

    private int menuId;           // Primary key
    private int restaurantId;     // Foreign key to restaurant
    private String itemName;      // Name of the menu item
    private String description;   // Description of the menu item
    private float price;          // Price of the menu item
    private String isAvailable;   // Availability status (could be 'yes' or 'no')
    private String imgpath;         // Image of the menu item
    
	public Menu() {
		super();
	}

	public Menu(int menuId, int restaurantId, String itemName, String description, float price, String isAvailable,
			String imgpath) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
		this.imgpath = imgpath;
	}

	public Menu(int restaurantId, String itemName, String description, float price, String isAvailable,
			String imgpath) {
		super();
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
		this.imgpath = imgpath;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", restaurantId=" + restaurantId + ", itemName=" + itemName + ", description="
				+ description + ", price=" + price + ", isAvailable=" + isAvailable + ", imgpath=" + imgpath + "]";
	}
    
	
    
}
