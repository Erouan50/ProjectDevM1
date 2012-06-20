package org.youfood.resources;

import org.youfood.model.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Path("/orders")
public interface OrderResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Order> getAllOrders();


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Order getOrder(@PathParam("id") Long id);


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Order updateOrder(Order order);

    @POST
    void addOrder(String order);
}
