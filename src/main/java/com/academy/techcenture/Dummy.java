package com.academy.techcenture;

import org.testng.asserts.SoftAssert;

public class Dummy {
    public static void main(String[] args) {

        SoftAssert softAssert = new SoftAssert();

        System.out.println("Hello Batch 7 Test Started");
        System.out.println("Test Running");
        softAssert.assertEquals(5, 7, " Numbers did not match");
        System.out.println("Test Running 1");
        softAssert.assertTrue(false, "Supposed to be true");
        System.out.println("Test Running 2");
        softAssert.assertEquals(false, true, "Supposed to be equal");
        System.out.println("Test Ended");

        System.out.println("Goodbye batch 7");

        softAssert.assertAll();

    }
}
