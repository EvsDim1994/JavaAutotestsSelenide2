import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Hooks {

    @BeforeEach
    public void tearDown() {
        Configuration.reportsFolder = "ScreenShots";
        Configuration.timeout = 20000;
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        WebDriverRunner.setWebDriver(webDriver);
        Selenide.open("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }

    @AfterEach
    public void doAfter() {
        WebDriverRunner.getWebDriver().quit();
    }
}
