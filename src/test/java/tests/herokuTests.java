package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class herokuTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        String browser = System.getProperty("browser");

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.get("https://the-internet.herokuapp.com");
        driver.manage().window().maximize();

    }

    @Test
    public void checkCurrentUrl() throws InterruptedException {
        Assertions.assertEquals("https://the-internet.herokuapp.com/", driver.getCurrentUrl());
        Thread.sleep(2000);
    }

    @Test
    public void checkTitle() throws InterruptedException {
        Assertions.assertEquals("The Internet", driver.getTitle());
        Thread.sleep(2000);
    }

    @Test
    public void testAuth() throws InterruptedException {
        String username = "tomsmith";
        String password = "SuperSecretPassword!";


        driver.findElement(By.xpath("//a[text()='Form Authentication']")).click();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("fa-sign-in")).click();

        Assertions.assertEquals("https://the-internet.herokuapp.com/secure", driver.getCurrentUrl());
        driver.findElement(By.id("flash")).isDisplayed();
        WebElement succesLogMsgLabel = driver.findElement(By.id("flash"));
        String succesLogMsg = succesLogMsgLabel.getText();

        Assertions.assertTrue(succesLogMsgLabel.isDisplayed());
        Assertions.assertTrue(succesLogMsg.contains("You logged into a secure area!"));

        driver.findElement(By.className("icon-signout")).click();
        Assertions.assertEquals("https://the-internet.herokuapp.com/login", driver.getCurrentUrl());


        Thread.sleep(5000);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
