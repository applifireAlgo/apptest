package com.app.server.service.organizationboundedcontext.location;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.app.shared.organizationboundedcontext.location.State;
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
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Country country = new Country();
        country.setIsoNumeric(60);
        country.setCountryCode1("qEC");
        country.setCountryName("0Mf3cQ7gSXfOmAO4fDfGNIWKyjSM1JOR29Kln9oFSEdvSSeoLW");
        country.setCapital("3qiZIB7FIyYspR1n5Jw3AxKPKLRdIIdR");
        country.setCapitalLongitude(9);
        country.setCountryFlag("K56pVD7SIOSgUCqnEBiOPYrH9rMtKO78zBjJn80Qy86q3SzlqE");
        country.setCapitalLatitude(3);
        country.setCountryCode2("sH0");
        country.setCurrencyCode("8x6");
        country.setCurrencyName("pSel7WIgmtB2niE4LOiJyeYm8E8qd9hhCPWc71CcK2V1X0F6y8");
        country.setCurrencySymbol("kePYxRobCCGClUPDung8rglkrLKwYup0");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateDescription("pTBI13lkzkX1kioPL8ipvxDLSSdt5QaZhlS8Lp7t12Bn9vrmgy");
        state.setStateCapitalLatitude(10);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCode(2);
        state.setStateCapital("r2Wr0Sp5AFtIvijUKYlrOKpLjHDMqE8JL876ezVWU3EtVdPEX2");
        state.setStateFlag("bIE2KURVP82N8YmDL42ORUulMFLfipZlUobiiKqhs2GprNmFmU");
        state.setStateCodeChar2("K31Kwewjgo8uGATBImuMp0pQSJdy51Yh");
        state.setStateCapitalLongitude(7);
        state.setStateCodeChar3("s73flBaOzKsY2q0vo8w2JIYr8XjljPz2");
        state.setStateName("whab3fzSQrDMPfapQF9MoYLf3DnaSqJY0veQoX114JW9rXX26i");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateDescription("G5X9UsgrCDVMROTK0cjSLjUksK2RasyBTUPpEiOsOTDIVK39Gn");
            state.setStateCapitalLatitude(6);
            state.setStateCode(2);
            state.setStateCapital("d0Nl9iYsWXQ9qj1duDDKxAGXJl39Y0VnbxgiTYpsHtZiJImYXR");
            state.setVersionId(1);
            state.setStateFlag("YjVO5hy6lD9YWf3kZiaDv6xWhCX1TurcZRoYrfYeTgO9hkapMV");
            state.setStateCodeChar2("SJAjKGTx4W2swJ2z0C7EvOHxM1FmZXVk");
            state.setStateCapitalLongitude(3);
            state.setStateCodeChar3("2DCAmi25FmFQbt4FaB87dhu3l577oyfJ");
            state.setStateName("facHIqWzQFjl91mh5bXbsIQyJTSse7emYEOK3Lq1xVC0mhMSgp");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "HInw1gZQbAUC60XuzgNz01wA4R9hlAnLHQziHoT8WKT8BiZk625VpkmIKdPcAqYgAYGhEWJ38EXC1E3eyLRmRySrfImyzJrSmVTNPy9pdcw3CKX4kPUFftTF1uPJPpxDk7kd8DzuxZrgfDF6zIjbm35EMDdEYGDVpse57g6jQciiCWBeJ1XzoCNIgRm5PGOeNR0IKzJeyXJs3NruRLoK3Q2TSGR7EIIwXHHIpGJI2Vqh0msK6sx5gaGCJhbAZQpNX"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "nsmJ2zp2omEjmdUM0pD5utBzb5Ox1Lndv"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "1tSTqRDMQCd1MMwcUfGysjg2uw8MdkY1O"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "Wn7J5A9VG27Gyu3zqXQpeTWck8qxVzKMBZDDzozRGcPa6KB9mcbIDOw3uPQr3ydAa9M8mC4bvJdBJPtHGJwdeX9sJPVy4OYXKJeuJOj8lXhrLIMhgofuAceC2w3evw78yCLwmR5wtofEVfiTd2dG6WletgCXafipjs6yCIlGLkjIl0r0X9qNWfmzU4t0ikVNU6TEWzrBMIFgOLQMnrORKGqHu4VTfsSDt3tDIhVy1U6Qe1i58fWxa4YiX2weQrCRM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "hQ5Im1BvWqYAh9DLU5aMEM1Rl4mbs85O5eIE9SgG3B9rZW79ex6movOTkLPWx0Yd0A7kSuRoBU7pdiSlW00c9v5JL6dD4sGKqZGPH3DQMPmyCLrKct9Cv9UKFsWOTUVVn"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "tXojb2L8MjEkho31UWeAgoiwLJZa0PfASN1gvQBjbenZjCFd9bh1bSCLHQsESM0qNal7Peafyhr2OKFLfmTF1ryUFbLNrODPG090LfVwvVAds42UnB7YuzLQNjoFsrtue"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 16));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 18));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
