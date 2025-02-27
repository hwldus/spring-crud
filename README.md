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
- Author, Book API 모두 존재 **(마지막에 주의사항 참고!)**
  - 모든 api에 example이 있으므로 예시로 실행해도 확인 가능
  - 필요하면 예시를 보고 원하는 부분 수정 가능
> api 실행 순서(예시를 사용)  
1. 저자 생성  
{  
  "name": "홍길동",  
  "email": "hong@naver.com"  
}
2. 저자 조회(전체,특정 상관 X)
3. 저자 수정  
{  
  "name": "유재석",  
  "email": "yoo@naver.com"  
}
4. 저자 조회(전체,특정 상관 X)
5. 도서 생성  
{  
  "title": "노인과 바다",  
  "description": "쿠바 연안을 배경으로 늙은 어부 산티아고의 이야기를 그린 작품이다.",  
  "isbn": "1234567890",  
  "publication_date": "1952-09-01",  
  "author_id": 1  
}
6. 도서 조회(전체,특정 상관 X)
7. 도서 수정  
{  
  "title": "노인",  
  "description": "",  
  "isbn": "1234567890",  
  "publication_date": "",  
  "author_id": "1"  
}  
8. 도서 조회(전체,특정 상관 X)
9. 도서 삭제 or 저자 삭제(단, 저자를 삭제할 때는 해당 저자가 쓴 도서도 삭제된다.)
10. 도서 조회 or 저자 조회

### ❗️주의사항  
**- 400, 500 등 에러 발생 시 IllegalStateException 또는 message로 오류 발생 이유 확인 가능**  
**- 저자 생성과 수정 시 name, email은 필수(email은 중복 불가)**  
**- 도서 생성과 수정 시 title, isbn, author_id는 필수(isbn은 생성 시 중복 불가, 유효성 검사 존재O)**  
**- 도서 or 저자 삭제 후 재생성 시 id는 1이 아닌 2이므로 api 실행 시 예시 수정 필요**
