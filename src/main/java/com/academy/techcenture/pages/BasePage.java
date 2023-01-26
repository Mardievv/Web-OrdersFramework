package com.academy.techcenture.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;


public class BasePage {

    protected WebDriver driver;

    protected SoftAssert softAssert;

    protected Faker faker;

    public BasePage(WebDriver driver, SoftAssert softAssert){
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(text(),'Welcome, Tester!')]")
    private WebElement welcomeText;

    @FindBy(id = "ctl00_logout")
    private WebElement logoutLink;

    @FindBy(tagName = "h1")
    private WebElement logo;

    @FindBy(xpath = "//ul[@id='ctl00_menu']")
    private WebElement menuItems;


    public void logout(){
        softAssert.assertTrue(logoutLink.isDisplayed(),"LOGOUT LINK IS NOT DISPLAYED");
        logoutLink.click();
    }

    public void clickOnViewAllOrders(){
        WebElement viewAllOrders = menuItems.findElement(By.linkText("View all orders"));
        softAssert.assertTrue(viewAllOrders.isDisplayed(),"VIEW ALL ORDERS LINK IS NOT DISPLAYED");
        viewAllOrders.click();
    }

    public void clickOnViewAllProducts(){
        WebElement viewAllProducts = menuItems.findElement(By.linkText("View all products"));
        softAssert.assertTrue(viewAllProducts.isDisplayed(),"VIEW ALL PRODUCTS LINK IS NOT DISPLAYED");
        viewAllProducts.click();
    }

    public void clickOnOrders(){
        WebElement order = menuItems.findElement(By.linkText("Order"));
        softAssert.assertTrue(order.isDisplayed(),"ORDER LINK IS NOT DISPLAYED");
        order.click();
    }



}
