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
public class Dimension {
    private int dimensionId;
    private int subjectId;
    private int dimensionTypeId;
    private String dimensionTypeName;
    private String dimensionName;
    private String description;
    private boolean status;

    public Dimension() {
    }

    public Dimension(int dimensionId, int subjectId, int dimensionTypeId, String dimensionTypeName, String dimensionName, String description, boolean status) {
        this.dimensionId = dimensionId;
        this.subjectId = subjectId;
        this.dimensionTypeId = dimensionTypeId;
        this.dimensionTypeName = dimensionTypeName;
        this.dimensionName = dimensionName;
        this.description = description;
        this.status = status;
    }

    public int getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(int dimensionId) {
        this.dimensionId = dimensionId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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

    public void setDimensionType(String dimensionTypeName) {
        this.dimensionTypeName = dimensionTypeName;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
