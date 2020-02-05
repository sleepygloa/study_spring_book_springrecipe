package recipe5_2_14.com.pojo.calculator;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorLoggingAspect implements Ordered{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

//	@Pointcut("execution(* *.*(..))")
//	private void loggingOperation() {}
	
	//@Before("loggingOperation")
	@Before("CalculatorPointcuts.loggingOperation()")
	public void logBefore(JoinPoint joinPoint) {
		log.info("The method " + joinPoint.getSignature().getName() + "() begins with " + Arrays.toString(joinPoint.getArgs()));
	}
	
	@Before("execution(* *.*(..))")
	public void logJoinPoint(JoinPoint joinPoint) {
		log.info("Join point kind : {}", joinPoint.getKind());
		log.info("Signature declaring type : {} ", joinPoint.getSignature().getDeclaringTypeName());
		log.info("Signature name : {}", joinPoint.getSignature().getName());
		log.info("Arguments : {}", Arrays.toString(joinPoint.getArgs()) );
		log.info("Target class : {} ", joinPoint.getTarget().getClass().getName());
		log.info("This class : {}", joinPoint.getThis().getClass().getName());
		
	}
	
	//요청의 성공, 실패 모두 작동.
	@After("execution(* *.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		log.info("The method " + joinPoint.getSignature().getName() + "() ends");
	}
	

	//특정경우에 작동
	@AfterReturning(
			//pointcut = "loggingOperation()",
			pointcut = "CalculatorPointcuts.loggingOperation()",
			returning = "result"
			)
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		log.info("The method {}() ends with {} ", joinPoint.getSignature().getName(), result);
	}
	
	//예외발생시 사용
	//1
//	@AfterThrowing("execution(* *.*(..))")
//	public void logAfterThrowing(JoinPoint joinPoint) {
//		log.error("An Exception has been thrown in {}()", joinPoint.getSignature().getName());
//	}
	//2
	@AfterThrowing(
			//pointcut = "loggingOperation()",
			pointcut = "CalculatorPointcuts.loggingOperation()",
			throwing="e"
			)
	public void logAfterThrowing(JoinPoint joinPoint, IllegalArgumentException e) {
		log.error("An Exception has been thrown in {}()", e, joinPoint.getSignature().getName());
	}
	
	//@Around("loggingOperation()")
	@Around("CalculatorPointcuts.loggingOperation()")
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
