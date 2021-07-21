작업 환경
============
- 사용언어 : Java11
- 프레임워크 : spring boot2, maven
- DB : H2 
- IDE : STS
- api document : Swagger

API
============
- 접속 정보 : http://localhost:8080/swagger-ui.html

카테고리
--------------
- 카테고리 목록 : /category/list

상품
--------------
- 특정 상품 조회 : /product/{productNo}
- 특정 카테고리에 해당하는 상품 목록 조회 : /product/category/{categoryNo}

admin
--------------
- 카테고리 명 변경 : /admin/category/{categoryNo}
- 상품 정보 변경 : /admin/product/{productNo}

특이사항
============
 - admin에서 DB 변경시 externalservice를 통해 cache에 반영하는 구조로 구성되어 있으나 정상 동작하지 않음
 - schedular를 통해 cache 전체를 반영하게 구성되어 있음. (소스는 주석으로 되어 있음) 


기타. 
============
- 실행방법 : MainApplication.java > Run As > Spring Boot App 선택.

DB 정보.
--------------
- URL : http://localhost:8080/h2
- 접속 정보 : root / test1 


