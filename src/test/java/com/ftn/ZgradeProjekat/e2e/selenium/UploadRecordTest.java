package com.ftn.ZgradeProjekat.e2e.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Momir on 1/28/2018.
 */
public class UploadRecordTest {

    private WebDriver browser;

    SessionPage sessionPage;
    LoginPage loginPage;
    TenantPage tenantPage;
    ParlamentPage parlamentPage;
    UploadRecordPage uploadRecordPage;

    @BeforeMethod
    public void setupSelenium() {
        //instantiate browser
        System.setProperty("webdriver.chrome.driver", "C:/javatools/selenium-chrome-driver/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:4200");


        loginPage = PageFactory.initElements(browser, LoginPage.class);
        sessionPage = PageFactory.initElements(browser, SessionPage.class);
        tenantPage = PageFactory.initElements(browser, TenantPage.class);
        parlamentPage = PageFactory.initElements(browser, ParlamentPage.class);
        uploadRecordPage = PageFactory.initElements(browser, UploadRecordPage.class);

    }

    @Test
    public void testUpdateRecord() {
        loginPage.ensureIsDisplayed();

        assertTrue(loginPage.getInputPassword().isDisplayed());
        assertTrue(loginPage.getInputUsername().isDisplayed());

        loginPage.setUsername("aaa");
        loginPage.setInputPassword("aaa");
        loginPage.getButton().click();

        tenantPage.ensureIsDisplayed();
        assertEquals("http://localhost:4200/tenantPageComponent", browser.getCurrentUrl());
        tenantPage.getParlamentViewLink().click();


        parlamentPage.ensureIsDisplayed();
        assertEquals("http://localhost:4200/parlaments", browser.getCurrentUrl());
        parlamentPage.getButtonToSessions().click();


        sessionPage.ensureIsDisplayed();
        assertEquals("http://localhost:4200/parlament/-1/sessions", browser.getCurrentUrl());
        sessionPage.getButtonToUploadRecord().click();


        uploadRecordPage.ensureIsDisplayed();

        assertTrue(uploadRecordPage.getInputUrl().isDisplayed());
        assertTrue(uploadRecordPage.getButtonToUploadRecord().isDisplayed());
        assertTrue(uploadRecordPage.getCloseButton().isDisplayed());

        uploadRecordPage.setInputUrl("https://docs.google.com/document/d/1LG2YtbGc_R5C3tcXuRIcGYEV3GxQwnbRXZAWDjyJzls/edit");
        uploadRecordPage.getButtonToUploadRecord().click();

        sessionPage.ensureIsDisplayed();
        assertEquals("http://localhost:4200/parlament/-1/sessions", browser.getCurrentUrl());

    }

}
