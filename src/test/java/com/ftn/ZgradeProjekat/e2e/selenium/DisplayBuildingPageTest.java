package com.ftn.ZgradeProjekat.e2e.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by djuro on 1/26/2018.
 */
public class DisplayBuildingPageTest
{
    private WebDriver browser;

    AdminPage adminPage;
    LoginPage loginPage;
    DisplayBuildingPage displayBuildingPage;

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
        assertEquals("http://localhost:4200/location/-1", browser.getCurrentUrl());

    }

    @Test
    public void testDeleteLocation() {
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
        int noOfTableRows = browser.findElements(By.cssSelector("tr")).size();
        displayBuildingPage.getDeleteButton().click();
        assertEquals(noOfTableRows - 1 , browser.findElements(By.cssSelector("tr")).size());
    }

    @Test
    public void testAddNewLocation() {
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
        displayBuildingPage.getAddButton().click();
        assertEquals("http://localhost:4200/addLocation/-2", browser.getCurrentUrl());
    }

    @Test
    public void testAddResponsiblePerson() {
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
        displayBuildingPage.getAddResponsiblePersonButton().click();
        assertEquals("http://localhost:4200/addResposiblePerson/-2", browser.getCurrentUrl());
    }

    @Test
    public void testShowResponsiblePerson() {
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
        displayBuildingPage.getShowResponsiblePeopleButton().click();
        assertEquals("http://localhost:4200/showAllResponsiblePersonsComponent/-2", browser.getCurrentUrl());
    }

    @Test
    public void testSetBuildingManager() {
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
        displayBuildingPage.getSetBuildingManagerButton().click();
        assertEquals("http://localhost:4200/setBuildingManagerComponent/-2", browser.getCurrentUrl());
    }

    @AfterMethod
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }
}
