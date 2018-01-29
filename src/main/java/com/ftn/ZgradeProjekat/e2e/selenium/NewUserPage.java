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
public class NewUserPage
{
    private WebDriver driver;

    @FindBy(id="usr")
    private WebElement inputUsername;

    @FindBy(id="exampleInputPassword1")
    private WebElement iputPassword;

    @FindBy(id="firstname")
    private WebElement iputFirstname;

    @FindBy(id="lastname")
    private WebElement iputLastname;

    @FindBy(id="institutionName")
    private WebElement iputInstitutionName;

    @FindBy(id="firmName")
    private WebElement iputFirmName;

    @FindBy(id="firmDescription")
    private WebElement iputFirmDescription;

    @FindBy(id="inputDefault1")
    private WebElement iputCity;

    @FindBy(id="inputDefault2")
    private WebElement iputBuildingNumber;

    @FindBy(id="inputDefault3")
    private WebElement iputStreet;

    @FindBy(id="inputDefault4")
    private WebElement iputPostalCode;

    @FindBy(id="inputDefault5")
    private WebElement iputCountry;

    @FindBy(id="registerButton")
    private WebElement registerButton;

    @FindBy(id="Logout1")
    private WebElement logout;

    public NewUserPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(registerButton));
    }

    public void setUsername(String value) {
        WebElement el = getInputUsername();
        el.clear();
        el.sendKeys(value);
    }

    public void setPassword(String value) {
        WebElement el = getIputPassword();
        el.clear();
        el.sendKeys(value);
    }

    public void setFirstname(String value) {
        WebElement el = getIputFirstname();
        el.clear();
        el.sendKeys(value);
    }

    public void setLastname(String value) {
        WebElement el = getIputLastname();
        el.clear();
        el.sendKeys(value);
    }
}
