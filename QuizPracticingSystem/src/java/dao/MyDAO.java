/**
 *  Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
 *  Created on : Sep 23, 2021
 *  MyDAO
 *  Quiz practicing system
 *
 *  Record of change:
 *  Date        Version     Author              Description
 *  23/9/21     1.0         ChucNVHE150618      First Deploy
 *  18/10/21    1.0         NamDHHE150519       Add comment
*/
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Lớp này chứa các interface của AnswerDAOImpl
 *
 * @author NamDH
 */
public class MyDAO extends DBConnection{
    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    public String xSql = null;
    
    public MyDAO(){
        conn = connection;
    }
    
    public void finalize(){
        try{
            if (conn != null) conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
