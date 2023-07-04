package it.sevenbits.UI;

import it.sevenbits.ExcelDataProviders;
import it.sevenbits.UI.pages.ForgotPasswordPage;
import it.sevenbits.UI.pages.PromoLinksPage;
import it.sevenbits.UI.pages.SignInPage;
import it.sevenbits.UI.pages.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;

public class SignInPageTest {

    @BeforeClass
    public void init(){
        Configurator.setupChrome("1920x1080");
    }

    @Test
    public void testLoadSignInPage() {
        SignInPage.load();
        String expected = "Sign In";
        String actual = SignInPage.getTitleOfPage();
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testLoadSignUpPage() {
        SignInPage.load();
        SignInPage.clickOnSignUpLink();
        String expected = "Sign Up";
        String actual = SignUpPage.getTitleOfPage();
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testLoadForgotPasswordPage() {
        SignInPage.load();
        SignInPage.clickOnForgotPasswordLink();

        String expected = "Password recovery";
        String actual = ForgotPasswordPage.getTitleOfPage();
        Assert.assertEquals(actual,expected);
    }

    @Test (dataProvider = "incorrectEmailPass", dataProviderClass = ExcelDataProviders.class)
    public void testIncorrectEmailPassSignIn(String email, String password) {
        SignInPage.load();
        SignInPage.setEmail(email);
        SignInPage.setPassword(password);
        SignInPage.clickSignInButton();
        SignInPage.getError().shouldBe(visible);
    }

    @Test
    public void testCorrectEmailPassSignIn() {
        SignInPage.load();
        SignInPage.setEmail("heytest1@rambler.ru");
        SignInPage.setPassword("Ppp1234#");
        SignInPage.clickSignInButton();
        PromoLinksPage.getAddButton().shouldBe(visible);
    }
}
