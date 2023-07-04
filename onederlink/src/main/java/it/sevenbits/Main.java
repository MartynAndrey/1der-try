package it.sevenbits;

import it.sevenbits.UI.Configurator;
import it.sevenbits.UI.pages.PromoLinksPage;
import it.sevenbits.UI.pages.SignInPage;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Configurator.setupChrome("1920x1080");
        SignInPage.load();

        String a = SignInPage.getTitleOfPage();
        SignInPage.setEmail("maiail.ru");
        SignInPage.setPassword("12pass34");
        SignInPage.clickSignInButton();
        String b = SignInPage.getErrorMessage();
        SignInPage.setEmail("heytest1@rambler.ru");
        SignInPage.setPassword("Ppp1234#");
        SignInPage.clickSignInButton();
        PromoLinksPage.clickIntroSkipButton();
    }
}