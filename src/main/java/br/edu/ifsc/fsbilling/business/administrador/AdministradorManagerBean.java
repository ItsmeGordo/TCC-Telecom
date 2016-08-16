package br.edu.ifsc.fsbilling.business.administrador;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifsc.fsbilling.business.AbstractManagerBean;
import br.edu.ifsc.fsbilling.entity.Administrador;

@Stateless
public class AdministradorManagerBean extends AbstractManagerBean<Administrador> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Administrador> search(int firstResult, int maxResult) {
		return em.createNamedQuery(Administrador.SEARCH).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();

	}

	public Long count() {
		return (Long) em.createNamedQuery(Administrador.COUNT).getSingleResult();

	}

	public Administrador login(String login, String senha) {
		try {
			Query query = em.createNamedQuery(Administrador.LOGIN);
			query.setParameter("login", login);
			query.setParameter("password", senha);
			return (Administrador) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}