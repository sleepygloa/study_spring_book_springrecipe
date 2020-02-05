# 스프링 레시피5 2장

##### 메인

1. Aspect 의 순서 정하기

- Aspect가 많을때 어느것이 먼저 실행되는지 알수없다.


1) implements Ordered
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}
	
2) @Order(0)

##### 서브
