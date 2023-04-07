package pageobjects;

import io.qameta.allure.Step;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConstructorTest {
    static Constructor constructor;
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
      //System.setProperty("webdriver.chrome.driver","yandexdriver.exe");
        driver = new ChromeDriver();
        constructor = new Constructor(driver);
        driver.get("https://stellarburgers.nomoreparties.site/");
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
        driver.quit();
    }
}