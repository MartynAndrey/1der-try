package it.sevenbits;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static it.sevenbits.TestSettings.DEFAULT_SHEET_LIST;

/**
 * Класс ExcelReader описывает методы для чтения Эксель документов
 * используем библиотеку apache.poi
 * filePath - путь к файлу
 * sheet - данные на странице
 * book - переменная для работы с самим excel
 * */
public class ExcelReader {
    private final String filePath;
    private XSSFSheet sheet;
    private XSSFWorkbook book;

    /**
     * Конструктор ExcelReader принимает одну переменную
     * @param filePath - путь
     * Используем шаблонное название листа DEFAULT_SHEET_LIST
     * */
    public ExcelReader(String filePath) throws IOException {
        this.filePath = filePath;
        File file = new File(filePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            book = new XSSFWorkbook(fileInputStream);
            sheet = book.getSheet(DEFAULT_SHEET_LIST); //Надо заранее определить название листов
        } catch (IOException e) {
            throw new IOException("Не поддерживаемый формат");
        }
    }

    /**
     * Перегруженный конструктор, который принимает другое нестандартизированное название листа
     * @param filePath - путь
     * @param sheetName - название листа
     * */
    public ExcelReader(String filePath, String sheetName) throws Exception {
        this.filePath = filePath;
        File file = new File(filePath);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            book = new XSSFWorkbook(fileInputStream);
            sheet = book.getSheet(sheetName); //Надо заранее определить название листов
        } catch (Exception e) {
            throw new Exception ("Не поддерживаемый формат");
        }
    }

    /**
     *  Метод cellToString определяет тип ячейки - что находится внутри одного листа.
     *  читаем ячейку и преобразуем все в строку
     *  на входе принимает ячейку. Будет использоваться в методе, где перепбираем ячейки
     *  */
    public String cellToString(XSSFCell cell) throws Exception {
        Object result = null;
        CellType type = cell.getCellType();
        switch (type) {
            case NUMERIC:
                result = cell.getNumericCellValue();
                break;
            case STRING:
                result = cell.getStringCellValue();
                break;
            case FORMULA:
                result = cell.getCellFormula();
                break;
            case BLANK:
                result = "";
                break;
            default:
                throw new Exception("Ошибка чтения ячейки");
        }
        return result.toString();
    }

    /**
     * Методы xlsxCountColumn и xlsxCountRow определяют и возвращает размер таблицы: кол-во столбцов и кол-во строк
     * */
    private int xlsxCountColumn() {
        return sheet.getRow(0).getLastCellNum(); //получаем строку с номером 0 и у этой строки получаем номер последней строки
    }

    private int xlsxCountRow() {
        return sheet.getLastRowNum() + 1; //исключили первую строку
    }

    /** Метод getSheetDataForDataProvider
     * считывает из файла, создает массив data и заполняет его из листа данными и возвращает на выходе
     * */
    public String[][] getSheetDataForDataProvider() throws Exception {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        book = new XSSFWorkbook(fileInputStream);
        sheet = book.getSheet(DEFAULT_SHEET_LIST);
        int numberOfColumn = xlsxCountColumn();
        int numberOfRows = xlsxCountRow();
        String[][] data = new String[numberOfRows - 1][numberOfColumn];
        for (int i = 1; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumn; j++) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(j);
                String value = cellToString(cell);
                data[i - 1][j] = value;
                if (value == null) {
                    throw new Exception("Пустые ячейки");
                }
            }
        }
        return data;
    }

    /** Перегруженный метод getSheetDataForDataProvider, принимающий название листа
     * @param sheetName - название листа
     * */
    public final String[][] getSheetDataForDataProvider(String sheetName) throws Exception {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        book = new XSSFWorkbook(fileInputStream);
        sheet = book.getSheet(sheetName);
        int numberOfColumn = xlsxCountColumn();
        int numberOfRows = xlsxCountRow();
        String[][] data = new String[numberOfRows - 1][numberOfColumn];
        for (int i = 1; i < numberOfRows; i++) {
            for (int j = 1; j < numberOfColumn; j++) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell = row.getCell(j);
                String value = cellToString(cell);
                data[i - 1][j] = value;
                if (value == null) {
                    throw new Exception("Пустые ячейки");
                }
            }
        }
        return data;
    }

}