/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.connection;

/**
 *
 * @author RSS
 */
public class Constants {
    
    public static final class OracleConn {
        public static final String dbClass = "oracle.jdbc.OracleDriver";
        private static final String dbName = "oadmin";
        public static final String dbUrl = "jdbc:oracle:thin:@localhost:1523:oadmin";
        public static final String dbUser = "oracle_root";
        public static final String dbPwd = "root1324";
    }
    
    public static final class MySQLConn {
        public static final String dbClass = "com.mysql.jdbc.Driver";
        private static final String dbName = "uberfood";
        public static final String dbUrl = "jdbc:mysql://localhost:3308/uberfood?zeroDateTimeBehavior=convertToNull";
        public static final String dbUser = "root";
        public static final String dbPwd = "root";
    }
}
