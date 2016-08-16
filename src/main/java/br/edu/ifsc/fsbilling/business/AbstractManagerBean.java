package br.edu.ifsc.fsbilling.business;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.edu.ifsc.fsbilling.entity.AbstractEntity;

@Stateless
public abstract class AbstractManagerBean<T extends AbstractEntity> implements
		Serializable {
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "fsbilling")
	protected EntityManager em;

	/**
	 * Recupera a entidade atravéz de seu id.
	 * 
	 * @param id
	 * @param classe
	 * @return
	 */
	public T findById(Serializable id, Class<T> classe) {
		return em.find(classe, id);
	}

	/**
	 * Persiste uma entidade sem id na base de dados. Um id será gerado para o
	 * mesmo.
	 * 
	 * @param toPersist
	 */
	public void persist(final T toPersist) {
		em.persist(toPersist);
	}

	/**
	 * Atualiza uma entidade já existente e retorna o resultado final. Caso a
	 * entidade ainda não exista na base de dados, a mesma será persistida e um
	 * id será gerado.
	 * 
	 * @param toUpdate
	 * @return
	 */
	public T update(final T toUpdate) {
		T merge = em.merge(toUpdate);
		em.flush();
		return merge;
	}

	/**
	 * Remove a entidade da base de dados.
	 * 
	 * @param toDelete
	 * @throws BusinessException
	 */
	public void delete(final T toDelete) {
		em.remove(em.merge(toDelete));
		em.flush();
	}

	/**
	 * Sincroniza os dados da entidade com os dados da base. Todas as
	 * modificações realizadas localmente serão descartadas.
	 * 
	 * @param toRefresh
	 */
	public void refresh(T toRefresh) {
		em.refresh(toRefresh);
	}

	/** Efetua o commit de todas atualizações de base ainda não efetuadas. */
	public void flush() {
		em.flush();
	}

	/**
	 * Desconsidera a entidade em futuros commits (não será
	 * atualizado/persistido).
	 * 
	 * @param toDetach
	 */
	public void detach(T toDetach) {
		em.detach(toDetach);
	}
}