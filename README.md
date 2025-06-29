# HPMS (부서 관리 시스템)
## 1. 제작 동기
해당 프로젝트는 교수님과 함께 개발한 부서 관리 시스템으로, 각 부서의 요청 사항 수행률을 쉽게 파악할 수 있도록 설계되었습니다.

PHP로 2주 만에 개발했으나, 개발 기간이 시험 기간과 겹쳐 코드 완성도가 개인적으로 많이 아쉬웠습니다. 이후 Spring Framework를 학습한 뒤, 복습을 겸해 Spring Boot로 다시 개발했습니다.


## 2. 설계
![hpmsERD](https://github.com/coldsteelpope/hpms/assets/128117575/a44ff3cc-0a52-4d50-a9d7-3f812caeb6e0)

## 3. 기술
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"> <img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white"> <img src="https://github.com/coldsteelpope/minitube/assets/128117575/67c28619-635c-42a4-a39a-a48dec9b1201" alt="h2database" height="27.99">

## 4. 기능

### 4.1. 회원 가입
![로그인](https://github.com/coldsteelpope/hpms/assets/128117575/a2e8f618-62a9-4be0-92d6-5e14087eaf07)

![회원가입](https://github.com/coldsteelpope/hpms/assets/128117575/7da47a05-6210-4372-bd28-1ade213d7724)

### 4.2. 메인 페이지
![아무것도 안했을때 부서 성취도](https://github.com/coldsteelpope/hpms/assets/128117575/134bc652-9435-4b44-85af-1570258b5789)

![아무것도 안했을때 학과 성취도](https://github.com/coldsteelpope/hpms/assets/128117575/a3f1c88d-39e8-40d9-a4fd-2836d138e467)

#### 4.2.1. 성취율 오름차순, 내림차순 설정
![부서내림차순](https://github.com/coldsteelpope/hpms/assets/128117575/92745e2f-4a31-4125-ada3-d2c9d8c676ba)

#### 4.2.1. 메인 페이지에서 요청 사항 페이지로 이동
![index페이지에서요청사항으로이동](https://github.com/coldsteelpope/hpms/assets/128117575/06c4e31c-2911-4d38-9abb-18a719574185)

### 4.3. 요청사항 추가
![요청사항추가하기](https://github.com/coldsteelpope/hpms/assets/128117575/cbfaf1d9-79e6-4c0e-9b26-9b3916a6a678)

### 4.4. 요청사항 페이지
#### 4.4.1 요청사항 상태 변경
![준비중에서진행중으로](https://github.com/coldsteelpope/hpms/assets/128117575/4e5449b2-4970-4e1e-877c-9bf5f2aa556d)

요청 사항 상태를 "준비 중"에서 "진행 중"으로 변경합니다.

![진행중에서완료](https://github.com/coldsteelpope/hpms/assets/128117575/0c9f0fad-aff6-4db2-87bc-441eb819d235)

요청 사항 상태를 "진행 중"에서 "완료"로 변경합니다.

#### 4.4.2. 댓글 작성
![파일과 함께 댓글 작성하기](https://github.com/coldsteelpope/hpms/assets/128117575/3c70af8d-16c4-48be-83ca-dbc1f8c7f724)

파일까지 첨부해 댓글 작성이 가능합니다.

#### 4.4.3. 댓글 수정
![댓글수정](https://github.com/coldsteelpope/hpms/assets/128117575/0ba6940f-8a68-4224-8cbc-ec6126f276b0)

#### 4.4.4. 댓글 삭제
![댓글삭제](https://github.com/coldsteelpope/hpms/assets/128117575/8b8ce378-5723-4073-b6a8-35a0465f07bf)

#### 4.4.5. 댓글 첨부 파일 다운로드
![댓글 파일 다운로드](https://github.com/coldsteelpope/hpms/assets/128117575/30bf48be-6390-4195-8214-226f7d2ba77d)

### 4.7. 부서 관리 페이지
![회원 관리 페이지](https://github.com/coldsteelpope/hpms/assets/128117575/7a701bfc-9a9c-47b0-b20e-5c18d02abb6f)

#### 4.7.1. 부서 관리 상태 변경하기
![팀원상태고치기](https://github.com/coldsteelpope/hpms/assets/128117575/2057e1a6-d93a-4ad1-b710-139645e8dc67)

![팀원상태 부정적으로 고치기](https://github.com/coldsteelpope/hpms/assets/128117575/5b16fd0d-084b-4191-b6eb-e422a4aac50c)

### 4.8. 랭킹 페이지
![랭킹](https://github.com/coldsteelpope/hpms/assets/128117575/898a827a-ed26-424b-a786-18aee3fa7815)
