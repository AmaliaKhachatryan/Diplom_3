package pageobjects;

import databaseuri.BaseURI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class UserRegistration extends BaseURI {

    protected static final By PERSONAL_ACCOUNT_BUTTON = By.xpath(".//*[text()='Личный Кабинет']");
    protected static final By REGISTER_STRING = By.xpath(".//*[@class='Auth_link__1fOlj']");
    protected static final By SING_IN_BUTTON = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    protected static final By USERNAME_EMAIL_STRING = (By.xpath("//*[@type='text']"));
    protected static final By USER_PASSWORD_STRING = By.xpath("//*[@type='password']");
    protected static final By REGISTER_BUTTON = By.xpath(".//button[text()='Зарегистрироваться']");
    protected static final By ACCOUNT_LOGIN_STRING = By.xpath(".//*[text()='Вход']");
    protected static final By EXIT_BUTTON = By.xpath(".//button[text()='Выход']");
    protected static final By ERROR_MESSAGE = By.xpath("//*[@class='input__error text_type_main-default']");
    private WebDriver driver;

    public UserRegistration(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement wait(By element) {
        return new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public UserRegistration userRegistration(DataUser user) {
        openRage();
        clickOnPersonalAccountButtonForRegistration();
        clickOnStringRegister();
        setUserName(user.getName());
        setUserEmail(user.getEmail());
        setUserPassword(user.getPassword());
        clickOnButtonRegister();
        return this;
    }

    public boolean checkingUserAlreadyExist() {
        return wait(ERROR_MESSAGE).getText().equals("Такой пользователь уже существует");
    }

    public boolean checkingIncorrectPassword() {
        return wait(ERROR_MESSAGE).getText().equals("Некорректный пароль");
    }

    public UserRegistration openRage() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(STELLAR_BURGERS_SITE);
        return this;
    }

    public UserRegistration clickOnPersonalAccountButtonForRegistration() {
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        wait(SING_IN_BUTTON);
        return this;
    }

    public UserRegistration clickOnStringRegister() {
        WebElement element = driver.findElements(REGISTER_STRING).get(0);
        ((JavascriptExecutor) driver).executeScript(
                ("arguments[0].scrollIntoView();"), element);
        driver.findElements(REGISTER_STRING).get(0).click();
        return this;
    }

    public UserRegistration setUserName(String name) {
        driver.findElements(USERNAME_EMAIL_STRING).get(0).sendKeys(name);
        return this;
    }

    public UserRegistration setUserEmail(String email) {
        driver.findElements(USERNAME_EMAIL_STRING).get(1).sendKeys(email);
        return this;
    }

    public UserRegistration setUserPassword(String pwd) {
        driver.findElement(USER_PASSWORD_STRING).sendKeys(pwd);
        return this;
    }

    public UserRegistration clickOnButtonRegister() {
        driver.findElement(REGISTER_BUTTON).click();
        return this;
    }

    public UserRegistration setUserEmailLogin(String email) {
        driver.findElement(USERNAME_EMAIL_STRING).sendKeys(email);
        return this;
    }

    public boolean checkingSuccessfulRegistration() {
        return wait(ACCOUNT_LOGIN_STRING).getText().equals("Вход");
    }
}
