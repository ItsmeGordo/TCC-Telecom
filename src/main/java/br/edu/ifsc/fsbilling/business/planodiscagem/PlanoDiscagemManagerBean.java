package br.edu.ifsc.fsbilling.business.planodiscagem;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.edu.ifsc.fsbilling.business.AbstractManagerBean;
import br.edu.ifsc.fsbilling.entity.PlanoDiscagem;

@Stateless
public class PlanoDiscagemManagerBean extends AbstractManagerBean<PlanoDiscagem> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<PlanoDiscagem> search(int firstResult, int maxResult) {
		return em.createNamedQuery(PlanoDiscagem.SEARCH).setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
		
	}

	public Long count() {
		return (Long) em.createNamedQuery(PlanoDiscagem.COUNT).getSingleResult();
		
	}
	
	public PlanoDiscagem searchByRegexp(String numeroDiscado, Long idPlano) {
		Query qry = em.createNativeQuery("SELECT pd.* FROM fsbilling.PLANO_DE_DISCAGEM pd "
				+ "LEFT JOIN TARIFA_VENDA tf on tf.ID = pd.ID_TARIFA_VENDA "
				+ "where ? REGEXP pd.REGULAR_EXPRESSION "
				+ " and pd.ID_PLANO = ? "
				+ " order by tf.VALOR_VENDA/tf.CADENCIA asc", PlanoDiscagem.class);
		qry.setParameter(1, numeroDiscado);
		qry.setParameter(2, idPlano);
		qry.setMaxResults(1);
		try {
			return (PlanoDiscagem) qry.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
}