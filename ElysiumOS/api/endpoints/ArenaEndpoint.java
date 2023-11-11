package api.endpoints;

import arena.GameManager;
import arena.ScenarioGenerator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/arena")
public class ArenaEndpoint {

    private GameManager gameManager = new GameManager();
    private ScenarioGenerator scenarioGenerator = new ScenarioGenerator();

    @POST
    @Path("/start-game")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response startGame(String gameConfig) {
        try {
            boolean success = gameManager.startGame(gameConfig);
            if (success) {
                return Response.ok().entity("{\"message\":\"Game started successfully.\"}").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("{\"message\":\"Failed to start game.\"}").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error starting game: " + e.getMessage() + "\"}").build();
        }
    }

    @GET
    @Path("/generate-scenario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateScenario(@QueryParam("theme") String theme) {
        try {
            String scenario = scenarioGenerator.generateScenario(theme);
            return Response.ok().entity("{\"scenario\":\"" + scenario + "\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\":\"Error generating scenario: " + e.getMessage() + "\"}").build();
        }
    }

    // Additional endpoints can be added here for managing the Arena's features
}
