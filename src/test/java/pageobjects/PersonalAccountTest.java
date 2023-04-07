package pageobjects;

import io.qameta.allure.Step;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PersonalAccountTest {
    static UserRegistration registration;
    static UserLogin login;
    static DataUser user = GeneratorUser.getRandom();
    static WebDriver driver;

    @Before
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver","yandexdriver.exe");
        driver = new ChromeDriver();
        registration = new UserRegistration(driver);
        login = new UserLogin(driver);
        registration.userRegistration(user);
        login.userLogin(user.getEmail(), user.getPassword());
        login.clickOnPersonalAccountButton();
    }

    @Test
    @Step("Проверка перехода в личный кабинет.")
    public void checkLogin() {
        Assert.assertTrue(login.checkingSuccessfulProfile());
    }

    @Test
    @Step("Проверка выхода из личного кабинета.")
    public void checkLogout() {
        login.logoutOfPersonalAccount();
        Assert.assertTrue(login.checkingLogout());
        login.userLogin(user.getEmail(), user.getPassword());
        Assert.assertTrue(login.checkUserLogin());//для получения токена
    }

    @Test
    @Step("Переход в конструктор из ЛЧ")
    public void checkTrekToConstructor() {
        login.clickOnConstructorButton();
        Assert.assertTrue(login.checkUserLogin());
    }

    @Test
    @Step("Переход в логотип из ЛЧ")
    public void checkTrekToLogo() {
        login.clickOnLogoButton();
        Assert.assertTrue(login.checkUserLogin());
    }

    @After
    public void cleanUp() {
        Assert.assertTrue(login.checkingRemoveUser(login.getToken()));
        driver.quit();
    }
}