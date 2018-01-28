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
public class AddTopicPage {

    private WebDriver driver;

    @FindBy(id="description")
    private WebElement inputDescription;

    @FindBy(id="addTopic")
    private WebElement addTopic;

    @FindBy(id="like_1")
    private WebElement like;

    @FindBy(id="dislike_1")
    private WebElement dislike;


    public AddTopicPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(addTopic));
    }

    public void setInputDescription(String value) {
        WebElement el = getInputDescription();
        el.clear();
        el.sendKeys(value);
    }
}
