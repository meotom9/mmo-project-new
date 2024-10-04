package mmo.project.task;

import mmo.project.dao.MmoConfigDAO;
import mmo.project.dao.MmoUserAgentDAO;
import mmo.project.function.UtilsFunction;
import mmo.project.model.MmoConfig;
import mmo.project.model.MmoUserAgent;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class TaskSyncDB implements Runnable {
    private static ArrayList<MmoConfig> l_configs;
    private static ArrayList<MmoUserAgent> l_users_mobile;
    private static ArrayList<MmoUserAgent> l_users_pc;

    public static int num_flag = 0;

    public static ArrayList<MmoConfig> getConfigs() {
        return l_configs;
    }

    public void setConfigs(ArrayList<MmoConfig> l_configs) {
        this.l_configs = l_configs;
    }

    public static ArrayList<MmoUserAgent> getUsersMobile() {
        return l_users_mobile;
    }

    public static MmoUserAgent getRandomUsersMobile() {
        return l_users_mobile.get(UtilsFunction.randomRange(0, l_users_mobile.size() - 1));
    }

    public void setUsersMobile(ArrayList<MmoUserAgent> l_users_mobile) {
        this.l_users_mobile = l_users_mobile;
    }

    public static ArrayList<MmoUserAgent> getUsersPC() {
        return l_users_pc;
    }

    public static MmoUserAgent getRandomUsersPC() {
        return l_users_pc.get(UtilsFunction.randomRange(0, l_users_pc.size() - 1));
    }

    public void setUserPC(ArrayList<MmoUserAgent> l_users_pc) {
        this.l_users_pc = l_users_pc;
    }

    @Override
    public void run() {
        try {
            System.out.println("TaskSyncDB started: " + Thread.currentThread().getName());
            ArrayList<MmoConfig> v_configs = new MmoConfigDAO().getAllConfigs();
            ArrayList<MmoConfig> v_configs_2 = new ArrayList<MmoConfig>();
            for(MmoConfig c : v_configs) {
                System.out.println("Config: " + c.toString());
                for(int i=0; i<c.getCountT(); i++){
                    v_configs_2.add(c);
                }
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


            setConfigs(v_configs_2);
            setUsersMobile(v_users_mb);
            setUserPC(v_users_pc);
            System.out.println("TaskSyncDB finished: " + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MmoConfig getCurrentConfig(){
        if(num_flag >= l_configs.size()){
            num_flag = 0;
        }
        MmoConfig mc = l_configs.get(num_flag);
        num_flag++;
        return mc;
    }
}
