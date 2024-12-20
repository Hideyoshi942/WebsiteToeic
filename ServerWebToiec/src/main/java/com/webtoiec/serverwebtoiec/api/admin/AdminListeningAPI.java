package com.webtoiec.serverwebtoiec.api.admin;

import com.webtoiec.serverwebtoiec.entities.CommentListening;
import com.webtoiec.serverwebtoiec.entities.CommentVocabulary;
import com.webtoiec.serverwebtoiec.entities.Listening;
import com.webtoiec.serverwebtoiec.entities.PracticeTest;
import com.webtoiec.serverwebtoiec.entities.QuestionListening;
import com.webtoiec.serverwebtoiec.entities.QuestionPracticeTest;
import com.webtoiec.serverwebtoiec.entities.QuestionReading;
import com.webtoiec.serverwebtoiec.entities.Reading;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import com.webtoiec.serverwebtoiec.repository.CommentListeningRepo;
import com.webtoiec.serverwebtoiec.repository.ListeningRepo;
import com.webtoiec.serverwebtoiec.repository.QuestionGrammarRepo;
import com.webtoiec.serverwebtoiec.repository.QuestionListeningRepo;
import com.webtoiec.serverwebtoiec.repository.UserRepo;
import com.webtoiec.serverwebtoiec.service.impl.ListeningService;
import com.webtoiec.serverwebtoiec.service.impl.QuestionListeningService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/listening")
public class AdminListeningAPI {

  @Autowired
  private ListeningService listeningService;
  @Autowired
  private QuestionListeningRepo questionListeningRepo;
  @Autowired
  private ListeningRepo listeningRepo;
  @Autowired
  private CommentListeningRepo commentListeningRepo;
  @Autowired
  private UserRepo userRepo;

  @GetMapping("/all")
  public ResponseEntity<Page<Listening>> findAllByListeningpartAndListeninglevel(@RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "") String listeningpart, @RequestParam(defaultValue = "") String listeninglevel) {
    return new ResponseEntity<>(listeningService.findAllListListeningByListeningpartAndListeninglevelAdmin(listeningpart, listeninglevel, page, 6),
        HttpStatus.OK);
  }

  @GetMapping("/loadListening")
  public List<Map<String, Object>> showAllListening() {
    List<Listening> listeningList = listeningRepo.findAll();

    List<Map<String, Object>> response = new ArrayList<>();

    for (Listening listening : listeningList) {
      Map<String, Object> map = new HashMap<>();
      map.put("listeningid", listening.getListeningid());
      map.put("listeninglevel", listening.getListeninglevel());
      map.put("listeningpart", listening.getListeningpart());
      map.put("listeningscript", listening.getListeningscript());
      map.put("listeningname", listening.getListeningname());
      response.add(map);
    }
    return response;
  }

  @GetMapping("/{listeningid}")
  public ResponseEntity<Listening> findByListeningid(@PathVariable("listeningid") int listeningid) {
    Optional<Listening> bn = listeningService.findListeningByListeningid(listeningid);
    return bn.isPresent() ? new ResponseEntity<>(bn.get(), HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/loadCommentListening/{listeningid}")
  public List<Map<String, Object>> showAllCommentListening(@PathVariable int listeningid) {
    // Tìm đối tượng Vocabulary từ vocabularyid
    Listening listening = listeningRepo.findById(listeningid).orElse(null);
    if (listening == null) {
      return Collections.emptyList();  // Trả về danh sách rỗng nếu không tìm thấy vocabulary
    }

    // Tìm danh sách CommentVocabulary dựa trên đối tượng vocabulary
    List<CommentListening> list = commentListeningRepo.findByListening(listening);

    // Xây dựng danh sách phản hồi
    List<Map<String, Object>> response = new ArrayList<>();
    for (CommentListening comment : list) {
      Map<String, Object> commentMap = new HashMap<>();
      commentMap.put("commentlisteningid", comment.getCommentlisteningid());
      commentMap.put("commentlisteningcontent", comment.getCommentlisteningcontent());
      commentMap.put("commentlisteningtime", comment.getCommentlisteningtime());
      commentMap.put("listeningid", comment.getListening().getListeningid());
      commentMap.put("id", comment.getId().getId());
      commentMap.put("name", comment.getId().getUsername());
      response.add(commentMap);
    }

    return response;
  }

  @GetMapping("/loadQuestionListening")
  public List<Map<String, Object>> showAllQuestionListening(
      @RequestParam("listeningid") int listeningid
  ) {
    Listening listening = listeningRepo.findById(listeningid).orElse(null);
    if (listening == null) {
      return Collections.emptyList();
    }

    List<QuestionListening> list = questionListeningRepo.findByListening(listening);
    System.out.println(list);

    List<Map<String, Object>> response = new ArrayList<>();
    for (QuestionListening questionListening : list) {
      Map<String, Object> map = new HashMap<>();
      map.put("questionlisteningid", questionListening.getQuestionlisteningid());
      map.put("questionlisteningask", questionListening.getQuestionlisteningask());
      map.put("questionlisteninganswercorrect", questionListening.getQuestionlisteninganswercorrect());
      map.put("questionlisteninganswer1", questionListening.getQuestionlisteninganswer1());
      map.put("questionlisteninganswer2", questionListening.getQuestionlisteninganswer2());
      map.put("questionlisteninganswer3", questionListening.getQuestionlisteninganswer3());
      map.put("questionlisteninganswer4", questionListening.getQuestionlisteninganswer4());
      map.put("questionlisteningexplain", questionListening.getQuestionlisteningexplain());
      map.put("questionlisteningserial", questionListening.getQuestionlisteningserial());
      map.put("questionlisteningscript", questionListening.getQuestionlisteningscript());

      // Kiểm tra giá trị null cho questionlisteningaudio
      String audioPath = questionListening.getQuestionlisteningaudio();
      if (audioPath != null && !audioPath.isEmpty()) {
        map.put("questionlisteningaudio", "/api/admin/listening/audio/" + URLEncoder.encode(audioPath, StandardCharsets.UTF_8) + ".mp3");
      } else {
        map.put("questionlisteningaudio", null);
      }

      // Kiểm tra giá trị null cho questionlisteningimage
      String imagePath = questionListening.getQuestionlisteningimage();
      if (imagePath != null && !imagePath.isEmpty()) {
        map.put("questionlisteningimage", "/api/admin/listening/image/" + URLEncoder.encode(imagePath, StandardCharsets.UTF_8));
      } else {
        map.put("questionlisteningimage", null);
      }

      response.add(map);
    }
    return response;
  }


  @GetMapping("/image/{filename}")
  public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
    try {
      String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8.name());
      Path filePath = Paths.get("src/main/resources/static/img/listening").resolve(decodedFilename).normalize();
      System.out.println("File path being accessed: " + filePath.toString());
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
      Path filePath = Paths.get("src/main/resources/static/audio/listening").resolve(decodedFilename).normalize();
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


  // API tạo bình luận cho bài viết Vocabulary
  @PostMapping("/createCommentListening")
  public ResponseEntity<?> createCommentListening(
      @RequestParam int listeningid,
      @RequestParam int id,
      @RequestParam String commentlisteningcontent
  ) {
    try {
      // Kiểm tra xem từ vựng (Vocabulary) có tồn tại không
      Listening listening = listeningRepo.findById(listeningid)
          .orElseThrow(() -> new RuntimeException("Vocabulary with id " + listeningid + " not found"));

      // Kiểm tra xem người dùng (UserToeic) có tồn tại không
      UserToeic user = userRepo.findById(id)
          .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));

      // Tạo đối tượng CommentVocabulary
      CommentListening commentListening = new CommentListening();
      commentListening.setCommentlisteningcontent(commentlisteningcontent);
      commentListening.setListening(listening);  // Gán từ vựng cho bình luận
      commentListening.setId(user);  // Gán người dùng cho bình luận
      commentListening.setCommentlisteningtime(new Date(System.currentTimeMillis()));  // Gán thời gian bình luận

      // Lưu bình luận vào cơ sở dữ liệu
      CommentListening savedComment = commentListeningRepo.save(commentListening);

      // Trả về bình luận đã lưu
      return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    } catch (Exception e) {
      // Log lỗi và trả về phản hồi lỗi
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating comment: " + e.getMessage());
    }
  }

  @PostMapping(value = "/save", consumes = "multipart/form-data")
  @ResponseBody
  public List<String> saveBaiNghe(@RequestParam("listeninglevel") int listeninglevel,
      @RequestParam("listeningpart") int listeningpart,
      @RequestParam("listeningname") String listeningname,
      @RequestParam("file_questionlistening") MultipartFile file_questionlistening,
      @RequestParam("file_questionlisteningaudio") MultipartFile[] file_questionlisteningaudio,
      @RequestParam("file_questionlisteningimage") MultipartFile[] file_questionlisteningimage
  ) {
    List<String> response = new ArrayList<>();
    String rootDirectory = Paths.get("src/main").toAbsolutePath().toString();

     Listening listening = new Listening();
     listeningRepo.save(listening);

    try {

      Path pathExcelQuestion = Paths.get(rootDirectory + "/resources/static/excel/listening/question/" + "listening." + listening.getListeningid() + "." + file_questionlistening.getOriginalFilename());
      file_questionlistening.transferTo(new File(pathExcelQuestion.toString()));
      for (MultipartFile single_listening : file_questionlisteningaudio) {
        Path pathAudioQuestion = Paths.get(rootDirectory + "/resources/static/audio/listening/"   + listening.getListeningid() + "." + single_listening.getOriginalFilename());
        single_listening.transferTo(new File(pathAudioQuestion.toString()));
      }
      for (MultipartFile single_listening : file_questionlisteningimage) {
        Path pathImmageQuestion = Paths.get(rootDirectory + "/resources/static/img/listening/"   + listening.getListeningid() + "." + single_listening.getOriginalFilename());
        single_listening.transferTo(new File(pathImmageQuestion.toString()));
      }
      listening.setListeninglevel(listeninglevel);
      listening.setListeningpart(listeningpart);
      listening.setListeningname(listeningname);
      listeningRepo.save(listening);

      AdminListeningAPI adminListeningAPI = new AdminListeningAPI();
      List<QuestionListening> list = adminListeningAPI.getListFromExcel(pathExcelQuestion.toString(), listening);
      for (int i = 0; i < list.size(); i++) {
        questionListeningRepo.save(list.get(i));
      }

    } catch (IllegalStateException | IOException e) {
      response.add(e.toString());
      System.out.println("ErrorReadFileExcel: " + e);
    }
    return response;
  }

  @DeleteMapping("/delete/{listeningid}")
  public ResponseEntity<?> deleteById(@PathVariable int listeningid) {
    listeningService.deleteListening(listeningid);
    return new ResponseEntity<>(HttpStatus.OK);
  }

//  // lưu ảnh và audio của bài nghe vào thư mục
//  private void saveFileForListening(Listening ltn, HttpServletRequest request) throws IllegalStateException, IOException {
//    MultipartFile filaAudio = ltn.getListeningaudio();
//    MultipartFile fileImage = ltn.getListeningimage();
//    MultipartFile fileExcel = ltn.getListeningfileExcelQuestion();
//    String rootDirectory = System.getProperty("user.dir");
//    if (fileImage != null && !fileImage.isEmpty()) {
//      Path pathImage = Paths.get(rootDirectory + "/resources/static/img/listening/listeningid=" + ltn.getListeningid() + ".png");
//      fileImage.transferTo(new File(pathImage.toString()));
//    }
//    if (filaAudio != null && !filaAudio.isEmpty()) {
//      Path pathAudio = Paths.get(rootDirectory + "/resources/static/audio/listening/listeningid=" + ltn.getListeningid() + ".mp3");
//      filaAudio.transferTo(new File(pathAudio.toString()));
//    }
//    if (fileExcel != null && !fileExcel.isEmpty()) {
//      Path pathExcel = Paths.get(rootDirectory + "/resources/static/excel/listening/listeningid=" + ltn.getListeningid() + ".xlsx");
//      fileExcel.transferTo(new File(pathExcel.toString()));
//    }
//  }

  public List<QuestionListening> getListFromExcel(String path_file_excel, Listening listening) {
    List<QuestionListening> list = new ArrayList<>();

    try {
      FileInputStream excelFile = new FileInputStream(new File(path_file_excel));
      XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
      XSSFSheet worksheet = workbook.getSheetAt(0);

      for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
        QuestionListening questionListening = new QuestionListening();

        XSSFRow row = worksheet.getRow(i);

        if (row.getCell(0) != null)
          questionListening.setQuestionlisteningserial(String.valueOf(row.getCell(0).getNumericCellValue()));

        if (row.getCell(1) != null)
          questionListening.setQuestionlisteningask(row.getCell(1).getStringCellValue());

        if (row.getCell(2) != null && row.getCell(2).getCellType() == row.getCell(2).CELL_TYPE_STRING)
          questionListening.setQuestionlisteninganswercorrect(row.getCell(2).getStringCellValue().toString());
        if (row.getCell(2) != null && row.getCell(2).getCellType() == row.getCell(2).CELL_TYPE_NUMERIC)
          questionListening.setQuestionlisteninganswercorrect(String.valueOf(row.getCell(2).getNumericCellValue()));

        if (row.getCell(3) != null && row.getCell(3).getCellType() == row.getCell(3).CELL_TYPE_STRING)
          questionListening.setQuestionlisteninganswer1(row.getCell(3).getStringCellValue().toString());
        if (row.getCell(3) != null && row.getCell(3).getCellType() == row.getCell(3).CELL_TYPE_NUMERIC)
          questionListening.setQuestionlisteninganswer1(String.valueOf(row.getCell(3).getNumericCellValue()));

        if (row.getCell(4) != null && row.getCell(4).getCellType() == row.getCell(4).CELL_TYPE_STRING)
          questionListening.setQuestionlisteninganswer2(row.getCell(4).getStringCellValue().toString());
        if (row.getCell(4) != null && row.getCell(4).getCellType() == row.getCell(4).CELL_TYPE_NUMERIC)
          questionListening.setQuestionlisteninganswer2(String.valueOf(row.getCell(4).getNumericCellValue()));

        if (row.getCell(5) != null && row.getCell(5).getCellType() == row.getCell(5).CELL_TYPE_STRING)
          questionListening.setQuestionlisteninganswer3(row.getCell(5).getStringCellValue().toString());
        if (row.getCell(5) != null && row.getCell(5).getCellType() == row.getCell(5).CELL_TYPE_NUMERIC)
          questionListening.setQuestionlisteninganswer3(String.valueOf(row.getCell(5).getNumericCellValue()));

        if (row.getCell(6) != null && row.getCell(6).getCellType() == row.getCell(6).CELL_TYPE_STRING)
          questionListening.setQuestionlisteninganswer4(row.getCell(6).getStringCellValue().toString());
        if (row.getCell(6) != null && row.getCell(6).getCellType() == row.getCell(6).CELL_TYPE_NUMERIC)
          questionListening.setQuestionlisteninganswer4(String.valueOf(row.getCell(6).getNumericCellValue()));

        if (row.getCell(7) != null)
          questionListening.setQuestionlisteningexplain(row.getCell(7).getStringCellValue());


        if (row.getCell(8) != null)
          questionListening.setQuestionlisteningimage(
              listening.getListeningid() + "." + row.getCell(8).getStringCellValue().toString());

        if (row.getCell(9) != null)
          questionListening.setQuestionlisteningaudio(
              listening.getListeningid() + "." + row.getCell(9).getStringCellValue().toString());

        if (row.getCell(10) != null)
          questionListening.setQuestionlisteningscript(row.getCell(10).getStringCellValue());

        questionListening.setListening(listening);
        list.add(questionListening);

      }
    } catch (Exception e) {
      System.out.println("Errorhere: " + e);
    }
    return list;
  }

}
