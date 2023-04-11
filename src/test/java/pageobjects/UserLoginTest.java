package pageobjects;

import databaseuri.UserClient;
import datauser.DataUser;
import datauser.GeneratorUser;
import jdk.jfr.Description;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserLoginTest {
    protected static final String STELLAR_BURGERS_SITE_LOGIN="https://stellarburgers.nomoreparties.site/login";
    private DataUser user = GeneratorUser.getRandom();
    private UserLogin login;
    private WebDriver driver;
    private UserClient userClient;

    @Before
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver","yandexdriver.exe");
        userClient = new UserClient();
        userClient.createUser(user);
        driver = new ChromeDriver();
        login = new UserLogin(driver);
    }

    @Test
    @Description("Авторизация после регистрации")
    public void checkOfAuthorizationAfterRegistration() {
        driver.get(STELLAR_BURGERS_SITE_LOGIN);
        login.userLogin(user.getEmail(), user.getPassword());
        Assert.assertTrue(login.checkUserLogin());
    }

    @Test
    @Description("Авторизация через кнопку (Войти в аккаунт)")
    public void checkOfAuthorizationThroughTheLoginButton() {
        login.loginSingInAccountButton(user.getEmail(), user.getPassword());
        Assert.assertTrue(login.checkUserLogin());
    }

    @Test
    @Description("Авторизация через кнопку (Личный  кабинет)")
    public void checkOfAuthorizationThroughThePersonalAccountButton() {
        login.loginPersonalAccountButton(user.getEmail(), user.getPassword());
        Assert.assertTrue(login.checkUserLogin());
    }

    @Test
    @Description("Авторизация через кнопку в форме регистрации")
    public void checkOfAuthorizationThroughTheButtonInTheRegistrationForm() {
        login.loginByRegistrationForm(user.getEmail(), user.getPassword());
        Assert.assertTrue(login.checkUserLogin());
    }

    @Test
    @Description("Авторизация через кнопку в форме восстановления пароля")
    public void checkOfAuthorizationThroughTheButtonInThePwdRecoveryForm() {
        login.loginByPasswordRecovery(user.getEmail(), user.getPassword());
        Assert.assertTrue(login.checkUserLogin());
    }

    @After
    public void cleanUp() {
        Assert.assertTrue(userClient.checkRemoveUser(login.getToken()));
        driver.quit();
    }
}