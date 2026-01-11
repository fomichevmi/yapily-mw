package com.mif.interview.yapily.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mif.interview.yapily.model.Transaction;
import com.mif.interview.yapily.storage.TransactionStorage;

@Service
public class BusinessLogicService {

  @Autowired
  TransactionStorage transactionStorage;
  @Autowired
  YapilyHttpService yapilyHttpService;

  public void processTransaction(Transaction transaction) {

  }

}
