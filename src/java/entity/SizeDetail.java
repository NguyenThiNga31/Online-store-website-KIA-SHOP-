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

    private int pID;
    private int quantity;


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


}
