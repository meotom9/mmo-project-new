package mmo.project.task;

import mmo.project.App;
import mmo.project.dao.MmoConfigDAO;
import mmo.project.dao.MmoUserAgentDAO;
import mmo.project.function.UtilsFunction;
import mmo.project.model.MmoConfig;
import mmo.project.model.MmoUserAgent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class TaskSyncDB implements Runnable {
    private static final Logger logger = LogManager.getLogger(TaskSyncDB.class);

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
            while (true) {
                logger.info("TaskSyncDB started");
                ArrayList<MmoConfig> v_configs = new MmoConfigDAO().getAllConfigs();
                logger.info("Done get active configs with: " + v_configs.size() + " elements");
                ArrayList<MmoConfig> v_configs_2 = new ArrayList<MmoConfig>();
                for (MmoConfig c : v_configs) {
//                    logger.info("Config: " + c.toString());
                    if (c.getStatus() == 1) {
                        for (int i = 0; i < c.getCountT(); i++) {
                            v_configs_2.add(c);
                        }
                    }
                }
                logger.info("Done get all count configs with: " + v_configs_2.size() + " elements");

                ArrayList<MmoUserAgent> v_users_mb = (ArrayList<MmoUserAgent>) new MmoUserAgentDAO().getAllUsers()
                        .stream().filter(userAgent -> "mobile".equals(userAgent.getType()))
                        .collect(Collectors.toList());
//                for (MmoUserAgent u : v_users_mb) {
//                    logger.info("User Mobile: " + u.toString());
//                }
                ArrayList<MmoUserAgent> v_users_pc = (ArrayList<MmoUserAgent>) new MmoUserAgentDAO().getAllUsers()
                        .stream().filter(userAgent -> "pc".equals(userAgent.getType()))
                        .collect(Collectors.toList());
//                for (MmoUserAgent u : v_users_pc) {
//                    logger.info("User PC: " + u.toString());
//                }


                setConfigs(v_configs_2);
                setUsersMobile(v_users_mb);
                setUserPC(v_users_pc);
                logger.info("TaskSyncDB finished: " + Thread.currentThread().getName());

                Thread.sleep(300000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MmoConfig getCurrentConfig() {
        if (num_flag >= l_configs.size()) {
            num_flag = 0;
        }
        MmoConfig mc = l_configs.get(num_flag);
        num_flag++;
        return mc;
    }
}
