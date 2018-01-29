package com.ftn.ZgradeProjekat.e2e.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by EmulatE on 29-Jan-18.
 */
public class PublicNotificationPageTest {

    private WebDriver browser;

    LoginPage loginPage;

    PublicNotificationPage publicNotificationPage;



    @BeforeMethod
    public void setupSelenium() {
        //instantiate browser
        System.setProperty("webdriver.chrome.driver", "C:/javatools/selenium-chrome-driver/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:4200");


        loginPage = PageFactory.initElements(browser, LoginPage.class);
        publicNotificationPage = PageFactory.initElements(browser, PublicNotificationPage.class);

    }

    @Test
    public void testSizeOfTableRows() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("aaa");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("aaa");
        loginPage.getButton().click();

        publicNotificationPage.ensureIsDisplayed();
        publicNotificationPage.getNotificationLink().click();

        assertEquals("http://localhost:4200/publicNotifications", browser.getCurrentUrl());
    }



    @Test
    public void testAddNotification() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("aaa");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("aaa");
        loginPage.getButton().click();

        publicNotificationPage.ensureIsDisplayed();
        publicNotificationPage.getNotificationLink().click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publicNotificationPage.getUlaz().clear();
        publicNotificationPage.getUlaz().sendKeys("NOVI NASLOV :D");

        publicNotificationPage.getGotov().click();

        List<WebElement> TRcount = browser.findElements(By.tagName("tr"));

        assertEquals(true,TRcount.size() > 1);
    }
}

