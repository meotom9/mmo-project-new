package mmo.project.task;

import mmo.project.function.GoogleFunctionUI;
import mmo.project.function.UtilsFunction;
import mmo.project.model.MmoConfig;

public class TaskMMO implements Runnable {




    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Task started: " + Thread.currentThread().getName());
                MmoConfig mc = TaskSyncDB.getCurrentConfig();
                int p_userAgent = mc.getPerMobile();
                String user_agent = "";
                if(p_userAgent < UtilsFunction.randomRange(1, 100)) {
                    user_agent = TaskSyncDB.getRandomUsersMobile().getUserAgent();
                }else{
                    user_agent = TaskSyncDB.getRandomUsersPC().getUserAgent();
                }
                new GoogleFunctionUI().requestAPI(mc.getTerm(), mc.getSiteUrl());
                System.out.println("Task finished: " + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }


}
