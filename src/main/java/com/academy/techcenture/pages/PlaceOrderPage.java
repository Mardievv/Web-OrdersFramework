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
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static com.academy.techcenture.config.ConfigReader.getProperties;
import static com.academy.techcenture.config.ConfigReader.setProperties;
import static com.academy.techcenture.utils.Utils.generateRandomNumber;
import static com.academy.techcenture.utils.Utils.generateStringNumbers;

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


    public void placeNewOrder(){
        fillOutProductInformation();
        fillOutAddressInformation();
        fillOutPaymentInformation();

        softAssert.assertTrue(insertBtn.isEnabled() && resetBtn.isEnabled(), "PROCESS OR RESET BUTTON IS NOT ENABLED");
        insertBtn.click();

        softAssert.assertEquals(confirmationMsg.getText().trim(),"New order has been successfully added.", "CONFIRMATION MESSAGE DOES NOT MATCH");
    }


    /**
     * This method will fill out Product Information
     */
    private void fillOutProductInformation(){

        faker = new Faker(new Locale("en-us"));
        Select selectProduct = new Select(productsDropDown);
        selectProduct.selectByIndex(generateRandomNumber(0,2));
        String selectedProductText = selectProduct.getFirstSelectedOption().getText();
        setProperties("product", selectedProductText);

        String quantity = generateRandomNumber(5, 10) + "";
        setProperties("#", quantity);
        quantityInput.sendKeys(getProperties("#"));

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
    private void fillOutAddressInformation(){

        String expectedAddressInfo = "Address Information";
        softAssert.assertEquals(addressInfo.getText().trim(), expectedAddressInfo,"ADDRESS INFORMATION DO NOT MATCH");

//      a random fake information
        String fullName = faker.name().fullName();
        setProperties("name", fullName);
        nameInput.sendKeys(getProperties("name"));

        String street = faker.address().streetAddress();
        setProperties("street", street);
        streetInput.sendKeys(getProperties("street"));

        String city = faker.address().city();
        setProperties("city",city);
        cityInput.sendKeys(getProperties("city"));

        String state = faker.address().stateAbbr();
        setProperties("state",state);
        stateInput.sendKeys(getProperties("state"));

        String zip = faker.address().zipCodeByState(state);
        setProperties("zip",zip);
        zipInput.sendKeys(getProperties("zip"));

    }


    /**
     * This method will fill out Payment Information
     */
    private void fillOutPaymentInformation(){

        String expectedPaymentInformation = "Payment Information";
        softAssert.assertEquals(paymentInfo.getText().trim(), expectedPaymentInformation,"PAYMENT INFORMATION DO NOT MATCH");

        String cardNumberId = "ctl00_MainContent_fmwOrder_cardList_"+generateRandomNumber(0,2)+"";
        WebElement card = driver.findElement(By.id(cardNumberId));
        card.click();
        String selectedCard = card.getAttribute("value");
        setProperties("card", selectedCard);

        String randomCardNumber = generateStringNumbers(0, 9, 16);
        setProperties("card number", randomCardNumber);
        cardNumberInput.sendKeys(getProperties("card number"));

        int expMonthNum = generateRandomNumber(1,12);
        String expMont = expMonthNum <= 9 ? "0" + expMonthNum : expMonthNum + "";
        int expYear = generateRandomNumber(23,29);
        setProperties("exp", expMont + "/" + expYear);
        expDate.sendKeys(getProperties("exp"));

    }


    public void verifyNewOrder(){

        //   Name,      Product,   #,    Data,       Street,          City,      State,   Zip,    Card,    Card Number,    Exp
        List<WebElement> columns = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[1]/th"));
        // Kevin Lee,   MyMoney,   11,   10/10/2010, 123 Main Street, Franklin,  TN,      37067    Visa   1237462884483    12/23
        List<WebElement> firstRow = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td"));

        for (int i = 1; i < columns.size()-1; i++) {
            String columnName = columns.get(i).getText().trim().toLowerCase();
            String expectedColumnData = getProperties(columnName);
            String actualColumnData = firstRow.get(i).getText();
            softAssert.assertEquals(actualColumnData, expectedColumnData,"NAMES NOT MATCHING");
        }

    }


}
