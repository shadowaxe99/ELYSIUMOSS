```java
package api.endpoints;

import blockchain.BlockchainService;
import blockchain.SmartContractManager;
import com.google.gson.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/blockchain")
public class BlockchainEndpoint {

    private BlockchainService blockchainService = new BlockchainService();
    private SmartContractManager smartContractManager = new SmartContractManager();

    @POST
    @Path("/deployContract")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deployContract(String contractDetails) {
        try {
            JsonObject contractJson = blockchainService.parseContractDetails(contractDetails);
            String transactionHash = smartContractManager.deployContract(contractJson);
            JsonObject response = new JsonObject();
            response.addProperty("transactionHash", transactionHash);
            return Response.status(Response.Status.OK).entity(response.toString()).build();
        } catch (Exception e) {
            JsonObject error = new JsonObject();
            error.addProperty("error", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error.toString()).build();
        }
    }

    @GET
    @Path("/getContractInfo/{contractAddress}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContractInfo(@PathParam("contractAddress") String contractAddress) {
        try {
            JsonObject contractInfo = smartContractManager.getContractInfo(contractAddress);
            return Response.status(Response.Status.OK).entity(contractInfo.toString()).build();
        } catch (Exception e) {
            JsonObject error = new JsonObject();
            error.addProperty("error", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(error.toString()).build();
        }
    }

    @POST
    @Path("/executeTransaction")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response executeTransaction(String transactionDetails) {
        try {
            JsonObject transactionJson = blockchainService.parseTransactionDetails(transactionDetails);
            String transactionHash = blockchainService.executeTransaction(transactionJson);
            JsonObject response = new JsonObject();
            response.addProperty("transactionHash", transactionHash);
            return Response.status(Response.Status.OK).entity(response.toString()).build();
        } catch (Exception e) {
            JsonObject error = new JsonObject();
            error.addProperty("error", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error.toString()).build();
        }
    }
}
```