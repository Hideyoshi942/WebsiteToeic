package com.webtoiec.serverwebtoiec.api.admin;

import com.webtoiec.serverwebtoiec.entities.CommentGrammar;
import com.webtoiec.serverwebtoiec.entities.CommentVocabulary;
import com.webtoiec.serverwebtoiec.entities.Grammar;
import com.webtoiec.serverwebtoiec.entities.QuestionGrammar;
import com.webtoiec.serverwebtoiec.entities.QuestionVocabulary;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import com.webtoiec.serverwebtoiec.repository.CommentGrammarRepo;
import com.webtoiec.serverwebtoiec.repository.GrammarRepo;
import com.webtoiec.serverwebtoiec.repository.QuestionGrammarRepo;
import com.webtoiec.serverwebtoiec.repository.UserRepo;
import com.webtoiec.serverwebtoiec.service.CommentGrammarImpl;
import com.webtoiec.serverwebtoiec.service.GrammarImpl;
import com.webtoiec.serverwebtoiec.service.impl.QuestionGrammarService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/grammar")
public class AdminGrammarAPI {
  @Autowired
  private HttpServletRequest request;

  @Autowired
  private GrammarImpl grammarInterface;
  @Autowired
  private QuestionGrammarService questionGrammarService;
  @Autowired
  private QuestionGrammarRepo questionGrammarRepo;
  @Autowired
  private GrammarRepo grammarRepo;
  @Autowired
  private CommentGrammarImpl commentGrammarImpl;
  @Autowired
  private UserRepo userRepo;
  @Autowired
  private CommentGrammarRepo commentGrammarRepo;

  @GetMapping("/loadGrammar")
  public List<Map<String, Object>> showAllGrammar() {
    List<Grammar> grammarList = grammarInterface.getAllGrammar();

    List<Map<String, Object>> response = new ArrayList<>();

    for (Grammar grammar : grammarList) {
      Map<String, Object> map = new HashMap<>();
      map.put("grammarid", grammar.getGrammarid());
      map.put("grammarname", grammar.getGrammarname());
      map.put("grammarimage", "/api/admin/grammar/image/grammarid=" + URLEncoder.encode(grammar.getGrammarimage(), StandardCharsets.UTF_8));
      map.put("grammarcontenthtml", grammar.getGrammarcontenthtml());
      map.put("grammarcontenthtmlmarkdown", grammar.getGrammarcontentmarkdown());
      response.add(map);
    }
    return response;
  }

  @GetMapping("/loadGrammar/{grammarid}")
  public ResponseEntity<?> showGrammarById(@PathVariable int grammarid) {
    Grammar grammar = grammarRepo.findById(grammarid).orElse(null);
    if (grammar == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Grammar not found"));
    }

    Map<String, Object> map = new HashMap<>();
    map.put("grammarid", grammar.getGrammarid());
    map.put("grammarname", grammar.getGrammarname());
    map.put("grammarimage", "/api/admin/grammar/image/grammarid=" + URLEncoder.encode(grammar.getGrammarimage(), StandardCharsets.UTF_8));
    map.put("grammarcontenthtml", grammar.getGrammarcontenthtml());
    map.put("grammarcontenthtmlmarkdown", grammar.getGrammarcontentmarkdown());

    return ResponseEntity.ok(map);
  }

  @GetMapping("/loadQuestionGrammar")
  public List<Map<String, Object>> showAllQuestionGrammar(
      @RequestParam("grammarid") int grammarid
  ) {
    Grammar grammar = grammarRepo.findById(grammarid).orElse(null);
    if (grammar == null) {
      return Collections.emptyList();
    }
    List<QuestionGrammar> list = questionGrammarService.findListQuestionByGammarId(grammar);
    System.out.println(list);

    List<Map<String, Object>> response = new ArrayList<>();
    for (QuestionGrammar questionGrammar : list) {
      Map<String, Object> map = new HashMap<>();
      map.put("questiongrammarid", questionGrammar.getQuestiongrammarid());
      map.put("questiongrammarask", questionGrammar.getQuestiongrammarask());
      map.put("questiongrammaranswercorrect", questionGrammar.getQuestiongrammaranswercorrect());
      map.put("questiongrammaranswer1", questionGrammar.getQuestiongrammaranswer1());
      map.put("questiongrammaranswer2", questionGrammar.getQuestiongrammaranswer2());
      map.put("questiongrammaranswer3", questionGrammar.getQuestiongrammaranswer3());
      map.put("questiongrammaranswer4", questionGrammar.getQuestiongrammaranswer4());
      map.put("questiongrammarexplain", questionGrammar.getQuestiongrammarexplain());
      map.put("questiongrammarserial", questionGrammar.getQuestiongrammarserial());
      response.add(map);
    }
    return response;
  }

  @GetMapping("/image/{filename}")
  public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
    try {
      String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8.name());
      Path filePath = Paths.get("src/main/resources/static/img/grammar").resolve(decodedFilename).normalize();

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

  @GetMapping("/loadCommentGrammar/{grammarid}")
  public List<Map<String, Object>> showAllCommentGrammar(@PathVariable int grammarid) {
    // Tìm đối tượng Vocabulary từ vocabularyid
    Grammar grammar = grammarRepo.findById(grammarid).orElse(null);
    if (grammar == null) {
      return Collections.emptyList();  // Trả về danh sách rỗng nếu không tìm thấy vocabulary
    }

    // Tìm danh sách CommentVocabulary dựa trên đối tượng vocabulary
    List<CommentGrammar> list = commentGrammarImpl.findByGramamar(grammar);
    // Xây dựng danh sách phản hồi
    List<Map<String, Object>> response = new ArrayList<>();
    for (CommentGrammar comment : list) {
      Map<String, Object> commentMap = new HashMap<>();
      commentMap.put("commentgrammarid", comment.getCommentgrammarid());
      commentMap.put("commentgrammarcontent", comment.getCommentgrammarcontent());
      commentMap.put("commentgrammartime", comment.getCommentgrammartime());
      commentMap.put("grammarid", comment.getGrammar().getGrammarid());
      commentMap.put("id", comment.getUser().getId());
      commentMap.put("name", comment.getUser().getUsername());
      response.add(commentMap);
    }
    System.out.println(response);
    return response;
  }

  // API tạo bình luận cho bài viết Vocabulary
  @PostMapping("/createCommentGrammar")
  public ResponseEntity<?> createCommentVocab(
      @RequestParam int grammarid,
      @RequestParam int id,
      @RequestParam String commmentgrammarcontent
  ) {
    try {
      // Kiểm tra xem từ vựng (Vocabulary) có tồn tại không
      Grammar grammar = grammarRepo.findById(grammarid)
          .orElseThrow(() -> new RuntimeException("Vocabulary with id " + grammarid + " not found"));

      // Kiểm tra xem người dùng (UserToeic) có tồn tại không
      UserToeic user = userRepo.findById(id)
          .orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));

      // Tạo đối tượng CommentVocabulary
      CommentGrammar commentGram = new CommentGrammar();
      commentGram.setCommentgrammarcontent(commmentgrammarcontent);
      commentGram.setGrammar(grammar);  // Gán từ vựng cho bình luận
      commentGram.setUser(user);  // Gán người dùng cho bình luận
      commentGram.setCommentgrammartime(new Date(System.currentTimeMillis()));  // Gán thời gian bình luận

      // Lưu bình luận vào cơ sở dữ liệu
      CommentGrammar savedComment = commentGrammarRepo.save(commentGram);

      // Trả về bình luận đã lưu
      return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    } catch (Exception e) {
      // Log lỗi và trả về phản hồi lỗi
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating comment: " + e.getMessage());
    }
  }

  @RequestMapping(value = "/delete/{grammarid}")
  public ResponseEntity<?> deleteById(@PathVariable("grammarid") int idGrammar) {
    try {
      grammarInterface.delete(idGrammar);
      return ResponseEntity.ok(Collections.singletonMap("message", "success"));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("error", e.getMessage()));
    }
  }

  // get info Grammar ->edit Grammar
  @RequestMapping(value = "/infoGrammar/{grammar_id}")
  public ResponseEntity<?> infoGrammarById(@PathVariable("grammar_id") int idGrammar) {
    try {
      Grammar grammar = grammarInterface.getGrammar(idGrammar).get(0);
      Map<String, Object> response = new HashMap<>();
      response.put("grammarname", grammar.getGrammarname());
      response.put("grammarimage", grammar.getGrammarimage());
      response.put("grammarcontentmarkdown", grammar.getGrammarcontentmarkdown());

      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body(Collections.singletonMap("error", e.getMessage()));
    }
  }

  @PostMapping("/save")
  @ResponseBody
  public List<String> addGrammar(
      @RequestParam("grammarname") String grammarname,
      @RequestParam("grammarimage") MultipartFile grammarimage,
      @RequestParam("grammarcontenthtml") String grammarcontenthtml,
      @RequestParam("grammarcontentmarkdown") String grammarcontentmarkdown,
      @RequestParam("file_questiongrammar") MultipartFile file_questiongrammar
  ) {
    List<String> response = new ArrayList<String>();

    String rootDirectory = System.getProperty("user.dir");

    Grammar grammar = new Grammar();

    grammarInterface.save(grammar);

    try {
      // save file upload to local folder
      Path pathImage = Paths.get( rootDirectory + "/src/main/resources/static/img/grammar/" + "grammarid=" + grammar.getGrammarid() + "." + grammarimage.getOriginalFilename());
      grammarimage.transferTo(new File(pathImage.toString()));

      // Excel question vocab
      Path pathExcelQuestion = Paths.get(rootDirectory + "/src/main/resources/static/excel/grammar/question/" + "grammar." + grammar.getGrammarid() + "." + file_questiongrammar.getOriginalFilename());
      file_questiongrammar.transferTo(new File(pathExcelQuestion.toString()));

      grammar.setGrammarimage(grammar.getGrammarid() + "." + grammarimage.getOriginalFilename());

      grammar.setGrammarname(grammarname);
      grammar.setGrammarcontentmarkdown(grammarcontentmarkdown);
      grammar.setGrammarcontenthtml(grammarcontenthtml);

      grammarInterface.save(grammar);

      AdminGrammarAPI adminGrammarAPI = new AdminGrammarAPI();
      List<QuestionGrammar> list = adminGrammarAPI.getListFromExcelForQuestion(pathExcelQuestion.toString(), grammar);
      for (int i = 0; i < list.size(); i++) {
        questionGrammarRepo.save(list.get(i));
      }
    } catch (Exception e) {
      response.add(e.toString());
      System.out.println("ErrorAddGrammar: " + e);
    }
    return response;
  }

  @PutMapping("/update")
  @ResponseBody
  public List<String> updateGrammar(
      @RequestParam("grammarid") int grammarid,
      @RequestParam("grammarname") String grammarname,
      @RequestParam("grammarimage") MultipartFile grammarimage,
      @RequestParam("grammarcontenthtml") String grammarcontenthtml,
      @RequestParam("grammarcontentmarkdown") String grammarcontentmarkdown,
      @RequestParam("file_questiongrammar") MultipartFile file_questiongrammar
  ) {
    List<String> response = new ArrayList<String>();

    String rootDirectory = Paths.get("src/main").toAbsolutePath().toString();
    Grammar grammar = grammarInterface.getGrammar(grammarid).get(0);
    grammarInterface.save(grammar);

    try {
      // save file upload to local folder
      Path pathImage = Paths.get(rootDirectory + "/src/main/resources/static/img/grammar/" + "grammar." + grammar.getGrammarid() + "." + grammarimage.getOriginalFilename());
      grammarimage.transferTo(new File(pathImage.toString()));
      // Excel question vocab
      Path pathExcelQuestion = Paths.get(rootDirectory + "/src/main/resources/static/excel/grammar/question/" + "grammar." + grammar.getGrammarid() + "." + file_questiongrammar.getOriginalFilename());
      file_questiongrammar.transferTo(new File(pathExcelQuestion.toString()));

      grammar.setGrammarimage(grammar.getGrammarid() + "." + grammarimage.getOriginalFilename());
      grammar.setGrammarname(grammarname);
      grammar.setGrammarcontentmarkdown(grammarcontentmarkdown);
      grammar.setGrammarcontenthtml(grammarcontenthtml);

      grammarInterface.save(grammar);
      AdminGrammarAPI adminGrammarAPI = new AdminGrammarAPI();
      List<QuestionGrammar> list = adminGrammarAPI.getListFromExcelForQuestion(pathExcelQuestion.toString(), grammar);
      for (int i = 0; i < list.size(); i++) {
        questionGrammarRepo.save(list.get(i));
      }
    } catch (Exception e) {
      response.add(e.toString());
      System.out.println("ErrorAddGrammar: " + e);
    }

    return response;

  }

  public List<QuestionGrammar> getListFromExcelForQuestion(String path, Grammar grammar) {
    List<QuestionGrammar> list = new ArrayList<>();

    try {
      FileInputStream excelFile = new FileInputStream(new File(path));
      XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
      XSSFSheet sheet = workbook.getSheetAt(0);
      for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
        QuestionGrammar questionGrammar = new QuestionGrammar();

        XSSFRow row = sheet.getRow(i);
        if (row.getCell(0) != null)
          questionGrammar.setQuestiongrammarserial(
              String.valueOf(row.getCell(0).getNumericCellValue()));
        if (row.getCell(1) != null)
          questionGrammar.setQuestiongrammarask(row.getCell(1).getStringCellValue());
        if (row.getCell(2) != null)
          questionGrammar.setQuestiongrammaranswercorrect(row.getCell(2).getStringCellValue());
        if (row.getCell(3) != null)
          questionGrammar.setQuestiongrammaranswer1(row.getCell(3).getStringCellValue());
        if (row.getCell(4) != null)
          questionGrammar.setQuestiongrammaranswer2(row.getCell(4).getStringCellValue());
        if (row.getCell(5) != null)
          questionGrammar.setQuestiongrammaranswer3(row.getCell(5).getStringCellValue());
        if (row.getCell(6) != null)
          questionGrammar.setQuestiongrammaranswer4(row.getCell(6).getStringCellValue());
        if (row.getCell(7) != null)
          questionGrammar.setQuestiongrammarexplain(row.getCell(7).getStringCellValue());

        questionGrammar.setGrammar(grammar);
        list.add(questionGrammar);
      }
    } catch (Exception e) {
      System.out.println("Errorhere: " + e);
    }
    return list;
  }


}
