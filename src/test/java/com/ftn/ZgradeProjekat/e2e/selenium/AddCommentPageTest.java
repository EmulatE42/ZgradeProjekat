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
public class AddCommentPageTest {

    private WebDriver browser;

    LoginPage loginPage;
    DisplayBugsPage displayBugsPage;
    AddCommentPage addCommentPage;

    @BeforeMethod
    public void setupSelenium() {
        //instantiate browser
        System.setProperty("webdriver.chrome.driver", "C:/javatools/selenium-chrome-driver/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.navigate().to("http://localhost:4200");


        loginPage = PageFactory.initElements(browser, LoginPage.class);
        displayBugsPage = PageFactory.initElements(browser, DisplayBugsPage.class);
        addCommentPage = PageFactory.initElements(browser, AddCommentPage.class);
    }

    @Test
    public void testAddComment() {
        loginPage.ensureIsDisplayed();

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("aaa");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("aaa");
        loginPage.getButton().click();

        displayBugsPage.ensureIsDisplayed();
        displayBugsPage.getDugme().click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addCommentPage.getOpen().click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addCommentPage.getUnos().clear();
        addCommentPage.getUnos().sendKeys("Novi kom");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addCommentPage.getGotov().click();

        List<WebElement> TRcount = browser.findElements(By.tagName("tr"));

        assertEquals(true,TRcount.size() > 1);
    }
}
