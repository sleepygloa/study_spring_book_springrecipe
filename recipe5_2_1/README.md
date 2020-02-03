# 스프링 레시피5 2장

##### 메인

1. Spring-core, Spring-context

- Spring-context에서 annotation을 지원한다.
- @Configuration, @Bean, ApplicationContext, AnnotationConfigApplicationContext 모두 Spring-context 에 포함된다.




2. @Component

- spring-context 가 발견할 수 있게 하는 범용 어노테이션이다
- @Repository, @Controller, @Service 보다 상위 개념으로 구체적이지않다.


3. @ComponentScan 

- 주로 xml 에서 사용하던 동일한 기능을가진 어노테이션
- bean 으로 읽어 들일 파일의 이름 규칙과, 제외시킬 규칙을 선언하여 필요한 파일만 메모리에 상주하게 관리한다.





--- 

##### 서브

1. AtomicInteger

- java.util.concurrent.atomic.AtomicInteger
- AtomicInteger는 Int 자료형을 갖고 있는 wrapping 클래스이다.
- 멀티쓰레드환경에서 동시성을 보장

- 한쪽에서 읽고, 한쪽에서 쓰기만 한다면 동시성을 보장하지만, 둘다쓰면 아니다
- 둘다 쓸때, synchronized 를 사용하면 동시성 보장이 되지만 비용이크다
- AtomicInteger 는 Synchronized 보다 적은 비용으로 동시성을 보장한다.