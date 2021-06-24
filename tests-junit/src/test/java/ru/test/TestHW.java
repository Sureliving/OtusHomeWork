package ru.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class TestHW {
    private static final Logger logger = LogManager.getLogger("ru.otus.TestHW");
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        logger.info("Драйвер поднят");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testOtusContacts() {
        logger.info("Домашнее задание 1");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        logger.info("Установка ожиданий выполнена");

        driver.get("http://www.otus.ru/");
        logger.info("Страница загружена");

        String actualTitle = driver.getTitle();
        logger.info("Заголовок получен");

        String expectedTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        if (actualTitle.equals(expectedTitle)) {
            logger.info("Заголовок соответствует ожидаемому");
        } else {
            assertEquals(expectedTitle, actualTitle);
            logger.info("Заголовок не соответствует ожидаемому");
        }
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
