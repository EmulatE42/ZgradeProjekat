package com.ftn.ZgradeProjekat.e2e.selenium;

import org.openqa.selenium.By;
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
public class ShowAllResponsiblePeoplePageTest
{
    private WebDriver browser;

    LoginPage loginPage;
    AdminPage adminPage;
    DisplayBuildingPage displayBuildingPage;
    ShowAllResponsiblePeoplePage showAllResponsiblePeoplePage;

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
        showAllResponsiblePeoplePage = PageFactory.initElements(browser, ShowAllResponsiblePeoplePage.class);
    }

    @Test
    public void testDeleteResponsiblePerson() {
        loginPage.ensureIsDisplayed();

        loginPage.setUsername("a");
        loginPage.setInputPassword("a");
        loginPage.getButton().click();

        adminPage.ensureIsDisplayed();
        adminPage.getOpenButton().click();

        displayBuildingPage.ensureIsDisplayed();
        displayBuildingPage.getShowResponsiblePeopleButton().click();

        showAllResponsiblePeoplePage.ensureIsDisplayed();
        int noOfTableRows = browser.findElements(By.cssSelector("tr")).size();
        showAllResponsiblePeoplePage.getDeleteButton().click();
        assertEquals(noOfTableRows - 1 , browser.findElements(By.cssSelector("tr")).size());
    }

    @AfterMethod
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }
}
