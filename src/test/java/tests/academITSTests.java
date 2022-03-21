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

public class academITSTests {

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

        driver.get("https://academ-it-school.ru/payment?course=java_begin");
        driver.manage().window().maximize();

    }

    @Test
    public void testAuth() throws InterruptedException {
        WebElement testingRB = driver.findElement(By.cssSelector("input[name=CourseType][value=Testing]"));
        WebElement programmingRB = driver.findElement(By.cssSelector("input[name=CourseType][value=Programming]"));
        testingRB.click();
        Assertions.assertEquals("true", testingRB.getAttribute("checked"));
        Assertions.assertFalse(programmingRB.isSelected());

        Thread.sleep(5000);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
