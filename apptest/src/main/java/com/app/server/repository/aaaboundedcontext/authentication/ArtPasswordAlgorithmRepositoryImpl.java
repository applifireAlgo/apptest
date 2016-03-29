package com.app.server.repository.aaaboundedcontext.authentication;
import com.app.shared.aaaboundedcontext.authentication.ArtPasswordAlgorithm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.athena.config.server.helper.ResourceFactoryManagerHelper;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;

@Repository
public class ArtPasswordAlgorithmRepositoryImpl implements ArtPasswordAlgorithmRepository<ArtPasswordAlgorithm> {

	@Autowired
	private ResourceFactoryManagerHelper emfResource;

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Override
	@Transactional
	public List<ArtPasswordAlgorithm> findAll() throws SpartanPersistenceException {
		try {
			javax.persistence.EntityManager emanager = emfResource.getResource();
			java.util.List<ArtPasswordAlgorithm> query = emanager.createQuery("select u from ArtPasswordAlgorithm u").getResultList();
			return query;
		} catch (javax.persistence.PersistenceException e) {
			throw new SpartanPersistenceException("Error in retrieving entity", e);
		}
	}

	@Override
	@Transactional
	public ArtPasswordAlgorithm save(ArtPasswordAlgorithm entity) throws SpartanPersistenceException {
		try {
			javax.persistence.EntityManager emanager = emfResource.getResource();
			emanager.persist(entity);
			return entity;
		} catch (javax.persistence.PersistenceException e) {
			throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error in entity creation", e);
		}
	}

	@Override
	@Transactional
	public List<ArtPasswordAlgorithm> save(List<ArtPasswordAlgorithm> entity) throws SpartanPersistenceException {
		try {
			javax.persistence.EntityManager emanager = emfResource.getResource();
			for (int i = 0; i < entity.size(); i++) {
				ArtPasswordAlgorithm obj = entity.get(i);
				emanager.persist(obj);
			}
			return entity;
		} catch (javax.persistence.PersistenceException e) {
			throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error in entity Saving", e);
		}
	}

	@Transactional
	@Override
	public void delete(String id) throws SpartanPersistenceException {
		try {
			javax.persistence.EntityManager emanager = emfResource.getResource();
			ArtPasswordAlgorithm s = emanager.find(ArtPasswordAlgorithm.class, id);
			emanager.remove(s);
		} catch (javax.persistence.PersistenceException e) {
			throw new SpartanPersistenceException("Error in deleting entity", e);
		}
	}

	@Override
	@Transactional
	public void update(ArtPasswordAlgorithm entity) throws SpartanPersistenceException {
		try {
			javax.persistence.EntityManager emanager = emfResource.getResource();
			emanager.merge(entity);
		} catch (javax.persistence.PersistenceException e) {
			throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error in entity creation", e);
		}
	}

	@Override
	@Transactional
	public void update(List<ArtPasswordAlgorithm> entity) throws SpartanPersistenceException {
		try {
			javax.persistence.EntityManager emanager = emfResource.getResource();
			for (int i = 0; i < entity.size(); i++) {
				ArtPasswordAlgorithm obj = entity.get(i);
				emanager.merge(obj);
			}
		} catch (javax.persistence.PersistenceException e) {
			throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error in entity updation", e);
		}
	}

	@Override
	public ArtPasswordAlgorithm findById(String id) throws SpartanPersistenceException {
		try {
			javax.persistence.EntityManager emanager = emfResource.getResource();
			ArtPasswordAlgorithm s = emanager.find(ArtPasswordAlgorithm.class, id);
			return s;
		} catch (javax.persistence.PersistenceException e) {
			throw new SpartanPersistenceException("Error in deleting entity", e);
		}
	}

}
