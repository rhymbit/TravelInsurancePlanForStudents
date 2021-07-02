package com.cognizant.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * This class creates and sets the configuration parameters in a {@code config.properties} file
 * inside the root directory. These configurations are required for running tests.
 */
public class Configuration {
    private static final Path configFilePath = Path.of("config.properties");
    // Path to WebDrivers executable
    private static final String absPath = Path.of(".").toString();
    private static final String webDPath = Path.of(".\\webdrivers").toString();

    private static final Properties prop = new Properties();

    /**
     * Creates a 'config.properties' file with the configurations or properties required for the project.
     */
    public static void  createConfigurations() {
        if (Files.notExists(configFilePath)) {
            try {
                Files.createFile(configFilePath);
            } catch (IOException exp) {
                exp.printStackTrace();
            }
        }
        try (OutputStream out = Files.newOutputStream(configFilePath)) {
            prop.setProperty("baseUrl", "https://www.policybazaar.com/");
            prop.setProperty("edgeDriverPath", webDPath + "\\edgedriver_win64\\msedgedriver.exe");
            prop.setProperty("chromeDriverPath", webDPath + "\\chromedriver_win32\\chromedriver.exe");
            prop.setProperty("geckoDriverPath", webDPath + "\\geckodriver-v0.29.1-win64\\geckodriver.exe");
            prop.setProperty("firefoxDriverPath", webDPath + "\\geckodriver-v0.29.1-win64\\geckodriver.exe");
            prop.setProperty("screenshotPath",  absPath + "\\screenshots");
            prop.setProperty("excelFilePath", absPath + "\\src\\main\\resources\\InputData.xlsx");
            prop.store(out, "Student Insurance Registration Configurations");
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    /**
     * Helper method to check for if a file is present at a give path.
     * @param filePath FilePath to check for existence.
     * @return
     */
    public static boolean checkIfNotExists(Path filePath) {
        return Files.notExists(filePath);
    }

    /**
     * Returns a property value from System's properties for a key argument.
     * @param key Key corresponding to value in System's properties.
     * @return Value for the the given key parameter.
     */
    public static String getProperty(String key) {
        if (checkIfNotExists(configFilePath)) {
            Configuration.createConfigurations();
        }
        String value = null;
        try (InputStream in = Files.newInputStream(configFilePath)) {
            prop.load(in);
            value = prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}

