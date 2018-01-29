package com.ftn.ZgradeProjekat.e2e.selenium;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by djuro on 1/27/2018.
 */
@Getter
public class SetBuildingMangerPage
{
    private WebDriver driver;

    @FindBy(id="saveBuildingManager")
    private WebElement saveBuildingManagerButton;

    @FindBy(id="disabledInput3")
    private WebElement currentManger;

    @FindBy(id="exampleSelect2")
    private WebElement selectBuildingManager;

    public SetBuildingMangerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(saveBuildingManagerButton));
    }

    public void ensureCurrentManagerIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(currentManger));
    }
}
