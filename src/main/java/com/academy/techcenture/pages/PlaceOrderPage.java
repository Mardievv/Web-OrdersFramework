package com.academy.techcenture.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.academy.techcenture.config.ConfigReader.setProperties;

public class PlaceOrderPage extends BasePage{


    public PlaceOrderPage(WebDriver driver, SoftAssert softAssert) {
        super(driver, softAssert);
    }


    @FindBy(xpath = "//h3[contains(text(),'Product Information')]")
    private WebElement productInfo;

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    private WebElement productsDropDown;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    private WebElement quantityInput;

    @FindBy(xpath = "//li/input[@class='btn_dark']")
    private WebElement calculateBtn;



    @FindBy(xpath = "//h3[contains(text(),'Address Information')]")
    private WebElement addressInfo;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
    private WebElement nameInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
    private WebElement streetInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
    private WebElement cityInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
    private WebElement stateInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
    private WebElement zipInput;

    @FindBy(xpath = "//h3[contains(text(),'Payment Information')]")
    private WebElement paymentInfo;

    @FindBy(id = "ctl00_MainContent_fmwOrder_cardList_2")
    private WebElement card;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    private WebElement cardNumberInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    private WebElement expDate;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    private WebElement insertBtn;

    @FindBy(xpath = "//div/input[@class='btn_dark']")
    private WebElement resetBtn;

    @FindBy(tagName = "strong")
    private WebElement confirmationMsg;


    public void placeNewOrder(HashMap<String,String> data) {
        fillOutProductInformation(data);
        fillOutAddressInformation(data);
        fillOutPaymentInformation(data);

        softAssert.assertTrue(insertBtn.isEnabled() && resetBtn.isEnabled(), "PROCESS OR RESET BUTTON IS NOT ENABLED");
        insertBtn.click();

        softAssert.assertEquals(confirmationMsg.getText().trim(),data.get("SuccessMessage"), "CONFIRMATION MESSAGE DOES NOT MATCH");

        SimpleDateFormat gmDateFormat = new SimpleDateFormat("MM/DD/YYYY");
        gmDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String currentDate = gmDateFormat.format(new Date());
        data.put("Date", currentDate);

    }


    /**
     * This method will fill out Product Information
     */
    private void fillOutProductInformation(HashMap<String,String> data){

        faker = new Faker(new Locale("en-us"));
        Select selectProduct = new Select(productsDropDown);
        selectProduct.selectByVisibleText(data.get("Product"));

        String quantity = data.put("#", (int)Double.parseDouble(data.get("#"))+"");
        quantityInput.sendKeys(quantity);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String currentGMTDate = dateFormat.format(new Date());
        setProperties("date", currentGMTDate);



        softAssert.assertTrue(calculateBtn.isEnabled() && calculateBtn.isDisplayed(), "CALCULATE BUTTON IS NOT ENABLED");
        calculateBtn.click();

    }


    /**
     * This method will fill out Address Information
     */
    private void fillOutAddressInformation(HashMap<String,String> data){

        String expectedAddressInfo = "Address Information";
        softAssert.assertEquals(addressInfo.getText().trim(), expectedAddressInfo,"ADDRESS INFORMATION DO NOT MATCH");

        nameInput.sendKeys(data.get("Name"));

        streetInput.sendKeys(data.get("Street"));

        cityInput.sendKeys(data.get("City"));

        stateInput.sendKeys(data.get("State"));

        data.put("Zip", (int)Double.parseDouble(data.get("Zip")) + "");
        zipInput.sendKeys(data.get("Zip"));

    }


    /**
     * This method will fill out Payment Information
     */
    private void fillOutPaymentInformation(HashMap<String,String> data) {

        String expectedPaymentInformation = "Payment Information";
        softAssert.assertEquals(paymentInfo.getText().trim(), expectedPaymentInformation,"PAYMENT INFORMATION DO NOT MATCH");

        WebElement card = driver.findElement(By.xpath("//input[@value='"+data.get("Card")+"']"));
        card.click();

        cardNumberInput.sendKeys(data.get("Card Number"));

        expDate.sendKeys(data.get("Exp"));

    }


    public void verifyNewOrder(HashMap<String,String> data){

        //   Name,      Product,   #,    Data,       Street,          City,      State,   Zip,    Card,    Card Number,    Exp
        List<WebElement> columns = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[1]/th"));
        // Kevin Lee,   MyMoney,   11,   10/10/2010, 123 Main Street, Franklin,  TN,      37067    Visa   1237462884483    12/23
        List<WebElement> firstRow = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td"));

        for (int i = 1; i < columns.size()-1; i++) {
            String columnName = columns.get(i).getText().trim();
            String expectedColumnData = data.get(columnName);
            String actualColumnData = firstRow.get(i).getText();
            softAssert.assertEquals(actualColumnData, expectedColumnData,columnName+" NOT MATCHING");
        }
    }

}
