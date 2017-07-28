/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.service;

import com.reverside.connection.transactions.Utility;
import com.reverside.entity.Order1;
import com.reverside.entity.OrderList;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author StrettO
 */
@Path("/addEntity")
public class AddEntity {
    @POST
    @Path("/addOrder")
    @Produces(MediaType.APPLICATION_JSON)
    public String addOrder(
            @QueryParam("jsonOrder") String jsonOrder) {
        String response = "";
        int retCode = addOrderM(jsonOrder);
        switch (retCode) {
            case 0:
                response = Utility.constructJSON("add", true);
                break;
            case 1:
                response = Utility.constructJSON("add", false, "You are already logged in.");
                break;
            case 2:
                response = Utility.constructJSON("add", false, "Special Characters are not allowed.");
                break;
            case 3:
                response = Utility.constructJSON("add", false, "Error occured");
                break;
            default:
                break;
        }

        return response;
    }
    
    private int addOrderM(String jsonOrder) {
        int result = 3;
        if(Utility.isNotNull(jsonOrder)) {
            try {
                if (Order1.insertOrder(new Order1().fromString(jsonOrder))) {
                    result = 0;
                }
            } catch(SQLException ex) {
                if(ex.getErrorCode() == 1062) {
                    result = 0;
                } else if(ex.getErrorCode() == 1064) {
                    result = 2;
                }
                ex.printStackTrace();
            } catch (Exception ex) {
                result = 3;
                ex.printStackTrace();
            }
        } else {
            result = 3;
        }
        
        
        System.out.println("result=" + result);
        
        return result;
    }
    
    @POST
    @Path("/addOrderList")
    @Produces(MediaType.APPLICATION_JSON)
    public String addOrderList(@QueryParam("jsonOrderList") String jsonOrderList) {
        String response = "";
        int retCode = addOrderListM(jsonOrderList);
        if(retCode == 0) {
            response = Utility.constructJSON("add", true);
        } else if (retCode == 1) {
            response = Utility.constructJSON("add", false, "You are already logged in.");
        } else if(retCode == 2) {
            response = Utility.constructJSON("add", false, "Special Characters are not allowed.");
        } else if(retCode == 3) {
            response = Utility.constructJSON("add", false, "Error occured");
        }

        return response;
    }
    
    private int addOrderListM(String jsonOrderList) {
        int result = 3;
        if(Utility.isNotNull(jsonOrderList)) {
            try {
                if (OrderList.insertOrderList(new OrderList().fromString(jsonOrderList))) {
                    result = 0;
                }
            } catch(SQLException ex) {
                if(ex.getErrorCode() == 1062) {
                    result = 0;
                } else if(ex.getErrorCode() == 1064) {
                    result = 2;
                }
                ex.printStackTrace();
            } catch (Exception ex) {
                result = 3;
                ex.printStackTrace();
            }
        } else {
            result = 3;
        }
        
        return result;
    }
}
