package com.webtoiec.serverwebtoiec.util;

import com.webtoiec.serverwebtoiec.entities.QuestionListening;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelUtilListening {
  public List<QuestionListening> getListQuestionListeningFromFileExcel(InputStream excelFile)
  {
    try {
      List<QuestionListening> listQuestionListening = new ArrayList<>();

      Workbook workbook = new XSSFWorkbook(excelFile);

      Sheet datatypeSheet = workbook.getSheetAt(0);

      DataFormatter fmt = new DataFormatter();

      Iterator<Row> iterator = datatypeSheet.iterator();

      Row firstRow = iterator.next();

      Cell firstCell = firstRow.getCell(0);

      System.out.println(firstCell.getStringCellValue());

      XSSFDrawing dp = (XSSFDrawing) datatypeSheet.createDrawingPatriarch();

      List<XSSFShape> pics = dp.getShapes();

      while (iterator.hasNext()) {
        Row currentRow = iterator.next();
        QuestionListening questionListening = new QuestionListening();
        questionListening.setQuestionlisteningserial(fmt.formatCellValue(currentRow.getCell(0)));
        questionListening.setQuestionlisteningask(fmt.formatCellValue(currentRow.getCell(1)));
        questionListening.setQuestionlisteninganswer1(fmt.formatCellValue(currentRow.getCell(2)));
        questionListening.setQuestionlisteninganswer2(fmt.formatCellValue(currentRow.getCell(3)));
        questionListening.setQuestionlisteninganswer3(fmt.formatCellValue(currentRow.getCell(4)));
        questionListening.setQuestionlisteninganswer4(fmt.formatCellValue(currentRow.getCell(5)));
        questionListening.setQuestionlisteninganswercorrect(fmt.formatCellValue(currentRow.getCell(6)));
        questionListening.setQuestionlisteningexplain(fmt.formatCellValue(currentRow.getCell(7)));

        for (Iterator<? extends XSSFShape> it = pics.iterator(); it.hasNext();) {
          XSSFPicture inpPic = (XSSFPicture) it.next();

          XSSFClientAnchor clientAnchor = inpPic.getClientAnchor();
          PictureData pic = inpPic.getPictureData();
          byte[] data = pic.getData();
          if (clientAnchor != null && clientAnchor.getCol1() == 8 && clientAnchor.getRow1() == currentRow.getRowNum()) {
            byte[] pictureData = data;
            questionListening.setQuestionlisteningimagedata(pictureData);
          }
        }
        System.out.println(questionListening.toString());
        questionListening.setQuestionlisteningscript(fmt.formatCellValue(currentRow.getCell(9)));
        listQuestionListening.add(questionListening);
      }
      workbook.close();
      return listQuestionListening;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
