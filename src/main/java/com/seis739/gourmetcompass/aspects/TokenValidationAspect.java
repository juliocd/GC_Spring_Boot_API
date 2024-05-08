package com.seis739.gourmetcompass.aspects;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seis739.gourmetcompass.enums.TokenStatus;
import com.seis739.gourmetcompass.service.ClerkService;
import com.seis739.gourmetcompass.utils.ApiResponse;

@Aspect
@Component
public class TokenValidationAspect {

    @Autowired
    private ClerkService clerkService;

    @SuppressWarnings("rawtypes")
	@Around("@annotation(TokenValidator)")
    public Object tokenValidator(ProceedingJoinPoint joinPoint) throws Throwable {
        Map<String, String> headers = extractHeadersFromJoinPoint(joinPoint);
        if (headers == null) {
            return new ApiResponse("Authorization header not found");
        }

        String token = headers.get("authorization");

        if (token == null) {
            return new ApiResponse("Bearer Toekn not found");
        }

        TokenStatus tokenStatus = clerkService.getTokenStatus(token.split(" ")[1]);

        if (tokenStatus == TokenStatus.EXPIRED) {
            return new ApiResponse("Token has expired.");
        } else if (tokenStatus == TokenStatus.INVALID) {
            return new ApiResponse("Token is invalid.");
        }

        return joinPoint.proceed();
    }

    @SuppressWarnings("unchecked")
	private Map<String, String> extractHeadersFromJoinPoint(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg instanceof Map<?, ?> headers) {
                return (Map<String, String>) headers;
            }
        }
        return null;
    }
}
