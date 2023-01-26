package com.academy.techcenture.utils;

import com.academy.techcenture.pages.ProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Driver;

public class Utils {


    //    RANDOM NUMBER FROM MIN TO MAX
    /**
     * This method generates a random number from min argument to max argument
     * @param min
     * @param max
     * @return
     */
    public static int generateRandomNumber(int min, int max){
        return (int)(Math.random() * ((max-min) + 1 )) + min;
    }

    /**
     * This method will generate a random string number
     * @param min from
     * @param max to
     * @param length length of string numbers
     * @return string number
     */
    public static String generateStringNumbers(int min, int max, int length){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(generateRandomNumber(min, max));
        }
        return result.toString();
    }


    public static void main(String[] args) {


        for (int i = 0; i < 100; i++) {
            int expMonth = generateRandomNumber(2023,2050);
            System.out.println(generateRandomNumber(0,2));
        }
    }


}

