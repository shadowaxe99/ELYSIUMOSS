```java
package automation;

import config.AutomationStationConfig;
import java.util.HashMap;
import java.util.UUID;

public class AutomationService {

    private final HashMap<UUID, TaskScheduler> taskSchedulers;
    private final AutomationStationConfig config;

    public AutomationService(AutomationStationConfig config) {
        this.config = config;
        this.taskSchedulers = new HashMap<>();
    }

    public UUID createAutomation(String taskType, String parameters) {
        TaskScheduler scheduler = new TaskScheduler(config);
        UUID automationId = UUID.randomUUID();
        taskSchedulers.put(automationId, scheduler);
        scheduler.scheduleTask(taskType, parameters);
        return automationId;
    }

    public boolean cancelAutomation(UUID automationId) {
        TaskScheduler scheduler = taskSchedulers.get(automationId);
        if (scheduler != null) {
            scheduler.cancelTask();
            taskSchedulers.remove(automationId);
            return true;
        }
        return false;
    }

    public String getAutomationStatus(UUID automationId) {
        TaskScheduler scheduler = taskSchedulers.get(automationId);
        if (scheduler != null) {
            return scheduler.getTaskStatus();
        }
        return "Automation ID not found";
    }

    // Additional methods to manage and monitor automations can be added here
}
```