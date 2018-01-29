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
 * Created by djuro on 1/26/2018.
 */
public class NewUserPageTest
{
    private WebDriver browser;

    LoginPage loginPage;
    AdminPage adminPage;
    DisplayBuildingPage displayBuildingPage;
    LocationPage locationPage;
    NewUserPage newUserPage;
    TenantPage tenantPage;

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
        newUserPage = PageFactory.initElements(browser, NewUserPage.class);
        tenantPage = PageFactory.initElements(browser, TenantPage.class);
    }

    @Test
    public void testRegisterUser() {
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

        newUserPage.ensureIsDisplayed();
        Select selector = new Select(locationPage.getSelectOwner());
        selector.selectByIndex(3);

        newUserPage.setUsername("abcabc");
        newUserPage.setPassword("abcabc");
        newUserPage.setFirstname("abcabc");
        newUserPage.setLastname("abcabc");
        newUserPage.getRegisterButton().click();
        newUserPage.getLogout().click();

        loginPage.ensureIsDisplayed();
        loginPage.setUsername("abcabc");
        loginPage.setInputPassword("abcabc");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {}
        loginPage.getButton().click();
        tenantPage.ensureIsDisplayed();
        assertEquals("http://localhost:4200/tenantPageComponent", browser.getCurrentUrl());

    }

    @AfterMethod
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }
}
