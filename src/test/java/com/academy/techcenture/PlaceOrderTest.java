package com.academy.techcenture;

import com.academy.techcenture.pages.*;
import com.academy.techcenture.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;


public class PlaceOrderTest extends BaseTest {


    @Test(dataProvider = "webOrders")
    public void PlaceAnOrderTest(HashMap<String,String> data) {
        LoginPage loginPage = new LoginPage(driver,softAssert);
        OrdersPage ordersPage = new OrdersPage(driver,softAssert);
        PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver,softAssert);

        loginPage.navigateToLoginPage();
        loginPage.login();

        ordersPage.clickOnViewAllOrders();
        ordersPage.checkAllOrders();

        placeOrderPage.clickOnOrders();
        placeOrderPage.placeNewOrder(data);

        ordersPage.clickOnViewAllOrders();
        placeOrderPage.verifyNewOrder(data);

    }


    @DataProvider(name = "webOrders")
    public Object[][] getWebOrdersData(){
        ExcelReader excelReader = new ExcelReader("src/main/resources/testData/Data.xlsx", "orders");
        Object[][] data = excelReader.getData();
        return data;
    }


}
