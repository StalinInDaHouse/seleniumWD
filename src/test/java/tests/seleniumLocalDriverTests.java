package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class seleniumLocalDriverTests {

    @Test
    public void openPageChromeTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dima1\\OneDrive\\Рабочий стол\\Automation\\chromedriver.exe");
        WebDriver wbd = new ChromeDriver();

        wbd.get("https://the-internet.herokuapp.com");
        wbd.manage().window().fullscreen();


        Thread.sleep(2000);

        wbd.quit();
    }

    @Test
    public void openPageWithParameterTest() throws InterruptedException {
        WebDriver wbd = null;

        String browser = System.getProperty("browser");

        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\dima1\\OneDrive\\Рабочий стол\\Automation\\chromedriver.exe");
            wbd = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\dima1\\OneDrive\\Рабочий стол\\Automation\\geckodriver.exe");
            wbd = new ChromeDriver();
        }


        wbd.get("https://the-internet.herokuapp.com");
        wbd.manage().window().fullscreen();

        Thread.sleep(2000);

        wbd.quit();
    }
}
