package com.ftn.ZgradeProjekat.e2e.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by EmulatE on 29-Jan-18.
 */
public class LogoutPageTest {

    private WebDriver browser;


    LoginPage loginPage;
    LogoutPage logoutPage;

    @BeforeMethod
    public void setupSelenium() {
        //instantiate browser
        System.setProperty("webdriver.chrome.driver", "C:/javatools/selenium-chrome-driver/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:4200");


        loginPage = PageFactory.initElements(browser, LoginPage.class);
        logoutPage = PageFactory.initElements(browser, LogoutPage.class);
    }


    @Test
    public void testLogout() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("a");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("a");
        loginPage.getButton().click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logoutPage.getLogoutLink().click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logoutPage.ensureIsDisplayed();



        assertEquals("http://localhost:4200/", browser.getCurrentUrl());

    }
}
