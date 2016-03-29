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
import com.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
import com.app.shared.organizationboundedcontext.contacts.CoreContacts;
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
import com.app.shared.organizationboundedcontext.location.Language;
import com.app.server.repository.organizationboundedcontext.location.LanguageRepository;
import com.app.shared.organizationboundedcontext.contacts.Gender;
import com.app.server.repository.organizationboundedcontext.contacts.GenderRepository;
import com.app.shared.organizationboundedcontext.location.Timezone;
import com.app.server.repository.organizationboundedcontext.location.TimezoneRepository;
import com.app.shared.organizationboundedcontext.contacts.Title;
import com.app.server.repository.organizationboundedcontext.contacts.TitleRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationData;
import com.app.shared.organizationboundedcontext.contacts.CommunicationGroup;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationGroupRepository;
import com.app.shared.organizationboundedcontext.contacts.CommunicationType;
import com.app.server.repository.organizationboundedcontext.contacts.CommunicationTypeRepository;
import com.app.shared.organizationboundedcontext.location.Address;
import com.app.server.repository.organizationboundedcontext.location.AddressRepository;
import com.app.shared.organizationboundedcontext.location.Country;
import com.app.server.repository.organizationboundedcontext.location.CountryRepository;
import com.app.shared.organizationboundedcontext.location.AddressType;
import com.app.server.repository.organizationboundedcontext.location.AddressTypeRepository;
import com.app.shared.organizationboundedcontext.location.City;
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
import com.app.shared.organizationboundedcontext.location.State;
import com.app.server.repository.organizationboundedcontext.location.StateRepository;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        Language language = new Language();
        language.setLanguageType("JL1VEpgKEgZSanHKZDePRLjsp6yp0Lpk");
        language.setAlpha3("Djn");
        language.setAlpha4parentid(11);
        language.setLanguageDescription("ie7OPTIuXtyz7KFJEMxnUWgWhHmVSTbrCUsfaMEOCmOmuGDYXR");
        language.setLanguageIcon("VMCbFithjnKAe9MMxtOaJmm7aKLBnElc8r8xf95Ax4RlaUxB91");
        language.setAlpha4("DzmK");
        language.setLanguage("G3YmaPV9wpCUgg0GOIpGecXvWlY9vIfvP4I7MCUFiUxDoBbP6u");
        language.setAlpha2("HK");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("N8RePxZjx2Xw5FDOKMxPa2KaanV7kmtvRIQmR9WWGd6XG9uKyC");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(6);
        timezone.setGmtLabel("76BbGU01EkecMHisMSWn71tjFYCzwF7QUMd6uuBqXYsBiszr0a");
        timezone.setTimeZoneLabel("53Z0yxGbGoFJz0jLWu7THbmMR1BbL85Lbbr1iMIoWL141gQHGE");
        timezone.setCountry("VyETC0lxD6bRzlQm6W477LuuUBgXVflEGahmJ8gmlSPUAiav58");
        timezone.setCities("BuRaKSdofxyHurs1I3WlaP7UirrQ7UME07CEWI3uamE37MrU0A");
        Title title = new Title();
        title.setTitles("hGeqyfeDQoH48TmhVzUoNQcBxpdSsiBp8AfkU4aewuUMepdwTi");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setMiddleName("BBNKwTln95Bja95taZkXJjOOxjEGYYM26lbqra5XyAGTHhUw7c");
        corecontacts.setNativeFirstName("bydnBQfsbWzSngvNCu6GC2LBn0TgsOypyhaQIgFqQA51KEW8K2");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setEmailId("Wn6hKdWhRp3Udo7xYDO8t2CoCN3OHCfoPfwGNH2QzV6gyVTQE0");
        corecontacts.setNativeTitle("793nqioj4KeBHhBzn9RFqhReKWhwSylqJgn3DyLC7Q7SAaPNWG");
        corecontacts.setAge(104);
        corecontacts.setFirstName("67NdrVoGmzSNz2t9DNP52kXDXCNeKzmOJ37aXQb9DFpc2lNNLX");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1459240894060l));
        corecontacts.setLastName("HiPH8PzOQukTADJdHBBQshvZhYwje8Qbnf8dD3J9M4Lzl4wTvl");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1459240894060l));
        corecontacts.setPhoneNumber("u4aqKmafJuTCawQLm8PV");
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        corecontacts.setNativeMiddleName("Xb5NDl0wkd76AVoOTJSNisPJGibukIXjoYJs9FsEMAEnNL5ZJd");
        corecontacts.setNativeLastName("m8Qdp8aNokScaNgcazHpK1mv13flTzc9eUwE9DeJC4vWvQL7af");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("IH0ZkmaA71pCe87QvSkWcEfyjz7i3Q9uyAxFHiCFEbuDE0nVsk");
        communicationgroup.setCommGroupName("V4WY8ZmB725Ea1ExEkEyjt3aI5hAuMIJJQbbUPwe2Dv3yTJ9lH");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("ZoMV3E28kXM9fbuwY2QPgmnXM3dgty5jad5NZ26MViDOezMWHw");
        communicationtype.setCommTypeDescription("iodiMg8BgBzIHFDb54zwKa2eqMdKjnuEMsXJR74KQbVY7X2w1D");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommData("");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLatitude("7uFpxtqVCY3ztSBYesL5hxFXrYD3PWdqCFrKhI2L4Wp6UTp3zx");
        Country country = new Country();
        country.setIsoNumeric(14);
        country.setCountryCode1("dyh");
        country.setCountryName("BVBZCkCm33QhJglHpnvjPsncVCDLDGvML9yGbK2uw0mSRdnhAX");
        country.setCapital("AKt9RNjHsChbfwD1FEyagaDtpo57bXsB");
        country.setCapitalLongitude(10);
        country.setCountryFlag("WAuKvJtr1e5jtKFydfYah2Xb7l66uEoT6VUyQcINiE8kMBSBEJ");
        country.setCapitalLatitude(6);
        country.setCountryCode2("yPI");
        country.setCurrencyCode("T2c");
        country.setCurrencyName("WAFEE6IQ1DZ7gTKOUNkNP2pO3MUAE1oipOEbwMVX9zWKgv43mb");
        country.setCurrencySymbol("PWFfU3wQQf2BwCY7VUE9GLmXwjsENxcw");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("gr80PFEA1JhJ8Wv26V1kHowzd4yzad0kjhoGNwOqhn6ZGxgq1P");
        addresstype.setAddressTypeIcon("Adyz5zOshyULA54qt7KFi148UXJRj0ZHbAEG8gZVcAdGCz8j71");
        addresstype.setAddressTypeDesc("ivnJ7DmLMsVMr1Vb1DkJZIxyCG384g7ebea5K2DeQV0sYMmxJF");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityDescription("bxS5bczekdDgf1RwPiYELXNLNvcwoIgZVsETxaI03LmyQ2oJvf");
        city.setCityLatitude(7);
        city.setCityCode(3);
        city.setCityFlag("DudtPbi6Gp8C2ZTpPvLHxEiokdcB6qbqGKPGcEd00JMEXgiBjl");
        State state = new State();
        state.setStateDescription("vVIhnUnHJrKAql5dcxUT2twuX007MANrgF1jhsetGJzWw4wXcS");
        state.setStateCapitalLatitude(3);
        state.setStateDescription("TrtD4LfZ2HXs2496KPJFXDM3vfumwx9eFWcgsS1lJwyHD1kdly");
        state.setStateCapitalLatitude(8);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(1);
        state.setStateCapital("x3YNKEfjzDEinAX3AjOCPoiSxJPFI5L4wz7uAV4YYjmsAyoAO6");
        state.setStateFlag("O6qPPa61Wc4NQ2IMI0w7tdP1ofsDgH7ICT7Vh4zdR5gVlftHAw");
        state.setStateCodeChar2("7s34gbXq2HbqxN4c8eEGBrBVbMNaeaI1");
        state.setStateCapitalLongitude(8);
        state.setStateCodeChar3("yN7XwJrVzdkRUd85QNOVsEix36vfthaQ");
        state.setStateName("ZNTavSnToWXLn8C5e9uWIZnm9mEYF1a1ksjnCsJwoKiMiWojGY");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityDescription("FjwdATS93drsLo9yxsa6mLgXdMP6vuPlugnQGP5BORGFXQVR2J");
        city.setCityLatitude(7);
        city.setCityCode(3);
        city.setCityFlag("1SDCXqSSFfIJs4gUj7Jcete6Ff6ALd0SJtsYV8Is5iplXUtUYP");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("dLSiPI3pT6PGDjWseHdFrafJCkJkQeuh");
        city.setCityLongitude(7);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("MjaJmGIV5mKMWkiUYAelz2xn3VxheDDHCYOegsH8nBTXbgzu4A");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setLatitude("mG3dWTwJvmMVuM8WvjGy96WD1xp8f68hxyu0ecVnkuLS98N5bj");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("LjCwUTWl0P7gbh1laYOBHATEYjkLdd6AQsn3SNf40W36cw5lQ5");
        address.setAddress2("G0tTcwlickz2PSNYu8j8iYkCliUxtFssslFAIt3cbeC5UfRffV");
        address.setZipcode("RJauVl");
        address.setAddressLabel("mIP3BHBb53p");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("aAB446Yty7gOGbe0WvpW6g6MdfnWzmRPSFFhZ8J1SFv4ZANr0Q");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        address.setAddress3("VEEvpDOkJ1269ACj7HlpYxsiRkVSr6sphodhNxaqZTd273wWXZ");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setMiddleName("55ApzH3HFH8JwnATpCuU5EAnxqDn1QiWReJLIm1cT2BkUe4VL6");
            corecontacts.setNativeFirstName("5cZvAkpiMb15kGLUXHupEUHs2PKDa92h2gE4qbd4rWMcmo93Ph");
            corecontacts.setEmailId("roKH0aK4getgp6kaQAgzHcNj5BAnpzwk1q749DvwGhXaY3TM6B");
            corecontacts.setNativeTitle("rLGaJ49aX0RzGOSSpKK5sUbrlynAuQ61x5H0TuyUD8MrDj6hw1");
            corecontacts.setAge(55);
            corecontacts.setFirstName("8eTgDoJFBiFsWEUs6ELVGt30Uh6709ejJFNe4wXgRkvgpGyDVI");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1459240896224l));
            corecontacts.setVersionId(1);
            corecontacts.setLastName("qHM54FgMR2GlvZlpDm1zTY6kKEhPNTpeAYowCJbDshus7RS98n");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1459240896439l));
            corecontacts.setPhoneNumber("VxW6obBJKn73PbjjGhq5");
            corecontacts.setNativeMiddleName("pu4KxGaAkMfn010pNMpbsn0pnDkWpspohYhFADItTgl7AeU1Yw");
            corecontacts.setNativeLastName("JiHP6Gk2vBi7nE9wSizPn47gttGriP6uKgxVa7p1F8FbCmX1BT");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
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
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
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
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "SG3AunahcgJo38svBgyLGZGF6wnO7iDzsngjQ310KfZKDIngHl7PxSsl4CVKdjQQjm91ONdRZUTKZbA1JRBZr3Yz7a4HtebaLw2XSwLow1ruAERCoSzAhbsziQsiyIvBl7gOJ8ZKh09ImqOlwLDFnRiwcQTvdQFHGHh68aWJKTzjI4yibZIPSOhzqbYGL5s5Exo61jy00rR0Xqtc0aJBIKCgDHTxc0nrfDbRQhq9PQ6rvm6tIVqWAbf02kBLkLl7s"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "1ggkLAXIsoynbvnevSa9gYAO9oLvNZ33RfYomB5T2MPHDFeDrMs77Y0GqZh2lCNtxaoPGhNocjDx8KoZDcp1wLilBKFGfOv3i5CtCgN4S791WXyGHgLFrha1XVzsNm7mlITwtjeILkZLSn4oiEyT5F0nskGpkm8BY189zBvyZcZptzQVaUBSTJPFktX3Sbk55yqfz3mK8bCuw4qmzx2dQaTJVKeuhPv1SJC6UUGnZh0Cd1YjPAAKSqG46FNcvZEs7"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "uAA505ZKmVG3dQi0by08emlz4LGqlvJJS3fUD0H3ADhFAmMgt5yhsH4uLYvoQjwZnz36svj95H13cXiVWQKL1qXcYMuz0Uou8yeccdXKf1Fy6Jaez7Dv0Jf7xfN9aMdk1UNQOtMaqu67k68M0B7QGkGRAWOaB51vblmpo2ittLoemdk7azymGjqnCZZvYUnRcIw2nTEcq7dqr0zTxdyUb8ul3LofcDAtQJaFH6lKcQ8Uxx0jRw3lSOzTkXrQOtyCv"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "OA1PcnYvjwhNSp22SR9Qndgw8hhbChnlzvFVhq4iE7P01Bo87nn0lAQjX0kdWoepF"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "uBcvuhlrfvDIYTpk6SFSDBeaVeJYjTYYPqxe80eCxUgB2BNITk0MODOc3a5KscRNs1lmgJf3WgtdyGI5kKlyRPR8wRsOsZv7O9FM9meLvoZcORadbPPeUaHpfNLKShItmyc2wfxCkOtu1kbGyxEZ1xsE6mwIUnMa0FZGue8F3HS37LtrcTVkrQmHJQG4wvnaStlecUH3NED3o10r0UEMhih5CmH8l1J8TOVsZXElZu4NhS6gh9mqmOfJFaj53l22W"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "dghRiReGCJjQMIMcBQQJqUcAUdNW9v4q3pfQFaUwM1rcEAQ3crVgA07G1iwnUQ0n4YmvAFMh41HCqzIWiiXbqcVwtzbtsEtZnmwt4a5ib4HawmlH0mUzUMSvAVDGRELVJjPixqgDlEOQzLupRcpqDZfb0tIOoIeuwWtsRT8LwgaKPD7xSVauG1r0CKvo7eYQ7Lpxi61T05XxwFlqMyG05Vt0TBgSrwN1Zj5S58qJEcnIRRjedg2ckveAM97dopULc"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "z12IJoa97ADkPoBDiIu3zodtv5hsI1i3i1bHqgQMun2xbb3DckpMLiwxciS3UJmHq3dlGrUHKUautduNBDug5xeFWL1RGXIYT6TMJPYxIpL0U2GtZ7EJ28tFSlJ7OUz16QwOHpxbPSnEzzArBMehSI5S2fYBK7UfAjgOAEWPO13LQ7eSL2bptN2pwYwkUY7nP35AmNL0UmkjvVxd2d4s9mZQ9U0uZmylrkrSNJ33gAwzUoaDozwRDPXAdSEzsZlBh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 215));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "8xR5uuuqVZGQi5u9Vku7G74imHO1HvDQ2WfDXMmMhkKpigZCZnYvLfIfijlT8ZOJS4mA9QS7elhvHcJEvTeRq1rFUC8KymqoUPoGPfYb8h5D2G06D1MTAz4fPt1SWOVOv0C2kqpb8r0VaDRjjteSxPX4LkbHOTyL0PCkF5WxY4tySp5E6lsUslDdB0oIMHhARcS5xAjsq9DqMyUSGmiKOgnBqGW7acIZ3cZBqkJGO12Bl91zPSJFZe3VPqw5r4JDe"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "0pvp0RiEbhNodVPBE3vqI"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
