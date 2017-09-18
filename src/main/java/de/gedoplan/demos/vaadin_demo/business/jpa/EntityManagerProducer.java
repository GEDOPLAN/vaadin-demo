package de.gedoplan.demos.vaadin_demo.business.jpa;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

  @PersistenceContext
  @Produces
  private EntityManager em;
}
