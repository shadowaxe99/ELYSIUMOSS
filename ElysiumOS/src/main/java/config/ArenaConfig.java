```java
package config;

public class ArenaConfig {
    // Default configuration values for The Arena
    private static final int DEFAULT_MAX_PLAYERS = 100;
    private static final int DEFAULT_MAX_AI_AGENTS = 1000;
    private static final String DEFAULT_ARENA_NAME = "Elysium Coliseum";
    private static final boolean DEFAULT_REAL_WORLD_INTEGRATION = true;

    // Configurable properties
    private int maxPlayers;
    private int maxAIAgents;
    private String arenaName;
    private boolean realWorldIntegration;

    public ArenaConfig() {
        // Initialize with default values
        this.maxPlayers = DEFAULT_MAX_PLAYERS;
        this.maxAIAgents = DEFAULT_MAX_AI_AGENTS;
        this.arenaName = DEFAULT_ARENA_NAME;
        this.realWorldIntegration = DEFAULT_REAL_WORLD_INTEGRATION;
    }

    // Getters and setters for configurable properties
    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMaxAIAgents() {
        return maxAIAgents;
    }

    public void setMaxAIAgents(int maxAIAgents) {
        this.maxAIAgents = maxAIAgents;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public boolean isRealWorldIntegration() {
        return realWorldIntegration;
    }

    public void setRealWorldIntegration(boolean realWorldIntegration) {
        this.realWorldIntegration = realWorldIntegration;
    }
}
```