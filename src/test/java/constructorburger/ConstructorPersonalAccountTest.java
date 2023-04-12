package constructorburger;

import databaseuri.UserClient;
import datauser.DataUser;
import datauser.GeneratorUser;
import jdk.jfr.Description;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.UserLogin;

public class ConstructorPersonalAccountTest {
    private static DataUser user = GeneratorUser.getRandom();
    private static UserLogin login;
    private static WebDriver driver;
    private static ConstructorBurger constructor;
    private static UserClient userClient;

    @BeforeClass
    public static void setUp() {
        // System.setProperty("webdriver.chrome.driver","yandexdriver.exe");
        userClient = new UserClient();
        userClient.createUser(user);
        driver = new ChromeDriver();
        login = new UserLogin(driver);
        login.loginSingInAccountButton(user.getEmail(), user.getPassword());
        constructor = new ConstructorBurger(driver);
    }
    @Test
    @Description("Переход в раздел 'Булки'")
    public void checkTrekOnBuns() {
        Assert.assertTrue(constructor.checkTrekBuns());
        constructor.checkTrekFillings();
        constructor.clickOnBuns();
        Assert.assertTrue(constructor.checkTrekBuns());
    }

    @Test
    @Description("Переход в раздел 'Начинки'")
    public void checkTrekFillings() {
        constructor.clickOnFillings();
        Assert.assertTrue(constructor.checkTrekFillings());
    }

    @Test
    @Description("Переход в раздел 'Соусы'")
    public void checkTrekSauces() {
        constructor.clickOnSauces();
        Assert.assertTrue(constructor.checkTrekSauces());
    }
    @AfterClass
    public static void cleanUp() {
        userClient.checkRemoveUser(login.getToken());
        driver.quit();
    }
}
