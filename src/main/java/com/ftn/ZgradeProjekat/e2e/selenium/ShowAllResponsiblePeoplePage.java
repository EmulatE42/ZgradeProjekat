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
public class ShowAllResponsiblePeoplePage
{
    private WebDriver driver;

    @FindBy(id="-1")
    private WebElement deleteButton;

    @FindBy(id="Logout1")
    private WebElement logout;

    public ShowAllResponsiblePeoplePage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("showAllResponsiblePeople")));
    }
}
