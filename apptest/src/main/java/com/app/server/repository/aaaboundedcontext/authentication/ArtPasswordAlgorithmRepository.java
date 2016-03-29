package com.app.server.repository.aaaboundedcontext.authentication;
import java.util.List;

import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;


public interface ArtPasswordAlgorithmRepository<T>  {
	 public List<T> findAll() throws SpartanPersistenceException;

	    public T save(T entity) throws SpartanPersistenceException;

	    public List<T> save(List<T> entity) throws SpartanPersistenceException;

	    public void delete(String id) throws SpartanPersistenceException;

	    public void update(T entity) throws SpartanConstraintViolationException, SpartanPersistenceException;

	    public void update(List<T> entity) throws SpartanPersistenceException;

		public T findById(String id) throws SpartanPersistenceException;

}
