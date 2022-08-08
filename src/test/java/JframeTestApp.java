import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class JframeTestApp extends JFrame {
    public JButton button = new JButton("Open");
    public JButton button2 = new JButton("Cancel");
    public JTextField input1 = new JTextField("", 50);
    public JLabel label1 = new JLabel("Введите фимилию:");
    public JTextField input2 = new JTextField("", 50);
    public JLabel label2 = new JLabel("Введите имя:");
    public JTextField input3 = new JTextField("", 50);
    public JLabel label3 = new JLabel("Введите индекс:");


    public JframeTestApp() {
        super("OpenAccount");
        this.setBounds(300, 300, 600, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);


        try {
            this.setIconImage(ImageIO.read(new File("Р.jpg")));
        } catch (IOException ex) {
        }

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4, 0, 0, 0));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(label1);
        container.add(input1);
        container.add(label2);
        container.add(input2);
        container.add(label3);
        container.add(input3);
        button.setBackground(Color.GREEN);
        button.addActionListener(new Createaccount());
        container.add(button);
        button2.setBackground(Color.RED);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container frame = button2.getParent();
                do
                    frame = frame.getParent();
                while (!(frame instanceof JFrame));
                ((JFrame) frame).dispose();

            }
        });
        container.add(button2);
    }


    public class Createaccount implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Открытие браузера
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver.exe");
            WebDriver webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            WebDriverRunner.setWebDriver(webDriver);
            Selenide.open("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
            //добавление пользователя
            String lastname = input1.getText();
            String firstname = input2.getText();
            String postcode = input3.getText();
            $x("//button[contains(text(),'Bank Manager Login')]").shouldBe(Condition.visible).click();
            List<SelenideElement> addc = $$x("//button[contains(text(),'Add Customer')]");
            addc.get(0).shouldBe(Condition.visible).click();
            $x("//input[@placeholder='First Name']").sendKeys(firstname);
            $x("//input[@placeholder='Last Name']").sendKeys(lastname);
            $x("//input[@placeholder='Post Code']").sendKeys(postcode);
            addc.get(1).shouldBe(Condition.visible).click();

            //Открытие счёта
            String person = firstname + " " + lastname;
            $x("//button[contains(text(),'Open Account')]").shouldBe(Condition.visible).click();String xpath = String.format("//option[contains(text(),'%s')]", person);$x(xpath).click();
            $x("//option[contains(text(),'Dollar')]").click();
            $x("//button[contains(text(),'Process')]").shouldBe(Condition.visible).click();
            $x("//button[contains(text(),'Customers')]").shouldBe(Condition.visible).click();
            $x("//input[@type = 'text']").sendKeys(lastname);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            String number = $x("//tbody/tr/td/span[@ng-repeat='account in cust.accountNo']").getText();
            WebDriverRunner.getWebDriver().quit();
            String massage = "Account opened with number: " + number;
            JOptionPane.showMessageDialog(null,massage, "Output", JOptionPane.PLAIN_MESSAGE);
        }
    }
}

