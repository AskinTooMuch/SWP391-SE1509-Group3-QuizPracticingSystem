/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author duong
 */
public class DimensionType {

    private int dimensionTypeId;
    private String dimensionTypeName;
    private boolean status;

    public DimensionType() {
    }

    public DimensionType(int dimensionTypeId, String dimensionTypeName, boolean status) {
        this.dimensionTypeId = dimensionTypeId;
        this.dimensionTypeName = dimensionTypeName;
        this.status = status;
    }

    public int getDimensionTypeId() {
        return dimensionTypeId;
    }

    public void setDimensionTypeId(int dimensionTypeId) {
        this.dimensionTypeId = dimensionTypeId;
    }

    public String getDimensionTypeName() {
        return dimensionTypeName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setDimensionTypeName(String dimensionTypeName) {
        this.dimensionTypeName = dimensionTypeName;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
