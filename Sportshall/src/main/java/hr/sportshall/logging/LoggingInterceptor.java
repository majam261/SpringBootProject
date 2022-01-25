package hr.sportshall.logging;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingInterceptor {

	Logger logger = Logger.getLogger(LoggingInterceptor.class.getName());
	
	@Before("execution(* hr.sportshall.controller.AppointmentController.getAppointments(..))")
	public void logBefore(JoinPoint joinPoint) {
		logger.info("Method : " + joinPoint.getSignature().getName() + " started.");
	}
	
	@After("execution(* hr.sportshall.controller.AppointmentController.getAppointments(..))")
	public void logAfter(JoinPoint joinPoint) {
		logger.info("Method : " + joinPoint.getSignature().getName() + " ended.");
	}
	
}