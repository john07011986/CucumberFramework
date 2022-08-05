package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
    //use this class to work with file
    static Workbook book;
    static Sheet sheet;

    public static void openExcel(String filepath)  {
        try {
            //to open the file
            FileInputStream fileInputStream = new FileInputStream(filepath);
            book = new XSSFWorkbook(fileInputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//open sheet int he excel
  public static void getSheet(String sheetName){
        sheet= book.getSheet(sheetName);
  }

  //get the total number of rows
    public static int getRowCount(){
        return sheet.getPhysicalNumberOfRows();

    }

    //get the total number of columns in each row
    public static int getColumnCount(int rowIndex){
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }

   //this will return data from cell in string format
   public static String getCellData(int rowIndex, int cellIndex){
        return sheet.getRow(rowIndex).getCell(cellIndex).toString();
   }

   public static List<Map<String,String>> excelIntoMap(String filePath, String SheetName){

        openExcel(filePath);
        getSheet(SheetName);

        List<Map<String,String>> listData = new ArrayList<>();

        //outer loop
       for (int row=1; row<getRowCount();row++){
           //creating a map for every row
           Map<String,String> map = new LinkedHashMap<>();
           for (int col=0;col<getColumnCount(row);col++){
               map.put(getCellData(0,col),getCellData(row,col));
           }
           listData.add(map);
       }
        return listData;

   }




}


