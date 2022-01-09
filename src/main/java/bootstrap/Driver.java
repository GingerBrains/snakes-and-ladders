package bootstrap;

import domain.*;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;


public class Driver {
    public static Logger logger = LoggerFactory.getLogger(Driver.class);
    public static int i = 0;
    public static int[] winArray = new int[4];

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to Snake and Ladders - a multithreaded software");
        configureLogging(args[0]+"snakeLogs.log", "INFO");

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        GameThread[] threads = new GameThread[4];

        List<Future<Integer>> winOrder;

        GameThread player0 = new GameThread(0);
        GameThread player1 = new GameThread(1);
        GameThread player2 = new GameThread(2);
        GameThread player3 = new GameThread(3);

        threads[0]=player0;
        threads[1]=player1;
        threads[2]=player2;
        threads[3]=player3;

        winOrder=executorService.invokeAll(Arrays.asList(threads));

        System.out.println("The win order is as follows:\n" + Arrays.toString(Driver.winArray));
        logger.info("The win order is as follows:\n" + Arrays.toString(Driver.winArray));
        executorService.shutdown();
    }

    public static String configureLogging(String logFile,String logLevel){
        DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender();

        String logFilename="";
        switch(logLevel){
            case "DEBUG":{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.DEBUG_INT));
            }
            case "WARN":{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.WARN_INT));
            }
            case "ERROR":{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.ERROR_INT));
            }
            default:{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.INFO_INT));
            }
            break;
        }
        System.out.println("Log files written out at " + logFile);
        dailyRollingFileAppender.setFile(logFile);
        dailyRollingFileAppender.setLayout(new EnhancedPatternLayout("%d [%t] %-5p %c - %m%n"));

        dailyRollingFileAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(dailyRollingFileAppender);
        return dailyRollingFileAppender.getFile();
    }
}
