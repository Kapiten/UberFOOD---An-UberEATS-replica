/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.service;

import com.reverside.connection.transactions.Transactions;
import com.reverside.entity.Cuisine;
import com.reverside.entity.Order1;
import com.reverside.entity.Restaurant;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author reversidesoftwaresolutions
 */
@Path("/findEntity")
public class FindEntity {
    
    @GET
    @Path("/restaurant")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRestaurentInfo(@QueryParam("id") int id) throws Exception {
        Restaurant restaurant = Restaurant.getRestaurant(id);
        return restaurant.toString();
    }
    
    @GET
    @Path("/restaurantAll") 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRestaurants() {
        GenericEntity generic = null;
        
        try {
            generic = new GenericEntity<List<Restaurant>>(Restaurant.getRestaurantAll()){};
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return Response.status(201).entity(generic).build();
    }
    
    @GET
    @Path("/restaurantAllBySearch") 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRestaurantsBySearch(@QueryParam("search") String search) {
        GenericEntity generic = null;
        
        try {
            generic = new GenericEntity<List<Restaurant>>(Restaurant.getRestaurantAllBySearch(search)){};
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return Response.status(201).entity(generic).build();
    }
    
    @GET
    @Path("/cuisine")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCuisineInfo(@QueryParam("id") int id) throws Exception {
        Cuisine cuisine = Cuisine.getCuisine(id);
        return cuisine.toString();
    }
    
    @GET
    @Path("/cuisineAll") 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCuisines(@QueryParam("restaurantId") int id) {
        GenericEntity generic = null;
        
        try {
            generic = new GenericEntity<List<Cuisine>>(Cuisine.getCuisineAll(id)){};
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return Response.status(201).entity(generic).build();
    }
    
    @GET
    @Path("/cuisineAllBySearch") 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCuisinesBySearch(@QueryParam("search") String search) {
        GenericEntity generic = null;
        
        try {
            generic = new GenericEntity<List<Cuisine>>(Cuisine.getCuisineAllBySearch(search)){};
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return Response.status(201).entity(generic).build();
    }
    
    @GET
    @Path("/order")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOrderInfo(@QueryParam("id") int id) throws Exception {
        Order1 order = Order1.getOrderById(id);
        return order.toString();
    }
    
    @GET
    @Path("/orderAll") 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders() {
        GenericEntity generic = null;
        
        try {
            generic = new GenericEntity<List<Order1>>(Order1.getAllOrders()){};
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return Response.status(201).entity(generic).build();
    }
    
    @GET
    @Path("/orderAllByRestaId") 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders(@QueryParam("restaurantId") String restaurantId) {
        GenericEntity generic = null;
        
        try {
            generic = new GenericEntity<List<Order1>>(Order1.getAllOrdersByRestaurantId(restaurantId)){};
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return Response.status(201).entity(generic).build();
    }
}
