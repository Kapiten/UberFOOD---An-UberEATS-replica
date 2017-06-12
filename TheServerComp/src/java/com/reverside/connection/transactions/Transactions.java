/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.connection.transactions;

import com.reverside.connection.DBConnection;
import com.reverside.entity.Cuisine;
import com.reverside.entity.Restaurant;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RSS
 */
public class Transactions {
    public static boolean insertUser(
            String uname, 
            String passw,
            String names,
            String surname, 
            String email,
            String cell1)
            throws SQLException {
        boolean insertStatus = false;
        Connection dbConn = null;
        
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            int token_id = (int) new java.util.Date().getTime();
            
            Statement stmt = dbConn.createStatement();
            String sqlInsPers = "INSERT into person ("
                    + "names,"
                    + "surname,"
                    + "token_id) "
                    + "values ('" + names + "', "
                    + "'" + surname + "', "
                    + "'" + token_id + "')";
            
            int persRecord = stmt.executeUpdate(sqlInsPers);
            
            String sqlInsUsr = "INSERT into user ("
                    + "username, "
                    + "password,"
                    + "person_id) "
                    + "values ('" + uname + "', "
                    + "'" + passw + "', "
                    + " (SELECT person_id FROM person WHERE token_id = " + token_id + "))";
            
            int records = stmt.executeUpdate(sqlInsUsr);
            
            String sqlInsCont = "INSERT into contact ("
                    + "cell1,"
                    + "email) "
                    + "values ('" + cell1 + "', "
                    + "'" + email + "')";
            
            int contRecords = stmt.executeUpdate(sqlInsCont);
            
            if(persRecord > 0 && records > 0 && contRecords > 0) {
                insertStatus = true;
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
        
        return insertStatus;
    }
    
    public static boolean checkLogin(String uname, String passw)
    throws Exception {
        boolean isUserAvail = false;
        Connection dbConn = null;
        
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM user "
                    + "WHERE username = '" + uname + "' AND "
                    + "password = '" + passw + "'";
            
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                isUserAvail = true;
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
    
        return isUserAvail;
    }
    
    public static Restaurant getRestaurant(int id) throws Exception {
        Connection dbConn = null;
        Restaurant restaurant = new Restaurant();
        
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            Statement stmt = dbConn.createStatement();
            String sqlRest = "SELECT * FROM restaurant WHERE restaurant_id = " + id;
            ResultSet rs = stmt.executeQuery(sqlRest);
            
            while(rs.next()) {
                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setDescription(rs.getString("description"));
                restaurant.setOperatingHours(rs.getString("operating_hours"));
                restaurant.setProfilePic(rs.getString("profile_pic"));
            }
        } catch(SQLException ex) {
                ex.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
         if(dbConn != null) {
                dbConn.close();
            }   
        }
        finally {
            if(dbConn != null) {
                dbConn.close();
            }
        }
        
        return restaurant;
    } 
    
    public static List<Restaurant> getRestaurantAll() throws Exception {
        Connection dbConn = null;
        List<Restaurant> restaurants = new ArrayList<>();
        
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            Statement stmt = dbConn.createStatement();
            String sqlRest = "SELECT * FROM restaurant";
            ResultSet rs = stmt.executeQuery(sqlRest);
            
            while(rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setRestaurantId(rs.getInt("restaurant_id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setDescription(rs.getString("description"));
                restaurant.setOperatingHours(rs.getString("operating_hours"));
                restaurant.setProfilePic(rs.getString("profile_pic"));
                
                restaurants.add(restaurant);
            }
        } catch(SQLException ex) {
                ex.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
         if(dbConn != null) {
                dbConn.close();
            }   
        }
        finally {
            if(dbConn != null) {
                dbConn.close();
            }
        }
        
        return restaurants;
    }
    
    public static Cuisine getCuisine(int id) throws Exception {
        Connection dbConn = null;
        Cuisine cuisine = new Cuisine();
        
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            Statement stmt = dbConn.createStatement();
            String sqlRest = "SELECT * FROM cuisine WHERE cuisine_id = " + id;
            ResultSet rs = stmt.executeQuery(sqlRest);
            
            while(rs.next()) {
                cuisine.setCuisineId(rs.getInt("cuisine_id"));
                cuisine.setName(rs.getString("name"));
                cuisine.setDescription(rs.getString("description"));
                cuisine.setPrice(rs.getString("price"));
                cuisine.setProfilePic(rs.getString("profile_pic"));
                cuisine.setIngredients(rs.getString("ingredients"));
                cuisine.setPreparation(rs.getString("preparation"));
                cuisine.setDateAdded(rs.getString("date_added"));
            }
        } catch(SQLException ex) {
                ex.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
         if(dbConn != null) {
                dbConn.close();
            }   
        }
        finally {
            if(dbConn != null) {
                dbConn.close();
            }
        }
        
        return cuisine;
    }
    
    public static List<Cuisine> getCuisineAll(int restId) throws Exception {
        Connection dbConn = null;
        List<Cuisine> cuisines = new ArrayList<>();
        
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            Statement stmt = dbConn.createStatement();
            String sqlRest = "SELECT * FROM cuisine, restaurant_has_cuisine "
                    + "WHERE restaurant_has_cuisine.restaurant_id = " + restId + " AND "
                    + "cuisine.cuisine_id = restaurant_has_cuisine.cuisine_id";
            ResultSet rs = stmt.executeQuery(sqlRest);
            
            while(rs.next()) {
                Cuisine cuisine = new Cuisine();
                cuisine.setCuisineId(rs.getInt("cuisine_id"));
                cuisine.setName(rs.getString("name"));
                cuisine.setDescription(rs.getString("description"));
                cuisine.setPrice(rs.getString("price"));
                cuisine.setProfilePic(rs.getString("profile_pic"));
                cuisine.setIngredients(rs.getString("ingredients"));
                cuisine.setPreparation(rs.getString("preparation"));
                cuisine.setDateAdded(rs.getString("date_added"));
                
                cuisines.add(cuisine);
            }
        } catch(SQLException ex) {
                ex.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
         if(dbConn != null) {
                dbConn.close();
            }   
        }
        finally {
            if(dbConn != null) {
                dbConn.close();
            }
        }
        
        return cuisines;
    }
}
