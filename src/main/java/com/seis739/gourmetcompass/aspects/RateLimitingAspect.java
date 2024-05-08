package com.seis739.gourmetcompass.aspects;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seis739.gourmetcompass.utils.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class RateLimitingAspect {

    @Autowired 
    private HttpServletRequest request;

    private static final ConcurrentHashMap<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();
    private static final int REQUEST_LIMIT = 15;
    private static final long TIME_LIMIT = 60000;

	@SuppressWarnings("rawtypes")
    @Around("@annotation(RateLimited)")
    public Object beforeRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        String clientId = request.getHeader("X-FORWARDED-FOR");  
        if (clientId == null) {  
            clientId = request.getRemoteAddr();  
        }
        AtomicInteger count = requestCounts.computeIfAbsent(clientId, k -> new AtomicInteger(0));
        if (count.incrementAndGet() > REQUEST_LIMIT) {
            return new ApiResponse("Too many requests");
        }
        if (requestCounts.size() == 1) {
            resetRequestCounts();
        }

        return joinPoint.proceed();
    }

    private void resetRequestCounts() {
        new Thread(() -> {
            try {
                Thread.sleep(TIME_LIMIT);
                requestCounts.clear();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
