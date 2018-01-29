package com.ftn.ZgradeProjekat.e2e.selenium;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by EmulatE on 26-Jan-18.
 */

@Getter
public class FillSurveyPage {

    private WebDriver driver;

    @FindBy(linkText = "Surveys")
    private WebElement surveyLink;

    @FindBy(className = "ha1")
    private WebElement kreni;

    @FindBy(id = "filled")
    private WebElement zavrsi;

    public FillSurveyPage(WebDriver driver) {this.driver = driver;}

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Surveys")));
    }

}
