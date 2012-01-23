package org.youfood.resources;

import org.youfood.model.Menu;
import org.youfood.service.MenuService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Path("menu")
public class MenuWebService {

    @Inject
    private MenuService menuService;

    @POST
    @Path("{name}")
    public Response addMenu(@PathParam("name") String name) {
        Menu menu = new Menu();
        menu.setName(name);
        menuService.addMenu(menu);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    public String getMenu(@PathParam("id") String id) {
        Menu menu = menuService.getMenuById(Long.parseLong(id));
        return menu.toString();
    }

    @PUT
    @Path("{name}/{id}")
    public Response putMenu(@PathParam("name") String name, @PathParam("id") String id) {
        Menu menu = new Menu();
        menu.setName(name);
        menu.setId(Long.parseLong(id));
        menuService.updateMenu(menu);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteMenu(@PathParam("id") String id) {
        Menu menu = new Menu();
        menu.setId(Long.parseLong(id));
        menuService.removeMenu(menu);
        return Response.ok().build();
    }
}
