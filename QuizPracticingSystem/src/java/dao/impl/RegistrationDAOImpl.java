/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import bean.Registration;
import bean.Subject;
import dao.DBConnection;
import java.util.ArrayList;
import dao.RegistrationDAO;
import dao.SubjectDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
