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
    private int dimensionType;
    private String dimensionTypeName;
    private boolean status;

    public DimensionType(int dimensionType, String dimensionTypeName, boolean status) {
        this.dimensionType = dimensionType;
        this.dimensionTypeName = dimensionTypeName;
        this.status = status;
    }

    public int getDimensionType() {
        return dimensionType;
    }

    public String getDimensionTypeName() {
        return dimensionTypeName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setDimensionType(int dimensionType) {
        this.dimensionType = dimensionType;
    }

    public void setDimensionTypeName(String dimensionTypeName) {
        this.dimensionTypeName = dimensionTypeName;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
