package Kelompok2_RPL.AplikasiKlinik.User;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component
public class AuthorizationAspect {
    @Autowired
    private HttpSession httpSession;

    @Before("@annotation(requiredRole)")
    public void CheckAuthorization(JoinPoint joinPoint, RequiredRole requiredRole) throws Throwable {
        
        String[] requiredRoles = requiredRole.value();
        String role = (String) httpSession.getAttribute("role");
        String username = (String) httpSession.getAttribute("email");
        if(username == null || username.isEmpty()){
            throw new SecurityException("User not login");
        }
        if(!Arrays.asList(requiredRoles).contains(role)){
            throw new SecurityException("User not required role");
        }
    }
}
