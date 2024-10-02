package mmo.project.task;

import mmo.project.dao.MmoConfigDAO;
import mmo.project.dao.MmoUserAgentDAO;
import mmo.project.model.MmoConfig;
import mmo.project.model.MmoUserAgent;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class TaskSyncDB implements Runnable {
    private ArrayList<MmoConfig> l_configs;
    private ArrayList<MmoUserAgent> l_users_mobile;
    private ArrayList<MmoUserAgent> l_users_pc;

    public ArrayList<MmoConfig> getL_configs() {
        return l_configs;
    }

    public void setConfigs(ArrayList<MmoConfig> l_configs) {
        this.l_configs = l_configs;
    }

    public ArrayList<MmoUserAgent> getUsersMobile() {
        return l_users_mobile;
    }

    public void setUsersMobile(ArrayList<MmoUserAgent> l_users_mobile) {
        this.l_users_mobile = l_users_mobile;
    }

    public ArrayList<MmoUserAgent> getUsersPC() {
        return l_users_pc;
    }

    public void setUserPC(ArrayList<MmoUserAgent> l_users_pc) {
        this.l_users_pc = l_users_pc;
    }

    @Override
    public void run() {
        try {
            System.out.println("TaskSyncDB started: " + Thread.currentThread().getName());
            ArrayList<MmoConfig> v_configs = new MmoConfigDAO().getAllConfigs();
            for(MmoConfig c : v_configs) {
                System.out.println("Config: " + c.toString());
            }
            ArrayList<MmoUserAgent> v_users_mb = (ArrayList<MmoUserAgent>) new MmoUserAgentDAO().getAllUsers()
                    .stream().filter(userAgent -> "mobile".equals(userAgent.getType()))
                    .collect(Collectors.toList());
            for(MmoUserAgent u : v_users_mb) {
                System.out.println("User Mobile: " + u.toString());
            }
            ArrayList<MmoUserAgent> v_users_pc = (ArrayList<MmoUserAgent>) new MmoUserAgentDAO().getAllUsers()
                    .stream().filter(userAgent -> "pc".equals(userAgent.getType()))
                    .collect(Collectors.toList());
            for(MmoUserAgent u : v_users_pc) {
                System.out.println("User PC: " + u.toString());
            }


            setConfigs(v_configs);
            setUsersMobile(v_users_mb);
            setUserPC(v_users_pc);
            System.out.println("TaskSyncDB finished: " + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
