/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;

/**
 *
 * @author Nguyen Thi Nga - CS171351
 */
public class SizeDetail extends Size {

//    private int sizeID;
    private int pID;
    private int quantity;
    
//     private int numberInCart;

    public SizeDetail() {
    }

    public SizeDetail(int sizeID, int pID, int quantity, int sizevalue) {
        super(sizeID, sizevalue);
        this.pID = pID;
        this.quantity = quantity;
    }
    
    public SizeDetail(int sizeID, int pID, int quantity) {
        super(sizeID);
        this.pID = pID;
        this.quantity = quantity;
    }

//    public SizeDetail(int pID, int quantity, int numberInCart, int sizeID, int sizevalue) {
//        super(sizeID, sizevalue);
//        this.sizeID = sizeID;
//        this.pID = pID;
//        this.quantity = quantity;
//        this.numberInCart = numberInCart;
//    }
    
//    public int getSizeID() {
//        return sizeID;
//    }
//
//    public void setSizeID(int sizeID) {
//        this.sizeID = sizeID;
//    }
//
//    public int getSizevalue() {
//        return sizevalue;
//    }
//
//    public void setSizevalue(int sizevalue) {
//        this.sizevalue = sizevalue;
//    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    public int getNumberInCart() {
//        return numberInCart;
//    }
//
//    public void setNumberInCart(int numberInCart) {
//        this.numberInCart = numberInCart;
//    }

}
