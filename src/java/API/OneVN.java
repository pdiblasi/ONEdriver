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
@Path("onevn")
public class OneVN {

    @Context
    private UriInfo context;

    private XmlRpcClient client = new XmlRpcClient();
    private XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    public String credential="oneadmin:depjouphCaj2";
    public String endpoint="http://localhost:2633/RPC2";
    /**
     * Creates a new instance of OneVN
     */
    public OneVN() {
       try{
           this.config.setServerURL(new URL(endpoint));
           this.client.setConfig(this.config);
       }
       catch(MalformedURLException e){
            System.err.println(e);
       }

    }

   /**
     * Description: Allocates a new virtual network in OpenNebula.
     * @param template A string containing the template of the virtual network. Syntax can be the usual attribute=value or XML.
     * @param cluster_ID The cluster ID. If it is -1, this virtual network won’t be added to any cluster.
     * @return result[0] true or false whenever is successful or not
     * result[1] The allocated resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("allocate/{string_template}/{int_cluster_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String allocate(@PathParam("string_template")String template, @PathParam("int_cluster_id")int cluster_ID) throws XmlRpcException{
        Object[] params = {credential, template, cluster_ID};

        Object[] result = (Object[])this.client.execute("one.vn.allocate",params);

        return result[1].toString();
    }

    /**
     * Description: Deletes the given virtual network from the pool.
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

        Object[] result = (Object[])this.client.execute("one.vn.delete",params);

        return result[1].toString();
    }

    /**
     * Description: Adds address ranges to a virtual network.
     * @param ID The object ID.
     * @param template 	template of the address ranges to add. Syntax can be the usual attribute=value or XML, see below.
     * 
     * Examples of valid templates:
     * AR = [TYPE = IP4, IP = 192.168.0.5, SIZE = 10 ]
     * or

&lt;TEMPLATE&gt;
  &lt;AR&gt;
    &lt;TYPE&gt;IP4&lt;/TYPE&gt;
    &lt;IP&gt;192.168.0.5&lt;/IP&gt;
    &lt;SIZE&gt;10&lt;/SIZE&gt;
  &lt;/AR&gt;
&lt;/TEMPLATE&gt;

     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("add_ar/{int_id}/{string_template}")
    @Produces(MediaType.TEXT_PLAIN)
    public String add_ar(@PathParam("int_id")int ID, @PathParam("string_template")String template) throws XmlRpcException{
        Object[] params = {credential, ID, template};

        Object[] result = (Object[])this.client.execute("one.vn.add_ar",params);

        return result[1].toString();
    }

    /**
     * Description: Removes an address range from a virtual network.
     * @param ID The object ID.
     * @param ar_ID ID of the address range to remove.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("rm_ar/{int_id}/{int_ar_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String rm_ar(@PathParam("int_id")int ID, @PathParam("int_ar_id")int ar_ID) throws XmlRpcException{
        Object[] params = {credential, ID, ar_ID};

        Object[] result = (Object[])this.client.execute("one.vn.rm_ar",params);

        return result[1].toString();
    }

    /**
     * Description: Updates the attributes of an address range.
     * @param ID The object ID.
     * @param template template of the address ranges to update. Syntax can be the usual attribute=value or XML, see below.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("update_ar/{int_id}/{string_template}")
    @Produces(MediaType.TEXT_PLAIN)
    public String update_ar(@PathParam("int_id")int ID, @PathParam("string_template")String template) throws XmlRpcException{
        Object[] params = {credential, ID, template};

        Object[] result = (Object[])this.client.execute("one.vn.update_ar",params);

        return result[1].toString();
    }

    /**
     * Description: Reserve network addresses.
     * @param ID The virtual network to reserve from.
     * @param template Template, see below.
     * 
     * The third parameter must be an OpenNebula ATTRIBUTE=VALUE template, with these values:

SIZE	Size of the reservation;	
NAME	If set, the reservation will be created in a new Virtual Network with this name;	
AR_ID	ID of the AR from where to take the addresses;	
NETWORK_ID	Instead of creating a new Virtual Network, the reservation will be added to the existing virtual network with this ID;	
MAC	First MAC address to start the reservation range [MAC, MAC+SIZE];	
IP	First IPv4 address to start the reservation range [IP, IP+SIZE];	
     * 
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("reserve/{int_id}/{string_template}")
    @Produces(MediaType.TEXT_PLAIN)
    public String reserve(@PathParam("int_id")int ID, @PathParam("string_template")String template) throws XmlRpcException{
        Object[] params = {credential, ID, template};

        Object[] result = (Object[])this.client.execute("one.vn.reserve",params);

        return result[1].toString();
    }

    /**
     * Description: Frees a reserved address range from a virtual network.
     * @param ID The object ID.
     * @param ar_ID ID of the address range to free.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("free_ar/{int_id}/{int_ar_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String free_ar(@PathParam("int_id")int ID, @PathParam("int_ar_id")int ar_ID) throws XmlRpcException{
        Object[] params = {credential, ID, ar_ID};

        Object[] result = (Object[])this.client.execute("one.vn.free_ar",params);

        return result[1].toString();
    }

    /**
     * Description: Holds a virtual network Lease as used.
     * @param ID The object ID.
     * @param template template of the lease to hold, e.g. LEASES=[IP=192.168.0.5].
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("hold/{int_id}/{string_template}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hold(@PathParam("int_id")int ID, @PathParam("string_template")String template) throws XmlRpcException{
        Object[] params = {credential, ID, template};

        Object[] result = (Object[])this.client.execute("one.vn.hold",params);

        return result[1].toString();
    }

    /**
     * Description: Releases a virtual network Lease on hold.
     * @param ID The object ID.
     * @param template template of the lease to release, e.g. LEASES=[IP=192.168.0.5].
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("release/{int_id}/{string_template}")
    @Produces(MediaType.TEXT_PLAIN)
    public String release(@PathParam("int_id")int ID, @PathParam("string_template")String template) throws XmlRpcException{
        Object[] params = {credential, ID, template};

        Object[] result = (Object[])this.client.execute("one.vn.release",params);

        return result[1].toString();
    }

    /**
     * Description: Replaces the virtual network template contents.
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

        Object[] result = (Object[])this.client.execute("one.vn.update",params);

        return result[1].toString();
    }

    /**
     * Description: Changes the permission bits of a virtual network.
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

        Object[] result = (Object[])this.client.execute("one.vn.chmod",params);

                return result[1].toString();
    }

    /**
     * Description: Changes the ownership of a virtual network.
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

        Object[] result = (Object[])this.client.execute("one.vn.chown",params);

                return result[1].toString();
    }

    /**
     * Description: Renames a virtual network.
     * @param ID The object ID.
     * @param name The new name.
     * @return result[0] true or false whenever is successful or not
     * result[1] The VM ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("rename/{int_id}/{string_name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String rename(@PathParam("int_id")int ID, @PathParam("string_name")String name) throws XmlRpcException{
        Object[] params = {credential, ID, name};

        Object[] result = (Object[])this.client.execute("one.vn.rename",params);

        return result[1].toString();
    }

    /**
     * Description: Retrieves information for the virtual network.
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

        Object[] result = (Object[])this.client.execute("one.vn.info",params);

        return result[1].toString();
    } 
}
