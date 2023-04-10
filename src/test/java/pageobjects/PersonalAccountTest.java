package pageobjects;

import databaseuri.BaseURI;
import datauser.DataUser;
import datauser.GeneratorUser;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PersonalAccountTest {
    static UserLogin login;
    static DataUser user = GeneratorUser.getRandom();
    static WebDriver driver;
    static BaseURI baseURI;

    @Before
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver","yandexdriver.exe");
        baseURI = new BaseURI();
        baseURI.createUser(user);
        driver = new ChromeDriver();
        login = new UserLogin(driver);
        login.loginSingInAccountButton(user.getEmail(), user.getPassword());
        login.clickOnPersonalAccountButton();
    }

    @Test
    @Description("Перехода в личный кабинет.")
    public void checkLogin() {
        Assert.assertTrue(login.checkingSuccessfulProfile());
    }

    @Test
    @Description("Выход из личного кабинета.")
    public void checkLogout() {
        login.logoutOfPersonalAccount();
        Assert.assertTrue(login.checkingLogout());
        login.userLogin(user.getEmail(), user.getPassword());
        Assert.assertTrue(login.checkUserLogin());//для получения токена
    }

    @Test
    @Description("Переход в конструктор из ЛК")
    public void checkTrekToConstructor() {
        login.clickOnConstructorButton();
        Assert.assertTrue(login.checkUserLogin());
    }

    @Test
    @Description("Переход в логотип из ЛК")
    public void checkTrekToLogo() {
        login.clickOnLogoButton();
        Assert.assertTrue(login.checkUserLogin());
    }

    @After
    public void cleanUp() {
        Assert.assertTrue(baseURI.checkRemoveUser(login.getToken()));
        driver.quit();
    }
}