package com.academy.techcenture;

import com.academy.techcenture.pages.*;
import org.testng.annotations.Test;


public class PlaceOrderTest extends BaseTest {


    @Test
    public void PlaceAnOrderTest() {
        LoginPage loginPage = new LoginPage(driver,softAssert);
        OrdersPage ordersPage = new OrdersPage(driver,softAssert);
        PlaceOrderPage placeOrderPage = new PlaceOrderPage(driver,softAssert);

        loginPage.navigateToLoginPage();
        loginPage.login();

        ordersPage.clickOnViewAllOrders();
        ordersPage.checkAllOrders();

        placeOrderPage.clickOnOrders();
        placeOrderPage.placeNewOrder();

        ordersPage.clickOnViewAllOrders();
        placeOrderPage.verifyNewOrder();
    }

}
