# 스프링 레시피5 2장

##### 메인

1. 스코프

> singleton
@Compenent 를 이용하여 기본 선언을 하였을 때, Singleton scope으로 선언된다.
따라서, 같은 class를 빈으로 조회하여 업데이트한다면 신규객체가 아닌 기존객체를 불러와 수정이된다.

AAA 2.5
CD-RW 1.5
DVD-RW 3.0
shopping cart 1 contains [AAA 2.5, CD-RW 1.5]
shopping cart 2 contains [AAA 2.5, CD-RW 1.5, DVD-RW 3.0]

> prototype

AAA 2.5
CD-RW 1.5
DVD-RW 3.0
shopping cart 1 contains [AAA 2.5, CD-RW 1.5]
shopping cart 2 contains [DVD-RW 3.0]

--- 

##### 서브

singleton : loc 컨테이너당 인스턴스 하나를 생성
prototype : 요청할 때마다 빈 인스턴스를 새로 만듬
request   : http 요청당 하나의 빈 인스턴스를 생성, 웹 애플리케이션 컨텍스트에만 해당
session   : http 세션당 빈 인스턴스 하나를 생성, 웹 애플리케이션 컨텍스트에만 해당
globalsession : 전역 http 세션당 빈 인스턴스 하나를 생성, 포털 애플리케이션 컨텍스트만 해당
