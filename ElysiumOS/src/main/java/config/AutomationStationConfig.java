```java
package config;

public class AutomationStationConfig {
    private boolean enableCustomAutomations;
    private boolean enableRoboticAssistantManagement;
    private boolean enableAdvancedAITools;

    public AutomationStationConfig() {
        // Default configuration values
        this.enableCustomAutomations = true;
        this.enableRoboticAssistantManagement = true;
        this.enableAdvancedAITools = true;
    }

    public boolean isCustomAutomationsEnabled() {
        return enableCustomAutomations;
    }

    public void setCustomAutomationsEnabled(boolean enableCustomAutomations) {
        this.enableCustomAutomations = enableCustomAutomations;
    }

    public boolean isRoboticAssistantManagementEnabled() {
        return enableRoboticAssistantManagement;
    }

    public void setRoboticAssistantManagementEnabled(boolean enableRoboticAssistantManagement) {
        this.enableRoboticAssistantManagement = enableRoboticAssistantManagement;
    }

    public boolean isAdvancedAIToolsEnabled() {
        return enableAdvancedAITools;
    }

    public void setAdvancedAIToolsEnabled(boolean enableAdvancedAITools) {
        this.enableAdvancedAITools = enableAdvancedAITools;
    }
}
```