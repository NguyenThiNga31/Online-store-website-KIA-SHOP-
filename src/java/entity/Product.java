/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;

/**
 *
 * @author Admin
 */
public class Product {

    private int id;
    private String name;
    private String image;
    private double price;
    private String title;
    private String description;
    private int cateID;
    private List<SubImage> subImage;
    private List<SizeDetail> sizedetail;
    private int isDeleted;
    private int amount; //số lượng

    private int numberInCart;
    private int sizeIdInCart;
    private Size sizeInCart;

    public Product() {
    }

    public Product(int id, String name, String image, double price, String title, String description, int cateID, List<SubImage> subImage, List<SizeDetail> sizedetail, int amount, int isDeleted) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.title = title;
        this.description = description;
        this.cateID = cateID;
        this.subImage = subImage;
        this.sizedetail = sizedetail;
        this.amount = amount;
        this.isDeleted = isDeleted;
    }

    public Product(int id, String name, String image, double price, String title, String description, int cateID, List<SubImage> subImage, List<SizeDetail> sizedetail, int amount, int numberInCart, int isDeleted) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.title = title;
        this.description = description;
        this.cateID = cateID;
        this.subImage = subImage;
        this.sizedetail = sizedetail;
        this.amount = amount;
        this.numberInCart = numberInCart;
        this.isDeleted = isDeleted;
    }

    // mới 
    public Product(int id, String name, String image, double price, String title, String description, int cateID, List<SubImage> subImage, List<SizeDetail> sizedetail, int amount, int numberInCart, int isDeleted, int sizeIdInCart) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.title = title;
        this.description = description;
        this.cateID = cateID;
        this.subImage = subImage;
        this.sizedetail = sizedetail;
        this.amount = amount;
        this.numberInCart = numberInCart;
        this.isDeleted = isDeleted;
        this.sizeIdInCart = sizeIdInCart;
    }

    public Product(int id, String name, String image, double price, String title, String description, int cateID, List<SubImage> subImage, List<SizeDetail> sizedetail, int amount, int numberInCart, int isDeleted, Size sizevalue) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.title = title;
        this.description = description;
        this.cateID = cateID;
        this.subImage = subImage;
        this.sizedetail = sizedetail;
        this.amount = amount;
        this.numberInCart = numberInCart;
        this.isDeleted = isDeleted;
        this.sizeInCart = sizevalue;
    }

    public Product(int id, String name, String image, double price, String title, String description, int cateID, List<SubImage> subImage, List<SizeDetail> sizedetail, int amount, int isDeleted, Size sizevalue) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.title = title;
        this.description = description;
        this.cateID = cateID;
        this.subImage = subImage;
        this.sizedetail = sizedetail;
        this.amount = amount;
        this.isDeleted = isDeleted;
        this.sizeInCart = sizevalue;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public List<SubImage> getSubImage() {
        return subImage;
    }

    public void setSubImage(List<SubImage> subImage) {
        this.subImage = subImage;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<SizeDetail> getSizedetail() {
        return sizedetail;
    }

    public void setSizedetail(List<SizeDetail> sizedetail) {
        this.sizedetail = sizedetail;
    }
// mới

    public int getSizeIdInCart() {
        return sizeIdInCart;
    }

    public void setSizeIdInCart(int sizeIdInCart) {
        this.sizeIdInCart = sizeIdInCart;
    }

    public Size getSizeInCart() {
        return sizeInCart;
    }

    public void setSizeInCart(Size sizeInCart) {
        this.sizeInCart = sizeInCart;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", title=" + title + ", description=" + description + ", cateID=" + cateID + '}';
    }

}
