package at.ticketline.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface GenericDao<E, ID extends Serializable> {

	@Transactional(propagation=Propagation.REQUIRED)
	public E persist(E entity);

	@Transactional(propagation=Propagation.REQUIRED)
	public void remove(E entity);

	@Transactional(propagation=Propagation.REQUIRED)
	public E findById(ID id);

	@Transactional(propagation=Propagation.REQUIRED)
	public List<E> findAll();

	@Transactional(propagation=Propagation.REQUIRED)
	public List<E> find(String where);

	@Transactional(propagation=Propagation.REQUIRED)
	public E merge(E entity);

	@Transactional(propagation=Propagation.REQUIRED)
	public void flush();

	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public Long count();

}