import com.codeborne.selenide.*;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Account extends Hooks{

    @Test
    public void Test1(){
        $x("//button[contains(text(), 'Home')]").shouldBe(Condition.visible);
        $x("//button[contains(text(), 'Customer Login')]").shouldBe(Condition.visible).click();
        $x("//select[@id='userSelect']").click();
        $x("//option[contains(text(), 'Harry Potter')]").click();
        $x("//button[contains(text(), 'Login')]").shouldBe(Condition.visible).click();
        List<SelenideElement> data = $$x("//strong[@class='ng-binding']");
        String AccountNumber = data.get(0).getText();
        String Balance = data.get(1).getText();
        String currency = data.get(2).getText();

        System.out.println("AccountNumber:" + AccountNumber);
        System.out.println("Balance:" + Balance);
        System.out.println("Currency:" + currency + "\n");

        if (Balance.equals("0")){
            $x("//button[contains(text(), 'Deposit')]").shouldBe(Condition.visible).click();
            $x("//input[@type='number']").sendKeys("1000");
            $x("//button[@type='submit']").shouldBe(Condition.visible).click();
            String amountmoney = "1000";
            Assertions.assertEquals(amountmoney, data.get(1).getText());
            String AccountNumber1 = data.get(0).getText();
            String Balance1 = data.get(1).getText();
            String currency1 = data.get(2).getText();
            System.out.println("Теперь на балансе");
            System.out.println("AccountNumber:" + AccountNumber1);
            System.out.println("Balance:" + Balance1);
            System.out.println("Currency:" + currency1);
        }
        else {
            System.out.println("На балансе уже есть деньги");
        }
    }
}
