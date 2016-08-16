package br.edu.ifsc.fsbilling.business.usuario;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifsc.fsbilling.business.AbstractManagerBean;
import br.edu.ifsc.fsbilling.entity.Usuario;

@Stateless
public class UsuarioManagerBean extends AbstractManagerBean<Usuario> {
	private static final long serialVersionUID = 1L;

	
	@SuppressWarnings("unchecked")
	public List<Usuario> search(int firstResult, int maxResult) {
		return em.createNamedQuery(Usuario.SEARCH).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
		
	}

	public Usuario searchByUsername(String username) {
		Usuario usuario = null;
		try {
			usuario = (Usuario) em.createNamedQuery(Usuario.SEARCH_BY_USERNAME).setParameter("username", username).getSingleResult();
		} catch (Exception e) {
		}
		return usuario;
	}
	
	public List<Usuario> listAll() {
		try {
			return em.createNamedQuery(Usuario.LIST_ALL).getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> retrieveUsers(String filter, Integer maxResult) {
		try {
			return em.createNamedQuery(Usuario.RETRIEVE_USER).setParameter("filter", "%" + filter + "%").setMaxResults(maxResult).getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Long count() {
		return (Long) em.createNamedQuery(Usuario.COUNT).getSingleResult();
		
	}
}