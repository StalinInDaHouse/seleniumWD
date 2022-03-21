package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class democaRegistration {
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

        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
    }


    @Test
    public void regFormTest() throws InterruptedException {

        WebElement firstName = driver.findElement(By.id("firstName"));
        WebElement lastName = driver.findElement(By.id("lastName"));
        WebElement userEmail = driver.findElement(By.id("userEmail"));
        WebElement genderMale = driver.findElement(By.xpath("//*[@class='custom-control custom-radio custom-control-inline'][1]"));
        WebElement genderFemale = driver.findElement(By.xpath("//*[@class='custom-control custom-radio custom-control-inline'][2]"));
        WebElement genderOther = driver.findElement(By.xpath("//*[@class='custom-control custom-radio custom-control-inline'][3]"));
        WebElement userNumber = driver.findElement(By.id("userNumber"));
        WebElement dateOfBirthInput = driver.findElement(By.id("dateOfBirthInput"));
        WebElement subjects = driver.findElement(By.id("subjectsInput"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", subjects);
        WebElement hobbieSports = driver.findElement(By.xpath("//*[@class='custom-control custom-checkbox custom-control-inline'][1]"));
        WebElement hobbieReading = driver.findElement(By.xpath("//*[@class='custom-control custom-checkbox custom-control-inline'][2]"));
        WebElement hobbieMusic = driver.findElement(By.xpath("//*[@class='custom-control custom-checkbox custom-control-inline'][3]"));
        File file = new File("src/img.png");
        WebElement selectPictureButton = driver.findElement(By.id("uploadPicture"));
        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        WebElement stateSelector = driver.findElement(By.id("react-select-3-input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", stateSelector);
        WebElement citySelector = driver.findElement(By.id("react-select-4-input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", citySelector);
        WebElement submitButton = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);


        firstName.sendKeys("Ivan");
        lastName.sendKeys("Ivanovich");
        userEmail.sendKeys("IvanIvanovich@gmail.com");
        genderMale.click();
        userNumber.sendKeys("9137775533");
        dateOfBirthInput.click();
        WebElement monthSelector = driver.findElement(By.cssSelector(".react-datepicker__month-select"));
        Select selectMonth = new Select(monthSelector);
        selectMonth.selectByValue("7");
        WebElement yearSelector = driver.findElement(By.cssSelector(".react-datepicker__year-select"));
        Select selectYear = new Select(yearSelector);
        selectYear.selectByValue("1995");
        WebElement dayOfBirth = driver.findElement(By.xpath("//*[@class='react-datepicker__day react-datepicker__day--013 react-datepicker__day--weekend']"));
        dayOfBirth.click();
        dateOfBirthInput.sendKeys(Keys.ESCAPE);
        subjects.click();
        subjects.sendKeys("Maths");
        subjects.sendKeys(Keys.ENTER);
        hobbieReading.click();
        selectPictureButton.sendKeys(file.getAbsolutePath());
        currentAddress.sendKeys("Lenina 33");
        stateSelector.sendKeys("NCR");
        stateSelector.sendKeys(Keys.ENTER);
        citySelector.sendKeys("Gurgaon");
        citySelector.sendKeys(Keys.ENTER);
        submitButton.click();


        String StudentName = driver.findElement(By.xpath("//div['@class=table-responsive']/table/tbody/tr[1]/td[2]")).getText();
        assertEquals("Ivan Ivanovich", StudentName);
        String StudentEmail = driver.findElement(By.xpath("//div['@class=table-responsive']/table/tbody/tr[2]/td[2]")).getText();
        assertEquals("IvanIvanovich@gmail.com", StudentEmail);
        String Gender = driver.findElement(By.xpath("//div['@class=table-responsive']/table/tbody/tr[3]/td[2]")).getText();
        assertEquals("Male", Gender);
        String Mobile = driver.findElement(By.xpath("//div['@class=table-responsive']/table/tbody/tr[4]/td[2]")).getText();
        assertEquals("9137775533", Mobile);
        String DateOfBirth = driver.findElement(By.xpath("//div['@class=table-responsive']/table/tbody/tr[5]/td[2]")).getText();
        assertEquals("13 August,1995", DateOfBirth);
        String Subjects = driver.findElement(By.xpath("//div['@class=table-responsive']/table/tbody/tr[6]/td[2]")).getText();
        assertEquals("Maths", Subjects);
        String Hobbies = driver.findElement(By.xpath("//div['@class=table-responsive']/table/tbody/tr[7]/td[2]")).getText();
        assertEquals("Reading", Hobbies);
        String Picture = driver.findElement(By.xpath("//div['@class=table-responsive']/table/tbody/tr[8]/td[2]")).getText();
        assertEquals("img.png", Picture);
        String Address = driver.findElement(By.xpath("//div['@class=table-responsive']/table/tbody/tr[9]/td[2]")).getText();
        assertEquals("Lenina 33", Address);
        String StateAndCity	 = driver.findElement(By.xpath("//div['@class=table-responsive']/table/tbody/tr[10]/td[2]")).getText();
        assertEquals("NCR Gurgaon", StateAndCity);


        Thread.sleep(10000);
    }

//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }
}
