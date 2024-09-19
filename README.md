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
|임주연| 가맹점 - 관리자관리(CRUD), 가맹점관리(CRUD), 테이블관리(CRUD), 주문(CRUD), 테이블오더 앱 디자인 및 개발 |

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

[ERD CLOUD 설계](https://www.erdcloud.com/d/Jcot8WXsi7yvCXEHL)

## 🌸 API 설계
[API 설계 문서](https://www.notion.so/api-73f371e16c1841cd9b23ea6e753eb140)

## 📔 API 명세서

| API 명칭            | HTTP 메서드 | 엔드포인트                | 설명                                |
|---------------------|-------------|---------------------------|-------------------------------------|
| 가맹점 로그인     | POST        | /login                      | 가맹점주가 포스기에 로그인하기 위해 사용하는 API입니다.          |
| 테이블 목록 조회     | GET         | /table?storeId={storeId}           | 해당 가맹점 테이블 목록을 조회합니다.          |
| 테이블 신규 등록       | POST        | /table                    | 해당 가맹점 테이블을 신규 등록합니다.          |
| 현재 주문 목록 조회    | GET         | /order?storeId={storeId}                 | 현재 가맹점 내의 모든 진행 중인 주문 목록을 조회합니다.           |
| 주문 상태 변경    | PUT         | /order/state?orderId={orderId}            | 주문의 상태를 변경합니다. |
| 선택한 주문 상세 정보 조회       | GET       | /order?orderId={orderId}        | 선택한 주문의 단일 상세 정보를 조회합니다.       |
| 결제 목록 조회 (영수증)        | GET         | /payment?storeId={storeId}      | 해당 가맹점의 당일 결제 목록을 조회합니다.                |
| (단일) 결제 반품        | PUT         | /payment      | 단일 결제 항목을 반품합니다.      |
| 재고 목록 조회     | GET         | /stock?storeId={storeId}          | 현재 가맹점의 재고 목록을 조회합니다.         |
| 재고량 수정       | PUT        | /stock                    | 선택한 재고의 수량을 수정합니다.           |
| 재고 생성    | POST         | /stock                 | 신규 재고 품목을 생성합니다.          |
| 재고 삭제   | DELETE         | /stock             | 더 이상 관리할 필요가 없는 재고를 삭제합니다. |
| 재고 단일 품목 발주 신청      | POST       | /purchase-order        | 재고 단일 품목 발주를 신청합니다.      |
| 발주 입고 완료 여부 체크        | PUT         | /purchase-order       | 발주 신청한 재고 품목이 입고됐을 시, 입고 완료 여부 확인을 위한 API입니다.      |
| 발주 삭제       | DELETE       | /purchase-order        | 발주한 항목을 취소합니다.     |
| 매출 정보 조회        | GET         | /sales?storeId={storeId}      | 해당 가맹점의 매출 정보를 조회합니다.      |
| 관리자 로그인     | POST         | /admin/login           | 가맹점 관리자(이하 관리자)가 관리자 페이지에 로그인 하기 위해 사용하는 API입니다.        |
| 지점 목록 조회     | GET         | /admin/store           | 가맹점 목록을 조회합니다.       |
| 지점 신규 등록(=지점장 회원 가입)       | POST        | /admin/store                    | 가맹점을 신규 등록합니다.            |
| 지점 정보 수정    | PUT         | /admin/store                 | 가맹점 정보를 수정합니다.          |
| 지점 삭제    | DELETE         | /admin/store             | 계약이 끝났거나 경고 횟수가 일정 수준을 넘어간 가맹점을 비활성화 시킵니다. |
| 카테고리 신규 등록       | POST       | /admin/category        | 메뉴 카테고리를 새로 등록합니다.       |
| 카테고리 수정        | PUT         | /admin/category      | 카테고리 정보를 수정합니다.             |
| (카테고리 별) 메뉴 목록 조회        | GET         | /admin/menu/{categoryId}      | 카테고리 별로 메뉴 목록을 조회합니다.      |
| 메뉴 신규 등록     | POST         | /admin/menu           | 관리자가 메뉴를 새로 등록합니다.          |
| 메뉴 수정       | PUT        | /admin/menu                    | 관리자가 메뉴 정보를 수정합니다.            |
| 메뉴 단종 (DELETE)    | DELETE         | /admin/menu                 | 더 이상 판매하지 않는 메뉴를 비활성화 시킵니다.           |
| 테이블 오더 로그인    | POST         | /to            | 테이블 오더 기기에 로그인합니다. 이때 테이블 번호와 이름도 함께 등록합니다. |
| 테이블 오더 관리자 모드 로그인       | POST       | /to/admin       | 테이블 오더 관리자 모드 로그인합니다(실제로는, 입력한 아이디와 비밀번호가 일치하는지 여부만 조회)       |
| 테이블 오더 비활성화        | PUT         | /to/admin      | 선택한 테이블을 비활성화합니다.              |
| 테이블오더 주문 내역 조회        | GET         | /to/order?tableId={tableId}     | 해당 테이블에서 현재까지 주문한 내역을 조회합니다(결제 전 항목만 조회)     |
| 테이블 오더 메뉴 목록 조회     | GET         | /to/order          | 현재 가맹점에서 판매하는 메뉴 목록을 조회합니다.           |
| 테이블 오더 메뉴 단일(상세) 조회       | GET        | /to/menu?categoryId={categoryId}                    | 선택한 메뉴의 상세 정보를 확인합니다.            |
| 테이블 오더 주문 신규 등록    | POST         | /to/menu?menuId={menuId}                 | 테이블 오더에서 신규 주문을 진행합니다.           |
| 신규 발주 목록 조회    | GET         | /api/order             | (본사 측에서) 신규 발조 목록을 조회합니다. |
| 월별 매출 조회        | GET         | /api/sales?year={year}&month={month}       | (본사 측에서) query param으로 입력한 연도, 월별 매출을 조회합니다.                |

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
