/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dao.DAO;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author dell
 */
public class Cart {
    private int productID;
    private int accountID;
    private int sizeID;
    private int quantity;


   private List<Product> items;

    public Cart(int productID, int accountID, int sizeID, int quantity) {
        this.productID = productID;
        this.accountID = accountID;
        this.sizeID = sizeID;
        this.quantity = quantity;
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

    public Cart() {
    }

    public Cart(List<Product> items) {
        this.items = items;
    }

     public void add(Product ci, int sizeValue) {
        for (Product product : items) {
            if (ci.getId() == product.getId() && sizeValue == product.getSizeInCart().getSizevalue()) {
                DAO dao = new DAO();
                SizeDetail size = dao.getProductSizesByProductIDAndSizeValue(product.getId(), product.getSizeInCart().getSizevalue());
                if ((product.getNumberInCart() + 1) <= size.getQuantity()) {
                    product.setNumberInCart(product.getNumberInCart() + 1);
                }
                return;
            }
        }
        items.add(ci);
    }

    public void minus(Product ci, int sizeId) {
        for (Product product : items) {
            if (ci.getId() == product.getId() && sizeId == product.getSizeInCart().getSizeID()) {
                if (product.getNumberInCart() > 1) {
                    product.setNumberInCart(product.getNumberInCart() - 1);
                } else {
                    items.remove(product);
                }
                return;
            }
        }
    }

    public void remove(int id, int sizeId) {
        for (Product product : items) {
            if (product.getId() == id && sizeId == product.getSizeInCart().getSizeID()) {
                items.remove(product);
                return;
            }
        }
    }

    public double getAmount() {
        double s = 0;
        for (Product product : items) {
            s += product.getPrice() * product.getNumberInCart();
        }
        return Math.round(s * 100.0) / 100.0;
    }

    public List<Product> getItems() {
        return items;
    }

}
