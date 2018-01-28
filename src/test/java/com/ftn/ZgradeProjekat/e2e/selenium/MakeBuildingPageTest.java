package com.ftn.ZgradeProjekat.e2e.selenium;

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
public class MakeBuildingPageTest
{
    private WebDriver browser;

    AdminPage adminPage;
    LoginPage loginPage;
    MakeBuildingPage makeBuildingPage;

    @BeforeMethod
    public void setupSelenium() {
        //instantiate browser
        System.setProperty("webdriver.chrome.driver", "C:/javatools/selenium-chrome-driver/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:4200");


        loginPage = PageFactory.initElements(browser, LoginPage.class);
        adminPage = PageFactory.initElements(browser, AdminPage.class);
        makeBuildingPage = PageFactory.initElements(browser, MakeBuildingPage.class);
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


        makeBuildingPage.ensureIsDisplayed();

        //makeBuildingPage.setDate("20-Aug-1985");
        makeBuildingPage.setNumber("5");
        makeBuildingPage.setCity("Novi Sad");
        makeBuildingPage.setCountry("Srbija");
        makeBuildingPage.setPostalCode("21000");
        makeBuildingPage.setStreet("Gogoljeva");

        makeBuildingPage.getAddButton().click();
        adminPage.ensureIsDisplayed();
        assertEquals("http://localhost:4200/adminPage", browser.getCurrentUrl());

    }

    @AfterMethod
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }
}
