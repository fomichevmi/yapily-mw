package com.mif.interview.yapily.dto;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public class YapilyAccountIdentification {

  private String type;
  private String identification;

  public YapilyAccountIdentification() {
  }

  public YapilyAccountIdentification(@NotBlank String type, @NotBlank String identification) {
    this.type = type;
    this.identification = identification;
  }

  public String getType() {
    return type;
  }

  public void setType(@NotBlank String type) {
    this.type = type;
  }

  public String getIdentification() {
    return identification;
  }

  public void setIdentification(@NotBlank String identification) {
    this.identification = identification;
  }

  @Override
  public int hashCode() {
    return Objects.hash(identification, type);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    YapilyAccountIdentification other = (YapilyAccountIdentification) obj;
    return Objects.equals(identification, other.identification) && Objects.equals(type, other.type);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyAccountIdentification [type=");
    builder.append(type);
    builder.append(", identification=");
    builder.append(identification);
    builder.append("]");
    return builder.toString();
  }

}
