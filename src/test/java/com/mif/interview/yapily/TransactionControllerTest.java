package com.mif.interview.yapily;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.mif.interview.yapily.controller.TransactionController;
import com.mif.interview.yapily.service.TransactionQueueManager;
import com.mif.interview.yapily.storage.TransactionStorage;

@WebMvcTest(TransactionController.class)
@Import(SecurityConfig.class)
class TransactionControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private TransactionQueueManager queueManager;
  @MockBean
  private TransactionStorage transactionStorage;

  @Test
  @WithMockUser(roles = "USER")
  void register_shouldReturn200_whenAuthenticated() throws Exception {
    String json = "{\"employeeId\": \"b64caf23-0c8c-4ca8-80b2-e6ef8a6ba703\", \"paymentDestination\": 123, \"amount\": 234.5}";

    mockMvc.perform(
        post("/api/v1/transactions/register").header("X-Idempotency-Key", "test-key").with(httpBasic("user", "user123"))
            .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk());
  }

  @Test
  void register_shouldReturn401_whenNoAuth() throws Exception {
    mockMvc.perform(post("/api/v1/transactions/register")).andExpect(status().isUnauthorized());
  }
}