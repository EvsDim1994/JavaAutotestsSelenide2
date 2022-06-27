import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Hooks {

    @BeforeAll
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver.exe");
    }

    @BeforeEach
    public void tearDown() {
        Configuration.reportsFolder = "ScreenShots";
        Configuration.timeout = 20000;
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        WebDriverRunner.setWebDriver(webDriver);
        Selenide.open("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }

  /*  @AfterEach
    public void doAfter() {
        WebDriverRunner.getWebDriver().quit();
    }*/

}
