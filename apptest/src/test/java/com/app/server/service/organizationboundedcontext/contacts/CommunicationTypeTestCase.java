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
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationTypeRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationType;
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
import com.app.shared.organizationboundedcontext.contacts.CommunicationGroup;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationGroupRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CommunicationTypeTestCase extends EntityTestCriteria {

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

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

    private CommunicationType createCommunicationType(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("0D7pgW2yZQQHPqfgcEypbDXJTehgDvs4swbKSB79HUqUI9ZCh8");
        communicationgroup.setCommGroupName("4xTiCbAVpIBMCPnTXaNfxyaPOP80wDRPEGtWBKISMiMtC73o5B");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey());
        communicationtype.setCommTypeName("pvr9wTydZG6lCSloO9kQ8yiPmm7U6SWzi3IAqYDihI29T9KKim");
        communicationtype.setCommTypeDescription("92YyYstXbVvI3vEhtXLMdNHPJbTNO9heNlGFTaO9wsgSRvdOUB");
        communicationtype.setEntityValidator(entityValidator);
        return communicationtype;
    }

    @Test
    public void test1Save() {
        try {
            CommunicationType communicationtype = createCommunicationType(true);
            communicationtype.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            communicationtype.isValid();
            communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CommunicationTypePrimaryKey"));
            CommunicationType communicationtype = communicationtypeRepository.findById((java.lang.String) map.get("CommunicationTypePrimaryKey"));
            communicationtype.setCommTypeName("2ArQde7OWjPWOzudIDpwxt3OC4SqNGf2cO0ek2i5EzJWPtURN2");
            communicationtype.setVersionId(1);
            communicationtype.setCommTypeDescription("hLMXaGgF74sMq3LaiPAp8IMN1w7Bib02GAf98aHsvoJsCQ0aUw");
            communicationtype.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            communicationtypeRepository.update(communicationtype);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CommunicationTypePrimaryKey"));
            communicationtypeRepository.findById((java.lang.String) map.get("CommunicationTypePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycommGroupId() {
        try {
            java.util.List<CommunicationType> listofcommGroupId = communicationtypeRepository.findByCommGroupId((java.lang.String) map.get("CommunicationGroupPrimaryKey"));
            if (listofcommGroupId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CommunicationTypePrimaryKey"));
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCommunicationType(EntityTestCriteria contraints, CommunicationType communicationtype) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            communicationtype.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            communicationtype.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            communicationtype.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            communicationtypeRepository.save(communicationtype);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "commTypeName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "commTypeName", "Bu7ziM1L3sIXns364AF2sZZszK8wUXfudjIkbb5rhkkWUhk9FEfern3V7BJhWNQAw8hrGQvspXH8r6s9qBYg3lrYbGZ57T3q7ZVW1HA1g1F4z66xOkWeJRvWkN8uz5d0e"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "commTypeDescription", "X4XHd9UWD2YftKz5b6YmlMKo6uyJWB1TFGIAF10nu2Gdr7eDtlxdi2fFUvdKrREsPqBGUm7JiUg5ucrXLJgIPB877utZMmlMMcPr8NuM9C4IDDm7n27KOMv6z5SpkzglKCEHP798S8lClhS31C3iouMSjyUjXyiq1aHXGo3CT374ZfV1JBKzgY8qgUv13YwOIoqT2NH0uf3f9r6LKsxS8UzESzvWtE7uFhm889OiAKjpb4cxZ5wVsdP4vqbf8YtjB"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CommunicationType communicationtype = createCommunicationType(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = communicationtype.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(communicationtype, null);
                        validateCommunicationType(contraints, communicationtype);
                        failureCount++;
                        break;
                    case 2:
                        communicationtype.setCommTypeName(contraints.getNegativeValue().toString());
                        validateCommunicationType(contraints, communicationtype);
                        failureCount++;
                        break;
                    case 3:
                        communicationtype.setCommTypeDescription(contraints.getNegativeValue().toString());
                        validateCommunicationType(contraints, communicationtype);
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
