package ru.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestHW extends AbstractTest {
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
        wait = new WebDriverWait(driver, 10l, 100l);
        executor = (JavascriptExecutor) driver;
    }

    @Test
    public void testHomeWork1() {
        logger.info("Home work #1");
        driver.manage().timeouts().pageLoadTimeout(10l, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10l, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10l, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        logger.info("Timeouts are configured");

        driver.get("http://www.otus.ru/");
        logger.info("Page load is complete");

        String actualTitle = driver.getTitle();
        logger.info("Title is received");

        String expectedTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        assertEquals("The title (root Otus) is not as expected", expectedTitle, actualTitle);
    }

    @Test
    public void testHomeWork2_1() {
        logger.info("Home work #1 part 1");
        driver.manage().timeouts().pageLoadTimeout(10l, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10l, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10l, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        logger.info("Timeouts are configured");

        driver.get("http://www.otus.ru/");
        logger.info("Root page load is complete");

        WebElement element;
        String xPath;

        xPath = "//div[@class='header2_subheader-container header2_subheader-container__inline']/a[@title='Контакты']";
        element = driver.findElement(By.xpath(xPath));
        element.click();
        logger.info("Target (contacts) page load is complete");

        element = driver.findElement(By.xpath("//div[@class='c0qfa0-1 lblsQs']/div[@class='c0qfa0-5 cXQVNI']"));
        String actualAddress = element.getText();
        String expectedAddress = "125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02";
        assertEquals("The address is not as expected", expectedAddress, actualAddress);

        String actualTitle = driver.getTitle();
        String expectedTitle = "Контакты | OTUS";
        assertEquals("The title (contacts Otus) is not as expected", expectedTitle, actualTitle);

    }

    @Test
    public void testHomeWork2_2() {
        logger.info("Home work #1 part 2");
        driver.manage().timeouts().setScriptTimeout(10l, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        logger.info("Timeouts are configured");

        driver.get("https://msk.tele2.ru/shop/number");
        logger.info("Tele2 numbers page load is complete");

        WebElement element;

        element = driver.findElement(By.xpath("//div[@class='text-field-holder']/input[@class='text-field']"));

        element.sendKeys("97");
        logger.info("Before wait");
        String script = "return window.jQuery != undefined && jQuery.active === 0";
        wait.until(x -> (executor.executeScript(script)));
        assertTrue((Boolean) executor.executeScript(script));
        logger.info("Changes have taken place");

    }

    @Test
    public void testHomeWork2_3() {
        logger.info("Home work #2 part 3");
        driver.manage().timeouts().pageLoadTimeout(10l, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10l, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10l, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        logger.info("Timeouts are configured");


        driver.get("http://www.otus.ru/");
        logger.info("Root page load is complete");

        WebElement element;
        String xPath;


        xPath = "//div[@class='header2_subheader-container header2_subheader-container__inline']/a[@title='FAQ']";
        element = driver.findElement(By.xpath(xPath));
        element.click();
        logger.info("Target (FAQ) page load is complete");


        xPath = "//div[@class='faq-question js-faq-question']/div[contains(text(), 'Где посмотреть программу интересующего курса?')]";
        element = driver.findElement(By.xpath(xPath));
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        logger.info("Target (text) load is complete");


        String expectedText = "Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями. Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”";
        xPath = "//div[@class='faq-question js-faq-question']/div[contains(text(), '" + expectedText + "')]";
        assertTrue("Target (text) is not displayed", driver.findElement(By.xpath(xPath)).isDisplayed());

    }

    @Test
    public void testHomeWork2_4() {
        logger.info("Home work #2 part 4");
        driver.manage().timeouts().pageLoadTimeout(10l, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10l, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10l, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        logger.info("Timeouts are configured");


        driver.get("http://www.otus.ru/");
        logger.info("Root page load is complete");

        WebElement element;
        String xPath;


        xPath = "//form[@class='footer2__subscribe js-subscribe']/input[@class='input footer2__subscribe-input']";
        element = driver.findElement(By.xpath(xPath));
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        element.sendKeys("test@test.com");
        logger.info("Target (subscribe field) was filled");


        xPath = "//form[@class='footer2__subscribe js-subscribe']/button[@class='footer2__subscribe-button button button_blue button_as-input']";
        element = driver.findElement(By.xpath(xPath));
        element.click();
        logger.info("Target (subscribe button) was clicked");
        xPath = "//div[@class='container__col container__col_4']/p[@class='subscribe-modal__success']";
        element = driver.findElement(By.xpath(xPath));
        String currentText = element.getText();
        String expectedText = "Вы успешно подписались";
        assertEquals("Te text for subscription is not as expected", expectedText, currentText);

    }


    @Test
    public void testHomeWork3_1() throws InterruptedException {
        logger.info("Home work #3 part 1");
        //driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        logger.info("Timeouts are configured");

        WebElement element;
        String xPath;
        String expectedText;
        String script = "return window.jQuery != undefined && jQuery.active === 0";

        driver.get("https://www.220-volt.ru/");
        wait.until(x -> (executor.executeScript(script)));
        logger.info("Root page load is complete");

        xPath = "//ul[@class='js-menu']//a[@title='Электроинструменты']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath))).click();

        xPath = "//div[@class='catalogue-tile mhtspace-20']/ul/li/a[@title='Перфораторы']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath))).click();

        xPath = "//ul[@class='characteristics-select']/li/span/input[@title='MAKITA']";
        if (!wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath))).isSelected()) {
            driver.findElement(By.xpath(xPath)).click();
        }

        xPath = "//ul[@class='characteristics-select']/li/span/input[@title='ЗУБР']";
        if (!wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath))).isSelected()) {
            driver.findElement(By.xpath(xPath)).click();
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.id("filterSubm"))).click();

        wait.until(x -> (executor.executeScript(script)));

        xPath = "//span[@class='selection']//span[@class='select2-selection__arrow']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath))).click();

        xPath = "//ul[@class='select2-results__options']//span[@class='listing-select-icon1']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath))).click();

        wait.until(x -> (executor.executeScript(script)));

        Pair<Integer, WebElement> zubr = new Pair<>(Integer.MAX_VALUE, null);
        Pair<Integer, WebElement> makita = new Pair<>(Integer.MAX_VALUE, null);
        int i = 1;
        while (true) {
            try {
                xPath = "//ul[@id='product-list']/li[" + i + "]/div/div[@class = 'new-item-list-name']/a[contains(text(), 'Перфоратор')]";
                String title = driver.findElement(By.xpath(xPath)).getText().toLowerCase();
                logger.info("title - " + title);
                if (title.contains("перфоратор зубр")) {
                    xPath = "//ul[@id='product-list']/li[" + i + "]/div/div[@class='new-item-list-group']/div[@class='new-item-list-price-im']/ins";
                    int price = Integer.parseInt(driver.findElement(By.xpath(xPath)).getText().replaceAll("\\D", ""));
                    logger.info("price - " + price);
                    if (price <= zubr.getKey()) {
                        xPath = "//ul[@id='product-list']/li[" + i + "]/div/div/span[@class = 'new-item-list-compare-button']/label/i[@title = 'Добавить к сравнению']";
                        element = driver.findElement(By.xpath(xPath));
                        zubr = new Pair<>(price, element);
                    }
                } else if (title.contains("перфоратор makita")) {
                    xPath = "//ul[@id='product-list']/li[" + i + "]/div/div[@class='new-item-list-group']/div[@class='new-item-list-price-im']/ins";
                    int price = Integer.parseInt(driver.findElement(By.xpath(xPath)).getText().replaceAll("\\D", ""));
                    logger.info("price - " + price);
                    if (price <= makita.getKey()) {
                        xPath = "//ul[@id='product-list']/li[" + i + "]/div/div/span[@class = 'new-item-list-compare-button']/label/i[@title = 'Добавить к сравнению']";
                        element = driver.findElement(By.xpath(xPath));
                        makita = new Pair<>(price, element);
                    }
                }
                i++;
            } catch (NoSuchElementException ignored) {
                break;
            }
        }
        wait.until(ExpectedConditions.elementToBeClickable(zubr.getValue())).click();
        xPath = "//td[@class='buttons']/div[@class='button line toCompare']/a[@class='activeButton']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath))).click();
        wait.until(ExpectedConditions.elementToBeClickable(makita.getValue())).click();
        xPath = "//tr/td/div/a[@href='/compare/']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath))).click();
        Thread.sleep(5000);
    }


    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
