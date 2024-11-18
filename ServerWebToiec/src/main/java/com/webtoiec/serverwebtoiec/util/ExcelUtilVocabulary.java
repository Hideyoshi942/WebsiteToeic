package com.webtoiec.serverwebtoiec.util;

import com.webtoiec.serverwebtoiec.entities.QuestionVocabulary;
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
public class ExcelUtilVocabulary {
  public List<QuestionVocabulary> getListQuestionVocabularyFromFileExcel(InputStream excelFile) {
    try {
      List<QuestionVocabulary> listQuestionVocabulary = new ArrayList<>();
      Workbook workbook = new XSSFWorkbook(excelFile);

      Sheet datatypeSheet = workbook.getSheetAt(0);

      DataFormatter fmt = new DataFormatter();

      Iterator<Row> iterator = datatypeSheet.iterator();
      Row firstRow = iterator.next();
      Cell firstCell = firstRow.getCell(0);
      System.out.println(firstCell.getStringCellValue());

      while (iterator.hasNext()) {
        Row currentRow = iterator.next();
        QuestionVocabulary questionVocabulary = new QuestionVocabulary();
        questionVocabulary.setQuestionvocabularyserial(fmt.formatCellValue(currentRow.getCell(0)));
        questionVocabulary.setQuestionvocabularyask(fmt.formatCellValue(currentRow.getCell(1)));
        questionVocabulary.setQuestionvocabularyanswer1(fmt.formatCellValue(currentRow.getCell(2)));
        questionVocabulary.setQuestionvocabularyanswer2(fmt.formatCellValue(currentRow.getCell(3)));
        questionVocabulary.setQuestionvocabularyanswer3(fmt.formatCellValue(currentRow.getCell(4)));
        questionVocabulary.setQuestionvocabularyanswer3(fmt.formatCellValue(currentRow.getCell(5)));
        questionVocabulary.setQuestionvocabularyanswercorrect(fmt.formatCellValue(currentRow.getCell(6)));
        questionVocabulary.setQuestionvocabularyexplain(fmt.formatCellValue(currentRow.getCell(7)));
        System.out.println(questionVocabulary.toString());
        listQuestionVocabulary.add(questionVocabulary);
      }

      workbook.close();
      return listQuestionVocabulary;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
