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
public class PositiveVotingTest {

    private WebDriver browser;

    SessionPage sessionPage;
    LoginPage loginPage;
    TenantPage tenantPage;
    ParlamentPage parlamentPage;
    AddTopicPage addTopicPage;

    @BeforeMethod
    public void setupSelenium() {

        System.setProperty("webdriver.chrome.driver", "C:/javatools/selenium-chrome-driver/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:4200");


        loginPage = PageFactory.initElements(browser, LoginPage.class);
        sessionPage = PageFactory.initElements(browser, SessionPage.class);
        tenantPage = PageFactory.initElements(browser, TenantPage.class);
        parlamentPage = PageFactory.initElements(browser, ParlamentPage.class);
        addTopicPage = PageFactory.initElements(browser, AddTopicPage.class);

    }

    @Test
    public void testSwitchOnSessionPage() {
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
        sessionPage.getButtonToAddTopic().click();


        addTopicPage.ensureIsDisplayed();
        assertEquals("http://localhost:4200/parlament/-1/session/2/topics", browser.getCurrentUrl());
        assertTrue(addTopicPage.getInputDescription().isDisplayed());
        assertTrue(addTopicPage.getAddTopic().isDisplayed());
        int nbr_of_like = Integer.parseInt(browser.findElement(By.id("like_1")).getText());
        System.out.println("Broj lajkova je : " + nbr_of_like);

        addTopicPage.getLike().click();
        addTopicPage.ensureIsDisplayed();
        assertEquals(Integer.parseInt(browser.findElement(By.id("like_1")).getText()), nbr_of_like + 1);


    }
}
