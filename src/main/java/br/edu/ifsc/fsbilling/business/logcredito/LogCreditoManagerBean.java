package br.edu.ifsc.fsbilling.business.logcredito;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifsc.fsbilling.business.AbstractManagerBean;
import br.edu.ifsc.fsbilling.entity.LogCredito;
import br.edu.ifsc.fsbilling.entity.Usuario;

@Stateless
public class LogCreditoManagerBean extends AbstractManagerBean<LogCredito> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<LogCredito> search(int firstResult, int maxResult) {
		return em.createNamedQuery(LogCredito.SEARCH).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
		
	}

	public Long count() {
		return (Long) em.createNamedQuery(LogCredito.COUNT).getSingleResult();
		
	}
	
	@Override
	public LogCredito update(LogCredito toUpdate) {
		Usuario usuario = toUpdate.getUsuario();
		usuario.setCredito(usuario.getCredito().add(toUpdate.getValorAdicionado()));
		em.merge(usuario);
		return super.update(toUpdate);
	}
}