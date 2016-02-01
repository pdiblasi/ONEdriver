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
@Path("onehost")
public class OneHost {
    @Context
    private UriInfo context;

    private XmlRpcClient client = new XmlRpcClient();
    private XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    public String credential="oneadmin:depjouphCaj2";
    public String endpoint="http://localhost:2633/RPC2";
    
    /**
     * Creates a new instance of OneHost
     */
    public OneHost(){
       try{
           this.config.setServerURL(new URL(endpoint));
           this.client.setConfig(this.config);
       }
       catch(MalformedURLException e){
            System.err.println(e);
       }

    }

    
    
    /**
     * Description: Allocates a new host in OpenNebula
     * @param hostname      String	Hostname of the machine we want to add
     * @param im_mad_name   String	The name of the information manager (im_mad_name), this values are taken from the oned.conf with the tag name IM_MAD (name)
     * @param vmm_mad_name  String	The name of the virtual machine manager mad name (vmm_mad_name), this values are taken from the oned.conf with the tag name VM_MAD (name)
     * @param vnm_mad_name  String	The name of the virtual network manager mad name (vnm_mad_name), see the Networking Subsystem documentation
     * @param cluster_ID    Int	The cluster ID. If it is -1, this host won’t be added to any cluster.
     * @return result[0] true or false whenever is successful or not
     * result[1] The allocated Host ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("allocate/{string_hostname}/{string_im_mad_name}/{string_vmm_mad_name}/{string_vnm_mad_name}/{int_cluser_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String allocate(@PathParam("string_hostname")String hostname, @PathParam("string_ima_mad_name")String im_mad_name, @PathParam("string_vmm_mad_name")String vmm_mad_name, @PathParam("string_vnma_mad_name")String vnm_mad_name, @PathParam("int_cluster_id")int cluster_ID) throws XmlRpcException{
        Object[] params = {credential, hostname, im_mad_name, vmm_mad_name, vnm_mad_name, cluster_ID};

        Object[] result = (Object[])this.client.execute("one.host.allocate",params);

        return result[1].toString();
    }
    
    /**
     * Description: Deletes the given host from the pool
     * @param host_id The object ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("delete/{int_host_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("int_host_id")int host_id) throws XmlRpcException{
        Object[] params = {credential, host_id};

        Object[] result = (Object[])this.client.execute("one.host.delete",params);

        return result[1].toString();
    }    
    
    /**
     * Description: Enables or disables the given host
     * @param id The Host ID.
     * @param enable Set it to true/false to enable or disable the target Host.
     * @return result[0] true or false whenever is successful or not
     * result[1] The resource ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("enable/{int_id}/{bool_enable}")
    @Produces(MediaType.TEXT_PLAIN)
    public String enable(@PathParam("int_id")int id, @PathParam("bool_enable")boolean enable) throws XmlRpcException{
        Object[] params = {credential, id, enable};

        Object[] result = (Object[])this.client.execute("one.host.enable",params);

        return result[1].toString();
    }

    /**
     * Description: Replaces the host’s template contents.
     * @param id The object ID.
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
    public String update(@PathParam("int_id")int id, @PathParam("string_template")String template, @PathParam("int_type")int type) throws XmlRpcException{
        Object[] params = {credential, id, template, type};

        Object[] result = (Object[])this.client.execute("one.host.update",params);

        return result[1].toString();
    }

    /**
     * Description: Renames a host.
     * @param id The object ID.
     * @param new_name The new name.
     * @return result[0] true or false whenever is successful or not
     * result[1] The VM ID / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("rename/{int_id}/{strng_new_name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String rename(@PathParam("int_id")int id, @PathParam("string_new_name")String new_name) throws XmlRpcException{
        Object[] params = {credential, id, new_name};

        Object[] result = (Object[])this.client.execute("one.host.rename",params);

        return result[1].toString();
    }

    /**
     * Description: Retrieves information for the host.
     * @param id    Int	The object ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The information string / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("info/{id}")
    @Produces(MediaType.APPLICATION_JSON)     
    public String info(@PathParam("id") int id) throws XmlRpcException{
        
        Object[] params = {credential, id};
        Object[] result = (Object[])this.client.execute("one.host.info",params);

        return result[1].toString();
    }
    
    /**
     * Description: Returns the host monitoring records.
     * @param id    Int	The object ID.
     * @return result[0] true or false whenever is successful or not
     * result[1] The monitoring information string / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("monitoring/{int_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String monitoring(@PathParam("int_id")int id) throws XmlRpcException{
        Object[] params = {credential, id};

        Object[] result = (Object[])this.client.execute("one.host.monitoring",params);

        return result[1].toString();
    }       

}
