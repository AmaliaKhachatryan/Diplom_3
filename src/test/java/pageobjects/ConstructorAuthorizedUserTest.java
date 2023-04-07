package pageobjects;

import io.qameta.allure.Step;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConstructorAuthorizedUserTest {
    static DataUser user;
    static UserRegistration registration;
    static UserLogin login;
    static WebDriver driver;
    static Constructor constructor;


    @BeforeClass
    public static void setUp() {
        // System.setProperty("webdriver.chrome.driver","yandexdriver.exe");
        driver = new ChromeDriver();
        user = GeneratorUser.getRandom();
        login = new UserLogin(driver);
        registration = new UserRegistration(driver);
        constructor=new Constructor(driver);
        registration.userRegistration(user);
        login.userLogin(user.getEmail(),user.getPassword());

    }
    @Test
    @Step("Проверка перехода в раздел 'Булки'")
    public void checkTrekOnBuns() {
        Assert.assertTrue(constructor.checkTrekBuns());
        constructor.checkTrekFillings();
        constructor.clickOnBuns();
        Assert.assertTrue(constructor.checkTrekBuns());
    }

    @Test
    @Step("Проверка перехода в раздел 'Начинки'")
    public void checkTrekFillings() {
        constructor.clickOnFillings();
        Assert.assertTrue(constructor.checkTrekFillings());
    }

    @Test
    @Step("Проверка перехода в раздел 'Соусы'")
    public void checkTrekSauces() {
        constructor.clickOnSauces();
        Assert.assertTrue(constructor.checkTrekSauces());
    }
    @AfterClass
    public static void cleanUp() {
        login.removeUser(login.getToken());
        driver.quit();
    }
}
