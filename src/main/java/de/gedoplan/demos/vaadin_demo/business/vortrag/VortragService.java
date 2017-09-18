package de.gedoplan.demos.vaadin_demo.business.vortrag;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class VortragService {

	@Inject
	private EntityManager em;
	
	public void create(Vortrag vortrag) {
		em.persist(vortrag);
	}
	
	public Vortrag update(Vortrag vortrag) {
		return em.merge(vortrag);
	}
	
	public void remove(Vortrag vortrag) {
		em.remove(vortrag);
	}
	
	public List<Vortrag> findAll() {
		return em.createQuery("select v from Vortrag v", Vortrag.class).getResultList();
	}
	
	public Integer count() {
		return em.createQuery("select count(v) from Vortrag v",Long.class).getSingleResult().intValue();
	}
}
