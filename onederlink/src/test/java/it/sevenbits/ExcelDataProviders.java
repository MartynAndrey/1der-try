package it.sevenbits;

import org.testng.annotations.DataProvider;

/**
 * Один датапровайдер - один тестовый набор
 * */

public class ExcelDataProviders {
    @DataProvider
    public static Object[][] incorrectEmailPass() throws Exception {
        String path = "src/test/resources/DataProviderFiles/negative-cases-for-login.xlsx";
        ExcelReader excelReader = new ExcelReader(path);
        return excelReader.getSheetDataForDataProvider();
    }
}
