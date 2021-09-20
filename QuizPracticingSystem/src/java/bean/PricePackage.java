/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author admin
 */
public class PricePackage {
    private int packId;
    private String packName;
    private int subjectId;
    private int duration;
    private float listPrice;
    private float salePrice;
    private boolean status;

    public PricePackage() {
    }

    public PricePackage(int packId, int subjectId, int duration, String packName, float listPrice, float salePrice, boolean status) {
        this.packId = packId;
        this.subjectId = subjectId;
        this.duration = duration;
        this.packName = packName;
        this.listPrice = listPrice;
        this.salePrice = salePrice;
        this.status = status;
    }

    public int getPackId() {
        return packId;
    }

    public void setPackId(int packId) {
        this.packId = packId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public float getListPrice() {
        return listPrice;
    }

    public void setListPrice(float listPrice) {
        this.listPrice = listPrice;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
