package com.academy.techcenture;

import com.academy.techcenture.pages.LoginPage;
import com.academy.techcenture.pages.ProductsPage;
import org.testng.annotations.Test;


public class ProductsTest extends BaseTest {


    @Test
    public void productPositiveTest(){
        LoginPage loginPage = new LoginPage(driver,softAssert);
        ProductsPage productsPage = new ProductsPage(driver,softAssert);
        loginPage.navigateToLoginPage();
        loginPage.login();

        productsPage.clickOnViewAllProducts();
        productsPage.verifyProductsTable();

    }

 

}
