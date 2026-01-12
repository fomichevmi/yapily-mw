package com.mif.interview.yapily.service;

import org.springframework.stereotype.Service;

/**
 * In the real-world there should be integration with something like prometheus.
 * Here I just want to show that we need this integration for collecting
 * different metrics of our application with the example of such metrics
 */
@Service
public class MetricsEmmiterService {

  public void emitSusccessful() {
  }

  public void emitDuplicated() {
  }

  public void emitRejected() {
  }

  public void emit500Error() {
  }

  public void emitPutToTheQueue() {
  }

  public void emitSentToYapily() {
  }

  public void recordQuetime() {
  }

  public void recordProcessingTime() {
  }
}
