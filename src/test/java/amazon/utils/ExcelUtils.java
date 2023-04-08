package amazon.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelUtils {

    public static String getDataXRowYCell(String fileName, String sheetName, int row, int cell){

        String data = null;
        String path = "src/test/resources/data/" + fileName;
        try {
            FileInputStream file = new FileInputStream(path);
            Workbook excel = new XSSFWorkbook(file);

            Sheet sheet = excel.getSheet(sheetName);
            Row myRow = sheet.getRow(row);
            Cell myCell = myRow.getCell(cell);

            data = myCell.toString();
            //data = excel.getSheet(sheetName).getRow(row).getCell(cell).toString();

            closeSources(excel, file);
        } catch (Exception ex) {
            System.out.println("Error : " + ex);
        }

        return data;
    }

    private static void closeSources(Workbook excel, FileInputStream file) {
        try {
            excel.close();
            file.close();
        } catch (Exception ex) {
            System.out.println("Error : " + ex);
        }
    }

    public static List<String> getDataXRow(String fileName, String sheetName, String testcaseName){

        List<String> dataList = new ArrayList<>();

        String path = "src/test/resources/data/" + fileName;
        try {
            FileInputStream file = new FileInputStream(path);
            Workbook excel = new XSSFWorkbook(file);
            Sheet sheet = excel.getSheet(sheetName);

            String wanted = testcaseName;

            int count = -1;
            for (Row row : sheet) {
                count++;
                String cellValue = row.getCell(0).toString();
                if (cellValue.equalsIgnoreCase(wanted)) {
                    break;
                }
            }

            int cellCount = sheet.getRow(count).getLastCellNum();

            for (int i = 1; i < cellCount; i++) { // first column skip
                String data = sheet.getRow(count).getCell(i).toString();
                dataList.add(data);
            }

            closeSources(excel, file);
        } catch (Exception ex) {
            System.out.println("Error : " + ex);
        }

        return dataList;
    }


    public static List<List<String>> getDataAllRows(String fileName, String sheetName){

        List<String> dataList = new ArrayList<>();

        String path = "src/test/resources/data/" + fileName;
        List<List<String>> rows = null;
        try {
            FileInputStream file = new FileInputStream(path);
            Workbook excel = new XSSFWorkbook(file);
            Sheet sheet = excel.getSheet(sheetName);


            rows = new ArrayList<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) // x rows
            {
                List<String> row = new ArrayList<>();
                for (int j = 1; j < sheet.getRow(i).getLastCellNum(); j++) // y celss
                {
                    row.add(sheet.getRow(i).getCell(j).toString());
                }
                rows.add(row);
            }
            System.out.println(rows);

            closeSources(excel, file);
        } catch (Exception ex) {
            System.out.println("Error : " + ex);
        }

        return rows;
    }

    public static List<String> getDataYColumn(String fileName, String sheetName, String columnName){

        List<String> dataList = new ArrayList<>();


        String path = "src/test/resources/data/" + fileName;
        try {
            FileInputStream file = new FileInputStream(path);
            Workbook excel = new XSSFWorkbook(file);

            String wantedCell = columnName;

            Sheet sheet = excel.getSheet(sheetName);
            int cellCount = sheet.getRow(0).getLastCellNum(); // total cell

            int whichCell = -1;
            for (int i = 0; i < cellCount; i++) {
                String cellName = sheet.getRow(0).getCell(i).toString();

                if (cellName.equalsIgnoreCase(wantedCell)) {
                    whichCell = i;
                    break;
                }
            }
            int rowCount = sheet.getLastRowNum(); // index olarak

            for (int i = 1; i <= rowCount; i++) { // first row skip
                String value = sheet.getRow(i).getCell(whichCell).toString();
                dataList.add(value);
            }

            closeSources(excel, file);
        } catch (Exception exception) {
            System.out.println("Error : " + exception);
        }
        return dataList;
    }

    // csv reader - get all rows
    public static Object[][] getCsvData(String csvFileName, String splitCharacter) {
        String excelFilePath = System.getProperty("user.dir") +
                "\\src\\test\\resources\\data\\" +
                csvFileName; // excel.csv

        String line = "";
        String splitBy = splitCharacter;
        ArrayList<ArrayList<String>> datas = new ArrayList<>();
        ArrayList<String> data;
        String[] temp = null;
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(excelFilePath));
            br.readLine(); // dummy reading to header (columns name)
            int countLine = 0;

            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                data = new ArrayList<String>();
                temp = line.split(splitBy); // use comma as separator

                for (String item : temp) {
                    data.add(item);
                }

                datas.add(countLine, data);
                countLine++;
                // datas.add(Arrays.asList(employee)); // Add String Array to ArrayList
                // ****onemli -- dikkat

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(datas);
        // [[Ayse, female, Argentina], [Hatice, female, Belarus], [Zeynep, female, Argentina]]
        // [[us1, pass1], [us2, pass2], [us3, pass3]]
        Object[][] objArray = new Object[datas.size()][temp.length]; // too dynamic

        for (int i = 0; i < objArray.length; i++) {
            // ArrayList to Array (toArray())
            objArray[i] = datas.get(i).toArray(); // datas.toArray() -> ArrayList -> Array ****onemli
            // Array to ArrayList (Arrays.asList(array))
        }

        // System.out.println(Arrays.toString(objArray[0]));
        return objArray;
    }


    // main codes
    /*
        String cellValue = ExcelUtils.getDataXRowYCell("data.xlsx", "testcases",4,0);
        System.out.println(cellValue);

        List<String> rowAsList = ExcelUtils.getDataXRow("data.xlsx", "testcases", "cart");
        System.out.println("Cart Row : " + rowAsList);

        List<String> columnAsList = ExcelUtils.getDataYColumn("data.xlsx", "testcases", "data4");
        System.out.println("Data4 Column : "+ columnAsList);
     */


}

/*
         if(cell.getCellType() == CellType.NUMERIC)
        {
            System.out.println("Numeric : "+ cell.toString());
        }
        else if(cell.getCellType() == CellType.STRING)
        {
            System.out.println("Text : "+ cell);
        }

 */
/*
        FileInputStream file = new FileInputStream("src/test/resources/data.xlsx");
        Workbook excel = new XSSFWorkbook(file);

        Sheet sheet = excel.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        System.out.println(cell.toString());

*/


/* METOTLAR

public static String getDataXRowYCell(String fileName, String sheetName, int rowCount, int cellCount) throws IOException {
        String path = "src/test/resources/" + fileName; // "src/test/resources/data.xlsx"

        FileInputStream file = new FileInputStream(path);
        Workbook excel = new XSSFWorkbook(file);

        Sheet sheet = excel.getSheet(sheetName);
        Row row = sheet.getRow(rowCount);
        Cell cell = row.getCell(cellCount);
        return cell.toString();
    }

    public static void getXRow(String fileName, String sheetName, String rowName) throws IOException {
        String path = "src/test/resources/" + fileName;
        FileInputStream file = new FileInputStream(path);
        Workbook excel = new XSSFWorkbook(file);

        Sheet sheet = excel.getSheet(sheetName);

        int i = 0;
        for (Row row : sheet) {
            String rname = row.getCell(0).getStringCellValue();

            if (rname.equalsIgnoreCase(rowName)) {
                break;
            }
            i++;
        }

        int lastCellNum = sheet.getRow(i).getLastCellNum();
        ArrayList<String> datalistFromExcel = new ArrayList<>();
        for (int j = 1; j < lastCellNum; j++) {
            addCellDataToList(sheet.getRow(i).getCell(j), datalistFromExcel);
        }
        System.out.println(datalistFromExcel);

    }

    public static ArrayList<String> addCellDataToList(Cell ce, ArrayList<String> datalistFromExcel)  // all cells in particular row
    {
        if (ce.getCellType() == CellType.STRING) {
            datalistFromExcel.add(ce.getStringCellValue());
        } else // CellType can be NUMERIC, BOOLEAN, ERROR
        {
            String newType = NumberToTextConverter.toText(ce.getNumericCellValue());
            datalistFromExcel.add(newType);
        }
        return datalistFromExcel;
    }

    public static void addRowDataToList(String fileName, String sheetName, String columnName) throws IOException // all row in particular cell
    {
        String path = "src/test/resources/" + fileName;
        FileInputStream file = new FileInputStream(path);
        Workbook excel = new XSSFWorkbook(file);

        Sheet sheet = excel.getSheet(sheetName);
        int cellCount = sheet.getRow(0).getLastCellNum();

        int whichCell = -1;
        for (int i = 0; i < cellCount; i++) {
            String cellName = sheet.getRow(0).getCell(i).getStringCellValue();
            if (cellName.equalsIgnoreCase(columnName)) {
                whichCell = i;
                break;
            }
        }

        int rowCount = sheet.getLastRowNum(); // index olarak

        ArrayList<String> dataList = new ArrayList<>();
        for (int i = 1; i <= rowCount ; i++) {
            addCellDataToList(sheet.getRow(i).getCell(whichCell), dataList);

        }
        System.out.println(dataList);

    }
 */