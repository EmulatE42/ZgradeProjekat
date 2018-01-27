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
public class TenantPageTest
{
    private WebDriver browser;

    LoginPage loginPage;
    TenantPage tenantPage;

    @BeforeMethod
    public void setupSelenium() {
        //instantiate browser
        System.setProperty("webdriver.chrome.driver", "C:/javatools/selenium-chrome-driver/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:4200");


        loginPage = PageFactory.initElements(browser, LoginPage.class);
        tenantPage = PageFactory.initElements(browser, TenantPage.class);
    }

    @Test
    public void testSizeOfTableRows() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("aaa");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("aaa");
        loginPage.getButton().click();

        tenantPage.ensureIsDisplayed();

        //int noOfTableRows = browser.findElements(By.cssSelector("tr")).size();
        //assertEquals(noOfTableRows , 2);
        assertEquals("http://localhost:4200/tenantPageComponent", browser.getCurrentUrl());
    }

    @AfterMethod
    public void closeSelenium() {
        // Shutdown the browser
        browser.quit();
    }
}
