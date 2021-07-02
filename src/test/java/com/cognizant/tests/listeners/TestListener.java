package com.cognizant.tests.listeners;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TestNG listener class to use with Jenkins and Cucumber.
 */
public class TestListener extends TestListenerAdapter {

    /**
     * @hidden
     * @param testContext
     */
    @Override
    public void onStart(ITestContext testContext) {
        try {
            log("\nSuite Start Date: "+
                    new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss").format(new Date())+
            ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onStart(testContext);
    }

    /**
     * @hidden
     * @param testContext
     */
    @Override
    public void onFinish(ITestContext testContext) {
        try {
            log("\nTotal Passed = " +
                    getPassedTests().size() +
                    ", Total Failed = " +
                    getFailedTests().size() +
                    ", Total Skipped = " +
                    getSkippedTests().size() +
                    "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onFinish(testContext);
    }

    /**
     * @hidden
     * @param tr
     */
    @Override
    public void onTestSuccess(ITestResult tr) {
        try {
            log("***Result = PASSED***\n");
            log(tr.getEndMillis(),
                    "Test  -> " +
                    tr.getInstanceName() +
                    "." +
                    tr.getName());
        } catch(Exception e) {
            e.printStackTrace();
        }
        super.onTestSuccess(tr);
    }

    /**
     * @hidden
     * log - method to log data to standard out or logfile
     * @param date
     * @param dataLine
     */
    public void log(long date, String dataLine) {
        System.out.format("%s%n", new Date(date), dataLine);
        if (logFile != null){
//            writeLogFile(logFile, dataLine);
        }
    }

    /**
     * @hidden
     */
    public static String logFile = null;

    /**
     * @hidden
     * log - overloaded method to log data to standard out or logfile
     *
     * @param dataLine
     */
    public void log(String dataLine) {
        System.out.format("%s%n", dataLine);
        if ( logFile != null ) {
//            writeLogFile(logFile, dataLine);
        }
    }

}
