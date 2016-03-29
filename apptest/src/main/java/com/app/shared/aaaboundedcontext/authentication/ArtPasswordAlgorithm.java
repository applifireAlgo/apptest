package com.app.shared.aaaboundedcontext.authentication;
import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.config.CacheIsolationType;
import com.athena.annotation.Complexity;
import com.athena.annotation.SourceCodeAuthorClass;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "art_password_algorithm")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "root", updatedBy = "", versionNumber = "1", comments = "art_password_algorithm", complexity = Complexity.LOW)
public class ArtPasswordAlgorithm implements Serializable, Comparator<ArtPasswordAlgorithm> {

	@Column(name = "algorithm")
	@JsonProperty("algorithm")
	@NotNull
	@Min(0)
	@Max(11)
	private Integer algorithm;

	@Column(name = "algoName")
	@JsonProperty("algoName")
	@NotNull
	@Size(min = 0, max = 256)
	private String algoName;

	@Column(name = "algoDescription")
	@JsonProperty("algoDescription")
	@Size(min = 0, max = 256)
	private String algoDescription;


	@Transient
	private String primaryKey;

	@Id
	@Column(name = "algoId")
	@JsonProperty("algoId")
	@GeneratedValue(generator = "UUIDGenerator")
	@Size(min = 0, max = 64)
	private String algoId;

	@Transient
	@JsonIgnore
	private EntityValidatorHelper<Object> entityValidator;

	@Version
	@Column(name = "version_id")
	@JsonProperty("versionId")
	private int versionId;


	@Transient
	private String primaryDisplay;

	public Integer getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Integer _algorithm) {
		if (_algorithm != null) {
			this.algorithm = _algorithm;
		}
	}

	public String getAlgoName() {
		return algoName;
	}

	public void setAlgoName(String _algoName) {
		if (_algoName != null) {
			this.algoName = _algoName;
		}
	}

	public String getAlgoDescription() {
		return algoDescription;
	}

	public void setAlgoDescription(String _algoDescription) {
		this.algoDescription = _algoDescription;
	}


	public String getPrimaryKey() {
		return algoId;
	}

	public void setPrimaryKey(String _primaryKey) {
		this.primaryKey = _primaryKey;
	}

	public String _getPrimarykey() {
		return algoId;
	}

	public String getAlgoId() {
		return algoId;
	}

	public void setAlgoId(String _algoId) {
		this.algoId = _algoId;
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

	

	public String getPrimaryDisplay() {
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append((algoName == null ? " " : algoName));
		return sb.toString();
	}

	public String toString() {
		return getPrimaryDisplay();
	}

	public int hashCode() {
		if (algoId == null) {
			return super.hashCode();
		} else {
			return algoId.hashCode();
		}
	}

	public boolean equals(Object obj) {
		try {
			ArtPasswordAlgorithm other = (ArtPasswordAlgorithm) obj;
			if (algoId == null) {
				return false;
			} else if (!algoId.equals(other.algoId)) {
				return false;
			}
		} catch (java.lang.Exception ignore) {
			return false;
		}
		return true;
	}

	@Override
	public int compare(ArtPasswordAlgorithm arg0, ArtPasswordAlgorithm arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

}
