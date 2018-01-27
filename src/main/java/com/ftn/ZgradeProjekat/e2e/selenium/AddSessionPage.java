package com.ftn.ZgradeProjekat.e2e.selenium;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Momir on 1/27/2018.
 */
@Getter
public class AddSessionPage {

    private WebDriver driver;

    @FindBy(id="date")
    private WebElement inputDate;

    @FindBy(id="time")
    private WebElement inputTime;

    @FindBy(id="description")
    private WebElement inputDescription;

    @FindBy(id="addTopic")
    private WebElement addTopic;

    @FindBy(id="addSession")
    private WebElement addSession;


    public AddSessionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(addSession));
    }

    public void setInputDate(String value) {
        WebElement el = getInputDate();
        el.sendKeys(Keys.ARROW_DOWN);
        el.sendKeys(Keys.ARROW_RIGHT);
        el.sendKeys(Keys.ARROW_UP);
        el.sendKeys(Keys.ARROW_RIGHT);
        el.sendKeys(Keys.ARROW_UP);

//        el.sendKeys(Keys.CLEAR);
//        el.sendKeys(value);
    }

    public void setInputTime(String value) {
        WebElement el = getInputTime();
        el.sendKeys(Keys.ARROW_DOWN);
        el.sendKeys(Keys.ARROW_RIGHT);
        el.sendKeys(Keys.ARROW_UP);
        el.sendKeys(Keys.ARROW_RIGHT);
        el.sendKeys(Keys.ARROW_UP);

//        el.sendKeys(Keys.CLEAR);
//        el.sendKeys(value);
    }

    public void setInputDescription(String value) {
        WebElement el = getInputDescription();
        el.clear();
        el.sendKeys(value);
    }
}
