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
@Path("onevm")
public class OneVM {

    @Context
    private UriInfo context;

    private XmlRpcClient client = new XmlRpcClient();
    private XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    public String credential="oneadmin:depjouphCaj2";
    public String endpoint="http://localhost:2633/RPC2";
    /**
     * Creates a new instance of OneVM
     */
    public OneVM() {
       try{
           this.config.setServerURL(new URL(endpoint));
           this.client.setConfig(this.config);
       }
       catch(MalformedURLException e){
            System.err.println(e);
       }

    }

    /**
     * Description: Allocates a new virtual machine in OpenNebula.
     * @param template A string containing the template for the vm. Syntax can be the usual attribute=value or XML.
     * @param hold False to create the VM on pending (default), True to create it on hold.
     * @return result[0] true or false whenever is successful or not
     * result[1] The allocated resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("allocate/{string_template}/{bool_hold}")
    @Produces(MediaType.TEXT_PLAIN)
    public String allocate(@PathParam("string_template")String template, @PathParam("bool_hold")boolean hold) throws XmlRpcException{
        Object[] params = {credential, template, hold};

        Object[] result = (Object[])this.client.execute("one.vm.allocate",params);

    return result[1].toString();
    }
    
    /**
     * Description: initiates the instance of the given vmid on the target host.
     * @param ID The object ID.
     * @param Host_ID The Host ID of the target host where the VM will be deployed.
     * @param force true to enforce the Host capacity is not overcommitted.
     * @param datastore_ID The Datastore ID of the target system datastore where the VM will be deployed. It is optional, and can be set to -1 to let OpenNebula choose the datastore.
     * @return result[0] true or false whenever is successful or not
     * result[1] The VM ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("deploy/{int_id}/{int_host_id}/{bool_force}/{int_datastore_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deploy(@PathParam("int_id")int ID, @PathParam("int_host_id")int Host_ID, @PathParam("bool_force")boolean force, @PathParam("int_datastore_id")int datastore_ID) throws XmlRpcException{
        Object[] params = {credential, ID, Host_ID, force, datastore_ID};

        Object[] result = (Object[])this.client.execute("one.vm.deploy",params);

    return result[1].toString();
    }
    
    /**
     * Description: submits an action to be performed on a virtual machine.
     * @param action the action name to be performed.
     * The action String must be one of the following:
        shutdown
        shutdown-hard
        hold
        release
        stop
        suspend
        resume
        delete
        delete-recreate
        reboot
        reboot-hard
        resched
        unresched
        poweroff
        poweroff-hard
        undeploy
        undeploy-hard
     * @param ID The object ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The VM ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("action/{string_action}/{int_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String action(@PathParam("string_action")String action, @PathParam("int_id")int ID) throws XmlRpcException{
        Object[] params = {credential, action, ID};

        Object[] result = (Object[])this.client.execute("one.vm.action",params);

                return result[1].toString();
    }
    
    /**
     * Description: migrates one virtual machine (vid) to the target host (hid).
     * @param ID The object ID.
     * @param target_host_ID the target host id (hid) where we want to migrate the vm.
     * @param live if true we are indicating that we want livemigration, otherwise false.
     * @param force true to enforce the Host capacity is not overcommitted.
     * @param target_datastore_ID 	the target system DS id where we want to migrate the vm.
     * @return result[0] true or false whenever is successful or not
     * result[1] The VM ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("migrate/{int_id}/{int_target_host_id}/{bool_live}/{bool_force}/{int_target_datastore_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String migrate(@PathParam("int_id")int ID, @PathParam("int_target_host_id")int target_host_ID, @PathParam("bool_live")boolean live, @PathParam("bool_force")boolean force, @PathParam("int_target_datastore_id")int target_datastore_ID) throws XmlRpcException{
        Object[] params = {credential, ID, target_host_ID, live, force, target_datastore_ID};

        Object[] result = (Object[])this.client.execute("one.vm.migrate",params);

    return result[1].toString();
    }
    
    /**
     * Description: Sets the disk to be saved in the given image.
     * @param ID The object ID.
     * @param disk_ID Disk ID of the disk we want to save.
     * @param new_image_name Name for the new Image where the disk will be saved.
     * @param new_image_type Type for the new Image. If it is an empty string, then the default one will be used. See the existing types in the Image template reference.
     * @param snapshot_ID Id of the snapshot to export, if -1 the current image state will be used.
     * @return
     * result[0] true or false whenever is successful or not
     * result[1] The new allocated Image ID / The error string.
       If the Template was cloned, the new Template ID is not returned. The Template can be found by name: "&lt;image_name&gt;-&lt;image_id&gt;"
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("disksave/{int_id}/{int_disk_id}/{string_new_image_name}/{string_new_image_type}/{int_snapshot_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String disksave(@PathParam("int_id")int ID, @PathParam("int_disk_id")int disk_ID, @PathParam("string_new_image_name")String new_image_name, @PathParam("string_new_image_type")String new_image_type, @PathParam("int_snapshot_id")int snapshot_ID) throws XmlRpcException{
        Object[] params = {credential, ID, disk_ID, new_image_name, new_image_type, snapshot_ID};

        Object[] result = (Object[])this.client.execute("one.vm.disksave",params);

    return result[1].toString();
    }
    
    /**
     * Description: Takes a new snapshot of the disk image
     * @param ID The object ID.
     * @param disk_ID Disk ID of the disk we want to save.
     * @param snapshot_descr Description for the snapshot.
     * @return result[0] true or false whenever is successful or not
     * result[1] The new snapshot ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("disksnapshotcreate/{int_id}/{int_disk_id}/{string_snapshot_descr}")
    @Produces(MediaType.TEXT_PLAIN)
    public String disksnapshotcreate(@PathParam("int_id")int ID, @PathParam("int_disk_id")int disk_ID, @PathParam("string_snapshot_descr")String snapshot_descr) throws XmlRpcException{
        Object[] params = {credential, ID, disk_ID, snapshot_descr};

        Object[] result = (Object[])this.client.execute("one.vm.disksnapshotcreate",params);

    return result[1].toString();
    }
    
    /**
     * Description: Deletes a disk snapshot
     * @param ID The object ID.
     * @param disk_ID_to_save Disk ID of the disk we want to save.
     * @param disk_ID_to_delete ID of the snapshot to be deleted.
     * @return result[0] true or false whenever is successful or not
     * result[1] The ID of the snapshot deleted/ The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("disksnapshotdelete/{int_id}/{int_disk_id_to_save}/{int_disk_id_to_delete}")
    @Produces(MediaType.TEXT_PLAIN)
    public String disksnapshotdelete(@PathParam("int_id")int ID, @PathParam("int_disk_id_to_save")int disk_ID_to_save, @PathParam("int_disk_id_to_delete")int disk_ID_to_delete) throws XmlRpcException{
        Object[] params = {credential, ID, disk_ID_to_save, disk_ID_to_delete};

        Object[] result = (Object[])this.client.execute("one.vm.disksnapshotdelete",params);

    return result[1].toString();
    }    
    
    /**
     * Description: Reverts disk state to a previously taken snapshot
     * @param ID The object ID.
     * @param disk_ID_to_revert Disk ID of the disk to revert its state.
     * @param snapshot_ID Snapshot ID to revert the disk state to.
     * @return result[0] true or false whenever is successful or not
     * result[1] The snapshot ID used / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("disksnapshotrevert/{int_id}/{int_disk_id_to_revert}/{int_snapshot_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String disksnapshotrevert(@PathParam("int_id")int ID, @PathParam("int_disk_id_to_revert")int disk_ID_to_revert, @PathParam("int_snapshot_id")int snapshot_ID) throws XmlRpcException{
        Object[] params = {credential, ID, disk_ID_to_revert, snapshot_ID};

        Object[] result = (Object[])this.client.execute("one.vm.disksnapshotrevert",params);

    return result[1].toString();
    }

    /**
     * Description: Attaches a new disk to the virtual machine
     * @param ID The object ID.
     * @param DISK A string containing a single DISK vector attribute. Syntax can be the usual attribute=value or XML.
     * @return result[0] true or false whenever is successful or not
     * result[1] The VM ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("attach/{int_id}/{string_disk}")
    @Produces(MediaType.TEXT_PLAIN)
    public String attach(@PathParam("int_id")int ID, @PathParam("string_disk")String DISK) throws XmlRpcException{
        Object[] params = {credential, ID, DISK};

        Object[] result = (Object[])this.client.execute("one.vm.attach",params);

    return result[1].toString();
    }    
    
    /**
     * Description: Detaches a disk from a virtual machine
     * @param ID The object ID.
     * @param disk_ID The disk ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The VM ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("detach/{int_id}/{int_disk_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String detach(@PathParam("int_id")int ID, @PathParam("int_disk_id")int disk_ID) throws XmlRpcException{
        Object[] params = {credential, ID, disk_ID};

        Object[] result = (Object[])this.client.execute("one.vm.detach",params);

    return result[1].toString();
    }    
    
    /**
     * Description: Attaches a new network interface to the virtual machine
     * @param ID The object ID.
     * @param NIC A string containing a single NIC vector attribute. Syntax can be the usual attribute=value or XML.
     * @return result[0] true or false whenever is successful or not
     * result[1] The VM ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("attachnic/{int_id}/{string_nic}")
    @Produces(MediaType.TEXT_PLAIN)
    public String attachnic(@PathParam("int_id")int ID, @PathParam("string_nic")String NIC) throws XmlRpcException{
        Object[] params = {credential, ID, NIC};

        Object[] result = (Object[])this.client.execute("one.vm.attachnic",params);

    return result[1].toString();
    }

    /**
     * Description: Detaches a network interface from a virtual machine
     * @param ID The object ID.
     * @param nic_ID The nic ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The VM ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("detachnic/{int_id}/{nic_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String detachnic(@PathParam("int_id")int ID, @PathParam("int_nic_id")int nic_ID) throws XmlRpcException{
        Object[] params = {credential, ID, nic_ID};

        Object[] result = (Object[])this.client.execute("one.vm.detachnic",params);

    return result[1].toString();
    }

    /**
     * Description: Changes the permission bits of a virtual machine.
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

        Object[] result = (Object[])this.client.execute("one.vm.chmod",params);

    return result[1].toString();
    }    

    /**
     * Description: Changes the ownership of a virtual machine.
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
        Object[] params = {credential, ID, User_ID, Group_ID};

        Object[] result = (Object[])this.client.execute("one.vm.chown",params);

    return result[1].toString();
    }    
    
    /**
     * Description: Renames a virtual machine
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

        Object[] result = (Object[])this.client.execute("one.vm.rename",params);

    return result[1].toString();
    }   
    
    /**
     * Description: Creates a new virtual machine snapshot
     * @param ID The object ID.
     * @param snapshot_name The new snapshot name. It can be empty.
     * @return result[0] true or false whenever is successful or not
     * result[1] The new snapshot ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("snapshotcreate/{int_id}/{string_snapshot_name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String snapshotcreate(@PathParam("int_id")int ID, @PathParam("string_snapshot_name")String snapshot_name) throws XmlRpcException{
        Object[] params = {credential, ID, snapshot_name};

        Object[] result = (Object[])this.client.execute("one.vm.snapshotcreate",params);

    return result[1].toString();
    }    
    
    /**
     * Description: Reverts a virtual machine to a snapshot
     * @param ID The object ID.
     * @param snapshot_ID The snapshot ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The VM ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("snapshotrevert/{int_id}/{snapshot_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String snapshotrevert(@PathParam("int_id")int ID, @PathParam("int_snapshot_id")int snapshot_ID) throws XmlRpcException{
        Object[] params = {credential, ID, snapshot_ID};

        Object[] result = (Object[])this.client.execute("one.vm.snapshotrevert",params);

    return result[1].toString();
    } 
    
    /**
     * Description: Deletes a virtual machine snapshot
     * @param ID The object ID.
     * @param snapshot_ID The snapshot ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The VM ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("snapshotdelete/{int_id}/{int_snapshot_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String snapshotdelete(@PathParam("int_id")int ID, @PathParam("int_snapshot_id")int snapshot_ID) throws XmlRpcException{
        Object[] params = {credential, ID, snapshot_ID};

        Object[] result = (Object[])this.client.execute("one.vm.snapshotdelete",params);

    return result[1].toString();
    }    
    
    /**
     * Description: Changes the capacity of the virtual machine
     * @param ID The object ID.
     * @param new_template Template containing the new capacity elements CPU, VCPU, MEMORY. If one of them is not present, or its value is 0, it will not be resized.
     * @param force true to enforce the Host capacity is not overcommitted. This parameter is only acknoledged for users in the oneadmin group, Host capacity will be always enforced for regular users.
     * @return result[0] true or false whenever is successful or not
     * result[1] The VM ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("resize/{int_id}/{string_new_template}/{bool_force}")
    @Produces(MediaType.TEXT_PLAIN)
    public String resize(@PathParam("int_id")int ID, @PathParam("string_new_template")String new_template, @PathParam("bool_force")boolean force) throws XmlRpcException{
        Object[] params = {credential, ID, new_template, force};

        Object[] result = (Object[])this.client.execute("one.vm.resize",params);

    return result[1].toString();
    }    
    
    /**
     * Description: Replaces the user template contents.
     * @param ID The object ID.
     * @param template The new user template contents. Syntax can be the usual attribute=value or XML.
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

        Object[] result = (Object[])this.client.execute("one.vm.update",params);

    return result[1].toString();
    }    
 
    /**
     * Description: Recovers a stuck VM that is waiting for a driver operation. The recovery may be done by failing or succeeding the pending operation. You need to manually check the vm status on the host, to decide if the operation was successful or not.
     * @param ID The object ID.
     * @param operation Recover operation: success (1), failure (0) or retry (2)
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("recover/{int_id}/{int_operation}")
    @Produces(MediaType.TEXT_PLAIN)
    public String recover(@PathParam("int_id")int ID, @PathParam("int_operation")int operation) throws XmlRpcException{
        Object[] params = {credential, ID, operation};

        Object[] result = (Object[])this.client.execute("one.vm.recover",params);

    return result[1].toString();
    }    
    
    /**
     * Description: Retrieves information for the virtual machine.
     * @param ID The object ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The information string / The error string.
     * result[2] Error code.     
     * @throws XmlRpcException
     */
    @GET
    @Path("int_id")
    @Produces(MediaType.TEXT_PLAIN)
    public String info(@PathParam("int_id")int ID) throws XmlRpcException{
        Object[] params = {credential, ID};

        Object[] result = (Object[])this.client.execute("one.vm.info",params);

    return result[1].toString();
    }    
    
    /**
     * Description: Returns the virtual machine monitoring records.
     * @param ID The object ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The monitoring information string / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("monitoring/{int_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String monitoring(@PathParam("int_id")int ID) throws XmlRpcException{
        Object[] params = {credential, ID};

        Object[] result = (Object[])this.client.execute("one.vm.monitoring",params);

    return result[1].toString();
    }    
}