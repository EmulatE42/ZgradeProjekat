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
public class LocationPage
{
    private WebDriver driver;

    @FindBy(id="saveButton")
    private WebElement saveButton;

    @FindBy(id="addNewTenant")
    private WebElement addNewTenantButton;

    @FindBy(id="exampleSelect1")
    private WebElement selectOwner;

    @FindBy(id="exampleSelect2")
    private WebElement currentOwner;

    public LocationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(saveButton));
    }


}
