package br.edu.ifsc.fsbilling.business.tarifacompra;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifsc.fsbilling.business.AbstractManagerBean;
import br.edu.ifsc.fsbilling.entity.TarifaCompra;

@Stateless
public class TarifaCompraManagerBean extends AbstractManagerBean<TarifaCompra> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<TarifaCompra> search(int firstResult, int maxResult) {
		return em.createNamedQuery(TarifaCompra.SEARCH).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
		
	}

	public Long count() {
		return (Long) em.createNamedQuery(TarifaCompra.COUNT).getSingleResult();
		
	}
	
	public List<TarifaCompra> listAll() {
		return em.createNamedQuery(TarifaCompra.LIST_ALL).getResultList();
	}
}