package com.academy.techcenture;

import com.academy.techcenture.pages.LoginPage;
import com.academy.techcenture.pages.OrdersPage;
import org.testng.annotations.Test;

public class OrdersTest extends BaseTest {



    @Test
    public void deleteOrderPositiveTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, softAssert);
        OrdersPage ordersPage = new OrdersPage(driver,softAssert);

        loginPage.navigateToLoginPage();
        loginPage.login();
        ordersPage.clickOnViewAllOrders();
        ordersPage.deleteOrder();
        Thread.sleep(2000);
    }

    @Test
    public void checkAndUncheckAllOrders() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, softAssert);
        OrdersPage ordersPage = new OrdersPage(driver,softAssert);

        loginPage.navigateToLoginPage();
        loginPage.login();
        ordersPage.clickOnViewAllOrders();
        ordersPage.checkAllOrders();
        ordersPage.uncheckAllOrders();
        Thread.sleep(2000);
    }


}
