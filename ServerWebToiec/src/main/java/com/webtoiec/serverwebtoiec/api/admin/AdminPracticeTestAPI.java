package com.webtoiec.serverwebtoiec.api.admin;

import com.webtoiec.serverwebtoiec.entities.PracticeTest;
import com.webtoiec.serverwebtoiec.entities.QuestionPracticeTest;
import com.webtoiec.serverwebtoiec.repository.PracticeTestRepo;
import com.webtoiec.serverwebtoiec.repository.QuestionPracticeTestRepo;
import com.webtoiec.serverwebtoiec.service.impl.PracticeTestService;
import com.webtoiec.serverwebtoiec.service.impl.QuestionPracticeTestService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/practicetest")
public class AdminPracticeTestAPI {
  @Autowired
  private HttpServletRequest request;

  @Autowired
  public PracticeTestService practicetestService;

  @Autowired
  public QuestionPracticeTestService questionpracticetestService;
  @Autowired
  private QuestionPracticeTestRepo questionPracticeTestRepo;
  @Autowired
  private PracticeTestRepo practiceTestRepo;

  @GetMapping("/loadpracticetest")
  public List<Map<String, Object>> showAllPracticeTest() {
    List<PracticeTest> list = practicetestService.getAllPracticeTest();
    List<Map<String, Object>> response = new ArrayList<>();

    for (PracticeTest practiceTest : list) {
      Map<String, Object> map = new HashMap<>();
      map.put("practicetestid", practiceTest.getPracticetestid());
      map.put("practicetestname", practiceTest.getPracticetestname());
      map.put("practicetestimage", "/api/admin/practicetest/image/" + URLEncoder.encode(practiceTest.getPracticetestimage(), StandardCharsets.UTF_8));
      response.add(map);
    }

    return response;
  }

  @GetMapping("/loadQuestionPracticeTest/{practicetestid}")
  public Page<QuestionPracticeTest> findListQuestion(
      @PathVariable int practicetestid,
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "50") int size) {
    PracticeTest practicetest = practiceTestRepo.findById(practicetestid).orElse(null);

    return questionPracticeTestRepo.findByPracticetest(PageRequest.of(page - 1, size), practicetest);
  }

  @GetMapping("/image/{filename}")
  public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
    try {
      String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8.name());
      Path filePath = Paths.get("src/main/resources/static/img/practicetest").resolve(decodedFilename).normalize();

      Resource resource = new UrlResource(filePath.toUri());
      if (!resource.exists()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }

      String contentType = Files.probeContentType(filePath);
      if (contentType == null) {
        contentType = "application/octet-stream";
      }

      return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType(contentType))
          .body(resource);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @GetMapping("/audio/{filename}")
  public ResponseEntity<Resource> serveAudio(@PathVariable String filename) {
    try {
      String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8.name());
      Path filePath = Paths.get("src/main/resources/static/audio/practicetest").resolve(decodedFilename).normalize();
      System.out.println("Audio path being accessed: " + filePath.toString());
      Resource resource = new UrlResource(filePath.toUri());
      if (!resource.exists()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }

      String contentType = Files.probeContentType(filePath);
      if (contentType == null) {
        contentType = "application/octet-stream";
      }

      return ResponseEntity.ok()
          .contentType(MediaType.parseMediaType(contentType))
          .body(resource);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }

  @RequestMapping(value = "/delete/{practicetestid}")
  public String deleteByPracticetestid(@PathVariable("practicetestid") int practicetestid) {
    practicetestService.delete(practicetestid);
    return "success";
  }

  @PostMapping(value = "/save", consumes = "multipart/form-data")
  @ResponseBody
  public List<String> addPracticeTest(@RequestParam("file_excel") MultipartFile file_excel,
      @RequestParam("name") String name,
      @RequestParam("file_image") MultipartFile file_image,
      @RequestParam("file_image_question") MultipartFile[] file_image_question,
      @RequestParam("file_listening") MultipartFile[] file_listening) {
    List<String> response = new ArrayList<>();
    String rootDirectory = Paths.get("src/main").toAbsolutePath().toString();

    PracticeTest practiceTest = new PracticeTest();
    practicetestService.save(practiceTest);

    try {
      Path pathExcel = Paths.get(rootDirectory + "/resources/static/excel/"  + "practicetest." + practiceTest.getPracticetestid() + "." + file_excel.getOriginalFilename());
      file_excel.transferTo(new File(pathExcel.toString()));
      Path pathImage = Paths.get(rootDirectory + "/resources/static/img/practicetest/"  + "" + practiceTest.getPracticetestid() + "." + file_image.getOriginalFilename());
      file_image.transferTo(new File(pathImage.toString()));
      for (MultipartFile single_listening : file_listening) {
        Path pathImageQuestion = Paths.get(rootDirectory + "/resources/static/img/listening/"  + "" + practiceTest.getPracticetestid() + "." + single_listening.getOriginalFilename());
        single_listening.transferTo(new File(pathImageQuestion.toString()));
      }
      for (MultipartFile single_image : file_image_question) {
        Path pathImageQuestion = Paths.get(rootDirectory + "/resources/static/img/question/" + "" + practiceTest.getPracticetestid() + "." + single_image.getOriginalFilename());
        single_image.transferTo(new File(pathImageQuestion.toString()));
      }
      practiceTest.setPracticetestname(name);
      practiceTest.setPracticetestimage(practiceTest.getPracticetestid() + "." + file_image.getOriginalFilename());
      practicetestService.save(practiceTest);

      // save data from file excel

      AdminPracticeTestAPI adminPracticeTestAPI = new AdminPracticeTestAPI();
      List<QuestionPracticeTest> list = adminPracticeTestAPI.getListFromExcel(pathExcel.toString(), practiceTest);
      for (int i = 0; i < list.size(); i++) {
        questionpracticetestService.save(list.get(i));
      }
    } catch (Exception e) {
      response.add(e.toString());
      System.out.println("ErrorUpdate: " + e);
    }
    return response;
  }

  @RequestMapping(value = "/infoPracticeTest/{practicetestid}")
  public String infoPracticeTestByPracticetestid(@PathVariable("practicetestid") int practicetestid) {
    PracticeTest practiceTest = practicetestService.getPracticeTest(practicetestid).get(0);
    return practiceTest.getPracticetestname();
  }

  public List<QuestionPracticeTest> getListFromExcel(String path_file_excel, PracticeTest practiceTest) {
    List<QuestionPracticeTest> list = new ArrayList<>();

    try {
      FileInputStream excelFile = new FileInputStream(new File(path_file_excel));
      XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
      XSSFSheet worksheet = workbook.getSheetAt(0);

      for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
        QuestionPracticeTest questionPracticeTest = new QuestionPracticeTest();

        XSSFRow row = worksheet.getRow(i);

        if (row.getCell(0) != null)
          questionPracticeTest.setQuestionpracticetestnumber((int) row.getCell(0).getNumericCellValue());

        if (row.getCell(1) != null)
          questionPracticeTest.setQuestionpracticetestimage(
              practiceTest.getPracticetestid() + "." + row.getCell(1).getStringCellValue().toString());

        if (row.getCell(2) != null)
          questionPracticeTest.setQuestionpracticetestaudio(
              practiceTest.getPracticetestid() + "." + row.getCell(2).getStringCellValue().toString());

        if (row.getCell(3) != null)
          questionPracticeTest.setQuestionpracticetestparagraph(row.getCell(3).getStringCellValue().toString());
        if (row.getCell(4) != null)
          questionPracticeTest.setQuestionpracticetestask(row.getCell(4).getStringCellValue().toString());

        if (row.getCell(5) != null && row.getCell(5).getCellType() == row.getCell(5).CELL_TYPE_STRING)
          questionPracticeTest.setQuestionpracticetestanswer1(row.getCell(5).getStringCellValue().toString());
        if (row.getCell(5) != null && row.getCell(5).getCellType() == row.getCell(5).CELL_TYPE_NUMERIC)
          questionPracticeTest.setQuestionpracticetestanswer1(String.valueOf(row.getCell(5).getNumericCellValue()));

        if (row.getCell(6) != null && row.getCell(6).getCellType() == row.getCell(6).CELL_TYPE_STRING)
          questionPracticeTest.setQuestionpracticetestanswer2(row.getCell(6).getStringCellValue().toString());
        if (row.getCell(6) != null && row.getCell(6).getCellType() == row.getCell(6).CELL_TYPE_NUMERIC)
          questionPracticeTest.setQuestionpracticetestanswer2(String.valueOf(row.getCell(6).getNumericCellValue()));

        if (row.getCell(7) != null && row.getCell(7).getCellType() == row.getCell(7).CELL_TYPE_STRING)
          questionPracticeTest.setQuestionpracticetestanswer3(row.getCell(7).getStringCellValue().toString());
        if (row.getCell(7) != null && row.getCell(7).getCellType() == row.getCell(7).CELL_TYPE_NUMERIC)
          questionPracticeTest.setQuestionpracticetestanswer3(String.valueOf(row.getCell(7).getNumericCellValue()));

        if (row.getCell(8) != null && row.getCell(8).getCellType() == row.getCell(8).CELL_TYPE_STRING)
          questionPracticeTest.setQuestionpracticetestanswer4(row.getCell(8).getStringCellValue().toString());
        if (row.getCell(8) != null && row.getCell(8).getCellType() == row.getCell(8).CELL_TYPE_NUMERIC)
          questionPracticeTest.setQuestionpracticetestanswer4(String.valueOf(row.getCell(8).getNumericCellValue()));

        if (row.getCell(9) != null)
          questionPracticeTest.setQuestionpracticetestanswercorrect(row.getCell(9).getStringCellValue().toString());
        questionPracticeTest.setPracticetest(practiceTest);
        list.add(questionPracticeTest);

      }
    } catch (Exception e) {
      System.out.println("Errorhere: " + e);
    }
    return list;
  }
}
