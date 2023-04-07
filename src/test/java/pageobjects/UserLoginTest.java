package pageobjects;

import io.qameta.allure.Step;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserLoginTest {
    static DataUser user = GeneratorUser.getRandom();
    static UserRegistration registration;
    static UserLogin login;
    static WebDriver driver;

    @Before
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver","yandexdriver.exe");
        driver = new ChromeDriver();
        registration = new UserRegistration(driver);
        login = new UserLogin(driver);
        registration.userRegistration(user);
    }

    @Test
    @Step("Авторизация после регистрации")
    public void checkOfAuthorizationAfterRegistration() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
        login.userLogin(user.getEmail(), user.getPassword());
        Assert.assertTrue(login.checkUserLogin());

    }

    @Test
    @Step("Авторизация через кнопку (Войти в аккаунт)")
    public void checkOfAuthorizationThroughTheLoginButton() {
        login.loginSingInAccountButton(user.getEmail(), user.getPassword());
        Assert.assertTrue(login.checkUserLogin());
    }

    @Test
    @Step("Авторизация через кнопку (Личный  кабинет)")
    public void checkOfAuthorizationThroughThePersonalAccountButton() {
        login.loginPersonalAccountButton(user.getEmail(), user.getPassword());
        Assert.assertTrue(login.checkUserLogin());

    }

    @Test
    @Step("Авторизация через кнопку в форме регистрации")
    public void checkOfAuthorizationThroughTheButtonInTheRegistrationForm() {
        login.loginByRegistrationForm(user.getEmail(), user.getPassword());
        Assert.assertTrue(login.checkUserLogin());
    }

    @Test
    @Step("Авторизация через кнопку в форме восстановления пароля")
    public void checkOfAuthorizationThroughTheButtonInThePwdRecoveryForm() {
        login.loginByPasswordRecovery(user.getEmail(), user.getPassword());
        Assert.assertTrue(login.checkUserLogin());
    }

    @After
    public void cleanUp() {
        Assert.assertTrue(login.checkingRemoveUser(login.getToken()));
        driver.quit();
    }
}