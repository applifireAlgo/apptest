package com.app.server.businessservice.organizationboundedcontext.location.aspect;
import com.app.server.businessservice.aspect.BusinessAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import com.spartan.healthmeter.msgWriter.core.Healthmeter;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import com.spartan.healthmeter.entity.scheduler.MethodCallDetails;
import com.spartan.healthmeter.msgWriter.config.HealthConstants;

@Aspect
@Component
public class BusinessServicelocationAspect extends BusinessAspect {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private ArtMethodCallStack requestDetails;

    @Autowired
    private Healthmeter healthmeter;

    @Pointcut("execution(* com.app.server.businessservice.organizationboundedcontext.location..*(..))")
    protected void allOperation() {
    }

    @Around("allOperation()")
    public Object aroundfindAll(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodCallDetails methodCallDetails = new MethodCallDetails(requestDetails.getRequestId(), requestDetails.getCallSequence(), HealthConstants.CLASS_TYPE.BUSINESS, runtimeLogInfoHelper.getRuntimeLogInfo().getUserIpAddress(), "", joinPoint.getTarget().getClass().toString(), joinPoint.getSignature().getName(), runtimeLogInfoHelper.getRuntimeLogInfo().getUserId(), requestDetails.getAppSessionId());
        Object object = null;
        try {
            object = handleMethodCall(joinPoint, runtimeLogInfoHelper.getRuntimeLogInfo());
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_NORMAL_EXECUTION);
        } catch (SpartanPersistenceException e) {
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_EXCEPTION, e.getExceptionId());
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_EXCEPTION, HealthConstants.DEFAULT_EXCEPTION_ID);
            e.printStackTrace();
            throw e;
        } finally {
            requestDetails.addMethodCallDetails(methodCallDetails);
        }
        return object;
    }
}
