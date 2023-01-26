package com.academy.techcenture.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ProductsPage extends BasePage{

    public ProductsPage(WebDriver driver, SoftAssert softAssert){
        super(driver, softAssert);
    }




    @FindBy(tagName = "h2")
    private WebElement listOfProductsHeader;

    @FindBy(xpath = "//table[@class='ProductsTable']")
    private WebElement productsTable;





    public void verifyProductsTable(){
        String[][] productsInfo = {
                {"MyMoney", "$100", "8%"},
                {"FamilyAlbum", "$80", "15%"},
                {"ScreenSaver", "$20", "10%"}
        };

        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='ProductsTable']/tbody/tr"));
        List<WebElement> cols = driver.findElements(By.xpath("//table[@class='ProductsTable']/tbody/tr[2]/td"));

        for (int i = 1; i < rows.size(); i++){
            for (int j = 0; j < cols.size(); j++) {
                String xpath = "//table[@class='ProductsTable']/tbody/tr["+(i+1)+"]/td["+(j+1)+"]";
                String actualCellData = driver.findElement(By.xpath(xpath)).getText();
                String expectedCellData = productsInfo[i-1][j];
                softAssert.assertEquals(actualCellData,expectedCellData,"CELL DO NOT MATCH");
            }
        }

    }
}
