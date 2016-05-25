package com.bytelife.qatest.ui;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * @author akondrasev
 */
public class UiTests {

    @Before
    public void setUp() {
        open("http://localhost:8080/");
    }

    @Test
    public void ggSearchTest() {
        $(By.name("q")).setValue("selenide").pressEnter();
        $("document").shouldHave(text("Selenide.org"));
    }


}
