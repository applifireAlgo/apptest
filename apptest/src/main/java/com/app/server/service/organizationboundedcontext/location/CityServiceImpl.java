package com.app.server.service.organizationboundedcontext.location;
import org.springframework.web.bind.annotation.RestController;
import com.athena.annotation.Complexity;
import com.athena.annotation.SourceCodeAuthorClass;
import com.app.server.repository.organizationboundedcontext.location.CityRepository;
import com.app.shared.organizationboundedcontext.location.City;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.spartan.sprinkler.core.Sprinkler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import com.athena.framework.server.bean.ResponseBean;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import java.lang.Override;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import com.athena.framework.server.exception.repository.SpartanTransactionException;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import com.athena.framework.server.bean.FindByBean;

@RestController
@SourceCodeAuthorClass(createdBy = "deepali.arvind@algorhythm.co.in", updatedBy = "", versionNumber = "1", comments = "Service for City Master table Entity", complexity = Complexity.LOW)
@RequestMapping("/City")
public class CityServiceImpl extends CityService {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private CityRepository<City> cityrepo;

    @Autowired
    private Sprinkler sprinkler;

    @RequestMapping(value = "/findAll", consumes = "application/json", method = RequestMethod.GET)
    @Override
    public HttpEntity<ResponseBean> findAll() throws SpartanPersistenceException, Exception {
        ResponseBean responseBean = new ResponseBean();
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        java.util.List<com.app.shared.organizationboundedcontext.location.City> lstcity = cityrepo.findAll();
        responseBean.add("success", true);
        responseBean.add("message", "Successfully retrived ");
        responseBean.add("data", lstcity);
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> save(@RequestBody City entity) throws SpartanPersistenceException, SpartanTransactionException, Exception {
        ResponseBean responseBean = new ResponseBean();
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.CREATED;
        try {
            cityrepo.save(entity);
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
    public HttpEntity<ResponseBean> save(@RequestBody List<City> entity, @RequestHeader("isArray") boolean request) throws SpartanPersistenceException, SpartanTransactionException, Exception {
        ResponseBean responseBean = new ResponseBean();
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.CREATED;
        try {
            cityrepo.save(entity);
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
            cityrepo.delete(entity);
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
    public HttpEntity<ResponseBean> update(@RequestBody City entity) throws SpartanPersistenceException, SpartanTransactionException, Exception {
        ResponseBean responseBean = new ResponseBean();
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        try {
            cityrepo.update(entity);
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
    public HttpEntity<ResponseBean> update(@RequestBody List<City> entity, @RequestHeader("isArray") boolean request) throws SpartanPersistenceException, SpartanTransactionException, Exception {
        ResponseBean responseBean = new ResponseBean();
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        try {
            cityrepo.update(entity);
            responseBean.add("success", true);
            responseBean.add("message", "Successfully updated entities");
            httpStatus = org.springframework.http.HttpStatus.OK;
        } catch (org.springframework.transaction.TransactionException e) {
            throw new com.athena.framework.server.exception.repository.SpartanTransactionException("can not update", e.getRootCause());
        }
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
    }

    @RequestMapping(value = "/findByCountryId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByCountryId(@RequestBody FindByBean findByBean) throws SpartanPersistenceException, Exception {
        com.athena.framework.server.bean.ResponseBean responseBean = new ResponseBean();
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        try {
            List<com.app.shared.organizationboundedcontext.location.City> lstcity = cityrepo.findByCountryId((java.lang.String) findByBean.getFindKey());
            responseBean.add("success", true);
            responseBean.add("message", "Successfully retrived ");
            responseBean.add("data", lstcity);
        } catch (org.springframework.transaction.TransactionException e) {
            throw new com.athena.framework.server.exception.repository.SpartanTransactionException("can not find ID", e.getRootCause());
        }
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
    }

    @RequestMapping(value = "/findByStateId", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findByStateId(@RequestBody FindByBean findByBean) throws SpartanPersistenceException, Exception {
        com.athena.framework.server.bean.ResponseBean responseBean = new ResponseBean();
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        try {
            List<com.app.shared.organizationboundedcontext.location.City> lstcity = cityrepo.findByStateId((java.lang.String) findByBean.getFindKey());
            responseBean.add("success", true);
            responseBean.add("message", "Successfully retrived ");
            responseBean.add("data", lstcity);
        } catch (org.springframework.transaction.TransactionException e) {
            throw new com.athena.framework.server.exception.repository.SpartanTransactionException("can not find ID", e.getRootCause());
        }
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @Override
    public HttpEntity<ResponseBean> findById(@RequestBody FindByBean findByBean) throws SpartanPersistenceException, Exception {
        com.athena.framework.server.bean.ResponseBean responseBean = new ResponseBean();
        org.springframework.http.HttpStatus httpStatus = org.springframework.http.HttpStatus.OK;
        try {
            com.app.shared.organizationboundedcontext.location.City lstcity = cityrepo.findById((java.lang.String) findByBean.getFindKey());
            responseBean.add("success", true);
            responseBean.add("message", "Successfully retrived ");
            responseBean.add("data", lstcity);
        } catch (org.springframework.transaction.TransactionException e) {
            throw new com.athena.framework.server.exception.repository.SpartanTransactionException("can not find ID", e.getRootCause());
        }
        return new org.springframework.http.ResponseEntity<ResponseBean>(responseBean, httpStatus);
    }
}
