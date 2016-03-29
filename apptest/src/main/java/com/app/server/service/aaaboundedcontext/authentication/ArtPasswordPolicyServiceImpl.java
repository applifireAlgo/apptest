package com.app.server.service.aaaboundedcontext.authentication;
import com.app.shared.aaaboundedcontext.authentication.ArtPasswordPolicy;

import com.app.server.repository.aaaboundedcontext.authentication.ArtPasswordPolicyRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.athena.annotation.Complexity;
import com.athena.annotation.SourceCodeAuthorClass;
import com.athena.framework.server.bean.FindByBean;
import com.athena.framework.server.bean.ResponseBean;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import com.athena.framework.server.exception.repository.SpartanTransactionException;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.spartan.sprinkler.core.Sprinkler;

@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@SourceCodeAuthorClass(createdBy = "root", updatedBy = "", versionNumber = "1", comments = "Service for AppMenus Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/ArtPasswordPolicy")
public class ArtPasswordPolicyServiceImpl extends ArtPasswordPolicyService {

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Autowired
	private Sprinkler sprinkler;

	@Autowired
	private ArtPasswordPolicyRepository<ArtPasswordPolicy> artPasswordPolicyrepository;

	@RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
	@Override
	public HttpEntity<ResponseBean> findAll() throws SpartanPersistenceException, Exception {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		java.util.List<ArtPasswordPolicy> lstappmenus = artPasswordPolicyrepository.findAll();
		responseBean.add("success", true);
		responseBean.add("message", "Successfully retrived ");
		responseBean.add("data", lstappmenus);
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

	@RequestMapping(consumes = "application/json", method = RequestMethod.POST)
	@Override
	public HttpEntity<ResponseBean> save(@RequestBody ArtPasswordPolicy entity) throws SpartanPersistenceException, SpartanTransactionException, Exception {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.CREATED;
		try {
			artPasswordPolicyrepository.save(entity);
			responseBean.add("success", true);
			responseBean.add("message", "Successfully Created");
			responseBean.add("data", entity);
			httpStatus = org.springframework.http.HttpStatus.CREATED;
		} catch (org.springframework.transaction.TransactionException e) {
			throw new com.athena.framework.server.exception.repository.SpartanTransactionException("can not save", e.getRootCause());
		}
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

	@RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.POST)
	@Override
	public HttpEntity<ResponseBean> save(@RequestBody List<ArtPasswordPolicy> entity, @RequestHeader("isArray") boolean request) throws SpartanPersistenceException,
			SpartanTransactionException, Exception {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.CREATED;
		try {
			artPasswordPolicyrepository.save(entity);
			responseBean.add("success", true);
			responseBean.add("message", "Successfully Created");
			httpStatus = org.springframework.http.HttpStatus.CREATED;
		} catch (org.springframework.transaction.TransactionException e) {
			throw new com.athena.framework.server.exception.repository.SpartanTransactionException("can not save", e.getRootCause());
		}
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

	@RequestMapping(value = "/{cid}", consumes = "application/json", method = RequestMethod.DELETE)
	@Override
	public HttpEntity<ResponseBean> delete(@PathVariable("cid") String entity) throws SpartanPersistenceException, SpartanTransactionException, Exception {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		try {
			artPasswordPolicyrepository.delete(entity);
			httpStatus = org.springframework.http.HttpStatus.OK;
			responseBean.add("success", true);
			responseBean.add("message", "Successfully deleted ");
		} catch (org.springframework.transaction.TransactionException e) {
			throw new com.athena.framework.server.exception.repository.SpartanTransactionException("can not delete", e.getRootCause());
		}
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

	@RequestMapping(consumes = "application/json", method = RequestMethod.PUT)
	@Override
	public HttpEntity<ResponseBean> update(@RequestBody ArtPasswordPolicy entity) throws SpartanPersistenceException, SpartanTransactionException, Exception {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		try {
			artPasswordPolicyrepository.update(entity);
			responseBean.add("success", true);
			responseBean.add("message", "Successfully updated ");
			responseBean.add("data", entity._getPrimarykey().toString());
		} catch (org.springframework.transaction.TransactionException e) {
			throw new com.athena.framework.server.exception.repository.SpartanTransactionException("can not update", e.getRootCause());
		}
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

	@RequestMapping(consumes = "application/json", headers = { "isArray" }, method = RequestMethod.PUT)
	@Override
	public HttpEntity<ResponseBean> update(@RequestBody List<ArtPasswordPolicy> entity, @RequestHeader("isArray") boolean request) throws SpartanPersistenceException,
			SpartanTransactionException, Exception {
		ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		try {
			artPasswordPolicyrepository.update(entity);
			responseBean.add("success", true);
			responseBean.add("message", "Successfully updated entities");
			httpStatus = org.springframework.http.HttpStatus.OK;
		} catch (org.springframework.transaction.TransactionException e) {
			throw new com.athena.framework.server.exception.repository.SpartanTransactionException("can not update", e.getRootCause());
		}
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

	@RequestMapping(value = "/findById", method = RequestMethod.POST)
	@Override
	public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws SpartanPersistenceException, Exception {
		com.athena.framework.server.bean.ResponseBean responseBean = new ResponseBean();
		org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
		try {
			ArtPasswordPolicy lstappmenus = artPasswordPolicyrepository.findById((java.lang.String) findByBean.getFindKey());
			responseBean.add("success", true);
			responseBean.add("message", "Successfully retrived ");
			responseBean.add("data", lstappmenus);
		} catch (org.springframework.transaction.TransactionException e) {
			throw new com.athena.framework.server.exception.repository.SpartanTransactionException("can not find ID", e.getRootCause());
		}
		return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
	}

}
