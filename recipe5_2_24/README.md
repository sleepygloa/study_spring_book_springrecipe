# 스프링 레시피5 2장

##### 메인

> 현재의 프로젝트는 다중구현방법에 대해 에러를 포함하고있다.

1. Thread 구현학.
- TaskExecutorAdapter : java.util.concurrence.executors 인스턴스를 감싼 단순 래퍼, 스프링 taskExecutor 인터페이스와 같은 방식으로 다룰수 있다.
- SimpleAsyncTaskExecutor : 전송한 잡마다 thread 를 새로 만들어 제공하면 스레드 풀링을 하거나 재사용하지 않는다. 전송한 각 잡은 스레드에서 비동기로 실행.
- SyncTaskExecutor : 가장 단순한 taskExecutor 구현체, 동기적으로 thread를 듸우고 join을 바로 연결.
- ScheduledExecutorFactoryBean : scheduledExecutorTask 빈으로 정의된 잡을 자동트리거한다., scheduledExecutorTask인스턴스목록을 지정해서 여러잡을 동시해 실행할수 있다. 
- ThreadPoolTaskExecutor : java.util.concurrent.threadPoolExecutor을 기반으로 모든 기능이 완비된 스레드 풀 구현체.

##### 서브
