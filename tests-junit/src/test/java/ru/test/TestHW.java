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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        executor = (JavascriptExecutor) driver;
    }

    @Test
    public void testHomeWork1() {
        logger.info("Home work #1");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(3));
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
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(3));
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
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(3));
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
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(3));
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
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(3));
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
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        logger.info("Timeouts are configured");


        driver.get("https://yandex.ru/");
        logger.info("Root page load is complete");

        WebElement element;
        String xPath;
        String expectedText;

        xPath = "//li[@class='services-new__list-item']/a[@data-id='market']";
        element = driver.findElement(By.xpath(xPath));

        Set<String> oldWindowsSet = driver.getWindowHandles();

        element.click();
        // ожидаем открытия и получаем дескриптор нового окна
        String newWindow = wait
                .until((ExpectedCondition<String>) driver -> {
                            Set<String> newWindowsSet = driver.getWindowHandles();
                            newWindowsSet.removeAll(oldWindowsSet);
                            return newWindowsSet.size() > 0 ?
                                    newWindowsSet.iterator().next() : null;
                        }
                );

        driver.switchTo().window(newWindow);

        expectedText = "Электроника";
        xPath = "//a[@class='_3Lwc_UVFq4']/span[contains(text(), '" + expectedText + "')]";
        element = driver.findElement(By.xpath(xPath));
        element.click();

        expectedText = "Смартфоны";
        xPath = "//li/div/a[contains(text(), '" + expectedText + "')]";
        element = driver.findElement(By.xpath(xPath));
        element.click();

        expectedText = "Samsung";
        xPath = "//label/div/span[contains(text(), '" + expectedText + "')]";
        element = driver.findElement(By.xpath(xPath));
        element.click();

        expectedText = "Xiaomi";
        xPath = "//label/div/span[contains(text(), '" + expectedText + "')]";
        element = driver.findElement(By.xpath(xPath));
        element.click();

        expectedText = "по цене";
        xPath = "//div/div/button[contains(text(), '" + expectedText + "')]";
        element = driver.findElement(By.xpath(xPath));
        element.click();


        /*String script = "return window.jQuery != undefined && jQuery.active === 0";
        wait.until(x -> (executor.executeScript(script)));
        assertTrue((Boolean) executor.executeScript(script));*/

        //element.click();


        //assertTrue((Boolean) executor.executeScript(script));

        xPath = "//div[@class='_3U6u5DZPMH _1EDYEYRfnr cia-vs']/article[@class='_1_IxNTwqll cia-vs cia-cs']";
        List<WebElement> elements = driver.findElements(By.xpath(xPath));



        for (WebElement we : elements) {

            logger.info(we.findElement(By.xpath(".//span[contains(text(), 'Смартфон')]")).getText());
            logger.info(we.getAttribute("data-zone-data"));
        }

        Thread.sleep(5000);
        logger.info(elements.size() + "- elements was found");



        logger.info("Target (FAQ) page load is complete");



    }


    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
