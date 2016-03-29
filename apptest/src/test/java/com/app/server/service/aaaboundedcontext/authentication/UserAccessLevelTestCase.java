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
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("FLICxINErh02Hzz6DU6xQhKSutOi7Hx5sKa8lnZOfSeOjN4i7E");
        useraccesslevel.setLevelDescription("O3i8bI5u1tZUbxRDHCxrSB7yeekWgTKULq08uqPV2b0HbcNCAd");
        useraccesslevel.setLevelName("nkcWuRnO1QOLHlU88BBXarfkKI90KDe9mLbY7xgF2DskXVjbT6");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("G5UnhoBfWpksCS2kJKeWYhfwT44NXf23RJUhYpe99izOtGrR4X");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelHelp("PusJ9tsv6uuUT6NqTsfETqEdpLUgixWPPVnUJ9LCgeevs0qhyz");
            useraccesslevel.setLevelDescription("vTPOk4cDDwoG913LGmvGH7Hoq4cbVttU1IUaLP7fFMBuiWOkCZ");
            useraccesslevel.setLevelName("9I6j0xI5fVgugm2rMqkJO2uyffEd80JOX9ZbXfsAB3D3fwzJQb");
            useraccesslevel.setUserAccessLevel(51422);
            useraccesslevel.setLevelIcon("rMs5P03q1PzTgY0Dau89gVfvFm6IXg7vVJQ7eRED4xuhPJ314j");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 124837));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "9UMZ2vy1VVdgD6WpGa8DjohYwYqmMXZqP8FGYARIBeEDgz03HcFLj2QsdTgpNSzUMKNOjwUpcnNZ9ufDqUfs7fyGSEvzLsoi8ve7iZSdxHrTkx7UPpjxXFrRBHjQNFixFplT5tzIpnKkTmmcGWRJdds3qHrpvYZs6zzOmBvBq6JkpBCh8qF2PPxpTXCBFddPJ4J5eH6tPfIgHlDJGtKtPjSJCIjqTCSPvd1UGF6AkU0M1bn4iMpkjGRTVCvAGVrB6"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "sQtoSHbnHZzx09tomMht2MvTQDcTzOs1wnSsuR6qpkXf6rqZpn9GpvfP1uMQMIL50hBv4eSFVXxKlWKo8HhKMOgW7WIhshIUcifh6GlAyMqd7u82a0rzlk8mvAgv0C0rv0IZUcniBoRacoZVVso4AZKrAW8D8rYQHW6cuvNmY1VKWel5eko0cu9QqxGJubTPZlNXmDNzBVy5zR1bGEaVlLQJM5UDH1cX4gZLr337deFliopYRPaDxbg9ZNx9XLF3h"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "m9gWVUe8b7QL1n6eA4pWuasUonGJcZYMR5TMJzojcBnlj6gYOsmg5gvH4VDWve3YwhY2MIDzNPrjOPVlaat7JB3vf2QfVXdsxzNouWB2mCK54Zs2T1OogDARqMT2cWlZvliWo8gVMTEpIlw14F2OTHyoBXEjaazKpjoZj8MYVI44mZP1t1RHwwlVefcKuDtVDPRLijqitv8HctO1reLPYaP5R1zy0frD62qvGBHzORgUvGwqOi9SLdDXwVfcfOUM4"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
