package ru.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractTest {
    private static final Logger logger = LogManager.getLogger("ru.otus.TestHW");
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static JavascriptExecutor executor;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("enable-automation");
        //options.addArguments("--headless");
        //options.addArguments("--window-size=1920,1080");
        //options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-gpu");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        logger.info("Driver loaded");
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        executor = (JavascriptExecutor) driver;
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
