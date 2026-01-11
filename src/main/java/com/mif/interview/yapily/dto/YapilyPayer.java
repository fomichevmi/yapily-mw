package com.mif.interview.yapily.dto;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;

public class YapilyPayer {

  private Set<YapilyAccountIdentification> accountIdentifications;
  private YapilyAddress address;

  public YapilyPayer() {
  }

  public YapilyPayer(@NotEmpty Set<YapilyAccountIdentification> accountIdentifications, YapilyAddress address) {
    this.accountIdentifications = accountIdentifications;
    this.address = address;
  }

  public Set<YapilyAccountIdentification> getAccountIdentifications() {
    return accountIdentifications;
  }

  public void setAccountIdentifications(@NotEmpty Set<YapilyAccountIdentification> accountIdentifications) {
    this.accountIdentifications = accountIdentifications;
  }

  public YapilyAddress getAddress() {
    return address;
  }

  public void setAddress(YapilyAddress address) {
    this.address = address;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(accountIdentifications.toArray());
    result = prime * result + Objects.hash(address);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    YapilyPayer other = (YapilyPayer) obj;
    return Arrays.equals(accountIdentifications.toArray(), other.accountIdentifications.toArray())
        && Objects.equals(address, other.address);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyPayer [");
    if (accountIdentifications != null) {
      builder.append("accountIdentifications=");
      builder.append(Arrays.toString(accountIdentifications.toArray()));
      builder.append(", ");
    }
    if (address != null) {
      builder.append("address=");
      builder.append(address);
    }
    builder.append("]");
    return builder.toString();
  }

}
