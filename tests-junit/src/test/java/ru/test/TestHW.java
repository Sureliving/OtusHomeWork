package ru.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestHW {
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        executor = (JavascriptExecutor)driver;
    }

    @Test
    public void testHomeWork1() {
        logger.info("Home work #1");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        logger.info("Timeouts are configured");

        driver.get("http://www.otus.ru/");
        logger.info("Page load is complete");

        String actualTitle = driver.getTitle();
        logger.info("Title is received");

        String expectedTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        if (actualTitle.equals(expectedTitle)) {
            logger.info("The title (root Otus) is as expected");
        } else {
            logger.info("The title (root Otus) is not as expected");
            assertEquals(expectedTitle, actualTitle);
        }
    }

    @Test
    public void testHomeWork2_1() {
        logger.info("Home work #1 part 1");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        logger.info("Timeouts are configured");

        driver.get("http://www.otus.ru/");
        logger.info("Root page load is complete");

        WebElement element;
        String xPath;
        try {
            xPath = "//div[@class='header2_subheader-container header2_subheader-container__inline']/a[@title='Контакты']";
            element = driver.findElement(By.xpath(xPath));
            element.click();
            logger.info("Target (contacts) page load is complete");

            try {
                element = driver.findElement(By.xpath("//div[@class='c0qfa0-1 lblsQs']/div[@class='c0qfa0-5 cXQVNI']"));
                String actualAddress = element.getText();
                String expectedAddress = "125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02";

                if (actualAddress.equals(expectedAddress)) {
                    logger.info("The address is as expected");
                } else {
                    logger.info("The address is not as expected");
                    assertEquals(expectedAddress, actualAddress);
                }
            } catch (NoSuchElementException ignored) {
                logger.error("Target (address) element doesn't exist");
                assert false;
            }

            String actualTitle = driver.getTitle();
            String expectedTitle = "Контакты | OTUS";
            assertEquals(expectedTitle, actualTitle);
            if (actualTitle.equals(expectedTitle)) {
                logger.info("The title (contacts Otus) is as expected");
            } else {
                logger.info("The title (contacts Otus) is not as expected");
                assertEquals(expectedTitle, actualTitle);
            }

        } catch (NoSuchElementException ignored) {
            logger.error("Target (contacts) page doesn't exist");
            assert false;
        }
    }

    @Test
    public void testHomeWork2_2() {
        logger.info("Home work #1 part 2");
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        logger.info("Timeouts are configured");

        driver.get("https://msk.tele2.ru/shop/number");
        logger.info("Tele2 numbers page load is complete");

        WebElement element;
        try {
            element = driver.findElement(By.xpath("//div[@class='text-field-holder']/input[@class='text-field']"));

            element.sendKeys("97");
            logger.info("Before wait");
            String script = "return window.jQuery != undefined && jQuery.active === 0";
            wait.until(x -> (executor.executeScript(script)));
            assertTrue((Boolean) executor.executeScript(script));
            logger.info("Changes have taken place");
            /*File screenshot = ((TakesScreenshot) driver). //for checking on file system
                    getScreenshotAs(OutputType.FILE);
            String path = "d:/screenshots/" + screenshot.getName();
            FileUtils.copyFile(screenshot, new File(path));

            logger.info("Screenshot saved");*/
        } catch (NoSuchElementException ignored) {
            logger.error("Target (address) element doesn't exist");
            assert false;
        } /*catch (IOException ignored) {                   //for screenshot maker
            logger.error("Access to file denied");
        }*/
    }

    @Test
    public void testHomeWork2_3() {
        logger.info("Home work #2 part 3");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        logger.info("Timeouts are configured");


        driver.get("http://www.otus.ru/");
        logger.info("Root page load is complete");

        WebElement element;
        String xPath;

        try {
            xPath = "//div[@class='header2_subheader-container header2_subheader-container__inline']/a[@title='FAQ']";
            element = driver.findElement(By.xpath(xPath));
            element.click();
            logger.info("Target (FAQ) page load is complete");

        } catch (NoSuchElementException e) {
            logger.error("Target (FAQ) page doesn't exist");
            assert false;
        }

        try {
            xPath = "//div[@class='faq-question js-faq-question']/div[contains(text(), 'Где посмотреть программу интересующего курса?')]";
            element = driver.findElement(By.xpath(xPath));
            executor.executeScript("arguments[0].scrollIntoView(true);",element);
            element.click();
            logger.info("Target (text) load is complete");
        } catch (NoSuchElementException e) {
            logger.error("Target bottom doesn't exist");
            assert false;
        }

        try {
            String expectedText = "Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями. Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”";
            xPath = "//div[@class='faq-question js-faq-question']/div[contains(text(), '" + expectedText + "')]";
            assertTrue(driver.findElement(By.xpath(xPath)).isDisplayed());
            logger.info("Target (text) is displayed");
        } catch (NoSuchElementException e) {
            logger.error("Target text doesn't exist");
            assert false;
        }
    }

    @Test
    public void testHomeWork2_4() {
        logger.info("Home work #2 part 4");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        logger.info("Timeouts are configured");


        driver.get("http://www.otus.ru/");
        logger.info("Root page load is complete");

        WebElement element;
        String xPath;

        try {
            xPath = "//form[@class='footer2__subscribe js-subscribe']/input[@class='input footer2__subscribe-input']";
            element = driver.findElement(By.xpath(xPath));
            executor.executeScript("arguments[0].scrollIntoView(true);",element);
            element.sendKeys("test@test.com");
            logger.info("Target (subscribe field) was filled");
        } catch (NoSuchElementException e) {
            logger.error("Target (subscribe field) page doesn't exist");
            assert false;
        }

        try {
            xPath = "//form[@class='footer2__subscribe js-subscribe']/button[@class='footer2__subscribe-button button button_blue button_as-input']";
            element = driver.findElement(By.xpath(xPath));
            element.click();
            logger.info("Target (subscribe button) was clicked");
            xPath = "//div[@class='container__col container__col_4']/p[@class='subscribe-modal__success']";
            element = driver.findElement(By.xpath(xPath));
            String currentText = element.getText();
            String expectedText = "Вы успешно подписались";
            assertEquals(expectedText, currentText);
        } catch (NoSuchElementException e) {
            logger.error("Target (subscribe button) doesn't exist");
            assert false;
        }
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
