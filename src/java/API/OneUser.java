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
@Path("oneuser")
public class OneUser {

    @Context
    private UriInfo context;

    private XmlRpcClient client = new XmlRpcClient();
    private XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    public String credential="oneadmin:depjouphCaj2";
    public String endpoint="http://localhost:2633/RPC2";
    /**
     * Creates a new instance of OneUser
     */
    public OneUser() {
       try{
           this.config.setServerURL(new URL(endpoint));
           this.client.setConfig(this.config);
       }
       catch(MalformedURLException e){
            System.err.println(e);
       }

    }

    /**
     * Description: Allocates a new user in OpenNebula
     * @param username username for the new user
     * @param password password for the new user
     * @param auth_driver authentication driver for the new user. If it is an empty string, then the default (‘core’) is used
     * @return result[0] true or false whenever is successful or not
     * result[1] The allocated User ID / The error string.
     * result[2] Error code.

     * @throws XmlRpcException
     */
    @GET
    @Path("allocate/{string_username}/{string_password}/{string_auth_driver}")
    @Produces(MediaType.TEXT_PLAIN)
    public String allocate(@PathParam("string_username")String username, @PathParam("string_password")String password, @PathParam("string_auth_driver")String auth_driver) throws XmlRpcException{
        Object[] params = {credential, username, password, auth_driver};

        Object[] result = (Object[])this.client.execute("one.user.allocate",params);

    return result[1].toString();
    }

    /**
     * Description: Deletes the given user from the pool.
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

        Object[] result = (Object[])this.client.execute("one.user.delete",params);

    return result[1].toString();
    }

    /**
     * Description: Changes the password for the given user.
     * @param ID The object ID.
     * @param password The new password
     * @return result[0] true or false whenever is successful or not
     * result[1] The User ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("passwd/{int_id}/{string:password}")
    @Produces(MediaType.TEXT_PLAIN)
    public String passwd(@PathParam("int_id")int ID, @PathParam("string_password")String password) throws XmlRpcException{
        Object[] params = {credential, ID, password};

        Object[] result = (Object[])this.client.execute("one.user.passwd",params);

    return result[1].toString();
    }
    
    /**
     * Description: Generates or sets a login token.
     * @param username The user name to generate the token for
     * @param token The token, if empty oned will generate one
     * @param valid_period Valid period in seconds; 0 reset the token and -1 for a non-expiring token.
     * @return result[0] true or false whenever is successful or not
     * result[1] The new token / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("login/{string_username}/{string_token}/{int_valid_period}")
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@PathParam("string_username")String username, @PathParam("string_token")String token, @PathParam("int_valid_period")int valid_period) throws XmlRpcException{
        Object[] params = {credential, username, token, valid_period};

        Object[] result = (Object[])this.client.execute("one.user.login",params);

    return result[1].toString();
    }
        
    /**
     * Description: Replaces the user template contents.
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

        Object[] result = (Object[])this.client.execute("one.user.update",params);

    return result[1].toString();
    }
    
    /**
     * Description: Changes the authentication driver and the password for the given user.
     * @param ID The object ID.
     * @param new_auth_driver The new authentication driver.
     * @param new_password The new password. If it is an empty string, the password is not changed.
     * @return result[0] true or false whenever is successful or not
     * result[1] The User ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("chauth/{int_id}/{string_new_auth_driver}/{string_new_password}")
    @Produces(MediaType.TEXT_PLAIN)
    public String chauth(@PathParam("int_id")int ID, @PathParam("string_new_auth_driver")String new_auth_driver, @PathParam("string_new_password")String new_password) throws XmlRpcException{
        Object[] params = {credential, ID, new_auth_driver, new_password};

        Object[] result = (Object[])this.client.execute("one.user.chauth",params);

    return result[1].toString();
    }
    
    /**
     * Description: Sets the user quota limits.
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

        Object[] result = (Object[])this.client.execute("one.user.quota",params);

    return result[1].toString();
    }

    /**
     * Description: Changes the group of the given user.
     * @param user_ID The User ID.
     * @param group_ID The Group ID of the new group.
     * @return result[0] true or false whenever is successful or not
     * result[1] The User ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("chgroup/{int_user_id}/{int_group_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String chgroup(@PathParam("int_user_id")int user_ID, @PathParam("int_group_id")int group_ID) throws XmlRpcException{
        Object[] params = {credential, user_ID, group_ID};

        Object[] result = (Object[])this.client.execute("one.user.chgroup",params);

    return result[1].toString();
    }

    /**
     * Description: Adds the User to a secondary group.
     * @param user_ID The User ID.
     * @param group_ID The Group ID of the new group.
     * @return result[0] true or false whenever is successful or not
     * result[1] The User ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("addgroup/{int_user_id}/{int_group_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addgroup(@PathParam("int_user_id")int user_ID, @PathParam("int_group_id")int group_ID) throws XmlRpcException{
        Object[] params = {credential, user_ID, group_ID};

        Object[] result = (Object[])this.client.execute("one.user.addgroup",params);

    return result[1].toString();
    }

    /**
     * Description: Removes the User from a secondary group
     * @param user_ID The User ID.
     * @param group_ID The Group ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The User ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("delgroup/{int_user_id}/{int_group_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delgroup(@PathParam("int_user_id")int user_ID, @PathParam("int_group_id")int group_ID) throws XmlRpcException{
        Object[] params = {credential};

        Object[] result = (Object[])this.client.execute("one.user.delgroup",params);

    return result[1].toString();
    }

    /**
     * Description: Retrieves information for the user.
     * @param ID The object ID. If it is -1, then the connected user’s own info info is returned
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

        Object[] result = (Object[])this.client.execute("one.user.info",params);

    return result[1].toString();
    }
}
