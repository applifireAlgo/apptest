package com.app.server.repository.aspect;
import java.util.HashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.athena.config.server.helper.ResourceFactoryManagerHelper;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.spartan.sprinkler.core.RuntimeLogUserInfo;
import com.spartan.sprinkler.core.Sprinkler;

@Aspect
@Component
public class RepositoryBaseAspect extends RepositoryAspect{

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Autowired
	private EntityValidatorHelper<Object> entityValidator;

	@Autowired
	private ResourceFactoryManagerHelper emfResource;

	@Autowired
	private Sprinkler sprinkler;

	public void setCustomerIdInEntityManager() {
		if (runtimeLogInfoHelper.getCustomerId() != "" && runtimeLogInfoHelper.getCustomerId() != null) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("tenant.id", runtimeLogInfoHelper.getCustomerId());
			emfResource.setResourceAttributes(map);
			runtimeLogInfoHelper.setMultitenantFields(map);
		}
	}
	
	@Around(value = "applifireRepositoryOperation()")
    @Order(2)
    public Object aroundApplifire(ProceedingJoinPoint joinPoint) throws Throwable {
        setCustomerIdInEntityManager();
        return handleRepositoryCall(joinPoint, runtimeLogInfoHelper.getRuntimeLogInfo());
    }

    @Around(value = "applifireJPQLToSQLConverter()")
    @Order(1)
    public Object aroundApplifireJPQLToSQLConverter(ProceedingJoinPoint joinPoint) throws Throwable {
        RuntimeLogUserInfo localRuntimeLogInfo = createLocalRuntimeLogInfo();
        return handleRepositoryCall(joinPoint, localRuntimeLogInfo);
    }

	@Pointcut("(execution(* com.athena..repository..*(..)) || execution(* com.spartan..repository..*(..))) && !within(com.athena.server.repository.ArtQueryRepository+)")
	protected void applifireRepositoryOperation() {
	}

	@Pointcut("execution(* com.athena.server.repository.ArtQueryRepository.findSqlFromJPQL())")
	protected void applifireJPQLToSQLConverter() {

	}

}
