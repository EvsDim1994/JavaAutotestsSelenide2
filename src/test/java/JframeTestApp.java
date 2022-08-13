import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import com.codeborne.selenide.*;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;


public class JframeTestApp extends JFrame {
    public JButton button = new JButton("Open");
    public JButton button2 = new JButton("Cancel");

    public JButton button3 = new JButton("Clear");
    public JButton button4 = new JButton("Seach Account");
    public JTextField input1 = new JTextField("", 50);
    public JLabel label1 = new JLabel("Введите фимилию:");
    public JTextField input2 = new JTextField("", 50);
    public JLabel label2 = new JLabel("Введите имя:");
    public JTextField input3 = new JTextField("", 50);
    public JLabel label3 = new JLabel("Введите индекс:");

    public JTextField input4 = new JTextField("", 50);

    public JLabel label4 = new JLabel("Введите имя или фамилию для поиска:");



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
        container.setLayout(new GridLayout(5, 0, 0, 0));
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
                Selenide.closeWebDriver();
            }
        });
        container.add(button2);
        button3.setBackground(Color.YELLOW);
        button3.addActionListener(new Cleartextfield());
        container.add(button3);
        button4.setBackground(Color.GREEN);
        button4.addActionListener(new Searcaccount());
        container.add(button4);
    }

    public class Createaccount implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Открытие браузера
            Selenide.open("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
            Selenide.webdriver().driver().getWebDriver().manage().window().maximize();
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
            $x("//button[contains(text(),'Open Account')]").shouldBe(Condition.visible).click();
            String xpath = String.format("//option[contains(text(),'%s')]", person);
            $x(xpath).click();
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
            Selenide.closeWebDriver();
            String massage = "Account opened with number: " + number;
            JOptionPane.showMessageDialog(null, massage, "Output", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public class Searcaccount implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JframeTestApp2 jframeTestApp2 = new JframeTestApp2();
            jframeTestApp2.setVisible(true);
        }
    }
    class JframeTestApp2 extends JFrame {
        public JButton button5 = new JButton("Search");
        public JTextField input4 = new JTextField("", 50);
        public JLabel label4 = new JLabel("Введите имя или фамилию для поиска:");

        public JButton button6 = new JButton("Cancel");


        public JframeTestApp2(){
            super("Search Account");
            this.setBounds(200, 200, 600, 200);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Container container2 = this.getContentPane();
            container2.setLayout(new GridLayout(2, 0, 0, 0));
            label4.setHorizontalAlignment(SwingConstants.CENTER);
            container2.add(label4);
            container2.add(input4);
            button5.setBackground(Color.GREEN);
            button5.addActionListener(new SeacrSelenium());
            container2.add(button5);
            button6.setBackground(Color.RED);
            button6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Container frame = button6.getParent();
                    do
                        frame = frame.getParent();
                    while (!(frame instanceof JFrame));
                    ((JFrame) frame).dispose();
                    Selenide.closeWebDriver();
                }
            });
            container2.add(button6);
        }
        public class SeacrSelenium implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Открытие браузера
                Selenide.open("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
                Selenide.webdriver().driver().getWebDriver().manage().window().maximize();
                $x("//button[contains(text(),'Bank Manager Login')]").shouldBe(Condition.visible).click();
                $x("//button[contains(text(),'Customers')]").shouldBe(Condition.visible).click();
                String data = input4.getText();
                $x("//input[@type='text']").sendKeys(data);
            }
        }
    }

    public class Cleartextfield implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            input1.setText("");
            input2.setText("");
            input3.setText("");
        }
    }
}



