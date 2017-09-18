package de.gedoplan.demos.vaadin_demo.business.country;

import java.util.Currency;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Startup
@Singleton
public class TestDataGenerator {

  @Inject
  private EntityManager em;

  @PostConstruct
  protected void init() {
    insertCountries();
  }

  private void insertCountries() {
    for (String countryCode : Locale.getISOCountries()) {
      Locale locale = new Locale("de", countryCode);
      Country country = new Country();
      country.setCode(countryCode);
      country.setFullName(locale.getDisplayCountry());
      country.setIso3Code(locale.getISO3Country());
      Currency currency = Currency.getInstance(locale);
      if (currency != null) {
        country.setCurrency(currency.getDisplayName());
      }
      em.persist(country);
    }
  }
}
