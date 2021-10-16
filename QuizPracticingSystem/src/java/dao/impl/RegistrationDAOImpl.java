/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  SubjectCateDAO
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author          Description
 *  23/9/21     1.0         NamDHHE150519  First Deploy
 *  9/10/21     1.0         NamDHHE150519  add method View,convertJson
 */
package dao.impl;

import bean.ItemDashboard;
import bean.Quiz;
import bean.Registration;
import bean.Subject;
import bean.ItemDashboard;
import com.google.gson.Gson;
import dao.DBConnection;
import java.util.ArrayList;
import dao.RegistrationDAO;
import dao.SubjectDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author admin
 */
public class RegistrationDAOImpl extends DBConnection implements RegistrationDAO {

    @Override
    public ArrayList<Registration> getAllRegistration() throws Exception {
        return null;

    }

    @Override
    public Registration getRegistrationById(int registrationId) throws Exception {

        return null;
    }

    @Override
    public int addRegistration(Registration newRegistration) throws Exception {
        return 0;
    }

    @Override
    public int editRegistration(int registrationId, Registration editedRegistration) throws Exception {
        return 0;
    }

    @Override
    public int deleteRegistration(int registrationId) throws Exception {
        return 0;
    }

    @Override
    public ArrayList<Subject> getRegistedSubject(int userId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */
        SubjectDAO subjectDAO = new SubjectDAOImpl();
        ArrayList<Subject> registedSubject = new ArrayList<>();
        String sql = "SELECT b.subjectId\n"
                + "  FROM [QuizSystem].[dbo].[Registration] as a "
                + "inner join [QuizSystem].[dbo].[PricePackage] as b "
                + "on a.packId = b.packId where a.userId = ?";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setInt(1, userId);
            rs = pre.executeQuery();
            while (rs.next()) {
                registedSubject.add(subjectDAO.getSubjectbyId(rs.getInt("subjectId")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return registedSubject;
    }

    @Override
    public ArrayList<Subject> getRegistedSubjectbyUserId(int userId) throws Exception {
        Connection conn = null;
        ResultSet rs = null;
        /* Result set returned by the sqlserver */
        PreparedStatement pre = null;
        /* Prepared statement for executing sql queries */

        ArrayList<Subject> registedSubjectbyUserId = new ArrayList();

        /* Sql query */
        String sqlSubject = "SELECT  Subject.[subjectId]\n"
                + "      ,Subject.[subjectName]\n"
                + "      ,Subject.[description]\n"
                + "      ,Subject.[thumbnail]\n"
                + "      ,Subject.[featuredSubject]\n"
                + "      ,Subject.status\n"
                + "  FROM ([QuizSystem].[dbo].[Subject]\n"
                + "inner JOIN PricePackage\n"
                + "ON Subject.subjectId = PricePackage.subjectId)\n"
                + "inner join Registration on Registration.packId=PricePackage.packId\n"
                + "where Registration.userId=? and Subject.status=1";

        /* Get the subject */
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sqlSubject);
            pre.setInt(1, userId);
            rs = pre.executeQuery();
            /* Get information from resultset and add it to arrayList */
            while (rs.next()) {
                int subjectId = rs.getInt("subjectId");
                String subjectName = rs.getString("subjectName");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                Boolean featured = rs.getBoolean("featuredSubject");
                Boolean status = rs.getBoolean("status");

                registedSubjectbyUserId.add(new Subject(subjectId, subjectName, description,
                        thumbnail, featured, status
                ));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return registedSubjectbyUserId;
    }

    @Override
    public ArrayList<ItemDashboard> getSubjectStasistic(String from, String to, ArrayList<Subject> subjectList, String type) throws Exception {
        ArrayList<ItemDashboard> list = new ArrayList();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        int[] subjectIdList = new int[subjectList.size()];
        for (int i = 0; i < subjectList.size(); i++) {
            subjectIdList[i] = subjectList.get(i).getSubjectId();
        }
        String string;
        if (type.equalsIgnoreCase("revenue")) {
            string = "SUM(a.cost) AS revenue";
        } else {
            string = "COUNT(regId) AS registrationCount";
        }
        String sql = "SELECT a.validFrom," + string + ",c.subjectName FROM "
                + "[Registration] AS a join [PricePackage] AS b ON a.packId = b.packId "
                + "join [Subject] AS c ON b.subjectId=c.subjectId "
                + "WHERE b.subjectId IN(";
        for (int i = 0; i < subjectIdList.length - 1; i++) {
            sql += subjectIdList[i] + ",";
        }
        sql += subjectIdList[subjectIdList.length - 1] + ")";

        sql += " AND (a.validFrom >= '" + from + "' AND a.validFrom <= '" + to + "') GROUP BY c.subjectName,a.validFrom order By a.validFrom ";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {

                list.add(new ItemDashboard(rs.getString("subjectName"),
                        rs.getDouble(type),
                        rs.getDate("validFrom").getTime()));

            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pre);
            closeConnection(conn);
        }
        return list;
    }

    @Override
    public ArrayList<ItemDashboard> getRevenueStasistic(String from, String to) {
        ArrayList<ItemDashboard> list = new ArrayList();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        String sql = "select SUM(cost) as revenue, validFrom FROM [Registration] "
                + "WHERE validFrom <= ? AND validFrom >= ? GROUP BY validFrom ORDER BY validFrom";
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, to);
            pre.setString(2, from);
            rs = pre.executeQuery();
            while (rs.next()) {
                list.add(new ItemDashboard("", rs.getDouble("revenue"),
                        rs.getDate("validFrom").getTime()));
            }
        } catch (Exception e) {
        }
        return list;
    }
    @Override
    public ArrayList<ItemDashboard> getRevenueStasisticBySubjectCate(String from, String to) throws Exception {
        String sql="SELECT SUM(cost) AS revenue,a.validFrom,e.subjectCateName FROM (Registration AS a "
                + "JOIN PricePackage AS b ON a.packId = b.packId) "
                + "JOIN [Subject] AS c ON b.subjectId = c.subjectId "
                + "JOIN CategorySubject AS d ON c.subjectId=d.subjectId "
                + "JOIN SubjectCate AS e ON d.cateId = e.subjectCateId "
                + "WHERE validFrom <= ? AND validFrom >= ? "
                + "GROUP BY a.validFrom,e.subjectCateName ORDER BY a.validFrom";
        ArrayList<ItemDashboard> list = new ArrayList();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pre = null;
        try {
            conn = getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, to);
            pre.setString(2, from);
            rs = pre.executeQuery();
            while (rs.next()) {
                list.add(new ItemDashboard(rs.getString("subjectCateName"), rs.getDouble("revenue"),
                        rs.getDate("validFrom").getTime()));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    @Override 
    public ArrayList<String> convertJson(ArrayList<ItemDashboard> viewList) throws Exception {
        // create a new Gson instance
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
        RegistrationDAOImpl IRegistration = new RegistrationDAOImpl();
        SubjectDAO i = new SubjectDAOImpl();
        ArrayList<ItemDashboard> list = IRegistration.getSubjectStasistic("2019-12-12", "2019-12-15", i.get5LastAddedSubject(), "revenue");
        ArrayList<String> a = IRegistration.getNameList(list);
        System.out.print(a.size());

    }
}
