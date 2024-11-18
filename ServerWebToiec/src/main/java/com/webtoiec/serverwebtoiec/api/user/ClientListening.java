package com.webtoiec.serverwebtoiec.api.user;

import com.webtoiec.serverwebtoiec.entities.Listening;
import com.webtoiec.serverwebtoiec.entities.QuestionListening;
import com.webtoiec.serverwebtoiec.service.impl.ListeningService;
import com.webtoiec.serverwebtoiec.service.impl.QuestionListeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/client/listening")
public class ClientListening {
  @Autowired
  private ListeningService listeningService;

  @Autowired
  private QuestionListeningService questionListeningService;

  @GetMapping("/listeningid={listeningid}")
  public ResponseEntity<Page<QuestionListening>> getListQuestioneListeningByListeningid(@RequestParam(defaultValue = "1") int page,
      @PathVariable int listeningid) {
    return new ResponseEntity<>(questionListeningService.findListQuestionListeningId(page, 3,listeningid), HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<Page<Listening>> findAllListeningpartAndListeninglevel(@RequestParam(defaultValue = "1") int page,
      @RequestParam(required = true) int listeningpart, @RequestParam(required = true) int listeninglevel) {
    return new ResponseEntity<>(listeningService.findAllListListeningByListeningpartAndListeninglevel(listeningpart, listeninglevel, page, 10), HttpStatus.OK);
  }
}
