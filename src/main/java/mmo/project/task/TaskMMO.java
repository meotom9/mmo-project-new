package mmo.project.task;

public class TaskMMO implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Task started: " + Thread.currentThread().getName());
            Thread.sleep(2000); // Simulate a long-running task
            System.out.println("Task finished: " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
