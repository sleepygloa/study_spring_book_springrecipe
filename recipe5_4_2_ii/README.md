# 스프링 레시피5 2장

##### 메인


1. @REST API 사용방법
response 될 list 를 rest api 호출이 있을때, 
Jaxb2Marshaller 을 이용하여 xml로 변환하여 응답한다.
-> Jaxb2Marshaller는 너무 불편하니 @Responsebody를 사용하여 위 과정을 생략한다.
-> @ResponseBody 사용하는대신 @Controller 를 @RestController 로 사용할수있다.
-> url/member/{memberid} 와 같이 url 패턴을 구성함으로써, 원하는 사용자의 정보만 응답할수있다.
->적절하지않은 요청이올때 에러코드 반환

2.  @REST API 사용방법
위와 같지만 Json을 이용한 응답방법
-> json과 xml를 구별하여 응답하는 방법
-> json과 xml을 구별하고, 적절한 요청에 의한 응답방법을 회신하는 데
 uri를 코딩하지않고, 객체 또는 다양하게 사용하능 방법 기술.
##### 서브
