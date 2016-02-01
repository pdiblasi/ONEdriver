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
@Path("onevdc")
public class OneVDC {

    @Context
    private UriInfo context;

    private XmlRpcClient client = new XmlRpcClient();
    private XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    public String credential="oneadmin:depjouphCaj2";
    public String endpoint="http://localhost:2633/RPC2";
    /**
     * Creates a new instance of OneVDC
     */
    public OneVDC() {
       try{
           this.config.setServerURL(new URL(endpoint));
           this.client.setConfig(this.config);
       }
       catch(MalformedURLException e){
            System.err.println(e);
       }

    }

   /**
     * Description: Allocates a new VDC in OpenNebula.
     * @param template A string containing the template of the VDC. Syntax can be the usual attribute=value or XML.
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

        Object[] result = (Object[])this.client.execute("one.vdc.allocate",params);

    return result[1].toString();
    }

    /**
     * Description: Deletes the given VDC from the pool.
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

        Object[] result = (Object[])this.client.execute("one.vdc.delete",params);

    return result[1].toString();
    }

    /**
     * Description: Replaces the VDC template contents.
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
        Object[] params = {credential,ID, template, type};

        Object[] result = (Object[])this.client.execute("one.vdc.update",params);

    return result[1].toString();
    }

    /**
     * Description: Renames a VDC.
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

        Object[] result = (Object[])this.client.execute("one.vdc.rename",params);

    return result[1].toString();
    }

    /**
     * Description: Retrieves information for the VDC.
     * @param ID The object ID. If it is -1, then the connected user’s VDC info info is returned
     * @return result[0] true or false whenever is successful or not
     * result[1] The information string / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("info/{int_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String info(@PathParam("int_id")int ID) throws XmlRpcException{
        Object[] params = {credential};

        Object[] result = (Object[])this.client.execute("one.vdc.info",params);

    return result[1].toString();
    }

    /**
     * Description: Adds a group to the VDC
     * @param VDC_ID The VDC ID.
     * @param group_ID The group ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("addgroup/{int_vdc_id}/{int_group_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addgroup(@PathParam("int_vdc_id")int VDC_ID, @PathParam("int_grou_id")int group_ID) throws XmlRpcException{
        Object[] params = {credential, VDC_ID, group_ID};

        Object[] result = (Object[])this.client.execute("one.vdc.addgroup",params);

    return result[1].toString();
    }

    /**
     * Description: Deletes a group from the VDC
     * @param VDC_ID The VDC ID.
     * @param group_ID The group ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("delgroup/{int_vdc_id}/{int_group_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delgroup(@PathParam("int_vdc_id")int VDC_ID, @PathParam("int_group_id")int group_ID) throws XmlRpcException{
        Object[] params = {credential, VDC_ID, group_ID};

        Object[] result = (Object[])this.client.execute("one.vdc.delgroup",params);

    return result[1].toString();
    }

    /**
     * Description: Adds a cluster to the VDC
     * @param VDC_ID The VDC ID.
     * @param zone_ID The Zone ID.
     * @param cluster_ID The Cluster ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("addcluster/{int_vdc_id}/{int_zone_id}/{int_custer_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addcluster(@PathParam("int_vdc_id")int VDC_ID, @PathParam("int_zone_id")int zone_ID, @PathParam("int_cluster_id")int cluster_ID) throws XmlRpcException{
        Object[] params = {credential, VDC_ID, zone_ID, cluster_ID};

        Object[] result = (Object[])this.client.execute("one.vdc.addcluster",params);

    return result[1].toString();
    }

    /**
     * Description: Deletes a cluster from the VDC
     * @param VDC_ID The VDC ID.
     * @param zone_ID The Zone ID.
     * @param cluster_ID The Cluster ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("delcluster/{int_vdc_id}/{int_zone_id}/{int_cluster_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delcluster(@PathParam("int_vdc_id")int VDC_ID, @PathParam("int_zone_id")int zone_ID, @PathParam("int_cluster_id")int cluster_ID) throws XmlRpcException{
        Object[] params = {credential, VDC_ID, zone_ID, cluster_ID};

        Object[] result = (Object[])this.client.execute("one.vdc.delcluster",params);

    return result[1].toString();
    }

    /**
     * Description: Adds a host to the VDC
     * @param VDC_ID The VDC ID.
     * @param zone_ID The Zone ID.
     * @param host_ID The Host ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("addhost/{int_vdc_id}/{int_zone_id}/{int_host_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addhost(@PathParam("int_vdc_id")int VDC_ID, @PathParam("int_zone_id")int zone_ID, @PathParam("int_host_id")int host_ID) throws XmlRpcException{
        Object[] params = {credential, VDC_ID, zone_ID, host_ID};

        Object[] result = (Object[])this.client.execute("one.vdc.addhost",params);

    return result[1].toString();
    }

    /**
     * Description: Deletes a host from the VDC
     * @param VDC_ID The VDC ID.
     * @param zone_ID The Zone ID.
     * @param host_ID The Host ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("delhost/{int_vdc_id}/{int_zone_id}/{int_host_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delhost(@PathParam("int_vdc_id")int VDC_ID, @PathParam("int_zone_id")int zone_ID, @PathParam("int_host_id")int host_ID) throws XmlRpcException{
        Object[] params = {credential, VDC_ID, zone_ID, host_ID};

        Object[] result = (Object[])this.client.execute("one.vdc.delhost",params);

    return result[1].toString();
    }

    /**
     * Description: Adds a datastore to the VDC
     * @param VDC_ID The VDC ID.
     * @param zone_ID The Zone ID.
     * @param datastore_ID The Datastore ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("adddatastore/{int_vdc_id}/{int_zone_id}/{int_datastore_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String adddatastore(@PathParam("int_vdc_id")int VDC_ID, @PathParam("int_zone_id")int zone_ID, @PathParam("int_datastore_id")int datastore_ID) throws XmlRpcException{
        Object[] params = {credential, VDC_ID, zone_ID, datastore_ID};

        Object[] result = (Object[])this.client.execute("one.vdc.adddatastore",params);

    return result[1].toString();
    }

    /**
     * Description: Deletes a datastore from the VDC
     * @param VDC_ID The VDC ID.
     * @param zone_ID The Zone ID.
     * @param datastore_ID The Datastore ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.

     * @throws XmlRpcException
     */
    @GET
    @Path("deldatastore/{int_vdc_id}/{int_zone_id}/{int_datastore_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deldatastore(@PathParam("int_vdc_id")int VDC_ID, @PathParam("int_zone_id")int zone_ID, @PathParam("int_datastore_id")int datastore_ID) throws XmlRpcException{
        Object[] params = {credential, VDC_ID, zone_ID, datastore_ID};

        Object[] result = (Object[])this.client.execute("one.vdc.deldatastore",params);

    return result[1].toString();
    }

    /**
     * Description: Adds a vnet to the VDC
     * @param VDC_ID The VDC ID.
     * @param zone_ID The Zone ID.
     * @param vnet_ID The Vnet ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("addvnet/{int_vdc_id}/{int_zone_id}/{int_vnet_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addvnet(@PathParam("int_vdc_id")int VDC_ID, @PathParam("int_zone_id")int zone_ID, @PathParam("int_vnet_id")int vnet_ID) throws XmlRpcException{
        Object[] params = {credential, VDC_ID, zone_ID, vnet_ID};

        Object[] result = (Object[])this.client.execute("one.vdc.addvnet",params);

    return result[1].toString();
    }

    /**
     * Description: Deletes a vnet from the VDC
     * @param VDC_ID The VDC ID.
     * @param zone_ID The Zone ID.
     * @param vnet_ID The Vnet ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("delvnet/{int_vdc_id}/{int_zne_id}/{int_vnet_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delvnet(@PathParam("int_vdc_id")int VDC_ID, @PathParam("int_zone_id")int zone_ID, @PathParam("nt_vnet_id")int vnet_ID) throws XmlRpcException{
        Object[] params = {credential, VDC_ID, zone_ID, vnet_ID};

        Object[] result = (Object[])this.client.execute("one.vdc.delvnet",params);

    return result[1].toString();
    }

}
