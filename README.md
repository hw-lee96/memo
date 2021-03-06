# [2021-02-21]
## 도서명 : 스프링 부트와 AWS로 혼자 구현하는 웹 서비스
###
### Chapter 1 : 인텔리제이로 스프링 부트 시작하기
###
#### 1.1. 진행 내용

1.1.1. 인텔리제이 설치

1.1.2. JDK 1.8 설치 및 환경변수 설정

1.1.3. 스프링부트 프로젝트 생성

1.1.4. Gradle 설정

1.1.5. Github 연동 및 gitignore 생성 

1.1.6. Github first push

###
#### 1.2. 주의사항

1.2.1. build.gradle 파일 설정 시 책에있는 내용 그대로 진행시 오류
 
- Gradle 7 이상 부터는 `compile -> implementation`, `testCompile -> testImplementation` 로 사용


-  jcenter 는 2022년 2월 11일 이후로 서비스 중단


###
### Chapter 2 : 스프링 부트에서 테스트 코드 작성하기
###
#### 2.1. 진행 내용
2.1.1. 테스트코드 작성

 - JUnit4 를 이용한 테스트 코드 작성 및 실행
 - 테스트코드는 필수로 작성하고 먼저 검증해야되며, 정말 못 믿겠다 싶으면 프로젝트를 실행하여 확인

2.2.2. 테스트 코드 작성 스텝

 - 항상 실패하는 테스트를 먼저 진행 (Red)
 - 테스트가 통과하는 프로덕션 코드를 작성 (Green)
 - 테스트가 통과하면 프로덕션 코드를 리펙토링 (Refactor)

2.2.3. 롬복 설치

 - 관련 어노테이션 테스트 진행

###

#### 2.2. 참고사항
2.2.1. Editor 내의 가이드 라인 설정하기

 - `Ctrl + Alt + S ` : Settings 화면 오픈
 - Edit > General > Appearance : Show hard wrap and visual guides (configured in Code style options) 의 체크를 제거

2.2.2. 자주 쓸 것 같은 인텔리제이 단축키 정리

 - `Ctrl + Shift + S` : 현재 파일명 수정 팝업 오픈
 - `Ctrl + E` : File 탐색 - `Ctrl + Alt + S ` : Settings 화면 오픈
 - `Ctrl + Shift + E` : File 탐색인데 최근 수정한 소스도 보여줌
 - `Ctrl + Shift + A` : Action 탐색
 - `Ctrl + B` : 선택한 변수의 선언부로 이동. 클래스를 선택 시 해당 클래스의 파일 오픈
 - `F2` : 오류나 경고 이동
 - `Alt + Enter` : 표시된 오류, 경고, 제안 목록 확인
 - `Alt + F7` : 선택한 항목이 사용된 위치를 전부 검색
 - `Ctrl Ctrl` : 선택한 항목을 실행하는 Run Anything 창 오픈
 - `Ctrl + Shift + Enter` : 구문 완성. 보통은 세미콜론이 붙지만, if문이나 for문에서는 문장을 자동 완성시킴
 - `Ctrl + Alt + L` : 줄맞춤
 - `Ctrl + Shift + Alt + T` : 리팩토링 옵션 표시
 - `Shift Shift` : 전체검색


###
### Chapter 3 : 스프링 부트에서 JPA로 데이터베이스를 다뤄보자
###
#### 3.1. 진행 내용
3.1.1. JPA 소개
- 관계형 데이터베이스를 사용해야만 하는 상황에서 SQL은 피할 수 없다. 그리고 이러한 반복적인 SQL을 생성하는 것 만큼 힘든 일은 없다.
- 또한, 관계형 데이터베이스와 객체 지향은 패러다임이 불일치하는 것 또한 문제가 된다.
- JPA는 중간에서 패러다임을 일치시키는 역할을 하여 이러한 문제점을 해결한다.
- 개발자는 객체지향적으로 프로그래밍을 하고, 이를 JPA가 관계형 데이터베이스에 맞게 SQL을 대신 생성하여 실행한다.

3.1.2. Spring 웹 계층
- Web Layer
  - 컨트롤러(@Controller), JSP/Freemarker 등의 뷰 템플릿 영역. 
  - 이외에도 필터(@Filter), 인터셉터, 컨트롤러 어드바이스(@ControllerAdvice) 등 외부 요청과 응답에 대한 전반적인 영역을 말함

- Service Layer
  - @Service에 사용되는 서비스 영역
  - 일반적으로 Controller 와 Dao 의 중간 영역에서 사용됨
  - @Transactional이 사용되어야 하는 영역이기도 함

- Repository Layer
  - Database와 같이 데이터 저장소에 접근하는 영역
  - Dao(Data Access Object) 영역으로 이해하면 쉬움

- Dtos
  - Dto(Data Transfer Object)는 계층 간에 데이터 교환을 위한 객체를 이야기 함
  - 뷰 템플릿 엔진에서 사용될 객체나 Repository Layer 에서 결과로 넘겨준 객체를 예로 들 수 있다.

- Domain Model
  - 개발 대상을 모든 사람이 동일한 관점에서 이해/공유 할 수 있도록 단순화시킨 것을 말함
  - @Entity가 사용된 영역 역시 도메인 모델이라고 할 수 있음
  - 다만, 무조건 데이터베이스의 테이블과 관계가 있어야하는 것은 아님. VO 처럼 값 객체들도 이 영역에 해당됨.


이러한 5가지 레이어에서 비지니스 로직을 처리할 곳은 Domain 영역이다.


###
### Chapter 4 : 머스테치로 화면 구성하기
###
#### 4.1. 진행 내용
4.1.1. 서버 템플릿 엔진과 머스테치 소개
 - 템플릿 엔진 : 지정된 템플릿 양식과 데이터를 합쳐 HTML 문서를 출력하는 녀석
   - 서버 템플릿 엔진 : JSP, Freemarker 등
     - JSP의 경우 서버에섯 Java 코드로 문자열을 만든 뒤 이 문자열을 HTML로 변환하여 브라우저로 전달함.
   - 클라이언트 템플릿 엔진 : React, Vue 등
     - 브라우저(클라이언트 영역)에서 화면을 생성함.
 
 - 머스테치
   - 다양한 언어를 지원하는 심플한 템플릿 엔진


4.1.2. 하 이 세팅이 있었나. 왜 파일을 못 찾지
 - footer.mustache 에서 index.js 파일을 못 찾아서 자꾸 에러 뜸
 - 별의 별 짓을 다 했는데 안됨
 - application.properties 파일에 아래 내용도 추가해봄
   - spring.resources.static-locations=classpath:/static/
 - 어 갑자기 됨
 - 에러 보려고 저거 지우고 재가동 함
 - 근데 잘 됨(?)
 - 가설 중 하나가 '저 설정으로 인해 파일이 잡혔다.' 인데 나중에 또 안되면 다시 추가해봐야지


###
### Chapter 5 : 스프링 시큐리티와 OAuth 2.0으로 로그인 기능 구현하기
###
#### 5.1. 진행 내용
5.1.1. 스프링부트 1.5 vs 2.0
 - 1.5에서의 연동방법이 2.0에서는 크게 변했으나, 아래 라이브러리 덕분에 설정 방법이 크게 차이가 없다.
 - 라이브러리 사용 시 1.5에 사용하던 설정을 2에서도 사용 가능하다.
 - spring-security-oauth2-autoconfigure

5.1.2. 구글 서비스 등록
 - OAuth 클라이언트 ID 로 생성
 - 승인된 리디렉션 URI 입력 
 - application-oauth.properties 파일 생성 및 클라이언트 ID, 클라이언트 보안 비밀코드 등록
 - 구글 로그인 연동

5.1.3. 용어 정리
 - Enum : 상수처럼 사용할 수 있는 클래스로, Enumeration 의 앞글자를 땄다. '글자로 열거(나열)' 이라는 의미를 가지게 되며 용도와 관련된 상수들의 집합이다.

5.1.4. 용법 정리
 - 머스테치 용법
   - {{#userName}} : true, false 여부를 판단하는 경우 사용.
   - {{^userName}} : 해당 값이 존재하지 않는 경우 ^ 를 사용.
 - 어노테이션
   - @interface : 해당 클래스를 어노테이션 클래스로 지정

5.1.5.  세션 저장소로 데이터베이스 사용
 - 현재 내장 톰캣 메모리에 저장되어 재실행 DB가 초기화 되는 상황 개선
 - 2대 이상의 서버일 경우 세션 동기화 설정
   - 톰캣 세션 사용
     - 기본적으로 사용되며, 2대 이상의 WAS 가 구동되는 경우 세션 공유를 위한 추가 설정 필요
   - MySQL 과 같은 DB 를 세션 저장소로 이용 (현재는 이 방법 사용)
     - 여러 WAS 간의 공용 세션을 사용할 수 있는 가장 쉬운 방법
     - 로그인 요청마다 DB I/O 가 발생하기 때문에 성능 이슈가 발생할 수 있음
     - 로그인 요청이 많이 없는 백오피스 등에 사용
   - Redis, Memcahed 와 같은 메모리 DB 를 세션 저장소로 사용
     - B2C 서비스에서 가장 많이 사용 됨
       - 실제 서비스로 사용하기 위해서는 Embedded Redis 와 같은 방식이 아닌 외부 메모리 서버가 필요함
       
 - 현재는 DB를 H2로 사용하고 있기 때문에 재시작 시 여전히 초기화가 된다. (스프링 재시작 시 H2도 재시작 되기 때문에)
    - 추후 AWS의 DB 서비스인 RDS(Relational Database Servie)를 사용하여 해결 예정

5.1.6. 네이버 로그인
 - application-oauth.properties 설정
   - 설정 내용 중 user-name-attribute를 response로 해야 함 (네이버 회원 조회 시 반환되는 JSON 형태 때문)
     - JSON 이 아래와 같은 형태이기 때문에 하위 필드는 명시할 수 없음. 
 {
     "resultcode" : "00",
     "message" : "success",
     "response" : {
        ~~~ 
    }
 }

5.1.7. 테스트 진행
 - src/main 과 src/test의 환경 차이로 인해 에러 발생
   - test에 application.properties 가 없으면 main에서 가져오게 됨
   - 여기서 application-oauth.properties 파일은 가져오지 않기 때문에 에러가 발생
   - test 하단에 application.properties 파일 생성 및 가짜 설정값을 추가하여 테스트가 진행 가능하도록 설정
