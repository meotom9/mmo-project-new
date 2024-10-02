package mmo.project;

import mmo.project.model.MmoConfig;
import mmo.project.model.MmoUserAgent;
import mmo.project.task.TaskMMO;
import mmo.project.task.TaskSyncDB;

import java.util.Vector;
import java.util.concurrent.*;

/**
 * Hello world!
 */
public class App {
    public static Vector<MmoConfig> l_configs;
    public static Vector<MmoUserAgent> l_users;

    public App() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // Wait for the task to complete or timeout
        try {
            Future<?> f_config = executor.submit(new TaskSyncDB());
            Future<?> f_task = executor.submit(new TaskMMO());

            System.out.println("Task completed successfully.");
        } catch (Exception e) {
            System.out.println("Task encountered an error: " + e.getCause());
        } finally {
            executor.shutdown(); // Shut down the executor when finished
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        new App();
    }
}
