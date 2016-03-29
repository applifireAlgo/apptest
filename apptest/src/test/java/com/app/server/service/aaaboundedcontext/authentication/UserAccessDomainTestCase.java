package com.app.server.service.aaaboundedcontext.authentication;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("WVkriNzxgNBSihR4kgKc3HjQggeZa6Y0I3tzCEsHIgOLU0pBkE");
        useraccessdomain.setDomainName("GLiFkP3SqC6n7O6FMrJk3PoneS5zlrw1bhnZVnFEdNvwAoznNv");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("LfG3xcHGfoIVQrKnhvspdLdSfmxaCwtYPOxIyPWoif6JAGjhfN");
        useraccessdomain.setDomainIcon("LG4bHOSJe3tZ40LySNBAMytn79M6UPSIaEV4lHR9rs3KLv9XXP");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainDescription("EmCHFbLKHXVXZUNw8tAP1U03ut6p2zBHs13jC3yA4keFElx3dO");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainName("26sfvGgdg4gOBbEDUGA7czziMr7QTwxzs4smSakGNvH6Lca5OU");
            useraccessdomain.setUserAccessDomain(74343);
            useraccessdomain.setDomainHelp("dPz5KmomhVNmrIGHkHppN0UsivCBnAFkkrqxCSJGVkSFdU9knF");
            useraccessdomain.setDomainIcon("MwaXUDYBRv2z7c0kkfYl2zxMySvSGXHmrKjVAqA96FTQbcpcpY");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 112199));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "P807tZU28Bqeg5C1E7sLn70VwUtaHY4lFade0xuktHCb0Yxuvt2EJmNKeEE49SQVSP6PoTx9oNJCSsrkUnEUSERh6OSJfahg9RjkPHjsmu4MZkKAtC0NejMRkjqVr44jjat30RKpAf4Ppd5jVZ7JtR6TuaoTOwbaA10a4C47ciSr4XpWIyMIeGXGdgDWBeIb3e55LEZpx7j0wSQ9ts2EnvdEgPe5SSyAs9VUTT9SPwUuCp1VNImgMLoSQN09RTtzV"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "NzWJwSVIJ5Ffs36UxLsE4OvlDyYn6GlyL2N5zWKgTDcDtKs3lZqGoFWJtFLML761VlFEoNSTzSTVLAbsXzuYZPIN9IihiURMmd9w5lwz3tchjLMNmGEcP2d0TsvLmxHSb9EJFQFToQXhsTkuqrrljMWDGOBIGxX8jCcWOhQ4k62kFdCL6Stvmks0LXJSs24ldEoOCWL8IagK7yFQbubA8uwlpG9VprO1tUOzYkdjNLxQpOSmbjQRQTfLUf4G0YYWP"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "DC0snQRDBDw3ICRmydAXUFznBtBiEuOgnSGbNzmN4qgnLFWyDsX3zPVaU6aPggPj9ad8kvQna25s2tKeIiUpho8XJYs0kBQrcKFRwxqCvYxom2B9tgDBj9B2mvSnqvuuGlmm2eOXA11SoV3i82GrsmFskgCNihV1fYnOmAt7RQpIpeUSnbQRFJuos8uqfSpZjToynlslKGBy5e5HsVVjWcJ3BfzHoWAzE0f6g5Ax6XuOrs5uViRZsEREct4ZjiTTj"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
