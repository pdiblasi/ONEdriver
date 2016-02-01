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
@Path("oneimage")
public class OneImage {

    @Context
    private UriInfo context;

    private XmlRpcClient client = new XmlRpcClient();
    private XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    public String credential="oneadmin:depjouphCaj2";
    public String endpoint="http://localhost:2633/RPC2";
    /**
     * Creates a new instance of OneImage
     */
    public OneImage() {
       try{
           this.config.setServerURL(new URL(endpoint));
           this.client.setConfig(this.config);
       }
       catch(MalformedURLException e){
            System.err.println(e);
       }

    }

    /**
     * Description: Allocates a new image in OpenNebula.
     * @param template A string containing the template of the image. Syntax can be the usual attribute=value or XML.
     * @param datastore_ID The datastore ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The allocated resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("allocate/{string_template}/{int_datastore_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String allocate(@PathParam("string_template")String template, @PathParam("int_datastore_id")int datastore_ID) throws XmlRpcException{
        Object[] params = {credential, template, datastore_ID};

        Object[] result = (Object[])this.client.execute("one.image.allocate",params);

        return result[1].toString();
    }

    /**
     * Description: Clones an existing image.
     * @param ID The ID of the image to be cloned.
     * @param new_name Name for the new image.
     * @param target_datastore_ID The ID of the target datastore. Optional, can be set to -1 to use the current one.
     * @return result[0] true or false whenever is successful or not
     * result[1] The new image ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("clone/{int_id}/{string_new_name}/{int_target_datastore_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String clone(@PathParam("int_id")int ID, @PathParam("string_new_name")String new_name, @PathParam("int_target_datastore_id")int target_datastore_ID) throws XmlRpcException{
        Object[] params = {credential, ID, new_name, target_datastore_ID};

        Object[] result = (Object[])this.client.execute("one.image.clone",params);

        return result[1].toString();
    }
    
    /**
     * Description: Deletes the given image from the pool.
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

        Object[] result = (Object[])this.client.execute("one.image.delete",params);

        return result[1].toString();
    }

    /**
     * Description: Enables or disables an image.
     * @param ID The Image ID.
     * @param enable True for enabling, false for disabling.
     * @return result[0] true or false whenever is successful or not.
     * result[1] The Image ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("enable/{int_id}/{bool_enable}")
    @Produces(MediaType.TEXT_PLAIN)
    public String enable(@PathParam("int_id")int ID, @PathParam("bool_enable")boolean enable) throws XmlRpcException{
        Object[] params = {credential, ID, enable};

        Object[] result = (Object[])this.client.execute("one.image.enable",params);

        return result[1].toString();
    }   
    
    /**
     * Description: Sets the Image as persistent or not persistent.
     * @param ID The Image ID.
     * @param persistent True for persistent, false for non-persisent.
     * @return result[0] true or false whenever is successful or not.
     * result[1] The Image ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("persistent/{int_id}/{bool_persistent}")
    @Produces(MediaType.TEXT_PLAIN)
    public String persistent(@PathParam("int_id")int ID, @PathParam("bool_persistent")boolean persistent) throws XmlRpcException{
        Object[] params = {credential, ID, persistent};

        Object[] result = (Object[])this.client.execute("one.image.persistent",params);

        return result[1].toString();
    }    
    
    /**
     * Description: Changes the type of an Image.
     * @param ID The Image ID.
     * @param new_type New type for the Image. See the existing types in the Image template reference.
     * @return result[0] true or false whenever is successful or not.
     * result[1] The Image ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("chtype/{int_id}/{string_new_type}")
    @Produces(MediaType.TEXT_PLAIN)
    public String chtype(@PathParam("int_id")int ID, @PathParam("string_new_type")String new_type) throws XmlRpcException{
        Object[] params = {credential, ID, new_type};

        Object[] result = (Object[])this.client.execute("one.image.chtype",params);

        return result[1].toString();
    }     
     
    /**
     * Description: Replaces the image template contents.
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

        Object[] result = (Object[])this.client.execute("one.image.update",params);

        return result[1].toString();
    }     
     
    /**
     * Description: Changes the permission bits of an image.
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
    public String chmod(@PathParam("int_id")int ID, @PathParam("int_user_use")int USER_USE, @PathParam("int_user_manage")int USER_MANAGE, @PathParam("int_user_admin")int USER_ADMIN, @PathParam("int_group_use")int GROUP_USE, @PathParam("nt_group_manage")int GROUP_MANAGE, @PathParam("int_group_admin")int GROUP_ADMIN, @PathParam("int_other_use")int OTHER_USE, @PathParam("int_other_manage")int OTHER_MANAGE, @PathParam("int_other_admin")int OTHER_ADMIN) throws XmlRpcException{        Object[] params = {credential, ID, USER_USE, USER_MANAGE, USER_ADMIN, GROUP_USE, GROUP_MANAGE, GROUP_ADMIN, OTHER_USE, OTHER_MANAGE, OTHER_ADMIN};
        Object[] result = (Object[])this.client.execute("one.image.chmod",params);

        return result[1].toString();
    }     
     
    /**
     * Description: Changes the ownership of an image.
     * @param ID The object ID.
     * @param User_ID The User ID of the new owner. If set to -1, the owner is not changed.
     * @param Group_ID The Group ID of the new group. If set to -1, the group is not changed.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("chown/{int_id}/{int_user_id}/{int_group_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String chown(@PathParam("int_id")int ID, @PathParam("int_user_id")int User_ID, @PathParam("int_group_id")int Group_ID) throws XmlRpcException{
        Object[] params = {credential,ID,User_ID,Group_ID};

        Object[] result = (Object[])this.client.execute("one.image.chown",params);

        return result[1].toString();
    }     
     
    /**
     * Description: Renames an image.
     * @param ID The object ID.
     * @param name The new name.
     * @return result[0] true or false whenever is successful or not
     * result[1] The VM ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("rename/{int_ID}/{string_name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String rename(@PathParam("int_id")int ID, @PathParam("string_name")String name) throws XmlRpcException{
        Object[] params = {credential, ID, name};

        Object[] result = (Object[])this.client.execute("one.image.rename",params);

        return result[1].toString();
    }

    /**
     * Description: Deletes a snapshot from the image
     * @param ID The object ID.
     * @param snapshot_ID ID of the snapshot to delete
     * @return result[0] true or false whenever is successful or not
     * result[1] ID of the deleted snapshot/The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("snapshotdelete/{int_id}/{int_snapshot_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String snapshotdelete(@PathParam("int_id")int ID, @PathParam("int_snapshot_id")int snapshot_ID) throws XmlRpcException{
        Object[] params = {credential, ID, snapshot_ID};

        Object[] result = (Object[])this.client.execute("one.image.snapshotdelete",params);

        return result[1].toString();
    }

    /**
     * Description: Reverts image state to a previous snapshot
     * @param ID The object ID.
     * @param snapshot_ID ID of the snapshot to revert to
     * @return result[0] true or false whenever is successful or not
     * result[1] ID of the snapshot/The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("snapshotrevert/{int_id}/{int_snapshot_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String snapshotrevert(@PathParam("int_id")int ID, @PathParam("int_snapshot_id")int snapshot_ID) throws XmlRpcException{
        Object[] params = {credential, ID, snapshot_ID};

        Object[] result = (Object[])this.client.execute("one.image.snapshotrevert",params);

        return result[1].toString();
    }

    /**
     * Description: Flatten the snapshot of image and discards others
     * @param ID The object ID.
     * @param snapshot_ID ID of the snapshot to flatten
     * @return result[0] true or false whenever is successful or not
     * result[1] ID of the snapshot/The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("snapshotflatten/{int_id}/{int_snapshot_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String snapshotflatten(@PathParam("int_id")int ID, @PathParam("int_snapshot_id")int snapshot_ID) throws XmlRpcException{
        Object[] params = {credential, ID, snapshot_ID};

        Object[] result = (Object[])this.client.execute("one.image.snapshotflatten",params);

        return result[1].toString();
    }

    /**
     * Description: Retrieves information for the image.
     * @param ID The object ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The information string / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("inf/{int_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String info(@PathParam("int_id")int ID) throws XmlRpcException{
        Object[] params = {credential, ID};

        Object[] result = (Object[])this.client.execute("one.image.info",params);

        return result[1].toString();
    }     
}
