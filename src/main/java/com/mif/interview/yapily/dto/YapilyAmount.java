package com.mif.interview.yapily.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class YapilyAmount {

  /**
   * (Amount)
   * 
   * Mandatory. The monetary value
   */
  @Positive
  private double amount;
  /**
   * (Currency)
   * 
   * Mandatory. The ISO 4217 (https://www.xe.com/iso4217.php) currency code
   */
  @NotBlank
  private String currency;

  public YapilyAmount() {
  }

  public YapilyAmount(@Positive double amount, @NotBlank String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    YapilyAmount other = (YapilyAmount) obj;
    return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
        && Objects.equals(currency, other.currency);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyAmount [amount=");
    builder.append(amount);
    builder.append(", ");
    if (currency != null) {
      builder.append("currency=");
      builder.append(currency);
    }
    builder.append("]");
    return builder.toString();
  }

}
