package com.webtoiec.serverwebtoiec.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.webtoiec.serverwebtoiec.entities.QuestionListening;
import com.webtoiec.serverwebtoiec.entities.QuestionReading;
import com.webtoiec.serverwebtoiec.entities.Reading;
import com.webtoiec.serverwebtoiec.entities.QReading;
import com.webtoiec.serverwebtoiec.repository.ReadingRepo;
import com.webtoiec.serverwebtoiec.util.ExcelUtilReading;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReadingService {
  @Autowired
  private ReadingRepo readingRepo;

  @Autowired
  private QuestionReadingService questionReadingService;

  @Autowired
  private ExcelUtilReading excelUtilReading;

  public Page<Reading> findAllListReadingByReadingpartAndReadinglevel(int page, int size, int readingpart, int readinglevel) {
    return readingRepo.findByReadingpartAndReadinglevel(readingpart, readinglevel, PageRequest.of(page - 1, size));
  }

  public Page<Reading> findAllListReadingByReadingpartAndReadinglevelAdmin(int page, int size, String readingpart, String readinglevel) {
    BooleanBuilder builder = new BooleanBuilder();

    if (!readingpart.equals("")) {
      builder.and(QReading.reading.readingpart.eq(Integer.parseInt(readingpart)));
    }

    if (!readinglevel.equals("")) {
      builder.and(QReading.reading.readinglevel.eq(Integer.parseInt(readinglevel)));
    }

    return readingRepo.findAll(builder, PageRequest.of(page - 1, size));
  }

  public Optional<Reading> findReadingByReadingid(int readingid) {
    return readingRepo.findById(readingid);
  }

  @Transactional
  public Reading saveReading(Reading reading, HttpServletRequest request) {
    List<QuestionReading> questionReadingList = new ArrayList<>();
    Reading currentReading = readingRepo.save(reading);
    try {
      for (QuestionReading questionReading : excelUtilReading.getListQuestionReadingFromFileExcel(reading.getReadingfileExcelQuestion().getInputStream())) {
        questionReading.setReading(currentReading);
        questionReadingList.add(questionReadingService.saveQuestionReading(questionReading, request));
      }
      return currentReading;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void deleteReading(int readingid) {
    readingRepo.deleteById(readingid);
  }
}
