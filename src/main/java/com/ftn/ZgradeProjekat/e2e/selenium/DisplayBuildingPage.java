package com.ftn.ZgradeProjekat.e2e.selenium;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by djuro on 1/26/2018.
 */
@Getter
public class DisplayBuildingPage
{
    private WebDriver driver;

    @FindBy(id="-1")
    private WebElement openButton;

    @FindBy(id="delete-2")
    private WebElement deleteButton;

    @FindBy(id="addButton2")
    private WebElement addButton;

    @FindBy(id="addResponsiblePerson")
    private WebElement addResponsiblePersonButton;

    @FindBy(id="showResponsiblePeople")
    private WebElement showResponsiblePeopleButton;

    @FindBy(id="setBuildingManager")
    private WebElement setBuildingManagerButton;

    public DisplayBuildingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(addButton));
    }

    public void ensureIsOpenButonDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("-1")));
    }
}
