package org.youfood.resources;

import org.youfood.model.Category;

import javax.ejb.Local;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Local
@Path("/categories")
public interface CategoryResource {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Category getCategoryById(@PathParam("id") Long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategories();
}
