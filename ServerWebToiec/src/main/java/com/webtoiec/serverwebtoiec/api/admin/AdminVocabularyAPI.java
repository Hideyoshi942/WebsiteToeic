package com.webtoiec.serverwebtoiec.api.admin;

import com.webtoiec.serverwebtoiec.entities.CommentVocabulary;
import com.webtoiec.serverwebtoiec.entities.QuestionVocabulary;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import com.webtoiec.serverwebtoiec.entities.VocabularyLessonContent;
import com.webtoiec.serverwebtoiec.repository.CommentVocabularyRepo;
import com.webtoiec.serverwebtoiec.repository.UserRepo;
import com.webtoiec.serverwebtoiec.repository.VocabularyRepo;
import com.webtoiec.serverwebtoiec.service.CommentVocabularyImpl;
import com.webtoiec.serverwebtoiec.service.VocabularyLessonContentImpl;
import com.webtoiec.serverwebtoiec.service.impl.QuestionVocabularyService;
import com.webtoiec.serverwebtoiec.service.impl.VocabularyLessonContentService;
import com.webtoiec.serverwebtoiec.service.impl.VocabularyService;
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
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/vocab")
public class AdminVocabularyAPI {
  @Autowired
  private HttpServletRequest request;

  @Autowired
  VocabularyService vocabularyService;

  @Autowired
  VocabularyLessonContentService vocabularyLessonContentService;

  @Autowired
  QuestionVocabularyService questionVocabularyService;

  @Autowired
  private CommentVocabularyImpl commentVocabularyImpl;

  @Autowired
  private VocabularyLessonContentImpl vocabularyLessonContent;

  @Autowired
  private VocabularyRepo vocabularyRepo;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private CommentVocabularyRepo commentVocabularyRepo;

  @GetMapping("/loadVocab")
  public List<Map<String, Object>> showAllVocab() {
    List<Vocabulary> list = vocabularyService.findAll();
    List<Map<String, Object>> response = new ArrayList<>();

    for (Vocabulary vocabulary : list) {
      Map<String, Object> map = new HashMap<>();
      map.put("vocabularyid", vocabulary.getVocabularyid());
      map.put("vocabularyimage", "/api/admin/vocab/image/vocab." + URLEncoder.encode(vocabulary.getVocabularyimage(), StandardCharsets.UTF_8));
      map.put("vocabularyname", vocabulary.getVocabularyname());
      response.add(map);
    }
    return response;
  }

  @GetMapping("/loadQuestionVocab")
  public List<Map<String, Object>> showAllQuestionVocab(
      @RequestParam("vocabularyid") int vocabularyid
  ) {
    Vocabulary vocabulary = vocabularyRepo.findById(vocabularyid).orElse(null);
    if (vocabulary == null) {
      return Collections.emptyList();
    }
    List<QuestionVocabulary> list = questionVocabularyService.findListQuestionByVocabularyId(vocabulary);
    System.out.println(list);

    List<Map<String, Object>> response = new ArrayList<>();
    for (QuestionVocabulary questionVocabulary : list) {
      Map<String, Object> map = new HashMap<>();
      map.put("questionvocabularyid", questionVocabulary.getQuestionvocabularyid());
      map.put("questionvocabularyask", questionVocabulary.getQuestionvocabularyask());
      map.put("questionvocabularyanswercorrect", questionVocabulary.getQuestionvocabularyanswercorrect());
      map.put("questionvocabularyanswer1", questionVocabulary.getQuestionvocabularyanswer1());
      map.put("questionvocabularyanswer2", questionVocabulary.getQuestionvocabularyanswer2());
      map.put("questionvocabularyanswer3", questionVocabulary.getQuestionvocabularyanswer3());
      map.put("questionvocabularyanswer4", questionVocabulary.getQuestionvocabularyanswer4());
      map.put("questionvocabularyexplain", questionVocabulary.getQuestionvocabularyexplain());
      map.put("questionvocabularyserial", questionVocabulary.getQuestionvocabularyserial());
      response.add(map);
    }
    return response;
  }

  @GetMapping("/image/{filename}")
  public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
    try {
      String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8.name());
      Path filePath = Paths.get("src/main/resources/static/img/vocabulary").resolve(decodedFilename).normalize();
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
      Path filePath = Paths.get("src/main/resources/static/audio/vocabulary").resolve(decodedFilename).normalize();
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

  @GetMapping("/loadLessonVocab/{vocabularyid}")
  public List<Map<String, Object>> showLessonVocab(@PathVariable int vocabularyid) {
    // Tìm đối tượng Vocabulary từ vocabularyid
    Vocabulary vocabulary = vocabularyRepo.findById(vocabularyid).orElse(null);
    if (vocabulary == null) {
      return Collections.emptyList();  // Trả về danh sách rỗng nếu không tìm thấy vocabulary
    }

    // Tìm danh sách VocabularyLessonContent dựa trên Vocabulary
    List<VocabularyLessonContent> list = vocabularyLessonContent.findByVocabulary(vocabulary);

    // Xây dựng danh sách phản hồi
    List<Map<String, Object>> response = new ArrayList<>();
    for (VocabularyLessonContent vocabularyLesson : list) {
      Map<String, Object> map = new HashMap<>();
      map.put("vocabularylessoncontentid", vocabularyLesson.getVocabularylessoncontentid());
      map.put("vocabularylessoncontentaudio", "/api/admin/vocab/audio/" + URLEncoder.encode(vocabularyLesson.getVocabularylessoncontentaudio(), StandardCharsets.UTF_8));
      map.put("vocabularylessoncontentdescript", vocabularyLesson.getVocabularylessoncontentdescript());
      map.put("vocabularylessoncontentimage", "/api/admin/vocab/image/" + URLEncoder.encode(vocabularyLesson.getVocabularylessoncontentimage(), StandardCharsets.UTF_8));
      map.put("vocabularylessoncontentmeaning", vocabularyLesson.getVocabularylessoncontentmeaning());
      map.put("vocabularylessoncontentsentence", vocabularyLesson.getVocabularylessoncontentsentence());
      map.put("vocabularylessoncontenttranscript", vocabularyLesson.getVocabularylessoncontenttranscript());
      response.add(map);
    }
    return response;
  }

  @GetMapping("/loadCommentVocab/{vocabularyid}")
  public List<Map<String, Object>> showAllCommentVocab(@PathVariable int vocabularyid) {
    Vocabulary vocabulary = vocabularyRepo.findById(vocabularyid).orElse(null);
    if (vocabulary == null) {
      return Collections.emptyList();
    }

    List<CommentVocabulary> list = commentVocabularyImpl.findByVocabulary(vocabulary);
    List<Map<String, Object>> response = new ArrayList<>();
    for (CommentVocabulary comment : list) {
      Map<String, Object> commentMap = new HashMap<>();
      commentMap.put("commentvocabularyid", comment.getCommentvocabularyid());
      commentMap.put("commentvocabularycontent", comment.getCommentvocabularycontent());
      commentMap.put("commentvocabularytime", comment.getCommentvocabularytime());
      commentMap.put("vocabularyid", comment.getVocabulary().getVocabularyid());
      commentMap.put("id", comment.getUser().getId());
      commentMap.put("name", comment.getUser().getUsername());
      response.add(commentMap);
    }

    return response;
  }


  // API tạo bình luận cho bài viết Vocabulary
  @PostMapping("/createCommentVocabulary")
  public ResponseEntity<?> createCommentVocab(
      @RequestParam int vocabularyid,
      @RequestParam int id,
      @RequestParam String commentvocabularycontent
  ) {
    try {
      // Kiểm tra xem từ vựng (Vocabulary) có tồn tại không
      Vocabulary vocabulary = vocabularyRepo.findById(vocabularyid)
          .orElseThrow(() -> new RuntimeException("Vocabulary with id " + vocabularyid + " not found"));

      // Kiểm tra xem người dùng (UserToeic) có tồn tại không
      UserToeic user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not found"));

      // Tạo đối tượng CommentVocabulary
      CommentVocabulary commentVocabulary = new CommentVocabulary();
      commentVocabulary.setCommentvocabularycontent(commentvocabularycontent);
      commentVocabulary.setVocabulary(vocabulary);
      commentVocabulary.setUser(user);
      commentVocabulary.setCommentvocabularytime(new Date(System.currentTimeMillis()));

      CommentVocabulary savedComment = commentVocabularyRepo.save(commentVocabulary);

      return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating comment: " + e.getMessage());
    }
  }

  @RequestMapping(value = "/delete/{vocabularyid}")
  public String deleteByVocabularyid(@PathVariable("vocabularyid") int vocabularyid) {
    vocabularyService.delete(vocabularyid);
    return "success";
  }

  @PostMapping(value = "/save", consumes = "multipart/form-data")
  @ResponseBody
  public List<String> addVocabulary(@RequestParam("file_excel_lesson") MultipartFile file_excel_lesson,
      @RequestParam("file_imageVocab") MultipartFile file_imageVocab,
      @RequestParam("namevocab") String namevocab,
      @RequestParam("file_image_lessonVocab") MultipartFile[] file_image_lessonVocab,
      @RequestParam("file_listening") MultipartFile[] file_listening,
      @RequestParam("file_questionvocab") MultipartFile file_questionvocab) {
    List<String> response = new ArrayList<String>();
    String rootDirectory = Paths.get("src/main").toAbsolutePath().toString();

    Vocabulary vocabulary = new Vocabulary();
    vocabularyService.save(vocabulary);

    try {
      // save file upload to local folder
      // Excel Vocab lesson
      Path pathExcel = Paths.get(rootDirectory + "/resources/static/excel/vocabulary/lesson/" + "vocab." + vocabulary.getVocabularyid() + "." + file_excel_lesson.getOriginalFilename());
      file_excel_lesson.transferTo(new File(pathExcel.toString()));

      // Image vocab
      Path pathImage = Paths.get(rootDirectory + "/resources/static/img/vocabulary/" + "vocab." + vocabulary.getVocabularyid() + "." + file_imageVocab.getOriginalFilename());
      file_imageVocab.transferTo(new File(pathImage.toString()));

      // Audio vocab lesson
      for (MultipartFile single_listening : file_listening) {
        Path pathImageQuestion = Paths.get(rootDirectory + "/resources/static/audio/vocabulary/" + vocabulary.getVocabularyid() + "." + single_listening.getOriginalFilename());
        single_listening.transferTo(new File(pathImageQuestion.toString()));
      }
      // Image vocab lesson
      for (MultipartFile single_image : file_image_lessonVocab) {
        Path pathImageQuestion = Paths.get(rootDirectory + "/resources/static/img/vocabulary/" + vocabulary.getVocabularyid() + "." + single_image.getOriginalFilename());
        single_image.transferTo(new File(pathImageQuestion.toString()));
      }

      // Excel question vocab
      Path pathExcelQuestion = Paths.get(rootDirectory + "/resources/static/excel/vocabulary/question/" + "vocab." + vocabulary.getVocabularyid() + "." + file_questionvocab.getOriginalFilename());
      file_questionvocab.transferTo(new File(pathExcelQuestion.toString()));

      vocabulary.setVocabularyname(namevocab);
      vocabulary.setVocabularyimage(vocabulary.getVocabularyid() + "." + file_imageVocab.getOriginalFilename());
      vocabularyService.save(vocabulary);

      // save data from file excel for lesson
      AdminVocabularyAPI adminVocabularyAPI = new AdminVocabularyAPI();
      List<VocabularyLessonContent> list = adminVocabularyAPI.getListFromExcel(pathExcel.toString(), vocabulary);
      for (int i = 0; i < list.size(); i++) {
        vocabularyLessonContentService.save(list.get(i));
      }

      List<QuestionVocabulary> listQuestion = adminVocabularyAPI.getListFromExcelForQuestion(pathExcelQuestion.toString(), vocabulary);
      for (int i = 0; i < listQuestion.size(); i++) {
        questionVocabularyService.save(listQuestion.get(i));
      }

    } catch (Exception e) {
      response.add(e.toString());
      System.out.println("ErrorReadFileExcel: " + e);
    }
    return response;
  }

  // Vocab Lesson
  public List<VocabularyLessonContent> getListFromExcel(String path, Vocabulary vocabulary) {
    List<VocabularyLessonContent> list = new ArrayList<>();

    try {
      FileInputStream excelFile = new FileInputStream(new File(path));
      XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
      XSSFSheet sheet = workbook.getSheetAt(0);
      for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
        VocabularyLessonContent vocabularyLessonContent = new VocabularyLessonContent();

        XSSFRow row = sheet.getRow(i);
        if (row.getCell(0) != null)
          vocabularyLessonContent.setVocabularylessoncontentnumber(
              String.valueOf(row.getCell(0).getNumericCellValue()));
        if (row.getCell(1) != null)
          vocabularyLessonContent.setVocabularylessoncontentdescript(row.getCell(1).getStringCellValue());
        if (row.getCell(2) != null)
          vocabularyLessonContent.setVocabularylessoncontenttranscript(row.getCell(2).getStringCellValue());
        if (row.getCell(3) != null)
          vocabularyLessonContent.setVocabularylessoncontentimage(vocabulary.getVocabularyid() + "." + row.getCell(3).getStringCellValue());
        if (row.getCell(4) != null)
          vocabularyLessonContent.setVocabularylessoncontentaudio(vocabulary.getVocabularyid() + "." + row.getCell(4).getStringCellValue());
        if (row.getCell(5) != null)
          vocabularyLessonContent.setVocabularylessoncontentmeaning(row.getCell(5).getStringCellValue());
        if (row.getCell(6) != null)
          vocabularyLessonContent.setVocabularylessoncontentsentence(row.getCell(6).getStringCellValue());

        vocabularyLessonContent.setVocabulary(vocabulary);
        list.add(vocabularyLessonContent);
      }
    } catch (Exception e) {
      System.out.println("Errorhere: " + e);
    }
    return list;
  }

  // Vocab question
  public List<QuestionVocabulary> getListFromExcelForQuestion(String path, Vocabulary vocabulary) {
    List<QuestionVocabulary> list = new ArrayList<>();

    try {
      FileInputStream excelFile = new FileInputStream(new File(path));
      XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
      XSSFSheet sheet = workbook.getSheetAt(0);
      for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
        QuestionVocabulary questionVocabulary = new QuestionVocabulary();

        XSSFRow row = sheet.getRow(i);
        if (row.getCell(0) != null)
          questionVocabulary.setQuestionvocabularyserial(
              String.valueOf(row.getCell(0).getNumericCellValue()));
        if (row.getCell(1) != null)
          questionVocabulary.setQuestionvocabularyask(row.getCell(1).getStringCellValue());
        if (row.getCell(2) != null)
          questionVocabulary.setQuestionvocabularyanswercorrect(row.getCell(2).getStringCellValue());
        if (row.getCell(3) != null)
          questionVocabulary.setQuestionvocabularyanswer1(row.getCell(3).getStringCellValue());
        if (row.getCell(4) != null)
          questionVocabulary.setQuestionvocabularyanswer2(row.getCell(4).getStringCellValue());
        if (row.getCell(5) != null)
          questionVocabulary.setQuestionvocabularyanswer3(row.getCell(5).getStringCellValue());
        if (row.getCell(6) != null)
          questionVocabulary.setQuestionvocabularyanswer4(row.getCell(6).getStringCellValue());
        if (row.getCell(7) != null)
          questionVocabulary.setQuestionvocabularyexplain(row.getCell(7).getStringCellValue());

        questionVocabulary.setVocabulary(vocabulary);
        list.add(questionVocabulary);
      }
    } catch (Exception e) {
      System.out.println("Errorhere: " + e);
    }
    return list;
  }

  // get infor Vocab -> edit Vocab
  @RequestMapping(value = "/infoVocabulary/{vocabularyid}")
  public String infoVocabularyByVocabularyid(@PathVariable("vocabularyid") int vocabularyid) {
    Vocabulary vocabulary = vocabularyService.getVocabulary(vocabularyid).get(0);
    return vocabulary.getVocabularyname();
  }

  @PostMapping(value = "/update")
  @ResponseBody
  public List<String> updateVocabulary(@RequestParam("vocabularyid") int vocabularyid,
      @RequestParam("namevocab") String namevocab,
      @RequestParam("file_imageVocab") MultipartFile file_imageVocab,
//      @RequestParam("file_image_question") MultipartFile[] file_image_question,
//      @RequestParam("file_listening") MultipartFile[] file_listening,
      @RequestParam("file_excel_lesson") MultipartFile file_excel_lesson,
      @RequestParam("file_questionvocab") MultipartFile file_questionvocab
      ) {
        List<String> response = new ArrayList<String>();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        Vocabulary vocabulary = vocabularyService.getVocabulary(vocabularyid).get(0);

        try {
          // save file upload to local folder
          // Excel Vocab lesson
          Path pathExcel = Paths.get(rootDirectory + "/resources/static/excel/vocabulary/lesson/" + "vocab." + vocabulary.getVocabularyid() + "." + file_excel_lesson.getOriginalFilename());
          file_excel_lesson.transferTo(new File(pathExcel.toString()));

          // Image vocab
          Path pathImage = Paths.get(rootDirectory + "/resources/static/img/vocabulary/" + "vocab." + vocabulary.getVocabularyid() + "." + file_imageVocab.getOriginalFilename());
          file_imageVocab.transferTo(new File(pathImage.toString()));

//      // Audio vocab lesson
//      Path pathAudio = Paths.get(rootDirectory + "/resources/static/audio/vocabulary/" + "vocab." + vocabulary.getVocabularyid() + "." + file_listening.getOriginalFilename());
//      file_listening.transferTo(new File(pathAudio.toString()));

          // Image vocab lesson
//      Path pathImageLesson = Paths.get(rootDirectory + "/resources/static/img/vocabulary/lesson/" + "vocab." + vocabulary.getVocabularyid() + "." + file_image_lessonVocab.getOriginalFilename());
//      file_image_lessonVocab.transferTo(new File(pathImageLesson.toString()));

          // Excel question vocab
          Path pathExcelQuestion = Paths.get(rootDirectory + "/resources/static/excel/vocabulary/question/" + "vocab." + vocabulary.getVocabularyid() + "." + file_questionvocab.getOriginalFilename());
          file_questionvocab.transferTo(new File(pathExcelQuestion.toString()));

          vocabulary.setVocabularyname(namevocab);
          vocabulary.setVocabularyimage(vocabulary.getVocabularyid() + "." + file_imageVocab.getOriginalFilename());
          vocabularyService.save(vocabulary);

          // save data from file excel

          AdminVocabularyAPI adminVocabularyAPI = new AdminVocabularyAPI();
          List<VocabularyLessonContent> list = adminVocabularyAPI.getListFromExcel(pathExcel.toString(), vocabulary);
          for (int i = 0; i < list.size(); i++) {
            vocabularyLessonContentService.save(list.get(i));
          }

          List<QuestionVocabulary> listQuestion = adminVocabularyAPI.getListFromExcelForQuestion(pathExcelQuestion.toString(), vocabulary);
          for (int i = 0; i < listQuestion.size(); i++) {
            questionVocabularyService.save(listQuestion.get(i));
          }

        } catch (Exception e) {
          response.add(e.toString());
          System.out.println("ErrorUpdate: " + e);
        }
        return response;
      }
}
