package com.ftn.ZgradeProjekat.e2e.selenium;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Djuric on 1/25/2018.
 */
@Getter
public class LoginPage {

    private WebDriver driver;

    @FindBy(id="usr")
    private WebElement inputUsername;

    @FindBy(id="exampleInputPassword1")
    private WebElement inputPassword;

    @FindBy(id="loginButton")
    private WebElement button;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ensureIsDisplayed() {
        //wait for add button to be present
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(button));
                //.until(ExpectedConditions.presenceOfElementLocated(
                //By.id("loginButton")));
    }

    public void setUsername(String value) {
        WebElement el = getInputUsername();
        el.clear();
        el.sendKeys(value);
    }

    public void setInputPassword(String value) {
        WebElement el = getInputPassword();
        el.clear();
        el.sendKeys(value);
    }

}
