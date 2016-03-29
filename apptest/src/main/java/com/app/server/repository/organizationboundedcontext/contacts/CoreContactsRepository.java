package com.app.server.repository.organizationboundedcontext.contacts;
import com.athena.server.repository.SearchInterface;
import com.athena.annotation.Complexity;
import com.athena.annotation.SourceCodeAuthorClass;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import java.util.List;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.app.shared.organizationboundedcontext.contacts.CommunicationData;
import com.app.shared.organizationboundedcontext.location.Address;

@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "", versionNumber = "1", comments = "Repository for CoreContacts Transaction table", complexity = Complexity.MEDIUM)
public interface CoreContactsRepository<T> extends SearchInterface {

    public List<T> findAll() throws SpartanPersistenceException;

    public T save(T entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public List<T> save(List<T> entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public void delete(String id) throws SpartanPersistenceException;

    public void deleteCommunicationData(List<CommunicationData> communicationdata) throws SpartanPersistenceException;

    public void deleteAddress(List<Address> address) throws SpartanPersistenceException;

    public void update(T entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public void update(List<T> entity) throws SpartanPersistenceException, SpartanConstraintViolationException;

    public List<T> findByTitleId(String titleId) throws Exception, SpartanPersistenceException;

    public List<T> findByNativeLanguageCode(String nativeLanguageCode) throws Exception, SpartanPersistenceException;

    public List<T> findByGenderId(String genderId) throws Exception, SpartanPersistenceException;

    public List<T> findByTimeZoneId(String timeZoneId) throws Exception, SpartanPersistenceException;

    public T findById(String contactId) throws Exception, SpartanPersistenceException;
}
