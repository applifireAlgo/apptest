package com.app.server.repository.aaaboundedcontext.authentication;
import com.app.shared.aaaboundedcontext.authentication.ArtPasswordPolicy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.athena.config.server.helper.ResourceFactoryManagerHelper;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;

@Repository
public class ArtPasswordPolicyRepositoryImpl implements ArtPasswordPolicyRepository<ArtPasswordPolicy> {

	@Autowired
	private ResourceFactoryManagerHelper emfResource;

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Override
	public List<ArtPasswordPolicy> findAll() throws SpartanPersistenceException {
		try {
			javax.persistence.EntityManager emanager = emfResource.getResource();
			java.util.List<ArtPasswordPolicy> query = emanager.createQuery("select u from ArtPasswordPolicy u").getResultList();
			return query;
		} catch (javax.persistence.PersistenceException e) {
			throw new SpartanPersistenceException("Error in retrieving entity", e);
		}
	}

	@Override
	public ArtPasswordPolicy save(ArtPasswordPolicy entity) throws SpartanPersistenceException {
		try {
			javax.persistence.EntityManager emanager = emfResource.getResource();
			emanager.persist(entity);
			return entity;
		} catch (javax.persistence.PersistenceException e) {
			throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error in entity creation", e);
		}
	}

	@Override
	public List<ArtPasswordPolicy> save(List<ArtPasswordPolicy> entity) throws SpartanPersistenceException {
		try {
			javax.persistence.EntityManager emanager = emfResource.getResource();
			for (int i = 0; i < entity.size(); i++) {
				ArtPasswordPolicy obj = entity.get(i);
				emanager.persist(obj);
			}
			return entity;
		} catch (javax.persistence.PersistenceException e) {
			throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error in entity Saving", e);
		}
	}

	@Override
	public void delete(String id) throws SpartanPersistenceException {
		try {
			javax.persistence.EntityManager emanager = emfResource.getResource();
			ArtPasswordPolicy s = emanager.find(ArtPasswordPolicy.class, id);
			emanager.remove(s);
		} catch (javax.persistence.PersistenceException e) {
			throw new SpartanPersistenceException("Error in deleting entity", e);
		}

	}

	@Override
	public void update(ArtPasswordPolicy entity) throws SpartanConstraintViolationException, SpartanPersistenceException {
		try {
			javax.persistence.EntityManager emanager = emfResource.getResource();
			emanager.merge(entity);
		} catch (javax.persistence.PersistenceException e) {
			throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error in entity creation", e);
		}

	}

	@Override
	public void update(List<ArtPasswordPolicy> entity) throws SpartanPersistenceException {
		try {
			javax.persistence.EntityManager emanager = emfResource.getResource();
			for (int i = 0; i < entity.size(); i++) {
				ArtPasswordPolicy obj = entity.get(i);
				emanager.merge(obj);
			}
		} catch (javax.persistence.PersistenceException e) {
			throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error in entity updation", e);
		}

	}

	@Override
	public ArtPasswordPolicy findById(String id) throws SpartanPersistenceException {
		try {
			javax.persistence.EntityManager emanager = emfResource.getResource();
			ArtPasswordPolicy s = emanager.find(ArtPasswordPolicy.class, id);
			return s;
		} catch (javax.persistence.PersistenceException e) {
			throw new SpartanPersistenceException("Error in deleting entity", e);
		}
	}

}
