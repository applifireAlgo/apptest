package com.app.server.service.aaaboundedcontext.authentication;
import com.app.shared.aaaboundedcontext.authentication.ArtPasswordAlgorithm;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;



import com.athena.framework.server.bean.FindByBean;
import com.athena.framework.server.bean.ResponseBean;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import com.athena.framework.server.exception.repository.SpartanTransactionException;

public abstract class ArtPasswordAlgorithmService {

	 public HttpEntity<ResponseBean> findAll() throws Exception, SpartanPersistenceException {
	        return null;
	    }

	    public HttpEntity<ResponseBean> save(ArtPasswordAlgorithm entity) throws Exception, SpartanTransactionException, SpartanPersistenceException {
	        return null;
	    }

	    public HttpEntity<ResponseBean> save(List<ArtPasswordAlgorithm> entity, boolean isArray) throws Exception, SpartanTransactionException, SpartanPersistenceException {
	        return null;
	    }

	    public HttpEntity<ResponseBean> delete(String id) throws SpartanTransactionException, SpartanPersistenceException, Exception {
	        return null;
	    }

	    public HttpEntity<ResponseBean> update(ArtPasswordAlgorithm entity) throws SpartanTransactionException, SpartanPersistenceException, Exception {
	        return null;
	    }

	    public HttpEntity<ResponseBean> update(List<ArtPasswordAlgorithm> entity, boolean isArray) throws SpartanTransactionException, SpartanPersistenceException, Exception {
	        return null;
	    }

	    public HttpEntity<ResponseBean> search(Map<String, Object> fieldData) throws Exception, SpartanPersistenceException {
	        return null;
	    }

	    public HttpEntity<ResponseBean> findById(FindByBean findByBean) throws SpartanPersistenceException, Exception {
	        return null;
	    }
}
