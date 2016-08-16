package br.edu.ifsc.fsbilling.business;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

import br.edu.ifsc.fsbilling.entity.AbstractEntity;


@Stateless
public class GenericManagerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "fsbilling")
	private EntityManager em;

	/** Recupera o objeto da classe desejada através do id informado.
	 * @param id chave primaria do objeto
	 * @param classe classe do objeto
	 * @return objeto recuperado da base de dados */
	public AbstractEntity findById(Long id, Class<?> classe) {
		try {
			AbstractEntity ae = (AbstractEntity) em.find(classe, id);
			return ae;
		} catch (Exception e) {
			return null;
		}
	}

}
