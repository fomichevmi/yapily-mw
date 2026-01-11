package com.mif.interview.yapily.dto;

import java.util.Objects;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class YapilyPayee {

  /**
   * Payee Name)
   * 
   * Mandatory. The account holder name of the beneficiary.
   * 
   */
  @NotBlank
  private String name;
  /**
   * (Account Identifications) unique
   * 
   * Mandatory. The account identifications that identify the Payee bank account.
   * 
   */
  @NotEmpty
  private Set<YapilyAccountIdentification> accountIdentifications;
  /**
   * (Payee Account Type)
   * 
   * Optional. The payee account type.
   * 
   * Allowed values: BUSINESS, BUSINESS_SAVING, CHARITY, COLLECTION, CORPORATE,
   * E_WALLET, GOVERNMENT, INVESTMENT, INVESTMENT_ISA, JOINT_PERSONAL, PENSION,
   * PERSONAL, PERSONAL_SAVING, PREMIER, WEALTH
   */
  private String accountType;
  /**
   * (Address Details)
   * 
   * Conditional. The address of the Payee or Payer.
   * 
   * payee.address is mandatory when the paymentType is an INTERNATIONAL payment
   * An Institution may require you to specify the country when used in the
   * context of the Payee to be able to make a payment.
   */
  private YapilyAddress address;
  /**
   * (Merchant Id)
   * 
   * Optional. The merchant ID is a unique code provided by the payment processor
   * to the merchant.
   * 
   */
  private String merchantId;
  /**
   * (Merchant Category)
   * 
   * Optional. The category code of the merchant in case the Payee is a business.
   * Specified as a 4-letter ISO 18245 code
   */
  @Pattern(regexp = "\\d{4}$")
  private String merchantCategoryCode;

  public YapilyPayee() {
  }

  public YapilyPayee(@NotBlank String name, @NotEmpty Set<YapilyAccountIdentification> accountIdentifications,
      String accountType, YapilyAddress address, String merchantId,
      @Pattern(regexp = "\\d{4}$") String merchantCategoryCode) {
    super();
    this.name = name;
    this.accountIdentifications = accountIdentifications;
    this.accountType = accountType;
    this.address = address;
    this.merchantId = merchantId;
    this.merchantCategoryCode = merchantCategoryCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<YapilyAccountIdentification> getAccountIdentifications() {
    return accountIdentifications;
  }

  public void setAccountIdentifications(Set<YapilyAccountIdentification> accountIdentifications) {
    this.accountIdentifications = accountIdentifications;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public YapilyAddress getAddress() {
    return address;
  }

  public void setAddress(YapilyAddress address) {
    this.address = address;
  }

  public String getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }

  public String getMerchantCategoryCode() {
    return merchantCategoryCode;
  }

  public void setMerchantCategoryCode(String merchantCategoryCode) {
    this.merchantCategoryCode = merchantCategoryCode;
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountIdentifications, accountType, address, merchantCategoryCode, merchantId, name);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    YapilyPayee other = (YapilyPayee) obj;
    return Objects.equals(accountIdentifications, other.accountIdentifications)
        && Objects.equals(accountType, other.accountType) && Objects.equals(address, other.address)
        && Objects.equals(merchantCategoryCode, other.merchantCategoryCode)
        && Objects.equals(merchantId, other.merchantId) && Objects.equals(name, other.name);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyPayee [");
    if (name != null) {
      builder.append("name=");
      builder.append(name);
      builder.append(", ");
    }
    if (accountIdentifications != null) {
      builder.append("accountIdentifications=");
      builder.append(accountIdentifications);
      builder.append(", ");
    }
    if (accountType != null) {
      builder.append("accountType=");
      builder.append(accountType);
      builder.append(", ");
    }
    if (address != null) {
      builder.append("address=");
      builder.append(address);
      builder.append(", ");
    }
    if (merchantId != null) {
      builder.append("merchantId=");
      builder.append(merchantId);
      builder.append(", ");
    }
    if (merchantCategoryCode != null) {
      builder.append("merchantCategoryCode=");
      builder.append(merchantCategoryCode);
    }
    builder.append("]");
    return builder.toString();
  }

}
