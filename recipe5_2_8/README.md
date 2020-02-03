# 스프링 레시피5 2장

##### 메인

1. 애너테이션이용해 POJO 초기화/폐기 커스터마이징하기

- VO 같은 시동단계에서 불필요한 클래스는 @Lazy 선언
- 선언 후 먼저 실행되어야 하는 메서드는 @PostConstructor, 실행 후 꼭 실행되어야 하는 메서드는 @PreDestroy
- 빈을 선언할 때도 	@Bean(initMethod="openFile", destroyMethod="closeFile")

- 지정한 Bean 보다 먼저 실행시키기 위해선 @DependsOn("빈이름") 


##### 서브
