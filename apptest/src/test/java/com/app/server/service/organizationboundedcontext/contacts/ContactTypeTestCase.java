package com.app.server.service.organizationboundedcontext.contacts;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationboundedcontext.contacts.ContactTypeRepository;
import com.app.shared.organizationboundedcontext.contacts.ContactType;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.athena.framework.server.test.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.Before;
import org.junit.After;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class ContactTypeTestCase extends EntityTestCriteria {

    @Autowired
    private ContactTypeRepository<ContactType> contacttypeRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo(1, "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
    }

    private ContactType createContactType(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        ContactType contacttype = new ContactType();
        contacttype.setContactTypeIcon("VNp0KqvItKHE62xRYKqgw2KHmBOmfnylgYODsm9TyXEmeeTpmv");
        contacttype.setContactTypeDesc("W3MJAMtc9vU2JUYz0Lg75dVWBzFHBEfHJoHfaNz4V6zsc9XiHt");
        contacttype.setContactType("4aAlPUlztnmJdM0eMkNLhifPCF5MCIKU51Ds9cI1AQ5Ixip07w");
        contacttype.setEntityValidator(entityValidator);
        return contacttype;
    }

    @Test
    public void test1Save() {
        try {
            ContactType contacttype = createContactType(true);
            contacttype.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            contacttype.isValid();
            contacttypeRepository.save(contacttype);
            map.put("ContactTypePrimaryKey", contacttype._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("ContactTypePrimaryKey"));
            ContactType contacttype = contacttypeRepository.findById((java.lang.String) map.get("ContactTypePrimaryKey"));
            contacttype.setContactTypeIcon("WCzxRLILLWGa9SIyT55ZbxfoE3OO7JKBcZmXfzLmB67v9vB1YU");
            contacttype.setContactTypeDesc("xUx0k74GOYR2Svy0pdvrtKXuTaTZK0rbyMx2WmwKmPVToozdxQ");
            contacttype.setVersionId(1);
            contacttype.setContactType("V1nDWQfAD3KrnVLUKV59n6LhTaxer2PtSsl6HH1QLs0KgXLPkE");
            contacttype.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            contacttypeRepository.update(contacttype);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("ContactTypePrimaryKey"));
            contacttypeRepository.findById((java.lang.String) map.get("ContactTypePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("ContactTypePrimaryKey"));
            contacttypeRepository.delete((java.lang.String) map.get("ContactTypePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateContactType(EntityTestCriteria contraints, ContactType contacttype) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            contacttype.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            contacttype.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            contacttype.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            contacttypeRepository.save(contacttype);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "contactType", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "contactType", "BNMBMgAPQ9Bn5i6GJhFaj4o2YFBqjDkzqW5Kd9USunAClMX5F97Ljt81HwaIbwULaGpN5YxH6pDganfQkcA3TDBg4ufZnbU3iyMApxJ8Po3CXq6Gr3D9X9RY6VNBmEfFz"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "contactTypeDesc", "ocz9jEEPq0ZZ8lHX6IXlPkjnapvjPj9PntDcjIRk6BnRWJdM3YIh46XtPNJb6NnSkp5Ffp1EyVuD5unT0y8Tq4QYlL9vgc8Wpt6kB4tLdfOSpS3J8ZHf2exnWtbnVxLVS5OsGJHMF2oFarUc8yt7Ew1m9OoibnKwX8Zu4CfXbctk7QWOWx2Op2ijzlMNjEH1Epi09Hz05gCh4pRiazO4oQyPssEakvpPlYo1KONobZmH5v8dvpu3oRcCIXeeNc4Y2"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "contactTypeIcon", "SEwjM3bJEr21GmRWIrSSX72T3KTDr2GGWsmTllMjoFK6IBGdrXgTOrmjcSs1mCaCJ8RgpKvX5sRHQNQ2cIpa08KQKXSSaNckiZQ5QneLyzVhdHBNwQQ0JYMiiZntPnIf8"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                ContactType contacttype = createContactType(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = contacttype.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(contacttype, null);
                        validateContactType(contraints, contacttype);
                        failureCount++;
                        break;
                    case 2:
                        contacttype.setContactType(contraints.getNegativeValue().toString());
                        validateContactType(contraints, contacttype);
                        failureCount++;
                        break;
                    case 3:
                        contacttype.setContactTypeDesc(contraints.getNegativeValue().toString());
                        validateContactType(contraints, contacttype);
                        failureCount++;
                        break;
                    case 4:
                        contacttype.setContactTypeIcon(contraints.getNegativeValue().toString());
                        validateContactType(contraints, contacttype);
                        failureCount++;
                        break;
                }
            } catch (SpartanIncorrectDataException e) {
                e.printStackTrace();
            } catch (SpartanConstraintViolationException e) {
                e.printStackTrace();
            } catch (SpartanPersistenceException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
