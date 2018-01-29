package com.ftn.ZgradeProjekat.e2e.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by djuro on 1/27/2018.
 */
public class LoginPageTest
{
    private WebDriver browser;

    LoginPage loginPage;
    AdminPage adminPage;

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
    public void testLogin() {
        loginPage.ensureIsDisplayed();

        loginPage.setUsername("a");
        loginPage.setInputPassword("a");
        loginPage.getButton().click();

        adminPage.ensureIsDisplayed();
        assertEquals("http://localhost:4200/adminPage", browser.getCurrentUrl());
    }

    @AfterMethod
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }
}
