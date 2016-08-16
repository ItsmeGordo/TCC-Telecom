package br.edu.ifsc.fsbilling.business.tarifavenda;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifsc.fsbilling.business.AbstractManagerBean;
import br.edu.ifsc.fsbilling.entity.TarifaVenda;
import br.edu.ifsc.fsbilling.entity.Usuario;

@Stateless
public class TarifaVendaManagerBean extends AbstractManagerBean<TarifaVenda> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<TarifaVenda> search(int firstResult, int maxResult) {
		return em.createNamedQuery(TarifaVenda.SEARCH).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
		
	}

	public Long count() {
		return (Long) em.createNamedQuery(TarifaVenda.COUNT).getSingleResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<TarifaVenda> listAll() {
		return em.createNamedQuery(TarifaVenda.LIST_ALL).getResultList();
	}
}