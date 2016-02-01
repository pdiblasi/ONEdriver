/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.net.MalformedURLException;
import java.net.URL;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 * REST Web Service
 *
 * @author pdiblasi <ingpdiblasi at gmail.com>
 */
@Path("onesystem")
public class OneSystem {

    @Context
    private UriInfo context;

    private XmlRpcClient client = new XmlRpcClient();
    private XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    public String credential="oneadmin:depjouphCaj2";
    public String endpoint="http://localhost:2633/RPC2";
    /**
     * Creates a new instance of OneSystem
     */
    public OneSystem() {
       try{
           this.config.setServerURL(new URL(endpoint));
           this.client.setConfig(this.config);
       }
       catch(MalformedURLException e){
            System.err.println(e);
       }

    }

    /**
     * Description: Returns the OpenNebula core version
     * @return result[0] true or false whenever is successful or not
     * result[1] The OpenNebula version, e.g. 4.4.0
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("version")
    @Produces(MediaType.TEXT_PLAIN)
    public String version() throws XmlRpcException{
        Object[] params = {credential};

        Object[] result = (Object[])this.client.execute("one.system.version",params);

    return result[1].toString();
    }

    /**
     * Description: Returns the OpenNebula configuration
     * @return result[0] true or false whenever is successful or not
     * result[1] The loaded oned.conf file, in XML form
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("config")
    @Produces(MediaType.TEXT_PLAIN)
    public String config() throws XmlRpcException{
        Object[] params = {credential};

        Object[] result = (Object[])this.client.execute("one.system.config",params);

    return result[1].toString();
    }    
}
