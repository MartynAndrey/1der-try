package it.sevenbits.UI.pages;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import static it.sevenbits.TestSettings.*;

public class PromoLinksPage {

    private static final SelenideElement addForm = $(byCssSelector(".add-form"));
    private static final SelenideElement addPromoLinkButton = addForm.$(byCssSelector(".add-form__button"));
    private static final SelenideElement introSkipButton = $(byCssSelector(".introjs-skipbutton"));

    public static void load() {
        open(STAGING_URL);
    }

    public static SelenideElement getAddButton(){
        return addPromoLinkButton;
    }

    public static void clickIntroSkipButton(){
        introSkipButton.click();
    }
}
