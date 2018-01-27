package com.ftn.ZgradeProjekat.e2e.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
public class SetBuildingManagerPagerTest
{
    private WebDriver browser;

    LoginPage loginPage;
    AdminPage adminPage;
    DisplayBuildingPage displayBuildingPage;
    SetBuildingMangerPage setBuildingMangerPage;

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
        setBuildingMangerPage = PageFactory.initElements(browser, SetBuildingMangerPage.class);
    }

    @Test
    public void testSaveNewResponsiblePerson() {
        loginPage.ensureIsDisplayed();

        loginPage.setUsername("a");
        loginPage.setInputPassword("a");
        loginPage.getButton().click();

        adminPage.ensureIsDisplayed();
        adminPage.getOpenButton().click();

        displayBuildingPage.ensureIsDisplayed();
        displayBuildingPage.getSetBuildingManagerButton().click();

        setBuildingMangerPage.ensureIsDisplayed();
        Select selector = new Select(setBuildingMangerPage.getSelectBuildingManager());
        selector.selectByIndex(0);
        String name = selector.getFirstSelectedOption().getText();
        setBuildingMangerPage.getSaveBuildingManagerButton().click();

        setBuildingMangerPage.ensureCurrentManagerIsDisplayed();

        String text = setBuildingMangerPage.getCurrentManger().getAttribute("value");

        assertEquals(text, name.split("--")[0]);
    }

    @AfterMethod
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }
}
