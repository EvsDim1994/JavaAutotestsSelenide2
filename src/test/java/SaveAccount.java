import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SaveAccount extends Hooks {
    @Test
    public void test() throws SQLException{
        $x("//button[contains(text(),'Bank Manager Login')]").shouldBe(Condition.visible).click();
        $x("//button[contains(text(),'Customers')]").shouldBe(Condition.visible).click();
        try {
            List<SelenideElement> list = $$x("//tbody/tr");
            Thread.sleep(5000);
            for (int i = 0; i < list.size(); i++){
                String lastname = list.get(i).$x("./td[2]").getText();
                System.out.println(lastname);
                String postcode = list.get(i).$x("./td[3]").getText();
                System.out.println(postcode);
                JDBC jdbc = new JDBC();
                jdbc.insert(lastname,postcode);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
