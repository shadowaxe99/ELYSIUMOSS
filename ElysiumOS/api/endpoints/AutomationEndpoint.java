```java
package api.endpoints;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import automation.AutomationService;
import services.AuthenticationService;

@Path("/automation")
public class AutomationEndpoint {

    private AutomationService automationService = new AutomationService();
    private AuthenticationService authenticationService = new AuthenticationService();

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAutomation(String automationConfig, @HeaderParam("Authorization") String authToken) {
        if (!authenticationService.isAuthenticated(authToken)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not authenticated").build();
        }
        
        try {
            String automationId = automationService.createAutomation(automationConfig);
            return Response.ok().entity("{\"automationId\": \"" + automationId + "\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/status/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAutomationStatus(@PathParam("id") String automationId, @HeaderParam("Authorization") String authToken) {
        if (!authenticationService.isAuthenticated(authToken)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not authenticated").build();
        }
        
        try {
            String status = automationService.getAutomationStatus(automationId);
            return Response.ok().entity("{\"status\": \"" + status + "\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Automation not found").build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteAutomation(@PathParam("id") String automationId, @HeaderParam("Authorization") String authToken) {
        if (!authenticationService.isAuthenticated(authToken)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not authenticated").build();
        }
        
        try {
            automationService.deleteAutomation(automationId);
            return Response.ok().entity("Automation deleted successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Automation not found").build();
        }
    }
}
```