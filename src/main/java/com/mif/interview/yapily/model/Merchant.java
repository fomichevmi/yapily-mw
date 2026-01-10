package com.mif.interview.yapily.model;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Merchant {

  private Integer id;
  private String name;

  public Merchant() {
  }

  public Merchant(@NotNull Integer id, @NotBlank String name) {
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(@NotNull Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(@NotBlank String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Merchant other = (Merchant) obj;
    return Objects.equals(id, other.id);
  }

}
