/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author ADMN
 */
public class ItemDashboard {

    private String Name;
    private double value;
    private long date;

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
