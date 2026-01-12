package com.mif.interview.yapily;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mif.interview.yapily.exception.DuplicateTransactoinException;
import com.mif.interview.yapily.model.Transaction;
import com.mif.interview.yapily.model.TransactionStatus;
import com.mif.interview.yapily.service.MetricsEmmiterService;
import com.mif.interview.yapily.storage.TransactionStorage;

@ExtendWith(MockitoExtension.class)
class TransactionStorageTest {

  @Mock
  private MetricsEmmiterService metricsService;
  @InjectMocks
  private TransactionStorage storage;

  @Test
  void createTransaction_shouldDetectIdempotencyCollision() {
    Transaction tx1 = new Transaction("key1", UUID.randomUUID(), UUID.randomUUID(), 1, 10.0, TransactionStatus.NEW);
    Transaction tx2 = new Transaction("key1", UUID.randomUUID(), UUID.randomUUID(), 1, 20.0, TransactionStatus.NEW);

    storage.createTransaction(tx1);

    var error = assertThrows(DuplicateTransactoinException.class, () -> storage.createTransaction(tx2));
    assertEquals("The transaction is already NEW", error.getMessage());

    verify(metricsService).emitDuplicated();
  }
}