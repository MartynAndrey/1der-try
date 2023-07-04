package it.sevenbits.UI.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import static it.sevenbits.TestSettings.*;

public class SignInPage {

    private static final SelenideElement authForm = $(byCssSelector(".auth-form"));
    //private static final SelenideElement titleOfPage = $(byCssSelector("h1.auth-form_title-text"));
    private static final SelenideElement titleOfPage = authForm.$(byCssSelector("h1.auth-form_title-text"));
    //private static final SelenideElement emailInput = $(byCssSelector(".auth-form_email-input input"));
    private static final SelenideElement emailInput = authForm.$(byCssSelector(".auth-form_email-input input"));
    //private static final SelenideElement passwordInput = $(byCssSelector("auth-form_password-input input"));
    private static final SelenideElement passwordInput = authForm.$(byCssSelector(".auth-form_password-input input"));
    private static final SelenideElement passwordToggleButton = authForm.$(byCssSelector(".auth-form_password-input button"));
    private static final SelenideElement forgotPassLink = authForm.$(byCssSelector(".auth-form_forgot-password"));
    private static final SelenideElement signInButton = authForm.$(byCssSelector(".auth-form_submit-button"));
    private static final SelenideElement authFormError = authForm.$(byCssSelector(".auth-form_error.auth-form_error_enabled"));
    private static final SelenideElement signUpLink = authForm.$(byCssSelector(".auth-form_link"));

    public static void load() {
        open(STAGING_SIGNIN_URL);
    }

    public static String getTitleOfPage(){
        return titleOfPage.innerText();
    }

    public static void setEmail(String email){
        emailInput.sendKeys(Keys.CONTROL + "a");
        emailInput.sendKeys(Keys.BACK_SPACE);
        emailInput.setValue(email);
    }

    public static void setPassword(String password){
        passwordInput.sendKeys(Keys.CONTROL + "a");
        passwordInput.sendKeys(Keys.BACK_SPACE);
        passwordInput.setValue(password);
    }

    public static SelenideElement getError(){
        return authFormError;
    }

    public static String getErrorMessage(){
        return authFormError.innerText();
    }

    public static void clickOnForgotPasswordLink(){
        forgotPassLink.click();
    }

    public static void clickSignInButton(){
        signInButton.click();
    }

    public static void clickOnSignUpLink(){
        signUpLink.click();
    }
}
