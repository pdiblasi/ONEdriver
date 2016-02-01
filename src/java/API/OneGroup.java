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
@Path("onegroup")
public class OneGroup {

    @Context
    private UriInfo context;

    private XmlRpcClient client = new XmlRpcClient();
    private XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    public String credential="oneadmin:depjouphCaj2";
    public String endpoint="http://localhost:2633/RPC2";
    /**
     * Creates a new instance of OneGroup
     */
    public OneGroup() {
       try{
           this.config.setServerURL(new URL(endpoint));
           this.client.setConfig(this.config);
       }
       catch(MalformedURLException e){
            System.err.println(e);
       }

    }

    /**
     * Description: Allocates a new group in OpenNebula.
     * @param name Name for the new group.
     * @return result[0] true or false whenever is successful or not
     * result[1] The allocated Group ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("allocate/{string_name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String allocate(@PathParam("string_name")String name) throws XmlRpcException{
        Object[] params = {credential, name};

        Object[] result = (Object[])this.client.execute("one.group.allocate",params);

        return result[1].toString();
    }

    /**
     * Description: Deletes the given group from the pool.
     * @param ID The object ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("delete/{int_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("int_id")int ID) throws XmlRpcException{
        Object[] params = {credential, ID};

        Object[] result = (Object[])this.client.execute("one.group.delete",params);

        return result[1].toString();
    }

    /**
     * Description: Retrieves information for the group.
     * @param ID The object ID. If it is -1, then the connected user’s group info info is returned
     * @return result[0] true or false whenever is successful or not
     * result[1] The information string / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("info/{int_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String info(@PathParam("int_id")int ID) throws XmlRpcException{
        Object[] params = {credential, ID};

        Object[] result = (Object[])this.client.execute("one.group.info",params);

        return result[1].toString();
    }

    /**
     * Description: Replaces the group template contents.
     * @param ID The object ID.
     * @param template The new template contents. Syntax can be the usual attribute=value or XML.
     * @param type Update type: 0: Replace the whole template. 1: Merge new template with the existing one.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("update/{int_id}/{string_template}/{int_type}")
    @Produces(MediaType.TEXT_PLAIN)
    public String update(@PathParam("int_id")int ID, @PathParam("string_template")String template, @PathParam("int_type")int type) throws XmlRpcException{
        Object[] params = {credential, ID, template, type};

        Object[] result = (Object[])this.client.execute("one.group.update",params);

        return result[1].toString();
    }

    /**
     * Description: Adds a User to the Group administrators set
     * @param group_ID The group ID.
     * @param user_ID The user ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("addadmin/{int_group_id}/{int_user_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addadmin(@PathParam("int_group_id")int group_ID, @PathParam("int_user_id")int user_ID) throws XmlRpcException{
        Object[] params = {credential, group_ID, user_ID};

        Object[] result = (Object[])this.client.execute("one.group.addadmin",params);

        return result[1].toString();
    }

    /**
     * Description: Removes a User from the Group administrators set
     * @param group_ID The group ID.
     * @param user_ID The user ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("deladmin/{int_group_id}/{int_user_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deladmin(@PathParam("int_group_id")int group_ID, @PathParam("int_user_id")int user_ID) throws XmlRpcException{
        Object[] params = {credential, group_ID, user_ID};

        Object[] result = (Object[])this.client.execute("one.group.deladmin",params);

        return result[1].toString();
    }

    /**
     * Description: Sets the group quota limits.
     * @param ID The object ID.
     * @param template The new quota template contents. Syntax can be the usual attribute=value or XML.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("quota/{int_id}/{string_template}")
    @Produces(MediaType.TEXT_PLAIN)
    public String quota(@PathParam("int_id")int ID, @PathParam("string_template")String template) throws XmlRpcException{
        Object[] params = {credential, ID, template};

        Object[] result = (Object[])this.client.execute("one.group.quota",params);

        return result[1].toString();
    }

}
