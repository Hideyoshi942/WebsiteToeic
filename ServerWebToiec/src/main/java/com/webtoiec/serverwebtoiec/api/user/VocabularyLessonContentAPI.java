package com.webtoiec.serverwebtoiec.api.user;

import com.webtoiec.serverwebtoiec.entities.CommentVocabulary;
import com.webtoiec.serverwebtoiec.entities.UserToeic;
import com.webtoiec.serverwebtoiec.entities.Vocabulary;
import com.webtoiec.serverwebtoiec.service.impl.CommentVocabularyService;
import com.webtoiec.serverwebtoiec.service.impl.QuestionVocabularyService;
import com.webtoiec.serverwebtoiec.service.impl.UserService;
import com.webtoiec.serverwebtoiec.service.impl.VocabularyService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vocabularylessoncontent")
public class VocabularyLessonContentAPI {
  @Autowired
  private UserService userService;

  @Autowired
  private CommentVocabularyService commentVocabularyService;

  @Autowired
  private VocabularyService vocabularyService;

  @ModelAttribute("loggedInUser")
  public UserToeic getSessionUser(HttpServletRequest request) {
    return (UserToeic) request.getSession().getAttribute("loggedInUser");
  }

  @RequestMapping("/testAjax")
  String index() {
    return "Hello from ajax";
  }

  @GetMapping("/ajaxCmtVocab")
  public String getAjax(@RequestParam int vocabularyid, @RequestParam String contentComment) {
    return contentComment;
  }

  @RequestMapping(value = "/ajaxCmtVocab/{contentCommnet}/{vocabularyid}", method = RequestMethod.POST)
  @ResponseBody
  public List<String> AjaxCmtVocab(@PathVariable("contentCommnet") String contentComment, @PathVariable("vocabularyid") int vocabularyid) {
    List<String> response = new ArrayList<String>();

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserToeic user = userService.findByEmail(authentication.getName());
    List<Vocabulary> vocabularies = vocabularyService.getVocabulary(vocabularyid);

    // save comment
    CommentVocabulary commentVocabulary = new CommentVocabulary();
    commentVocabulary.setVocabulary(vocabularies.get(0));
    commentVocabulary.setUser(user);
    commentVocabulary.setCommentvocabularycontent(contentComment);
    commentVocabularyService.save(commentVocabulary);
    response.add(commentVocabulary.toString());
    return response;
  }
}
