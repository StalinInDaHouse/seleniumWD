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
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class selectTests {

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

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

    }
//
//    @Test
//    public void selectTest() throws InterruptedException {
//
//        WebElement element = driver.findElement(By.name("select2"));
//        Select select = new Select(element);
//
////        select.selectByIndex(3);
////        Assertions.assertEquals("Шапокляк", element.getAttribute("value"));
//
//        List<WebElement> allOptions = select.getOptions();
//        Assertions.assertEquals("Шапокляк", allOptions.get(3).getAttribute("value"));
//
//        Thread.sleep(3000);
//    }

    @Test
    public void herokuDropDown() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[11]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[3]")).click();

        WebElement dropDown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropDown);

        select.selectByIndex(2);
        Assertions.assertEquals("2", dropDown.getAttribute("value"));

        Thread.sleep(3000);
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
