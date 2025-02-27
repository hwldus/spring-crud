# 도서 관리 시스템 CRUD
#### 프로젝트 실행 방법
- github(https://github.com/hwldus/spring-crud) 에서 프로젝트 다운로드(압축 풀기)
- IntelliJ IDEA에서 프로젝트 실행

#### H2 사용법(프로젝트 실행 후)
- http://localhost:8080/h2-console H2 주소로 이동
  - Driver Class:	org.h2.Driver
  - JDBC URL:	jdbc:h2:mem:bookDB
  - User Name: hjy
  - 입력 후 Connect 실행(Password는 없으므로 입력 불필요)
    
#### API 사용법(프로젝트 실행 후)
- http://localhost:8080/swagger-ui/index.html Swagger API 주소로 이동
- Author, Book API 모두 존재
  - 모든 api에 example이 있으므로 예시로 실행해도 확인 가능
  - 필요하면 예시를 보고 수정 가능
  - api 실행 추천 순서 **(생성 -> 조회 -> 수정 -> 삭제 -> 조회)**
- 400, 500 등 에러 발생 시 IllegalStateException 또는 message로 오류 발생 이유 확인 가능
