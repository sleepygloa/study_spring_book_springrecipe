# 스프링 레시피5 2장

##### 메인


1.I18NConfiguration 클래스를 이용한 다국어 처리
- get방식을 이용해 localhost:8080/welcome?language=kr 이용
- interceptor 단계에서, 쿠키/세션 바운더리를 지정하고
- 그에 맞는 언어를 화면에 출력한다.


- getMessageSource 를 이용한 properties 관리.
- get 방식으로 쿠키/세션 바운더리의 언어요청이왔다만 필요한 문구를
properties 에 관리하여 출력해준다.
- 에러: CourtConfiguration 의 extends WebMvcConfigurerAdapter 추가와 @EnableWebMvc 추가
##### 서브
