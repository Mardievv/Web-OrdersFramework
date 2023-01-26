package com.academy.techcenture.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.DevToolsException;

import java.time.Duration;

import static com.academy.techcenture.config.ConfigReader.getProperties;

public class Driver {

    private Driver(){}

    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void waitConfiguration(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(getProperties("implicitWait"))));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(getProperties("pageLoadTime"))));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public static WebDriver getDriver(){

        String browser = getProperties("browser");

        switch (browser.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                waitConfiguration();
                return driver;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                waitConfiguration();
                return driver;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                waitConfiguration();
                return driver;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                waitConfiguration();
                return driver;
            default:
                throw new RuntimeException("No  driver found!!!");
            }
        }


    public static void quitDriver(){
        if (driver != null)
            driver.quit();
    }




}
