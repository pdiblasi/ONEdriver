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
@Path("onedocument")
public class OneDocument {

    @Context
    private UriInfo context;

    private XmlRpcClient client = new XmlRpcClient();
    private XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    public String credential="oneadmin:depjouphCaj2";
    public String endpoint="http://localhost:2633/RPC2";
    /**
     * Creates a new instance of OneDocument
     */
    public OneDocument() {
       try{
           this.config.setServerURL(new URL(endpoint));
           this.client.setConfig(this.config);
       }
       catch(MalformedURLException e){
            System.err.println(e);
       }

    }

    /**
     * Description: Allocates a new document in OpenNebula.
     * @param template A string containing the document template contents. Syntax can be the usual attribute=value or XML.
     * @param type The document type.Type is an integer value used to allow dynamic pools compartmentalization.

Let’s say you want to store documents representing Chef recipes, and EC2 security groups; you would allocate documents of each kind with a different type. This type is then used in the one.documentpool.info method to filter the results.

     * @return result[0] true or false whenever is successful or not
     * result[1] The allocated resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("allocate/{string_template}/{int_type}")
    @Produces(MediaType.TEXT_PLAIN)
    public String allocate(@PathParam("string_template")String template, @PathParam("int_type")int type) throws XmlRpcException{
        Object[] params = {credential, template, type};

        Object[] result = (Object[])this.client.execute("one.document.allocate",params);

        return result[1].toString();
    }

    /**
     * Description: Clones an existing virtual machine document.
     * @param ID The ID of the document to be cloned.
     * @param name Name for the new document.
     * @return result[0] true or false whenever is successful or not
     * result[1] The new document ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("clone/{int_id}/{string_name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String clone(@PathParam("int_id")int ID, @PathParam("string_name")String name) throws XmlRpcException{
        Object[] params = {credential, ID, name};

        Object[] result = (Object[])this.client.execute("one.document.clone",params);

        return result[1].toString();
    }

    /**
     * Description: Deletes the given document from the pool.
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

        Object[] result = (Object[])this.client.execute("one.document.delete",params);

        return result[1].toString();
    }
    
    /**
     * Description: Replaces the document template contents.
     * @param ID The object ID.
     * @param template The new document template contents. Syntax can be the usual attribute=value or XML.
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

        Object[] result = (Object[])this.client.execute("one.document.update",params);

        return result[1].toString();
    }
    
    /**
     * Description: Changes the permission bits of a document.
     * @param ID The object ID.
     * @param USER_USE USER USE bit. If set to -1, it will not change.
     * @param USER_MANAGE USER MANAGE bit. If set to -1, it will not change.
     * @param USER_ADMIN USER ADMIN bit. If set to -1, it will not change.
     * @param GROUP_USE GROUP USE bit. If set to -1, it will not change.
     * @param GROUP_MANAGE GROUP MANAGE bit. If set to -1, it will not change.
     * @param GROUP_ADMIN GROUP ADMIN bit. If set to -1, it will not change.
     * @param OTHER_USE OTHER USE bit. If set to -1, it will not change.
     * @param OTHER_MANAGE OTHER MANAGE bit. If set to -1, it will not change.
     * @param OTHER_ADMIN OTHER ADMIN bit. If set to -1, it will not change.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("chmod/{int_id}/{int_user_use}/{int_user_manager}/{int_user_admin}/{int_group_use}/{int_group_manager}/{int_group_admin}/{int_other_use}/{int_other_manager}/{int_other_admin}")
    @Produces(MediaType.TEXT_PLAIN)    
    public String chmod(@PathParam("int_id")int ID, @PathParam("int_user_use")int USER_USE, @PathParam("int_user_manage")int USER_MANAGE, @PathParam("int_user_admin")int USER_ADMIN, @PathParam("int_group_use")int GROUP_USE, @PathParam("nt_group_manage")int GROUP_MANAGE, @PathParam("int_group_admin")int GROUP_ADMIN, @PathParam("int_other_use")int OTHER_USE, @PathParam("int_other_manage")int OTHER_MANAGE, @PathParam("int_other_admin")int OTHER_ADMIN) throws XmlRpcException{
        Object[] params = {credential, ID, USER_USE, USER_MANAGE, USER_ADMIN, GROUP_USE, GROUP_MANAGE, GROUP_ADMIN, OTHER_USE, OTHER_MANAGE, OTHER_ADMIN};

        Object[] result = (Object[])this.client.execute("one.document.chmod",params);

        return result[1].toString();
    }

    /**
     * Description: Changes the ownership of a document.
     * @param ID The object ID.
     * @param user_ID The User ID of the new owner. If set to -1, the owner is not changed.
     * @param group_ID The Group ID of the new group. If set to -1, the group is not changed.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("chown/{int_id}/{int_user_id}/{int_group_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String chown(@PathParam("int_id")int ID, @PathParam("int_user_id")int User_ID, @PathParam("int_group_id")int Group_ID) throws XmlRpcException{
        Object[] params = {credential, ID, User_ID, Group_ID};

        Object[] result = (Object[])this.client.execute("one.document.chown",params);

        return result[1].toString();
    }
    
    /**
     * Description: Renames a document.
     * @param ID The object ID.
     * @param new_name The new name.
     * @return result[0] true or false whenever is successful or not
     * result[1] The VM ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("rename/{int_id}/{string_new_name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String rename(@PathParam("int_id")int ID, @PathParam("string_new_name")String new_name) throws XmlRpcException{
        Object[] params = {credential, ID, new_name};

        Object[] result = (Object[])this.client.execute("one.document.rename",params);

        return result[1].toString();
    }
    
    /**
     * Description: Locks the document at the api level. The lock automatically expires after 2 minutes.
     * @param ID The object ID.
     * @param requesting_app String to identify the application requesting the lock.
     * @return result[0] true or false whenever is successful or not
     * result[1] True if the lock was granted, and false if the object is already locked. / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("lock/{int_id}/{string_requesting_app}")
    @Produces(MediaType.TEXT_PLAIN)
    public String lock(@PathParam("int_id")int ID, @PathParam("string_requesting_app")String requesting_app) throws XmlRpcException{
        Object[] params = {credential, ID, requesting_app};

        Object[] result = (Object[])this.client.execute("one.document.lock",params);

        return result[1].toString();
    }

    /**
     * Description: Unlocks the document at the api level.
     * @param ID The object ID.
     * @param requesting_app String to identify the application requesting the lock.
     * @return result[0] true or false whenever is successful or not
     * result[1] The object ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("unlock/{int_id}/{string_requesting_app}")
    @Produces(MediaType.TEXT_PLAIN)
    public String unlock(@PathParam("int_id")int ID, @PathParam("string_requesting_app")String requesting_app) throws XmlRpcException{
        Object[] params = {credential, ID, requesting_app};

        Object[] result = (Object[])this.client.execute("one.document.unlock",params);

        return result[1].toString();
    }
    
    /**
     * Description: Retrieves information for the document.
     * @param ID The object ID.
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

        Object[] result = (Object[])this.client.execute("one.document.info",params);

        return result[1].toString();
    }
}
