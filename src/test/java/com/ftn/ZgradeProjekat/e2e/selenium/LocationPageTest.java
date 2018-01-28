package com.ftn.ZgradeProjekat.e2e.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by djuro on 1/26/2018.
 */
public class LocationPageTest
{
    private WebDriver browser;

    AdminPage adminPage;
    LoginPage loginPage;
    DisplayBuildingPage displayBuildingPage;
    LocationPage locationPage;

    @BeforeMethod
    public void setupSelenium() {
        //instantiate browser
        System.setProperty("webdriver.chrome.driver", "C:/javatools/selenium-chrome-driver/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:4200");


        loginPage = PageFactory.initElements(browser, LoginPage.class);
        adminPage = PageFactory.initElements(browser, AdminPage.class);
        displayBuildingPage = PageFactory.initElements(browser, DisplayBuildingPage.class);
        locationPage = PageFactory.initElements(browser, LocationPage.class);
    }

    @Test
    public void testOpenLocation() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("a");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("a");
        loginPage.getButton().click();

        adminPage.ensureIsDisplayed();
        adminPage.ensureIsOpenButonDisplayed();
        adminPage.getOpenButton().click();

        displayBuildingPage.ensureIsDisplayed();
        displayBuildingPage.ensureIsOpenButonDisplayed();
        displayBuildingPage.getOpenButton().click();


        locationPage.ensureIsDisplayed();

        Select Selector = new Select(locationPage.getSelectOwner());
        Selector.selectByIndex(1);

        Select selector2 = new Select(locationPage.getCurrentOwner());

        int numberOfOwner = selector2.getOptions().size();

        locationPage.getSaveButton().click();
        /*
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e) {}
        */
        Select selector1 = new Select(locationPage.getCurrentOwner());

        assertEquals(selector1.getOptions().size(), numberOfOwner+1);
    }

    @Test
    public void testAddNewUser() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("a");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("a");
        loginPage.getButton().click();

        adminPage.ensureIsDisplayed();
        adminPage.ensureIsOpenButonDisplayed();
        adminPage.getOpenButton().click();

        displayBuildingPage.ensureIsDisplayed();
        displayBuildingPage.ensureIsOpenButonDisplayed();
        displayBuildingPage.getOpenButton().click();


        locationPage.ensureIsDisplayed();
        locationPage.getAddNewTenantButton().click();

        assertEquals("http://localhost:4200/addUser", browser.getCurrentUrl());
    }

    @AfterMethod
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }
}
