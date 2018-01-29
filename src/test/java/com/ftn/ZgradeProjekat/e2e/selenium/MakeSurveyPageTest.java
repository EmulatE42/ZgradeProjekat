package com.ftn.ZgradeProjekat.e2e.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by EmulatE on 27-Jan-18.
 */
public class MakeSurveyPageTest {

    private WebDriver browser;

    LoginPage loginPage;
    MakeSurveyPage makeSurveyPage;


    @BeforeMethod
    public void setupSelenium() {
        //instantiate browser
        System.setProperty("webdriver.chrome.driver", "C:/javatools/selenium-chrome-driver/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:4200");


        loginPage = PageFactory.initElements(browser, LoginPage.class);
        makeSurveyPage = PageFactory.initElements(browser, MakeSurveyPage.class);
    }



    @Test
    public void testSizeOfTableRows() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("aaa");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("aaa");
        loginPage.getButton().click();

        makeSurveyPage.ensureIsDisplayed();
        makeSurveyPage.getMakeSurveyLink().click();

        assertEquals("http://localhost:4200/makeSurvey", browser.getCurrentUrl());
    }

    @Test
    public void testAddSurvey() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("aaa");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("aaa");
        loginPage.getButton().click();

        makeSurveyPage.ensureIsDisplayed();
        makeSurveyPage.getMakeSurveyLink().click();

        makeSurveyPage.getTittleOfSurvey().clear();
        makeSurveyPage.getTittleOfSurvey().sendKeys("Naslov nekimmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
        makeSurveyPage.getAddquestion().click();

        makeSurveyPage.getText().clear();
        makeSurveyPage.getText().sendKeys("Naslov nekimmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");

        makeSurveyPage.getEnd().click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("http://localhost:4200/tenantPageComponent", browser.getCurrentUrl());
    }



    @AfterMethod
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }

}







