package com.cognizant.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Configuration {
    private static final Path configFilePath = Path.of("config.properties");
    // Path to WebDrivers executable
    private static final String webDPath = Path.of(
            Path.of("").toAbsolutePath().toString(),"\\webdrivers").toString();

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
            String absPath = Path.of("").toAbsolutePath().toString();
            prop.setProperty("baseUrl", "http://demo.automationtesting.in/Alerts.html");
            prop.setProperty("edgeDriverPath", webDPath + "\\edgedriver_win64\\msedgedriver.exe");
            prop.setProperty("chromeDriverPath", webDPath + "\\chromedriver_win32\\chromedriver.exe");
            prop.setProperty("geckoDriverPath", webDPath + "\\geckodriver-v0.29.1-win64\\geckodriver.exe");
            prop.setProperty("firefoxDriverPath", webDPath + "\\geckodriver-v0.29.1-win64\\geckodriver.exe");
            prop.setProperty("screenshotPath",  absPath + "\\test-screenshots\\alerts");
            prop.setProperty("usernameFilePath", absPath + "\\src\\main\\resources\\username.txt");
            prop.store(out, "Handel Alert, Prompt and Confirm Box Configurations");
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    /**
     * Returns a property value from System's properties for a key argument.
     * @param key Key corresponding to value in System's properties.
     * @return Value for the the given key parameter.
     */
    public static String getProperty(String key) {
        String value = null;
        try (InputStream in = Files.newInputStream(configFilePath)) {
            prop.load(in);
            value = prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * @hidden
     * @return File path of Configuration file.
     */
    public static Path getConfigFilePath() {
        return configFilePath;
    }
}

