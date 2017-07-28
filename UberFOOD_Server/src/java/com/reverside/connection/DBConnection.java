/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author RSS
 */
public class DBConnection {
    @SuppressWarnings("finally")
    public static Connection createConnection()
            throws Exception {
        Connection con = null;
        
        try{
            Class.forName(Constants.MySQLConn.dbClass);
            con = DriverManager.getConnection(
                    Constants.MySQLConn.dbUrl, 
                    Constants.MySQLConn.dbUser, 
                    Constants.MySQLConn.dbPwd);
        } catch(Exception e) {
            throw e;
        } finally {
            return con;
        }
    }
}
