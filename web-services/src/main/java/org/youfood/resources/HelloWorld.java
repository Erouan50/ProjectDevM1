package org.youfood.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author: Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Path("helloword")
public class HelloWorld {

    @GET
    public String getHelloWorld() {
        return "Hello world !";
    }
}
