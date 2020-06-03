# 스프링 레시피5 2장

##### 메인

1. 외부 리소스 파일 불러오기

> xml

1.미리 사용할 xml 파일 discounts.properties 를 만들고 key=value 형식으로 데이터를 만들어둔다.

2. 
클래스선언
@PropertySource("classpath:discounts.properties")
변수선언.
@Value("${endofyear.discount:0}")
0 자리는 예외처리시 0 으로 표현한다는 뜻이다.

3. 사용
Resource resource = new ClassPathResource("discounts.properties");
Properties props = PropertiesLoaderUtils.loadProperties(resource);

System.out.println("And don't forget out discounts!");
System.out.println(props);

> txt
1.txt 의 변환, 주입을 위한 클래스를 만들고 미리 선언되기 위한 어노테이션을 지정한다.
@PostContstruct 를 지정해둔다.
Files.lines(Paths.get(banner.getURI()), Charset.forName("UTF-8")).forEachOrdered(System.out::println);

2. 사용
@Bean 등록하여 메서드안에서 해당 1번의 자바파일을 생성하여 변수로 저장한다.
@Value("classpath:banner.txt")

--- 

##### 서브
