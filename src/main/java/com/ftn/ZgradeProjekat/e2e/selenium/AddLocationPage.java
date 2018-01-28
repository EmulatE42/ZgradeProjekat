package com.ftn.ZgradeProjekat.e2e.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by djuro on 1/27/2018.
 */
@Getter
public class AddLocationPage
{
    private WebDriver driver;

    @FindBy(id="exampleSelect1")
    private WebElement selectType;

    @FindBy(id="floor")
    private WebElement inputFloor;

    @FindBy(id="square")
    private WebElement inputSquare;

    @FindBy(id="numberOfFloors")
    private WebElement inputNumberOfFloors;

    @FindBy(id="addLocationButton")
    private WebElement addLocationButton;

    public AddLocationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(addLocationButton));
    }

    public void setInputFloor(String value) {
        WebElement el = getInputFloor();
        el.clear();
        el.sendKeys(value);
    }

    public void setInputSquare(String value) {
        WebElement el = getInputSquare();
        el.clear();
        el.sendKeys(value);
    }
}
