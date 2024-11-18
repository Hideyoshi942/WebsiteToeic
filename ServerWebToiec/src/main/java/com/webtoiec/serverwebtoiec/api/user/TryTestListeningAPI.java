package com.webtoiec.serverwebtoiec.api.user;

import com.webtoiec.serverwebtoiec.entities.QuestionPracticeTest;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.service.impl.PracticeTestService;
import com.webtoiec.serverwebtoiec.service.impl.QuestionListeningService;
import com.webtoiec.serverwebtoiec.service.impl.QuestionPracticeTestService;
import com.webtoiec.serverwebtoiec.service.impl.ResultTestService;
import com.webtoiec.serverwebtoiec.service.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/client/practicetest")
public class TryTestListeningAPI {
  @Autowired
  ResultTestService resultTestService;

  @Autowired
  QuestionPracticeTestService questionPracticeTestService;

  @Autowired
  PracticeTestService practicetestService;

  @Autowired
  private UserService userService;

  @ModelAttribute("loggedInUser")
  public UserToeic getSessionUser(HttpServletRequest request) {
    return (UserToeic) request.getSession().getAttribute("loggedInUser");
  }

  @RequestMapping(value="/ajaxTestListening/{practicetestid}/{numbercorrect}", method = RequestMethod.POST)
  public @ResponseBody List<String> AjaxListening(@RequestBody String[] jsonAnswerUser, @PathVariable("practicetestid") int practicetestid, @PathVariable("numbercorrect") String numbercorrect) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserToeic user = userService.findByEmail(authentication.getName());
    List<QuestionPracticeTest> list = questionPracticeTestService.getListQuestion(practicetestService.getPracticeTest(practicetestid).get(0));
    List<String> response = new ArrayList<String>();

    return response;
  }
}
