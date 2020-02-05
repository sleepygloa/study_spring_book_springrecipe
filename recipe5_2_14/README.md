# 스프링 레시피5 2장

##### 메인

1. Aspect, pointcut, joinPoint 정보불러오기.

		log.info("Join point kind : {}", joinPoint.getKind());
		log.info("Signature declaring type : {} ", joinPoint.getSignature().getDeclaringTypeName());
		log.info("Signature name : {}", joinPoint.getSignature().getName());
		log.info("Arguments : {}", Arrays.toString(joinPoint.getArgs()) );
		log.info("Target class : {} ", joinPoint.getTarget().getClass().getName());
		log.info("This class : {}", joinPoint.getThis().getClass().getName());
		
그러나 안됨.

SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.

##### 서브
