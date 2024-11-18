package com.webtoiec.serverwebtoiec.util;

import com.webtoiec.serverwebtoiec.entities.QuestionGrammar;
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
public class ExcelUtilGrammar {
  public List<QuestionGrammar> getListQuestionGrammarFromFileExcel(InputStream excelFile) {
    try {
      List<QuestionGrammar> listQuestionGrammar = new ArrayList<>();
      Workbook workbook = new XSSFWorkbook(excelFile);
      Sheet datatypeSheet = workbook.getSheetAt(0);
      DataFormatter fmt = new DataFormatter();

      Iterator<Row> iterator = datatypeSheet.iterator();
      Row firstRow = iterator.next();
      Cell firstCell = firstRow.getCell(0);
      System.out.println(firstCell.getStringCellValue());

      while (iterator.hasNext()) {
        Row currentRow = iterator.next();
        QuestionGrammar questionGrammar = new QuestionGrammar();
        questionGrammar.setQuestiongrammarserial(fmt.formatCellValue(currentRow.getCell(0)));
        questionGrammar.setQuestiongrammarask(fmt.formatCellValue(currentRow.getCell(1)));
        questionGrammar.setQuestiongrammaranswer1(fmt.formatCellValue(currentRow.getCell(2)));
        questionGrammar.setQuestiongrammaranswer2(fmt.formatCellValue(currentRow.getCell(3)));
        questionGrammar.setQuestiongrammaranswer3(fmt.formatCellValue(currentRow.getCell(4)));
        questionGrammar.setQuestiongrammaranswer4(fmt.formatCellValue(currentRow.getCell(5)));
        questionGrammar.setQuestiongrammaranswercorrect(fmt.formatCellValue(currentRow.getCell(6)));
        questionGrammar.setQuestiongrammarexplain(fmt.formatCellValue(currentRow.getCell(7)));
        System.out.println(questionGrammar.toString());
        listQuestionGrammar.add(questionGrammar);
      }

      workbook.close();
      return listQuestionGrammar;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
