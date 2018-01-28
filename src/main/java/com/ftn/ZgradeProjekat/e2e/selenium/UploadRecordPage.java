package com.ftn.ZgradeProjekat.e2e.selenium;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Momir on 1/28/2018.
 */
@Getter
public class UploadRecordPage {

    private WebDriver driver;

    @FindBy(id="saveRecord")
    private WebElement buttonToUploadRecord;

    @FindBy(id="record")
    private WebElement inputUrl;

    @FindBy(id="close")
    private WebElement closeButton;


    public UploadRecordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(buttonToUploadRecord));
    }

    public void setInputUrl(String value) {
        WebElement el = getInputUrl();
        el.clear();
        el.sendKeys(value);
    }
}
