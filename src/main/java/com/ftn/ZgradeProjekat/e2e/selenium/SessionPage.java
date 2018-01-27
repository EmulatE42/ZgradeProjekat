package com.ftn.ZgradeProjekat.e2e.selenium;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Momir on 1/27/2018.
 */
@Getter
public class SessionPage {

    private WebDriver driver;

    @FindBy(id="newSession")
    private WebElement buttonToNewSession;


    public SessionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(buttonToNewSession));
    }
}
