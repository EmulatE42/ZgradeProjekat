package com.ftn.ZgradeProjekat.e2e.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by Momir on 1/25/2018.
 */
public class ParlamentPageTest {

    private WebDriver browser;

    ParlamentPage parlamentPage;
    LoginPage loginPage;

    @BeforeMethod
    public void setupSelenium() {
        //instantiate browser
        System.setProperty("webdriver.chrome.driver", "C:/javatools/selenium-chrome-driver/chromedriver.exe");
        browser = new ChromeDriver();
        //maximize window
        //browser.manage().window().maximize();
        //navigate
        browser.get("http://localhost:4200");


        loginPage = PageFactory.initElements(browser, LoginPage.class);
    }

    @Test
    public void testAddStudent() {
        // get menu element
        System.out.println("Pre");
        //loginPage.ensureIsDisplayed();
        System.out.println("Posle");

        loginPage.getInputUsername().clear();
        loginPage.getInputUsername().sendKeys("aaa");
        loginPage.getInputPassword().clear();
        loginPage.getInputPassword().sendKeys("aaa");
        loginPage.getButton().click();

        browser.navigate().to("http://localhost:4200/parlaments");
        parlamentPage = PageFactory.initElements(browser, ParlamentPage.class);

        //parlamentPage.ensureIsDisplayed();
        parlamentPage.getButtonToSessions().click();



        assertEquals("http://localhost:8080/parlaments/-1/sessions", browser.getCurrentUrl());

    }
}
