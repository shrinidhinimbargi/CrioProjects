package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import dev.failsafe.internal.util.Assert;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.google.com");
        Thread.sleep(20000);
        driver.get("https://leetcode.com/");
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("leetcode")) {
            System.out.println("URL has leetcode");
        } else {
            System.out.println("URL doesn't has leetcode");
        }
    }

    public void testCase02() throws InterruptedException {
        boolean status = false;
        Thread.sleep(2000);
        // System.out.println("Start Test case: testCase01");
        driver.findElement(By.xpath("//a/p[contains(text(),'View Questions ')]")).click();
        status = driver.getCurrentUrl().contains("problemset/");
        // Assert.assertTrue(status,"url doesnot has problemset");
        if (status) {
            System.out.println("current page has problemset/");
        } else {
            System.out.println("current page doesn't has problemset/");
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000);");
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='truncate']/a"));
        for (int i = 1; i < 6; i++) {
            String title = elements.get(i).getText();
            if (title.contains("Two Sum")) {
                System.out.println("the title has Two Sum problem");
            }
            System.out.println(title);
        }

    }

    public void testCase03() throws InterruptedException {
        // System.out.println("Start Test case: testCase01");

        WebElement TwoSumLink = driver.findElement(By.xpath(
                "//*[@id=\"__next\"]/div[1]/div[4]/div[2]/div[1]/div[4]/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div/div/a"));
        TwoSumLink.click();
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("two-sum")) {
            System.out.println("Current url is Two-sum");
        }
    }

    public void testCase04() throws InterruptedException {
        // System.out.println("Start Test case: testCase01");
        driver.findElement(By.xpath("//*[@id=\"submissions_tab\"]/div[2]/div[2]")).click();
        WebElement element = driver.findElement(By.xpath("//a[text()='Register or Sign In']"));
        String elementText = element.getText();
        if (elementText.contains("Register or Sign In")) {
            System.out.println("Register or Sign In is displayed");
        }
    }

}
