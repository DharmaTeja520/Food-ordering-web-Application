package com.tapfoods.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    // The cart items, stored as a map of item IDs to CartItem objects
    private Map<Integer, CartItem> items;

    // Constructor to initialize the cart
    public Cart() {
        this.items = new HashMap<>();
    }

    // Add an item to the cart
    public void addItem(CartItem item) {
        int itemId = item.getItemId();
        if (items.containsKey(itemId)) {
            // If the item already exists, increase the quantity
            CartItem existingItem = items.get(itemId);
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            // If the item is new, add it to the cart
            items.put(itemId, item);
        }
    }

    // Update the quantity of an item in the cart
    public void updateItem(int itemId, int quantity) {
        if (items.containsKey(itemId)) {
            if (quantity <= 0) {
                // Remove the item if the quantity is zero or less
                items.remove(itemId);
            } else {
                // Update the item's quantity
                CartItem cartItem = items.get(itemId);
                cartItem.setQuantity(quantity);
            }
        }
    }

    // Remove an item from the cart
    public void removeItem(int itemId) {
        items.remove(itemId);
    }

    // Get all items in the cart
    public Map<Integer, CartItem> getItems() {
        return items;
    }

    // Clear the cart
    public void clear() {
        items.clear();
    }

	public double getTotalAmount() {
		    double total = 0;
		    for (CartItem item : items.values()) {
		        total += item.getPrice() * item.getQuantity(); // price * quantity for each item
		    }
		    return total;
		}
		
	}
