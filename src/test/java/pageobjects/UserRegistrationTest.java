package pageobjects;

import databaseuri.BaseURI;
import datauser.DataUser;
import datauser.GeneratorUser;

import jdk.jfr.Description;
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
    BaseURI baseURI;

    @Before
    public void setUp() {
      // System.setProperty("webdriver.chrome.driver","yandexdriver.exe");
        driver = new ChromeDriver();
        registration = new UserRegistration(driver);
        login = new UserLogin(driver);
        baseURI=new BaseURI();
    }

    @Test
    @Description("Успешная регистрация пользователя")
    public void checkingUserSuccessfulRegistration() {
        user= GeneratorUser.getRandom();
        registration.userRegistration(user);
        Assert.assertTrue(registration.checkSuccessfulRegistration());
    }

    @Test
    @Description("Регистрация пользователя с шестизначным паролем")
    public void checkingUserSuccessfulRegistrationWhitSixDigitPassword() {
        user = GeneratorUser.getRandom(6);
        registration.userRegistration(user);
        Assert.assertTrue(registration.checkSuccessfulRegistration());
    }

    @Test
    @Description("Регистрация пользователя с неверным паролем")
    public void checkingUserRegistrationWithWrongPassword() {
        user = GeneratorUser.getRandom(5);
        registration.userRegistration(user);
        Assert.assertTrue(registration.checkIncorrectPassword());
    }

    @Test
    @Description("Регистрация пользователя c авторизованными данными")
    public void checkingUserRegistrationWithAlreadyExistUserData() {
        user = GeneratorUser.getRandom();
        registration.userRegistration(user);
        registration.userRegistration(user);
        Assert.assertTrue(registration.checkUserAlreadyExist());
    }

    @After
    public void cleanUp() {
        if (!(login.loginSingInAccountButton(user.getEmail(), user.getPassword()) == null) &&
                (!(login.getToken() == null))) {
            Assert.assertTrue(baseURI.checkRemoveUser(login.getToken()));
        }
        driver.quit();
    }
}