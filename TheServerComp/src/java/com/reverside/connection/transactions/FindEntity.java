/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.connection.transactions;

import com.reverside.entity.Cuisine;
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
        Restaurant restaurant = Transactions.getRestaurant(id);
        return restaurant.toString();
    }
    
    @GET
    @Path("/restaurantAll") 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRestaurants() {
        GenericEntity generic = null;
        
        try {
            generic = new GenericEntity<List<Restaurant>>(Transactions.getRestaurantAll()){};
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return Response.status(201).entity(generic).build();
    }
    
    @GET
    @Path("/cuisine")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCuisineInfo(@QueryParam("id") int id) throws Exception {
        Cuisine cuisine = Transactions.getCuisine(id);
        return cuisine.toString();
    }
    
    @GET
    @Path("/cuisineAll") 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCuisines(@QueryParam("id") int id) {
        GenericEntity generic = null;
        
        try {
            generic = new GenericEntity<List<Cuisine>>(Transactions.getCuisineAll(id)){};
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return Response.status(201).entity(generic).build();
    }
}
