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
public class SubjectDashboard {

    private String subjectName;
    private double revenue;
    private long date;

    public SubjectDashboard() {
    }

    public SubjectDashboard(String subjectName, double revenue, long date) {
        this.subjectName = subjectName;
        this.revenue = revenue;
        this.date = date;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

}
