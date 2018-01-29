package com.ftn.ZgradeProjekat.e2e.selenium;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by EmulatE on 29-Jan-18.
 */
@Getter
public class MakeSurveyPage {
    private WebDriver driver;

    @FindBy(linkText = "Make survey")
    private WebElement makeSurveyLink;

    @FindBy(id = "title")
    private WebElement tittleOfSurvey;

    @FindBy(id = "dodavanje")
    private WebElement addquestion;

    @FindBy(id = "qt")
    private WebElement text;

    @FindBy(id = "kraj")
    private WebElement end;



    public MakeSurveyPage(WebDriver driver) {this.driver = driver;}

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Make survey")));
    }
}


