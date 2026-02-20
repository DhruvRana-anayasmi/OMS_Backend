package com.example.OMS.service.aspect;


import com.example.OMS.entity.AuditLog;
import com.example.OMS.repository.AuditRepository;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.example.OMS.entity.principle.UserPrinciple;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AuditLoggingAspect {

    @Autowired
    private AuditRepository auditRepo;

    @Pointcut("execution(* com.example.OMS.service..*(..))")
    public void serviceMethods() {}

    @AfterReturning(pointcut = "serviceMethods()")
    public void logAfterSuccess(JoinPoint joinPoint) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Long userId = null;
        String username = "SYSTEM";

        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            UserPrinciple principal = (UserPrinciple) auth.getPrincipal();

            username = principal.getUsername();
        }

        AuditLog log = new AuditLog();
        log.setServiceName(joinPoint.getTarget().getClass().getSimpleName());
        log.setMethodName(joinPoint.getSignature().getName());
        log.setArguments(Arrays.toString(joinPoint.getArgs()));
        log.setStatus("SUCCESS");

        log.setPerformedByName(username);
        log.setPerformedAt(LocalDateTime.now());

        auditRepo.save(log);
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logAfterFailure(JoinPoint joinPoint, Exception ex) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Long userId = null;
        String username = "SYSTEM";

        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            UserPrinciple principal = (UserPrinciple) auth.getPrincipal();

            username = principal.getUsername();
        }

        AuditLog log = new AuditLog();
        log.setServiceName(joinPoint.getTarget().getClass().getSimpleName());
        log.setMethodName(joinPoint.getSignature().getName());
        log.setArguments(Arrays.toString(joinPoint.getArgs()));
        log.setStatus("FAILED");
        log.setErrorMessage(ex.getMessage());

        log.setPerformedByName(username);
        log.setPerformedAt(LocalDateTime.now());

        auditRepo.save(log);
    }

}
