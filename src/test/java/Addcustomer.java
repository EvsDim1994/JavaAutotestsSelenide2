import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Addcustomer extends Hooks {
    @Test
    public void test(){
        $x("//button[contains(text(),'Bank Manager Login')]").shouldBe(Condition.visible).click();
        List<SelenideElement> addc = $$x("//button[contains(text(),'Add Customer')]");
        addc.get(0).shouldBe(Condition.appear).click();
        $x("//button[contains(text(),'Add Customer')]").shouldBe(Condition.visible).click();
        $x("//input[@placeholder='First Name']").sendKeys("Василий");
        $x("//input[@placeholder='Last Name']").sendKeys("Пупкин");
        $x("//input[@placeholder='Post Code']").sendKeys("400055");
        addc.get(1).shouldBe(Condition.visible).click();
        $x("//td[contains(text(),'Василий')]").shouldBe(Condition.visible);
        $x("//td[contains(text(),'Пупкин')]").shouldBe(Condition.visible);
        $x("//td[contains(text(),'400055')]").shouldBe(Condition.visible);
        //if ()
    }
}
