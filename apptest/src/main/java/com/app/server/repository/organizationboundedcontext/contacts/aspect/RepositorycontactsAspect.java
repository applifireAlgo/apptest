package com.app.server.repository.organizationboundedcontext.contacts.aspect;
import com.app.server.repository.aspect.RepositoryAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import com.spartan.healthmeter.entity.scheduler.MethodCallDetails;
import com.spartan.healthmeter.msgWriter.config.HealthConstants;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.sprinkler.core.Sprinkler;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

@Aspect
@Component
public class RepositorycontactsAspect extends RepositoryAspect {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private Sprinkler sprinkler;

    @Autowired
    private ArtMethodCallStack requestDetails;

    @Pointcut("execution(* com.app.server.repository.organizationboundedcontext.contacts..save*(..))")
    protected void saveOperation() {
    }

    @Pointcut("execution(* com.app.server.repository.organizationboundedcontext.contacts..update*(..))")
    protected void updateOperation() {
    }

    @Pointcut("execution(* com.app.server.repository.organizationboundedcontext.contacts..delete*(..))")
    protected void deleteOperation() {
    }

    @Pointcut("execution(* com.app.server.repository.organizationboundedcontext.contacts..find*(..))")
    protected void findOperation() {
    }

    @Pointcut("execution(* com.app.server.repository.organizationboundedcontext.contacts..get*(..))")
    protected void getOperation() {
    }

    @Pointcut("execution(* com.app.server.repository.organizationboundedcontext.contacts..*(..))")
    protected void allOperation() {
    }

    @Around("saveOperation()||deleteOperation()||updateOperation()||findOperation()||getOperation()||allOperation()")
    public Object aroundfindAll(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodCallDetails methodCallDetails = new MethodCallDetails(requestDetails.getRequestId(), requestDetails.getCallSequence(), HealthConstants.CLASS_TYPE.REPOSITORY, runtimeLogInfoHelper.getRuntimeLogInfo().getUserIpAddress(), "", joinPoint.getTarget().getClass().toString(), joinPoint.getSignature().getName(), runtimeLogInfoHelper.getRuntimeLogInfo().getUserId(), requestDetails.getAppSessionId());
        Object object = null;
        repositoryLogic(joinPoint);
        try {
            object = handleRepositoryCall(joinPoint, runtimeLogInfoHelper.getRuntimeLogInfo());
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
