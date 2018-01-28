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
public class AddResponsiblePersonPage
{
    private WebDriver driver;

    @FindBy(id="exampleSelect1")
    private WebElement selectType;

    @FindBy(id="exampleSelect2")
    private WebElement selectTenant;

    @FindBy(id="exampleSelect3")
    private WebElement selectInstitution;

    @FindBy(id="description")
    private WebElement inputDescription;

    @FindBy(id="addResponsilbePersonButton")
    private WebElement addResponsilbePersonButton;

    @FindBy(id="Logout1")
    private WebElement logout;

    public AddResponsiblePersonPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(addResponsilbePersonButton));
    }

    public void setInputDescription(String value) {
        WebElement el = getInputDescription();
        el.clear();
        el.sendKeys(value);
    }
}
