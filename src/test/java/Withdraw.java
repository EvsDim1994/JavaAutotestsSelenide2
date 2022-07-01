import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Withdraw extends Hooks {
    @Test
    public void Test() {
        $x("//button[@ng-click='withdrawl()']").shouldBe(Condition.visible).click();
        List<SelenideElement> data3 = $$x("//strong[@class='ng-binding']");
        String Balance = data3.get(1).getText();
        System.out.println(Balance);
        $x("//input[@type='number']").sendKeys(Balance);
        try {
            Thread.sleep(3000);
            $x("//input[@type='number']").sendKeys(Balance);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        $x("//button[text()='Withdraw']").click();

        $x("//span[contains(text(),'Transaction successful')]").shouldBe(Condition.visible);
        List<SelenideElement> data4 = $$x("//strong[@class='ng-binding']");
        String newBalance = data4.get(1).getText();
        Assertions.assertEquals("0", newBalance);
    }
}
