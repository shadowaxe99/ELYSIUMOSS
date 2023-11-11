```java
package automation;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * TaskScheduler is responsible for scheduling and executing tasks
 * that are created within the Automation Station of Elysium OS.
 * It manages both digital and physical tasks, ensuring they are
 * carried out by the AI agents in The Arena.
 */
public class TaskScheduler {

    private final ScheduledExecutorService scheduler;

    public TaskScheduler() {
        // Initialize the scheduler with a fixed thread pool size.
        // The size can be configured based on system capabilities and expected task load.
        this.scheduler = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    }

    /**
     * Schedules a task to be executed after a given delay.
     *
     * @param task  The task to be executed.
     * @param delay The delay after which the task will be executed.
     * @param unit  The time unit of the delay parameter.
     */
    public void scheduleTask(Runnable task, long delay, TimeUnit unit) {
        scheduler.schedule(task, delay, unit);
    }

    /**
     * Schedules a task to be executed periodically.
     *
     * @param task         The task to be executed.
     * @param initialDelay The initial delay before the task is executed for the first time.
     * @param period       The period between successive executions.
     * @param unit         The time unit of the initialDelay and period parameters.
     */
    public void scheduleTaskAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
        scheduler.scheduleAtFixedRate(task, initialDelay, period, unit);
    }

    /**
     * Schedules a task to be executed repeatedly with a fixed delay between the end of one
     * execution and the start of the next.
     *
     * @param task         The task to be executed.
     * @param initialDelay The initial delay before the task is executed for the first time.
     * @param delay        The delay between the end of one execution and the start of the next.
     * @param unit         The time unit of the initialDelay and delay parameters.
     */
    public void scheduleTaskWithFixedDelay(Runnable task, long initialDelay, long delay, TimeUnit unit) {
        scheduler.scheduleWithFixedDelay(task, initialDelay, delay, unit);
    }

    /**
     * Shuts down the scheduler, attempting to stop all actively executing tasks.
     */
    public void shutdown() {
        scheduler.shutdownNow();
    }
}
```