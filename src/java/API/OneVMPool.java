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
@Path("onevmpool")
public class OneVMPool {

    @Context
    private UriInfo context;

    private XmlRpcClient client = new XmlRpcClient();
    private XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    public String credential="oneadmin:depjouphCaj2";
    public String endpoint="http://localhost:2633/RPC2";
    /**
     * Creates a new instance of OneVMPool
     */
    public OneVMPool() {
       try{
           this.config.setServerURL(new URL(endpoint));
           this.client.setConfig(this.config);
       }
       catch(MalformedURLException e){
            System.err.println(e);
       }

    }

    /**
     * Description: Retrieves information for all or part of the VMs in the pool.
     * @param filter_flag Filter flag &lt; = -3: Connected user’s resources -2: All resources -1: Connected user’s and his group’s resources &gt; = 0: UID User’s Resources
     * @param range_start_ID When the next parameter is &gt;= -1 this is the Range start ID. Can be -1. For smaller values this is the offset used for pagination.
     * @param range_end_ID For values &gt;= -1 this is the Range end ID. Can be -1 to get until the last ID. For values &lt; -1 this is the page size used for pagination. The range can be used to retrieve a subset of the pool, from the ‘start’ to the ‘end’ ID. To retrieve the complete pool, use (-1, -1); to retrieve all the pool from a specific ID to the last one, use (&lt;id&gt;, -1), and to retrieve the first elements up to an ID, use (0, &lt;id&gt;).
     * @param state VM state to filter by.
     * 
The state filter can be one of the following:

Value	State
-2	Any state, including DONE
-1	Any state, except DONE
0	INIT
1	PENDING
2	HOLD
3	ACTIVE
4	STOPPED
5	SUSPENDED
6	DONE
8	POWEROFF
9	UNDEPLOYED
Warning
Value 7 is reserved for FAILED VMs for compatibility reasons.
     * 
     * @return result[0] true or false whenever is successful or not
     * result[1] The information string / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("info/{int_filter_flag}/{int_range_start_id}/{int_range_end_id}/{int_state}")
    @Produces(MediaType.TEXT_PLAIN)
    public String info(@PathParam("int_filter_flag")int filter_flag, @PathParam("int_range_start_id")int range_start_ID, @PathParam("int_range_end_id")int range_end_ID, @PathParam("int_state")int state) throws XmlRpcException{
        Object[] params = {credential, filter_flag, range_start_ID, range_end_ID, state};

        Object[] result = (Object[])this.client.execute("one.vmpool.info",params);

    return result[1].toString();
    }    

    /**
     * Description: Returns all the virtual machine monitoring records.
     * @param filter_flag Filter flag - &lt; = -3: Connected user’s resources - -2: All resources - -1: Connected user’s and his group’s resources - &gt; = 0: UID User’s Resources
     * @return result[0] true or false whenever is successful or not
     * result[1] The information string / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("monitoring/{int_filter_flag}")
    @Produces(MediaType.TEXT_PLAIN)
    public String monitoring(@PathParam("int_filter_flag")int filter_flag) throws XmlRpcException{
        Object[] params = {credential, filter_flag};

        Object[] result = (Object[])this.client.execute("one.vmpool.monitoring",params);

    return result[1].toString();
    }

    /**
     * Description: Returns the virtual machine history records.
     * @param filter_flag Filter flag - &lt; = -3: Connected user’s resources - -2: All resources - -1: Connected user’s and his group’s resources - &gt; = 0: UID User’s Resources
     * @param start_time Start time for the time interval. Can be -1, in which case the time interval won’t have a left boundary.
     * @param end_time End time for the time interval. Can be -1, in which case the time interval won’t have a right boundary.
     * @return result[0] true or false whenever is successful or not
     * result[1] The information string / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("accounting/{int_filter_flag}/{int_start_time}/{int_end_time}")
    @Produces(MediaType.TEXT_PLAIN)
    public String accounting(@PathParam("int_filter_flag")int filter_flag, @PathParam("int_start_time")int start_time, @PathParam("int_end_time")int end_time) throws XmlRpcException{
        Object[] params = {credential, filter_flag, start_time, end_time};

        Object[] result = (Object[])this.client.execute("one.vmpool.accounting",params);

    return result[1].toString();
    }
    
    /**
     * Description: Returns the virtual machine showback records
     * @param first_month First month for the time interval. January is 1. Can be -1, in which case the time interval won’t have a left boundary.
     * @param first_year First year for the time interval. Can be -1, in which case the time interval won’t have a left boundary.
     * @param last_month Last month for the time interval. January is 1. Can be -1, in which case the time interval won’t have a right boundary.
     * @param last_year Last year for the time interval. Can be -1, in which case the time interval won’t have a right boundary.
     * @return result[0] true or false whenever is successful or not
     * result[1] The information string / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("showback/{int_first_month}/{int_first_year}/{int_last_month}/{int_last_year}")
    @Produces(MediaType.TEXT_PLAIN)
    public String showback(@PathParam("int_first_month")int first_month, @PathParam("int_first_year")int first_year, @PathParam("int_last_month")int last_month, @PathParam("int_last_year")int last_year) throws XmlRpcException{
        Object[] params = {credential, first_month, first_year, last_month, last_year};

        Object[] result = (Object[])this.client.execute("one.vmpool.showback",params);

    return result[1].toString();
    }

    /**
     * Description: Processes all the history records, and stores the monthly cost for each VM
     * @param first_month First month for the time interval. January is 1. Can be -1, in which case the time interval won’t have a left boundary.
     * @param first_year First year for the time interval. Can be -1, in which case the time interval won’t have a left boundary.
     * @param last_month Last month for the time interval. January is 1. Can be -1, in which case the time interval won’t have a right boundary.
     * @param last_year Last year for the time interval. Can be -1, in which case the time interval won’t have a right boundary.
     * @return result[0] true or false whenever is successful or not
     * result[1] Empty / The error string.
     * result[2] Error code.
     * @throws XmlRpcException
     */
    @GET
    @Path("calculateshowback/{int_first_month}/{int_first_year}/{int_last_month}/{int_last_year}")
    @Produces(MediaType.TEXT_PLAIN)
    public String calculateshowback(@PathParam("int_first_month")int first_month, @PathParam("int_first_year")int first_year, @PathParam("int_last_month")int last_month, @PathParam("int_last_year")int last_year) throws XmlRpcException{
        Object[] params = {credential, first_month, first_year, last_month, last_year};

        Object[] result = (Object[])this.client.execute("one.vmpool.calculateshowback",params);

    return result[1].toString();
    }
}
