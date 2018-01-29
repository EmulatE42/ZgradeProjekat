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
 * Created by Momir on 1/27/2018.
 */
public class AddSessionTest {

    private WebDriver browser;

    SessionPage sessionPage;
    LoginPage loginPage;
    AddSessionPage addSessionPage;
    TenantPage tenantPage;
    ParlamentPage parlamentPage;

    @BeforeMethod
    public void setupSelenium() {
        //instantiate browser
        System.setProperty("webdriver.chrome.driver", "C:/javatools/selenium-chrome-driver/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:4200");


        loginPage = PageFactory.initElements(browser, LoginPage.class);
        sessionPage = PageFactory.initElements(browser, SessionPage.class);
        addSessionPage = PageFactory.initElements(browser, AddSessionPage.class);
        tenantPage = PageFactory.initElements(browser, TenantPage.class);
        parlamentPage = PageFactory.initElements(browser, ParlamentPage.class);

    }

    @Test
    public void testAddSession() {
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
        int noOfTableRows = browser.findElements(By.cssSelector("tr")).size();
        assertEquals("http://localhost:4200/parlament/-1/sessions", browser.getCurrentUrl());
        sessionPage.getButtonToNewSession().click();


        addSessionPage.ensureIsDisplayed();
        assertEquals("http://localhost:4200/parlament/-1/add_session", browser.getCurrentUrl());

        assertTrue(addSessionPage.getInputDate().isDisplayed());
        assertTrue(addSessionPage.getInputTime().isDisplayed());
        assertTrue(addSessionPage.getInputDescription().isDisplayed());

        addSessionPage.setInputDate("29/01/2018");
        addSessionPage.setInputTime("21:00");
        addSessionPage.setInputDescription("Tacka dnevnog reda!");
        addSessionPage.getAddTopic().click();

        addSessionPage.getAddSession().click();

        (new WebDriverWait(browser, 10))
                .until(ExpectedConditions.numberOfElementsToBe(
                        By.cssSelector("tr"), noOfTableRows + 1));

        assertEquals("http://localhost:4200/parlament/-1/sessions", browser.getCurrentUrl());

    }
}
