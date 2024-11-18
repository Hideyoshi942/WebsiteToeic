package com.webtoiec.serverwebtoiec.api.user;

import com.webtoiec.serverwebtoiec.entities.QuestionReading;
import com.webtoiec.serverwebtoiec.entities.Reading;
import com.webtoiec.serverwebtoiec.service.impl.QuestionReadingService;
import com.webtoiec.serverwebtoiec.service.impl.ReadingService;
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
@RequestMapping("api/client/reading")
public class ClientReading {
  @Autowired
  private ReadingService readingService;

  @Autowired
  private QuestionReadingService questionReadingService;

  @GetMapping("/readingid={readingid}")
  public ResponseEntity<Page<QuestionReading>> getListQuestioneReadingByReadingid(@RequestParam(defaultValue = "1") int page,
      @PathVariable int readingid) {
    return new ResponseEntity<>(questionReadingService.findListQuestionReading(page, 10,readingid), HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<Page<Reading>> findAllByPartToeic(@RequestParam(defaultValue = "1") int page,
      @RequestParam(required = true) int readingpart, @RequestParam(required = true) int readinglevel) {
        return new ResponseEntity<>(readingService.findAllListReadingByReadingpartAndReadinglevel(page, 10, readingpart, readinglevel), HttpStatus.OK);
      }
}
