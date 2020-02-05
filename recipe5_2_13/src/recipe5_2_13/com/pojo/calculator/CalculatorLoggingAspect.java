package recipe5_2_13.com.pojo.calculator;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorLoggingAspect {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	//@Before("execution(* ArithmeticCalculator.add(..))")
	@Before("execution(* *.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		log.info("The method " + joinPoint.getSignature().getName() + "() begins with " + Arrays.toString(joinPoint.getArgs()));
	}
	
	
	//요청의 성공, 실패 모두 작동.
	@After("execution(* *.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		log.info("The method " + joinPoint.getSignature().getName() + "() ends");
	}
	
	//특정경우에 작동
	@AfterReturning("execution(* *.*(..))")
	public void logAfterReturning(JoinPoint joinPoint) {
		log.info("The method {}() ends with {} ", joinPoint.getSignature().getName(), "result");
	}
	
	//예외발생시 사용
	//1
//	@AfterThrowing("execution(* *.*(..))")
//	public void logAfterThrowing(JoinPoint joinPoint) {
//		log.error("An Exception has been thrown in {}()", joinPoint.getSignature().getName());
//	}
	//2
	@AfterThrowing(
			pointcut = "execution(* *.*(..))",
			throwing="e"
			)
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.error("An Exception has been thrown in {}()", e, joinPoint.getSignature().getName());
	}
	
	@Around("execution(* *.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
		log.info("The method {}(); begins with {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		
		try {
			Object result = joinPoint.proceed();
			log.info("The method {}() ends with ", joinPoint.getSignature().getName(), result);
			return result;
		}catch(Exception e){
			log.error("Illegal argument {} in ();", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
			throw e;
		}
	}
}
