package com.webtoiec.serverwebtoiec.api.admin;

import com.webtoiec.serverwebtoiec.entities.PracticeTest;
import com.webtoiec.serverwebtoiec.entities.ResultTest;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.repository.PracticeTestRepo;
import com.webtoiec.serverwebtoiec.repository.ResultTestRepo;
import com.webtoiec.serverwebtoiec.repository.UserRepo;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/result")
public class ResultAPI {
  private final ResultTestRepo resultTestRepo;
  private final UserRepo userRepo;
  private final PracticeTestRepo practiceTestRepo;

  public ResultAPI(ResultTestRepo resultTestRepo, UserRepo userRepo,
      PracticeTestRepo practiceTestRepo) {
    this.resultTestRepo = resultTestRepo;
    this.userRepo = userRepo;
    this.practiceTestRepo = practiceTestRepo;
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
      response.add(map);
    }

    return response;
  }

  @PostMapping("/saveResult/{practicetestid}")
  public ResponseEntity<String> saveResult(
      @PathVariable("practicetestid") int practicetestid,
      @RequestParam("resulttestcorrectlistening") int resulttestcorrectlistening,
      @RequestParam("resulttestcorrectreading") int resulttestcorrectreading,
      @RequestParam("resulttestnumbercorrect") int resulttestnumbercorrect,
      @RequestParam("resulttestnumberincorrect") int resulttestnumberincorrect,
      @RequestParam("id") int id
  ) {
    UserToeic userToeic = userRepo.findById(id).orElse(null);

    if (userToeic == null) {
      // Nếu người dùng không tồn tại, trả về lỗi 404
      return ResponseEntity.notFound().build();
    }

    PracticeTest practiceTest = practiceTestRepo.findById(practicetestid).orElse(null);

    if (practiceTest == null) {
      // Nếu người dùng không tồn tại, trả về lỗi 404
      return ResponseEntity.notFound().build();
    }

    ResultTest resultTest = new ResultTest();
    resultTest.setPracticetest(practiceTest);
    resultTest.setResulttestcorrectlistening(resulttestcorrectlistening);
    resultTest.setResulttestcorrectreading(resulttestcorrectreading);
    resultTest.setResulttestnumbercorrect(resulttestnumbercorrect);
    resultTest.setResulttestnumberincorrect(resulttestnumberincorrect);
    resultTest.setUser(userToeic);
    resultTest.setResulttesttime(new Date(System.currentTimeMillis()));
    resultTestRepo.save(resultTest);
    return ResponseEntity.ok("Luận về thị trần thanh cong");
  }
}
