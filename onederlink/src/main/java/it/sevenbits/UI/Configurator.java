package it.sevenbits.UI;

import com.codeborne.selenide.Configuration;

import static it.sevenbits.TestSettings.*;

public final class Configurator {

    private static void setUpURL(final String size) {
        Configuration.baseUrl = STAGING_URL;
        Configuration.browserSize = size;
    }

    public static void setupFirefox(final String size) {
        setUpURL(size);
        Configuration.browser = "firefox";
    }

    public static void setupChrome(final String size) {
        setUpURL(size);
        Configuration.browser = "chrome";
    }

    public static void setupSafari(final String size) {
        setUpURL(size);
        Configuration.browser = "safari";
    }

}
