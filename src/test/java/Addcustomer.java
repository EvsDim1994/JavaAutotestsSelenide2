import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Addcustomer extends Hooks {
    @Test
    public void test(){
        $x("//button[contains(text(),'Bank Manager Login')]").shouldBe(Condition.visible).click();
        List<SelenideElement> addc = $$x("//button[contains(text(),'Add Customer')]");
        addc.get(0).shouldBe(Condition.visible).click();
        $x("//input[@placeholder='First Name']").sendKeys("Василий");
        $x("//input[@placeholder='Last Name']").sendKeys("Пупкин");
        $x("//input[@placeholder='Post Code']").sendKeys("400055");
        addc.get(1).shouldBe(Condition.visible).click();
        $x("//button[contains(text(),'Customers')]").shouldBe(Condition.visible).click();
        $x("//td[contains(text(),'Василий')]").shouldBe(Condition.visible);
        $x("//td[contains(text(),'Пупкин')]").shouldBe(Condition.visible);
        $x("//td[contains(text(),'400055')]").shouldBe(Condition.visible);
        //проверка наличия акканута и его заведение, если нет.
        List<SelenideElement> list = $$x("//tbody/tr");
        for (SelenideElement el: list) {
            if (el.$x("./td[4]").getText().equals("")) {
                String lastName = el.$x("./td[2]").getText();
                System.out.println("Не заведён аккаунт для " + lastName);
                OpenAccount openAccount = new OpenAccount();
                openAccount.test();
                System.out.println("Аккаунт заведён");
            }
        }
                List<SelenideElement> list2 = $$x("//tbody/tr");
                for (SelenideElement el2: list2){
                    if (el2.$x("./td[2]").getText().equals("Пупкин")) {
                        String nubac = el2.$x("./td[4]").$x("./span").getText();
                        System.out.println(nubac);
                    }
                }
            }
        }






