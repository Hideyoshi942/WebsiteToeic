package com.webtoiec.serverwebtoiec.api.admin;

import com.webtoiec.serverwebtoiec.entities.DetailResultExam;
import com.webtoiec.serverwebtoiec.entities.PracticeTest;
import com.webtoiec.serverwebtoiec.entities.ResultTest;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.repository.DetailResultExamRepo;
import com.webtoiec.serverwebtoiec.repository.PracticeTestRepo;
import com.webtoiec.serverwebtoiec.repository.ResultTestRepo;
import com.webtoiec.serverwebtoiec.repository.UserRepo;
import com.webtoiec.serverwebtoiec.service.DetailResultExamService;
import com.webtoiec.serverwebtoiec.service.impl.ResultTestService;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/result")
public class ResultAPI {
  private final ResultTestRepo resultTestRepo;
  private final UserRepo userRepo;
  private final PracticeTestRepo practiceTestRepo;
  private final DetailResultExamRepo detailResultExamRepo;
  private final DetailResultExamService detailResultExamService;

  public ResultAPI(ResultTestRepo resultTestRepo, UserRepo userRepo,
      PracticeTestRepo practiceTestRepo, DetailResultExamRepo detailResultExamRepo,
      DetailResultExamService detailResultExamService) {
    this.resultTestRepo = resultTestRepo;
    this.userRepo = userRepo;
    this.practiceTestRepo = practiceTestRepo;
    this.detailResultExamRepo = detailResultExamRepo;
    this.detailResultExamService = detailResultExamService;
  }

  @GetMapping("/showResult")
  public List<Map<String, Object>> showAllResult() {
    List<ResultTest> list = resultTestRepo.findAll();
    List<Map<String, Object>> response = new ArrayList<>();

    for (ResultTest resultTest : list) {
      Map<String, Object> map = new HashMap<>();
      map.put("resulttestid", resultTest.getResulttestid());
      map.put("resulttestcorrectlistening", resultTest.getResulttestcorrectlistening());
      map.put("resulttestcorrectreading", resultTest.getResulttestcorrectreading());
      map.put("resulttesttime", resultTest.getResulttesttime());
      map.put("resulttestnumbercorrect", resultTest.getResulttestnumbercorrect());
      map.put("resulttestnumberincorrect", resultTest.getResulttestnumberincorrect());
      map.put("practicetestid", resultTest.getPracticetest());
      map.put("username", resultTest.getUser().getUsername());
      map.put("resulttestadvise", resultTest.getResulttestadvise());
      response.add(map);
    }

    return response;
  }

  @GetMapping("/showResult/{resulttestid}")
  public List<Map<String, Object>> showResultByResultId(@PathVariable int resulttestid) {
    ResultTest resultTest = resultTestRepo.findById(resulttestid).orElse(null);
    List<DetailResultExam> detailResultExams = detailResultExamRepo.findAllByresultTest(resultTest);
    List<Map<String, Object>> response = new ArrayList<>();
    for (DetailResultExam detailResultExam : detailResultExams) {
      Map<String, Object> map = new HashMap<>();
      map.put("detailresultexamid", detailResultExam.getDetailresultexamid());
      map.put("detailresultexamanswercorrect", detailResultExam.getDetailresultexamanswercorrect());
      map.put("detailresultexamansweruser", detailResultExam.getDetailresultexamansweruser());
      map.put("detailresultexamquestion", detailResultExam.getDetailresultexamquestion());
      map.put("detailresultexamquestionid", detailResultExam.getDetailresultexamquestionid());
      response.add(map);
    }
    return response;
  }

  @PostMapping("/saveResult/{practicetestid}")
  public ResponseEntity<Map<String, Object>> saveResult(
      @PathVariable("practicetestid") int practicetestid,
      @RequestParam("resulttestcorrectlistening") int resulttestcorrectlistening,
      @RequestParam("resulttestcorrectreading") int resulttestcorrectreading,
      @RequestParam("resulttestnumbercorrect") int resulttestnumbercorrect,
      @RequestParam("resulttestnumberincorrect") int resulttestnumberincorrect,
      @RequestParam("id") int id,
      @RequestBody List<DetailResultExam> results
  ) {
    UserToeic userToeic = userRepo.findById(id).orElse(null);
    if (userToeic == null) {
      return ResponseEntity.notFound().build();
    }

    PracticeTest practiceTest = practiceTestRepo.findById(practicetestid).orElse(null);
    if (practiceTest == null) {
      return ResponseEntity.notFound().build();
    }

    String advise = generateAdvise(resulttestcorrectlistening, resulttestcorrectreading, resulttestnumbercorrect, resulttestnumberincorrect);

    ResultTest resultTest = new ResultTest();
    resultTest.setPracticetest(practiceTest);
    resultTest.setResulttestcorrectlistening(resulttestcorrectlistening);
    resultTest.setResulttestcorrectreading(resulttestcorrectreading);
    resultTest.setResulttestnumbercorrect(resulttestnumbercorrect);
    resultTest.setResulttestnumberincorrect(resulttestnumberincorrect);
    resultTest.setResulttestadvise(advise);
    resultTest.setUser(userToeic);
    resultTest.setResulttesttime(new Date(System.currentTimeMillis()));
    resultTestRepo.save(resultTest);

    for (DetailResultExam resultExam : results) {
      resultExam.setResultTest(resultTest);
      resultExam.setUser(userToeic);
      System.out.println(resultExam.getDetailresultexamanswercorrect());
      detailResultExamRepo.save(resultExam);
    }

    Map<String, Object> response = new HashMap<>();
    response.put("resulttestid", resultTest.getResulttestid());
    response.put("resulttestadvise", advise);
    return ResponseEntity.ok(response);
  }

  // Phương thức tính toán lời khuyên
  private String generateAdvise(int resulttestcorrectlistening, int resulttestcorrectreading, int resulttestnumbercorrect, int resulttestnumberincorrect) {

    if (resulttestcorrectlistening + resulttestcorrectreading < 100) {
      return "Bạn chưa hoàn thành hết số câu";
    } else if (resulttestnumbercorrect > resulttestnumberincorrect) {
      return "Chúc mừng bạn đã làm bài tốt! Tiếp tục luyện tập để đạt kết quả xuất sắc.";
    } else if (resulttestnumbercorrect == resulttestnumberincorrect) {
      if (resulttestcorrectlistening > resulttestcorrectreading) {
        return "Bạn đã làm bài khá tốt, nhưng cần luyện tập thêm để cải thiện phần nghe.";
      } else {
        return "Bạn đã làm bài khá tốt, nhưng cần luyện tập thêm để cải thiện phần đọc.";
      }
    } else {
      return "Cần cải thiện kỹ năng của bạn, hãy tập trung vào phần yếu để làm tốt hơn.";
    }
  }
}
