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
import com.app.server.repository.aaaboundedcontext.authentication.LoginRepository;
import com.app.shared.aaaboundedcontext.authentication.Login;
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
import com.app.shared.aaaboundedcontext.authentication.User;
import com.app.server.repository.aaaboundedcontext.authentication.UserRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessLevel;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessLevelRepository;
import com.app.shared.aaaboundedcontext.authentication.UserAccessDomain;
import com.app.server.repository.aaaboundedcontext.authentication.UserAccessDomainRepository;
import com.app.shared.aaaboundedcontext.authentication.PassRecovery;
import com.app.shared.aaaboundedcontext.authentication.Question;
import com.app.server.repository.aaaboundedcontext.authentication.QuestionRepository;
import com.app.shared.aaaboundedcontext.authentication.UserData;
import com.app.shared.organizationboundedcontext.contacts.CoreContacts;
import com.app.server.repository.organizationboundedcontext.contacts.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws SpartanPersistenceException, SpartanConstraintViolationException {
        User user = new User();
        user.setMultiFactorAuthEnabled(1);
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelHelp("o18EU04ps8grl6oITfM9kSZR8xAl1gLjNl1ehCfux3sbvMtHMb");
        useraccesslevel.setLevelDescription("YnsQlxBv7BVHwYKVZdMd0wREpBNMq4W97FrOXwG8SYYSMRvOIO");
        useraccesslevel.setLevelName("PhXvmGBo9Gf2f3qhDC6PL056RLkzMilAUYZocYH0TsANWiDlLg");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelIcon("RhrS1HjTHiRTz16lfbiqfSI1ypUnsJlGU6WdtHUkd3LZByxM3v");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("5PsuN3jYMWu4VQp1MIQxkUzTKvXjwENlStjQHweF9BaU6ToYsz");
        useraccessdomain.setDomainName("EFD8EQGGkC85cT8N1qnc0Miy7fzqkJbKpjwLWLmhnxakcnJZYp");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("8EQUFOUHAoVzssuroiKmVDrQ0xZbaOAX6xUu0Vvx5MW8WQp07P");
        useraccessdomain.setDomainIcon("5qck3eKLLVf0Ame6V2Cd8n7N2Zw2WpPTRkgdP3M0NtVF7E2ApH");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setMultiFactorAuthEnabled(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordExpiryDate(new java.sql.Timestamp(1459240908172l));
        user.setAllowMultipleLogin(1);
        user.setGenTempOneTimePassword(1);
        user.setPasswordAlgo("5AGsI7B9nZY2APsM6M6JQQBdC1aPHNQx8FwRQrk9VTvcMeuml6");
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1459240908315l));
        user.setChangePasswordNextLogin(1);
        user.setIsLocked(1);
        user.setSessionTimeout(2756);
        user.setIsDeleted(1);
        user.setUserAccessCode(36543);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("TGCptsd3dZYAGUWYGfl35glIon9XSpbgsShVJHSBWCNrmJHTBA");
        question.setQuestionDetails("THyD0r5uOG");
        question.setQuestion("JwXTeAfMujnX6nUcdv9wsNWpDzNv2mhmd2r3E2eRBZWX45rDHa");
        question.setLevelid(5);
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setAnswer("kZKHgxh7WOWRIzVUnlAEanKYY1qmMpVBt732Hw0bEXiQr5Vp0V");
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("OshIgbQwPXKOuVURauRG5yZh5xmy3gNs");
        userdata.setOneTimePassword("36V4l8zYRfF9K51NBSivC1LEQgHoxtB7");
        userdata.setUser(user);
        userdata.setLast5Passwords("14X9xooff3QMSigavXSM6Di7c42ScTHo0bKAtz0gxJuJzm3JyT");
        userdata.setOneTimePasswordExpiry(2);
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1459240909262l));
        userdata.setPassword("w9trINef9LePb6uZ90FbqHGaTzp6uAN9Oa3FJg7QW7IWyoDyjO");
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setMiddleName("WkVOEYVdXQts2wKkiMGxKDNCpHGCYuDo4arNQdKe5scuys5hqV");
        corecontacts.setNativeFirstName("aDcSO2rEML51f0329bCzHJri153ecJ7vyD6zvj3onWKwusju0V");
        Language language = new Language();
        language.setLanguageType("pV1YjwlxTRxUpr6sLQeoTSl7YzCUwuMw");
        language.setAlpha3("lqq");
        language.setAlpha4parentid(11);
        language.setLanguageDescription("ahVbRidekHDHviDzzVSTMpUWZCdUhrhVKk2z4NR6TM5dBI0Pgs");
        language.setLanguageIcon("p8jFgYS5jle5b0BZftbhlWKXQ0rncvSBf0ETh0xpUd7v4xM6C0");
        language.setAlpha4("i8aj");
        language.setLanguage("4tS2ZY0hnPElgTNlG4iJQrX2E3fqTpZeYtP8LySFSBEvX4b1pp");
        language.setAlpha2("Dk");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("1m9tMdeT6iXC2iNxC55Du8ADtLhZQwZAPo74mksvzuVl95Mpyo");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setUtcdifference(7);
        timezone.setGmtLabel("peboVpx5laTJH2rXUawHWhWB2mG02CJSNeUbgixRKWP8TIohit");
        timezone.setTimeZoneLabel("JJs2Q0E5pUmfvTMBbuC7BVjT07Ne69XZoK4ThEKY4pidmrFLAp");
        timezone.setCountry("sAdfZYjH2SYa5OTI1CrpKmnvQ9HueTTGTNwuUFP3jNH9Qhl3pB");
        timezone.setCities("UZTcItDxMIcmDDGalSvJTHt88wz1owtIcB3qcNdftM6viZCOwM");
        Title title = new Title();
        title.setTitles("iwZ9NsRXBjRucypFYIPSy2MIM7vUsJyfxwk3Tiifu7RYWvF3yi");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        corecontacts.setMiddleName("POG712PDmYbXdfibGfvsKgnicRA6oZ4cmprlt1MXmfQAw1MXNW");
        corecontacts.setNativeFirstName("g1WLfdt2j9v87OmICmjtxQCevdK4eBpjSKbGWacpUm2NwyU1Pd");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setEmailId("5M37B4PEwkzQAHKMg43HFcvM5aU5C4nnmE8btxHSt2KLdzyWyk");
        corecontacts.setNativeTitle("M4gwh6yej94xYassqHggEE98PQuneP8cTn29qiolHkU19MYKYS");
        corecontacts.setAge(63);
        corecontacts.setFirstName("8qeIrC0Rzm8FnUuLwhxAR5lD0SuFEF2gYuPCHVS9w4gDOIWccE");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1459240909975l));
        corecontacts.setLastName("hENZcGV6W0bAxWyj7QgW3zT1dwsupajWlwcEse7lDPVx24Jc6s");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1459240909975l));
        corecontacts.setPhoneNumber("tXVWVyhqfKXMBxTmfBut");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        corecontacts.setNativeMiddleName("q29gPDqCOcCSaTRpusRCycsMg7GGHzIRi2hEgUrPfSDp37ZxT5");
        corecontacts.setNativeLastName("5UXp1OwP0JUVIwzOnfi2JehxIk5YqeElv3dSISXZ4s3S8cOXYG");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        communicationdata.setCommData("");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("a9bUJ7xf47VgJdbXvKiVG6IY8PDP5thmTprRXAIM1LlrGUiZP2");
        communicationgroup.setCommGroupName("WKxPabDakUKIkvjEUZSmehxvxsJw1UaIPXiklgCNrCbUOFDLh1");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("aRf9tH7jJfGwnImIRPxjUQP03u58rp2Q2UACyCdradm3ra1AeE");
        communicationtype.setCommTypeDescription("5gckEymQ78svYqbYW5tulHatEJi9pffhjFJqWJ6lwjulmT45KX");
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
        address.setLatitude("airv716eYQD6Ww9AfWhFHJf9I0QQzDcEzCBlxAdUujWMUFNv1I");
        Country country = new Country();
        country.setIsoNumeric(72);
        country.setCountryCode1("ht0");
        country.setCountryName("EW8C1ujEjCcnducoaBIZ1py6wWzorqsX9wl2Ov4IvG6fEWOPjH");
        country.setCapital("64bfDEtDIdYoLn5GJlysUkUfNDqaLoQ7");
        country.setCapitalLongitude(10);
        country.setCountryFlag("u8ftNYAk9Rk9HirMlDwiVn7YSfiWDMOZP3V3xkNP7qvUBBBaXK");
        country.setCapitalLatitude(10);
        country.setCountryCode2("LVi");
        country.setCurrencyCode("ERr");
        country.setCurrencyName("vA2c17UIQLEmBurVlhhtVBvw41VBXyOi9aEvnbctKAFzaJasMe");
        country.setCurrencySymbol("PmgvYum2jS1ZSUMj1O95UmcRUucyvaDt");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("1Tvp5SlGCocA6rcgwEDamwTW3FPPBl8MmmX4UQn1qak0ySJIl6");
        addresstype.setAddressTypeIcon("lZWJHghYUX17rtkkivkkTRB0H18lArDRAJ0ZIVBb7PjIzdPDQt");
        addresstype.setAddressTypeDesc("g4TVqwJ7QDn8oqUKQ6dOQQgUJtO62q9S3Cqf1Jd316DRtWtBy6");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityDescription("DIbxniNEAEcjQvuaetznjnYTpFlKIAlNBXa1VJ1WmyXf9BhwIb");
        city.setCityLatitude(10);
        city.setCityCode(1);
        city.setCityFlag("5glWYiyBbOhrjjRAAjmE0BYbaVIGl8HKVJL2hibdKRE9yPQ58E");
        State state = new State();
        state.setStateDescription("eiolKsxx9R01A31hlsMSZ4myzf2z4P2qEjpcIT5afesD5h9Y4s");
        state.setStateCapitalLatitude(6);
        state.setStateDescription("CU1irbcUpdG59Nv4ah7xWitZjhkpv8S6nfsFvzvVRvGaBLU1uO");
        state.setStateCapitalLatitude(1);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCode(1);
        state.setStateCapital("PWW8yGJ0i9VXU25EoWQphuOuUqP6cL9ded25uDoqvDXb6aZuPe");
        state.setStateFlag("bJF27L1X8K6XwjV7WxsDuiAKtQjHxK7TYdULoD5rWxSzw8oO02");
        state.setStateCodeChar2("FViI7dy8kGsLzbVDOI3Z42nvjAaq73f2");
        state.setStateCapitalLongitude(8);
        state.setStateCodeChar3("rO470iVkTl3yZylpqOfc4IN6a78TsyZB");
        state.setStateName("508zLhm2r2uAD7MF5RDDj5pnoELIHZEPb1vv5sfhWoHxe5Yv7y");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityDescription("sZ6kjvqSNkfa7embvZHbBf2vJrqejgSWg7pxE5vFg9RD1ZtLnK");
        city.setCityLatitude(2);
        city.setCityCode(2);
        city.setCityFlag("OMQoblW5myVRRswbSTSPsHdkVnkHAiICZbH5kjWvOANjeMqRzc");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("mjmAa6uTuFdZwYL8MaicohWOgdHgN5Tp");
        city.setCityLongitude(7);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityName("R35i9UMbH0NuBoWXOT0ybSaCdTEbm0tooyuWWvfnBWpBsZzLCV");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setLatitude("j0qSppN9OupxPSDHf692L1qU7yHalKCv35U4UI25dvdClVtFAg");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLongitude("HBDwXssWy14wSFj9AIZ51I5aP0MZKnbEIggVgciqpavQ5P0I33");
        address.setAddress2("lCiqVd36yw88DQCy3eop3n7TtmtGDHm9j7dGYPmjWUgmAjDU4i");
        address.setZipcode("6ri4Nt");
        address.setAddressLabel("4VDbn88KrCJ");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("PkbRpzm4U8GWTfb4xT50KlcMEYO0GjuBRhKkJEjVZ7Goo0vYig");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        address.setAddress3("MxQuVvtRbxuDzMZlosffo8WzEUNdroYtXP6fzgSJvd2olfzGae");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        Login login = new Login();
        user.setUserId(null);
        login.setUser(user);
        login.setFailedLoginAttempts(4);
        login.setIsAuthenticated(true);
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setServerAuthImage("kdf6LCKwyrgznBjT0a5I2DsMvsrLvn02");
        login.setServerAuthText("XgcRV1anf1J5t4zv");
        login.setLoginId("D9Iiw9yBCEEh5iSal419BK1hK7U3cQC5BPKJ8DIqAlC8HodQtF");
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setVersionId(1);
            login.setFailedLoginAttempts(8);
            login.setServerAuthImage("8PhjCX8nyg20a1OJogsgmN7NepLfmktb");
            login.setServerAuthText("HHDTMeMFqPmuFvyt");
            login.setLoginId("JjY0wuOQfnNNASO2GF0bXKGJebuXz9nRFJeSnRXZwmutBaRdHA");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (com.athena.framework.server.exception.repository.SpartanPersistenceException e) {
            org.junit.Assert.fail(e.getMessage());
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws SpartanIncorrectDataException, SpartanConstraintViolationException, SpartanPersistenceException {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "xJrpC3H5EWXLkyqIbwDZ4v3JuaqIpP3LQBqJmuQG67yHVxP7B65Ilc9kHc5apyEpWeCCbfDxmQJjHZSYY1vqA1dkCLBbpSIpkaMkWyNF8Rc0JqxIk0D9X2L9Hk8O2BaR0tQzhZ9Dxd3dAWqWyzcAX4gUTj6jDZzNlG8gUEB6pTWiioMPSu37nA1OORq3Z8UC307kc3Ii8"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "NMEInabzs3cRqMwqg8KR7XIHzytL2RO4l"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "P98e9agH9Ln5CBxyK"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 17));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception, SpartanPersistenceException {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
