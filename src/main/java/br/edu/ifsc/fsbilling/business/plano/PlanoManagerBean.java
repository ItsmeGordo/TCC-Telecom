package br.edu.ifsc.fsbilling.business.plano;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifsc.fsbilling.business.AbstractManagerBean;
import br.edu.ifsc.fsbilling.entity.Plano;
import br.edu.ifsc.fsbilling.entity.Usuario;

@Stateless
public class PlanoManagerBean extends AbstractManagerBean<Plano> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Plano> search(int firstResult, int maxResult) {
		return em.createNamedQuery(Plano.SEARCH).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
		
	}

	public Long count() {
		return (Long) em.createNamedQuery(Plano.COUNT).getSingleResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Plano> listAll() {
		return em.createNamedQuery(Plano.SEARCH).getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Plano> retrievePlanos(String nome, int maxResults) {
		List<Plano> lista = em.createNamedQuery(Plano.RETRIEVE_PLANO).setParameter("nome", "%" + nome + "%").setMaxResults(maxResults).getResultList();
		return lista;
	}
	
	public Plano retrievePlanoFromUsuario(Usuario usuario) {
		return (Plano) em.createNamedQuery(Plano.RETRIEVE_PLANO_FROM_USUARIO).setParameter("usuario", usuario).getSingleResult();
	}
}