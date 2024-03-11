/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.List;

/**
 *
 * @author MSI GTX
 */
public class CartItem {

    private int productID;
    private int accountID;
    private int sizeID;
    private int quantity;
    private String image;
    private double price;
    private String pName;
    private List<CartItem> items;
    private int sizevalue;

    public CartItem() {
    }

    public CartItem(int productID, int accountID, int sizeID, int quantity, String image, double price, String pName, int sizevalue) {
        this.productID = productID;
        this.accountID = accountID;
        this.sizeID = sizeID;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
        this.pName = pName;
        this.sizevalue = sizevalue;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public int getSizevalue() {
        return sizevalue;
    }

    public void setSizevalue(int sizevalue) {
        this.sizevalue = sizevalue;
    }



    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public CartItem(List<CartItem> items) {
        this.items = items;
    }

    public double getAmount() {
        double s = 0;
        for (CartItem cartitem : items) {
            s += cartitem.getPrice() * cartitem.getQuantity();
        }
        return Math.round(s * 100.0) / 100.0;
    }
}
