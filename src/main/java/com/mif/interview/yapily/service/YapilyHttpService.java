package com.mif.interview.yapily.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.mif.interview.yapily.dto.YapilyCreatePaymentAuthorisationRequest;
import com.mif.interview.yapily.dto.YapilyCreatePaymentAuthorizationResponse;
import com.mif.interview.yapily.dto.YapilyCreatePaymentRequest;
import com.mif.interview.yapily.dto.YapilyCreatePaymentResponse;
import com.mif.interview.yapily.dto.YapilyGetConsentResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Service
public class YapilyHttpService {

  //TODO: move to application.yml
  private static final URI YAPILY_HOST = URI.create("https://api.yapily.com");

  @Autowired
  RestTemplate restTemplate;

  @Retryable(retryFor = { ResourceAccessException.class,
      HttpServerErrorException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000, multiplier = 2.0))
  public YapilyCreatePaymentAuthorizationResponse getPaymentAuthorization(
      @Valid YapilyCreatePaymentAuthorisationRequest request) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    var uri = UriComponentsBuilder.fromUri(YAPILY_HOST).path("/payment-auth-requests").build().toUri();
    HttpEntity<YapilyCreatePaymentAuthorisationRequest> entity = new HttpEntity<>(request, headers);
 
    var response = restTemplate.postForEntity(uri, entity, YapilyCreatePaymentAuthorizationResponse.class);
    return response.getBody();
  }

  @Retryable(retryFor = { ResourceAccessException.class,
      HttpServerErrorException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000, multiplier = 2.0))
  public YapilyGetConsentResponse getConsentToken(String consentId) {
    var uri = UriComponentsBuilder.fromUri(YAPILY_HOST).path("/consents/{consentId}").buildAndExpand(consentId).toUri();
    var response = restTemplate.getForEntity(uri, YapilyGetConsentResponse.class);
    return response.getBody();
  }
  
  @Retryable(retryFor = { ResourceAccessException.class,
      HttpServerErrorException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000, multiplier = 2.0))
  public YapilyCreatePaymentResponse processPayment(@NotBlank String consentToken,
      @Valid YapilyCreatePaymentRequest request) {
    var uri = UriComponentsBuilder.fromUri(YAPILY_HOST).path("/payments").build().toUri();

    HttpHeaders headers = new HttpHeaders();
    headers.set("consent", consentToken);
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<YapilyCreatePaymentRequest> entity = new HttpEntity<>(request, headers);

    var response = restTemplate.postForEntity(uri, entity, YapilyCreatePaymentResponse.class);
    return response.getBody();
  }

  // Add @Recover handler

}
