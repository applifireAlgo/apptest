package com.app.server.businessservice.aspect;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import com.spartan.sprinkler.core.Sprinkler;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import com.spartan.sprinkler.core.RuntimeLogUserInfo;

@Aspect
@Component
public abstract class BusinessAspect {

	@Autowired
	private Sprinkler sprinkler;

	protected Object handleMethodCall(ProceedingJoinPoint joinPoint, RuntimeLogUserInfo runtimeLogInfoHelper) throws Throwable {
		try {

			sprinkler.logger.log(runtimeLogInfoHelper, 2000, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), "none");
			Object returnObject = joinPoint.proceed();
			sprinkler.logger.log(runtimeLogInfoHelper, 2001, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
			return returnObject;

		} catch (SpartanPersistenceException e) {
			e.printStackTrace();
			sprinkler.logger.log(runtimeLogInfoHelper, e.getExceptionId(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e, "fetching",
					"Entity");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			sprinkler.logger.log(runtimeLogInfoHelper, 2007, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e, "fetching", "Entity");
			throw e;
		}
	}

}
