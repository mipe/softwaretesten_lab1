package at.ticketline.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import at.ticketline.log.LogFactory;
import at.ticketline.log.Logger;

public abstract class GenericDaoJpa<E, ID extends Serializable> 
	implements GenericDao<E, ID> {
	
	protected transient Logger log = LogFactory.getLogger(this.getClass());
	protected Validator validator = null;
	protected Class<E> entityClass = null;
	protected String findAllQueryString = null;
	protected String countQueryString = null;

	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public GenericDaoJpa() {
		ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
		this.findAllQueryString = "SELECT e FROM " + this.entityClass.getSimpleName() + " e";
		this.countQueryString = "SELECT COUNT(e) FROM " + this.entityClass.getSimpleName() + " e";
	}

	public void remove(E entity) {
		this.entityManager.remove(entity);
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		return (List<E>) this.entityManager
		.createQuery(this.findAllQueryString).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<E> find(String where) {
		return (List<E>)this.entityManager.createQuery("SELECT e FROM " + this.entityClass.getSimpleName() + " e WHERE " + where).getResultList();
	}

	public E findById(ID id) {
		return this.entityManager.find(entityClass, id);
	}

	public Long count() {
		return (Long)this.entityManager
				.createQuery(this.countQueryString).getSingleResult();
	}
	
	public E merge(E entity) {
		Set<ConstraintViolation<E>> violations = this.validator.validate(entity);
		if (violations.isEmpty() == false) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
		return this.entityManager.merge(entity);
	}
	
    public void flush() {
    	this.entityManager.flush();
    }
    
	public E persist(E entity) {
		Set<ConstraintViolation<E>> violations = this.validator.validate(entity);
		if (violations.isEmpty() == false) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
		this.entityManager.persist(entity);
		return entity;
	}

	public Validator getValidator() {
		return this.validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

}
