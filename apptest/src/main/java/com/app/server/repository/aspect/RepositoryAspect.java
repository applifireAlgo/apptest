package com.app.server.repository.aspect;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.athena.config.server.helper.ResourceFactoryManagerHelper;
import com.athena.framework.server.exception.biz.SpartanConstraintViolationException;
import com.athena.framework.server.exception.biz.SpartanIncorrectDataException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import com.athena.framework.server.helper.EntityValidatorHelper;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import com.athena.framework.shared.entity.web.entityInterface.CommonEntityInterface;
import com.spartan.sprinkler.core.RuntimeLogInfoImpl;
import com.spartan.sprinkler.core.RuntimeLogUserInfo;
import com.spartan.sprinkler.core.Sprinkler;

@Aspect
@Component
public abstract class RepositoryAspect {

	@Autowired
	private RuntimeLogInfoHelper runtimeLogInfoHelper;

	@Autowired
	private EntityValidatorHelper<Object> entityValidator;
	
	  @Autowired
	    private ResourceFactoryManagerHelper emfResource;
	  
	  @Autowired
		private Sprinkler sprinkler;

	public Object aroundfindAll(ProceedingJoinPoint joinPoint) throws Throwable {
		return joinPoint;
	}

	protected void repositoryLogic(ProceedingJoinPoint joinPoint) throws Throwable
	{
		setCustomerIdInEntityManager();
		Object object = null;
		if (joinPoint.getArgs().length > 0) {
			Object methodInputParam = joinPoint.getArgs()[0];
			if (methodInputParam != null && methodInputParam instanceof CommonEntityInterface) {
				CommonEntityInterface entity = (CommonEntityInterface) methodInputParam;
				preSaveUpdateOperation(entity);
			} else if (methodInputParam != null && methodInputParam instanceof List) {
				List listOfEntities = (List) methodInputParam;
				if (listOfEntities.size() > 0) {
					/*
					 * Checking 0th element type. So no need to check type for
					 * each element in the loop.
					 */
					if (listOfEntities.get(0) instanceof CommonEntityInterface) {
						for (Object object1 : listOfEntities) {
							CommonEntityInterface entity = (CommonEntityInterface) object1;
							preSaveUpdateOperation(entity);
						}
					}
				}
			}
		}
	}
	

	protected Object handleRepositoryCall(ProceedingJoinPoint joinPoint, RuntimeLogUserInfo runtimeLogInfoHelper) throws Throwable {
		try {
			if (joinPoint.getArgs().length > 0) {
				if (joinPoint.getArgs()[0] != null) {
					sprinkler.logger.log(runtimeLogInfoHelper, 2000, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
							joinPoint.getArgs()[0]);
					Object returnObject = joinPoint.proceed();
					sprinkler.logger.log(runtimeLogInfoHelper, 2001, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
					return returnObject;
				}
			} else {
				sprinkler.logger.log(runtimeLogInfoHelper, 2000, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), "none");
				Object returnObject = joinPoint.proceed();
				sprinkler.logger.log(runtimeLogInfoHelper, 2001, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
				return returnObject;
			}
			return null;
		} catch (SpartanPersistenceException e) {
			e.printStackTrace();
			sprinkler.logger.log(runtimeLogInfoHelper, e.getExceptionId(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e,
					"fetching", "Entity");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			sprinkler.logger
					.log(runtimeLogInfoHelper, 2007, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e, "fetching", "Entity");
			throw e;
		}
	}

	protected void preSaveUpdateOperation(CommonEntityInterface entity) throws SpartanConstraintViolationException, SpartanIncorrectDataException {
		entity.setEntityAudit(1, runtimeLogInfoHelper.getRuntimeLogInfo().getUserId());
		/* validates the entity */
		if (!entity.isEntityValidated()) {
			validateEntity(entity);
		}
	}

	private boolean validateEntity(CommonEntityInterface entity) throws SpartanConstraintViolationException, SpartanIncorrectDataException {
		boolean isValidEntity = true;
		/* set entity validator */
		entity.setEntityValidator(this.entityValidator);
		/* validates the entity */
		isValidEntity = entity.isValid();
		return isValidEntity;
	}

	protected RuntimeLogUserInfo createLocalRuntimeLogInfo() {
		String ipAddress = "localhost";
		try {
			InetAddress ip = InetAddress.getLocalHost();
			ipAddress = ip.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return new RuntimeLogInfoImpl(1, "DEFAULT", ipAddress);
	}

	public void setCustomerIdInEntityManager() {
		if (runtimeLogInfoHelper.getCustomerId() != "" && runtimeLogInfoHelper.getCustomerId() != null) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("tenant.id", runtimeLogInfoHelper.getCustomerId());
			emfResource.setResourceAttributes(map);
			runtimeLogInfoHelper.setMultitenantFields(map);
		}
	}

	@Pointcut("execution(* com.athena..repository..*(..))")
	protected void athenaServiceOperation() {
	}

	@Pointcut("execution(* com.spartan..repository..*(..))")
	protected void spartanServiceOperation() {
	}

	@Pointcut("(execution(* com.athena..repository..*(..)) || execution(* com.spartan..repository..*(..))) && !within(com.athena.server.repository.ArtQueryRepository+)")
	protected void applifireRepositoryOperation() {
	}

	@Pointcut("execution(* com.athena.server.repository.ArtQueryRepository.findSqlFromJPQL())")
	protected void applifireJPQLToSQLConverter() {

	}

}
