<p align="center">
 <img src="https://github.com/user-attachments/assets/c532138c-db7b-4b35-9f3d-10f3a7fccf0d">
</p>
 <h3> 테이블 오더 & 가맹점 & 본사(인트라넷) 연계 프로젝트 </h3></div>
<div align="center"></div>

# 테이블 오더 & 가맹점 개발 프로젝트

<br>

**목차**
1. [프로젝트 소개](#-프로젝트-소개)
2. [제작기간 & 팀원 소개](#-제작기간-&-팀원-소개개)
3. [⛏ 기술 Stack](#-기술-Stack)
4. [🌸 아키텍쳐](#-아키텍쳐)
5. [⚙️ ERD](#⚙-ERD)
6. [🌸 API 설계](#-API-설계)
7. [📔 API 명세서](#-API-명세서)
8. [✔ 주요 기능](#-주요-기능)
9. [🖼️ 스크린샷 (모바일)](#🖼-스크린샷-(모바일))
10. [🌋 트러블 슈팅](#-트러블-슈팅)

<br>

---
### 📌 프로젝트 소개
- 테이블 오더 & 가맹점 & 본사(인트라넷) 연계 프로젝트

<p align="center">
 <img src="https://github.com/user-attachments/assets/a0ec6284-c453-464c-8a17-004268c6cd9a">
</p>
 
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

## ⛏ 기술 Stack

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
- JPA(Hibernate)
- springdoc(Swagger) 2.5.0

###### Mobile Stack
- Kotlin 1.9.0
- Jetpack Compose
- Retrofit2, okthttp3, gson
- Database : Room

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

<details>
<summary>API 목록 (총 36개)</summary>
<div markdown="1">

 
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

</div>
</details>

## ✔ 주요 기능

<details>
<summary>주요 기능 목록</summary>
<div markdown="1">
 
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
    
</div>
</details>
<br>

## 🖼️ 스크린샷 (모바일)

<details>
<summary>테이블 오더(모바일) 앱 스크린</summary>
<div markdown="1">

### 테이블 등록
![3 PNG](https://github.com/user-attachments/assets/9644c5e2-3656-47f2-b24e-b38733025a22)


### 메뉴 목록 확인
![4 PNG](https://github.com/user-attachments/assets/ce3bd04a-196c-495b-af11-effc678325b2)
![7 PNG](https://github.com/user-attachments/assets/737e7b6c-8394-4213-9891-ac09f45a4519)


### 메뉴 상세 확인
![5 PNG](https://github.com/user-attachments/assets/6cb63bf5-48b9-4e9b-997d-acf6a8afb434)


### 장바구니 확인
![9 PNG](https://github.com/user-attachments/assets/ee18eb17-fac5-45ea-8828-67e9b367c0a9)


### 메뉴 주문 (현재 로직 상 '카드 결제'만 가능
![10 PNG](https://github.com/user-attachments/assets/f0cfce5b-acb7-4383-9ecb-36df9e2bda47)


<details>
<summary>주문 상태 따른 변화</summary>
<div markdown="1">

### 결제 플로우
![12 PNG](https://github.com/user-attachments/assets/db9672d0-19f5-4f24-b239-9d820d35402a)
![15 PNG](https://github.com/user-attachments/assets/fe4627a3-764c-44bf-bd69-85fe9534354c)


### 정상적으로 결제 & 서버에 정보 전송 완료
![16 PNG](https://github.com/user-attachments/assets/6cf3ebe6-0dd8-4984-b191-8426c78bb549)


### 재고 부족 시
![TO_CANT_ORDER PNG](https://github.com/user-attachments/assets/4e96fb39-4622-4fa8-af72-0a71abdca900)


### 서버 오류 발생 시
![TO_FAIL_ORDER PNG](https://github.com/user-attachments/assets/23eef1de-d8d6-48f3-bb0f-9eb9a489c09a)

 
</div>
</details>
<br>

### 주문 내역 확인
![17 PNG](https://github.com/user-attachments/assets/63868c8d-778f-4ae2-9ca2-fcab923eaced)


### 홀짝 게임 (메뉴 목록 화면에서 테이블 번호 10초 내로 5번 터치하면 플레이 가능)
![20 PNG](https://github.com/user-attachments/assets/d3fb6732-ee9d-46e0-a8d9-250edab6eb32)


### 테이블 등록 해제
![23 PNG](https://github.com/user-attachments/assets/b194c02e-1716-45ba-a91b-b753b5eb843d)

 
</div>
</details>
<br>

## 🌋 트러블 슈팅
<details>
<summary>임주연(ljy)</summary>
<div markdown="1">

- jpa repository 쿼리
    - No property 'no' found for type 'Order'; Traversed path: OrderMenu.order 에러
    - 원인: Order 엔티티에서 no라는 필드를 찾지 못해서 발생한 문제
        - Order 엔티티에서 실제로 사용되는 필드 이름을 사용해야 함
    - 해결
        - findByOrderNo에서 findByOrder_OrderNo로 수정
    - 특이사항
        - 해당 OrderMenu는 order_no(오라클 기준 컬럼명)를 fk로 가진 엔티티임, 즉 자식 엔티티에서 부모 엔티티 컬럼(예: pk)를 찾기 위해서는 부모 엔티티_부모 엔티티의 해당 컬럼 이름을 명시하는 식으로 jpa 메서드 명을 지정해야 하는 것으로 보임
- 스웨거 적용
    
    원인: No operations defined in spec! 메시지 표시되며 아무 api도 보이지 않음
    
    해결: application.proporties에 springdoc.packages-to-scan에 패키지값 제대로 세팅
    
- RequestBody null로 돌아오는 에러
    - 원인: 몰라…. ⇒ RequestBody를 spring이 아니라 swagger 걸 import
    - 해결: 했으면 좋겠다… ⇒ spring에서 제공해주는 걸로 import 문 변경
- store 정보 수정 뒤, 신규 레코드 생성했을 때 레코드 pk 번호가 수정 횟수만큼 건너뛰어지는 이슈
    - 원인: Builder로 객체 생성해서 해당 객체를 수정용 객체로 사용했는데, jpa의 경우 엔티티를 Builder를 이용해서 객체 생성하면 신규 객체를 생성하는 것이라고 무조건 생각하게 됨
    - 해결: 기존 db의 entity 객체 불러온 다음, setter로 수정
- (안드로이드) hilt 오류
    
    ```sql
    error: [Hilt]
      Unsupported metadata version. Check that your Kotlin version is >= 1.0: java.lang.IllegalStateException: Unsupported metadata version. Check that your Kotlin version is >= 1.0
      	at dagger.hilt.processor.internal.kotlin.KotlinMetadata.metadataOf(KotlinMetadata.java:200)
    ```
    
    위 에러를 비롯한 매우 다양한 오류들이 출몰
    
    - 문제: kotlin과 hilt의 버전 차이
    - 해결: kotlin version 1.9.0, hilt 2.48로 맞춤
        - 여담: 총 5시간 걸렸습니다 이젠 살의밖에 남아있지 않은 괴물이 되어버렸습니다…
- 주문 정보 가져오기 실패 : Text '2024-07-09T11:09:35.93592' could not be parsed at index 20
    - 원인: gson에서 LocalDateTime 형변환 못하는데 형변환 세팅해주니까 발생한 오류
- 관리자 로그인 시도 시 `서버와의 통신에 실패했습니다. : java.lang.RuntimeException: Unable to create instance of interface retrofit2.Call. Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.` 에러 발생
    - 원인: retrofit에서 suspend를 사용할 시, Call이 아닌 Response를 사용해야 하기에 발생한 오류
    - 해결: Call ⇒ Response로 고침
- 카테고리 정보 가져오기 실패 : Reading a state that was created after the snapshot was taken or in a snapshot that has not yet been applied
    - 상황: 바로 home screen 로드하면 안 뜨는데 로그인해서 넘어갈 때 뜸 share 문제인가?
    - 원인: 로그인하고 성공 뜬 다음에  `sharedPreferences` 에 데이터 저장하는데 바로 페이지 넘어가버려서 해당 데이터 null로 뜨는 이슈
    - 해결: delay 걸어서 바로 페이지 넘어가는 게 아니라, 적당한 시간 뒤에 넘어가도록, 그래서 데이터 저장까지 확보하고 home screen으로 넘어감

</div>
</details>
<br>

<details>
<summary>나소림(nsr)</summary>
<div markdown="1">
 
   - @RequestBody 애노테이션으로 받은 json 데이터가 Null
       - 에러 : not-null property references a null or transient value : com.nagane.franchise.menu.domain.Category.categoryName
       
       - 해결 : 어노테이션 import 잘못함
   - StackOverflowError
       - 에러 : [2024-06-30 14:55:08.076] [ERROR] [http-nio-9001-exec-1] org.apache.catalina.core.ContainerBase.[Tomcat].[localhost].[/].[dispatcherServlet] Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Handler dispatch failed: java.lang.StackOverflowError] with root cause
       java.lang.StackOverflowError: null
       
       - 원인 : 카테고리 엔티티 안에 메뉴 에티티 리스트 선언돼있고 메뉴 엔티티에 또 카테고리 엔티티가 선언돼있어서 무한반복으로 카테고리와 메뉴 정보를 불러옴
       
       - 해결 : @ToString 어노테이션에서 특정 컬럼 제거
   - 엔티티에 기본 생성자가 없다는 오류
       - 오류 : No default constructor for entity 'com.nagane.franchise.stoke.domain.Stock'
       - 원인 : 엔티티에 기본 생성자가 없어서
       - 해결 : @NoArgsConstructor 와 @AllArgsConstructor 어노테이션 붙여줌
   - 없는 부모를 값으로 넣으려고 해서 난 오류
       - 에러 : could not execute statement [ORA-02291: integrity constraint (NAGANE.FKIY2NYPGS7BM2W5RMLMVQKL1OU) violated - parent key not found
       ] [insert into purchase_order (order_date,price,quantity,state,stock_no,p_order_no) values (?,?,?,?,?,?)]; SQL [insert into purchase_order (order_date,price,quantity,state,stock_no,p_order_no) values (?,?,?,?,?,?)]; constraint [NAGANE.FKIY2NYPGS7BM2W5RMLMVQKL1OU]
       - 해결 : 부모를 만들어주고 실행
   - yarn start 안됨
       - 에러 : 'react-scripts'은(는) 내부 또는 외부 명령, 실행할 수 있는 프로그램, 또는
       배치 파일이 아닙니다.
       - 원인 : react-scripts라는 라이브러리(프로그램/명령)을 현재 경로에서 실행시킬수 없는 상황이기 때문
       - 해결 :  react-scripts모듈을 설치
           
           > yarn add global react-scripts
           > 
           
           > npm install -g react-script
           > 
   - 에러는 아니고 Middleware 경고
       - 경고
           - DeprecationWarning: 'onAfterSetupMiddleware' option is deprecated. Please use the 'setupMiddlewares' option.
           - 'onBeforeSetupMiddleware' option is deprecated. Please use the 'setupMiddlewares' option.
       - 뜻
           - **onAfterSetupMiddleware** 옵션은 더 이상 사용되지 않습니다. 대신 **setupMiddlewares** 옵션을 사용해야 합니다.
           - **onBeforeSetupMiddleware** 옵션도 더 이상 사용되지 않습니다. 마찬가지로 **setupMiddlewares** 옵션을 사용해야 합니다.
   - not null 컬럼인데 null 을 넣는다는 에러
       - 에러 : 복사 못함
       - 원인 : 카테고리 엔티티에서 state = 1이라고 지정해뒀지만 null 값으로 들어감
       - 해결 : state 변수에 @Builder.Default 어노테이션 붙이기
   - 직접 쿼리문으로 데이터 삽입 시 읽어오지 못함
       - commit 을 안함….
   - props 으로 값 안넘어감
       - 해결 : const CategoryForm = ({ toggleFormLayouts, changeCategory }) ⇒{}
       { toggleFormLayouts, changeCategory } 이 부분을 {toggleFormLayouts}, {changeCategory } 이렇게 씀
   - CORS 오류
       - 에러 : Access to XMLHttpRequest at 'http://localhost:9001/admin/category' from origin '[http://localhost:3001](http://localhost:3001/)' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
       - 이유 : 요청한 경로가 허용되지 않음
       - 해결 : WebConfig클래스의 addMapping 함수에 허용 경로를 모든 경로("/**")로 설정
   - 요청 데이터가 잘못 됨
       - 에러 : org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver Resolved [org.springframework.http.converter.HttpMessageNotReadableException: Required request body is missing: public org.springframework.http.ResponseEntity<? extends com.nagane.franchise.util.model.response.BaseResponseBody> com.nagane.franchise.store.api.StoreController.deleteStore(com.nagane.franchise.store.dto.store.StoreNoDto)]
       - 원인 : 지점 삭제시 서버는 Dto 로 요청을 받는데 프론트는 StoreNo(Long) 값을 넘겨줌
       - 해결 : StoreNo 을 객체에 담아서 보냄
</div>
</details>
<br>
