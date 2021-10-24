/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Oct 18, 2021
 *  Item Dashboard entity
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  18/10/21    1.0         NamDHHE150519       First Deploy
*/
package bean;

/**
 * this entity hold statistics data (name, value, date)
 * 
 * @author NamDH
 */
public class ItemDashboard {

    private String Name; //name of data 
    private double value; //value of data
    private long date; //date of data

    public ItemDashboard() {
    }

    public ItemDashboard(String Name, double value, long date) {
        this.Name = Name;
        this.value = value;
        this.date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }



}
