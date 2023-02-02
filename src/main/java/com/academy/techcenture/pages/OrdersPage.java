package com.academy.techcenture.pages;

import com.academy.techcenture.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class OrdersPage extends BasePage{

    public OrdersPage(WebDriver driver, SoftAssert softAssert) {
        super(driver,softAssert);
    }

    @FindBy(tagName = "h2")
    private WebElement listOfAllOrdersHeader;

    @FindBy(id = "ctl00_MainContent_btnCheckAll")
    private WebElement checkAllBtn;

    @FindBy(id = "ctl00_MainContent_btnUncheckAll")
    private WebElement uncheckAllBtn;

    @FindBy(id = "ctl00_MainContent_orderGrid")
    private WebElement ordersTable;

    @FindBy(id = "ctl00_MainContent_btnDelete")
    private WebElement deleteSelectedBtn;

    @FindBy(xpath = "//input[starts-with(@id,'ctl00_MainContent_orderGrid_ctl0')]")
    private List<WebElement> ordersInput;




    /**
     * This method will click a random order input and delete selected button and check if the order size is correct.
     */
    public void deleteOrder(){
        int orderSize = ordersInput.size();
        String xpath = "//input[@id='ctl00_MainContent_orderGrid_ctl0" + Utils.generateRandomNumber(2,9) + "_OrderSelector']";
        driver.findElement(By.xpath(xpath)).click();
        softAssert.assertTrue(deleteSelectedBtn.isEnabled(),"DELETE SELECTED BUTTON IS NOT ENABLED");
        deleteSelectedBtn.click();
        softAssert.assertEquals(orderSize - 1, ordersInput.size(), "ORDER WAS NOT DELETED");
    }


    /**
     * This method checks all orders and verifies that all orders are checked out
     */
    public void checkAllOrders(){
        softAssert.assertTrue(checkAllBtn.isDisplayed() && checkAllBtn.isEnabled(), "CHECK ALL BUTTON IS NOT ENABLED");
        checkAllBtn.click();

        for (WebElement order : ordersInput) {
            softAssert.assertTrue(order.isSelected(),"ORDER " + order + "FAILED");
        }
    }


    /**
     * This method unchecks all orders and verifies that all orders are unchecked
     */
    public void uncheckAllOrders(){
        softAssert.assertTrue(uncheckAllBtn.isDisplayed() && uncheckAllBtn.isEnabled(), "UNCHECK ALL BUTTON IS NOT ENABLED");
        uncheckAllBtn.click();

        for (WebElement order : ordersInput) {
            softAssert.assertFalse(order.isSelected(),"ORDER " + order + "FAILED");
        }
    }








}
