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
public class AddResponsiblePersonPageTest
{
    private WebDriver browser;

    AdminPage adminPage;
    LoginPage loginPage;
    DisplayBuildingPage displayBuildingPage;
    AddResponsiblePersonPage addResponsiblePersonPage;
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
        addResponsiblePersonPage = PageFactory.initElements(browser, AddResponsiblePersonPage.class);
        showAllResponsiblePeoplePage = PageFactory.initElements(browser, ShowAllResponsiblePeoplePage.class);
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
        displayBuildingPage.getShowResponsiblePeopleButton().click();

        showAllResponsiblePeoplePage.ensureIsDisplayed();
        int noOfTableRows = browser.findElements(By.cssSelector("tr")).size();
        showAllResponsiblePeoplePage.getLogout().click();

        loginPage.ensureIsDisplayed();

        loginPage.setUsername("a");
        loginPage.setInputPassword("a");
        loginPage.getButton().click();

        adminPage.ensureIsDisplayed();
        adminPage.getOpenButton().click();

        displayBuildingPage.ensureIsDisplayed();
        displayBuildingPage.getAddResponsiblePersonButton().click();

        addResponsiblePersonPage.ensureIsDisplayed();
        Select selector = new Select(addResponsiblePersonPage.getSelectType());
        selector.selectByIndex(0);

        Select selector1 = new Select(addResponsiblePersonPage.getSelectTenant());
        selector1.selectByIndex(1);

        addResponsiblePersonPage.setInputDescription("odrzavanje lifta");
        addResponsiblePersonPage.getAddResponsilbePersonButton().click();

        addResponsiblePersonPage.getLogout().click();

        loginPage.ensureIsDisplayed();

        loginPage.setUsername("a");
        loginPage.setInputPassword("a");
        loginPage.getButton().click();

        adminPage.ensureIsDisplayed();
        adminPage.getOpenButton().click();

        displayBuildingPage.ensureIsDisplayed();
        displayBuildingPage.getShowResponsiblePeopleButton().click();

        showAllResponsiblePeoplePage.ensureIsDisplayed();

        assertEquals(browser.findElements(By.cssSelector("tr")).size(), noOfTableRows+1);
    }


    @AfterMethod
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }

}
