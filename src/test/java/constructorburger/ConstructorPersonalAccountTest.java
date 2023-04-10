package constructorburger;

import databaseuri.BaseURI;
import datauser.DataUser;
import datauser.GeneratorUser;
import jdk.jfr.Description;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.UserLogin;

public class ConstructorPersonalAccountTest {
    static DataUser user = GeneratorUser.getRandom();
    static UserLogin login;
    static WebDriver driver;
    static ConstructorBurger constructor;
    static BaseURI baseURI;

    @BeforeClass
    public static void setUp() {
        // System.setProperty("webdriver.chrome.driver","yandexdriver.exe");
        baseURI = new BaseURI();
        baseURI.createUser(user);
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
        baseURI.checkRemoveUser(login.getToken());
        driver.quit();
    }
}
