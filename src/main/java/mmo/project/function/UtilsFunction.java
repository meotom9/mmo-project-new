package mmo.project.function;

import mmo.project.App;
import mmo.project.task.TaskSyncDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class UtilsFunction {
    private static final Logger logger = LogManager.getLogger(UtilsFunction.class);

    public static final Random random;

    static {
        random = new Random();
    }

    public static int randomRange(int min, int max){
        // Create a Random object
        Random random = new Random();

        // Generate a random integer within the range
        int randomNumber = random.nextInt(max - min + 1) + min;

        // Print the random number
        logger.info("Random number between " + min + " and " + max + ": " + randomNumber);

        // Return result
        return randomNumber;
    }

//    public static int rdRange(int min, int max){
//        int randomNumber = ThreadLocalRandom.current().nextInt(min, max);
//
//        // Print the random number
//        logger.info("Random number between " + min + " and " + max + ": " + randomNumber);
//
//        // Return result
//        return randomNumber;
//    }
}
