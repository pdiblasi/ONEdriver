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
@Path("oneacl")
public class OneACL {

    @Context
    private UriInfo context;

    private XmlRpcClient client = new XmlRpcClient();
    private XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    public String credential="oneadmin:depjouphCaj2";
    public String endpoint="http://localhost:2633/RPC2";
    /**
     * Creates a new instance of OneACL
     */
    public OneACL() {
       try{
           this.config.setServerURL(new URL(endpoint));
           this.client.setConfig(this.config);
       }
       catch(MalformedURLException e){
            System.err.println(e);
       }

    }

    /**
     * Description: Adds a new ACL rule.
     * @param user User component of the new rule. A string containing a hex number.
     * @param resource Resource component of the new rule. A string containing a hex number.
     * @param rights Rights component of the new rule. A string containing a hex number.
     * @return result[0] true or false whenever is successful or not
     * result[1] The allocated ACL rule ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("addrule/{string_user}/{string_resource}/{string_rights}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addrule(@PathParam("string_user")String user, @PathParam("string_resourc")String resource, @PathParam("string_rights")String rights) throws XmlRpcException{
        Object[] params = {credential, user, resource, rights};

	Object[] result = (Object[])this.client.execute("one.acl.addrule",params);

        return result[1].toString();
    }

    /**
     * Description: Deletes an ACL rule.
     * @param ACL_ID ACL rule ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The ACL rule ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("delrule/{int_acl_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delrule(@PathParam("int_acl_id")int ACL_ID) throws XmlRpcException{
        Object[] params = {credential, ACL_ID};

	Object[] result = (Object[])this.client.execute("one.acl.delrule",params);

        return result[1].toString();
    }

    /**
     * Description: Returns the complete ACL rule set.
     * @param ACL_ID ACL rule ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The information string / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("info/{int_acl_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String info(@PathParam("int_acl_id")int ACL_ID) throws XmlRpcException{
        Object[] params = {credential, ACL_ID};

	Object[] result = (Object[])this.client.execute("one.acl.info",params);

        return result[1].toString();
    }
}
