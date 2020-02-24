# 스프링 레시피5 2장

##### 메인


1. ViewResolver 파헤치기
- 기본적으로 ViewResolverConfiguration 에서 internalResourceViewResolver 의
viewResolver.setPrefix("/WEB-INF/jsp/");
viewResolver.setSuffix(".jsp");
을 controller 의 @RequestMapping 을 통한 매칭을 많이 바왔다.

1페이지 ajax 화면 구성에서 많이썼다.

- viewResolver 을 통한 별도의 뷰를 구성할 수 있다.
그러나 특별한 경우가 아니고서야 추가할이유없고 불편할 내용같다.  


##### 서브
