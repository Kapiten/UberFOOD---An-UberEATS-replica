/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.entity;

import com.reverside.connection.DBConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author StrettO
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId")
    , @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByTokenId", query = "SELECT u FROM User u WHERE u.tokenId = :tokenId")
    , @NamedQuery(name = "User.findBySessionToken", query = "SELECT u FROM User u WHERE u.sessionToken = :sessionToken")
    , @NamedQuery(name = "User.findByProfilePic", query = "SELECT u FROM User u WHERE u.profilePic = :profilePic")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;
    @Size(max = 100)
    @Column(name = "token_id")
    private String tokenId;
    @Size(max = 45)
    @Column(name = "session_token")
    private String sessionToken;
    @Size(max = 45)
    @Column(name = "profile_pic")
    private String profilePic;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("userId", userId);
            obj.put("username", username);
            obj.put("password", password);
            obj.put("tokenId", tokenId);
            obj.put("sessionToken", sessionToken);
            obj.put("profilePic", profilePic);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public static User fromString(String JSONUser) {
        User user = new User();

        try {
            JSONObject obj = new JSONObject(JSONUser);
            user.setUserId(obj.getInt("userId"));
            user.setUsername(obj.getString("username"));
            user.setPassword(obj.getString("password"));
            user.setTokenId(obj.getString("tokenId"));
            user.setSessionToken(obj.getString("sessionToken"));
            user.setProfilePic(obj.getString("profilePic"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return user;
    }
    
    public static User getUser(int id)
        throws SQLException {
        Connection dbConn = null;
        User user = new User();
            
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            String sql = "SELECT * FROM user WHERE user_id = " + id;
            Statement st = dbConn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setProfilePic(rs.getString("profile_pic"));
                user.setTokenId(rs.getString("token_id"));
                user.setSessionToken(rs.getString("session_token"));
            }
            
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception e) {
            if (dbConn != null) {
                dbConn.close();
            }

            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        
        return user;
    }
    
    public static User getUserByTokenId(String tokenId)
        throws SQLException {
        Connection dbConn = null;
        User user = new User();
            
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            String sql = "SELECT * FROM user WHERE token_id = '" + tokenId + "'";
            Statement st = dbConn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setProfilePic(rs.getString("profile_pic"));
                user.setTokenId(rs.getString("token_id"));
                user.setSessionToken(rs.getString("session_token"));
            }
            
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception e) {
            if (dbConn != null) {
                dbConn.close();
            }

            throw e;
        } finally {
            if (dbConn != null) {
                dbConn.close();
            }
        }
        
        return user;
    }
    
}
