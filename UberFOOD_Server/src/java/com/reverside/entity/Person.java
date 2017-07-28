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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author StrettO
 */
@Entity
@Table(name = "person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
    , @NamedQuery(name = "Person.findByPersonId", query = "SELECT p FROM Person p WHERE p.personId = :personId")
    , @NamedQuery(name = "Person.findByNames", query = "SELECT p FROM Person p WHERE p.names = :names")
    , @NamedQuery(name = "Person.findBySurname", query = "SELECT p FROM Person p WHERE p.surname = :surname")
    , @NamedQuery(name = "Person.findByNationalId", query = "SELECT p FROM Person p WHERE p.nationalId = :nationalId")
    , @NamedQuery(name = "Person.findByDateOfBirth", query = "SELECT p FROM Person p WHERE p.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "Person.findByGender", query = "SELECT p FROM Person p WHERE p.gender = :gender")
    , @NamedQuery(name = "Person.findByAge", query = "SELECT p FROM Person p WHERE p.age = :age")
    , @NamedQuery(name = "Person.findByTokenId", query = "SELECT p FROM Person p WHERE p.tokenId = :tokenId")
    , @NamedQuery(name = "Person.findByUserId", query = "SELECT p FROM Person p WHERE p.userId = :userId")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "person_id")
    private Integer personId;
    @Size(max = 45)
    @Column(name = "names")
    private String names;
    @Size(max = 45)
    @Column(name = "surname")
    private String surname;
    @Size(max = 45)
    @Column(name = "national_id")
    private String nationalId;
    @Size(max = 45)
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Size(max = 45)
    @Column(name = "gender")
    private String gender;
    @Size(max = 45)
    @Column(name = "age")
    private String age;
    @Size(max = 45)
    @Column(name = "token_id")
    private String tokenId;
    @Size(max = 45)
    @Column(name = "user_id")
    private String userId;

    public Person() {
    }

    public Person(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("personId", personId);
            obj.put("names", names);
            obj.put("surname", surname);
            obj.put("nationalId", nationalId);
            obj.put("dateOfBirth", dateOfBirth);
            obj.put("gender", gender);
            obj.put("age", age);
            obj.put("tokenId", tokenId);
            obj.put("userId", userId);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return obj.toString();
    }

    public static Person fromString(String JSONPerson) {
        Person person = new Person();

        try {
            JSONObject obj = new JSONObject(JSONPerson);
            person.setPersonId(obj.getInt("personId"));
            person.setNames(obj.getString("names"));
            person.setSurname(obj.getString("surname"));
            person.setNationalId(obj.getString("nationalId"));
            person.setDateOfBirth(obj.getString("dateOfBirth"));
            person.setGender(obj.getString("gender"));
            person.setAge(obj.getString("age"));
            person.setTokenId(obj.getString("tokenId"));
            person.setUserId(obj.getString("userId"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return person;
    }
    
    public static Person getPerson(int id)
        throws SQLException {
        Connection dbConn = null;
        Person person = new Person();
        
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            
            String sql = "SELECT * FROM person WHERE person_id = " + id;
            Statement st = dbConn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                person.setPersonId(rs.getInt("person_id"));
                person.setNames(rs.getString("names"));
                person.setSurname(rs.getString("surname"));
                person.setGender(rs.getString("gender"));
                person.setDateOfBirth(rs.getString("date_of_birth"));
                person.setAge(rs.getString("age"));
                person.setNationalId(rs.getString("national_id"));
                person.setTokenId(rs.getString("token_id"));
                person.setUserId(rs.getString("user_id"));
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
        
        return person;
    }
    
    public static Person getPersonByUserId(String userId)
        throws SQLException {
        Connection dbConn = null;
        Person person = new Person();
        
        try {
            try {
                dbConn = DBConnection.createConnection();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            
            String sql = "SELECT * FROM person WHERE user_id = '" + userId + "'";
            Statement st = dbConn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                person.setPersonId(rs.getInt("person_id"));
                person.setNames(rs.getString("names"));
                person.setSurname(rs.getString("surname"));
                person.setGender(rs.getString("gender"));
                person.setDateOfBirth(rs.getString("date_of_birth"));
                person.setAge(rs.getString("age"));
                person.setNationalId(rs.getString("national_id"));
                person.setTokenId(rs.getString("token_id"));
                person.setUserId(rs.getString("user_id"));
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
        
        return person;
    }
    
}
