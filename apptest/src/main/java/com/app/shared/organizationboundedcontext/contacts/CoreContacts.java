package com.app.shared.organizationboundedcontext.contacts;
import com.athena.annotation.Complexity;
import com.athena.annotation.SourceCodeAuthorClass;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface;
import com.spartan.shield.server.authentication.interfaces.CoreContactsInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.config.CacheIsolationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import com.athena.config.jsonHandler.CustomJsonTimestampSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomJsonTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import com.app.shared.organizationboundedcontext.location.Address;
import com.app.shared.organizationboundedcontext.location.Timezone;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_CoreContacts_T")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "", versionNumber = "1", comments = "CoreContacts", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "CoreContacts.findByTitleId", query = "select e from CoreContacts e where e.systemInfo.activeStatus=1 and e.titleId=:titleId"), @javax.persistence.NamedQuery(name = "CoreContacts.findByNativeLanguageCode", query = "select e from CoreContacts e where e.systemInfo.activeStatus=1 and e.nativeLanguageCode=:nativeLanguageCode"), @javax.persistence.NamedQuery(name = "CoreContacts.findByGenderId", query = "select e from CoreContacts e where e.systemInfo.activeStatus=1 and e.genderId=:genderId"), @javax.persistence.NamedQuery(name = "CoreContacts.findByTimeZoneId", query = "select e from CoreContacts e where e.systemInfo.activeStatus=1 and e.timezone.timeZoneId=:timeZoneId"), @javax.persistence.NamedQuery(name = "CoreContacts.findById", query = "select e from CoreContacts e where e.systemInfo.activeStatus=1 and e.contactId =:contactId") })
public class CoreContacts implements Serializable, CommonEntityInterface, CoreContactsInterface, Comparator<CoreContacts> {

    @Column(name = "firstName")
    @JsonProperty("firstName")
    @NotNull
    @Size(min = 0, max = 256)
    private String firstName;

    @Column(name = "middleName")
    @JsonProperty("middleName")
    @Size(min = 0, max = 256)
    private String middleName;

    @Column(name = "lastName")
    @JsonProperty("lastName")
    @NotNull
    @Size(min = 0, max = 256)
    private String lastName;

    @Column(name = "nativeTitle")
    @JsonProperty("nativeTitle")
    @Size(min = 0, max = 64)
    private String nativeTitle;

    @Column(name = "nativeFirstName")
    @JsonProperty("nativeFirstName")
    @Size(min = 0, max = 256)
    private String nativeFirstName;

    @Column(name = "nativeMiddleName")
    @JsonProperty("nativeMiddleName")
    @Size(min = 0, max = 256)
    private String nativeMiddleName;

    @Column(name = "nativeLastName")
    @JsonProperty("nativeLastName")
    @Size(min = 0, max = 256)
    private String nativeLastName;

    @Column(name = "dateofbirth")
    @JsonProperty("dateofbirth")
    @JsonSerialize(using = CustomJsonTimestampSerializer.class)
    @JsonDeserialize(using = CustomJsonTimestampDeserializer.class)
    private Timestamp dateofbirth;

    @Column(name = "age")
    @JsonProperty("age")
    @Min(0)
    @Max(125)
    private Integer age;

    @Column(name = "approximateDOB")
    @JsonProperty("approximateDOB")
    @JsonSerialize(using = CustomJsonTimestampSerializer.class)
    @JsonDeserialize(using = CustomJsonTimestampDeserializer.class)
    private Timestamp approximateDOB;

    @Column(name = "emailId")
    @JsonProperty("emailId")
    @NotNull
    @Size(min = 0, max = 256)
    private String emailId;

    @Column(name = "phoneNumber")
    @JsonProperty("phoneNumber")
    @NotNull
    @Size(min = 0, max = 20)
    private String phoneNumber;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "contactId")
    @JsonProperty("contactId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String contactId;

    @Column(name = "titleId")
    @JsonProperty("titleId")
    private String titleId;

    @Column(name = "nativeLanguageCode")
    @JsonProperty("nativeLanguageCode")
    private String nativeLanguageCode;

    @Column(name = "genderId")
    @JsonProperty("genderId")
    private String genderId;

    @JoinTable(name = "ast_CommunicationMap_B", joinColumns = { @javax.persistence.JoinColumn(name = "contactId", referencedColumnName = "contactId") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "commDataId", referencedColumnName = "commDataId") })
    @OneToMany(cascade = CascadeType.ALL)
    private List<CommunicationData> communicationData;

    @JoinTable(name = "ast_AddressMap_B", joinColumns = { @javax.persistence.JoinColumn(name = "contactId", referencedColumnName = "contactId") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "addressId", referencedColumnName = "addressId") })
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> address;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "timeZoneId", referencedColumnName = "timeZoneId")
    private Timezone timezone;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> entityValidator;

    @Version
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private int versionId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String _firstName) {
        if (_firstName != null) {
            this.firstName = _firstName;
        }
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String _middleName) {
        this.middleName = _middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String _lastName) {
        if (_lastName != null) {
            this.lastName = _lastName;
        }
    }

    public String getNativeTitle() {
        return nativeTitle;
    }

    public void setNativeTitle(String _nativeTitle) {
        this.nativeTitle = _nativeTitle;
    }

    public String getNativeFirstName() {
        return nativeFirstName;
    }

    public void setNativeFirstName(String _nativeFirstName) {
        this.nativeFirstName = _nativeFirstName;
    }

    public String getNativeMiddleName() {
        return nativeMiddleName;
    }

    public void setNativeMiddleName(String _nativeMiddleName) {
        this.nativeMiddleName = _nativeMiddleName;
    }

    public String getNativeLastName() {
        return nativeLastName;
    }

    public void setNativeLastName(String _nativeLastName) {
        this.nativeLastName = _nativeLastName;
    }

    public Timestamp getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Timestamp _dateofbirth) {
        this.dateofbirth = _dateofbirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer _age) {
        this.age = _age;
    }

    public Timestamp getApproximateDOB() {
        return approximateDOB;
    }

    public void setApproximateDOB(Timestamp _approximateDOB) {
        this.approximateDOB = _approximateDOB;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String _emailId) {
        if (_emailId != null) {
            this.emailId = _emailId;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String _phoneNumber) {
        if (_phoneNumber != null) {
            this.phoneNumber = _phoneNumber;
        }
    }

    public String getPrimaryKey() {
        return contactId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return contactId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String _contactId) {
        this.contactId = _contactId;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String _titleId) {
        this.titleId = _titleId;
    }

    public String getNativeLanguageCode() {
        return nativeLanguageCode;
    }

    public void setNativeLanguageCode(String _nativeLanguageCode) {
        this.nativeLanguageCode = _nativeLanguageCode;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String _genderId) {
        this.genderId = _genderId;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int _versionId) {
        this.versionId = _versionId;
    }

    public void setPrimaryDisplay(String _primaryDisplay) {
        this.primaryDisplay = _primaryDisplay;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo _systemInfo) {
        this.systemInfo = _systemInfo;
    }

    public CoreContacts addCommunicationData(CommunicationData _communicationData) {
        if (this.communicationData == null) {
            this.communicationData = new java.util.ArrayList<com.app.shared.organizationboundedcontext.contacts.CommunicationData>();
        }
        if (_communicationData != null) {
            this.communicationData.add(_communicationData);
        }
        return this;
    }

    public CoreContacts removeCommunicationData(CommunicationData _communicationData) {
        if (this.communicationData != null) {
            this.communicationData.remove(_communicationData);
        }
        return this;
    }

    public CoreContacts addAllCommunicationData(List<CommunicationData> _communicationData) {
        if (this.communicationData == null) {
            this.communicationData = new java.util.ArrayList<com.app.shared.organizationboundedcontext.contacts.CommunicationData>();
        }
        if (_communicationData != null) {
            this.communicationData.addAll(_communicationData);
        }
        return this;
    }

    @JsonIgnore
    public Integer sizeOfCommunicationData() {
        if (this.communicationData != null) {
            return this.communicationData.size();
        }
        return 0;
    }

    public List<CommunicationData> getCommunicationData() {
        return communicationData;
    }

    public void setCommunicationData(List<CommunicationData> _communicationData) {
        this.communicationData = _communicationData;
    }

    @JsonIgnore
    public List<CommunicationData> getDeletedCommunicationDataList() {
        List<CommunicationData> communicationdataToRemove = new java.util.ArrayList<CommunicationData>();
        for (CommunicationData _communicationdata : communicationData) {
            if (_communicationdata.isHardDelete()) {
                communicationdataToRemove.add(_communicationdata);
            }
        }
        communicationData.removeAll(communicationdataToRemove);
        return communicationdataToRemove;
    }

    public CoreContacts addAddress(Address _address) {
        if (this.address == null) {
            this.address = new java.util.ArrayList<com.app.shared.organizationboundedcontext.location.Address>();
        }
        if (_address != null) {
            this.address.add(_address);
        }
        return this;
    }

    public CoreContacts removeAddress(Address _address) {
        if (this.address != null) {
            this.address.remove(_address);
        }
        return this;
    }

    public CoreContacts addAllAddress(List<Address> _address) {
        if (this.address == null) {
            this.address = new java.util.ArrayList<com.app.shared.organizationboundedcontext.location.Address>();
        }
        if (_address != null) {
            this.address.addAll(_address);
        }
        return this;
    }

    @JsonIgnore
    public Integer sizeOfAddress() {
        if (this.address != null) {
            return this.address.size();
        }
        return 0;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> _address) {
        this.address = _address;
    }

    @JsonIgnore
    public List<Address> getDeletedAddressList() {
        List<Address> addressToRemove = new java.util.ArrayList<Address>();
        for (Address _address : address) {
            if (_address.isHardDelete()) {
                addressToRemove.add(_address);
            }
        }
        address.removeAll(addressToRemove);
        return addressToRemove;
    }

    public Timezone getTimezone() {
        return timezone;
    }

    public void setTimezone(Timezone _timezone) {
        this.timezone = _timezone;
    }

    @JsonIgnore
    public boolean isHardDelete() {
        if (this.systemInfo == null) {
            this.systemInfo = new SystemInfo();
        }
        if (this.systemInfo.getActiveStatus() == -1) {
            return true;
        } else {
            return false;
        }
    }

    @JsonIgnore
    @Override
    public boolean isValid() throws SpartanConstraintViolationException, SpartanIncorrectDataException {
        boolean isValid = false;
        if (this.entityValidator != null) {
            isValid = this.entityValidator.validateEntity(this);
            this.systemInfo.setEntityValidated(true);
        } else {
            throw new SpartanIncorrectDataException("Entity validator is not set");
        }
        return isValid;
    }

    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.entityValidator = _validateFactory;
        setValidatorcommunicationData(_validateFactory);
        setValidatoraddress(_validateFactory);
    }

    private void setValidatorcommunicationData(EntityValidatorHelper<Object> _validateFactory) {
        for (int i = 0; i < communicationData.size(); i++) {
            communicationData.get(i).setEntityValidator(_validateFactory);
        }
    }

    private void setValidatoraddress(EntityValidatorHelper<Object> _validateFactory) {
        for (int i = 0; i < address.size(); i++) {
            address.get(i).setEntityValidator(_validateFactory);
        }
    }

    @Override
    public void setEntityAudit(int customerId, String userId, RECORD_TYPE recordType) {
        System.out.println("Setting logged in user info for " + recordType);
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (recordType == RECORD_TYPE.ADD) {
            this.entityAudit.setCreatedBy(userId);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        setSystemInformation(recordType);
        if (this.communicationData == null) {
            this.communicationData = new java.util.ArrayList<CommunicationData>();
        }
        for (CommunicationData _communicationData : communicationData) {
            _communicationData.setEntityAudit(customerId, userId, recordType);
            _communicationData.setSystemInformation(recordType);
        }
        if (this.address == null) {
            this.address = new java.util.ArrayList<Address>();
        }
        for (Address _address : address) {
            _address.setEntityAudit(customerId, userId, recordType);
            _address.setSystemInformation(recordType);
        }
    }

    @Override
    public void setEntityAudit(int customerId, String userId) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (getPrimaryKey() == null) {
            this.entityAudit.setCreatedBy(userId);
            this.systemInfo.setActiveStatus(1);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        if (this.communicationData == null) {
            this.communicationData = new java.util.ArrayList<CommunicationData>();
        }
        for (CommunicationData _communicationData : communicationData) {
            _communicationData.setEntityAudit(customerId, userId);
        }
        if (this.address == null) {
            this.address = new java.util.ArrayList<Address>();
        }
        for (Address _address : address) {
            _address.setEntityAudit(customerId, userId);
        }
    }

    @JsonIgnore
    public String getLoggedInUserInfo() {
        String auditInfo = "";
        if (this.entityAudit != null) {
            auditInfo = entityAudit.toString();
        }
        return auditInfo;
    }

    @Override
    @JsonIgnore
    public void setSystemInformation(RECORD_TYPE recordType) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        if (recordType == RECORD_TYPE.DELETE) {
            this.systemInfo.setActiveStatus(-1);
        } else {
            this.systemInfo.setActiveStatus(1);
        }
    }

    @JsonIgnore
    public void setSystemInformation(Integer activeStatus) {
        this.systemInfo.setActiveStatus(activeStatus);
    }

    @JsonIgnore
    public String getSystemInformation() {
        String systemInfo = "";
        if (this.systemInfo != null) {
            systemInfo = systemInfo.toString();
        }
        return systemInfo;
    }

    @Override
    @JsonIgnore
    public void setSystemTxnCode(Integer transactionAccessCode) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        this.systemInfo.setTxnAccessCode(transactionAccessCode);
    }

    @Override
    public int compare(CoreContacts object1, CoreContacts object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append((firstName == null ? " " : firstName) + ",");
        sb.append((middleName == null ? " " : middleName) + ",");
        sb.append((lastName == null ? " " : lastName) + ",");
        sb.append((emailId == null ? " " : emailId) + ",");
        sb.append((phoneNumber == null ? " " : phoneNumber));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (contactId == null) {
            return super.hashCode();
        } else {
            return contactId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.organizationboundedcontext.contacts.CoreContacts other = (com.app.shared.organizationboundedcontext.contacts.CoreContacts) obj;
            if (contactId == null) {
                return false;
            } else if (!contactId.equals(other.contactId)) {
                return false;
            }
        } catch (java.lang.Exception ignore) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    public boolean isEntityValidated() {
        return this.systemInfo.isEntityValidated();
    }
}
