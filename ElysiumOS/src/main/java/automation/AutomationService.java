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

    /**
     * Creates an automation task with the given type and parameters.
     * @param taskType the type of the task to be scheduled
     * @param parameters the parameters required for the task
     * @return the UUID of the newly created automation
     * @throws IllegalArgumentException if taskType or parameters are invalid
     */
    public UUID createAutomation(String taskType, String parameters) throws IllegalArgumentException {
        if (taskType == null || taskType.trim().isEmpty()) {
            throw new IllegalArgumentException("Task type cannot be null or empty.");
        }
        if (parameters == null) {
            throw new IllegalArgumentException("Parameters cannot be null.");
        }
        TaskScheduler scheduler = new TaskScheduler(config);
        UUID automationId = UUID.randomUUID();
        taskSchedulers.put(automationId, scheduler);
        scheduler.scheduleTask(taskType, parameters);
        return automationId;
    }

    /**
     * Cancels the automation task associated with the given UUID.
     * @param automationId the UUID of the automation to cancel
     * @return true if the automation was cancelled successfully, false if the automation ID was not found
     * @throws IllegalArgumentException if the automation ID is null
     */
    public boolean cancelAutomation(UUID automationId) throws IllegalArgumentException {
        if (automationId == null) {
            throw new IllegalArgumentException("Automation ID cannot be null.");
        }
        TaskScheduler scheduler = taskSchedulers.get(automationId);
        if (scheduler != null) {
            scheduler.cancelTask();
            taskSchedulers.remove(automationId);
            return true;
        }
        return false;
    }

    /**
     * Retrieves the status of the automation task associated with the given UUID.
     * @param automationId the UUID of the automation to check
     * @return the status of the automation or a message indicating the automation ID was not found
     * @throws IllegalArgumentException if the automation ID is null
     */
    public String getAutomationStatus(UUID automationId) throws IllegalArgumentException {
        if (automationId == null) {
            throw new IllegalArgumentException("Automation ID cannot be null.");
        }
        TaskScheduler scheduler = taskSchedulers.get(automationId);
        if (scheduler != null) {
            return scheduler.getTaskStatus();
        }
        return "Automation ID not found";
    }

    // Additional methods to manage and monitor automations can be added here
}