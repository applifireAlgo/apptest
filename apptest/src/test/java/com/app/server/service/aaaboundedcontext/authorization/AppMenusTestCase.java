package com.app.server.service.aaaboundedcontext.authorization;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.aaaboundedcontext.authorization.AppMenusRepository;
import com.app.shared.aaaboundedcontext.authorization.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        AppMenus appmenus = new AppMenus();
        appmenus.setAppId("Ji9N3rDlAV3zlSMIp64Bvvkr8UL6fTC9nGlfHYlUyZzI2Hbncd");
        appmenus.setMenuLabel("Ova7JkgaKFWktuR09xfBgnNwYS1WdsA2NQZ1bNml0FX5mKOoBS");
        appmenus.setMenuIcon("PdMva5lIMUbOeCFQnsJsDGnWjrKtPjPMbAogL72ErkbgR4lzIf");
        appmenus.setAppType(1);
        appmenus.setMenuCommands("AeuRZdRi4G2Nxnf59P5QFMSFYyK2wh06qUYmZPXFkHeMTpFdfj");
        appmenus.setMenuHead(true);
        appmenus.setMenuDisplay(true);
        appmenus.setMenuAction("WEJeZAYWSZsC52xGsn1hZZyziAeHXkH2HkDKZ3rMmGhjDoEplq");
        appmenus.setAutoSave(true);
        appmenus.setRefObjectId("F7jhZxXXzNNXBSMHd39l8FXxRxYEwwOE61ukxNLdzXL5Oz3jdH");
        appmenus.setUiType("Hmk");
        appmenus.setMenuAccessRights(3);
        appmenus.setMenuTreeId("B5tJiO0DtmtLBOsLLDqJkxn3jPHPWgAuuYEoAi7wFfTY8e4Abj");
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setAppId("r6yvmmOKPyj43y5N3ArJrJfZwtEihEZuwgZBOoAGopgwj0R2lV");
            appmenus.setMenuLabel("91F9FRiQprueHnFQO5KHOKGUEy5nj8ZYW3WdQNKcebmUr91Kxg");
            appmenus.setVersionId(1);
            appmenus.setMenuIcon("w5vBm9p9vYQXhBPp05LgMkbGGJN5UUMkExLFnDxRW9Oog4f4st");
            appmenus.setAppType(1);
            appmenus.setMenuCommands("QP62gg4GXV9NA2AGXgFuP4tlDbAWmsHgi0aSFRBVjdn5HdKhRr");
            appmenus.setMenuAction("uatYcDYd1KWKcdYaCFhX8YqOFDVj362QtUPFCd9PtsZAyke3ZO");
            appmenus.setRefObjectId("v8OSbmQmBJ91RjmzrVozsa02QwekfsLJmE8cODGubwj5RLM5Re");
            appmenus.setUiType("vmL");
            appmenus.setMenuAccessRights(8);
            appmenus.setMenuTreeId("7JZB43TMIpu6xAWieBTPEoXqODqIEATVrCD0YbCsMqha3TLRpC");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "Frv4USB1drN2SDJR9uYrMwpVJL4UyARaNt988zueekKebIQKWdgo52yr5LUUDm66RFYK1I6x8xrq3qhVynwSKYyYveaMcyQ0HJhlqlj9ewDz1ofxHyYInnPfPBSWAqoVl65xfvaGOlwya91QB7aQA0GO6cfTOhe3hlUmdIcyQ7jLa1h4Dz3BbU7Z3dp8yooovT7rlPYTkmgHVTInjp5l6OfXcUHANJZOudzWh55nhSYuOYUTp1lELigeRZ9goBKRo"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "aynGksH1G2IuS62fGXHb55zUh0NdHCdbbBXuBm9umKu5IcTjJemVkUziAfssVgVkR81ITwb8Sqhj3bgzMAqb521YWKDA28b953xPtLtFfofAvt5aPgjNYX6sEVygVDbYx3B1FjlPHH7YtCCCj5HxIkKyviRsaPe3jW3cOVAdaAErsZ9vNj3blScyjB30EFsG4BSftl2b7MLCsljiGaFlRCCHrRQDOleXKcpPuGVfUutygdsVluAuA1CWgVLkXaUpC"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "H2sIgHo7eX3tEIwz9UfuoEJhPFeTGbYuuJXuYAePZEBENhext2A7enXAG5VzCwEwnpCCq11MUgG6DF6xKLZ7xzslFAfuy3xy2eOntmBgzIYdCGA1m1acFrIagdfo1FkAo90WDjOc9jJ1sj2eVRHw542Si2uKwboBjjkVXAs0o145ac7WwSAJNHgNzSh12fT806RZe9OATK6GItCSqb6HxbRNGSWtWPAHtxDfhWC00vp1jnJYD5boY6nbrsdDrG9ws"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "vor9AevgIfJjfGOoEWO2R2fxdxOqN2v4HVy9fqz14AijfYLXzCLuuZDkn144WEENe"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "B1gnNqLEsaPSPBdeZkFHnna3hkc6MI0X9U0oyviCzogkXkSdyduysCtXfRRxALYx1uwMzSmBqjXbRcDn7oxc2ZznaN4aeYZqPecI5fxIjQbieFCJTycFmJqEBhpLtDfPU1d2orZwjKJNsy9LTYGxk2gg4QJpbeyVbJ6myYEAFDohV8sYFZ027jNOOBa9k6QqdJsWGkM51WZ78j9rBZLUBFsG2cdjtV73MshYm2gMuyZG7juHyESI6AHQ6zIrPDZ0s"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "qk5D"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "o0jiLLXKoXBNELppiF9YoYQWOAVSygDag5XD6gIqEkWg2m3RAevDqY0EeWUyDQqOPfJzktSyiEIwIA3SQit8bdf9IgndCJQ6XTdStqHCYv6Lh64emWhxdCFqfXx2r47NMZe0QohGyWBCRr4i4oW3BmWg3PmtE48XViwkKjuLR56QjVkW8OPOgccp4zLpgSJg3nDzVYBJBq8pO35PPYw7ThNTC55s0Tuy7WY4wleFucZ38KLpnLGDZM4tzilD5cnty"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 3));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "8ohc9QnlzC16dSqwiCWYq3CUkUXMSYXdU3yDl6jxGwGBCKRlN1sH8U6uyKG1eFW9gFqQ0xmLAsiGhnWz08uFEOPIoO6lpwP7ga7c4yDJ95Ob7AWKfKhGpcRiQD0Tx2c5BV4pJZuOv5Lzx9sdoEz4hwFgEeLPF9d1GnOu0d7j01rvmA7bU4X6xTcGICvCdzxtryx8I2hmgQep1ZkN0nxIZnEHmgtVsnG1MawMF6CGRpJ4Bfop8KYAKo788KTSATIiZ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
