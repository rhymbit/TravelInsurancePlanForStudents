package com.cognizant.tests;

import com.cognizant.apachePOI.ReadExcel;
import com.cognizant.configuration.Configuration;
import org.testng.annotations.*;


/**
 * This is an abstract classes for all other test classes.
 */
public abstract class TestBase {
    // private variable
    protected ReadExcel readExcel = null;

    /**
     * This method runs at the start of test suite.
     * This method creates the necessary configurations required to run the project.
     */
    @Parameters({"browser", "platform", "environment"})
    @BeforeSuite(alwaysRun = true, enabled = true)
    protected void suiteSetup()
    {
        // Create 'config.properties' file
        Configuration.createConfigurations();
    }

    /**
     * This method runs at the end of test suites.
     * It is used for cleanup work after tests executions.
     */
    @AfterSuite(alwaysRun = true)
    protected void suiteTearDown() {
    }
}
