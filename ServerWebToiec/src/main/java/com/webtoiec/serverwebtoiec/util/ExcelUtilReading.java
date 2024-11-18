package com.webtoiec.serverwebtoiec.util;

import com.webtoiec.serverwebtoiec.entities.QuestionReading;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelUtilReading {
  public List<QuestionReading> getListQuestionReadingFromFileExcel(InputStream excelFile) {
    try {
      List<QuestionReading> listQuestionReading = new ArrayList<>();
      Workbook workbook = new XSSFWorkbook(excelFile);
      Sheet datatypeSheet = workbook.getSheetAt(0);
      DataFormatter fmt = new DataFormatter();
      Iterator<Row> iterator = datatypeSheet.iterator();
      Row firstRow = iterator.next();
      Cell firstCell = firstRow.getCell(0);
      System.out.println(firstCell.getStringCellValue());
      while (iterator.hasNext()) {
        Row currentRow = iterator.next();
        QuestionReading questionReading = new QuestionReading();
        questionReading.setQuestionreadingserial(fmt.formatCellValue(currentRow.getCell(0)));
        questionReading.setQuestionreadingask(fmt.formatCellValue(currentRow.getCell(1)));
        questionReading.setQuestionreadinganswer1(fmt.formatCellValue(currentRow.getCell(2)));
        questionReading.setQuestionreadinganswer2(fmt.formatCellValue(currentRow.getCell(3)));
        questionReading.setQuestionreadinganswer3(fmt.formatCellValue(currentRow.getCell(4)));
        questionReading.setQuestionreadinganswer4(fmt.formatCellValue(currentRow.getCell(5)));
        questionReading.setQuestionreadinganswercorrect(fmt.formatCellValue(currentRow.getCell(6)));
        questionReading.setQuestionreadingexplain(fmt.formatCellValue(currentRow.getCell(7)));
        questionReading.setQuestionreadingscript(fmt.formatCellValue(currentRow.getCell(8)));

        System.out.println(questionReading.toString());
        listQuestionReading.add(questionReading);
      }

      workbook.close();
      return listQuestionReading;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
