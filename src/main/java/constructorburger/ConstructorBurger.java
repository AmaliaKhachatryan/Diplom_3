package constructorburger;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConstructorBurger {
    protected static final String STELLAR_BURGERS_SITE = "https://stellarburgers.nomoreparties.site/";
    protected static final By INGREDIENTS = By.xpath((".//*[@class='text text_type_main-default']"));
    protected static final By INGREDIENTS_NAMES = By.xpath((".//*[@class='text text_type_main-medium mb-6 mt-10']"));
    protected static final By CURRENT_TAB = By.xpath(".//*[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
    WebDriver driver;

    public ConstructorBurger(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement wait(By element) {
        return new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    @Step("Переход в раздел 'Булки'")
    public ConstructorBurger clickOnBuns(){
        driver.findElements(INGREDIENTS_NAMES).get(0).click();
        return this;
    }
    public boolean checkTrekBuns(){
        wait(INGREDIENTS_NAMES);
        return driver.findElement(CURRENT_TAB).getText().equals("Булки");
    }
    @Step("Переход в раздел 'Соусы'")
    public ConstructorBurger clickOnSauces(){
        driver.findElements(INGREDIENTS).get(1).click();
        return this;
    }
    public boolean checkTrekSauces(){
        wait(INGREDIENTS_NAMES);
        return driver.findElement(CURRENT_TAB).getText().equals("Соусы");
    }
    @Step("Переход в раздел 'Начинки'")
    public ConstructorBurger clickOnFillings(){
        driver.findElements(INGREDIENTS).get(2).click();
        return this;
    }
    public boolean checkTrekFillings(){
        wait(INGREDIENTS_NAMES);
        return driver.findElement(CURRENT_TAB).getText().equals("Начинки");
    }
}
