package org.youfood.resources;

import org.youfood.model.Menu;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Path("/menus")
public interface MenuResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Menu> getAllMenus();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Menu getMenuById(@PathParam("id") Long id);

}
