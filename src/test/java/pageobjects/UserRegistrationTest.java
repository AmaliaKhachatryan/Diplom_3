package pageobjects;

import io.qameta.allure.Step;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class UserRegistrationTest {
    DataUser user;
    UserRegistration registration;
    UserLogin login;
    WebDriver driver;

    @Before
    public void setUp() {
      // System.setProperty("webdriver.chrome.driver","yandexdriver.exe");
        driver = new ChromeDriver();
        registration = new UserRegistration(driver);
        login = new UserLogin(driver);
    }

    @Test
    @Step("Успешная регистрация пользователя")
    public void checkingUserSuccessfulRegistration() {
        user=GeneratorUser.getRandom();
        registration.userRegistration(user);
        Assert.assertTrue(registration.checkingSuccessfulRegistration());
    }

    @Test
    @Step("Регистрация пользователя с шестизначным паролем")
    public void checkingUserSuccessfulRegistrationWhitSixDigitPassword() {
        user = GeneratorUser.getRandomWithSexDigitPassword();
        registration.userRegistration(user);
        Assert.assertTrue(registration.checkingSuccessfulRegistration());
    }

    @Test
    @Step("Регистрация пользователя с неверным паролем")
    public void checkingUserRegistrationWithWrongPassword() {
        user = GeneratorUser.getRandomIncorrectPassword();
        registration.userRegistration(user);
        Assert.assertTrue(registration.checkingIncorrectPassword());
    }

    @Test
    @Step("Регистрация пользователя c авторизованными данными")
    public void checkingUserRegistrationWithAlreadyExistUserData() {
        user = GeneratorUser.getRandom();
        registration.userRegistration(user);
        registration.userRegistration(user);
        Assert.assertTrue(registration.checkingUserAlreadyExist());
    }

    @After
    public void cleanUp() {
        if (!(login.loginSingInAccountButton(user.getEmail(), user.getPassword()) == null) &&
                (!(login.getToken() == null))) {
            Assert.assertTrue(login.checkingRemoveUser(login.getToken()));
        }
        driver.quit();
    }
}