package pageobjects;


import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class UserLogin extends UserRegistration {
    WebDriver driver;
    protected static final By SIGN_IN_ACCOUNT = By.xpath(".//button[text()='Войти в аккаунт']");
    protected static final By PROFILE_STRING = By.xpath(".//*[text()='Профиль']");
    protected static final By LOGIN_BUTTON = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    protected static final By LOGIN_STRING = By.xpath(".//*[@class='Auth_link__1fOlj']");
    protected static final By CHECKOUT_BUTTON = By.xpath(".//button[text()='Оформить заказ']");
    protected static final By CONSTRUCTOR_BUTTON = By.xpath(".//*[text()='Конструктор']");
    protected static final By STELLAR_BURGERS_LOGO = By.xpath(".//*[@class='AppHeader_header__logo__2D0X2']");
    protected static final By ACCOUNT_LOGIN_STRING = By.xpath(".//*[text()='Вход']");

    public UserLogin(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebElement wait(By element) {
        return new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    @Step("Авторизация после регистрации")
    public UserLogin userLogin(String email, String password) {
        wait(ACCOUNT_LOGIN_STRING);
        setUserEmailLogin(email);
        setUserPassword(password);
        clickOnButtonLogin();
        return this;
    }

    @Step("Авторизация через кнопку (Войти в аккаунт)")
    public UserLogin loginSingInAccountButton(String email, String password) {
        openRage();
        wait(SIGN_IN_ACCOUNT);
        driver.findElement(SIGN_IN_ACCOUNT).click();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        userLogin(email, password);
        return this;
    }
    @Step("Авторизация через личный  кабинет")
    public UserLogin loginPersonalAccountButton(String email, String pass) {
        openRage();
        wait(PERSONAL_ACCOUNT_BUTTON);
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        userLogin(email, pass);
        return this;
    }
    @Step("Авторизация через  форму регистрации")
    public UserLogin loginByRegistrationForm(String email, String pass) {
        openRage();
        wait(PERSONAL_ACCOUNT_BUTTON);
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        WebElement element = driver.findElements(REGISTER_STRING).get(0);
        ((JavascriptExecutor) driver).executeScript(
                ("arguments[0].scrollIntoView();"), element);
        driver.findElements(REGISTER_STRING).get(0).click();
        driver.findElement(LOGIN_STRING).click();
        userLogin(email, pass);
        return this;
    }
    @Step("Авторизация через форму восстановления пароля")
    public UserLogin loginByPasswordRecovery(String email, String pass) {
        openRage();
        wait(PERSONAL_ACCOUNT_BUTTON);
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        WebElement element = driver.findElements(REGISTER_STRING).get(1);
        ((JavascriptExecutor) driver).executeScript(
                ("arguments[0].scrollIntoView();"), element);
        driver.findElements(REGISTER_STRING).get(1).click();
        driver.findElement(LOGIN_STRING).click();
        userLogin(email, pass);
        return this;
    }

    public boolean checkUserLogin() {
        return wait(CHECKOUT_BUTTON).isDisplayed();
    }
    @Step("Выход из личного кабинета.")
    public UserLogin logoutOfPersonalAccount() {
        wait(EXIT_BUTTON);
        driver.findElement(EXIT_BUTTON).click();
        return this;
    }

    public boolean checkingLogout() {
        return wait(ACCOUNT_LOGIN_STRING).getText().equals("Вход");
    }

    public UserRegistration clickOnButtonLogin() {
        wait(LOGIN_BUTTON);
        driver.findElement(LOGIN_BUTTON).click();
        return this;
    }

    public UserRegistration clickOnConstructorButton() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
        return this;
    }

    public UserRegistration clickOnLogoButton() {
        driver.findElement(STELLAR_BURGERS_LOGO).click();
        return this;
    }

    public UserLogin clickOnPersonalAccountButton() {
        wait(CHECKOUT_BUTTON);
        WebElement element = driver.findElement(PERSONAL_ACCOUNT_BUTTON);
        ((JavascriptExecutor) driver).executeScript(
                ("window.scrollBy(0,25)"), element);
        driver.findElement(PERSONAL_ACCOUNT_BUTTON).click();
        return this;
    }
    public boolean checkingSuccessfulProfile() {
        return wait(PROFILE_STRING).getText().equals("Профиль");
    }
    @Step("Получить токен клиента.")
    public String getToken() {
        WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
        LocalStorage localStorage = webStorage.getLocalStorage();
        return localStorage.getItem("accessToken");
    }


}
