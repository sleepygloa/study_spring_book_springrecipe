# 스프링 레시피5 2장

##### 메인

1. @Resource
-Resource 는 Autoried, Qualifier 를 사용할때 대상이 여럿이라 모호한 경우 사용하면 좋다.
-명확하다.

2. @Inject 
- DI
- @Autowired, @Resource 와 같이 타입으로 pojo를 찾는다.

3. 좀더 명확하게 지정하기 위해서는 javax.inject 의 Qualifier 를 이용한다.
@Qualifier
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Retention(RetetionPolicy.RUNTIME)
public @interface DatePrefixAnnotation {

}

처럼 지정한후.
인터페이스를 구현한 곳에 @DatePrefixAnnotation을 선언하고

주입할 곳에
@Inject @DatePrefixAnnotation
을 사용한다


--- 

##### 서브
