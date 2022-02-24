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
3.1.1. 테스트코드 작성