package de.gedoplan.demos.vaadin_demo.business.country;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country implements Serializable {

  @Id
  private String code;

  private String iso3Code;

  private String currency;

  private String fullName;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getIso3Code() {
    return iso3Code;
  }

  public void setIso3Code(String isoCode) {
    this.iso3Code = isoCode;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 67 * hash + Objects.hashCode(this.code);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Country other = (Country) obj;
    if (!Objects.equals(this.code, other.code)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return fullName;
  }

}
