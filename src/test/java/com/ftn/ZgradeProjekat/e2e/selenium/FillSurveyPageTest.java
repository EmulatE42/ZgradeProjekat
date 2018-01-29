package com.ftn.ZgradeProjekat.e2e.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by EmulatE on 29-Jan-18.
 */
public class FillSurveyPageTest {

    private WebDriver browser;

    LoginPage loginPage;

    FillSurveyPage fillSurveyPage;

    @BeforeMethod
    public void setupSelenium() {
        //instantiate browser
        System.setProperty("webdriver.chrome.driver", "C:/javatools/selenium-chrome-driver/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:4200");


        loginPage = PageFactory.initElements(browser, LoginPage.class);
        fillSurveyPage = PageFactory.initElements(browser, FillSurveyPage.class);

    }

    @Test
    public void testSizeOfTableRows() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("aaa");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("aaa");
        loginPage.getButton().click();

        fillSurveyPage.ensureIsDisplayed();
        fillSurveyPage.getSurveyLink().click();

        assertEquals("http://localhost:4200/surveys", browser.getCurrentUrl());
    }


    @Test
    public void testAddSurvey() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("aaa");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("aaa");
        loginPage.getButton().click();

        fillSurveyPage.ensureIsDisplayed();
        fillSurveyPage.getSurveyLink().click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fillSurveyPage.getKreni().click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fillSurveyPage.getZavrsi().click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("http://localhost:4200/surveys", browser.getCurrentUrl());
    }
}
