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
@Path("onecluster")
public class OneCluster {

    @Context
    private UriInfo context;

    private XmlRpcClient client = new XmlRpcClient();
    private XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    public String credential="oneadmin:depjouphCaj2";
    public String endpoint="http://localhost:2633/RPC2";
    /**
     * Creates a new instance of OneCluster
     */
    public OneCluster() {
       try{
           this.config.setServerURL(new URL(endpoint));
           this.client.setConfig(this.config);
       }
       catch(MalformedURLException e){
            System.err.println(e);
       }

    }

    /**
     * Description: Allocates a new cluster in OpenNebula.
     * @param name Name for the new cluster.
     * @return result[0] true or false whenever is successful or not
     * result[1] The allocated cluster ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("allocate/{string_name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String allocate(@PathParam("string_name")String name) throws XmlRpcException{
        Object[] params = {credential, name};

        Object[] result = (Object[])this.client.execute("one.cluster.allocate",params);

        return result[1].toString();
    }

    /**
     * Description: Deletes the given cluster from the pool.
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

        Object[] result = (Object[])this.client.execute("one.cluster.delete",params);

        return result[1].toString();
    }

    /**
     * Description: Replaces the cluster template contents.
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

        Object[] result = (Object[])this.client.execute("one.cluster.update",params);

        return result[1].toString();
    }

    /**
     * Description: Adds a host to the given cluster.
     * @param ID The cluster ID.
     * @param host_ID The host ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("addhost/{int_id}/{int_host_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addhost(@PathParam("int_id")int ID, @PathParam("int_host_id")int host_ID) throws XmlRpcException{
        Object[] params = {credential, ID, host_ID};

        Object[] result = (Object[])this.client.execute("one.cluster.addhost",params);

        return result[1].toString();
    }

    /**
     * Description: Removes a host from the given cluster.
     * @param ID The cluster ID.
     * @param host_ID The host ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("delhost/{int_id}/{int_host_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delhost(@PathParam("int_id")int ID, @PathParam("int_host_id")int host_ID) throws XmlRpcException{
        Object[] params = {credential, ID, host_ID};

        Object[] result = (Object[])this.client.execute("one.cluster.delhost",params);

        return result[1].toString();
    }

    /**
     * Description: Adds a datastore to the given cluster.
     * @param ID The cluster ID.
     * @param datastore_ID The datastore ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("adddatastore/{int_id}/{int_datastore_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String adddatastore(@PathParam("int_id")int ID, @PathParam("int_datastore_id")int datastore_ID) throws XmlRpcException{
        Object[] params = {credential, ID, datastore_ID};

        Object[] result = (Object[])this.client.execute("one.cluster.adddatastore",params);

        return result[1].toString();
    }

    /**
     * Description: Removes a datastore from the given cluster.
     * @param ID The cluster ID.
     * @param datastore_ID The datastore ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("deldatastore/{int_id}/{int_datastore_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deldatastore(@PathParam("int_id")int ID, @PathParam("int_datastore_id")int datastore_ID) throws XmlRpcException{
        Object[] params = {credential, ID, datastore_ID};

        Object[] result = (Object[])this.client.execute("one.cluster.deldatastore",params);

        return result[1].toString();
    }

    /**
     * Description: Adds a vnet to the given cluster.
     * @param ID The cluster ID.
     * @param vnet_ID The vnet ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("addvnet/{int_id}/{int_vnet_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String addvnet(@PathParam("int_id")int ID, @PathParam("int_vent_id")int vnet_ID) throws XmlRpcException{
        Object[] params = {credential, ID, vnet_ID};

        Object[] result = (Object[])this.client.execute("one.cluster.addvnet",params);

        return result[1].toString();
    }

    /**
     * Description: Removes a vnet from the given cluster.
     * @param ID The cluster ID.
     * @param vnet_ID The vnet ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("delvnet/{int_id}/{int_vnet_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delvnet(@PathParam("int_id")int ID, @PathParam("int_vent_id")int vnet_ID) throws XmlRpcException{
        Object[] params = {credential, ID, vnet_ID};

        Object[] result = (Object[])this.client.execute("one.cluster.delvnet",params);

        return result[1].toString();
    }

    /**
     * Description: Renames a cluster.
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

        Object[] result = (Object[])this.client.execute("one.cluster.rename",params);

        return result[1].toString();
    }

    /**
     * Description: Retrieves information for the cluster.
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

        Object[] result = (Object[])this.client.execute("one.cluster.info",params);

        return result[1].toString();
    }
}
