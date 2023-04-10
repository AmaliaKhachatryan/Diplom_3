package constructorburger;

import jdk.jfr.Description;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConstructorBurgerTest {
    protected static final String STELLAR_BURGERS_SITE = "https://stellarburgers.nomoreparties.site/";
    static ConstructorBurger constructor;
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
      //System.setProperty("webdriver.chrome.driver","yandexdriver.exe");
        driver = new ChromeDriver();
        constructor = new ConstructorBurger(driver);
        driver.get(STELLAR_BURGERS_SITE);
    }

    @Test
    @Description("Переход в раздел 'Булки'")
    public void checkTrekOnBuns() {
        Assert.assertTrue(constructor.checkTrekBuns());
        constructor.clickOnFillings();
        constructor.clickOnBuns();
        Assert.assertTrue(constructor.checkTrekBuns());
    }

    @Test
    @Description("Переход в раздел 'Начинки'")
    public void checkTrekFillings() {
        constructor.clickOnFillings();
        Assert.assertTrue(constructor.checkTrekFillings());
    }

    @Test
    @Description("Переход в раздел 'Соусы'")
    public void checkTrekSauces() {
        constructor.clickOnSauces();
        Assert.assertTrue(constructor.checkTrekSauces());
    }

    @AfterClass
    public static void cleanUp() {
        driver.quit();
    }
}