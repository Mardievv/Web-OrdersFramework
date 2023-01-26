package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import static com.academy.techcenture.config.ConfigReader.getProperties;

public class LoginPage {

    private final WebDriver driver;

    private SoftAssert softAssert;

    public LoginPage(WebDriver driver, SoftAssert softAssert){
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "ctl00_MainContent_username")
    private WebElement usernameInput;

    @FindBy(id = "ctl00_MainContent_password")
    private WebElement passwordInput;

    @FindBy(id = "ctl00_MainContent_login_button")
    private WebElement loginBtn;


    @FindBy(id = "ctl00_MainContent_status")
    private WebElement errorMessage;

//    @FindBy(id = "")
//    private WebElement


    /**
     * This method navigates driver to Login Page and checks if the titles is correct
     */
    public void navigateToLoginPage(){
        driver.get(getProperties("url"));
        softAssert.assertEquals(driver.getTitle().trim(),"Web Orders Login", "LOGIN TITLES DO NOT MATCH");

    }


    /**
     * This method will enter credentials to username and password and click the login button
     */
    public void login(){
        enterUserName(getProperties("username"));
        enterPassword(getProperties("password"));
        clickOnLoginButton();
    }


    /**
     * This method will enter wrong credentials and check if the error message displays or not
     */
    public void loginNegative(){
        enterUserName("Tester");
        enterPassword("abc123");
        clickOnLoginButton();
        softAssert.assertTrue(errorMessage.isDisplayed(),"ERROR MESSAGE IS NOT DISPLAYED");
    }


    /**
     * This method clicks Login Button
     */
    private void  clickOnLoginButton(){
        softAssert.assertTrue(loginBtn.isDisplayed() && loginBtn.isEnabled(),"LOGIN BUTTON IS NOT ENABLED");
        loginBtn.click();
    }

    private void enterUserName(String username){
         usernameInput.clear();
         usernameInput.sendKeys(username);
    }

    private void enterPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }






}
