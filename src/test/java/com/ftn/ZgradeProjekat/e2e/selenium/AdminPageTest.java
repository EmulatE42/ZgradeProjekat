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
public class AdminPageTest
{
    private WebDriver browser;

    AdminPage adminPage;
    LoginPage loginPage;

    @BeforeMethod
    public void setupSelenium() {
        //instantiate browser
        System.setProperty("webdriver.chrome.driver", "C:/javatools/selenium-chrome-driver/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:4200");


        loginPage = PageFactory.initElements(browser, LoginPage.class);
        adminPage = PageFactory.initElements(browser, AdminPage.class);
    }

    @Test
    public void testAddNewBuilding() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("a");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("a");
        loginPage.getButton().click();

        adminPage.ensureIsDisplayed();
        adminPage.getAddButton().click();

        assertEquals("http://localhost:4200/makeBuilding", browser.getCurrentUrl());
    }

    @Test
    public void testOpenBuildingInformation() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("a");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("a");
        loginPage.getButton().click();

        adminPage.ensureIsDisplayed();
        adminPage.getOpenButton().click();

        assertEquals("http://localhost:4200/displayBuilding/-2", browser.getCurrentUrl());
    }

    @Test
    public void testDeleteBuilding() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("a");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("a");
        loginPage.getButton().click();

        adminPage.ensureIsDisplayed();
        adminPage.getDeleteButton().click();

        int noOfTableRows = browser.findElements(By.cssSelector("tr")).size();
        assertEquals(noOfTableRows , 3);
    }

    @AfterMethod
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }
}
