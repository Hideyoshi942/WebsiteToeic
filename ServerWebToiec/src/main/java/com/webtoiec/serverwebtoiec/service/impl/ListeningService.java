package com.webtoiec.serverwebtoiec.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.webtoiec.serverwebtoiec.entities.Listening;
import com.webtoiec.serverwebtoiec.entities.QListening;
import com.webtoiec.serverwebtoiec.entities.QuestionListening;
import com.webtoiec.serverwebtoiec.repository.ListeningRepo;
import com.webtoiec.serverwebtoiec.util.ExcelUtilListening;
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
public class ListeningService {
  @Autowired
  private ListeningRepo listeningRepo;

  @Autowired
  private QuestionListeningService questionListeningService;

  @Autowired
  private ExcelUtilListening excelUtilListening;

  public List<Listening> findAll()
  {
    return listeningRepo.findAll();
  }

  public Page<Listening> findAllListListeningByListeningpartAndListeninglevel(int listeningpart, int listeninglevel, int page, int size) {
    return listeningRepo.findByListeningpartAndListeninglevel(listeningpart, listeninglevel, PageRequest.of(page - 1, size));
  }

  public Page<Listening> findAllListListeningByListeningpartAndListeninglevelAdmin(String listeningpart, String listeninglevel, int page, int size) {
    BooleanBuilder builder = new BooleanBuilder();

    if (!listeningpart.equals("")) {
      builder.and(QListening.listening.listeningpart.eq(Integer.parseInt(listeningpart)));
    }

    if (!listeninglevel.equals("")) {
      builder.and(QListening.listening.listeninglevel.eq(Integer.parseInt(listeninglevel)));
    }

    return listeningRepo.findAll(builder, PageRequest.of(page - 1, size));
  }

  public Optional<Listening> findListeningByListeningid(int listeningid) {
    return listeningRepo.findById(listeningid);
  }

  @Transactional
  public Listening saveListening(Listening listening, HttpServletRequest request) {
    List<QuestionListening> questionListeningList = new ArrayList<>();
    Listening currentListening = listeningRepo.save(listening);
    try {
      for (QuestionListening questionListening : excelUtilListening
          .getListQuestionListeningFromFileExcel(listening.getListeningfileExcelQuestion().getInputStream())) {
        questionListening.setListening(currentListening);
        questionListeningList.add(questionListeningService.saveQuestionListening(questionListening, request));
      }
      return currentListening;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void deleteListening(int listeningid) {
    listeningRepo.deleteById(listeningid);
  }
}
