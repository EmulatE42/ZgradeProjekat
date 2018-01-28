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
public class AdminPage
{
    private WebDriver driver;

    @FindBy(id="-2")
    private WebElement openButton;

    @FindBy(id="delete-1")
    private WebElement deleteButton;

    @FindBy(id="addButton")
    private WebElement addButton;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(addButton));
        //.until(ExpectedConditions.presenceOfElementLocated(
        //By.id("loginButton")));
    }


    public void ensureIsOpenButonDisplayed() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("-2")));
    }
}
