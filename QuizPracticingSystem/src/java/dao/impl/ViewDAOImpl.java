/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.ItemDashboard;
import com.google.gson.Gson;
import dao.DBConnection;
import dao.ViewDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author ADMN
 */
public class ViewDAOImpl extends DBConnection implements ViewDAO {

    @Override
    public int updateView() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        Date currentDateRaw = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(currentDateRaw);
        String sql;
        if (checkCurrentDateViewExist() == true) {
            sql = "UPDATE [ViewCount] SET [view] = [view] + 1 WHERE [date] = '" + currentDate + "'";
        } else {
            sql = "INSERT INTO [ViewCount] values('" + currentDate + "'," + 1 + ")";
        }
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            return pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
    }

    @Override
    public boolean checkCurrentDateViewExist() throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;

        Date currentDateRaw = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(currentDateRaw);

        String sql = "SELECT * FROM [ViewCount] WHERE date = '" + currentDate + "'";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return false;
    }

    @Override
    public ArrayList<ItemDashboard> getViewStatistics(String from, String to) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        ArrayList<ItemDashboard> itemList = new ArrayList();
        String sql = "SELECT * FROM [ViewCount] WHERE [date]  >= ? AND [date] <= ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, from);
            pre.setString(2, to);
            rs = pre.executeQuery();
            while (rs.next()) {
                itemList.add(new ItemDashboard("view",
                        rs.getDouble("view"),
                        rs.getDate("date").getTime()));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return itemList;
    }

    @Override
    public ArrayList<String> convertJson(ArrayList<ItemDashboard> viewList) throws Exception {
        ArrayList<String> ret = new ArrayList();
        Gson gson = new Gson();
        HashMap<String, Integer> map = new HashMap<>();
        int j = 0;
        ArrayList<ArrayList<ItemDashboard>> list = new ArrayList();

        for (ItemDashboard item : viewList) {
            if (!map.containsKey(item.getName())) {
                map.put(item.getName(), j);
                j++;
                list.add(new ArrayList<>());
            }
            list.get(map.get(item.getName())).add(item);
        }

        // convert your list to json
        for (ArrayList<ItemDashboard> item : list) {
            ret.add(gson.toJson(item));
        }
        // print your generated json
        return ret;
    }

    @Override
    public ArrayList<String> getNameList(ArrayList<ItemDashboard> viewList) throws Exception {
        ArrayList<String> nameList = new ArrayList();
        HashMap<String, Integer> map = new HashMap<>();
        int j = 0;
        for (ItemDashboard subject : viewList) {
            if (!map.containsKey(subject.getName())) {
                map.put(subject.getName(), j);
                nameList.add(subject.getName());
                j++;
            }
        }
        return nameList;
    }

    public static void main(String[] args) throws Exception {
        ViewDAOImpl a = new ViewDAOImpl();
        System.out.print(a.convertJson(a.getViewStatistics("2021-10-1", "2021-10-22")));
    }
}
