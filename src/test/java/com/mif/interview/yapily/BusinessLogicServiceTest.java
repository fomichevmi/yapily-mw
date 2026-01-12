package com.mif.interview.yapily;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mif.interview.yapily.model.Transaction;
import com.mif.interview.yapily.model.TransactionStatus;
import com.mif.interview.yapily.service.BusinessLogicService;
import com.mif.interview.yapily.service.MetricsEmmiterService;
import com.mif.interview.yapily.service.YapilyHttpService;
import com.mif.interview.yapily.storage.InstitudionStorage;
import com.mif.interview.yapily.storage.TransactionStorage;

@ExtendWith(MockitoExtension.class)
class BusinessLogicServiceTest {

  @InjectMocks
  private BusinessLogicService service;
  @Mock
  private YapilyHttpService yapilyHttpService;
  @Mock
  private TransactionStorage storage;
  @Mock
  private InstitudionStorage institutionStorage;
  @Mock
  private MetricsEmmiterService metricsService;

  @Test
  void processPayment_shouldFailTransactionWhenYapilyResponseIsEmpty() {
    service.init(); // Fill the bucket
    Transaction tx = new Transaction("key", UUID.randomUUID(), UUID.randomUUID(), 1, 100.0, TransactionStatus.NEW);

    // Mock the sequence of Yapily calls
    when(yapilyHttpService.getPaymentAuthorization(any())).thenReturn(null);

    assertDoesNotThrow(() -> service.processPayment(tx));
    verify(storage).updateTransactionStatus(any(), eq(TransactionStatus.FAILED_PERMANENTLY));
  }
}