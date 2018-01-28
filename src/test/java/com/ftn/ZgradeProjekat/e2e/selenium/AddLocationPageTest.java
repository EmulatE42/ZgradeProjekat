package com.ftn.ZgradeProjekat.e2e.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by djuro on 1/27/2018.
 */
public class AddLocationPageTest
{
    private WebDriver browser;

    AdminPage adminPage;
    LoginPage loginPage;
    DisplayBuildingPage displayBuildingPage;
    AddLocationPage addLocationPage;

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
        addLocationPage = PageFactory.initElements(browser, AddLocationPage.class);
    }

    @Test
    public void testAddNewBuilding() {
        loginPage.ensureIsDisplayed();

        loginPage.setUsername("a");
        loginPage.setInputPassword("a");
        loginPage.getButton().click();

        adminPage.ensureIsDisplayed();
        adminPage.getOpenButton().click();

        displayBuildingPage.ensureIsDisplayed();
        displayBuildingPage.getAddButton().click();

        addLocationPage.ensureIsDisplayed();
        Select selector = new Select(addLocationPage.getSelectType());
        selector.selectByIndex(0);

        addLocationPage.setInputFloor("4");
        addLocationPage.setInputSquare("100");
        addLocationPage.getAddLocationButton().click();
        displayBuildingPage.ensureIsDisplayed();
        assertEquals("http://localhost:4200/displayBuilding/-2", browser.getCurrentUrl());
    }

    @AfterMethod
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }
}
