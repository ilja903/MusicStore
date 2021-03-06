package service.abstractions;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.abstractions.EntityInterface;

/**
 * CRUD operations for EJBs which manage Entities
 * 
 * @author ilja
 * @param <T>
 *            - Type of an EntityInterace
 * @see {@link EntityInterface}
 */
public abstract class AbstractFacade<T extends EntityInterface> {

	private final Class<T> entityClass;

	@PersistenceContext(unitName = "MusicStore")
	private EntityManager em;

	public AbstractFacade(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * @param entity
	 *            to be persisted to database
	 * 
	 */
	public void create(final T entity) {
		getEm().persist(entity);
	}

	/**
	 * @param entity
	 *            which will be synchronized with database
	 */
	public void update(final T entity) {
		getEm().merge(entity);
	}

	/**
	 * @param entity
	 *            which will be removed from database
	 * 
	 */
	public void delete(final T entity) {
		try {
			getEm().remove(getEm().merge(entity));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return all entities
	 */
	public List<T> findAll() {
		try {
			return getEm().createQuery(
					"SELECT e FROM " + entityClass.getName() + " e",
					entityClass).getResultList();
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * @param id
	 *            of entity which you want to find
	 * @return entity with given id from database
	 */
	public T find(final long id) {
		return getEm().find(entityClass, id);
	}

	/**
	 * @return sum of entities of a given type
	 */
	public long count() {
		return ((Long) getEm().createQuery(
				"select count(e) from " + entityClass.getName() + " as e")
				.getSingleResult()).longValue();
	}

	/**
	 * flushes EntityManger
	 */
	public void flushEm() {
		getEm().flush();
	}

	/**
	 * @return injected EntityManager
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 *            entityManager to be set
	 */
	public void setEm(final EntityManager em) {
		this.em = em;
	}
}