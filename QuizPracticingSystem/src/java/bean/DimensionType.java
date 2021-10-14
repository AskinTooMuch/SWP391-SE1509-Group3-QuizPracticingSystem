/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  Dimension Type entity
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         DuongNHHE150328      First Deploy
 *  14/10/21    1.0         ChucNVHE150618      Comment
*/
package bean;

/**
 *
 * @author duong
 */
public class DimensionType {

    private int dimensionTypeId; /*Dimension type id*/
    private String dimensionTypeName; /*Dimension type name*/
    private boolean status; /*Dimension Type Status*/

    /**
     * Blank constructor
     */
    public DimensionType() {
    }

    /**
     * Complete constructor
     * @param dimensionTypeId
     * @param dimensionTypeName
     * @param status 
     */
    public DimensionType(int dimensionTypeId, String dimensionTypeName, boolean status) {
        this.dimensionTypeId = dimensionTypeId;
        this.dimensionTypeName = dimensionTypeName;
        this.status = status;
    }

    /**
     * Get dimension Type Id
     * @return 
     */
    public int getDimensionTypeId() {
        return dimensionTypeId;
    }

    /**
     * Set dimension Type id
     * @param dimensionTypeId 
     */
    public void setDimensionTypeId(int dimensionTypeId) {
        this.dimensionTypeId = dimensionTypeId;
    }

    /**
     * Get Dimension Type name
     * @return 
     */
    public String getDimensionTypeName() {
        return dimensionTypeName;
    }

    /**
     * Get dimension Type status
     * @return 
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Set dimension Type name
     * @param dimensionTypeName 
     */
    public void setDimensionTypeName(String dimensionTypeName) {
        this.dimensionTypeName = dimensionTypeName;
    }

    /**
     * Set dimension type status
     * @param status 
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

}
