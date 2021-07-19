# 작업 환경
- 사용언어 : Java11
- 프레임워크 : spring boot2
- DB : H2 
- IDE : STS
- api document : Swagger

# API
- 접속 정보 : http://localhost:8080/swagger-ui.html

### 카테고리.
- 카테고리 목록 : /category/list

### 상품.
- 특정 상품 조회 : /product/{productNo}
- 특정 카테고리에 해당하는 상품 목록 조회 : /product/category/{categoryNo}

### admin
- 카테고리 명 변경 : /admin/category/{categoryNo}
- 상품 정보 변경 : /admin/product/{productNo}

# 기타. 

### DB 정보.
http://localhost:8080/h2
root / test1 


