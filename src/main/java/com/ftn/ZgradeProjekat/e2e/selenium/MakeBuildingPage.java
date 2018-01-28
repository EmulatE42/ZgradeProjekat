package com.ftn.ZgradeProjekat.e2e.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by djuro on 1/26/2018.
 */
@Getter
public class MakeBuildingPage
{
    private WebDriver driver;

    @FindBy(id="exampleInput")
    private WebElement inputDate;

    @FindBy(id="inputDefault1")
    private WebElement inputCity;

    @FindBy(id="inputDefault2")
    private WebElement inputNumber;

    @FindBy(id="inputDefault3")
    private WebElement inputStreet;

    @FindBy(id="inputDefault4")
    private WebElement postalCode;

    @FindBy(id="inputDefault5")
    private WebElement inputCountry;

    @FindBy(id="addButton1")
    private WebElement addButton;

    public MakeBuildingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(addButton));
    }

    public void setDate(String value) {
        WebElement el = getInputDate();
        el.clear();
        //el.getAttribute("value").;
        el.sendKeys(value);
    }

    public void setCity(String value) {
        WebElement el = getInputCity();
        el.clear();
        el.sendKeys(value);
    }

    public void setNumber(String value) {
        WebElement el = getInputNumber();
        el.clear();
        el.sendKeys(value);
    }

    public void setStreet(String value) {
        WebElement el = getInputStreet();
        el.clear();
        el.sendKeys(value);
    }

    public void setPostalCode(String value) {
        WebElement el = getPostalCode();
        el.clear();
        el.sendKeys(value);
    }

    public void setCountry(String value) {
        WebElement el = getInputCountry();
        el.clear();
        el.sendKeys(value);
    }
}

