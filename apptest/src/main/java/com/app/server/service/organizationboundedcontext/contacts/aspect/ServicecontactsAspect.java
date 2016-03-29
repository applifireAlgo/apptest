package com.app.server.service.organizationboundedcontext.contacts.aspect;
import com.app.server.service.aspect.ServiceAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import com.athena.framework.server.helper.RuntimeLogInfoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import com.spartan.sprinkler.core.Sprinkler;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import com.spartan.healthmeter.msgWriter.core.Healthmeter;
import org.springframework.http.HttpStatus;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import com.spartan.healthmeter.msgWriter.config.ExecutionTimer;
import org.springframework.boot.actuate.metrics.repository.MetricRepository;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import com.athena.framework.server.bean.ResponseBean;
import com.athena.framework.server.exception.biz.SpartanDataNotFoundException;
import com.athena.framework.server.exception.repository.SpartanPersistenceException;
import com.athena.framework.server.exception.repository.SpartanTransactionException;
import com.athena.framework.server.exception.security.SpartanAccessDeniedException;
import com.spartan.healthmeter.entity.scheduler.MethodCallDetails;
import com.spartan.healthmeter.msgWriter.config.HealthConstants;
import java.util.UUID;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.core.annotation.Order;
import java.io.IOException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Component
public class ServicecontactsAspect extends ServiceAspect {

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private Sprinkler sprinkler;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    @Autowired
    private Healthmeter healthmeter;

    public HttpStatus httpStatusCode;

    @Autowired
    private CounterService counterService;

    @Autowired
    private GaugeService gaugeservice;

    @Autowired
    private ExecutionTimer executionTimer;

    @Autowired
    private MetricRepository repository;

    public AtomicLong autoRequestId = new AtomicLong(1);

    @Around("allOperation()")
    @Order(1)
    public Object aroundAdvice1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        HttpServletResponse response = servletWebRequest.getResponse();
        HttpSession session = request.getSession();
        long nextAutoNum = autoRequestId.getAndIncrement();
        methodCallStack.setRequestId(UUID.randomUUID().toString().toUpperCase());
        methodCallStack.setAppSessionId(getSessionId(request));
        MethodCallDetails methodCallDetails = new MethodCallDetails(methodCallStack.getRequestId(), methodCallStack.getCallSequence(), HealthConstants.CLASS_TYPE.SERVICE, request.getRemoteHost(), request.getMethod(), proceedingJoinPoint.getTarget().getClass().toString(), proceedingJoinPoint.getSignature().getName(), loggedInUserId(request), getSessionId(request));
        String entityName = incrementUricounter(proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName(), proceedingJoinPoint.getSignature().getName());
        ResponseEntity<ResponseBean> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        try {
            serviceLogic(session, request, response);
            Object obj = proceedingJoinPoint.proceed();
            responseEntity = (ResponseEntity<ResponseBean>) obj;
            httpStatusCode = responseEntity.getStatusCode();
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_NORMAL_EXECUTION, responseEntity.getStatusCode().name());
        } catch (SpartanAccessDeniedException e) {
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), 4005, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), request.getRemoteHost(), e);
            ResponseBean exceptionbean = e.prepareExceptionBean(sprinkler, runtimeLogInfoHelper.getRuntimeLogInfo(), "Access Denied" + e.getMessage());
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_EXCEPTION, e.getExceptionId(), responseEntity.getStatusCode().name());
            return new ResponseEntity<ResponseBean>(exceptionbean, responseEntity.getStatusCode());
        } catch (SpartanPersistenceException e) {
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), e.getExceptionId(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), request.getRemoteHost(), e);
            ResponseBean exceptionbean = e.prepareExceptionBean(sprinkler, runtimeLogInfoHelper.getRuntimeLogInfo(), "Can not perform Operation on entity:" + e.getMessage());
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_EXCEPTION, e.getExceptionId(), responseEntity.getStatusCode().name());
            return new ResponseEntity<ResponseBean>(exceptionbean, responseEntity.getStatusCode());
        } catch (SpartanTransactionException e) {
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), e.getExceptionId(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), request.getRemoteHost(), e);
            ResponseBean exceptionbean = e.prepareExceptionBean(sprinkler, runtimeLogInfoHelper.getRuntimeLogInfo());
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_EXCEPTION, e.getExceptionId(), responseEntity.getStatusCode().name());
            return new ResponseEntity<ResponseBean>(exceptionbean, responseEntity.getStatusCode());
        } catch (SpartanDataNotFoundException e) {
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), e.getExceptionId(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), request.getRemoteHost(), e);
            ResponseBean exceptionbean = e.prepareExceptionBean(sprinkler, runtimeLogInfoHelper.getRuntimeLogInfo());
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_EXCEPTION, e.getExceptionId(), responseEntity.getStatusCode().name());
            return new ResponseEntity<ResponseBean>(exceptionbean, responseEntity.getStatusCode());
        } catch (com.athena.framework.server.exception.biz.SpartanBusinessValidationFailedException e) {
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), e.getExceptionId(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), request.getRemoteHost(), e);
            ResponseBean exceptionbean = e.prepareExceptionBean(sprinkler, runtimeLogInfoHelper.getRuntimeLogInfo());
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_EXCEPTION, e.getExceptionId(), responseEntity.getStatusCode().name());
            return new ResponseEntity<ResponseBean>(exceptionbean, responseEntity.getStatusCode());
        } catch (com.athena.framework.server.exception.biz.SpartanConstraintViolationException e) {
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), e.getExceptionId(), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), request.getRemoteHost(), e);
            ResponseBean exceptionbean = e.prepareExceptionBean(sprinkler, runtimeLogInfoHelper.getRuntimeLogInfo());
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_EXCEPTION, e.getExceptionId(), responseEntity.getStatusCode().name());
            httpStatusCode = HttpStatus.PRECONDITION_FAILED;
            return new ResponseEntity<ResponseBean>(exceptionbean, responseEntity.getStatusCode());
        } catch (Exception e) {
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), 4005, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), request.getRemoteHost(), e);
            ResponseBean responseBean = new ResponseBean();
            responseBean.add("success", false);
            responseBean.add("message", "Access Denied:" + e.getMessage());
            methodCallDetails.setPostCallDetails(HealthConstants.METHOD_EXCEPTION, HealthConstants.DEFAULT_EXCEPTION_ID, responseEntity.getStatusCode().name());
            return new ResponseEntity<ResponseBean>(responseBean, responseEntity.getStatusCode());
        } finally {
            methodCallStack.addMethodCallDetails(methodCallDetails);
            healthmeter.apphealth.writeHealthLog((ArtMethodCallStack) methodCallStack.clone());
            Integer existingValue = 0;
            Metric metric = repository.findOne("gauge." + "total.Time" + entityName + "");
            if (metric != null) {
                existingValue = metric.getValue().intValue();
            }
            gaugeservice.submit("total.Time" + entityName + "", executionTimer.getSystemTime + existingValue);
        }
        return responseEntity;
    }

    @Around("saveOperation()")
    @Order(2)
    public Object aroundAdvice2Save(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        boolean isValidEntity = true;
        Object returnObject = new Object();
        aroundAdviceSaveAndUpdateLogin(proceedingJoinPoint);
        if (isValidEntity) {
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), 1000, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getArgs()[0].getClass().getSimpleName(), proceedingJoinPoint.getArgs()[0].toString());
            returnObject = proceedingJoinPoint.proceed();
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), 1001, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getArgs()[0].toString());
            return returnObject;
        }
        return returnObject;
    }

    @Around("updateOperation()")
    @Order(2)
    public Object aroundAdvice2Update(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        boolean isValidEntity = true;
        Object returnObject = new Object();
        aroundAdviceSaveAndUpdateLogin(proceedingJoinPoint);
        if (isValidEntity) {
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), 1000, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getArgs()[0].getClass().getSimpleName(), proceedingJoinPoint.getArgs()[0].toString());
            returnObject = proceedingJoinPoint.proceed();
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), 1001, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getArgs()[0].toString());
            return returnObject;
        }
        return returnObject;
    }

    @Around("deleteOperation()")
    @Order(2)
    public Object aroundAdvice2Delete(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnObject = new Object();
        if (proceedingJoinPoint.getArgs().length > 0) {
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), 1000, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getArgs()[0].getClass().getSimpleName(), proceedingJoinPoint.getArgs()[0].toString());
            returnObject = proceedingJoinPoint.proceed();
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), 1001, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getArgs()[0].toString());
            return returnObject;
        }
        return returnObject;
    }

    @Around("findOperation()||getOperation()")
    @Order(2)
    public Object aroundAdvicefindOperation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnObject = new Object();
        if (proceedingJoinPoint.getArgs().length > 0) {
            Object methodInputParam = proceedingJoinPoint.getArgs()[0];
            if (methodInputParam != null) {
                sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), 1000, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), methodInputParam.getClass().getSimpleName(), methodInputParam.toString());
                returnObject = proceedingJoinPoint.proceed();
                sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), 1001, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), methodInputParam.toString());
            }
        } else {
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), 1000, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), "none", "none");
            returnObject = proceedingJoinPoint.proceed();
            sprinkler.logger.log(runtimeLogInfoHelper.getRuntimeLogInfo(), 1001, proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), "none");
        }
        return returnObject;
    }

    @AfterReturning("allOperation()")
    public void afterReturning(JoinPoint join) throws IOException {
        counterService.increment("counter.HttpStatus." + httpStatusCode.name() + "." + join.getSignature().getDeclaringType().getSimpleName() + "." + join.getSignature().getName() + ".calls");
        counterService.increment("counter.numberof.calls");
    }

    public String incrementUricounter(String className, String methodName) {
        counterService.increment(className + "." + methodName);
        Metric metric = repository.findOne("gauge." + className + "." + methodName + "");
        if (metric != null) {
            gaugeservice.submit(className + "." + methodName, (Double) metric.getValue() + 1);
        } else {
            gaugeservice.submit(className + "." + methodName, 1);
        }
        return className + "." + methodName;
    }

    @Pointcut("execution(* com.app.server.service.organizationboundedcontext.contacts..save*(..))")
    protected void saveOperation() {
    }

    @Pointcut("execution(* com.app.server.service.organizationboundedcontext.contacts..update*(..))")
    protected void updateOperation() {
    }

    @Pointcut("execution(* com.app.server.service.organizationboundedcontext.contacts..delete*(..))")
    protected void deleteOperation() {
    }

    @Pointcut("execution(* com.app.server.service.organizationboundedcontext.contacts..find*(..))")
    protected void findOperation() {
    }

    @Pointcut("execution(* com.app.server.service.organizationboundedcontext.contacts..get*(..)) && !within(com.spartan.shield.sessionService.SessionDataMgtService+)")
    protected void getOperation() {
    }

    @Pointcut("execution(* com.app.server.service.organizationboundedcontext.contacts..*(..)) && !within(com.spartan.shield.sessionService.SessionDataMgtService+)")
    protected void allOperation() {
    }
}
