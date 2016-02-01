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
@Path("onegroupquota")
public class OneGroupQuota {

    @Context
    private UriInfo context;

    private XmlRpcClient client = new XmlRpcClient();
    private XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    public String credential="oneadmin:depjouphCaj2";
    public String endpoint="http://localhost:2633/RPC2";
    /**
     * Creates a new instance of OneGroupQuota
     */
    public OneGroupQuota() {
       try{
           this.config.setServerURL(new URL(endpoint));
           this.client.setConfig(this.config);
       }
       catch(MalformedURLException e){
            System.err.println(e);
       }

    }

    /**
     * Description: Returns the default group quota limits.
     * @return result[0] true or false whenever is successful or not
     * result[1] The quota template contents / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("info")
    @Produces(MediaType.TEXT_PLAIN)
    public String info() throws XmlRpcException{
        Object[] params = {credential};

        Object[] result = (Object[])this.client.execute("one.groupquota.info",params);

        return result[1].toString();
    }
    
    /**
     * Description: Updates the default group quota limits.
     * @param new_quota The new quota template contents. Syntax can be the usual attribute=value or XML.
     * @return result[0] true or false whenever is successful or not
     * result[1] The quota template contents / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("update/{string_new_quota}")
    @Produces(MediaType.TEXT_PLAIN)
    public String update(@PathParam("string_new_quota")String new_quota) throws XmlRpcException{
        Object[] params = {credential, new_quota};

        Object[] result = (Object[])this.client.execute("one.groupquota.update",params);

        return result[1].toString();
    }    
}
