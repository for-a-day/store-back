<p align="center">
 <img src="https://github.com/user-attachments/assets/c532138c-db7b-4b35-9f3d-10f3a7fccf0d">
</p>
 <h3> 테이블 오더 & 가맹점 & 본사(인트라넷) 연계 프로젝트 </h3></div>
<div align="center"></div>

# 테이블 오더 & 가맹점 개발 프로젝트

---
### 📌 프로젝트 소개
- 테이블 오더 & 가맹점 & 본사(인트라넷) 연계 프로젝트
<p align="center">
 <img src="https://github.com/user-attachments/assets/a0ec6284-c453-464c-8a17-004268c6cd9a">
</p>

### 🎞 시연영상 👇
- 
 
<br>

### 📰 제작기간 & 팀원 소개
- 2024-06-19. ~ 2024-07-12.

|이름| 담당 기능 구현                                                                         |
|------|----------------------------------------------------------------------------------|
|김예린| 인트라넷 - 가맹점관리(CRUD), 메뉴관리(CRUD), 매출관리(CRUD), 재고관리(CRUD)                |
|나소림| 가맹점 - 재고관리(CRUD), 매출관리(CRUD), 메뉴관리(CRUD), 지점장POS(CRUD), 관리자용POS(CRUD)        |
|문승환| 인트라넷 - 인사관리(CRUD), 로그인(JWT), 메신저(CRUD) |
|박민규| 인트라넷 - 캘린더관리(CRUD), 일정관리(CRUD), 대시보드(CRUD), 디자인 |
|이윤재| 인트라넷 - 전자결재(CRUD), 대시보드(CRUD), 알림(CRUD) |
|임주연| 가맹점 - 관리자관리(CRUD), 가맹점관리(CRUD), 테이블관리(CRUD), 주문(CRUD), 테이블오더 |

<br>

## ⛏ BE 기술 Stack

###### Dev-Tools
- Notion
- Git
- GitHub
- Slack

<br>

###### Back-end Stack
- Java 17
- Spring Boot 3.3.1
- Database : Oracle 11g
- Security : Spring Security, JWT
- JPA
- springdoc(Swagger) 2.5.0

<br>

## 🌸 아키텍쳐

### 테이블 오더(TO)
<img alt="arc" src="https://github.com/user-attachments/assets/248430df-d6da-450a-932e-06b1ec1efbf2">

### 관리자 & 가맹점주 POS
<img alt="arc" src="https://github.com/user-attachments/assets/f9b22af1-6093-4cd3-a7d5-2e2150fef939">
<br>

## ⚙️ ERD

<img alt="erd" src="https://github.com/user-attachments/assets/14828cb4-5ad2-4311-bc93-328b0c451167">
<br>

## 🌸 API 설계
[API 설계 문서](https://www.notion.so/api-73f371e16c1841cd9b23ea6e753eb140)

## ✔ 주요 기능

- 🏬 가맹점관리
  - 가맹점 등록
  - 가맹점 경고 기능
  - 폐점한 매장 업데이트 및 조회

- 🍽 메뉴관리
  - 메뉴 등록
  - 메뉴 판매 상태 변경 및 조회(판매/미판매)
  - 필요한 재고 발주하기

- 💻 주문관리
  - 가맹점 별 (당일) 주문 내역 확인(영수증)
  - 테이블 별 주문 내역 확인
  - 손님 식사 완료 여부에 따라 주문 상태 변경

- 📈 매출관리
  - 당일 & 이번 달 매출 조회
  - 당일 & 이번 달 결제 건수 조회

- 📋 테이블 오더
  - 테이블 등록 & 비활성화
  - 메뉴 확인 & 장바구니 담기
  - 장바구니 담은 메뉴 주문하기
  - 해당 테이블 현재 주문 내역 확인
  - 홀짝 게임
