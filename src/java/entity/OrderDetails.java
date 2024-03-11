/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Admin
 */
public class OrderDetails {

    private int orderID;
    private int productID;
    private double price;
    private int quantity;

    private String image;
    private String productName;
    private String categoryName;
    private int sizeValue;

    public OrderDetails() {
    }

    public OrderDetails(int orderID, int productID, double price, int quantity) {
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderDetails(int orderID, int productID, double price, int quantity, String image, String productName, String categoryName, int size) {
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.productName = productName;
        this.categoryName = categoryName;
        this.sizeValue = size;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(int sizeValue) {
        this.sizeValue = sizeValue;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "orderID=" + orderID + ", productID=" + productID + ", price=" + price + ", quantity=" + quantity + '}';
    }

}
