package com.app.server.repository.aaaboundedcontext.authorization;
import com.app.shared.aaaboundedcontext.authorization.UserRoleBridge;
import com.athena.server.repository.SearchInterfaceImpl;
import org.springframework.stereotype.Repository;
import com.athena.annotation.Complexity;
import com.athena.annotation.SourceCodeAuthorClass;
import com.athena.config.server.helper.ResourceFactoryManagerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import java.lang.Override;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;

@Repository
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "", versionNumber = "1", comments = "Repository for UserRoleBridge Transaction table", complexity = Complexity.MEDIUM)
public class UserRoleBridgeRepositoryImpl extends SearchInterfaceImpl implements UserRoleBridgeRepository<UserRoleBridge> {

    @Autowired
    private ResourceFactoryManagerHelper emfResource;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Override
    @Transactional
    public List<UserRoleBridge> findAll() throws SpartanPersistenceException {
        try {
            javax.persistence.EntityManager emanager = emfResource.getResource();
            java.util.List<com.app.shared.aaaboundedcontext.authorization.UserRoleBridge> query = emanager.createQuery("select u from UserRoleBridge u where u.systemInfo.activeStatus=1").getResultList();
            return query;
        } catch (javax.persistence.PersistenceException e) {
            throw new SpartanPersistenceException("Error in retrieving entity", e);
        }
    }

    @Override
    @Transactional
    public UserRoleBridge save(UserRoleBridge entity) throws SpartanPersistenceException, SpartanConstraintViolationException {
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
    public List<UserRoleBridge> save(List<UserRoleBridge> entity) throws SpartanPersistenceException, SpartanConstraintViolationException {
        try {
            javax.persistence.EntityManager emanager = emfResource.getResource();
            for (int i = 0; i < entity.size(); i++) {
                com.app.shared.aaaboundedcontext.authorization.UserRoleBridge obj = entity.get(i);
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
            com.app.shared.aaaboundedcontext.authorization.UserRoleBridge s = emanager.find(com.app.shared.aaaboundedcontext.authorization.UserRoleBridge.class, id);
            emanager.remove(s);
        } catch (javax.persistence.PersistenceException e) {
            throw new SpartanPersistenceException("Error in deleting entity", e);
        }
    }

    @Override
    @Transactional
    public void update(UserRoleBridge entity) throws SpartanPersistenceException, SpartanConstraintViolationException {
        try {
            javax.persistence.EntityManager emanager = emfResource.getResource();
            emanager.merge(entity);
        } catch (javax.persistence.PersistenceException e) {
            throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error in entity creation", e);
        } catch (Exception e) {
            throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error while updating entity", e);
        }
    }

    @Override
    @Transactional
    public void update(List<UserRoleBridge> entity) throws SpartanPersistenceException, SpartanConstraintViolationException {
        try {
            javax.persistence.EntityManager emanager = emfResource.getResource();
            for (int i = 0; i < entity.size(); i++) {
                com.app.shared.aaaboundedcontext.authorization.UserRoleBridge obj = entity.get(i);
                emanager.merge(obj);
            }
        } catch (javax.persistence.PersistenceException e) {
            throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error in entity updation", e);
        } catch (Exception e) {
            throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error while updating entity", e);
        }
    }

    @Transactional
    public List<UserRoleBridge> findByRoleId(String roleId) throws Exception, SpartanPersistenceException {
        try {
            javax.persistence.EntityManager emanager = emfResource.getResource();
            javax.persistence.Query query = emanager.createNamedQuery("UserRoleBridge.findByRoleId");
            query.setParameter("roleId", roleId);
            java.util.List<com.app.shared.aaaboundedcontext.authorization.UserRoleBridge> listOfUserRoleBridge = query.getResultList();
            return listOfUserRoleBridge;
        } catch (javax.persistence.PersistenceException e) {
            throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error in executing query", e);
        }
    }

    @Transactional
    public List<UserRoleBridge> findByUserId(String userId) throws Exception, SpartanPersistenceException {
        try {
            javax.persistence.EntityManager emanager = emfResource.getResource();
            javax.persistence.Query query = emanager.createNamedQuery("UserRoleBridge.findByUserId");
            query.setParameter("userId", userId);
            java.util.List<com.app.shared.aaaboundedcontext.authorization.UserRoleBridge> listOfUserRoleBridge = query.getResultList();
            return listOfUserRoleBridge;
        } catch (javax.persistence.PersistenceException e) {
            throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error in executing query", e);
        }
    }

    @Transactional
    public UserRoleBridge findById(String roleUserMapId) throws Exception, SpartanPersistenceException {
        try {
            javax.persistence.EntityManager emanager = emfResource.getResource();
            javax.persistence.Query query = emanager.createNamedQuery("UserRoleBridge.findById");
            query.setParameter("roleUserMapId", roleUserMapId);
            com.app.shared.aaaboundedcontext.authorization.UserRoleBridge listOfUserRoleBridge = (com.app.shared.aaaboundedcontext.authorization.UserRoleBridge) query.getSingleResult();
            return listOfUserRoleBridge;
        } catch (javax.persistence.PersistenceException e) {
            throw new com.athena.framework.server.exception.repository.SpartanPersistenceException("Error in executing query", e);
        }
    }
}
