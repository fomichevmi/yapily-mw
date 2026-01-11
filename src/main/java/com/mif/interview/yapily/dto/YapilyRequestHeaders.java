package com.mif.interview.yapily.dto;

/**
 * Available headers for requests to Yapily API
 * https://docs.yapily.com/api/reference/#operation/createPayment
 */
public class YapilyRequestHeaders {

  /**
   * Mandatory. The consent-token containing the user's authorisation to make the
   * request. Example: {consentToken}
   */
  public static String CONSENT = "consent";
  /**
   * Conditional. Represents the user's login ID for the Institution to a personal
   * account.
   * 
   * See PSU identifiers
   * (https://docs.yapily.com/pages/knowledge/open-banking/psu_identifiers/) to
   * see if this header is required.
   */
  public static String PSU_ID = "psu-id";
  /**
   * Conditional. Represents the user's login ID for the Institution to a business
   * account.
   * 
   * See PSU identifiers
   * (https://docs.yapily.com/pages/knowledge/open-banking/psu_identifiers/) to
   * see if this header is required.
   */
  public static String PSU_CORPORATE_ID = "psu-corporate-id";
  /**
   * Conditional. The IP address of the PSU.
   * 
   * See PSU identifiers
   * (https://docs.yapily.com/pages/knowledge/open-banking/psu_identifiers/) to
   * see if this header is required.
   */
  public static String PSU_IP_ADDRESS = "psu-ip-address";
  /**
   * <uuid>
   * 
   * The sub-application ID to which event type is being subscribed to 
   */
  public static String SUB_APPLICATION = "sub-application";
}
