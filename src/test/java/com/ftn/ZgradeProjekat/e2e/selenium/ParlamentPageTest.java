package com.ftn.ZgradeProjekat.e2e.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Momir on 1/25/2018.
 */
public class ParlamentPageTest {

    private WebDriver browser;

    ParlamentPage parlamentPage;
    LoginPage loginPage;
    TenantPage tenantPage;

    @BeforeMethod
    public void setupSelenium() {
        //instantiate browser
        System.setProperty("webdriver.chrome.driver", "C:/javatools/selenium-chrome-driver/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:4200");


        loginPage = PageFactory.initElements(browser, LoginPage.class);
        parlamentPage = PageFactory.initElements(browser, ParlamentPage.class);
        tenantPage = PageFactory.initElements(browser, TenantPage.class);
    }

    @Test
    public void testSwitchOnSessionPage() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("aaa");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("aaa");
        loginPage.getButton().click();

        tenantPage.ensureIsDisplayed();
        tenantPage.getParlamentViewLink().click();

        parlamentPage.ensureIsDisplayed();
        parlamentPage.getButtonToSessions().click();

        assertEquals("http://localhost:4200/parlament/-1/sessions", browser.getCurrentUrl());
    }

    @AfterMethod
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }
}
