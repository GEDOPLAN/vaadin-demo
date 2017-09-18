package de.gedoplan.demos.vaadin_demo.business.country;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Transactional
public class CountryService implements Serializable {

  @Inject
  private EntityManager em;

  public List<Country> autoComplete(String text) {
    TypedQuery<Country> query = em.createQuery("select c from Country c where lower(c.fullName) like CONCAT(:text, '%')", Country.class);
    query.setParameter("text", text.toLowerCase());
    return query.getResultList();
  }

  public Country findCountryByName(String countryName) {
    TypedQuery<Country> query = em.createQuery("select c from Country c where lower(c.fullName) = :country ", Country.class);
    query.setParameter("country", countryName.toLowerCase());
    return query.getSingleResult();
  }

  public Country findById(String id) {
    return em.find(Country.class, id);
  }

  public List<Country> loadCountries(int offset, int limit) {
    TypedQuery<Country> query = em.createQuery("select c from Country c", Country.class);
    query.setFirstResult(offset);
    query.setMaxResults(limit);
    return query.getResultList();
  }

  public Long getCountryCount() {
    return em.createQuery("select count(c) from Country c", Long.class).getSingleResult();
  }

  public List<Country> loadCountries(int offset, int limit, Map<String, Boolean> sortOrder, Map<String, String> filters) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Country> query = cb.createQuery(Country.class);
    Root<Country> root = query.from(Country.class);

    query.select(root).where(getFilters(filters, cb, root)).orderBy(getSorting(sortOrder, cb, root));

    TypedQuery<Country> typedQuery = em.createQuery(query);
    typedQuery.setMaxResults(limit);
    typedQuery.setFirstResult(offset);

    return typedQuery.getResultList();
  }

  public Long countCountries(Map<String, String> filters) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> query = cb.createQuery(Long.class);
    Root<Country> root = query.from(Country.class);

    query.select(cb.count(root)).where(getFilters(filters, cb, root));

    return em.createQuery(query).getSingleResult();
  }

  private Order[] getSorting(Map<String, Boolean> sortOrder, CriteriaBuilder cb, Root<Country> root) {
    return sortOrder.entrySet().stream().map((sort) -> sort.getValue() ? cb.desc(root.get(sort.getKey())) : cb.asc(root.get(sort.getKey()))).toArray(Order[]::new);
  }

  private Predicate[] getFilters(Map<String, String> filters, CriteriaBuilder cb, Root<Country> root) {
    if (filters == null) {
      return new Predicate[0];
    }
    return filters.entrySet().stream().map((filter) -> cb.like(cb.lower(root.get(filter.getKey())), "%" + filter.getValue().toLowerCase() + "%"))
        .toArray(Predicate[]::new);
  }
}
