/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.concurrent.ExecutorService;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Param;
import static sun.rmi.transport.TransportConstants.Return;

/**
 * REST Web Service
 *
 * @author pdiblasi <ingpdiblasi at gmail.com>
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        
    }

    /**
     * Retrieves representation of an instance of entities.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("simple")
    @Produces(MediaType.TEXT_XML)
    public String getXml() {
        //TODO return proper representation object
        return "<h1>lol</h1>";
    }
    
    
    @GET
    @Path("special")
    @Produces({"application/xml", "application/json"})
    public String myMethod() {
        //TODO return proper representation object
        return "<h1>my method lol</h1>";
    }
    private ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();
    
    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */

    @PUT
    @Path("concat/{varX}/{varY}")
    @Consumes(MediaType.APPLICATION_XML)
    public String putXml(@PathParam("varX") String varX, @PathParam("varY") String varY) {
        return "<h1>loooool</h1>";
    }
    
    @GET
    @Path("concat/{varX}/{varY}")
    @Consumes(MediaType.TEXT_PLAIN)
    public String prova(@PathParam("varX") String varX, @PathParam("varY") String varY) {
        return varX.concat(varY);
    }    
}
