package pageobjects;

import databaseuri.BaseURI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Constructor extends BaseURI {
    protected static final By INGREDIENTS=By.xpath((".//*[@class='text text_type_main-default']"));
    protected static final By INGREDIENTS_NAMES=By.xpath((".//*[@class='text text_type_main-medium mb-6 mt-10']"));
WebDriver driver;

    public Constructor(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement wait(By element) {
        return new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public Constructor clickOnBuns(){
        driver.findElements(INGREDIENTS_NAMES).get(0).click();
        return this;
    }
    public boolean checkTrekBuns(){
        wait(INGREDIENTS_NAMES);
        return driver.findElements(INGREDIENTS_NAMES).get(0).getText().equals("Булки");
    }
    public Constructor clickOnSauces(){
        driver.findElements(INGREDIENTS).get(1).click();
        return this;
    }
    public boolean checkTrekSauces(){
        wait(INGREDIENTS_NAMES);
        return driver.findElements(INGREDIENTS_NAMES).get(1).getText().equals("Соусы");
    }
    public Constructor clickOnFillings(){
        driver.findElements(INGREDIENTS).get(2).click();
        return this;
    }
    public boolean checkTrekFillings(){
        wait(INGREDIENTS_NAMES);
        return driver.findElements(INGREDIENTS_NAMES).get(2).getText().equals("Начинки");
    }
}
