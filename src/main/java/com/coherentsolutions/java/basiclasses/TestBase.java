package com.coherentsolutions.java.basiclasses;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * TestBase
 *
 * This class provides the setup and teardown methods for the WebDriver. It also loads
 * the configuration properties from a file, ensuring tests run in the correct environment.
 */
public abstract class TestBase {

    protected WebDriver driver;
    protected Properties config;

    /**
     * This method is executed before each test method.
     * It sets up the WebDriver and loads the configuration properties.
     */
    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        loadConfigProperties();
    }

    /**
     * This method is executed after each test method.
     * It tears down the WebDriver, closing the browser.
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {  // Check if the driver is not null before quitting
            DriverManager.tearDown();  // Correctly close the WebDriver session
        }
    }

    /**
     * Loads the configuration properties from a file.
     */
    private void loadConfigProperties() {
        config = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/conf.properties")) {
            config.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a configuration value by key.
     * @param key The key of the configuration property.
     * @return String The value of the configuration property.
     */
    public String get(String key) {
        return config.getProperty(key);
    }

    /**
     * Retrieves the WebDriver instance.
     * @return WebDriver The WebDriver instance.
     */
    protected WebDriver getDriver() {
        return driver;
    }
}
