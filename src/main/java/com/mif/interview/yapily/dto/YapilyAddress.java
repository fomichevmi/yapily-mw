package com.mif.interview.yapily.dto;

import java.util.Arrays;
import java.util.Objects;

public class YapilyAddress {

  private String[] addressLines;
  private String streetName, buildingNumber, postCode, townName;
  private String[] county;
  private String country, department, subDepartment, addressType;

  public YapilyAddress() {
  }

  public YapilyAddress(String[] addressLines, String streetName, String buildingNumber, String postCode,
      String townName, String[] county, String country, String department, String subDepartment, String addressType) {
    super();
    this.addressLines = addressLines;
    this.streetName = streetName;
    this.buildingNumber = buildingNumber;
    this.postCode = postCode;
    this.townName = townName;
    this.county = county;
    this.country = country;
    this.department = department;
    this.subDepartment = subDepartment;
    this.addressType = addressType;
  }

  public String[] getAddressLines() {
    return addressLines;
  }

  public void setAddressLines(String[] addressLines) {
    this.addressLines = addressLines;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getBuildingNumber() {
    return buildingNumber;
  }

  public void setBuildingNumber(String buildingNumber) {
    this.buildingNumber = buildingNumber;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getTownName() {
    return townName;
  }

  public void setTownName(String townName) {
    this.townName = townName;
  }

  public String[] getCounty() {
    return county;
  }

  public void setCounty(String[] county) {
    this.county = county;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getSubDepartment() {
    return subDepartment;
  }

  public void setSubDepartment(String subDepartment) {
    this.subDepartment = subDepartment;
  }

  public String getAddressType() {
    return addressType;
  }

  public void setAddressType(String addressType) {
    this.addressType = addressType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(addressType, buildingNumber, country, department, postCode, streetName, subDepartment,
        townName);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    YapilyAddress other = (YapilyAddress) obj;
    return Objects.equals(addressType, other.addressType) && Objects.equals(buildingNumber, other.buildingNumber)
        && Objects.equals(country, other.country) && Objects.equals(department, other.department)
        && Objects.equals(postCode, other.postCode) && Objects.equals(streetName, other.streetName)
        && Objects.equals(subDepartment, other.subDepartment) && Objects.equals(townName, other.townName);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("YapilyAddress [");
    if (addressLines != null) {
      builder.append("addressLines=");
      builder.append(Arrays.toString(addressLines));
      builder.append(", ");
    }
    if (streetName != null) {
      builder.append("streetName=");
      builder.append(streetName);
      builder.append(", ");
    }
    if (buildingNumber != null) {
      builder.append("buildingNumber=");
      builder.append(buildingNumber);
      builder.append(", ");
    }
    if (postCode != null) {
      builder.append("postCode=");
      builder.append(postCode);
      builder.append(", ");
    }
    if (townName != null) {
      builder.append("townName=");
      builder.append(townName);
      builder.append(", ");
    }
    if (county != null) {
      builder.append("county=");
      builder.append(Arrays.toString(county));
      builder.append(", ");
    }
    if (country != null) {
      builder.append("country=");
      builder.append(country);
      builder.append(", ");
    }
    if (department != null) {
      builder.append("department=");
      builder.append(department);
      builder.append(", ");
    }
    if (subDepartment != null) {
      builder.append("subDepartment=");
      builder.append(subDepartment);
      builder.append(", ");
    }
    if (addressType != null) {
      builder.append("addressType=");
      builder.append(addressType);
    }
    builder.append("]");
    return builder.toString();
  }

}
