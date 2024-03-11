/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Nguyen Thi Nga - CS171351
 */
public class Size {
    public int sizeID;
    public int sizevalue;

    public Size() {
    }

    public Size(int sizeID, int sizevalue) {
        this.sizeID = sizeID;
        this.sizevalue = sizevalue;
    }
    
     public Size(int sizeID) {
        this.sizeID = sizeID;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public int getSizevalue() {
        return sizevalue;
    }

    public void setSizevalue(int sizevalue) {
        this.sizevalue = sizevalue;
    } 
}