import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.codeborne.selenide.Selenide.$x;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeachandDel extends Hooks {
    @Test
    public void test(){
        $x("//button[contains(text(),'Bank Manager Login')]").shouldBe(Condition.visible).click();
        $x("//button[contains(text(),'Customers')]").shouldBe(Condition.visible).click();
        String name = "Hermoine";
        $x("//input[@placeholder='Search Customer']").sendKeys(name);
        String postcode = $x("//tbody/tr/td[3]").getText();
        String postcode1 = "E859AB";
        Assertions.assertEquals(postcode, postcode1);

        try {
            Thread.sleep(4000);
            $x("//button[contains(text(),'Delete')]").shouldBe(Condition.visible).click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
