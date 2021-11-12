/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.PricePackage;

import dao.DBConnection;
import java.util.ArrayList;
import dao.PricePackageDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author tuan
 */
public class PricePackageDAOImpl extends DBConnection implements PricePackageDAO {

    @Override
    public ArrayList<PricePackage> getAllPricePackage() throws Exception {
       ArrayList<PricePackage> packageList = new ArrayList();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        String sql = "SELECT * FROM [PricePackage]";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                packageList.add(new PricePackage(rs.getInt("packId"),
                        rs.getString("packName"),
                        rs.getInt("subjectId"),
                        rs.getInt("duration"),
                        rs.getFloat("listPrice"),
                        rs.getFloat("salePrice"),
                        rs.getBoolean("status")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return packageList;
    }

    @Override
    public ArrayList<PricePackage> getAllPricePackagesBySubject(int subjectId) throws Exception {
        ArrayList<PricePackage> pricePackages = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        PricePackage pricePackage =null;
        String sql = "SELECT * from PricePackage where subjectId = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, subjectId);

            rs = pre.executeQuery();
            while (rs.next()) {
                pricePackage = new PricePackage(rs.getInt("packId"),
                        rs.getString("packname"),
                        rs.getInt("subjectId"),
                        rs.getInt("duration"),
                        rs.getInt("listPrice"),
                        rs.getInt("salePrice"),
                        rs.getBoolean("status"));
                pricePackages.add(pricePackage);
                return pricePackages;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return null;
    }

    @Override
    public PricePackage getPricePackageById(int packId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        String sql = "SELECT * FROM PricePackage WHERE packId=" + packId;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return new PricePackage(rs.getInt("packId"), 
                        rs.getString("packName"), 
                        rs.getInt("subjectId"), 
                        rs.getInt("duration"), 
                        rs.getFloat("listPrice"), 
                        rs.getFloat("salePrice"), 
                        rs.getBoolean("status"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return null;
    }

    @Override
    public int addPricePackage(PricePackage newPricePackage) throws Exception {
        return 0;
    }

    @Override
    public int updatePricePackage(PricePackage updatedPricePackage) throws Exception {
        return 0;
    }

    @Override
    public int deletePricePackage(int ppId) throws Exception {
        return 0;
    }
     
    
}

