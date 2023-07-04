package it.sevenbits.UI.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;

public class ForgotPasswordPage {
    private static final SelenideElement authForm = $(byCssSelector(".auth-form"));
    private static final SelenideElement titleOfPage = authForm.$(byCssSelector("h1.auth-form_title-text"));
    public static String getTitleOfPage(){
        return titleOfPage.innerText();
    }
}
