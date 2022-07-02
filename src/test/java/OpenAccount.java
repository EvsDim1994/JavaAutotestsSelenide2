import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.codeborne.selenide.Selenide.$x;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OpenAccount {
    @Test
    public void test() {
        $x("//button[contains(text(),'Open Account')]").shouldBe(Condition.visible).click();
        $x("//option[contains(text(),'Василий Пупкин')]").click();
        $x("//option[contains(text(),'Dollar')]").click();
        $x("//button[contains(text(),'Process')]").shouldBe(Condition.visible).click();
        $x("//button[contains(text(),'Customers')]").shouldBe(Condition.visible).click();
        //добавить поиск
    }
}
