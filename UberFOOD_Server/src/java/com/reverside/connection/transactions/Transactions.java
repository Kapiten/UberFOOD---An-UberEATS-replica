/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.connection.transactions;

import com.reverside.connection.DBConnection;
import com.reverside.entity.Contact;
import com.reverside.entity.Cuisine;
import com.reverside.entity.Person;
import com.reverside.entity.Restaurant;
import com.reverside.entity.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author RSS
 */
public class Transactions {
    public static String insertUser(User user, 
            Person person, Contact contact)
            throws SQLException {
        Connection dbConn = null;
        
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            Statement stmt = dbConn.createStatement();
           
            user.setSessionToken(new Random().nextLong() + "");
            String sqlInsUsr = "INSERT into user ("
                    + "username, "
                    + "password, "
                    + "token_id) "
                    + "values ('" + user.getUsername() + "', "
                    + "'" + user.getPassword() + "', "
                    + "'" + user.getTokenId()+ "')";
            
            int userRecords = stmt.executeUpdate(sqlInsUsr);
            
            String sqlInsPers = "INSERT into person ("
                    + "names,"
                    + "surname,"
                    + "token_id,"
                    + "user_id) "
                    + "values ('" + person.getNames() + "', "
                    + "'" + person.getSurname() + "', "
                    + "'" + user.getTokenId() + "',"
                    + "(SELECT user_id FROM user WHERE token_id = '" + user.getTokenId() + "'))";
            
            int persRecord = stmt.executeUpdate(sqlInsPers);
            
            String sqlInsCont = "INSERT into contact ("
                    + "cell1,"
                    + "email,"
                    + "person_id) "
                    + "values ('" + contact.getCell1() + "', "
                    + "'" + contact.getEmail() + "',"
                    + "(SELECT person_id FROM person WHERE token_id = '" + user.getTokenId() + "'))";
            
            int contRecords = stmt.executeUpdate(sqlInsCont);
            
            if(persRecord > 0 && userRecords > 0 && contRecords > 0) {
                
            } else {
                user = new User();
            }
        } catch(SQLException ex) {
            throw ex;
        } catch(Exception e) {
            if(dbConn != null) {
                dbConn.close();
            }
            
            throw e;
        } finally {
            if(dbConn != null) {
                dbConn.close();
            }
        }
        
        return user.toString();
    }
    
    public static String checkLogin(User user)
    throws Exception {
        String jsonUser = "";
        Connection dbConn = null;
        
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM user "
                    + "WHERE username = '" + user.getUsername() + "' AND "
                    + "password = '" + user.getPassword() + "'";
            
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user.setUserId(rs.getInt("user_id"));
                if(rs.getString("session_token").equals("")) {
                    user.setSessionToken(new Random().nextLong() + "");
                } else {
                    user.setSessionToken(rs.getString("session_token"));
                }
            }
        }
        catch(SQLException ex) {
                throw ex;
        } catch(Exception e) {
         if(dbConn != null) {
                dbConn.close();
            }   
        }
        finally {
            if(dbConn != null) {
                dbConn.close();
            }
        }
    
        return user.toString();
    }
        
}
