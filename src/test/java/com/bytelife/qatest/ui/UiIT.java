package com.bytelife.qatest.ui;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.impl.WebElementsCollection;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * @author akondrasev
 */
public class UiIT {

    @Before
    public void setUp() {
        open("http://localhost:8080/");
    }

    @Test
    public void addNewMachineTest() {
        String expectedName = "Test Machine";
        String expectedState = "string:POWERED_ON";
        $("#addNewBtn").click();
        $(By.name("name")).setValue(expectedName);
        $(By.name("powerState")).setValue(expectedState);
        $("#saveOrUpdateBtn").click();

        ElementsCollection tableRows = $("table").findAll("tr");
        tableRows.last().shouldHave(text(expectedName)).shouldHave(text(expectedState.split("string:")[1]));
    }


    @Test
    public void editMachineTest() {
        String expectedName = "Edited Machine";
        String expectedState = "string:POWERED_OFF";

        ElementsCollection tableRows = $("table tbody").findAll("tr");
        WebElement firstRow = tableRows.first();
        WebElement editLink = firstRow.findElement(By.tagName("a"));
        editLink.click();

        $(By.name("name")).setValue(expectedName);
        $(By.name("powerState")).setValue(expectedState);
        $("#saveOrUpdateBtn").click();

        tableRows = $("table tbody").findAll("tr");
        tableRows.first().shouldHave(text(expectedName)).shouldHave(text(expectedState.split("string:")[1]));
    }


}
