package com.webtoiec.serverwebtoiec.api.admin;

import com.webtoiec.serverwebtoiec.entities.CommentListening;
import com.webtoiec.serverwebtoiec.entities.CommentReading;
import com.webtoiec.serverwebtoiec.entities.Grammar;
import com.webtoiec.serverwebtoiec.entities.Listening;
import com.webtoiec.serverwebtoiec.entities.QuestionGrammar;
import com.webtoiec.serverwebtoiec.entities.QuestionListening;
import com.webtoiec.serverwebtoiec.entities.QuestionReading;
import com.webtoiec.serverwebtoiec.entities.Reading;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.repository.CommentListeningRepo;
import com.webtoiec.serverwebtoiec.repository.CommentReadingRepo;
import com.webtoiec.serverwebtoiec.repository.QuestionReadingRepo;
import com.webtoiec.serverwebtoiec.repository.ReadingRepo;
import com.webtoiec.serverwebtoiec.repository.UserRepo;
import com.webtoiec.serverwebtoiec.service.impl.ReadingService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/reading")
public class AdminReadingAPI {
  @Autowired
  private ReadingService readingService;
  @Autowired
  private ReadingRepo readingRepo;
  @Autowired
  private QuestionReadingRepo questionReadingRepo;
  @Autowired
  private CommentReadingRepo commentReadingRepo;
  @Autowired
  private UserRepo userRepo;

  @GetMapping("/all")
  public ResponseEntity<Page<Reading>> findAllReadingpartAndReadinglevel(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String readingpart, @RequestParam(defaultValue = "") String readinglevel) {
    return new ResponseEntity<>(readingService.findAllListReadingByReadingpartAndReadinglevelAdmin(page, 2, readingpart, readinglevel), HttpStatus.OK);
  }

  @GetMapping("/loadReading")
  public List<Map<String, Object>> showAllGrammar() {
    List<Reading> readingList = readingRepo.findAll();

    List<Map<String, Object>> response = new ArrayList<>();

    for (Reading reading : readingList) {
      Map<String, Object> map = new HashMap<>();
      map.put("readingid", reading.getReadingid());
      map.put("readinglevel", reading.getReadinglevel());
      map.put("readingpart", reading.getReadingpart());
      map.put("readingscript", reading.getReadingscript());
      map.put("readingname", reading.getReadingname());
      response.add(map);
    }
    return response;
  }

  @GetMapping("/{readingid}")
  public ResponseEntity<Reading> findByReadingid(@PathVariable int readingid) {
    Optional<Reading> bn = readingService.findReadingByReadingid(readingid);
    return bn.isPresent() ? new ResponseEntity<>(bn.get(), HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/image/{filename}")
  public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
    try {
      String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8.name());
      Path filePath = Paths.get("src/main/resources/static/img/reading").resolve(decodedFilename).normalize();

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
      Path filePath = Paths.get("src/main/resources/static/audio/reading").resolve(decodedFilename).normalize();
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


  @GetMapping("/loadCommentReading/{readingid}")
  public List<Map<String, Object>> showAllCommentReading(@PathVariable int readingid) {
    // Tìm đối tượng Vocabulary từ vocabularyid
    Reading reading = readingRepo.findById(readingid).orElse(null);
    if (reading == null) {
      return Collections.emptyList();  // Trả về danh sách rỗng nếu không tìm thấy vocabulary
    }

    // Tìm danh sách CommentVocabulary dựa trên đối tượng vocabulary
    List<CommentReading> list = commentReadingRepo.findByReading(reading);

    // Xây dựng danh sách phản hồi
    List<Map<String, Object>> response = new ArrayList<>();
    for (CommentReading comment : list) {
      Map<String, Object> commentMap = new HashMap<>();
      commentMap.put("commentreadingid", comment.getCommentreadingid());
      commentMap.put("commentreadingcontent", comment.getCommentreadingcontent());
      commentMap.put("commentreadingtime", comment.getCommentreadingtime());
      commentMap.put("readingid", comment.getReading().getReadingid());
      commentMap.put("id", comment.getUser().getId());
      commentMap.put("name", comment.getUser().getUsername());
      response.add(commentMap);
    }

    return response;
  }

  @GetMapping("/loadQuestionReading")
  public List<Map<String, Object>> showAllQuestionReading(
      @RequestParam("readingid") int readingid
  ) {
    Reading reading = readingRepo.findById(readingid).orElse(null);
    if (reading == null) {
      return Collections.emptyList();
    }
    List<QuestionReading> list = questionReadingRepo.findByReading(reading);
    System.out.println(list);

    List<Map<String, Object>> response = new ArrayList<>();
    for (QuestionReading questionReading : list) {
      Map<String, Object> map = new HashMap<>();
      map.put("questionreadingid", questionReading.getQuestionreadingid());
      map.put("questionreadingask", questionReading.getQuestionreadingask());
      map.put("questionreadinganswercorrect", questionReading.getQuestionreadinganswercorrect());
      map.put("questionreadinganswer1", questionReading.getQuestionreadinganswer1());
      map.put("questionreadinganswer2", questionReading.getQuestionreadinganswer2());
      map.put("questionreadinganswer3", questionReading.getQuestionreadinganswer3());
      map.put("questionreadinganswer4", questionReading.getQuestionreadinganswer4());
      map.put("questionreadingexplain", questionReading.getQuestionreadingexplain());
      map.put("questionreadingserial", questionReading.getQuestionreadingserial());
      map.put("questionreadingscript", questionReading.getQuestionreadingscript());

      response.add(map);
    }
    return response;
  }

  // API tạo bình luận cho bài viết Vocabulary
  @PostMapping("/createCommentReading")
  public ResponseEntity<?> createCommentListening(
      @RequestParam int readingid,
      @RequestParam int id,
      @RequestParam String commentreadingcontent
  ) {
    try {
      // Kiểm tra xem từ vựng (Vocabulary) có tồn tại không
      Reading reading = readingRepo.findById(readingid)
          .orElseThrow(() -> new RuntimeException("Vocabulary with id " + readingid + " not found"));

      // Kiểm tra xem người dùng (UserToeic) có tồn tại không
      UserToeic user = userRepo.findById(id)
          .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));

      // Tạo đối tượng CommentVocabulary
      CommentReading commentReading = new CommentReading();
      commentReading.setCommentreadingcontent(commentreadingcontent);
      commentReading.setReading(reading);  // Gán từ vựng cho bình luận
      commentReading.setUser(user);  // Gán người dùng cho bình luận
      commentReading.setCommentreadingtime(new Date(System.currentTimeMillis()));  // Gán thời gian bình luận

      // Lưu bình luận vào cơ sở dữ liệu
      CommentReading savedComment = commentReadingRepo.save(commentReading);

      // Trả về bình luận đã lưu
      return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    } catch (Exception e) {
      // Log lỗi và trả về phản hồi lỗi
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating comment: " + e.getMessage());
    }
  }

  @PostMapping(value = "/save", consumes = "multipart/form-data")
  public List<String> saveReading(@RequestParam("readingname")String readingname,
      @RequestParam("readinglevel") int readinglevel,
      @RequestParam("readingpart") int readingpart,
      @RequestParam("readingscript") String readingscript,
      @RequestParam("file_questionreading") MultipartFile file_questionreading
      ) {
    List<String> response = new ArrayList<>();
    String rootDirectory = Paths.get("src/main").toAbsolutePath().toString();

    Reading reading = new Reading();
    readingRepo.save(reading);

    try {

      Path pathExcelQuestion = Paths.get(rootDirectory + "/resources/static/excel/listening/question/" + "listening." + reading.getReadingid() + "." + file_questionreading.getOriginalFilename());
      file_questionreading.transferTo(new File(pathExcelQuestion.toString()));
      reading.setReadinglevel(readinglevel);
      reading.setReadingpart(readingpart);
      reading.setReadingname(readingname);
      reading.setReadingscript(readingscript);

      readingRepo.save(reading);

      AdminReadingAPI adminReadingAPI = new AdminReadingAPI();
      List<QuestionReading> list = adminReadingAPI.getListFromExcel(pathExcelQuestion.toString(), reading);
      for (int i = 0; i < list.size(); i++) {
        questionReadingRepo.save(list.get(i));
      }

    } catch (IllegalStateException | IOException e) {
      response.add(e.toString());
      System.out.println("ErrorReadFileExcel: " + e);
    }
    return response;
  }

  @DeleteMapping("/delete/{readingid}")
  public ResponseEntity<?> deleteByReadingid(@PathVariable int readingid) {
    readingService.deleteReading(readingid);
    return new ResponseEntity<>(HttpStatus.OK);
  }

//  private void saveFileForReading(Reading rd, HttpServletRequest request)
//      throws IllegalStateException, IOException {
//    MultipartFile fileImage = rd.getReadingimage();
//    MultipartFile fileExcel = rd.getReadingfileExcelQuestion();
//    String rootDirectory = request.getSession().getServletContext().getRealPath("/");
//    Path pathImage = Paths.get(rootDirectory + "/resources/file/images/reading/readingid=" + rd.getReadingid() + ".png");
//    Path pathExcel = Paths.get(rootDirectory + "/resources/file/excel/listening/readingid=" + rd.getReadingid() + ".xlsx");
//    fileImage.transferTo(new File(pathImage.toString()));
//    fileExcel.transferTo(new File(pathExcel.toString()));
//
//  }

  public List<QuestionReading> getListFromExcel(String path_file_excel, Reading reading) {
    List<QuestionReading> list = new ArrayList<>();

    try {
      FileInputStream excelFile = new FileInputStream(new File(path_file_excel));
      XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
      XSSFSheet worksheet = workbook.getSheetAt(0);

      for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
        QuestionReading questionReading = new QuestionReading();

        XSSFRow row = worksheet.getRow(i);

        if (row.getCell(0) != null)
          questionReading.setQuestionreadingserial(String.valueOf(row.getCell(0).getNumericCellValue()));

        if (row.getCell(1) != null)
          questionReading.setQuestionreadingask(row.getCell(1).getStringCellValue());

        if (row.getCell(2) != null && row.getCell(2).getCellType() == row.getCell(2).CELL_TYPE_STRING)
          questionReading.setQuestionreadinganswercorrect(row.getCell(2).getStringCellValue().toString());
        if (row.getCell(2) != null && row.getCell(2).getCellType() == row.getCell(2).CELL_TYPE_NUMERIC)
          questionReading.setQuestionreadinganswercorrect(String.valueOf(row.getCell(2).getNumericCellValue()));

        if (row.getCell(3) != null && row.getCell(3).getCellType() == row.getCell(3).CELL_TYPE_STRING)
          questionReading.setQuestionreadinganswer1(row.getCell(3).getStringCellValue().toString());
        if (row.getCell(3) != null && row.getCell(3).getCellType() == row.getCell(3).CELL_TYPE_NUMERIC)
          questionReading.setQuestionreadinganswer1(String.valueOf(row.getCell(3).getNumericCellValue()));

        if (row.getCell(4) != null && row.getCell(4).getCellType() == row.getCell(4).CELL_TYPE_STRING)
          questionReading.setQuestionreadinganswer2(row.getCell(4).getStringCellValue().toString());
        if (row.getCell(4) != null && row.getCell(4).getCellType() == row.getCell(4).CELL_TYPE_NUMERIC)
          questionReading.setQuestionreadinganswer2(String.valueOf(row.getCell(4).getNumericCellValue()));

        if (row.getCell(5) != null && row.getCell(5).getCellType() == row.getCell(5).CELL_TYPE_STRING)
          questionReading.setQuestionreadinganswer3(row.getCell(5).getStringCellValue().toString());
        if (row.getCell(5) != null && row.getCell(5).getCellType() == row.getCell(5).CELL_TYPE_NUMERIC)
          questionReading.setQuestionreadinganswer3(String.valueOf(row.getCell(5).getNumericCellValue()));

        if (row.getCell(6) != null && row.getCell(6).getCellType() == row.getCell(6).CELL_TYPE_STRING)
          questionReading.setQuestionreadinganswer4(row.getCell(6).getStringCellValue().toString());
        if (row.getCell(6) != null && row.getCell(6).getCellType() == row.getCell(6).CELL_TYPE_NUMERIC)
          questionReading.setQuestionreadinganswer4(String.valueOf(row.getCell(6).getNumericCellValue()));

        if (row.getCell(7) != null)
          questionReading.setQuestionreadingexplain(row.getCell(7).getStringCellValue());

        if (row.getCell(8) != null)
          questionReading.setQuestionreadingscript(row.getCell(8).getStringCellValue());

        questionReading.setReading(reading);
        list.add(questionReading);

      }
    } catch (Exception e) {
      System.out.println("Errorhere: " + e);
    }
    return list;
  }


}
