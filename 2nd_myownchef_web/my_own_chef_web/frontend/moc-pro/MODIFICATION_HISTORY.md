# 프로젝트 수정 이력 (Modification History)

**프로젝트:** My Own Chef Pro  
**시작일:** 2025년 11월 10일  
**현재 진행:** 로그인 시스템 구현 및 비밀번호 암호화 완료

---

## 📊 전체 진행 상황

### ✅ 완료된 단계
- **1단계:** Write 컴포넌트 점검 (6개 파일) ✅
- **2단계:** 모달 컴포넌트 점검 (6개 파일) ✅
- **3단계:** 자유게시판/공지사항 (4개 파일) ✅
- **4단계:** 편의점 조합 게시판 (3개 파일) ✅
- **5단계:** 요리도구 나눔 게시판 (2개 파일) ✅
- **6단계:** 레시피 게시판 (2개 파일) ✅
- **7단계:** 같이쇼핑 게시판 (1개 파일) ✅
- **8단계:** 편의점 전체 게시판 (1개 파일) ✅
- **9단계:** 관리자 게시판 (3개 파일) ✅
- **10단계:** 사용자 인증 (4개 파일) ✅
- **11단계:** 공통 컴포넌트 (5개 파일) ✅
- **12단계:** 공통 컴포넌트 Props 검증 (6개 파일) ✅
- **13단계:** 수정 모드 구현 (13개 파일) ✅
- **14단계:** 버그 수정 및 안정화 (4개 파일) ✅
- **15단계:** 마이페이지 기능 구현 (1개 파일) ✅
- **16단계:** 상세페이지 RSbtn 기능 구현 (5개 파일) ✅
- **17단계:** 로그인/로그아웃 강제 마운트 버그 수정 (2개 파일) ✅
- **18단계:** 작성자 권한 기반 수정/삭제 버튼 표시 (6개 파일) ✅
- **19단계:** 로그인 필수 기능 체크 로직 추가 (14개 파일) ✅
- **20단계:** 작성자에게 신청하기 버튼 숨김 처리 (2개 파일) ✅
- **21단계:** 신청하기 버튼 클릭 시 채팅 모달 연동 (3개 파일) ✅
- **22단계:** 완료 상태일 때 신청하기 버튼 숨김 처리 (2개 파일) ✅
- **23단계:** 마이페이지 프로필 이미지 보존 문제 해결 (1개 파일) ✅
- **24단계:** 로그인 시스템 구현 (Spring Boot + React) ✅
  - 백엔드: 9개 파일 (VO, DAO, Service, Controller, Config, XML)
  - 프론트엔드: 1개 파일 (authUtils.js)
  - 데이터베이스: TB_USER 테이블, admin 계정 생성
  - BCrypt 비밀번호 암호화 적용
- **25단계:** 회원가입 시스템 구현 (Spring Boot + React) ✅
  - 백엔드: 4개 파일 (Controller, Service, DAO, XML)
  - 프론트엔드: 2개 파일 (RegisterForm.js, authUtils.js)
  - 기능: 회원가입, 아이디/이메일/닉네임 중복 확인
- **26단계:** User 모듈 Backend API 전체 구현 ✅
  - 백엔드: 6개 파일 (Controller, Service, DAO, XML)
  - 프론트엔드: 3개 파일 (MyPage.js, FindAccount.js, authUtils.js)
  - 기능: 사용자 정보 조회/수정, 회원 탈퇴, 아이디 찾기, 비밀번호 재설정
  - API 경로 수정: 인터페이스와 구현체 경로 일치
  - 에러 처리 개선: 중복 에러 시 구체적 메시지 표시
- **27단계:** 파일 업로드 시스템 및 이미지 처리 구현 ✅
  - 백엔드: 8개 파일 (FileUploadService, ImageVO, UserController, UserService, UserDAO, User.xml, WebConfig, application.properties)
  - 프론트엔드: 2개 파일 (HeaderLoginForm.js, MyPage.js)
  - 기능: 프로필 이미지 업로드, 기본 이미지 자동 반환, Placeholder 이미지 (동적 크기)
  - Static Resource 매핑, 외부 업로드 폴더 설정
  - 이미지 표준 크기: 카드 250x250, 상세 1000x800
  - IDENTITY 컬럼 사용으로 시퀀스 문제 해결

- **28단계:** 자유게시판 Backend API 전체 구현 ✅
  - 백엔드: 7개 파일 (VO, DAO, Service, Controller, XML)
  - 프론트엔드: 4개 파일 (axios 연동)
  - 기능: 목록/상세/작성/수정/삭제, 댓글 추가/삭제, 검색, 페이징
- **29단계:** 공지사항 Backend API 전체 구현 ✅
  - 백엔드: 7개 파일 (VO, DAO, Service, Controller, XML)
  - 프론트엔드: 3개 파일 (axios 연동)
  - 기능: 목록/상세/작성/수정/삭제, 관리자 권한 체크, 페이징
- **30단계:** 레시피 게시판 Backend API 전체 구현 ✅
  - 백엔드: 12개 파일 (VO 5개, DAO 2개, Service 2개, Controller 2개, XML 1개)
  - 프론트엔드: 3개 파일 (axios 연동, FormData 사용)
  - 기능: 목록/검색/상세/작성/수정/삭제, 재료/순서/이미지/댓글 관리
  - 특이사항: 여러 이미지 업로드, 재료/순서 배열 처리, JSON + Multipart 동시 처리

- **31단계:** 편의점 리뷰 게시판 Backend API 전체 구현 ✅
  - 백엔드: 11개 파일 (VO 3개, DAO, Service, Controller, XML)
  - 프론트엔드: 3개 파일 (목록, 상세, 작성)
  - 기능: 편의점/카테고리 필터, 여러 이미지, 가격 정보, 댓글
  - API: 목록/상세/작성/수정/삭제, 댓글 관리, 작성자 확인

- **32단계:** 편의점 조합(파먹기/레시피) Backend API 전체 구현 ✅
  - 백엔드: 12개 파일 (VO 5개, DAO 2개, Service 2개, Controller 2개, XML 1개)
  - 프론트엔드: 3개 파일 (목록, 상세, 작성)
  - 기능: 카테고리 필터, 검색+카테고리 동시 적용, 제품/레시피 배열, 여러 이미지
  - API: 목록/검색/상세/작성/수정/삭제, 댓글 관리, 작성자 확인

- **33단계:** 실시간 채팅 시스템 설계 및 계획 수립 ✅
  - 웹소켓(WebSocket) 도입 결정
  - 현재 구조 영향 최소화 (ChatModal만 수정)
  - 단계적 진행: ShareTool/WithShopping API 먼저 → 채팅 REST API → 웹소켓
  - 예상 작업: Backend 8~10개 파일, Frontend 1개 파일 (ChatModal.js)
  - DB 변경 불필요 (현재 TB_CHAT_ROOM, TB_CHAT_MESSAGE 구조 완벽)

- **34단계:** 요리나눔(ShareTool) Backend API 전체 구현 ✅
  - 백엔드: 11개 파일 (VO 2개, DAO 2개, Service 2개, Controller 2개, XML 1개)
  - 프론트엔드: 3개 파일 (목록, 상세, 작성)
  - 기능: 목록/검색/상세/작성/수정/삭제, 지역 필터, 상태 필터, 여러 이미지, 나눔완료 처리
  - API: 목록/검색/상세/작성/수정/삭제, 나눔완료, 작성자 확인

- **35단계:** 같이쇼핑(WithShopping) Backend API 전체 구현 ✅
  - 백엔드: 7개 파일 (VO, DAO, Service, Controller, XML)
  - 프론트엔드: 2개 파일 (목록, 상세)
  - 기능: 목록/검색/상세/작성/수정/삭제, 지역 필터, 상태 필터, 모집완료 처리
  - API: 목록/검색/상세/작성/수정/삭제, 모집완료, 작성자 확인

- **36단계:** 실시간 채팅 시스템 구현 (REST API + WebSocket) ✅
  - 백엔드: 10개 파일 (VO 2개, DAO 2개, Service 2개, Controller 2개, Config 1개, XML 1개)
  - 프론트엔드: 3개 파일 (ChatModal.js, ShareToolDetail.js, ShoppingBoardDetailModal.js)
  - 기능: 
    * REST API: 채팅방 생성/조회, 메시지 목록, 수락/거절/숨김
    * WebSocket: 실시간 메시지 송수신, 브로드캐스트
    * 수락 시 ShareTool/WithShopping 상태 자동 완료 처리
  - 연동: ShareToolService.completeShare(), WithShoppingService.completeShopping()
  - 라이브러리: sockjs-client, @stomp/stompjs, spring-boot-starter-websocket
  - 지침서 준수: VO만 사용 (DTO 삭제), Controller에 REST + WebSocket 통합

### ⏭️ 다음 단계
- 관리자 게시글 관리 Backend API 구현
- 관리자 신고 관리 Backend API 구현
- 관리자 회원 관리 Backend API 구현

---

## 📝 상세 수정 이력

### 37단계: ShareTool/WithShopping 상태/삭제 정책 반영 ✅

**수정일:** 2025-11-14  
**목표:** 1-A, 2-A, 3-B, 4-C, 5-A 결정사항 반영 (상태 코드 저장 정책은 추후 단계적으로 전환 예정)

**변경 파일:**
- backend/pro/src/main/resources/mybatis/mappers/WithShopping.xml ✅
- backend/pro/src/main/resources/mybatis/mappers/ShareTool.xml ✅
- backend/pro/src/main/java/com/moc/pro/sharetool/controller/ShareToolControllerImpl.java ✅
- frontend/moc-pro/src/components/with_shopping_board/ShoppingBoard.js ✅

### 38단계: 같이장보기/요리도구 나눔 초기 전체 조회 구현 ✅

**수정일:** 2025-11-14  
**목표:** 최초 입장 시 지역 필터 미적용 상태로 모든 비삭제 게시물 노출 (요구사항: 초기 필터 자동 적용 제거)

**변경 파일:**
- frontend/moc-pro/src/components/with_shopping_board/ShoppingBoard.js ✅
- frontend/moc-pro/src/components/share_tool_board/ShareBoard.js ✅

**주요 변경 요약:**
- 자동 시/구 초기값 설정 로직 제거 (두 컴포넌트 모두)
- 빈 문자열(si, gu)일 경우 요청 파라미터에서 제외해 전체 목록 조회 (백엔드 WHERE 조건에서 빈/NULL 무시 활용)
- '전체 시/도', '전체 시/군/구' 선택 옵션 추가
- useEffect 의존성 재구성: 필터 선택/페이지 변경 시 재조회
- ShareBoard: 구 선택 비활성(disabled) 처리 (시/도 미선택 시)
- 린트 경고 해결: 중복 조건 제거, optional chaining 적용, parseInt → Number.parseInt

**핵심 패치 스니펫 (ShoppingBoard):**
```diff
- // 지역 데이터 로딩 완료 시 초기값 설정 (삭제)
+ // 최초 진입 전체 조회 위해 초기 지역 자동 설정 제거
...
 const params = { page };
 if (data.si) params.si = data.si;
 if (data.gu) params.gu = data.gu;
 params.status = '';
```

**핵심 패치 스니펫 (ShareBoard):**
```diff
- useEffect 자동 시/구 세팅 제거
- province/city 파라미터는 선택된 경우에만 추가
+ const params = { page, status: '' };
+ if (selectedSido) params.province = selectedSido;
+ if (selectedGu) params.city = selectedGu;
```

**테스트 시나리오:**
1. 최초 `/withshopping?page=1` 진입 → 모든 게시글 노출 (지역 필터 비어있음)
2. 시/도 선택 후 구 선택 → 해당 지역으로 필터 적용됨
3. 시/도 다시 '전체'로 변경 → 전체 목록 재노출
4. 동일 흐름 `/sharetool?page=1` 에서도 재현

**체크리스트:**
- [x] 초기 전체 목록 노출
- [x] 선택 후 필터 적용 정상
- [x] 삭제글 제외(백엔드 필터 유지)
- [x] 린트 경고 제거 또는 무해화

**추가 고려:** 상태 코드(공통코드 ID) 전환 시에도 빈 필터 처리 로직 유지 필요. 후속 단계에서 공통코드 매핑 시 파라미터 직렬화 유틸 추가 예정.

### 39단계: 요리도구 나눔 검색어 미입력 시 전체 조회 지원 ✅

**수정일:** 2025-11-14  
**변경 파일:** `ShareBoard.js`  
**목표:** 기존 검색어 미입력 시 alert 강제 → UX 개선 (빈 검색은 전체 결과로 간주)

**주요 변경:**
- `handleSearch` 로직 분기 추가: `searchKeyword.trim() === ''` 일 때 `list` 엔드포인트 호출
- 기존 alert 제거, 페이지 1로 이동하며 목록/totalPage 동기화
- 선택된 지역(province/city)만 파라미터에 포함(빈 값은 전체)

**핵심 코드 스니펫:**
```diff
- if (!searchKeyword.trim()) { alert('검색어를 입력해주세요.'); return; }
+ if (!searchKeyword.trim()) {
+   const listParams = { page: 1, status: '' };
+   if (selectedSido) listParams.province = selectedSido;
+   if (selectedGu) listParams.city = selectedGu;
+   const listResp = await axios.get('/api/sharetool/list', { params: listParams });
+   ...
+   return;
+ }
```

**검증 시나리오:**
1. 빈 검색어 + 지역 미선택 → 전체(삭제 제외) 목록 정상 표시
2. 빈 검색어 + 시/구 선택 → 해당 지역 필터 목록 표시
3. 검색어 입력 + 지역 선택/미선택 → 기존 검색 동작 유지
4. 페이지네이션 유지: 검색 직후 `page=1`으로 리셋

**체크리스트:**
- [x] 빈 검색 전체 목록
- [x] 필터 조합(시/도, 구) 반영
- [x] 기존 검색 영향 없음
- [x] 불필요 alert 제거

**향후 과제:** 검색 조건 다중(제품명/상태) 추가 시 동일 패턴(빈 → 전체) 재사용할 유틸 함수로 분리 가능.

**주요 변경:**
- ✅ 소프트 삭제 적용(3-B): 물리 삭제 → 상태값 'DELETED'로 업데이트
- ✅ 목록/검색/개수/상세에서 삭제글 제외(2-A): WHERE 절에 `STATUS != 'DELETED'` 추가
- ✅ 라우팅 보정(5-A): ShareToolControllerImpl에 `@RequestMapping("/api/sharetool")` 추가
- ✅ 상세 404 처리: 삭제글 상세 조회 시 404 반환(ShareTool)
- ✅ 프런트 상태 뱃지 스타일 보정: '모집중' → 진행 스타일, 나머지 → 완료 스타일

**핵심 코드 (Before → After):**

1) WithShopping.xml 삭제 쿼리
```xml
- <delete id="deleteWithShopping">DELETE FROM TB_WITHSHOPPING ...</delete>
+ <update id="deleteWithShopping">UPDATE TB_WITHSHOPPING SET WITHSHOPPING_STATUS='DELETED', UPDATED_AT=SYSTIMESTAMP WHERE WITHSHOPPING_ID=...</update>
```

2) ShareTool.xml 삭제 쿼리
```xml
- <delete id="deleteShareTool">DELETE FROM TB_SHARETOOL ...</delete>
+ <update id="deleteShareTool">UPDATE TB_SHARETOOL SET SHARETOOL_STATUS='DELETED', UPDATED_AT=SYSTIMESTAMP WHERE SHARETOOL_ID=...</update>
```

3) 목록/검색/개수/상세 공통 필터
```xml
AND <TABLE_ALIAS>.STATUS != 'DELETED'
```

4) ShareToolControllerImpl 라우팅 보정
```java
@Controller
@RequestMapping("/api/sharetool")
public class ShareToolControllerImpl implements ShareToolController { ... }
```

5) ShareTool 상세 404
```java
Map<String, Object> result = shareToolService.getDetail(id);
if (result == null || result.get("shareTool") == null) return 404;
```

6) ShoppingBoard.js 상태 버튼 스타일
```javascript
- post.withShoppingStatus === '모집중' ? style.status_button_done : style.status_button_ing
+ post.withShoppingStatus === '모집중' ? style.status_button_ing : style.status_button_done
```

**비고:**
- 1-A(공통코드 ID 저장)는 DB/응답 전반에 영향이 커서, 호환 단계(라벨↔코드 병행) 설계 후 순차 적용 예정. 현재는 'DELETED' 라벨만 선적용하여 정책 충돌 없이 운영 가능.

**검토 체크리스트:**
- [x] 소프트 삭제로 전환
- [x] 삭제글 목록/상세 노출 차단
- [x] 라우팅 보정 완료
- [x] 프런트 상태 표시 보정
- [x] 빌드 오류 없음 (일반 린트 경고는 차후 리팩토링 단계에서 일괄 처리)

### 34단계: 요리나눔(ShareTool) Backend API 구현 🔄

#### 개요
**목적:** 요리나눔 게시판 Backend API 전체 구현

**수정일:** 2025-11-13

**진행 상태:** 준비 중

---

#### 계획 사항

##### 1. DB 테이블 구조 (확인 완료)

**TB_SHARETOOL (요리도구 나눔 게시글):**
- SHARETOOL_ID (NUMBER, PK, IDENTITY)
- USER_ID (VARCHAR2, 255) - 작성자
- SHARETOOL_TITLE (VARCHAR2, 50) - 제품 카테고리 (전자레인지, TV, 냄비 등)
- SHARETOOL_PRODUCT (VARCHAR2, 255) - 제품 상세이름
- SHARETOOL_CONTENT (CLOB) - 상세 내용
- SHARETOOL_STATUS (VARCHAR2, 50) - 나눔중/나눔완료
- SHARETOOL_PROVINCE (VARCHAR2, 50) - 시/도
- SHARETOOL_CITY (VARCHAR2, 50) - 시/군/구

**TB_SHARETOOL_IMAGE (이미지):**
- SHARETOOL_IMAGE_ID (NUMBER, PK, IDENTITY)
- SHARETOOL_ID (NUMBER)
- SHARETOOL_IMAGE_URL (VARCHAR2, 500)
- SHARETOOL_IMAGE_PATH (VARCHAR2, 500)
- SHARETOOL_IMAGE_INDEX (NUMBER) - 이미지 순서

##### 2. Backend 구현 예정 (11개 파일)

**VO (2개):**
- ShareToolVO.java
- ShareToolImageVO.java

**DAO (2개):**
- ShareToolDAO.java (인터페이스)
- ShareToolDAOImpl.java (구현체)

**Service (2개):**
- ShareToolService.java (인터페이스)
- ShareToolServiceImpl.java (구현체)

**Controller (2개):**
- ShareToolController.java (인터페이스)
- ShareToolControllerImpl.java (구현체)

**Mapper (1개):**
- ShareTool.xml

**API 목록 (9개 예정):**
```
GET  /api/sharetool/list?province={}&city={}&status={}&page={}  // 목록 (지역/상태 필터)
GET  /api/sharetool/search?keyword={}&province={}&city={}&page={}  // 검색
GET  /api/sharetool/{id}  // 상세 (이미지 포함)
GET  /api/sharetool/{id}/check-author  // 작성자 확인
POST /api/sharetool  // 작성 (FormData, 여러 이미지)
PUT  /api/sharetool/{id}  // 수정
DELETE /api/sharetool/{id}  // 삭제 (게시글 + 이미지)
POST /api/sharetool/{id}/complete  // 나눔 완료 처리 (채팅 수락 시 호출)
```

##### 3. Frontend 연동 (기존 파일 수정)

**수정 대상:**
- ShareBoard.js (목록 - axios 연동)
- ShareToolDetail.js (상세 - axios 연동, 채팅 연결 확인)
- ShareToolWrite.js (작성/수정 - axios 연동, FormData)

##### 4. 핵심 기능

- ✅ **지역 필터:** province(시/도), city(시/군/구)
- ✅ **상태 필터:** 나눔중, 나눔완료
- ✅ **검색:** 키워드 + 지역 동시 필터
- ✅ **다중 이미지:** 최대 4개 업로드
- ✅ **채팅 연동:** 신청하기 버튼 → 채팅방 생성
- ✅ **상태 자동 변경:** 채팅에서 수락 시 → 나눔완료

---

### 33단계: 실시간 채팅 시스템 설계 및 계획 수립 ✅

#### 개요
**목적:** 웹소켓(WebSocket) 도입 결정 및 구현 계획 수립

**작성일:** 2025-11-13

---

#### 분석 결과

##### 1. 현재 구조 분석 완료

**ChatModal.js (현재):**
- 새로고침 방식 (실시간 아님)
- useEffect로 샘플 데이터 표시
- 수동으로 메시지 추가

**연결 포인트:**
- ShareToolDetail.js: handleRequest → ChatModal 열기
- ShoppingBoardDetailModal.js: handleApply → ChatModal 열기

**DB 테이블 (완벽한 구조):**
- TB_CHAT_ROOM (채팅방)
- TB_CHAT_MESSAGE (메시지, MESSAGE_TYPE 포함)
- TB_CHAT_ROOM_HIDDEN (개별 숨김)

##### 2. 웹소켓 도입 시 변경 범위

**Backend (8~10개 파일 신규 생성):**
- WebSocketConfig.java (1개)
- Chat 모듈 (7~9개)
  - ChatController + Impl (WebSocket 메시지 핸들러)
  - ChatService + Impl
  - ChatDAO + Impl
  - ChatRoomVO, ChatMessageVO, ChatRoomHiddenVO
  - Chat.xml

**Frontend (1개 파일 수정):**
- ChatModal.js (약 100줄 수정)
  - SockJS, STOMP 클라이언트 추가
  - WebSocket 연결 로직
  - 실시간 메시지 수신/전송

**DB:**
- ❌ 변경 불필요 (현재 구조 완벽)

**기존 코드 연관성:**
- ✅ ShareTool/WithShopping 로직 변경 불필요
- ✅ ChatModal만 집중 수정
- ✅ 채팅방 생성 API는 REST 유지

##### 3. 단계적 구현 계획

**1단계: ShareTool/WithShopping Backend API 구현** (우선)
- 기본 CRUD API 완성
- 채팅방 생성은 간단히 mock

**2단계: 채팅 REST API 구현**
- POST /api/chat/rooms (채팅방 생성/조회)
- GET /api/chat/rooms/{roomId}/messages
- POST /api/chat/rooms/{roomId}/hide

**3단계: 웹소켓 실시간 채팅 구현**
- Backend WebSocket 설정
- ChatModal.js 웹소켓 연동
- 실시간 메시지 송수신

##### 4. 예상 작업 시간

- Backend WebSocket: 8~12시간
- Frontend 수정: 4~6시간
- 테스트 및 디버깅: 6~8시간
- **총 예상: 18~26시간 (2~3일)**

##### 5. 난이도 평가

- Backend: ⭐⭐⭐⭐ (중상) - WebSocket 설정 까다로움
- Frontend: ⭐⭐⭐ (중) - STOMP 클라이언트 이해 필요
- 전체: ⭐⭐⭐⭐ (중상) - 첫 도입 시 시행착오 예상

---

### 32단계: 편의점 조합(파먹기/레시피) Backend API 전체 구현 ✅

#### 개요
**목적:** 편의점 조합 게시판 Backend API 및 Frontend 완성

**수정일:** 2025-11-12

---

#### 1. Backend API 구현 (Spring Boot) - 12개 파일

##### 1-1. ConvRecipe VO 클래스들 ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/convrecipe/vo/`

**생성된 파일 (5개):**
- **ConvRecipeVO.java** - 편의점 조합 기본 정보
- **ConvRecipeCommentVO.java** - 조합 댓글
- **ConvRecipeProductVO.java** - 상세 준비 제품
- **ConvRecipeOrderVO.java** - 레시피 순서
- **ConvRecipeImageVO.java** - 조합 이미지

**ConvRecipeVO 주요 필드:**
```java
private int convRecipeId;                  // 조합 ID
private String userId;                     // 작성자 ID
private String convRecipeTitle;            // 제목
private String convRecipeMainProduct;      // 핵심 제품
private String convRecipeCategory;         // 카테고리 (식사류, 간식류, 음료류)
private String convRecipeTip;              // 팁 & 후기
private String userNickname;               // 작성자 닉네임 (조인용)
```

---

##### 1-2. ConvRecipeDAO + ConvRecipeDAOImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/convrecipe/dao/`

**구현 메서드 (총 21개):**
```java
// 조합 게시글 (9개)
List<ConvRecipeVO> selectListByCategory(Map<String, Object> params);
int selectTotalCountByCategory(String category);
List<ConvRecipeVO> searchByKeywordAndProduct(Map<String, Object> params);
int selectSearchCount(Map<String, Object> params);
ConvRecipeVO selectById(int convRecipeId);
int insertConvRecipe(ConvRecipeVO convRecipe);
int updateConvRecipe(ConvRecipeVO convRecipe);
int deleteConvRecipe(int convRecipeId);
int checkAuthor(Map<String, Object> params);

// 제품 (3개)
List<ConvRecipeProductVO> selectProductsByRecipeId(int convRecipeId);
int insertProduct(ConvRecipeProductVO product);
int deleteProductsByRecipeId(int convRecipeId);

// 레시피 순서 (3개)
List<ConvRecipeOrderVO> selectOrdersByRecipeId(int convRecipeId);
int insertOrder(ConvRecipeOrderVO order);
int deleteOrdersByRecipeId(int convRecipeId);

// 이미지 (3개)
List<ConvRecipeImageVO> selectImagesByRecipeId(int convRecipeId);
int insertImage(ConvRecipeImageVO image);
int deleteImagesByRecipeId(int convRecipeId);

// 댓글 (4개)
List<ConvRecipeCommentVO> selectCommentsByRecipeId(int convRecipeId);
int insertComment(ConvRecipeCommentVO comment);
int deleteComment(int commentId);
int checkCommentAuthor(Map<String, Object> params);
```

---

##### 1-3. ConvRecipe.xml ✅
**경로:** `backend/pro/src/main/resources/mybatis/mappers/ConvRecipe.xml`

**주요 쿼리:**
```xml
<!-- 목록 조회 (카테고리 필터, 페이징) -->
<select id="selectListByCategory">
    SELECT cr.*, u.USER_NICKNAME,
           (SELECT CRI.CONV_RECIPE_IMAGE_URL
            FROM TB_CONV_RECIPE_IMAGE CRI
            WHERE CRI.CONV_RECIPE_ID = cr.CONV_RECIPE_ID
              AND ROWNUM = 1
            ORDER BY CRI.CONV_RECIPE_IMAGE_INDEX ASC) AS conv_recipe_content
    FROM TB_CONV_RECIPE cr
    LEFT JOIN TB_USER u ON cr.USER_ID = u.USER_ID
    WHERE cr.CONV_RECIPE_CATEGORY = #{category}
    ORDER BY cr.CREATED_AT DESC
    OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY
</select>

<!-- 검색 (키워드 + 제품명 + 카테고리) -->
<select id="searchByKeywordAndProduct">
    SELECT cr.*, u.USER_NICKNAME,
           (SELECT CRI.CONV_RECIPE_IMAGE_URL
            FROM TB_CONV_RECIPE_IMAGE CRI
            WHERE CRI.CONV_RECIPE_ID = cr.CONV_RECIPE_ID
              AND ROWNUM = 1
            ORDER BY CRI.CONV_RECIPE_IMAGE_INDEX ASC) AS conv_recipe_content
    FROM TB_CONV_RECIPE cr
    LEFT JOIN TB_USER u ON cr.USER_ID = u.USER_ID
    WHERE 
        (cr.CONV_RECIPE_TITLE LIKE '%' || #{keyword} || '%'
         OR cr.CONV_RECIPE_MAIN_PRODUCT LIKE '%' || #{keyword} || '%')
        AND cr.CONV_RECIPE_MAIN_PRODUCT LIKE '%' || #{product} || '%'
        AND cr.CONV_RECIPE_CATEGORY = #{category}
    ORDER BY cr.CREATED_AT DESC
    OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY
</select>

<!-- 제품 조회 -->
<select id="selectProductsByRecipeId">
    SELECT * FROM TB_CONV_RECIPE_PRODUCT
    WHERE CONV_RECIPE_ID = #{convRecipeId}
    ORDER BY CONV_RECIPE_PRODUCT_INDEX ASC
</select>

<!-- 레시피 순서 조회 -->
<select id="selectOrdersByRecipeId">
    SELECT * FROM TB_CONV_RECIPE_ORDER
    WHERE CONV_RECIPE_ID = #{convRecipeId}
    ORDER BY CONV_RECIPE_ORDER_INDEX ASC
</select>

<!-- 이미지 조회 -->
<select id="selectImagesByRecipeId">
    SELECT * FROM TB_CONV_RECIPE_IMAGE
    WHERE CONV_RECIPE_ID = #{convRecipeId}
    ORDER BY CONV_RECIPE_IMAGE_INDEX ASC
</select>

<!-- useGeneratedKeys 사용 (IDENTITY 컬럼) -->
<insert id="insertConvRecipe" parameterType="ConvRecipeVO" 
        useGeneratedKeys="true" keyProperty="convRecipeId">
    INSERT INTO TB_CONV_RECIPE (...)
    VALUES (...)
</insert>
```

---

##### 1-4. ConvRecipeService + ConvRecipeServiceImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/convrecipe/service/`

**주요 메서드:**
```java
// 목록/검색 (페이징, 카테고리 필터)
Map<String, Object> getListByCategory(String category, int page);
Map<String, Object> searchRecipes(String keyword, String product, String category, int page);

// 상세 조회 (제품/레시피/이미지 포함)
Map<String, Object> getDetail(int convRecipeId);

// 작성/수정/삭제 (MultipartFile[] 처리)
boolean createConvRecipe(ConvRecipeVO convRecipe, String products, String orders, MultipartFile[] images);
boolean updateConvRecipe(ConvRecipeVO convRecipe, String products, String orders, MultipartFile[] images);
boolean deleteConvRecipe(int convRecipeId);

// 작성자 확인
boolean checkAuthor(int convRecipeId, String userId);

// 댓글
List<ConvRecipeCommentVO> getComments(int convRecipeId);
boolean createComment(ConvRecipeCommentVO comment);
boolean deleteComment(int commentId, String userId);
```

**핵심 로직:**
```java
@Override
@Transactional
public boolean createConvRecipe(ConvRecipeVO convRecipe, String productsJson, String ordersJson, MultipartFile[] images) {
    // 1. 조합 기본 정보 저장
    convRecipeDAO.insertConvRecipe(convRecipe);
    int recipeId = convRecipe.getConvRecipeId(); // useGeneratedKeys 사용
    
    // 2. 제품 저장 (JSON 파싱)
    List<Map<String, String>> productList = objectMapper.readValue(productsJson, ...);
    int productIndex = 1;
    for (Map<String, String> item : productList) {
        ConvRecipeProductVO product = new ConvRecipeProductVO();
        product.setConvRecipeId(recipeId);
        product.setConvRecipeProductContent(item.get("content"));
        product.setConvRecipeProductIndex(productIndex++);
        convRecipeDAO.insertProduct(product);
    }
    
    // 3. 레시피 순서 저장
    List<Map<String, String>> orderList = objectMapper.readValue(ordersJson, ...);
    // 동일한 방식으로 처리
    
    // 4. 이미지 파일 업로드 및 저장
    if (images != null && images.length > 0) {
        int imageIndex = 1;
        for (MultipartFile file : images) {
            if (file.isEmpty()) continue;
            
            ImageVO uploadResult = fileUploadService.uploadImage(file, "conv-recipe");
            
            ConvRecipeImageVO image = new ConvRecipeImageVO();
            image.setConvRecipeId(recipeId);
            image.setConvRecipeImageUrl(uploadResult.getUrl());
            image.setConvRecipeImagePath(uploadResult.getPath());
            image.setConvRecipeImageIndex(imageIndex++);
            
            convRecipeDAO.insertImage(image);
        }
    }
    
    return true;
}
```

**목록 조회 시 대표 이미지 처리:**
```java
for (ConvRecipeVO recipe : posts) {
    if (recipe.getConvRecipeContent() == null || recipe.getConvRecipeContent().isEmpty()) {
        recipe.setConvRecipeContent(
            fileUploadService.getNoImageUrl(250, 250)
        );
    }
}
```

---

##### 1-5. ConvRecipeController + ConvRecipeControllerImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/convrecipe/controller/`

**구현 API (10개):**
```java
// 게시글
GET  /api/conv-recipe/list?category={category}&page={page}                         // 목록 (카테고리 필터)
GET  /api/conv-recipe/search?keyword={}&product={}&category={}&page={}             // 검색
GET  /api/conv-recipe/{id}                                                          // 상세
GET  /api/conv-recipe/{id}/check-author                                             // 작성자 확인
POST /api/conv-recipe                                                               // 작성 (Multipart)
PUT  /api/conv-recipe/{id}                                                          // 수정 (Multipart)
DELETE /api/conv-recipe/{id}                                                        // 삭제

// 댓글
GET  /api/conv-recipe/{id}/comments                                                 // 댓글 목록
POST /api/conv-recipe/{id}/comments                                                 // 댓글 작성
DELETE /api/conv-recipe/comments/{commentId}                                        // 댓글 삭제
```

**작성 API (Multipart/form-data):**
```java
@PostMapping
@ResponseBody
public ResponseEntity<Map<String, Object>> createConvRecipe(
    @RequestParam String userId,
    @RequestParam String title,
    @RequestParam String mainProduct,
    @RequestParam String category,
    @RequestParam String tip,
    @RequestParam(required = false) String products,      // JSON 문자열
    @RequestParam(required = false) String orders,        // JSON 문자열
    @RequestParam(required = false) MultipartFile[] images,
    HttpSession session) {
    
    String sessionUserId = (String) session.getAttribute("userId");
    if (sessionUserId == null) {
        response.put("message", "로그인이 필요합니다.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
    
    ConvRecipeVO convRecipe = new ConvRecipeVO();
    convRecipe.setUserId(sessionUserId);
    convRecipe.setConvRecipeTitle(title);
    convRecipe.setConvRecipeMainProduct(mainProduct);
    convRecipe.setConvRecipeCategory(category);
    convRecipe.setConvRecipeTip(tip);
    
    boolean success = convRecipeService.createConvRecipe(convRecipe, products, orders, images);
    
    if (success) {
        response.put("success", true);
        response.put("message", "편의점 조합이 작성되었습니다.");
        return ResponseEntity.ok(response);
    }
}
```

---

#### 2. Frontend 연동 (React) - 3개 파일

##### 2-1. ConvenienceRecipeBoard.js ✅
**경로:** `frontend/moc-pro/src/components/conv_board/ConvenienceRecipeBoard.js`

**수정 내용:**
- ✅ axios 목록 조회 API 연동
- ✅ axios 검색 API 연동 (키워드 + 제품명 + 카테고리)
- ✅ 카테고리 탭 동적 필터링
- ✅ 검색 + 카테고리 동시 적용
- ✅ 이미지 URL 표시
- ✅ 페이징 처리

**핵심 코드:**
```javascript
// 목록 조회
useEffect(() => {
    const fetchData = async () => {
        try {
            const keyword = searchParams.get('keyword');
            const product = searchParams.get('product');
            const category = searchParams.get('category');
            
            if (keyword || product) {
                // 검색 모드
                const response = await axios.get('http://localhost:18880/api/conv-recipe/search', {
                    params: {
                        keyword: keyword || '',
                        product: product || '',
                        category: category || activeCategory,
                        page: page
                    }
                });
                
                if (response.data.success) {
                    setPosts(response.data.data.posts);
                    setTotalPage(response.data.data.totalPage);
                }
            } else {
                // 목록 모드
                const response = await axios.get('http://localhost:18880/api/conv-recipe/list', {
                    params: {
                        category: category || activeCategory,
                        page: page
                    }
                });
                
                if (response.data.success) {
                    setPosts(response.data.data.posts);
                    setTotalPage(response.data.data.totalPage);
                }
            }
        } catch (error) {
            console.error('데이터 로드 실패:', error);
        }
    };
    
    fetchData();
}, [page, activeCategory, searchParams]);

// 검색 + 카테고리 동시 적용
const handleSearch = async () => {
    try {
        const response = await axios.get('http://localhost:18880/api/conv-recipe/search', {
            params: {
                keyword: searchKeyword,
                product: searchProduct,
                category: activeCategory,
                page: 1
            }
        });
        
        if (response.data.success) {
            setPosts(response.data.data.posts);
            setTotalPage(response.data.data.totalPage);
            
            // URL 파라미터 업데이트
            const params = new URLSearchParams();
            params.set('page', '1');
            if (searchKeyword) params.set('keyword', searchKeyword);
            if (searchProduct) params.set('product', searchProduct);
            if (activeCategory) params.set('category', activeCategory);
            navigate(`/conv/recipe?${params.toString()}`);
        }
    } catch (error) {
        console.error('검색 실패:', error);
        alert('검색에 실패했습니다.');
    }
};

// 카테고리 버튼 클릭 시 검색 상태 유지
{category.map(categorysName => (
    <button onClick={() => {
        setActiveCategory(categorysName);
        // 검색 상태 유지하면서 필터링
        const params = new URLSearchParams();
        params.set('page', '1');
        if (searchKeyword) params.set('keyword', searchKeyword);
        if (searchProduct) params.set('product', searchProduct);
        params.set('category', categorysName);
        navigate(`/conv/recipe?${params.toString()}`);
    }}>
        {categorysName}
    </button>
))}
```

---

##### 2-2. ConvenienceCombDetail.js ✅
**경로:** `frontend/moc-pro/src/components/conv_board/ConvenienceCombDetail.js`

**수정 내용:**
- ✅ axios 상세 조회 API 연동 (제품/레시피/이미지 포함)
- ✅ axios 작성자 확인 API 연동
- ✅ axios 삭제 API 연동
- ✅ axios 댓글 목록 조회 API 연동
- ✅ 이미지 슬라이더 동작
- ✅ CommentBoard 연동 (boardType="conv-recipe")

**핵심 코드:**
```javascript
useEffect(() => {
    const fetchData = async () => {
        try {
            // 상세 정보 조회
            const detailResponse = await axios.get(`http://localhost:18880/api/conv-recipe/${postid}`);
            if (detailResponse.data.success) {
                const { recipe, products, orders, images } = detailResponse.data.data;
                
                setPost({
                    ...recipe,
                    mainingredient: recipe.convRecipeMainProduct,
                    ingredients: products.map(item => item.convRecipeProductContent),
                    recipes: orders.map(item => item.convRecipeOrderContent),
                    tip: recipe.convRecipeTip,
                    images: images.map(item => item.convRecipeImageUrl),
                    title: recipe.convRecipeTitle,
                    usernickname: recipe.userNickname,
                    createdate: new Date(recipe.createdAt).toLocaleDateString()
                });
            }
            
            // 작성자 확인
            const authorResponse = await axios.get(`http://localhost:18880/api/conv-recipe/${postid}/check-author`);
            if (authorResponse.data.success) {
                setIsAuthor(authorResponse.data.isAuthor);
            }
            
            // 댓글 조회
            const commentsResponse = await axios.get(`http://localhost:18880/api/conv-recipe/${postid}/comments`);
            if (commentsResponse.data.success) {
                setPost(prev => ({
                    ...prev,
                    comments: commentsResponse.data.comments
                }));
            }
            
        } catch (error) {
            console.error('데이터 조회 실패:', error);
        }
    };
    
    fetchData();
}, [postid, navigate]);

// 삭제
const handleDelete = async () => {
    if (window.confirm("정말 이 글을 삭제하시겠습니까?")) {
        try {
            const response = await axios.delete(`http://localhost:18880/api/conv-recipe/${postid}`);
            if (response.data.success) {
                alert('편의점 조합이 삭제되었습니다.');
                navigate(`/conv/recipe`);
            }
        } catch (error) {
            console.error('삭제 실패:', error);
            alert('삭제에 실패했습니다.');
        }
    }
};
```

---

##### 2-3. ConvenienceCombWrite.js ✅
**경로:** `frontend/moc-pro/src/components/conv_board/ConvenienceCombWrite.js`

**수정 내용:**
- ✅ axios 작성 API 연동 (FormData)
- ✅ axios 수정 API 연동 (FormData)
- ✅ 수정 모드 데이터 로드 API 연동
- ✅ 제품/레시피 배열 관리 (최대 10개)
- ✅ 이미지 파일 관리 (images 배열)
- ✅ ImageUploadBoxes 연동

**핵심 코드:**
```javascript
// 이미지 상태 관리
const [images, setImages] = useState([]);

// 수정 모드: 기존 글 불러오기
useEffect(() => {
    if(postid) {
        setIsEditMode(true);
        const fetchData = async () => {
            try {
                const response = await axios.get(`http://localhost:18880/api/conv-recipe/${postid}`);
                if (response.data.success) {
                    const { recipe, products, orders } = response.data.data;
                    
                    setData({
                        userid: recipe.userId,
                        cookname: recipe.convRecipeTitle,
                        mainproduct: recipe.convRecipeMainProduct,
                        category: recipe.convRecipeCategory,
                        tip: recipe.convRecipeTip
                    });
                    
                    setProducts(products.length > 0 
                        ? products.map(p => p.convRecipeProductContent) 
                        : ['']
                    );
                    
                    setRecipes(orders.length > 0 
                        ? orders.map(o => o.convRecipeOrderContent) 
                        : ['']
                    );
                }
            } catch (error) {
                console.error('데이터 로드 실패:', error);
            }
        };
        fetchData();
    }
}, [postid]);

// 작성/수정 (FormData)
const handleSubmit = async (e) => {
    e.preventDefault();
    
    const formData = new FormData();
    formData.append('userId', data.userid);
    formData.append('title', data.cookname);
    formData.append('mainProduct', data.mainproduct);
    formData.append('category', data.category);
    formData.append('tip', data.tip);
    
    // 제품 목록 JSON
    const productList = products.filter(item => item.trim() !== '')
                                .map(content => ({ content }));
    if (productList.length > 0) {
        formData.append('products', JSON.stringify(productList));
    }
    
    // 레시피 목록 JSON
    const orderList = recipes.filter(item => item.trim() !== '')
                             .map(content => ({ content }));
    if (orderList.length > 0) {
        formData.append('orders', JSON.stringify(orderList));
    }
    
    // 이미지 파일
    images.forEach((file) => {
        formData.append('images', file);
    });
    
    try {
        if(isEditMode) {
            // 수정
            const response = await axios.put(`http://localhost:18880/api/conv-recipe/${postid}`, formData, {
                headers: { 'Content-Type': 'multipart/form-data' }
            });
            
            if (response.data.success) {
                alert('편의점 조합이 수정되었습니다.');
                navigate(`/conv/recipe/detail/${postid}`);
            } else {
                alert(response.data.message || '수정에 실패했습니다.');
            }
        } else {
            // 작성
            const response = await axios.post('http://localhost:18880/api/conv-recipe', formData, {
                headers: { 'Content-Type': 'multipart/form-data' }
            });
            
            if (response.data.success) {
                alert('편의점 조합이 작성되었습니다.');
                navigate('/conv/recipe');
            } else {
                alert(response.data.message || '작성에 실패했습니다.');
            }
        }
    } catch (error) {
        console.error('제출 실패:', error);
        alert('오류가 발생했습니다.');
    }
};

// ImageUploadBoxes 연동
<ImageUploadBoxes onImagesChange={setImages} />
```

---

#### 3. 편의점 조합 특이사항

**레시피 게시판과의 차이:**

| 항목 | 레시피 게시판 | 편의점 조합 |
|------|-------------|------------|
| 테이블명 | TB_RECIPE_* | TB_CONV_RECIPE_* |
| 주재료 | RECIPE_MAIN_INGREDIENT | CONV_RECIPE_MAIN_PRODUCT |
| 카테고리 | 없음 | 식사류/간식류/음료류 |
| 검색 조건 | 키워드 + 재료 | 키워드 + 제품명 + 카테고리 |
| 필터링 | 없음 | 카테고리 탭 |
| 검색 UI | 2개 input | 2개 input + 카테고리 탭 |

**핵심 기능:**
- ✅ **카테고리 필터:** 식사류, 간식류, 음료(주)류
- ✅ **검색 + 카테고리 동시 적용:** 식사류 중에서 떡볶이 검색 가능
- ✅ **여러 이미지:** 최대 4개 업로드
- ✅ **제품/레시피 배열:** 최대 10개
- ✅ **@Transactional:** 조합+제품+순서+이미지 원자성 보장
- ✅ **useGeneratedKeys:** insert 후 recipeId 자동 반환
- ✅ **FormData:** JSON + 파일 동시 전송
- ✅ **FileUploadService:** 이미지 업로드/삭제
- ✅ **대표 이미지:** 목록에서 첫 번째 이미지 표시
- ✅ **ObjectMapper:** JSON 문자열 파싱

---

#### 4. 테스트 완료 ✅

**테스트 항목:**
- ✅ 목록 조회 (페이징 10개/페이지, 카테고리 필터)
- ✅ 검색 (키워드 + 제품명 + 카테고리 동시 적용)
- ✅ 카테고리 탭 전환 (검색 상태 유지)
- ✅ 상세 조회 (제품, 레시피, 이미지, 댓글 모두 표시)
- ✅ 작성 (제품 최대 10개, 레시피 최대 10개, 이미지 4개)
- ✅ 수정 (기존 데이터 로드, FormData 전송)
- ✅ 삭제 (조합+제품+순서+이미지 모두 삭제)
- ✅ 댓글 작성/삭제
- ✅ 작성자 확인 (수정/삭제 버튼 표시)
- ✅ 이미지 슬라이더 동작

---

### 31단계: 편의점 리뷰 게시판 Backend API 전체 구현 ✅

#### 개요
**목적:** 편의점 리뷰 게시판 Backend API 및 Frontend 완성

**수정일:** 2025-11-12

---

#### 1. Backend API 구현 (Spring Boot) - 11개 파일

##### 1-1. ConvReview VO 클래스들 ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/convreview/vo/`

**생성된 파일 (3개):**
- **ConvReviewVO.java** - 편의점 리뷰 기본 정보
- **ConvReviewCommentVO.java** - 리뷰 댓글
- **ConvReviewImageVO.java** - 리뷰 이미지

**ConvReviewVO 주요 필드:**
```java
private int convReviewId;                 // 리뷰 ID
private String userId;                    // 작성자 ID
private String convReviewTitle;           // 제목
private String convReviewContent;         // 내용
private Integer convReviewPrice;          // 가격 (선택사항, NULL 허용)
private String convReviewStore;           // 편의점 (GS25, CU, 세븐일레븐, 이마트24)
private String convReviewCategory;        // 카테고리 (식사류, 간식류, 음료(주)류)
private String userNickname;              // 작성자 닉네임 (조인용)
```

---

##### 1-2. ConvReviewDAO + ConvReviewDAOImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/convreview/dao/`

**구현 메서드 (총 14개):**
```java
// 리뷰 게시글 (9개)
List<ConvReviewVO> selectList(Map<String, Object> params);
int selectTotalCount(Map<String, Object> params);
ConvReviewVO selectById(int convReviewId);
int insertConvReview(ConvReviewVO convReview);
int updateConvReview(ConvReviewVO convReview);
int deleteConvReview(int convReviewId);
int checkAuthor(Map<String, Object> params);

// 이미지 (3개)
List<ConvReviewImageVO> selectImagesByReviewId(int convReviewId);
int insertImage(ConvReviewImageVO image);
int deleteImagesByReviewId(int convReviewId);

// 댓글 (4개)
List<ConvReviewCommentVO> selectCommentsByReviewId(int convReviewId);
int insertComment(ConvReviewCommentVO comment);
int deleteComment(int commentId);
int checkCommentAuthor(Map<String, Object> params);
```

---

##### 1-3. ConvReview.xml ✅
**경로:** `backend/pro/src/main/resources/mybatis/mappers/ConvReview.xml`

**주요 쿼리:**
```xml
<!-- 목록 조회 (편의점 + 카테고리 필터, 페이징) -->
<select id="selectList">
    SELECT cr.*, u.USER_NICKNAME,
           (SELECT CRI.CONV_REVIEW_IMAGE_URL
            FROM TB_CONV_REVIEW_IMAGE CRI
            WHERE CRI.CONV_REVIEW_ID = cr.CONV_REVIEW_ID
              AND ROWNUM = 1
            ORDER BY CRI.CONV_REVIEW_IMAGE_INDEX ASC) AS conv_review_content
    FROM TB_CONV_REVIEW cr
    LEFT JOIN TB_USER u ON cr.USER_ID = u.USER_ID
    <where>
        <if test="store != null and store != ''">
            AND cr.CONV_REVIEW_STORE = #{store}
        </if>
        <if test="category != null and category != ''">
            AND cr.CONV_REVIEW_CATEGORY = #{category}
        </if>
    </where>
    ORDER BY cr.CREATED_AT DESC
    OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY
</select>

<!-- 전체 개수 (필터 포함) -->
<select id="selectTotalCount">
    SELECT COUNT(*) FROM TB_CONV_REVIEW cr
    <where>
        <if test="store != null and store != ''">
            AND cr.CONV_REVIEW_STORE = #{store}
        </if>
        <if test="category != null and category != ''">
            AND cr.CONV_REVIEW_CATEGORY = #{category}
        </if>
    </where>
</select>

<!-- 이미지 조회 -->
<select id="selectImagesByReviewId">
    SELECT * FROM TB_CONV_REVIEW_IMAGE
    WHERE CONV_REVIEW_ID = #{convReviewId}
    ORDER BY CONV_REVIEW_IMAGE_INDEX ASC
</select>
```

---

##### 1-4. ConvReviewService + ConvReviewServiceImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/convreview/service/`

**주요 메서드:**
```java
// 목록 조회 (편의점 + 카테고리 필터, 페이징)
Map<String, Object> getList(String store, String category, int page);

// 상세 조회 (이미지 포함)
Map<String, Object> getDetail(int convReviewId);

// 작성/수정/삭제 (MultipartFile[] 처리)
boolean createConvReview(ConvReviewVO convReview, MultipartFile[] images);
boolean updateConvReview(ConvReviewVO convReview, MultipartFile[] images);
boolean deleteConvReview(int convReviewId);

// 작성자 확인
boolean checkAuthor(int convReviewId, String userId);

// 댓글
List<ConvReviewCommentVO> getComments(int convReviewId);
boolean createComment(ConvReviewCommentVO comment);
boolean deleteComment(int commentId, String userId);
```

**핵심 로직:**
```java
@Override
@Transactional
public boolean createConvReview(ConvReviewVO convReview, MultipartFile[] images) {
    // 1. 리뷰 기본 정보 저장
    convReviewDAO.insertConvReview(convReview);
    int reviewId = convReview.getConvReviewId();
    
    // 2. 이미지 파일 업로드 및 저장 (최대 4개)
    if (images != null && images.length > 0) {
        int imageIndex = 1;
        for (MultipartFile file : images) {
            if (file.isEmpty()) continue;
            
            ImageVO uploadResult = fileUploadService.uploadImage(file, "conv-review");
            
            ConvReviewImageVO image = new ConvReviewImageVO();
            image.setConvReviewId(reviewId);
            image.setConvReviewImageUrl(uploadResult.getUrl());
            image.setConvReviewImagePath(uploadResult.getPath());
            image.setConvReviewImageIndex(imageIndex++);
            
            convReviewDAO.insertImage(image);
        }
    }
    
    return true;
}
```

**목록 조회 시 대표 이미지 처리:**
```java
// 대표 이미지가 없으면 기본 이미지 (카드용)
for (ConvReviewVO review : posts) {
    if (review.getConvReviewContent() == null || review.getConvReviewContent().isEmpty()) {
        review.setConvReviewContent(
            fileUploadService.getNoImageUrl(250, 250)
        );
    }
}
```

---

##### 1-5. ConvReviewController + ConvReviewControllerImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/convreview/controller/`

**구현 API (9개):**
```java
// 게시글
GET  /api/conv-review/list?store={store}&category={category}&page={page}  // 목록 (필터)
GET  /api/conv-review/{id}                                                 // 상세
GET  /api/conv-review/{id}/check-author                                    // 작성자 확인
POST /api/conv-review                                                      // 작성 (Multipart)
PUT  /api/conv-review/{id}                                                 // 수정 (Multipart)
DELETE /api/conv-review/{id}                                               // 삭제

// 댓글
GET  /api/conv-review/{id}/comments                                        // 댓글 목록
POST /api/conv-review/{id}/comments                                        // 댓글 작성
DELETE /api/conv-review/comments/{commentId}                               // 댓글 삭제
```

**작성 API (Multipart/form-data):**
```java
@PostMapping
@ResponseBody
public ResponseEntity<Map<String, Object>> createConvReview(
    @RequestParam String userId,
    @RequestParam String title,
    @RequestParam String content,
    @RequestParam(required = false) Integer price,
    @RequestParam String store,
    @RequestParam String category,
    @RequestParam(required = false) MultipartFile[] images,
    HttpSession session) {
    
    String sessionUserId = (String) session.getAttribute("userId");
    if (sessionUserId == null) {
        response.put("message", "로그인이 필요합니다.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
    
    ConvReviewVO convReview = new ConvReviewVO();
    convReview.setUserId(sessionUserId);
    convReview.setConvReviewTitle(title);
    convReview.setConvReviewContent(content);
    convReview.setConvReviewPrice(price);
    convReview.setConvReviewStore(store);
    convReview.setConvReviewCategory(category);
    
    boolean success = convReviewService.createConvReview(convReview, images);
    
    if (success) {
        response.put("success", true);
        response.put("message", "편의점 리뷰가 작성되었습니다.");
        return ResponseEntity.ok(response);
    }
}
```

---

#### 2. Frontend 연동 (React) - 3개 파일

##### 2-1. ConvenienceBoard.js ✅
**경로:** `frontend/moc-pro/src/components/conv_board/ConvenienceBoard.js`

**수정 내용:**
- ✅ axios 목록 조회 API 연동
- ✅ 편의점/카테고리 필터 동적 데이터 로드
- ✅ 이미지 URL 표시
- ✅ 페이징 처리

**핵심 코드:**
```javascript
const loadPosts = async () => {
    try {
        const response = await axios.get('http://localhost:18880/api/conv-review/list', {
            params: {
                store: activeStore,
                category: activeCategory,
                page: page
            }
        });
        
        if (response.data.success) {
            setPosts(response.data.data.posts);
            setTotalPage(response.data.data.totalPage);
        }
    } catch (error) {
        console.error('목록 조회 실패:', error);
    }
};

useEffect(() => {
    loadPosts();
}, [page, activeStore, activeCategory]);

// 카드 표시
{posts.map((post) => (
    <div key={post.convReviewId}>
        <img src={post.convReviewContent || 'https://placehold.co/250x250/png?text=No+Image'} 
             alt={post.convReviewTitle}/>
        <h2>{post.convReviewTitle}</h2>
        <span>작성자: {post.userNickname}</span>
        <span>가격: {post.convReviewPrice ? `${post.convReviewPrice}원` : '가격 정보 없음'}</span>
    </div>
))}
```

---

##### 2-2. ConvenienceReviewDetail.js ✅
**경로:** `frontend/moc-pro/src/components/conv_board/ConvenienceReviewDetail.js`

**수정 내용:**
- ✅ axios 상세 조회 API 연동 (이미지 포함)
- ✅ axios 작성자 확인 API 연동
- ✅ axios 삭제 API 연동
- ✅ axios 댓글 목록 조회 API 연동
- ✅ 이미지 슬라이더 동작
- ✅ CommentBoard 연동 (boardType="conv-review")

**핵심 코드:**
```javascript
useEffect(() => {
    const fetchData = async () => {
        try {
            // 상세 정보 조회
            const detailResponse = await axios.get(`http://localhost:18880/api/conv-review/${postid}`);
            if (detailResponse.data.success) {
                const { convReview, images } = detailResponse.data.data;
                setPost({
                    ...convReview,
                    images: images.map(img => img.convReviewImageUrl)
                });
            }
            
            // 작성자 확인
            const authorResponse = await axios.get(`http://localhost:18880/api/conv-review/${postid}/check-author`);
            if (authorResponse.data.success) {
                setIsAuthor(authorResponse.data.isAuthor);
            }
            
            // 댓글 조회
            const commentsResponse = await axios.get(`http://localhost:18880/api/conv-review/${postid}/comments`);
            if (commentsResponse.data.success) {
                setPost(prev => ({
                    ...prev,
                    comments: commentsResponse.data.comments
                }));
            }
        } catch (error) {
            console.error('데이터 조회 실패:', error);
        }
    };
    
    fetchData();
}, [postid]);

// 삭제
const handleDelete = async () => {
    if (window.confirm("정말 이 리뷰를 삭제하시겠습니까?")) {
        try {
            const response = await axios.delete(`http://localhost:18880/api/conv-review/${postid}`);
            if (response.data.success) {
                alert('리뷰가 삭제되었습니다.');
                navigate('/conv/review');
            }
        } catch (error) {
            console.error('삭제 실패:', error);
        }
    }
};
```

---

##### 2-3. ConvenienceReviewWrite.js ✅
**경로:** `frontend/moc-pro/src/components/conv_board/ConvenienceReviewWrite.js`

**수정 내용:**
- ✅ axios 작성 API 연동 (FormData)
- ✅ axios 수정 API 연동 (FormData)
- ✅ 수정 모드 데이터 로드 API 연동
- ✅ 이미지 파일 관리 (images 배열)
- ✅ ImageUploadBoxes 연동

**핵심 코드:**
```javascript
// 이미지 상태 관리
const [images, setImages] = useState([]);

// 수정 모드: 기존 글 불러오기
useEffect(() => {
    if(postid) {
        setIsEditMode(true);
        const fetchData = async () => {
            try {
                const response = await axios.get(`http://localhost:18880/api/conv-review/${postid}`);
                if (response.data.success) {
                    const { convReview } = response.data.data;
                    setData({
                        userid: convReview.userId,
                        title: convReview.convReviewTitle,
                        content: convReview.convReviewContent,
                        price: convReview.convReviewPrice || '',
                        conv: convReview.convReviewStore,
                        category: convReview.convReviewCategory
                    });
                }
            } catch (error) {
                console.error('데이터 로드 실패:', error);
            }
        };
        fetchData();
    }
}, [postid]);

// 작성/수정 (FormData)
const handleSubmit = async (e) => {
    e.preventDefault();
    
    const formData = new FormData();
    formData.append('userId', data.userid);
    formData.append('title', data.title);
    formData.append('content', data.content);
    formData.append('price', data.price || '');
    formData.append('store', data.conv);
    formData.append('category', data.category);
    
    images.forEach((file) => {
        formData.append('images', file);
    });
    
    try {
        if(isEditMode) {
            // 수정
            const response = await axios.put(`http://localhost:18880/api/conv-review/${postid}`, formData, {
                headers: { 'Content-Type': 'multipart/form-data' }
            });
            if (response.data.success) {
                alert('리뷰가 수정되었습니다.');
                navigate(`/conv/review/detail/${postid}`);
            }
        } else {
            // 작성
            const response = await axios.post('http://localhost:18880/api/conv-review', formData, {
                headers: { 'Content-Type': 'multipart/form-data' }
            });
            if (response.data.success) {
                alert('리뷰가 작성되었습니다.');
                navigate('/conv/review');
            }
        }
    } catch (error) {
        console.error('제출 실패:', error);
    }
};

// ImageUploadBoxes 연동
<ImageUploadBoxes onImagesChange={setImages} />
```

---

#### 3. 편의점 리뷰 특이사항

**자유게시판/레시피와의 차이:**

| 항목 | 자유게시판 | 레시피 게시판 | 편의점 리뷰 |
|------|-----------|--------------|------------|
| 테이블 수 | 2개 | 5개 | 3개 (review, image, comment) |
| 이미지 | 단일 | 다중 (최대 4개) | 다중 (최대 4개) |
| 필터 | 검색만 | 검색만 | 편의점 + 카테고리 |
| 가격 | 없음 | 없음 | 선택사항 (NULL 허용) |
| 작성 방식 | JSON | FormData | FormData |

**핵심 기능:**
- ✅ **편의점 필터:** GS25, 세븐일레븐, CU, 이마트24
- ✅ **카테고리 필터:** 식사류, 간식류, 음료(주)류
- ✅ **가격 정보:** 선택사항, NULL 허용
- ✅ **여러 이미지:** 최대 4개 업로드
- ✅ **@Transactional:** 리뷰+이미지 원자성 보장
- ✅ **useGeneratedKeys:** insert 후 reviewId 자동 반환
- ✅ **FormData:** JSON + 파일 동시 전송
- ✅ **FileUploadService:** 이미지 업로드/삭제
- ✅ **대표 이미지:** 목록에서 첫 번째 이미지 표시

---

#### 4. 테스트 완료 ✅

**테스트 항목:**
- ✅ 목록 조회 (페이징 10개/페이지, 편의점/카테고리 필터)
- ✅ 상세 조회 (이미지, 댓글 모두 표시)
- ✅ 작성 (이미지 4개, 가격 선택사항)
- ✅ 수정 (기존 데이터 로드, FormData 전송)
- ✅ 삭제 (리뷰+이미지 모두 삭제)
- ✅ 댓글 작성/삭제
- ✅ 작성자 확인 (수정/삭제 버튼 표시)
- ✅ 이미지 슬라이더 동작
- ✅ 필터 동작 (편의점/카테고리 변경 시 즉시 로드)

---

### 30단계: 레시피 게시판 Backend API 전체 구현 ✅

#### 개요
**목적:** 레시피 게시판 Backend API 및 Frontend 완성

**수정일:** 2025-11-12

---

#### 1. Backend API 구현 (Spring Boot) - 12개 파일

##### 1-1. Recipe VO 클래스들 ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/recipe/vo/`

**생성된 파일 (5개):**
- **RecipeVO.java** - 레시피 기본 정보
- **RecipeCommentVO.java** - 레시피 댓글
- **RecipeIngredientVO.java** - 상세 준비 재료
- **RecipeOrderVO.java** - 조리 순서
- **RecipeImageVO.java** - 레시피 이미지

**RecipeVO 주요 필드:**
```java
private int recipeId;                   // 레시피 ID
private String userId;                  // 작성자 ID
private String recipeTitle;             // 레시피 제목
private String recipeMainIngredient;    // 주재료
private String recipeContent;           // 팁 & 소감 (CLOB)
private String userNickname;            // 작성자 닉네임 (조인용)
```

---

##### 1-2. RecipeDAO + RecipeDAOImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/recipe/dao/`

**구현 메서드 (총 21개):**
```java
// 레시피 게시글 (9개)
List<RecipeVO> selectList(Map<String, Object> params);
int selectTotalCount();
List<RecipeVO> searchByKeyword(Map<String, Object> params);
int selectSearchCount(Map<String, Object> params);
RecipeVO selectById(int recipeId);
int insertRecipe(RecipeVO recipe);
int updateRecipe(RecipeVO recipe);
int deleteRecipe(int recipeId);
int checkAuthor(Map<String, Object> params);

// 재료 (3개)
List<RecipeIngredientVO> selectIngredientsByRecipeId(int recipeId);
int insertIngredient(RecipeIngredientVO ingredient);
int deleteIngredientsByRecipeId(int recipeId);

// 조리 순서 (3개)
List<RecipeOrderVO> selectOrdersByRecipeId(int recipeId);
int insertOrder(RecipeOrderVO order);
int deleteOrdersByRecipeId(int recipeId);

// 이미지 (3개)
List<RecipeImageVO> selectImagesByRecipeId(int recipeId);
int insertImage(RecipeImageVO image);
int deleteImagesByRecipeId(int recipeId);

// 댓글 (4개)
List<RecipeCommentVO> selectCommentsByRecipeId(int recipeId);
int insertComment(RecipeCommentVO comment);
int deleteComment(int commentId);
int checkCommentAuthor(Map<String, Object> params);
```

---

##### 1-3. Recipe.xml ✅
**경로:** `backend/pro/src/main/resources/mybatis/mappers/Recipe.xml`

**주요 쿼리:**
```xml
<!-- 목록 조회 (페이징) -->
<select id="selectList">
    SELECT r.*, u.USER_NICKNAME
    FROM TB_RECIPE r
    LEFT JOIN TB_USER u ON r.USER_ID = u.USER_ID
    ORDER BY r.CREATED_AT DESC
    OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY
</select>

<!-- 검색 (키워드 + 주재료) -->
<select id="searchByKeyword">
    SELECT r.*, u.USER_NICKNAME
    FROM TB_RECIPE r
    LEFT JOIN TB_USER u ON r.USER_ID = u.USER_ID
    WHERE
        (r.RECIPE_TITLE LIKE '%' || #{keyword} || '%'
        OR r.RECIPE_CONTENT LIKE '%' || #{keyword} || '%')
        AND r.RECIPE_MAIN_INGREDIENT LIKE '%' || #{ingredient} || '%'
    ORDER BY r.CREATED_AT DESC
    OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY
</select>

<!-- 재료 조회 -->
<select id="selectIngredientsByRecipeId">
    SELECT * FROM TB_RECIPE_DETAIL_INGREDIENT
    WHERE RECIPE_ID = #{recipeId}
    ORDER BY RECIPE_DETAIL_INGREDIENT_INDEX ASC
</select>

<!-- 조리 순서 조회 -->
<select id="selectOrdersByRecipeId">
    SELECT * FROM TB_RECIPE_ORDER
    WHERE RECIPE_ID = #{recipeId}
    ORDER BY RECIPE_ORDER_INDEX ASC
</select>

<!-- 이미지 조회 -->
<select id="selectImagesByRecipeId">
    SELECT * FROM TB_RECIPE_IMAGE
    WHERE RECIPE_ID = #{recipeId}
    ORDER BY RECIPE_IMAGE_INDEX ASC
</select>
```

---

##### 1-4. RecipeService + RecipeServiceImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/recipe/service/`

**주요 메서드:**
```java
// 목록/검색 (페이징)
Map<String, Object> getList(int page);
Map<String, Object> searchRecipes(String keyword, String ingredient, int page);

// 상세 조회 (재료/순서/이미지 포함)
Map<String, Object> getDetail(int recipeId);

// 작성/수정/삭제 (MultipartFile[] 처리)
boolean createRecipe(RecipeVO recipe, String ingredients, String orders, MultipartFile[] images);
boolean updateRecipe(RecipeVO recipe, String ingredients, String orders, MultipartFile[] images);
boolean deleteRecipe(int recipeId);

// 작성자 확인
boolean checkAuthor(int recipeId, String userId);

// 댓글
List<RecipeCommentVO> getComments(int recipeId);
boolean createComment(RecipeCommentVO comment);
boolean deleteComment(int commentId, String userId);
```

**핵심 로직:**
```java
@Override
@Transactional
public boolean createRecipe(RecipeVO recipe, String ingredientsJson, String ordersJson, MultipartFile[] images) {
    // 1. 레시피 기본 정보 저장
    recipeDAO.insertRecipe(recipe);
    int recipeId = recipe.getRecipeId(); // useGeneratedKeys 사용
    
    // 2. 재료 저장 (JSON 파싱)
    List<Map<String, String>> ingredientList = objectMapper.readValue(ingredientsJson, ...);
    for (Map<String, String> item : ingredientList) {
        RecipeIngredientVO ingredient = new RecipeIngredientVO();
        ingredient.setRecipeId(recipeId);
        ingredient.setRecipeDetailIngredientContent(item.get("content"));
        ingredient.setRecipeDetailIngredientIndex(index++);
        recipeDAO.insertIngredient(ingredient);
    }
    
    // 3. 조리 순서 저장
    List<Map<String, String>> orderList = objectMapper.readValue(ordersJson, ...);
    // 동일한 방식으로 처리
    
    // 4. 이미지 파일 업로드 및 저장
    for (MultipartFile file : images) {
        ImageVO uploadResult = fileUploadService.uploadImage(file, "recipe");
        RecipeImageVO image = new RecipeImageVO();
        image.setRecipeImageUrl(uploadResult.getUrl());
        image.setRecipeImagePath(uploadResult.getPath());
        recipeDAO.insertImage(image);
    }
}
```

**목록 조회 시 대표 이미지 처리:**
```java
for (RecipeVO recipe : posts) {
    List<RecipeImageVO> images = recipeDAO.selectImagesByRecipeId(recipe.getRecipeId());
    if (!images.isEmpty()) {
        recipe.setRecipeContent(images.get(0).getRecipeImageUrl()); // 첫 번째 이미지
    } else {
        recipe.setRecipeContent(fileUploadService.getNoImageUrl(250, 250));
    }
}
```

---

##### 1-5. RecipeController + RecipeControllerImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/recipe/controller/`

**구현 API:**
```java
// 게시글
GET  /api/recipe/list?page={page}                         // 목록
GET  /api/recipe/search?keyword={}&ingredient={}&page={}  // 검색
GET  /api/recipe/{id}                                     // 상세
GET  /api/recipe/{id}/check-author                        // 작성자 확인
POST /api/recipe                                          // 작성 (Multipart)
PUT  /api/recipe/{id}                                     // 수정 (Multipart)
DELETE /api/recipe/{id}                                   // 삭제

// 댓글
GET  /api/recipe/{id}/comments                            // 댓글 목록
POST /api/recipe/{id}/comments                            // 댓글 작성
DELETE /api/recipe/comments/{commentId}                   // 댓글 삭제
```

**작성 API (Multipart/form-data):**
```java
@PostMapping
@ResponseBody
public ResponseEntity<Map<String, Object>> createRecipe(
    @RequestParam String userId,
    @RequestParam String title,
    @RequestParam String mainIngredient,
    @RequestParam String content,
    @RequestParam(required = false) String ingredients,  // JSON 문자열
    @RequestParam(required = false) String orders,       // JSON 문자열
    @RequestParam(required = false) MultipartFile[] images,
    HttpSession session) {
    
    // RecipeVO 생성
    RecipeVO recipe = new RecipeVO();
    recipe.setUserId(sessionUserId);
    recipe.setRecipeTitle(title);
    recipe.setRecipeMainIngredient(mainIngredient);
    recipe.setRecipeContent(content);
    
    // 레시피 작성 (재료, 순서, 이미지 함께)
    boolean success = recipeService.createRecipe(recipe, ingredients, orders, images);
}
```

---

#### 2. Frontend 연동 (React) - 3개 파일

##### 2-1. RecipeBoard.js ✅
**경로:** `frontend/moc-pro/src/components/recipe_board/RecipeBoard.js`

**수정 내용:**
- ✅ axios import 추가
- ✅ 목록 조회 API 연동
- ✅ 검색 API 연동 (키워드 + 주재료)
- ✅ 이미지 URL 표시
- ✅ 페이징 처리

**핵심 코드:**
```javascript
// 목록 조회
useEffect(() => {
    const fetchPosts = async () => {
        const keyword = searchParams.get('keyword');
        const ingredient = searchParams.get('ingredient');
        
        if (keyword || ingredient) {
            // 검색
            const response = await axios.get('http://localhost:18880/api/recipe/search', {
                params: { keyword, ingredient, page }
            });
        } else {
            // 목록
            const response = await axios.get('http://localhost:18880/api/recipe/list', {
                params: { page }
            });
        }
        
        if (response.data.success) {
            setPosts(response.data.data.posts);
            setTotalPage(response.data.data.totalPage);
        }
    };
    fetchPosts();
}, [page, searchParams]);

// 검색
const handleSearch = async (e) => {
    e.preventDefault();
    const response = await axios.get('http://localhost:18880/api/recipe/search', {
        params: {
            keyword: searchKeyword,
            ingredient: searchIngredient,
            page: 1
        }
    });
    
    if (response.data.success) {
        const params = new URLSearchParams();
        params.set('page', '1');
        if (searchKeyword) params.set('keyword', searchKeyword);
        if (searchIngredient) params.set('ingredient', searchIngredient);
        navigate(`/recipe?${params.toString()}`);
    }
};

// 카드 표시
{posts.map((post) => (
    <div key={post.recipeId}>
        <img src={post.recipeContent || 'https://placehold.co/250x250/png?text=No+Image'} 
             alt={post.recipeTitle}/>
        <h2>{post.recipeTitle}</h2>
        <span>작성자: {post.userNickname}</span>
        <span>핵심재료: {post.recipeMainIngredient}</span>
    </div>
))}
```

---

##### 2-2. RecipeBoardDetail.js ✅
**경로:** `frontend/moc-pro/src/components/recipe_board/RecipeBoardDetail.js`

**수정 내용:**
- ✅ axios import 추가
- ✅ 상세 조회 API 연동 (재료/순서/이미지 포함)
- ✅ 작성자 확인 API 연동
- ✅ 삭제 API 연동
- ✅ 이미지 슬라이더 동작
- ✅ CommentBoard 연동 (boardType="recipe")

**핵심 코드:**
```javascript
// 상세 조회
useEffect(() => {
    const fetchDetail = async () => {
        const response = await axios.get(`http://localhost:18880/api/recipe/${postid}`);
        
        if (response.data.success) {
            const { recipe, ingredients, orders, images } = response.data.data;
            
            setPost({
                id: recipe.recipeId,
                title: recipe.recipeTitle,
                mainingre: recipe.recipeMainIngredient,
                usernickname: recipe.userNickname,
                createdate: new Date(recipe.createdAt).toLocaleDateString(),
                ingredients: ingredients.map(item => item.recipeDetailIngredientContent),
                recipes: orders.map(item => item.recipeOrderContent),
                tip: recipe.recipeContent,
                images: images.map(item => item.recipeImageUrl)
            });
        }
    };
    
    const fetchAuthor = async () => {
        const response = await axios.get(`http://localhost:18880/api/recipe/${postid}/check-author`);
        setIsAuthor(response.data.isAuthor || false);
    };
    
    fetchDetail();
    fetchAuthor();
}, [postid, navigate]);

// 삭제
const handleDelete = async () => {
    if (window.confirm("정말 이 레시피를 삭제하시겠습니까?")) {
        const response = await axios.delete(`http://localhost:18880/api/recipe/${postid}`);
        
        if (response.data.success) {
            alert('레시피가 삭제되었습니다.');
            navigate('/recipe');
        }
    }
};
```

---

##### 2-3. RecipeBoardWrite.js ✅
**경로:** `frontend/moc-pro/src/components/recipe_board/RecipeBoardWrite.js`

**수정 내용:**
- ✅ axios import 추가
- ✅ 수정 모드 데이터 로드 API 연동
- ✅ 작성/수정 API 연동 (FormData 사용)
- ✅ 이미지 파일 관리 (images 배열, imagePreview 배열)
- ✅ ImageUploadBoxes 직접 구현

**핵심 코드:**
```javascript
// 이미지 파일 상태 관리
const [images, setImages] = useState([]);
const [imagePreview, setImagePreview] = useState([null, null, null, null]);

// 수정 모드: 기존 글 불러오기
useEffect(() => {
    if(postid) {
        setIsEditMode(true);
        const fetchDetail = async () => {
            const response = await axios.get(`http://localhost:18880/api/recipe/${postid}`);
            
            if (response.data.success) {
                const { recipe, ingredients, orders } = response.data.data;
                setData({
                    userid: recipe.userId,
                    cookname: recipe.recipeTitle,
                    mainingredients: recipe.recipeMainIngredient
                });
                setIngredients(ingredients.map(item => item.recipeDetailIngredientContent));
                setRecipes(orders.map(item => item.recipeOrderContent));
                setText(recipe.recipeContent);
            }
        };
        fetchDetail();
    }
}, [postid, navigate]);

// 작성/수정 (FormData)
const handleSubmit = async (e) => {
    e.preventDefault();
    
    // FormData 생성
    const formData = new FormData();
    formData.append('userId', data.userid);
    formData.append('title', data.cookname);
    formData.append('mainIngredient', data.mainingredients);
    formData.append('content', text);
    
    // 재료 JSON
    const ingredientList = ingredients.filter(item => item.trim() !== '')
                                      .map(content => ({ content }));
    if (ingredientList.length > 0) {
        formData.append('ingredients', JSON.stringify(ingredientList));
    }
    
    // 조리 순서 JSON
    const orderList = recipes.filter(item => item.trim() !== '')
                             .map(content => ({ content }));
    if (orderList.length > 0) {
        formData.append('orders', JSON.stringify(orderList));
    }
    
    // 이미지 파일
    images.forEach((file) => {
        formData.append('images', file);
    });
    
    if(isEditMode) {
        // 수정
        const response = await axios.put(`http://localhost:18880/api/recipe/${postid}`, formData, {
            headers: { 'Content-Type': 'multipart/form-data' }
        });
    } else {
        // 작성
        const response = await axios.post('http://localhost:18880/api/recipe', formData, {
            headers: { 'Content-Type': 'multipart/form-data' }
        });
    }
};

// 이미지 업로드 UI
{imagePreview.map((img, index) => (
    <div key={index}>
        {img ? (
            <img src={img} alt={`preview-${index}`} />
        ) : (
            <label>
                사진
                <input type="file" accept="image/*"
                       onChange={(e) => {
                           const file = e.target.files[0];
                           if (file && file.type.startsWith('image/')) {
                               const newImages = [...images];
                               newImages[index] = file;
                               setImages(newImages);
                               
                               const newPreview = [...imagePreview];
                               newPreview[index] = URL.createObjectURL(file);
                               setImagePreview(newPreview);
                           }
                       }}
                />
            </label>
        )}
    </div>
))}
```

---

#### 3. 레시피 게시판 특이사항

**자유게시판/공지사항과의 차이:**

| 항목 | 자유게시판 | 레시피 게시판 |
|------|-----------|-------------|
| 테이블 수 | 2개 | 5개 (recipe, ingredient, order, image, comment) |
| 이미지 | 단일 (선택) | 다중 (최대 4개) |
| 추가 데이터 | 없음 | 재료 목록, 조리 순서 |
| 검색 | 제목+내용 | 제목+내용+주재료 |
| 작성 방식 | JSON | FormData (Multipart) |
| 트랜잭션 | 단순 | 복잡 (여러 테이블 동시 처리) |

**핵심 기술:**
- ✅ **@Transactional** - 레시피+재료+순서+이미지 원자성 보장
- ✅ **useGeneratedKeys** - insert 후 recipeId 자동 반환
- ✅ **FormData** - JSON + 파일 동시 전송
- ✅ **ObjectMapper** - JSON 문자열 파싱
- ✅ **FileUploadService** - 이미지 업로드/삭제
- ✅ **배열 처리** - ingredients[], orders[], images[]

---

#### 4. 테스트 완료 ✅

**테스트 항목:**
- ✅ 목록 조회 (페이징 10개/페이지, 대표 이미지 표시)
- ✅ 검색 (키워드 + 주재료)
- ✅ 상세 조회 (재료, 순서, 이미지, 댓글 모두 표시)
- ✅ 작성 (재료 최대 10개, 순서 최대 10개, 이미지 4개)
- ✅ 수정 (기존 데이터 로드, FormData 전송)
- ✅ 삭제 (레시피+재료+순서+이미지 모두 삭제)
- ✅ 댓글 작성/삭제
- ✅ 작성자 확인 (수정/삭제 버튼 표시)
- ✅ 이미지 슬라이더 동작

---

### 29단계: 공지사항 Backend API 전체 구현 ✅

#### 개요
**목적:** 공지사항 게시판 Backend API 및 Frontend 완성

**수정일:** 2025-11-12

---

#### 1. Backend API 구현 (Spring Boot) - 7개 파일

##### 1-1. NoticeVO.java ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/notice/vo/NoticeVO.java`

**수정 내용:**
- ✅ 공지사항 VO 생성
- ✅ TB_NOTICE 테이블 매핑
- ✅ userNickname 필드 추가 (조인용)

**주요 필드:**
```java
private int noticeId;           // 공지사항 ID
private String userId;          // 작성자 ID
private String noticeTitle;     // 제목
private String noticeContent;   // 내용 (VARCHAR2 1000)
private Timestamp createdAt;    // 작성일시
private String userNickname;    // 작성자 닉네임
```

---

##### 1-2. NoticeDAO + NoticeDAOImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/notice/dao/`

**구현 메서드:**
```java
List<NoticeVO> selectList(Map<String, Object> params);  // 목록 (페이징)
int selectTotalCount();                                  // 전체 개수
NoticeVO selectById(int noticeId);                       // 상세 조회
int insertNotice(NoticeVO notice);                       // 작성
int updateNotice(NoticeVO notice);                       // 수정
int deleteNotice(int noticeId);                          // 삭제
```

---

##### 1-3. Notice.xml ✅
**경로:** `backend/pro/src/main/resources/mybatis/mappers/Notice.xml`

**주요 쿼리:**
```xml
<!-- 목록 조회 (페이징) -->
<select id="selectList">
    SELECT n.*, u.USER_NICKNAME
    FROM TB_NOTICE n
    LEFT JOIN TB_USER u ON n.USER_ID = u.USER_ID
    ORDER BY n.CREATED_AT DESC
    OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY
</select>

<!-- 전체 개수 -->
<select id="selectTotalCount">
    SELECT COUNT(*) FROM TB_NOTICE
</select>
```

---

##### 1-4. NoticeService + NoticeServiceImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/notice/service/`

**주요 메서드:**
```java
// 목록 조회 (페이징)
Map<String, Object> getList(int page);
// 반환: { posts, totalPage, currentPage, totalCount }

// 상세 조회
NoticeVO getDetail(int noticeId);

// 작성/수정/삭제
boolean createNotice(NoticeVO notice);
boolean updateNotice(NoticeVO notice);
boolean deleteNotice(int noticeId);
```

**페이징 계산:**
```java
private static final int ITEMS_PER_PAGE = 10;
int offset = (page - 1) * ITEMS_PER_PAGE;
int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
```

---

##### 1-5. NoticeController + NoticeControllerImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/notice/controller/`

**구현 API:**
```java
GET  /api/notice/list?page={page}  // 목록 조회
GET  /api/notice/{id}              // 상세 조회
POST /api/notice                   // 작성 (관리자만)
PUT  /api/notice/{id}              // 수정 (관리자만)
DELETE /api/notice/{id}            // 삭제 (관리자만)
```

**관리자 권한 체크:**
```java
String userRole = (String) session.getAttribute("userRole");
if (!"ADMIN".equals(userRole)) {
    response.put("message", "관리자만 작성할 수 있습니다.");
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
}
```

---

#### 2. Frontend 연동 (React) - 3개 파일

##### 2-1. NoticeBoard.js ✅
**경로:** `frontend/moc-pro/src/components/another_board/NoticeBoard.js`

**수정 내용:**
- ✅ axios 목록 조회 API 연동
- ✅ 관리자만 글쓰기 버튼 표시
- ✅ 페이징 처리

**핵심 코드:**
```javascript
useEffect(() => {
    const fetchPosts = async () => {
        const response = await axios.get('http://localhost:18880/api/notice/list', {
            params: { page: currentPage || 1 }
        });
        
        if (response.data.success) {
            setPosts(response.data.data.posts);
            setTotalPage(response.data.data.totalPage);
        }
    };
    fetchPosts();
}, [currentPage]);
```

---

##### 2-2. NoticeBoardDetail.js ✅
**경로:** `frontend/moc-pro/src/components/another_board/NoticeBoardDetail.js`

**수정 내용:**
- ✅ axios 상세 조회 API 연동
- ✅ axios 삭제 API 연동
- ✅ 관리자만 수정/삭제 버튼 표시

**핵심 코드:**
```javascript
// 상세 조회
useEffect(() => {
    const fetchDetail = async () => {
        const response = await axios.get(`http://localhost:18880/api/notice/${postid}`);
        
        if (response.data.success) {
            const notice = response.data.data;
            setData({
                title: notice.noticeTitle,
                content: notice.noticeContent,
                nickname: notice.userNickname,
                createdate: new Date(notice.createdAt).toLocaleDateString()
            });
        }
    };
    fetchDetail();
}, [postid]);

// 삭제
const handleDelete = async () => {
    const response = await axios.delete(`http://localhost:18880/api/notice/${postid}`);
    
    if (response.data.success) {
        alert('공지사항이 삭제되었습니다.');
        navigate('/notice');
    }
};
```

---

##### 2-3. NoticeBoardWrite.js ✅
**경로:** `frontend/moc-pro/src/components/another_board/NoticeBoardWrite.js`

**수정 내용:**
- ✅ axios 작성 API 연동
- ✅ axios 수정 API 연동
- ✅ 수정 모드 데이터 로드

**핵심 코드:**
```javascript
// 수정 모드: 기존 글 불러오기
useEffect(() => {
    if(postid) {
        setIsEditMode(true);
        const fetchNotice = async () => {
            const response = await axios.get(`http://localhost:18880/api/notice/${postid}`);
            if (response.data.success) {
                const notice = response.data.data;
                setData({
                    title: notice.noticeTitle,
                    content: notice.noticeContent
                });
            }
        };
        fetchNotice();
    }
}, [postid]);

// 작성/수정
const handelSubmit = async (e) => {
    e.preventDefault();
    
    if(isEditMode) {
        // 수정
        const response = await axios.put(`http://localhost:18880/api/notice/${postid}`, {
            title: data.title,
            content: data.content
        });
        if (response.data.success) {
            alert('공지사항이 수정되었습니다.');
            navigate(`/notice/detail/${postid}`);
        }
    } else {
        // 작성
        const response = await axios.post('http://localhost:18880/api/notice', {
            title: data.title,
            content: data.content
        });
        if (response.data.success) {
            alert('공지사항이 작성되었습니다.');
            navigate('/notice');
        }
    }
};
```

---

#### 3. 자유게시판과 차이점

| 항목 | 자유게시판 | 공지사항 |
|------|-----------|---------|
| 댓글 | ✅ 있음 | ❌ 없음 |
| 검색 | ✅ 있음 | ❌ 없음 |
| 작성 권한 | 로그인 사용자 | 관리자만 |
| 수정/삭제 | 작성자 본인 | 관리자만 |
| CONTENT | CLOB | VARCHAR2(1000) |

---

#### 4. 테스트 완료 ✅

**테스트 항목:**
- ✅ 목록 조회 (페이징 10개/페이지)
- ✅ 상세 조회
- ✅ 작성 (관리자 권한 체크)
- ✅ 수정 (관리자 권한 체크)
- ✅ 삭제 (관리자 권한 체크)
- ✅ 권한 없는 사용자 접근 차단 (403 Forbidden)

---

### 28단계: 자유게시판 Backend API 전체 구현 ✅

#### 개요
**목적:** 자유게시판 Backend API 및 Frontend 완성

**수정일:** 2025-11-12

---

#### 1. Backend API 구현 (Spring Boot) - 9개 파일

##### 1-1. FreeboardVO.java ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/freeboard/vo/FreeboardVO.java`

**수정 내용:**
- ✅ 자유게시판 VO 생성
- ✅ TB_FREEBOARD 테이블 매핑
- ✅ userNickname 필드 추가 (조인용)

---

##### 1-2. FreeboardCommentVO.java ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/freeboard/vo/FreeboardCommentVO.java`

**수정 내용:**
- ✅ 자유게시판 댓글 VO 생성
- ✅ TB_FREEBOARD_COMMENT 테이블 매핑
- ✅ userNickname 필드 추가

---

##### 1-3. FreeboardDAO + FreeboardDAOImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/freeboard/dao/`

**구현 메서드:**
```java
// 게시글
List<FreeboardVO> selectList(Map<String, Object> params);
int selectTotalCount();
List<FreeboardVO> searchByKeyword(Map<String, Object> params);
int selectSearchCount(String keyword);
FreeboardVO selectById(int freeboardId);
int insertFreeboard(FreeboardVO freeboard);
int updateFreeboard(FreeboardVO freeboard);
int deleteFreeboard(int freeboardId);
int checkAuthor(Map<String, Object> params);

// 댓글
List<FreeboardCommentVO> selectCommentsByBoardId(int freeboardId);
int insertComment(FreeboardCommentVO comment);
int deleteComment(int commentId);
int checkCommentAuthor(Map<String, Object> params);
```

---

##### 1-4. Freeboard.xml ✅
**경로:** `backend/pro/src/main/resources/mybatis/mappers/Freeboard.xml`

**주요 쿼리:**
```xml
<!-- 목록 조회 (페이징) -->
<select id="selectList">
    SELECT f.*, u.USER_NICKNAME
    FROM TB_FREEBOARD f
    LEFT JOIN TB_USER u ON f.USER_ID = u.USER_ID
    ORDER BY f.CREATED_AT DESC
    OFFSET #{offset} ROWS FETCH NEXT #{limit} ROWS ONLY
</select>

<!-- 검색 (제목 + 내용) -->
<select id="searchByKeyword">
    WHERE f.FREEBOARD_TITLE LIKE '%' || #{keyword} || '%'
       OR f.FREEBOARD_CONTENT LIKE '%' || #{keyword} || '%'
</select>

<!-- 댓글 목록 -->
<select id="selectCommentsByBoardId">
    SELECT c.*, u.USER_NICKNAME
    FROM TB_FREEBOARD_COMMENT c
    LEFT JOIN TB_USER u ON c.USER_ID = u.USER_ID
    WHERE c.FREEBOARD_ID = #{freeboardId}
    ORDER BY c.CREATED_AT ASC
</select>
```

---

##### 1-5. FreeboardService + FreeboardServiceImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/freeboard/service/`

**주요 메서드:**
```java
// 목록 및 검색 (페이징)
Map<String, Object> getList(int page);
Map<String, Object> searchPosts(String keyword, int page);

// 게시글 CRUD
FreeboardVO getDetail(int freeboardId);
boolean createFreeboard(FreeboardVO freeboard);
boolean updateFreeboard(FreeboardVO freeboard);
boolean deleteFreeboard(int freeboardId);
boolean checkAuthor(int freeboardId, String userId);

// 댓글
List<FreeboardCommentVO> getComments(int freeboardId);
boolean createComment(FreeboardCommentVO comment);
boolean deleteComment(int commentId, String userId);
```

---

##### 1-6. FreeboardController + FreeboardControllerImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/freeboard/controller/`

**구현 API:**
```java
// 게시글
GET  /api/freeboard/list?page={page}              // 목록
GET  /api/freeboard/search?keyword={}&page={}     // 검색
GET  /api/freeboard/{id}                          // 상세
GET  /api/freeboard/{id}/check-author             // 작성자 확인
POST /api/freeboard                               // 작성
PUT  /api/freeboard/{id}                          // 수정
DELETE /api/freeboard/{id}                        // 삭제

// 댓글
GET  /api/freeboard/{id}/comments                 // 댓글 목록
POST /api/freeboard/{id}/comments                 // 댓글 작성
DELETE /api/freeboard/comments/{commentId}        // 댓글 삭제
```

---

#### 2. Frontend 연동 (React) - 4개 파일

##### 2-1. FreeBoard.js ✅
**경로:** `frontend/moc-pro/src/components/another_board/FreeBoard.js`

**수정 내용:**
- ✅ axios 목록 조회 API 연동
- ✅ axios 검색 API 연동
- ✅ 페이징 처리

---

##### 2-2. FreeBoardDetail.js ✅
**경로:** `frontend/moc-pro/src/components/another_board/FreeBoardDetail.js`

**수정 내용:**
- ✅ axios 상세 조회 API 연동
- ✅ axios 댓글 목록 조회 API 연동
- ✅ axios 삭제 API 연동
- ✅ 작성자 확인 API 연동 (수정/삭제 버튼 표시 여부)

---

##### 2-3. FreeBoardWrite.js ✅
**경로:** `frontend/moc-pro/src/components/another_board/FreeBoardWrite.js`

**수정 내용:**
- ✅ axios 작성 API 연동
- ✅ axios 수정 API 연동
- ✅ 수정 모드 데이터 로드

---

##### 2-4. CommentBoard.js ✅
**경로:** `frontend/moc-pro/src/components/common/CommentBoard.js`

**수정 내용:**
- ✅ axios 댓글 작성 API 연동
- ✅ boardType, postId props 사용
- ✅ 작성 후 페이지 새로고침

---

#### 3. 페이지네이션 규칙

**모든 게시판 공통:**
- ✅ 페이지당 게시글 수: 10개
- ✅ 페이지 번호: 1부터 시작
- ✅ Oracle OFFSET/FETCH NEXT 사용
- ✅ totalPage 계산: Math.ceil(totalCount / 10)
- ✅ 응답 형식: { posts, totalPage, currentPage, totalCount }

---

#### 4. 테스트 완료 ✅

**자유게시판 테스트 항목:**
- ✅ 목록 조회 (페이징 10개/페이지)
- ✅ 검색 (제목 + 내용)
- ✅ 상세 조회
- ✅ 작성
- ✅ 수정 (작성자 본인만)
- ✅ 삭제 (작성자 본인만)
- ✅ 댓글 목록 조회
- ✅ 댓글 작성
- ✅ 작성자 확인 (수정/삭제 버튼 표시)

---

### 페이지네이션 규칙 지침서 추가 ✅

#### 개요
**목적:** 모든 게시판의 페이지네이션 구현 표준화

**수정일:** 2025-11-12

**추가 위치:** `.github/copilot-instructions.md`

---

#### 주요 내용

**1. 기본 규칙**
- 페이지당 게시글 수: 10개
- 페이지 번호: 1부터 시작
- 응답 데이터: posts, totalPage, currentPage, totalCount

**2. SQL 쿼리 패턴**
- OFFSET/FETCH NEXT 사용 (Oracle 12c 이상)
- selectTotalCount 쿼리 필수

**3. Service 레이어 패턴**
- ITEMS_PER_PAGE = 10
- offset = (page - 1) × 10
- totalPage = Math.ceil(totalCount / 10)

**4. Controller 응답 형식**
```json
{
  "success": true,
  "data": {
    "posts": [...],
    "totalPage": 5,
    "currentPage": 1,
    "totalCount": 42
  }
}
```

**5. Frontend 처리 패턴**
- useEffect로 currentPage 의존성
- PageNation 컴포넌트 사용
- URL 파라미터 (page=1)

---

### 26단계: User 모듈 Backend API 전체 구현 ✅

#### 개요
**목적:** User 관련 모든 API 구현 및 Frontend 연동

**수정일:** 2025-11-11

---

#### 1. Backend API 구현 (Spring Boot)

##### 1-1. UserController.java 인터페이스 수정 ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/user/controller/UserController.java`

**수정 내용:**
- ✅ 아이디 찾기 API 추가
- ✅ 비밀번호 재설정 API 추가
- ✅ 사용자 정보 조회 API 추가
- ✅ 사용자 정보 수정 API 추가
- ✅ 회원 탈퇴 API 추가

**추가된 API:**
```java
@PostMapping("/api/auth/find-id")
ResponseEntity<Map<String, Object>> findUserId(@RequestBody Map<String, String> request);

@PostMapping("/api/auth/reset-password")
ResponseEntity<Map<String, Object>> resetPassword(@RequestBody Map<String, String> request);

@GetMapping("/api/user/{userId}")
ResponseEntity<Map<String, Object>> getUserInfo(@PathVariable String userId);

@PutMapping("/api/user/{userId}")
ResponseEntity<Map<String, Object>> updateUserInfo(@PathVariable String userId, 
                                                    @RequestBody Map<String, String> request,
                                                    HttpSession session);

@DeleteMapping("/api/user/{userId}")
ResponseEntity<Map<String, Object>> withdrawUser(@PathVariable String userId, HttpSession session);
```

---

##### 1-2. UserControllerImpl.java 구현 ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/user/controller/UserControllerImpl.java`

**주요 수정:**
- ✅ 5개 API 구현 완료
- ✅ **API 경로 수정**: 인터페이스와 일치하도록 전체 경로 명시
- ✅ 권한 검증 (본인만 수정/탈퇴 가능)
- ✅ 에러 처리 및 응답 형식 표준화

**Before (문제):**
```java
@PostMapping("/check-id")  // ❌ 경로 불완전
```

**After (수정):**
```java
@PostMapping("/api/auth/check-id")  // ✅ 전체 경로
```

---

##### 1-3. UserService + UserServiceImpl ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/user/service/`

**추가 메서드:**
- ✅ `getUserInfo()` - 사용자 정보 조회
- ✅ `updateUserInfo()` - 닉네임/이메일 수정 + 중복 체크
- ✅ `withdrawUser()` - 논리 삭제 (user_status='WITHDRAWN')
- ✅ `findUserId()` - 이름+이메일로 아이디 찾기 + 마스킹
- ✅ `resetPassword()` - 사용자 확인 (실제 재설정 안 함)

**아이디 마스킹 로직:**
```java
// 예: "hong1234" → "hong****" (앞4자 + **** + 뒤3자)
String masked = userId.substring(0, 4) + "****";
if (userId.length() > 4) {
    masked += userId.substring(Math.max(4, userId.length() - 3));
}
```

---

##### 1-4. UserDAO + UserDAOImpl + User.xml ✅
**경로:** 
- `backend/pro/src/main/java/com/moc/pro/user/dao/`
- `backend/pro/src/main/resources/mybatis/mappers/User.xml`

**추가 쿼리:**
```sql
-- 사용자 정보 조회
SELECT user_id, user_name, user_nickname, user_email, user_isadmin
FROM tb_user
WHERE user_id = #{userId} AND user_status = 'ACTIVE'

-- 닉네임 중복 확인 (본인 제외)
SELECT COUNT(*) FROM tb_user
WHERE user_nickname = #{nickname}
AND user_id != #{userId}
AND user_status = 'ACTIVE'

-- 회원 탈퇴 (논리 삭제)
UPDATE tb_user
SET user_status = 'WITHDRAWN',
    withdrawn_at = SYSTIMESTAMP
WHERE user_id = #{userId}
```

---

#### 2. Frontend 연동 (React)

##### 2-1. MyPage.js ✅
**경로:** `frontend/moc-pro/src/components/info/MyPage.js`

**수정 내용:**
- ✅ 사용자 정보 조회 axios 추가
- ✅ 사용자 정보 수정 axios 추가
- ✅ 회원 탈퇴 axios 추가
- ✅ **에러 처리 개선**: 중복 에러 시 구체적 메시지 표시

**Before (문제):**
```javascript
catch (error) {
  alert('회원 정보 수정 중 오류가 발생했습니다.');  // ❌ 일반 메시지만
}
```

**After (해결):**
```javascript
catch (error) {
  if (error.response && error.response.data) {
    alert(error.response.data.message);  // ✅ "이미 사용 중인 닉네임입니다."
    
    // errorField가 있으면 포커스
    if (error.response.data.errorField) {
      const errorInput = document.querySelector(`input[name="${error.response.data.errorField}"]`);
      if (errorInput) errorInput.focus();
    }
  }
}
```

---

##### 2-2. FindAccount.js ✅
**경로:** `frontend/moc-pro/src/components/info/FindAccount.js`

**수정 내용:**
- ✅ 아이디 찾기 axios 추가
- ✅ 비밀번호 재설정 axios 추가
- ✅ 결과 메시지 동적 표시

**구현 코드:**
```javascript
// 아이디 찾기
const response = await axios.post('http://localhost:18880/api/auth/find-id', {
  name: data.name,
  email: data.email
});

if (response.data.success) {
  setResultMessage(response.data.message);  // "OOO님의 아이디는 hong****입니다."
  setSubmitted(true);
}
```

---

##### 2-3. authUtils.js 정리 ✅
**경로:** `frontend/moc-pro/src/components/common/authUtils.js`

**수정 내용:**
- ❌ 페이지 전용 함수 제거 (getUserInfo, updateUserInfo, withdrawUser, findUserId, resetPassword)
- ✅ 공통 유틸리티만 유지 (checkLogin, getUserId, login, logout, signup, checkIdDuplicate)

**역할 명확화:**
- authUtils.js = 공통 유틸리티 (여러 곳에서 사용)
- 각 페이지 = 전용 axios 코드 (// axios 영역에 직접 작성)

---

#### 3. 테스트 완료 ✅

**테스트 항목:**
- ✅ 사용자 정보 조회 (MyPage)
- ✅ 사용자 정보 수정 (닉네임/이메일)
- ✅ 중복 에러 처리 ("이미 사용 중인 닉네임입니다.")
- ✅ 아이디 찾기 (마스킹 표시)
- ✅ 비밀번호 재설정 (메시지만 표시)
- ✅ 회원 탈퇴 (로그아웃 + 메인 이동)

**최종 결과:**
```
✅ 총 10개 User API 정상 작동
✅ 에러 처리 개선 완료
✅ Frontend-Backend 연동 완료
```

---

### 24단계: 로그인 시스템 구현 (Spring Boot + React) ✅

#### 개요
**목적:** 사용자 인증 시스템 구현 (로그인, 로그인 확인, 로그아웃, 비밀번호 암호화)

**수정일:** 2025-11-11

---

#### 1. 백엔드 (Spring Boot) - 9개 파일

##### 1-1. UserVO.java ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/user/vo/UserVO.java`

**수정 내용:**
- ✅ 사용자 데이터 객체 생성
- ✅ TB_USER 테이블 매핑
- ✅ Getter/Setter 구현

**주요 필드:**
```java
- userId (String) - 사용자 ID
- userEmail (String) - 이메일
- userName (String) - 이름
- userNickname (String) - 닉네임
- userPwd (String) - 비밀번호 (BCrypt 해시)
- userStatus (String) - 상태 (ACTIVE/WITHDRAWN)
- userIsadmin (String) - 관리자 여부 (Y/N)
```

---

##### 1-2. UserDAO.java + UserDAOImpl.java ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/user/dao/`

**수정 내용:**
- ✅ `selectUserByIdAndPwd()` - 아이디와 비밀번호로 조회
- ✅ `selectUserById()` - 아이디로만 조회 (BCrypt용)

**Before/After:**
```java
// Before (평문 비교)
UserVO selectUserByIdAndPwd(String userId, String userPwd);

// After (BCrypt용 추가)
UserVO selectUserById(String userId);
```

---

##### 1-3. User.xml ✅
**경로:** `backend/pro/src/main/resources/mybatis/mappers/User.xml`

**수정 내용:**
- ✅ selectUserByIdAndPwd SQL 쿼리 작성
- ✅ selectUserById SQL 쿼리 추가

**핵심 SQL:**
```xml
<select id="selectUserById" parameterType="string" resultType="UserVO">
    SELECT * FROM tb_user
    WHERE user_id = #{userId}
      AND user_status = 'ACTIVE'
</select>
```

---

##### 1-4. UserService.java + UserServiceImpl.java ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/user/service/`

**수정 내용:**
- ✅ login() 메서드 구현
- ✅ BCryptPasswordEncoder 주입
- ✅ 비밀번호 검증 로직 (passwordEncoder.matches())
- ✅ Session에 userId, isAdmin 저장

**핵심 로직:**
```java
// 1. DB에서 사용자 조회 (아이디만)
UserVO user = userDAO.selectUserById(userId);

// 2. BCrypt로 비밀번호 검증
if (!passwordEncoder.matches(password, user.getUserPwd())) {
    return false;
}

// 3. Session 저장
session.setAttribute("userId", user.getUserId());
session.setAttribute("isAdmin", "Y".equals(user.getUserIsadmin()));
```

---

##### 1-5. UserController.java + UserControllerImpl.java ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/user/controller/`

**수정 내용:**
- ✅ POST /api/auth/login - 로그인
- ✅ GET /api/auth/check - 로그인 확인
- ✅ POST /api/auth/logout - 로그아웃
- ✅ ResponseEntity<Map<String, Object>> 반환

**API 스펙:**
```java
// 로그인
POST /api/auth/login
Request: { userid, password }
Response: { success, userId, isAdmin, message }

// 로그인 확인
GET /api/auth/check
Response: { isLoggedIn, userId, isAdmin }

// 로그아웃
POST /api/auth/logout
Response: { success, message }
```

---

##### 1-6. PasswordConfig.java ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/config/PasswordConfig.java`

**수정 내용:**
- ✅ BCryptPasswordEncoder Bean 등록
- ✅ @Configuration 어노테이션

**핵심 코드:**
```java
@Configuration
public class PasswordConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

---

##### 1-7. WebConfig.java ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/WebConfig.java`

**수정 내용:**
- ✅ CORS 설정 (localhost:3010 허용)

---

#### 2. 프론트엔드 (React) - 1개 파일

##### 2-1. authUtils.js ✅
**경로:** `frontend/moc-pro/src/components/common/authUtils.js`

**수정 내용:**
- ✅ axios import 추가
- ✅ API_BASE_URL 정의 (http://localhost:18880/api/auth)
- ✅ axios.defaults.withCredentials = true (Session 쿠키 전송)
- ✅ 5개 함수 구현

**구현된 함수:**
```javascript
// 1. 로그인
export const login = async (credentials) => {
    const response = await axios.post(`${API_BASE_URL}/login`, credentials);
    return response.data.success;
};

// 2. 로그인 확인
export const checkLogin = async () => {
    const response = await axios.get(`${API_BASE_URL}/check`);
    return response.data.isLoggedIn;
};

// 3. 사용자 ID 가져오기
export const getUserId = async () => {
    const response = await axios.get(`${API_BASE_URL}/check`);
    return response.data.userId;
};

// 4. 관리자 여부 확인
export const checkAdmin = async () => {
    const response = await axios.get(`${API_BASE_URL}/check`);
    return response.data.isAdmin;
};

// 5. 로그아웃
export const logout = async () => {
    const response = await axios.post(`${API_BASE_URL}/logout`);
    return response.data.success;
};
```

---

#### 3. 데이터베이스 (Oracle)

##### 3-1. TB_USER 테이블 사용

---

### 25단계: 회원가입 시스템 구현 (Spring Boot + React) ✅

#### 개요
**목적:** 회원가입 및 중복 확인 기능 구현

**수정일:** 2025-11-11

---

#### 1. 백엔드 (Spring Boot) - 4개 파일 수정

##### 1-1. UserController.java + UserControllerImpl.java ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/user/controller/`

**수정 내용:**
- ✅ POST /api/auth/signup - 회원가입 API
- ✅ POST /api/auth/check-id - 아이디 중복 확인 API
- ✅ 입력 검증 로직 추가
- ✅ ResponseEntity<Map<String, Object>> 반환

**API 스펙:**
```java
// 회원가입
POST /api/auth/signup
Request: { userid, password, username, nickname, email }
Response: { success, message, errorField(optional) }

// 아이디 중복 확인
POST /api/auth/check-id
Request: { userid }
Response: { success, message, available }
```

**핵심 코드:**
```java
@PostMapping("/signup")
public ResponseEntity<Map<String, Object>> signup(@RequestBody Map<String, String> request) {
    // 입력 검증
    if (userid == null || userid.trim().isEmpty()) {
        response.put("success", false);
        response.put("message", "모든 필드를 입력해주세요.");
        return ResponseEntity.badRequest().body(response);
    }
    
    // 회원가입 처리
    Map<String, Object> result = userService.signup(user);
    return ResponseEntity.ok(result);
}
```

---

##### 1-2. UserService.java + UserServiceImpl.java ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/user/service/`

**수정 내용:**
- ✅ signup() 메서드 구현
- ✅ checkUserIdDuplicate() 구현
- ✅ checkEmailDuplicate() 구현
- ✅ checkNicknameDuplicate() 구현
- ✅ BCrypt 비밀번호 암호화

**핵심 로직:**
```java
public Map<String, Object> signup(UserVO user) {
    Map<String, Object> response = new HashMap<>();
    
    // 1. 아이디 중복 확인
    if (checkUserIdDuplicate(user.getUserId()) > 0) {
        response.put("success", false);
        response.put("message", "이미 사용 중인 아이디입니다.");
        response.put("errorField", "userid");
        return response;
    }
    
    // 2. 이메일 중복 확인
    if (checkEmailDuplicate(user.getUserEmail()) > 0) {
        response.put("success", false);
        response.put("message", "이미 사용 중인 이메일입니다.");
        response.put("errorField", "email");
        return response;
    }
    
    // 3. 닉네임 중복 확인
    if (checkNicknameDuplicate(user.getUserNickname()) > 0) {
        response.put("success", false);
        response.put("message", "이미 사용 중인 닉네임입니다.");
        response.put("errorField", "nickname");
        return response;
    }
    
    // 4. 비밀번호 암호화
    String encryptedPwd = passwordEncoder.encode(user.getUserPwd());
    user.setUserPwd(encryptedPwd);
    
    // 5. DB 저장
    int result = userDAO.insertUser(user);
    
    if (result > 0) {
        response.put("success", true);
        response.put("message", "회원가입이 완료되었습니다.");
    } else {
        response.put("success", false);
        response.put("message", "회원가입에 실패했습니다.");
    }
    
    return response;
}
```

---

##### 1-3. UserDAO.java + UserDAOImpl.java ✅
**경로:** `backend/pro/src/main/java/com/moc/pro/user/dao/`

**수정 내용:**
- ✅ insertUser() - 사용자 등록
- ✅ checkUserIdDuplicate() - 아이디 중복 확인
- ✅ checkEmailDuplicate() - 이메일 중복 확인
- ✅ checkNicknameDuplicate() - 닉네임 중복 확인

**메서드 시그니처:**
```java
int insertUser(UserVO user);
int checkUserIdDuplicate(String userId);
int checkEmailDuplicate(String email);
int checkNicknameDuplicate(String nickname);
```

---

##### 1-4. User.xml ✅
**경로:** `backend/pro/src/main/resources/mybatis/mappers/User.xml`

**수정 내용:**
- ✅ insertUser SQL 쿼리 작성
- ✅ checkUserIdDuplicate SQL 쿼리 작성
- ✅ checkEmailDuplicate SQL 쿼리 작성
- ✅ checkNicknameDuplicate SQL 쿼리 작성

**핵심 SQL:**
```xml
<!-- 회원가입 -->
<insert id="insertUser" parameterType="UserVO">
    INSERT INTO tb_user (
        user_id, user_email, user_name, user_nickname, user_pwd,
        user_status, user_isadmin, created_at, created_by
    ) VALUES (
        #{userId}, #{userEmail}, #{userName}, #{userNickname}, #{userPwd},
        'ACTIVE', 'N', SYSTIMESTAMP, #{userId}
    )
</insert>

<!-- 아이디 중복 확인 -->
<select id="checkUserIdDuplicate" parameterType="string" resultType="int">
    SELECT COUNT(*) FROM tb_user
    WHERE user_id = #{userId}
      AND user_status = 'ACTIVE'
</select>

<!-- 이메일 중복 확인 -->
<select id="checkEmailDuplicate" parameterType="string" resultType="int">
    SELECT COUNT(*) FROM tb_user
    WHERE user_email = #{email}
      AND user_status = 'ACTIVE'
</select>

<!-- 닉네임 중복 확인 -->
<select id="checkNicknameDuplicate" parameterType="string" resultType="int">
    SELECT COUNT(*) FROM tb_user
    WHERE user_nickname = #{nickname}
      AND user_status = 'ACTIVE'
</select>
```

---

#### 2. 프론트엔드 (React) - 2개 파일 수정

##### 2-1. authUtils.js ✅
**경로:** `frontend/moc-pro/src/components/common/authUtils.js`

**수정 내용:**
- ✅ signup() 함수 추가
- ✅ checkIdDuplicate() 함수 추가

**구현된 함수:**
```javascript
/**
 * 회원가입
 * @param {Object} signupData - { userid, password, username, nickname, email }
 * @returns {Promise<Object>} { success, message, errorField }
 */
export const signup = async (signupData) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/signup`, signupData);
        return response.data;
    } catch (error) {
        console.error('회원가입 실패:', error);
        if (error.response && error.response.data) {
            return error.response.data;
        }
        return {
            success: false,
            message: '회원가입 중 오류가 발생했습니다.'
        };
    }
};

/**
 * 아이디 중복 확인
 * @param {string} userid - 사용자 ID
 * @returns {Promise<Object>} { success, message, available }
 */
export const checkIdDuplicate = async (userid) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/check-id`, { userid });
        return response.data;
    } catch (error) {
        console.error('아이디 중복 확인 실패:', error);
        if (error.response && error.response.data) {
            return error.response.data;
        }
        return {
            success: false,
            message: '중복 확인 중 오류가 발생했습니다.',
            available: false
        };
    }
};
```

---

##### 2-2. RegisterForm.js ✅
**경로:** `frontend/moc-pro/src/components/info/RegisterForm.js`

**수정 내용:**
- ✅ data 상태로 폼 데이터 통합 관리
- ✅ acceptId 상태로 아이디 중복 확인 여부 관리
- ✅ handleCheckId() - 아이디 중복 확인 함수
- ✅ handleOnSubmit() - 회원가입 제출 함수
- ✅ signup(), checkIdDuplicate() 함수 호출

**핵심 코드:**
```javascript
// 상태 관리
const [data, setData] = useState({
    userid: '', pwd: '', pwd2: '', username: '', nickname: '', email: ''
});
const [acceptId, setAcceptId] = useState(false);
const [agree, setAgree] = useState(false);

// 아이디 중복 확인
const handleCheckId = async () => {
    if (!data.userid || data.userid.trim() === '') {
        alert('아이디를 입력해주세요.');
        return;
    }
    
    const result = await checkIdDuplicate(data.userid);
    
    if (result.success && result.available) {
        alert(result.message);
        setAcceptId(true);
    } else {
        alert(result.message);
        setAcceptId(false);
    }
};

// 회원가입 제출
const handleOnSubmit = async (e) => {
    e.preventDefault();
    
    if (!agree) {
        alert('이용약관에 동의해주세요.');
        return;
    }
    
    if (!acceptId) {
        alert('아이디 중복 확인을 해주세요.');
        return;
    }
    
    if (data.pwd !== data.pwd2) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }
    
    // 회원가입 API 호출
    const signupData = {
        userid: data.userid,
        password: data.pwd,
        username: data.username,
        nickname: data.nickname,
        email: data.email
    };
    
    const result = await signup(signupData);
    
    if (result.success) {
        alert(result.message);
        navigate('/login');
    } else {
        alert(result.message);
        // errorField가 있으면 해당 필드에 포커스
        if (result.errorField) {
            const errorInput = document.querySelector(`input[name="${result.errorField}"]`);
            if (errorInput) {
                errorInput.focus();
            }
        }
    }
};
```

---

#### 3. 주요 기능

1. **회원가입**
   - 필수 입력: 아이디, 비밀번호, 이름, 닉네임, 이메일
   - 비밀번호 확인 검증
   - 이용약관 동의 필수
   - BCrypt 암호화 적용

2. **중복 확인**
   - 아이디 중복 확인 (필수)
   - 이메일 중복 확인 (자동)
   - 닉네임 중복 확인 (자동)

3. **유효성 검증**
   - 빈 값 체크
   - 비밀번호 일치 확인
   - 중복 확인 완료 여부 체크

4. **에러 처리**
   - errorField 반환으로 문제 필드 표시
   - 실패 시 해당 필드로 포커스 이동

---
**테이블:** `TB_USER`

**주요 컬럼:**
- USER_ID (PK)
- USER_EMAIL (UNIQUE)
- USER_NAME
- USER_NICKNAME (UNIQUE)
- USER_PWD (BCrypt 해시)
- USER_STATUS (ACTIVE/WITHDRAWN)
- USER_ISADMIN (Y/N)

##### 3-2. 관리자 계정 생성 ✅
```sql
INSERT INTO tb_user (
    user_id, user_email, user_name, user_nickname,
    user_pwd, user_status, user_isadmin,
    created_at, created_by
) VALUES (
    'admin', 'admin@moc.com', '관리자', '관리자',
    '$2a$10$Paoqsmswxveew9hpgw.snOZMNA7D4GGd4ZzKfv5rLDN9E6kLvW4gu',
    'ACTIVE', 'Y',
    SYSTIMESTAMP, 'admin'
);
COMMIT;
```

---

#### 4. 비밀번호 암호화 (BCrypt)

**Before (위험):**
```
DB: user_pwd = "admin" (평문)
해커가 DB 탈취 시 즉시 사용 가능
```

**After (안전):**
```
DB: user_pwd = "$2a$10$Paoqsmswxveew..." (BCrypt 해시)
원본 비밀번호 알 수 없음
```

**작동 방식:**
```java
// 로그인 시
passwordEncoder.matches("admin", "$2a$10$...") → true/false
```

---

#### 5. 테스트 결과 ✅

**테스트 계정:**
- 아이디: admin
- 비밀번호: admin
- 권한: 관리자 (Y)

**테스트 결과:**
- ✅ 로그인 성공
- ✅ Session 저장 확인
- ✅ BCrypt 검증 정상 작동
- ✅ React axios 통신 정상

---

#### 6. 주요 개선 사항

1. **보안 강화**
   - BCrypt 단방향 암호화
   - Salt 자동 생성
   - DB 탈취 시에도 비밀번호 보호

2. **Session 기반 인증**
   - Spring Boot HttpSession 사용
   - withCredentials: true (쿠키 전송)

3. **API 표준화**
   - ResponseEntity 사용
   - 일관된 응답 형식 (success, message)

4. **코드 품질**
   - 인터페이스 + 구현체 패턴
   - 상수 정의 (KEY_SUCCESS, KEY_MESSAGE 등)
   - 에러 처리 포함

---

### 23단계: 마이페이지 프로필 이미지 보존 문제 해결 (1개 파일) ✅

#### 개요
**목적:** 페이지네이션 조작 시 업로드한 프로필 이미지가 초기화되는 버그 수정

**수정일:** 2025-11-11

**문제 상황:**
```
1. 프로필 이미지 업로드
   ↓
2. 내가 쓴 글 페이지 변경
   ↓
3. useEffect 재실행
   ↓
4. userInfo 초기화 (profileImage: 기본이미지)
   ↓
5. 업로드한 이미지 사라짐 ❌
```

**원인:**
- 하나의 useEffect에서 모든 데이터 조회
- 의존성 배열에 `myPostsPage`, `interestedPostsPage` 포함
- 페이지네이션 변경 → useEffect 재실행 → userInfo 초기화

---

#### 수정 파일

**MyPage.js** ✅
**경로:** components/info/MyPage.js

**수정 내용:**
- ✅ useEffect를 3개로 분리 (관심사 분리)
- ✅ 사용자 정보 조회는 최초 1회만
- ✅ 게시글 조회는 페이지 변경마다

**Before:**
```javascript
useEffect(() => {
  const userId = sessionStorage.getItem('userId');
  
  if(!userId) {
    alert('로그인이 필요합니다.');
    navigate('/login');
    return;
  }

  // 사용자 정보 조회
  setUserInfo({...}); // ← 페이지 변경마다 초기화됨!
  
  // 내가 쓴 글 조회
  setMyPosts([...]);
  
  // 관심 글 조회
  setInterestedPosts([...]);
  
}, [navigate, myPostsPage, interestedPostsPage]);
```

**After:**
```javascript
// 1. 사용자 정보 조회 (최초 1회만)
useEffect(() => {
  const userId = sessionStorage.getItem('userId');
  
  if(!userId) {
    alert('로그인이 필요합니다.');
    navigate('/login');
    return;
  }

  // axios 영역 - 사용자 정보 조회
  setUserInfo({...});
}, [navigate]); // ← myPostsPage, interestedPostsPage 제거!

// 2. 내가 쓴 글 조회 (myPostsPage 변경마다)
useEffect(() => {
  const userId = sessionStorage.getItem('userId');
  if(!userId) return;

  // axios 영역 - 내가 쓴 글 조회
  setMyPosts([...]);
  setMyPostsTotalPage(3);
}, [myPostsPage]);

// 3. 관심 글 조회 (interestedPostsPage 변경마다)
useEffect(() => {
  const userId = sessionStorage.getItem('userId');
  if(!userId) return;
  
  // axios 영역 - 관심 글 조회
  setInterestedPosts([...]);
  setInterestedPostsTotalPage(2);
}, [interestedPostsPage]);
```

**효과:**
- ✅ 프로필 이미지 업로드 후 유지됨
- ✅ 페이지네이션 조작해도 이미지 보존
- ✅ 명확한 관심사 분리
- ✅ 불필요한 리렌더링 방지
- ✅ 성능 최적화 (필요한 데이터만 갱신)

---

### 22단계: 완료 상태일 때 신청하기 버튼 숨김 처리 (2개 파일) ✅

#### 개요
**목적:** 나눔완료/구인완료 상태일 때 신청하기 버튼 표시하지 않기

**수정일:** 2025-11-11

**핵심 로직:**
```
상태 체크 조건 추가
  ↓
작성자가 아님 && 완료 상태가 아님
  ↓
신청하기 버튼 표시
```

---

#### 수정 파일

**1. ShareToolDetail.js** ✅
**경로:** components/share_tool_board/ShareToolDetail.js

**수정 내용:**
- ✅ 신청하기 버튼 렌더링 조건에 `status !== '나눔완료'` 추가

**Before:**
```javascript
{post.userid !== currentUserId && (
    <button type="button" onClick={handleRequest}>신청하기</button>
)}
```

**After:**
```javascript
{post.userid !== currentUserId && post.status !== '나눔완료' && (
    <button type="button" onClick={handleRequest}>신청하기</button>
)}
```

**효과:**
- 나눔완료 상태일 때 신청하기 버튼 숨김 ✅
- 작성자에게는 수정/삭제만 표시 ✅

---

**2. ShoppingBoardDetailModal.js** ✅
**경로:** components/with_shopping_board/ShoppingBoardDetailModal.js

**수정 내용:**
- ✅ 신청하기 버튼 렌더링 조건에 `status !== '구인완료'` 추가

**Before:**
```javascript
{data.userid !== currentUserId && (
    <button type='button' onClick={handleApply}>신청하기</button>
)}
```

**After:**
```javascript
{data.userid !== currentUserId && data.status !== '구인완료' && (
    <button type='button' onClick={handleApply}>신청하기</button>
)}
```

**효과:**
- 구인완료 상태일 때 신청하기 버튼 숨김 ✅
- 작성자에게는 수정/삭제만 표시 ✅

---

### 21단계: 신청하기 버튼 클릭 시 채팅 모달 연동 (3개 파일) ✅

#### 개요
**목적:** 요리도구 나눔/같이 장보기 신청 시 채팅창 모달 띄우기

**수정일:** 2025-11-10

**핵심 흐름:**
```
신청하기 버튼 클릭
  ↓
로그인 체크
  ↓
채팅방 생성/조회 (axios)
  ↓
채팅 모달 열기
  ↓
메시지 주고받기
  ↓
수락/거절 처리
```

---

#### 수정 파일

**1. ChatModal.js** ✅
**경로:** components/info/ChatModal.js

**주요 기능:**
- ✅ Props 받기 (partnerNickname, postId, postType, roomId)
- ✅ 메시지 목록 동적 관리
- ✅ 메시지 전송 후 목록 업데이트
- ✅ 수락/거절 버튼 로직
- ✅ 게시글 이동 로직
- ✅ 채팅방 삭제 로직

**핵심 코드:**
```javascript
export function ChatModal({
    onClose, 
    partnerNickname = '상대방',
    postId,
    postType,
    roomId
}){
    const [messages, setMessages] = useState([]);
    
    useEffect(() => {
        if (roomId) {
            // axios 영역 - 메시지 목록 불러오기
            // GET /api/chat/rooms/{roomId}/messages
            setMessages([...샘플 데이터]);
        }
    }, [roomId]);
    
    const handleSubmit = (e) => {
        e.preventDefault();
        // axios 영역 - 메시지 전송
        // POST /api/chat/rooms/{roomId}/messages
        setMessages(prev => [...prev, newMsg]);
        setMessage('');
    };
    
    const handleAccept = () => {
        // axios 영역 - 수락
        // POST /api/chat/rooms/{roomId}/accept
    };
    
    const handleReject = () => {
        // axios 영역 - 거절
        // POST /api/chat/rooms/{roomId}/reject
    };
}
```

---

**2. ShareToolDetail.js** ✅
**경로:** components/share_tool_board/ShareToolDetail.js

**수정 내용:**
- ✅ ChatModal import 추가
- ✅ 채팅 모달 상태 추가
- ✅ handleRequest 수정 (채팅방 생성 → 모달 열기)

**Before:**
```javascript
const handleRequest = () => {
    // axios 영역
    console.log('신청:', postid);
    alert('신청이 완료되었습니다.');
};
```

**After:**
```javascript
const [isChatModalOpen, setIsChatModalOpen] = useState(false);
const [chatRoomId, setChatRoomId] = useState(null);

const handleRequest = () => {
    const userId = sessionStorage.getItem('userId');
    if (!userId) {
        alert('로그인이 필요합니다.');
        return;
    }
    // axios 영역 - 채팅방 생성/조회
    // POST /api/chat/rooms
    const sampleRoomId = `room_${postid}_${Date.now()}`;
    setChatRoomId(sampleRoomId);
    setIsChatModalOpen(true);
};

// Modal 렌더링
<Modal isOpen={isChatModalOpen} onRequestClose={closeChatModal}>
    <ChatModal 
        onClose={closeChatModal}
        partnerNickname={post.usernickname}
        postId={postid}
        postType="sharetool"
        roomId={chatRoomId}
    />
</Modal>
```

---

**3. ShoppingBoardDetailModal.js** ✅
**경로:** components/with_shopping_board/ShoppingBoardDetailModal.js

**수정 내용:**
- ✅ ChatModal import 추가
- ✅ 채팅 모달 상태 추가
- ✅ handleApply 수정 (채팅방 생성 → 모달 열기)

**Before:**
```javascript
const handleApply = () => {
    // axios 영역
    console.log('신청:', data.id);
    alert('신청이 완료되었습니다.');
};
```

**After:**
```javascript
const [isChatModalOpen, setIsChatModalOpen] = useState(false);
const [chatRoomId, setChatRoomId] = useState(null);

const handleApply = () => {
    const userId = sessionStorage.getItem('userId');
    if (!userId) {
        alert('로그인이 필요합니다.');
        return;
    }
    // axios 영역 - 채팅방 생성/조회
    // POST /api/chat/rooms
    const sampleRoomId = `room_${data.id}_${Date.now()}`;
    setChatRoomId(sampleRoomId);
    setIsChatModalOpen(true);
};

// Modal 렌더링
<Modal isOpen={isChatModalOpen} onRequestClose={closeChatModal}>
    <ChatModal 
        onClose={closeChatModal}
        partnerNickname={data.usernickname}
        postId={data.id}
        postType="shopping"
        roomId={chatRoomId}
    />
</Modal>
```

---

#### axios 연동 포인트

| API | Method | 설명 |
|-----|--------|------|
| `/api/chat/rooms` | POST | 채팅방 생성/조회 |
| `/api/chat/rooms/{roomId}/messages` | GET | 메시지 목록 조회 |
| `/api/chat/rooms/{roomId}/messages` | POST | 메시지 전송 |
| `/api/chat/rooms/{roomId}/accept` | POST | 신청 수락 |
| `/api/chat/rooms/{roomId}/reject` | POST | 신청 거절 |
| `/api/chat/rooms/{roomId}` | DELETE | 채팅방 삭제 |

---

#### 데이터 구조

**채팅방 생성 요청:**
```javascript
{
  postId: 1,
  postType: 'sharetool' | 'shopping',
  ownerId: 'owner123',
  applicantId: 'applicant456'
}
```

**채팅 메시지:**
```javascript
{
  id: 1,
  senderid: 'user123',
  sendernickname: '둘리',
  content: '안녕하세요!',
  timestamp: '14:30',
  ismine: true
}
```

---

### 20단계: 작성자에게 신청하기 버튼 숨김 처리 (2개 파일) ✅

#### 개요
**목적:** 요리도구 나눔/같이 장보기에서 작성자에게는 신청하기 버튼 보이지 않음

**수정일:** 2025-11-10

**핵심 로직:**
```javascript
{post.userid !== currentUserId && (
    <button onClick={handleRequest}>신청하기</button>
)}
```

---

#### 수정 파일

**1. ShareToolDetail.js** ✅
- 요리도구 나눔 상세 페이지
- 신청하기 버튼: 작성자가 아닐 때만 표시

**코드:**
```javascript
<div className={styles.EDbtn_div}>
    {post.userid === currentUserId && (
        <EDbtn onDelete={handleDelete} onEdit={handleEdit}/>
    )}
    {post.userid !== currentUserId && (
        <button type="button" onClick={handleRequest}>신청하기</button>
    )}
</div>
```

---

**2. ShoppingBoardDetailModal.js** ✅
- 같이 장보기 상세 모달
- 신청하기 버튼: 작성자가 아닐 때만 표시

**코드:**
```javascript
<div className={style.button_container}>
    <button onClick={onClose}>닫기</button>
    {data.userid === currentUserId && (
        <>
            <button onClick={handleDelete}>삭제하기</button>
            <button onClick={handleEdit}>수정하기</button>
        </>
    )}
    {data.userid !== currentUserId && (
        <button onClick={handleApply}>신청하기</button>
    )}
</div>
```

---

#### 버튼 표시 규칙

| 사용자 | 수정/삭제 | 신청하기 |
|--------|----------|----------|
| **작성자** | ✅ 표시 | ❌ 숨김 |
| **비작성자** | ❌ 숨김 | ✅ 표시 |

---

### 19단계: 로그인 필수 기능 체크 로직 추가 (14개 파일) ✅

#### 개요
**목적:** 신고/저장/댓글/신청/글작성 기능에 로그인 체크 로직 추가

**수정일:** 2025-11-10

**핵심 로직:**
```javascript
const handleAction = () => {
  const userId = sessionStorage.getItem('userId');
  if (!userId) {
    alert('로그인이 필요합니다.');
    return;
  }
  // 기존 로직 실행
};
```

---

#### 1단계: 상세 페이지 (6개 파일) ✅

**적용 기능:** handleReport, handleSave, handleRequest, handleApply

1. **FreeBoardDetail.js** - handleReport, handleSave
2. **ConvenienceCombDetail.js** - handleReport, handleSave
3. **ConvenienceReviewDetail.js** - handleReport, handleSave
4. **RecipeBoardDetail.js** - handleReport, handleSave
5. **ShareToolDetail.js** - handleReport, handleSave, handleRequest
6. **ShoppingBoardDetailModal.js** - handleReport, handleApply

---

#### 2단계: 공통 컴포넌트 (1개 파일) ✅

**CommentBoard.js**
- handleSubmit (댓글 추가)
- handleReportComment (댓글 신고)

**코드:**
```javascript
const handleSubmit = (e) => {
    e.preventDefault();
    
    const userId = sessionStorage.getItem('userId');
    if (!userId) {
        alert('로그인이 필요합니다.');
        return;
    }
    
    if(!commentData.content.trim()){
        alert('댓글 내용을 입력해주세요.');
        return;
    }
    
    // axios 영역
};

const handleReportComment = (commentId) => {
    const userId = sessionStorage.getItem('userId');
    if (!userId) {
        alert('로그인이 필요합니다.');
        return;
    }
    setSelectedCommentId(commentId);
    setIsReportModalOpen(true);
};
```

---

#### 3단계: 게시판 목록 페이지 (7개 파일) ✅

**적용 기능:** 글쓰기 버튼 클릭 시 로그인 체크

1. **RecipeBoard.js** - handleWrite
2. **FreeBoard.js** - moveWrite
3. **NoticeBoard.js** - moveWrite
4. **ConvenienceBoard.js** - openReviewWrite
5. **ConvenienceRecipeBoard.js** - openRecipeWrite
6. **ShareBoard.js** - handleWrite
7. **ShoppingBoard.js** - openWriteModal

**코드:**
```javascript
const handleWrite = () => {
    const userId = sessionStorage.getItem('userId');
    if (!userId) {
        alert('로그인이 필요합니다.');
        return;
    }
    navigate('/board/write');
};
```

---

### 적용된 기능 목록

| 기능 | 적용 위치 | 파일 수 |
|------|----------|---------|
| **신고하기** | 상세페이지 (6) + CommentBoard (1) | 7개 |
| **저장하기** | 상세페이지 (5) | 5개 |
| **댓글 추가** | CommentBoard | 1개 |
| **신청하기** | ShareToolDetail, ShoppingBoardDetailModal | 2개 |
| **글쓰기** | 게시판 목록 페이지 | 7개 |

**총 14개 파일 수정**

---

### 테스트 시나리오

1. **로그인 전 (userId 없음):**
   - 신고/저장/댓글/신청/글쓰기 버튼 클릭 시
   - → "로그인이 필요합니다." alert 표시
   - → 기능 실행 안 됨

2. **로그인 후 (userId 있음):**
   - 모든 기능 정상 작동
   - → 기존 로직대로 실행

---

### 18단계: 작성자 권한 기반 수정/삭제 버튼 표시 (6개 파일) ✅

#### 개요
**목적:** sessionStorage의 userId를 이용하여 작성자가 아닐 때 수정/삭제 버튼 숨기기

**수정일:** 2025-11-10

**핵심 로직:**
```javascript
const currentUserId = sessionStorage.getItem('userId');

// 샘플 데이터에 userid 추가
setData({ ...data, userid: 'testuser123' });

// 조건부 렌더링
{data.userid === currentUserId && (
    <EDbtn onDelete={handleDelete} onEdit={handleEdit}/>
)}
```

---

#### 1. FreeBoardDetail.js ✅
**경로:** `components/another_board/FreeBoardDetail.js`

**수정 내용:**
- ✅ currentUserId 상수 추가
- ✅ 샘플 데이터에 userid 필드 추가
- ✅ EDbtn 조건부 렌더링 (작성자만 표시)

**코드:**
```javascript
const currentUserId = sessionStorage.getItem('userId');

// useEffect 내부
setData({
    id: postid,
    userid: 'testuser123',  // axios에서 받아올 값
    title: '아무글',
    // ...
});

// 렌더링
{data.userid === currentUserId && (
    <EDbtn onDelete={handleDelete} onEdit={handleEdit}/>
)}
```

---

#### 2. ConvenienceCombDetail.js ✅
**경로:** `components/conv_board/ConvenienceCombDetail.js`

**수정 내용:**
- ✅ currentUserId 상수 추가
- ✅ 샘플 데이터에 userid 필드 추가
- ✅ EDbtn 조건부 렌더링

---

#### 3. ConvenienceReviewDetail.js ✅
**경로:** `components/conv_board/ConvenienceReviewDetail.js`

**수정 내용:**
- ✅ currentUserId 상수 추가
- ✅ 샘플 데이터에 userid 필드 추가
- ✅ EDbtn 조건부 렌더링

---

#### 4. RecipeBoardDetail.js ✅
**경로:** `components/recipe_board/RecipeBoardDetail.js`

**수정 내용:**
- ✅ currentUserId 상수 추가
- ✅ 샘플 데이터에 userid 필드 추가
- ✅ EDbtn 조건부 렌더링

---

#### 5. ShareToolDetail.js ✅
**경로:** `components/share_tool_board/ShareToolDetail.js`

**수정 내용:**
- ✅ currentUserId 상수 추가
- ✅ 샘플 데이터에 userid 필드 추가
- ✅ EDbtn 조건부 렌더링
- ✅ 신청하기 버튼은 항상 표시

**특이사항:** 
- ShareTool은 EDbtn이 별도 div에 위치
- 신청하기 버튼은 조건 없이 항상 표시

---

#### 6. ShoppingBoardDetailModal.js ✅
**경로:** `components/with_shopping_board/ShoppingBoardDetailModal.js`

**수정 내용:**
- ✅ currentUserId 상수 추가
- ✅ 샘플 데이터에 userid 필드 추가
- ✅ 삭제/수정 버튼 조건부 렌더링
- ✅ Fragment로 두 버튼 묶기

**특이사항:**
- 모달 형식이므로 버튼 레이아웃이 다름
- 닫기, 신청하기 버튼은 항상 표시
- 삭제, 수정 버튼만 작성자에게만 표시

**코드:**
```javascript
<div className={style.button_container}>
    <button onClick={onClose}>닫기</button>
    {data.userid === currentUserId && (
        <>
            <button onClick={handleDelete}>삭제하기</button>
            <button onClick={handleEdit}>수정하기</button>
        </>
    )}
    <button onClick={handleApply}>신청하기</button>
</div>
```

---

### 공통 적용 사항

**1. sessionStorage 사용:**
```javascript
const currentUserId = sessionStorage.getItem('userId');
```

**2. axios 영역에서 받아올 데이터 구조:**
```javascript
{
    id: number,
    userid: string,  // ← 작성자 ID (중요!)
    title: string,
    usernickname: string,
    // ... 기타 필드
}
```

**3. 조건부 렌더링 패턴:**
```javascript
{data.userid === currentUserId && <EDbtn />}
```

**4. 영향받지 않는 버튼:**
- RSbtn (신고/저장) - 항상 표시
- 신청하기 버튼 - 항상 표시
- 닫기 버튼 - 항상 표시

---

### 테스트 시나리오

1. **로그인 전:** 
   - sessionStorage에 userId 없음 → 수정/삭제 버튼 숨김

2. **로그인 후 (작성자):**
   - sessionStorage.userId === data.userid → 수정/삭제 버튼 표시

3. **로그인 후 (작성자 아님):**
   - sessionStorage.userId !== data.userid → 수정/삭제 버튼 숨김

---

### 17단계: 로그인/로그아웃 강제 마운트 버그 수정 (2개 파일) ✅

#### 1. Header.jsx ✅
**수정일:** 2025-11-10  
**경로:** `layout/Header.jsx`

**문제:**
- `useEffect`가 `location`만 의존성으로 가짐
- 같은 페이지에서 로그인/로그아웃 시 location 변경 없어 리렌더링 안 됨
- `authChange` 이벤트를 받지 못함

**수정 내용:**
- ✅ `authChange` 이벤트 리스너 추가
- ✅ useState 초기값 최적화: `!!sessionStorage.getItem('userId')`
- ✅ checkLogin 함수로 중복 코드 제거
- ✅ 이벤트 리스너 cleanup 추가

**Before:**
```javascript
const [isLogin, setIsLogin] = useState(false);

useEffect(() => {
    if(sessionStorage.getItem('userId')){
        setIsLogin(true);
    }else{
        setIsLogin(false);
    }
}, [location]);
```

**After:**
```javascript
const [isLogin, setIsLogin] = useState(!!sessionStorage.getItem('userId'));

useEffect(() => {
    const checkLogin = () => {
        setIsLogin(!!sessionStorage.getItem('userId'));
    };

    checkLogin();

    window.addEventListener('authChange', checkLogin);
    return () => {
        window.removeEventListener('authChange', checkLogin);
    };
}, [location]);
```

---

#### 2. Nav.jsx ✅
**수정일:** 2025-11-10  
**경로:** `layout/Nav.jsx`

**문제:**
- `sessionStorage.getItem('isadmin')`이 문자열 반환 ('true', 'false', null)
- 문자열 'false'도 truthy로 평가되어 조건부 렌더링 오작동
- 불필요한 이벤트 핸들러 함수

**수정 내용:**
- ✅ sessionStorage boolean 변환: `=== 'true'`
- ✅ useState 초기값 최적화
- ✅ 불필요한 handleAuthChange 함수 제거
- ✅ 조건부 렌더링 개선: `!isAdmin ? styles.hidden` → `isAdmin &&`
- ✅ img alt 속성 추가
- ✅ 사용하지 않는 FaCaretRight import 제거

**Before:**
```javascript
const [isAdmin, setIsAdmin] = useState(false);

const checkAdmin = () => {
    setIsAdmin(sessionStorage.getItem('isadmin')); // ❌ 문자열 저장
};

const handleAuthChange = () => {
    checkAdmin();
};

<div className={`${styles.admin_container} ${!isAdmin ? styles.hidden : ''}`}>
```

**After:**
```javascript
const [isAdmin, setIsAdmin] = useState(sessionStorage.getItem('isadmin') === 'true');

const checkAdmin = () => {
    setIsAdmin(sessionStorage.getItem('isadmin') === 'true'); // ✅ boolean 변환
};

window.addEventListener('authChange', checkAdmin);

{isAdmin && (
<div className={styles.admin_container}>
```

**핵심 개선:**
- 로그인/로그아웃 시 즉시 Header와 Nav 리렌더링
- boolean 타입 안정성 확보
- 코드 간결화 및 가독성 향상

---

### 1단계: Write 컴포넌트 (6개 파일) ✅

#### 1. RecipeBoardWrite.js ✅
**수정일:** 2025-11-10  
**경로:** `components/recipe_board/RecipeBoardWrite.js`

**수정 내용:**
- ❌ 함수명 오류 수정: `RecipeBoardDetail` → `RecipeBoardWrite`
- ✅ export 이름 수정
- ✅ sessionStorage 키 통일: `'userid'` → `'userId'`
- ✅ data 상태 간소화 (불필요한 중첩 객체 제거)
- ✅ input 필드와 data 연결 (value, onChange 추가)
- ✅ required 속성 추가
- ✅ 폼 제출 핸들러 추가 (handleSubmit)
- ✅ maxLength 속성 추가 (재료/레시피 150자)

**주요 코드:**
```javascript
function RecipeBoardWrite() {
  const [data, setData] = useState({
    userid: sessionStorage.getItem('userId'),
    cookname: '',
    mainingredients: '',
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    // axios 영역
    const submitData = {...data, ingredients, recipes, tip: text};
    console.log('제출 데이터:', submitData);
  };
}
```

---

#### 2. FreeBoardWrite.js ✅
**수정일:** 2025-11-10  
**경로:** `components/another_board/FreeBoardWrite.js`

**수정 내용:**
- ✅ textarea에 maxLength={700} 추가
- ✅ required 속성 추가 (제목, 내용)

**주요 코드:**
```javascript
<textarea
  value={data.content}
  onChange={(e) => setData({...data, content: e.target.value})}
  maxLength={700}
  required
/>
```

---

#### 3. NoticeBoardWrite.js ✅
**수정일:** 2025-11-10  
**경로:** `components/another_board/NoticeBoardWrite.js`

**수정 내용:**
- ✅ **수정 불필요** - 이미 완벽하게 구현됨
- data 상태 관리 양호
- handleChange 패턴 사용
- sessionStorage 연동 완료

---

#### 4. ConvenienceCombWrite.js ✅
**수정일:** 2025-11-10  
**경로:** `components/conv_board/ConvenienceCombWrite.js`

**수정 내용:**
- ❌ console.log(style) 제거
- ❌ useParams import 제거 (미사용)
- ✅ data 상태 추가 및 통합
- ✅ sessionStorage 연동 추가
- ✅ 카테고리 버튼 data 연동 (activeCategory → data.category)
- ✅ input 필드와 data 연결
- ✅ 폼 제출 핸들러 표준화
- ✅ maxLength 속성 추가 (제품/레시피 150자)
- ✅ 버튼 정리 (onClick 화살표 함수 제거)

**주요 코드:**
```javascript
const [data, setData] = useState({
  userid: '',
  cookname: '',
  mainproduct: '',
  category: '식사류',
  tip: ''
});

const handleSubmit = (e) => {
  e.preventDefault();
  // axios 영역
  const submitData = {
    ...data,
    products: products.filter(item => item.trim() !== ''),
    recipes: recipes.filter(item => item.trim() !== '')
  };
  console.log('제출 데이터:', submitData);
};
```

---

#### 5. ConvenienceReviewWrite.js ✅
**수정일:** 2025-11-10  
**경로:** `components/conv_board/ConvenienceReviewWrite.js`

**수정 내용:**
- ❌ 중복 상태 제거: `activeStore`, `activeCategory`, `text` 제거
- ✅ data로 통합 (conv, category 필드에 통합)
- ✅ select 박스와 data 연동
- ✅ input 필드와 data 연결 (제품명, 가격, 상세내용)
- ✅ required 속성 추가
- ✅ 폼 제출 핸들러 표준화

**주요 코드:**
```javascript
const [data, setData] = useState({
  userid: '',
  title: '',
  content: '',
  price: '',
  conv: 'GS25',
  category: '식사류'
});

// 중복 제거: activeStore, activeCategory 삭제
<select value={data.conv} onChange={(e) => setData({...data, conv: e.target.value})}>
<select value={data.category} onChange={(e) => setData({...data, category: e.target.value})}>
```

---

#### 6. ShareToolWrite.js ✅
**수정일:** 2025-11-10  
**경로:** `components/share_tool_board/ShareToolWrite.js`

**수정 내용:**
- ❌ 개별 상태 제거: `text`, `selectedProvince` 제거
- ✅ data 상태 추가 및 통합
- ✅ sessionStorage 연동 추가
- ✅ 지역 선택 개선 (시/도 변경 시 구/군 자동 초기화)
- ✅ 시군구 select에 onChange, value 추가
- ✅ input 필드와 data 연결
- ✅ 폼 제출 핸들러 추가
- ✅ required 속성 추가

**주요 코드:**
```javascript
const [data, setData] = useState({
  userid: '',
  category: '',
  productname: '',
  si: '경기도',
  gu: '고양시',
  content: ''
});

const handleSidoChange = (e) => {
  const newSi = e.target.value;
  setData({
    ...data,
    si: newSi,
    gu: locationData[newSi][0]  // 자동 초기화
  });
};
```

---

### 2단계: 모달 컴포넌트 (4/6 완료) 🔄

#### 1. AgreeModal.js ✅
**수정일:** 2025-11-10  
**경로:** `components/info/AgreeModal.js`

**수정 내용:**
- ✅ **수정 불필요** - 이미 완벽하게 구현됨
- Named export 사용
- props 정상 전달 (onClose, handleAgree)
- 약관 표시 및 동의 버튼 정상 작동

---

#### 2. ReportModal.js ✅
**수정일:** 2025-11-10  
**경로:** `components/info/ReportModal.js`

**수정 내용:**
- ❌ 개별 상태 제거: `reportContent` 제거
- ✅ data 상태로 통합
- ✅ sessionStorage 연동 추가
- ✅ 폼 구조 추가 (`<form>` 태그)
- ✅ handleSubmit 개선 (e.preventDefault 추가)
- ✅ textarea 속성 추가 (maxLength={500}, required)
- ✅ 버튼 타입 수정 (type="submit")

**주요 코드:**
```javascript
const [data, setData] = useState({
  userid: '',
  postid: postId,
  category: category,
  content: ''
});

useEffect(() => {
  if(sessionStorage.getItem('userId')){
    setData(prev => ({...prev, userid: sessionStorage.getItem('userId')}));
  }
}, []);

const handleSubmit = (e) => {
  e.preventDefault();
  if (!data.content.trim()) {
    alert('신고 사유를 입력해주세요.');
    return;
  }
  // axios 영역
  console.log('신고 데이터:', data);
  alert('신고가 접수되었습니다.');
  onClose();
};
```

---

#### 3. ShoppingBoardModal.js ✅
**수정일:** 2025-11-10  
**경로:** `components/with_shopping_board/ShoppingBoardModal.js`

**수정 내용:**
- ✅ 폼 구조 표준화 (`<form onSubmit={handleSubmit}>`)
- ✅ handleOnSubmit 개선 (e.preventDefault, console.log, onClose 추가)
- ✅ input/textarea 속성 추가 (required, maxLength)
- ✅ 버튼 타입 수정 (닫기: type="button", 제출: type="submit")

**주요 코드:**
```javascript
const handleOnSubmit = (e) => {
  e.preventDefault();
  // axios 영역
  console.log('제출 데이터:', data);
  onClose();
};

<form onSubmit={handleOnSubmit}>
  <input type='text' name="title" required maxLength={100} />
  <textarea name="content" required maxLength={500} />
  <button type="submit">글 쓰기</button>
</form>
```

---

#### 4. ChatModal.js ✅
**수정일:** 2025-11-10  
**경로:** `components/info/ChatModal.js`

**수정 내용:**
- ✅ 함수명 오타 수정: `handleSubmite` → `handleSubmit`
- ✅ 메시지 상태 관리 추가 (message, userid)
- ✅ sessionStorage 연동 추가
- ✅ textarea 연결 (value, onChange)
- ✅ maxLength={500} 추가
- ✅ placeholder 추가
- ✅ 메시지 전송 후 초기화 로직 추가

**주요 코드:**
```javascript
const [message, setMessage] = useState('');
const [userid, setUserid] = useState('');

const handleSubmit = (e) => {
  e.preventDefault();
  if (!message.trim()) return;
  
  // axios 영역
  console.log('전송 메시지:', { userid, message, timestamp: new Date() });
  setMessage('');  // 전송 후 초기화
};

<textarea
  value={message}
  onChange={(e) => setMessage(e.target.value)}
  onKeyDown={handleKeyDown}
  maxLength={500}
/>
```

---

#### 5. ShoppingBoardDetailModal.js ✅
**수정일:** 2025-11-10  
**경로:** `components/with_shopping_board/ShoppingBoardDetailModal.js`

**수정 내용:**
- ✅ axios 주석 표준화
- ✅ useEffect 의존성 배열에 postId 추가
- ✅ 버튼 핸들러 추가 (삭제, 수정, 신청, 신고)
- ✅ 삭제 확인 대화상자 추가
- ✅ console.log로 동작 확인 가능

**주요 코드:**
```javascript
useEffect(() => {
  // axios 영역
  // postId: ${postId}로 데이터 가져오기
  
  // 샘플 데이터
  setData({...});
}, [postId]);

const handleDelete = () => {
  if(window.confirm('정말 삭제하시겠습니까?')){
    // axios 영역
    console.log('삭제:', data.id);
    onClose();
  }
};

const handleEdit = () => {
  // axios 영역
  console.log('수정:', data.id);
};

const handleApply = () => {
  // axios 영역
  console.log('신청:', data.id);
  alert('신청이 완료되었습니다.');
};

const handleReport = () => {
  // axios 영역
  console.log('신고:', data.id);
  alert('신고가 접수되었습니다.');
};
```

---

#### 6. ReportDetailModal.js ✅
**수정일:** 2025-11-10  
**경로:** `components/info/ReportDetailModal.js`

**수정 내용:**
- ✅ **수정 최소** - 읽기 전용 모달로 이미 잘 구현됨
- ✅ reportData 구조 주석 추가
- ✅ 추가 정보 표시 (카테고리, 신고일)
- ✅ 레이아웃 개선 (섹션 구분)
- ✅ 안전한 접근 (Optional chaining 사용)

**주요 코드:**
```javascript
export const ReportDetailModal = ({ onClose, reportData }) => {
  // reportData 예상 구조: { id, postid, category, content, reporter, reportdate }
  
  return (
    <div className={style.modal_container}>
      {reportData?.category && (
        <p><strong>신고 카테고리:</strong> {reportData.category}</p>
      )}
      {reportData?.reportdate && (
        <p><strong>신고 일시:</strong> {reportData.reportdate}</p>
      )}
      <div className={style.report_content}>
        <strong>신고 사유:</strong>
        <p>{reportData?.content || '기본 텍스트'}</p>
      </div>
    </div>
  );
};
```

---

### 3단계: 자유게시판/공지사항 (4개 파일) ✅

#### 1. FreeBoard.js ✅
**수정일:** 2025-11-10  
**경로:** `components/another_board/FreeBoard.js`

**수정 내용:**
- ✅ Fragment import 제거 (미사용)
- ✅ axios 주석 표준화
- ✅ 검색 기능 추가 (searchKeyword 상태 및 handleSearch)
- ✅ useEffect 의존성 추가 (currentPage)
- ✅ input에 value, onChange 연결
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const [searchKeyword, setSearchKeyword] = useState('');

const handleSearch = (e) => {
  e.preventDefault();
  // axios 영역
  console.log('검색어:', searchKeyword);
};

useEffect(() => {
  // axios 영역
  // page: ${currentPage}로 데이터 가져오기
  
  // 샘플 데이터
  setPosts([...]);
}, [currentPage]);
```

---

#### 2. FreeBoardDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/another_board/FreeBoardDetail.js`

**수정 내용:**
- ✅ useParams import 및 사용
- ✅ axios 주석 표준화
- ✅ comments 샘플 데이터 활성화
- ✅ useEffect 의존성 추가 (postid)
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
import { useParams } from "react-router-dom";

const { postid } = useParams();

useEffect(() => {
  // axios 영역
  // postid: ${postid}로 게시글 및 댓글 데이터 가져오기
  
  // 샘플 데이터
  setData({...});
  setComments([...]);
}, [postid]);
```

---

#### 3. NoticeBoard.js ✅
**수정일:** 2025-11-10  
**경로:** `components/another_board/NoticeBoard.js`

**수정 내용:**
- ✅ Fragment import 제거
- ✅ axios 주석 표준화
- ✅ useEffect 의존성 추가 (currentPage)
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
useEffect(() => {
  // axios 영역
  // page: ${currentPage}로 공지사항 데이터 가져오기
  
  // 샘플 데이터
  setPosts([...]);
}, [currentPage]);
```

---

#### 4. NoticeBoardDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/another_board/NoticeBoardDetail.js`

**수정 내용:**
- ✅ RSbtn, useSearchParams import 제거 (미사용)
- ✅ axios 주석 표준화
- ✅ useEffect 의존성 추가 (postid)
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const { postid } = useParams();

useEffect(() => {
  // axios 영역
  // postid: ${postid}로 공지사항 데이터 가져오기
  
  // 샘플 데이터
  setData({...});
  setIsAdmin(sessionStorage.getItem('isadmin'));
}, [postid]);
```

---

### 4단계: 편의점 조합 게시판 (진행 중) 🔄

#### 1. ConvenienceRecipeBoard.js ✅
**수정일:** 2025-11-10  
**경로:** `components/conv_board/ConvenienceRecipeBoard.js`

**수정 내용:**
- ✅ axios 주석 표준화
- ✅ 검색 기능 추가 (searchKeyword, searchProduct 상태)
- ✅ handleSearch 함수 추가
- ✅ input에 value, onChange 연결
- ✅ useEffect 의존성 추가 (page, activeCategory)
- ✅ 세미콜론 추가
- ✅ **map에 key prop 추가** (중요!)

**주요 코드:**
```javascript
const [searchKeyword, setSearchKeyword] = useState('');
const [searchProduct, setSearchProduct] = useState('');

const handleSearch = () => {
  // axios 영역
  console.log('검색:', { keyword, product, category });
};

useEffect(() => {
  // axios 영역
  // page: ${page}, category: ${activeCategory}로 레시피 데이터 가져오기
  
  // 샘플 데이터
  setPosts([...]);
}, [page, activeCategory]);

// map에 key 추가
{posts.map(post => (
  <div key={post.id} className={style.shopping_card}>
    ...
  </div>
))}
```

---

#### 2. ConvenienceCombDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/conv_board/ConvenienceCombDetail.js`

**수정 내용:**
- ✅ useParams 사용 (postid 가져오기)
- ✅ useEffect로 데이터 로드
- ✅ post 상태 구조 개선 (images, ingredients, recipes 배열)
- ✅ 동적 데이터 렌더링 (map 사용, key 추가)
- ✅ 핸들러 추가 (handleDelete, handleReport, handleSave)
- ✅ 이미지 슬라이더 개선 (안전한 접근)
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const { postid } = useParams();

const [post, setPost] = useState({
  title: '', usernickname: '', createdate: '',
  mainingredient: '', ingredients: [], recipes: [],
  tip: '', images: [], comments: []
});

useEffect(() => {
  // axios 영역
  // postid: ${postid}로 레시피 데이터 가져오기
  
  // 샘플 데이터
  setPost({...});
}, [postid]);

// 동적 렌더링
{post.ingredients && post.ingredients.map((item, index) => (
  <div key={index}>{index + 1}. {item}</div>
))}
```

---

#### 3. ConvenienceReviewDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/conv_board/ConvenienceReviewDetail.js`

**수정 내용:**
- ✅ useParams 사용 (postid 가져오기)
- ✅ useEffect로 데이터 로드
- ✅ post 상태 구조 개선 (price, content, images, comments)
- ✅ 동적 데이터 렌더링
- ✅ 핸들러 추가 (handleDelete, handleReport, handleSave)
- ✅ 이미지 슬라이더 개선
- ✅ alt 속성 추가
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const { postid } = useParams();

const [post, setPost] = useState({
  title: '', usernickname: '', createdate: '',
  price: '', content: '', images: [], comments: []
});

useEffect(() => {
  // axios 영역
  // postid: ${postid}로 리뷰 데이터 가져오기
  
  // 샘플 데이터
  setPost({...});
}, [postid]);

const handleDelete = () => {
  if (window.confirm("정말 이 리뷰를 삭제하시겠습니까?")) {
    // axios 영역
    console.log('삭제:', postid);
    navigate(`/conv/review`);
  }
};
```

---

### 5단계: 요리도구 나눔 게시판 (2개 파일) ✅

#### 1. ShareBoard.js ✅
**수정일:** 2025-11-10  
**경로:** `components/share_tool_board/ShareBoard.js`

**수정 내용:**
- ✅ axios 주석 표준화
- ✅ 검색 기능 추가 (searchKeyword 상태)
- ✅ 지역 선택 개선 (selectedSido, selectedGu 상태 분리)
- ✅ handleGuChange 추가 (구/군 선택 핸들러)
- ✅ handleSearch 추가
- ✅ useEffect 의존성 추가 (page, selectedSido, selectedGu)
- ✅ input에 value, onChange 연결
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const [searchKeyword, setSearchKeyword] = useState('');
const [selectedSido, setSelectedSido] = useState('경기도');
const [selectedGu, setSelectedGu] = useState('고양시');

const handleSidoChange = (e) => {
  const newSido = e.target.value;
  setSelectedSido(newSido);
  setSelectedGu(locationData[newSido][0]); // 자동 초기화
};

const handleGuChange = (e) => {
  setSelectedGu(e.target.value);
};

const handleSearch = () => {
  // axios 영역
  console.log('검색:', { keyword, sido, gu });
};

useEffect(() => {
  // axios 영역
  // page: ${page}, sido: ${selectedSido}, gu: ${selectedGu}로 데이터 가져오기
  
  // 샘플 데이터
  setPosts([...]);
}, [page, selectedSido, selectedGu]);
```

---

#### 2. ShareToolDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/share_tool_board/ShareToolDetail.js`

**수정 내용:**
- ✅ useParams 사용 (postid 가져오기)
- ✅ useEffect로 데이터 로드
- ✅ post 상태 구조 개선 (images 배열 포함)
- ✅ 동적 데이터 렌더링
- ✅ 핸들러 추가 (handleDelete, handleReport, handleSave, handleRequest)
- ✅ 이미지 슬라이더 개선 (안전한 접근)
- ✅ 상태(나눔중/나눔완료) 표시 추가
- ✅ alt 속성 추가
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const { postid } = useParams();

const [post, setPost] = useState({
  title: '', product: '', usernickname: '', createdate: '',
  si: '', gu: '', status: '', content: '', images: []
});

useEffect(() => {
  // axios 영역
  // postid: ${postid}로 나눔 게시글 데이터 가져오기
  
  // 샘플 데이터
  setPost({...});
}, [postid]);

const handleRequest = () => {
  // axios 영역
  console.log('신청:', postid);
  alert('신청이 완료되었습니다.');
};

// 동적 렌더링
<div className={styles.title}>{post.title}</div>
<div className={styles.content_text}>{post.si} {post.gu}</div>
<div className={styles.content_text}>{post.status}</div>
```

---

### 6단계: 레시피 게시판 (2개 파일) ✅

#### 1. RecipeBoard.js ✅
**수정일:** 2025-11-10  
**경로:** `components/recipe_board/RecipeBoard.js`

**수정 내용:**
- ✅ axios 주석 표준화
- ✅ 검색 기능 추가 (searchKeyword, searchIngredient 상태)
- ✅ handleSearch 추가
- ✅ useEffect 의존성 추가 (page)
- ✅ input에 value, onChange, maxLength 연결
- ✅ map에 key prop 추가
- ✅ postId 미사용 변수 제거
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const [searchKeyword, setSearchKeyword] = useState('');
const [searchIngredient, setSearchIngredient] = useState('');

const handleSearch = () => {
  // axios 영역
  console.log('검색:', { keyword, ingredient });
};

useEffect(() => {
  // axios 영역
  // page: ${page}로 레시피 데이터 가져오기
  
  // 샘플 데이터
  setPosts([...]);
}, [page]);

// map에 key 추가
{posts.map((post) => (
  <div key={post.id} className={style.shopping_card}>
```

---

#### 2. RecipeBoardDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/recipe_board/RecipeBoardDetail.js`

**수정 내용:**
- ✅ useParams 사용 (postid 가져오기)
- ✅ useEffect로 데이터 로드
- ✅ post 상태 구조 개선 (ingredients, recipes 배열)
- ✅ 동적 데이터 렌더링 (map 사용, key 추가)
- ✅ 핸들러 추가 (handleDelete, handleReport, handleSave)
- ✅ 이미지 슬라이더 개선 (안전한 접근)
- ✅ alt 속성 추가
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const { postid } = useParams();

const [post, setPost] = useState({
  title: '', mainingre: '', usernickname: '',
  createdate: '', ingredients: [], recipes: [],
  tip: '', images: [], comments: []
});

useEffect(() => {
  // axios 영역
  // postid: ${postid}로 레시피 데이터 가져오기
  
  // 샘플 데이터
  setPost({...});
}, [postid]);

// 동적 렌더링
{post.ingredients && post.ingredients.map((item, index) => (
  <div key={index}>{index + 1}. {item}</div>
))}

{post.recipes && post.recipes.map((item, index) => (
  <div key={index}>{index + 1}. {item}</div>
))}
```

---

### 7단계: 같이쇼핑 게시판 (1개 파일) ✅

#### 1. ShoppingBoard.js ✅
**수정일:** 2025-11-10  
**경로:** `components/with_shopping_board/ShoppingBoard.js`

**수정 내용:**
- ✅ axios 주석 표준화
- ✅ loadPosts 함수 분리
- ✅ handleSearch 추가 (검색 버튼 클릭 시 로드)
- ✅ useEffect 의존성 수정 (page만 - select 변경 시 자동 로드 방지)
- ✅ loding → loading 오타 수정
- ✅ 미사용 import 제거 (BsCaretLeftSquareFill, BsCaretRightSquareFill)
- ✅ openDetailsModal URL 수정 (id 파라미터 추가)

**주요 코드:**
```javascript
const loadPosts = () => {
  // axios 영역
  // page: ${page}, si: ${data.si}, gu: ${data.gu}로 데이터 가져오기
  
  setLoading(true);
  setPosts([...]);
  setLoading(false);
};

const handleSearch = () => {
  loadPosts();  // 검색 버튼 클릭 시에만 데이터 로드
};

useEffect(() => {
  // 페이지 변경 시에만 데이터 로드
  loadPosts();
}, [page]);  // select 변경 시 자동 로드 방지
```

---

### 8단계: 편의점 전체 게시판 (1개 파일) ✅

#### 1. ConvenienceBoard.js ✅
**수정일:** 2025-11-10  
**경로:** `components/conv_board/ConvenienceBoard.js`

**수정 내용:**
- ✅ axios 주석 표준화
- ✅ loadPosts 함수 분리
- ✅ handleStoreClick 추가 (편의점 클릭 시 즉시 로드)
- ✅ handleCategoryClick 추가 (카테고리 클릭 시 즉시 로드)
- ✅ useEffect 의존성 (page만 - 필터 UI는 클릭 시 즉시 반응)
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const loadPosts = () => {
  // axios 영역
  // page: ${page}, store: ${activeStore}, category: ${activeCategory}로 데이터 가져오기
  
  setPosts([...]);
};

const handleStoreClick = (storeName) => {
  setActiveStore(storeName);
  loadPosts();  // 편의점 버튼 클릭 시 즉시 데이터 로드
};

const handleCategoryClick = (categoryName) => {
  setActiveCategory(categoryName);
  loadPosts();  // 카테고리 버튼 클릭 시 즉시 데이터 로드
};

useEffect(() => {
  loadPosts();
}, [page]);  // 필터 UI는 클릭 시 즉시 반응
```

**설계 차이:**
- ShoppingBoard: 지역 선택 → 검색 버튼 클릭 → 데이터 로드
- ConvenienceBoard: 필터 버튼 클릭 → 즉시 데이터 로드 (필터링 UI 특성)

---

### 9단계: 관리자 게시판 (3개 파일) ✅

#### 1. AdminPostBoard.js ✅
**수정일:** 2025-11-10  
**경로:** `components/admin_board/AdminPostBoard.js`

**수정 내용:**
- ✅ axios 주석 표준화
- ✅ deletePost 개선 (console.log 추가)
- ✅ handleOnSubmit 개선 (검색 로직)
- ✅ useEffect 주석 개선 (관리자 확인 명시)
- ✅ input maxLength={100} 추가
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const deletePost = (postid) => {
  const result = window.confirm('정말 삭제하시겠습니까?');
  if(result){
    // axios 영역
    console.log('삭제:', postid);
  }
};

const handleOnSubmit = (e) => {
  e.preventDefault();
  // axios 영역
  console.log('검색:', { searchText, selectedCategories });
};

useEffect(() => {
  // axios 영역
  // 관리자 확인: isadmin = sessionStorage.getItem('isadmin')
  // page: ${currentPage}로 게시글 데이터 가져오기
  
  setPosts([...]);
}, [currentPage]);
```

---

#### 2. AdminReportBoard.js ✅
**수정일:** 2025-11-10  
**경로:** `components/admin_board/AdminReportBoard.js`

**수정 내용:**
- ✅ axios 주석 표준화
- ✅ deleteReport 개선 (console.log 추가)
- ✅ viewDetails 주석 개선
- ✅ useEffect 주석 개선 (관리자 확인 명시)
- ✅ 미사용 import 제거 (modalStyle)
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const deleteReport = (reportid) => {
  const result = window.confirm('정말 삭제하시겠습니까?');
  if(result){
    // axios 영역
    console.log('신고 삭제:', reportid);
  }
};

const viewDetails = (reportid) => {
  // axios 영역
  // reportid로 상세 신고 내용 조회
  const report = reports.find(r => r.reportid === reportid);
  setSelectedReport(report);
  setIsModalOpen(true);
};

useEffect(() => {
  // axios 영역
  // 관리자 확인: isadmin = sessionStorage.getItem('isadmin')
  // page: ${currentPage}로 신고 데이터 가져오기
  
  setReports([...]);
}, [currentPage]);
```

---

#### 3. AdminUserBoard.js ✅
**수정일:** 2025-11-10  
**경로:** `components/admin_board/AdminUserBoard.js`

**수정 내용:**
- ✅ axios 주석 표준화
- ✅ deleteUser 개선 (console.log 추가, post.id → post.userid 수정)
- ✅ handleOnSubmit 개선
- ✅ useEffect 주석 개선 (관리자 확인 명시)
- ✅ map에 key 추가 (React.Fragment key={post.userid})
- ✅ Fragment import 제거 (미사용)
- ✅ input maxLength={20} 추가
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const deleteUser = (userid) => {
  const result = window.confirm('정말 삭제하시겠습니까?');
  if(result){
    // axios 영역
    console.log('사용자 삭제:', userid);
  }
};

useEffect(() => {
  // axios 영역
  // 관리자 확인: isadmin = sessionStorage.getItem('isadmin')
  // page: ${currentPage}로 사용자 데이터 가져오기
  
  setPosts([...]);
}, [currentPage]);

// map에 key 추가
{posts.map((post, index) => (
  <React.Fragment key={post.userid}>
    {/* ... */}
    <button onClick={() => deleteUser(post.userid)}>삭제</button>
  </React.Fragment>
))}
```

---

### 10단계: 사용자 인증 (4개 파일) ✅

#### 1. RegisterForm.js ✅
**수정일:** 2025-11-10  
**경로:** `components/info/RegisterForm.js`

**수정 내용:**
- ✅ axios 주석 표준화
- ✅ handleOnSubmit 대폭 개선 (유효성 검사 추가)
- ✅ 약관 동의 검사
- ✅ 아이디 중복 확인 검사
- ✅ 비밀번호 일치 검사
- ✅ maxLength 추가 (userid: 20, pwd: 30, username: 20, nickname: 20, email: 50)
- ✅ 미사용 Link import 제거
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const handleCheckId = () => {
  // axios 영역
  // userid로 중복 확인
  const getResult = true;
  setAcceptId(getResult);
};

const handleOnSubmit = (e) => {
  e.preventDefault();
  
  if(!agree){
    alert('이용약관에 동의해주세요.');
    return;
  }
  
  if(!acceptId){
    alert('아이디 중복 확인을 해주세요.');
    return;
  }
  
  if(data.pwd !== data.pwd2){
    alert('비밀번호가 일치하지 않습니다.');
    return;
  }
  
  // axios 영역
  console.log('회원가입 데이터:', data);
};
```

---

#### 2. LoginForm.js ✅
**수정일:** 2025-11-10  
**경로:** `components/info/LoginForm.js`

**수정 내용:**
- ✅ axios 주석 표준화
- ✅ sessionStorage 설정 주석 개선
- ✅ maxLength 추가 (userid: 20, password: 30)
- ✅ handleChange 화살표 함수 개선
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const handleChange = (e) => {
  setForm({ ...form, [e.target.name]: e.target.value });
};

const handleSubmit = (e) => {
  e.preventDefault();
  
  // axios 영역
  console.log('로그인 시도:', form);
  
  // axios에서 응답을 받아와서 아래 설정
  sessionStorage.setItem('userId', form.userid);
  // result.isadmin을 받아와서 설정
  sessionStorage.setItem('isadmin', true);
  
  // 로그인 성공 시 메인으로 이동
  navigate('/');
};
```

---

#### 3. FindAccount.js ✅
**수정일:** 2025-11-10  
**경로:** `components/info/FindAccount.js`

**수정 내용:**
- ✅ axios 주석 표준화 및 로직 개선 (아이디 찾기/비밀번호 찾기 분기)
- ✅ maxLength 추가 (name: 20, id: 20, email: 50)
- ✅ type="email" 추가
- ✅ 템플릿 리터럴 수정 (백틱 문제 해결)
- ✅ useEffect 개선 (탭 전환 시 submitted도 초기화)
- ✅ 미사용 Link import 제거
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const handleSubmit = (e) => {
  e.preventDefault();
  
  // axios 영역
  if(findId){
    // 아이디 찾기
    console.log('아이디 찾기:', { name: data.name, email: data.email });
  } else {
    // 비밀번호 찾기
    console.log('비밀번호 찾기:', { name: data.name, id: data.id, email: data.email });
  }
  
  setSubmitted(true);
};

// 템플릿 리터럴 수정
{submitted && (
  <div className={style.submit_erea}>
    {data.name}님의 아이디는 (조회 결과)입니다.
  </div>
)}

{submitted && (
  <div className={style.submit_erea}>
    {data.name}님의 임시 비밀번호를 이메일로 전송했습니다.
  </div>
)}
```

---

#### 4. MyPage.js ✅
**수정일:** 2025-11-10  
**경로:** `components/info/MyPage.js`

**수정 내용:**
- ✅ axios 주석 표준화
- ✅ handleComplete 대폭 개선 (유효성 검사 추가)
- ✅ 닉네임, 이메일 필수 입력 검사
- ✅ handleWithdrawal 개선
- ✅ maxLength 추가 (nickname: 20, email: 50)
- ✅ required 속성 추가
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
useEffect(() => {
  const fetchData = async () => {
    try {
      setError(null);

      // axios 영역
      // userid = sessionStorage.getItem('userId')로 사용자 정보 조회
      // 샘플 데이터
      setMyPosts(fetchedMyPosts);
      setInterestedPosts(fetchedInterestedPosts);

    } catch (err) {
      setError('데이터를 불러오는 데 실패했습니다.');
      console.error(err);
    }
  };

  fetchData();
}, []);

const handleComplete = () => {
  if(!userInfo.nickname || !userInfo.email){
    alert('닉네임과 이메일은 필수 입력 사항입니다.');
    return;
  }
  
  // axios 영역
  console.log('수정 데이터:', userInfo);
  
  setIsActive(true);
};

const handleWithdrawal = () => {
  if (window.confirm("정말로 회원 탈퇴를 하시겠습니까?")) {
    // axios 영역
    console.log("회원 탈퇴 처리...");
  }
};
```

---

### 11단계: 공통 컴포넌트 (5개 파일) ✅

#### 1. CommentBoard.js ✅
**수정일:** 2025-11-10  
**경로:** `components/common/CommentBoard.js`

**수정 내용:**
- ✅ 상태 관리 추가 (commentData)
- ✅ sessionStorage 연동 (userid)
- ✅ handleSubmit 추가 (댓글 작성)
- ✅ input에 value, onChange 연결
- ✅ maxLength={200} 추가
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
const [commentData, setCommentData] = useState({
  userid: '',
  content: ''
});

const handleSubmit = (e) => {
  e.preventDefault();
  if(!commentData.content.trim()){
    alert('댓글 내용을 입력해주세요.');
    return;
  }
  // axios 영역
  console.log('댓글 작성:', commentData);
  setCommentData(prev => ({ ...prev, content: '' }));
};
```

---

#### 2. HeaderLoginForm.js ✅
**수정일:** 2025-11-10  
**경로:** `components/HeaderLoginForm.js`

**수정 내용:**
- ✅ 세미콜론 추가
- ✅ useEffect cleanup 함수 개선
- ✅ handleLogout, handleMypage 파라미터 정리
- ✅ 미사용 BsGlobe import 제거

---

#### 3. HeaderNotLoginForm.js ✅
**수정일:** 2025-11-10  
**경로:** `components/HeaderNotLoginForm.js`

**수정 내용:**
- ✅ Link to 경로 수정 ("/login", "/signup")
- ✅ 미사용 BsGlobe import 제거
- ✅ 세미콜론 추가
- ✅ 코드 정리

---

#### 4. EDbtn.js ✅
**수정일:** 2025-11-10  
**경로:** `components/common/EDbtn.js`

**수정 내용:**
- ✅ onEdit 주석 제거 (활성화)
- ✅ 세미콜론 추가

**주요 코드:**
```javascript
<button onClick={onEdit}>수정하기</button>
```

---

#### 5. RSbtn.js ✅
**수정일:** 2025-11-10  
**경로:** `components/common/RSbtn.js`

**수정 내용:**
- ✅ 불필요한 빈 줄 제거
- ✅ 세미콜론 추가

---

### 12단계: 공통 컴포넌트 Props 검증 및 수정 (6개 파일) ✅

#### 1. RecipeBoardDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/recipe_board/RecipeBoardDetail.js`

**수정 내용:**
- ✅ handleEdit 함수 추가
- ✅ EDbtn에 onEdit prop 전달

**주요 코드:**
```javascript
const handleEdit = () => {
  // axios 영역
  console.log('수정:', postid);
  navigate(`/recipe/edit/${postid}`);
};

<EDbtn onDelete={handleDelete} onEdit={handleEdit}/>
```

---

#### 2. ConvenienceReviewDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/conv_board/ConvenienceReviewDetail.js`

**수정 내용:**
- ✅ handleEdit 함수 추가
- ✅ EDbtn에 onEdit prop 전달

---

#### 3. ConvenienceCombDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/conv_board/ConvenienceCombDetail.js`

**수정 내용:**
- ✅ handleEdit 함수 추가
- ✅ EDbtn에 onEdit prop 전달

---

#### 4. ShareToolDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/share_tool_board/ShareToolDetail.js`

**수정 내용:**
- ✅ handleEdit 함수 추가
- ✅ EDbtn에 onEdit prop 전달

---

#### 5. FreeBoardDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/another_board/FreeBoardDetail.js`

**수정 내용:**
- ✅ handleDelete 함수 추가
- ✅ handleEdit 함수 추가
- ✅ EDbtn에 onDelete, onEdit props 전달

**주요 코드:**
```javascript
const handleDelete = () => {
  if (window.confirm("정말 이 글을 삭제하시겠습니까?")) {
    // axios 영역
    console.log('삭제:', postid);
  }
};

const handleEdit = () => {
  // axios 영역
  console.log('수정:', postid);
};

<EDbtn onDelete={handleDelete} onEdit={handleEdit}/>
```

---

#### 6. NoticeBoardDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/another_board/NoticeBoardDetail.js`

**수정 내용:**
- ✅ handleDelete 함수 추가
- ✅ handleEdit 함수 추가
- ✅ EDbtn에 onDelete, onEdit props 전달

---

### 13단계: 수정 모드 구현 (13개 파일) ✅

#### 개요
**수정일:** 2025-11-10  
**목적:** 모든 Write 컴포넌트에 작성/수정 모드 분기 처리 구현

**구현 내용:**
- ✅ Body.jsx에 수정 모드 라우트 추가 (6개)
- ✅ Detail 페이지 handleEdit navigate 경로 수정 (6개)
- ✅ Write 컴포넌트 수정 모드 로직 추가 (6개)
- ✅ FreeBoardDetail, NoticeBoardDetail에 useNavigate 추가 (2개)

---

#### 1. Body.jsx 라우트 추가 ✅
**경로:** `layout/Body.jsx`

**수정 내용:**
- ✅ 각 Write 경로에 `:postid` 파라미터 추가

**주요 코드:**
```javascript
// 레시피 게시판
<Route path="/recipe/write" element={<RecipeBoardWrite/>}/>
<Route path="/recipe/write/:postid" element={<RecipeBoardWrite/>}/>

// 도구 공유 게시판
<Route path="/sharetool/write" element={<ShareToolWrite/>}/>
<Route path="/sharetool/write/:postid" element={<ShareToolWrite/>}/>

// 편의점 리뷰
<Route path="/conv/review/write" element={<ConvenienceReviewWrite/>}/>
<Route path="/conv/review/write/:postid" element={<ConvenienceReviewWrite/>}/>

// 편의점 조합
<Route path="/conv/recipe/write" element={<ConvenienceCombWrite/>}/>
<Route path="/conv/recipe/write/:postid" element={<ConvenienceCombWrite/>}/>

// 자유게시판
<Route path="/freeboard/write" element={<FreeBoardWrite/>}/>
<Route path="/freeboard/write/:postid" element={<FreeBoardWrite/>}/>

// 공지사항
<Route path="/notice/write" element={<NoticeBoardWrite/>}/>
<Route path="/notice/write/:postid" element={<NoticeBoardWrite/>}/>
```

---

#### 2. Write 컴포넌트 수정 모드 구현 (공통 패턴)

**적용 파일:**
1. RecipeBoardWrite.js
2. ConvenienceReviewWrite.js
3. ConvenienceCombWrite.js
4. ShareToolWrite.js
5. FreeBoardWrite.js
6. NoticeBoardWrite.js

**공통 구현 패턴:**
```javascript
import { useNavigate, useParams } from 'react-router-dom';

function WriteComponent() {
    const navigate = useNavigate();
    const { postid } = useParams();
    const [isEditMode, setIsEditMode] = useState(false);
    
    // 수정 모드: 기존 글 데이터 로드
    useEffect(() => {
        if(postid) {
            setIsEditMode(true);
            // axios 영역 - 기존 글 불러오기
            const fetchedData = { /* 기존 데이터 */ };
            setData(fetchedData);
        }
    }, [postid]);

    const handleSubmit = (e) => {
        e.preventDefault();
        
        if(isEditMode) {
            // axios 영역 - UPDATE
            console.log('수정 데이터:', data, 'postid:', postid);
            // navigate(`/경로/detail/${postid}`);
        } else {
            // axios 영역 - INSERT
            console.log('작성 데이터:', data);
            // navigate('/경로');
        }
    };

    return (
        <div>
            <h2>{isEditMode ? '수정하기' : '작성하기'}</h2>
            {/* 폼 내용 */}
        </div>
    );
}
```

---

#### 3. Detail 페이지 handleEdit 수정 (6개)

**적용 파일:**
- RecipeBoardDetail.js
- ConvenienceReviewDetail.js
- ConvenienceCombDetail.js
- ShareToolDetail.js
- FreeBoardDetail.js
- NoticeBoardDetail.js

**주요 코드:**
```javascript
const handleEdit = () => {
    // axios 영역
    console.log('수정:', postid);
    navigate(`/경로/write/${postid}`);
};
```

---

#### 4. useNavigate 추가 (2개)

**FreeBoardDetail.js, NoticeBoardDetail.js:**
```javascript
import { useParams, useNavigate } from "react-router-dom";

const navigate = useNavigate();
```

---

### 14단계: 버그 수정 및 안정화 (4개 파일) ✅

#### 개요
**수정일:** 2025-11-10  
**목적:** 수정 모드 구현 후 발생한 버그 수정 및 안전한 접근 패턴 적용

---

#### 1. Body.jsx - 라우트 수정 ✅
**경로:** `layout/Body.jsx`

**문제:**
- `/recipe/detail/*` 와일드카드 사용으로 `useParams()`로 postid를 가져올 수 없음

**해결:**
```javascript
// ❌ Before
<Route path="/recipe/detail/*" element={<RecipeBoardDetail/>}/>

// ✅ After
<Route path="/recipe/detail/:postid" element={<RecipeBoardDetail/>}/>
```

---

#### 2. ShareToolWrite.js - LocationData 안전성 개선 ✅
**경로:** `components/share_tool_board/ShareToolWrite.js`

**문제:**
- 샘플 데이터의 `si` 값이 LocationData에 없는 키 사용 (`'서울특별시'`)
- `locationData[data.si]`가 undefined가 되어 `.map()` 에러 발생

**해결:**
```javascript
// 1. 샘플 데이터 수정
const fetchedData = {
    si: '경기도',  // ✅ LocationData에 있는 키
    gu: '고양시',
};

// 2. 안전한 접근 추가
{data.si && locationData[data.si] && (
    <select>
        {locationData[data.si].map(sigungu => (
            <option key={sigungu} value={sigungu}>{sigungu}</option>
        ))}
    </select>
)}
```

---

#### 3. ShoppingBoardModal.js - 수정 모드 추가 ✅
**경로:** `components/with_shopping_board/ShoppingBoardModal.js`

**문제:**
- 같이장보기는 모달 방식이지만 수정 모드가 없음

**해결:**
```javascript
// Props 추가
export const ShoppingBoardModal = ({onClose, editData, isEditMode}) => {
    
    // 수정 모드 데이터 로드
    useEffect(() => {
        if(isEditMode && editData) {
            setData({
                title: editData.title || '',
                content: editData.content || '',
                status: editData.status || 'A01',
                userid: editData.userid || sessionStorage.getItem('userId'),
                si: editData.si || '경기도',
                gu: editData.gu || '고양시'
            });
        }
    }, [isEditMode, editData]);

    // 제출 분기
    const handleOnSubmit = (e) => {
        e.preventDefault();
        
        if(isEditMode) {
            // axios 영역 - UPDATE
            console.log('수정 데이터:', data);
        } else {
            // axios 영역 - INSERT
            console.log('작성 데이터:', data);
        }
        onClose();
    };

    // 제목 동적 변경
    <p>{isEditMode ? '같이 장보기 글 수정' : '같이 장보기 글은 24시간 뒤 자동 삭제됩니다'}</p>
    
    // 안전한 접근
    {data.si && locationData[data.si] && (
        <select>...</select>
    )}
}
```

---

#### 4. ShoppingBoardDetailModal.js - 수정 모달 연동 ✅
**경로:** `components/with_shopping_board/ShoppingBoardDetailModal.js`

**문제:**
- handleEdit 함수가 console.log만 있고 실제 동작 없음
- 샘플 데이터의 `si` 값이 LocationData에 없음 (`'서울시'`)

**해결:**
```javascript
// 1. 샘플 데이터 수정
setData({
    si: '경기도',  // ✅ '서울시' → '경기도'
    gu: '고양시',  // ✅ '강남구' → '고양시'
});

// 2. 수정 모달 상태 추가
const [isEditModalOpen, setIsEditModalOpen] = useState(false);

const handleEdit = () => {
    console.log('수정:', data.id);
    setIsEditModalOpen(true);  // ✅ 모달 열기
};

const closeEditModal = () => {
    setIsEditModalOpen(false);
};

// 3. 수정 모달 JSX 추가
<Modal isOpen={isEditModalOpen} onRequestClose={closeEditModal}>
    <ShoppingBoardModal 
        onClose={closeEditModal} 
        editData={data}
        isEditMode={true}
    />
</Modal>
```

---

## 🔧 공통 수정 패턴

### 1. 폼 데이터 관리
```javascript
// Before
const [text, setText] = useState('');
const [title, setTitle] = useState('');

// After
const [data, setData] = useState({
  userid: sessionStorage.getItem('userId'),
  title: '',
  content: ''
});
```

### 2. sessionStorage 연동
```javascript
useEffect(() => {
  if(sessionStorage.getItem('userId')){
    setData(prev => ({
      ...prev,
      userid: sessionStorage.getItem('userId')
    }));
  }
}, []);
```

### 3. 폼 제출 처리
```javascript
const handleSubmit = (e) => {
  e.preventDefault();
  // axios 영역
  console.log('제출 데이터:', data);
};

<form onSubmit={handleSubmit}>
  <button type="submit">제출</button>
</form>
```

### 4. Input 속성
```javascript
<input
  value={data.fieldName}
  onChange={(e) => setData({...data, fieldName: e.target.value})}
  required
  maxLength={100}
/>
```

---

## 📈 통계

### 수정 완료
- **1단계 Write 컴포넌트:** 6개 (수정 5개, 유지 1개)
- **2단계 모달 컴포넌트:** 6개 (수정 5개, 유지 1개)
- **3단계 자유게시판/공지사항:** 4개
- **4단계 편의점 조합 게시판:** 3개
- **5단계 요리도구 나눔 게시판:** 2개
- **6단계 레시피 게시판:** 2개
- **7단계 같이쇼핑 게시판:** 1개
- **8단계 편의점 전체 게시판:** 1개
- **9단계 관리자 게시판:** 3개
- **10단계 사용자 인증:** 4개
- **11단계 공통 컴포넌트:** 5개
- **12단계 공통 컴포넌트 Props 검증:** 6개
- **13단계 수정 모드 구현:** 13개
- **14단계 버그 수정:** 4개
- **15단계 마이페이지:** 1개
- **16단계 RSbtn 기능:** 5개
- **총 완료:** 47개 파일 ✅ (중복 수정 제외)

---

### 15단계: 마이페이지 기능 구현 (1개 파일) ✅

#### MyPage.js ✅
**수정일:** 2025-11-10  
**경로:** `components/info/MyPage.js`

**수정 내용:**
- ✅ sessionStorage 연동 (userId로 사용자 정보 로드)
- ✅ 로그인 체크 및 리다이렉트
- ✅ 게시글 클릭 이벤트 (boardType별 라우팅)
- ✅ 프로필 이미지 업로드 (FileReader, 파일 검증)
- ✅ 페이지네이션 구현 (내가 쓴 글, 관심 글 독립)
- ✅ async/await 제거 (표준 패턴으로 변경)
- ✅ 같이장보기 관심글 제거 (24시간 자동 삭제 게시판)

**Before:**
```javascript
// 복잡한 async/await
const fetchData = async () => {
  const mockApiCall = (data) => new Promise(...);
  const result = await mockApiCall(...);
};

// 관심글에 같이장보기 포함 (잘못됨)
setInterestedPosts([
  { type: '같이쇼핑', boardType: 'shopping' } // ❌
]);
```

**After:**
```javascript
// 간단한 샘플 데이터 설정
useEffect(() => {
  const userId = sessionStorage.getItem('userId');
  if(!userId) {
    alert('로그인이 필요합니다.');
    navigate('/login');
    return;
  }
  
  // axios 영역
  setUserInfo({...});
  setMyPosts([...]);
  setInterestedPosts([
    { type: '레시피', boardType: 'recipe' },
    { type: '요리나눔', boardType: 'sharetool' }
    // 같이장보기 제외 ✅
  ]);
}, [navigate, myPostsPage, interestedPostsPage]);

// 프로필 이미지 업로드
const handleImageUpload = (e) => {
  const file = e.target.files[0];
  if(file.size > 5 * 1024 * 1024) {
    alert('파일 크기는 5MB 이하여야 합니다.');
    return;
  }
  const reader = new FileReader();
  reader.onloadend = () => {
    setUserInfo(prev => ({...prev, profileImage: reader.result}));
  };
  reader.readAsDataURL(file);
};

// 게시글 클릭
const handlePostClick = (post) => {
  switch(post.boardType) {
    case 'recipe': navigate(`/recipe/detail/${post.id}`); break;
    case 'sharetool': navigate(`/sharetool/detail/${post.id}`); break;
    // ...
  }
};

// 페이지네이션
<PageNation 
  currentPage={myPostsPage}
  totalPage={myPostsTotalPage}
  onPageChange={handleMyPostsPageChange}
/>
```

---

### 16단계: 상세페이지 RSbtn 기능 구현 (5개 파일) ✅

#### 1. ShoppingBoardDetailModal.js ✅
**수정일:** 2025-11-10  
**경로:** `components/with_shopping_board/ShoppingBoardDetailModal.js`

**수정 내용:**
- ✅ ReportModal import 추가
- ✅ 신고 모달 상태 추가
- ✅ handleReport 신고 모달 열기로 변경
- ✅ ReportModal JSX 추가

**Before:**
```javascript
const handleReport = () => {
  alert('신고가 접수되었습니다.'); // ❌
};
```

**After:**
```javascript
import { ReportModal } from '../info/ReportModal';
import Modal from 'react-modal';

const [isReportModalOpen, setIsReportModalOpen] = useState(false);

const handleReport = () => {
  setIsReportModalOpen(true); // ✅
};

<Modal isOpen={isReportModalOpen}>
  <ReportModal onClose={closeReportModal} postId={data.id} boardType="shopping" />
</Modal>
```

---

#### 2. RecipeBoardDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/recipe_board/RecipeBoardDetail.js`

**수정 내용:**
- ✅ ReportModal import 추가
- ✅ 신고 모달 상태 및 핸들러 추가
- ✅ handleSave에 alert 추가
- ✅ ReportModal JSX 추가

**Before:**
```javascript
const handleReport = () => {
  console.log('신고:', postid); // ❌
};

const handleSave = () => {
  console.log('저장:', postid); // ❌
};
```

**After:**
```javascript
const [isReportModalOpen, setIsReportModalOpen] = useState(false);

const handleReport = () => {
  console.log('신고:', postid);
  setIsReportModalOpen(true); // ✅
};

const handleSave = () => {
  console.log('저장:', postid);
  alert('관심 글로 저장되었습니다.'); // ✅
};

<Modal isOpen={isReportModalOpen}>
  <ReportModal onClose={closeReportModal} postId={post.id} boardType="recipe" />
</Modal>
```

---

#### 3. ShareToolDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/share_tool_board/ShareToolDetail.js`

**수정 내용:**
- ✅ ReportModal import 및 신고 모달 구현
- ✅ handleSave alert 추가
- ✅ boardType="sharetool"로 전달

---

#### 4. ConvenienceCombDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/conv_board/ConvenienceCombDetail.js`

**수정 내용:**
- ✅ ReportModal import 및 신고 모달 구현
- ✅ handleSave alert 추가
- ✅ boardType="conv-recipe"로 전달

---

#### 5. ConvenienceReviewDetail.js ✅
**수정일:** 2025-11-10  
**경로:** `components/conv_board/ConvenienceReviewDetail.js`

**수정 내용:**
- ✅ ReportModal import 및 신고 모달 구현
- ✅ handleSave alert 추가
- ✅ boardType="conv-review"로 전달

---

## 🖼️ 27단계: 파일 업로드 시스템 및 이미지 처리 구현 (2025-11-12)

### 개요
- **목적:** 사용자 프로필 이미지 업로드 및 기본 이미지 자동 처리 구현
- **작업 기간:** 2025-11-12
- **수정 파일 수:** 10개 (백엔드 8개, 프론트엔드 2개)

---

### 백엔드 구현

#### 1. FileUploadService.java (신규) ✅
**수정일:** 2025-11-12  
**경로:** `backend/pro/src/main/java/com/moc/pro/file/service/FileUploadService.java`

**주요 기능:**
```java
// 이미지 업로드
ImageVO uploadImage(MultipartFile file, String type);

// 이미지 삭제
boolean deleteImage(String path);

// 이미지 검증
boolean validateImage(MultipartFile file);

// 기본 프로필 이미지 URL
String getDefaultProfileImageUrl();

// Placeholder 이미지 URL (동적 크기)
String getNoImageUrl(int width, int height);
```

**수정 내용:**
- ✅ 파일 업로드 공통 서비스 인터페이스 생성
- ✅ 타입별 업로드 폴더 분리 (user, recipe, sharetool, conv-recipe, shopping)
- ✅ 동적 크기 Placeholder 이미지 지원

---

#### 2. FileUploadServiceImpl.java (신규) ✅
**수정일:** 2025-11-12  
**경로:** `backend/pro/src/main/java/com/moc/pro/file/service/FileUploadServiceImpl.java`

**수정 내용:**
- ✅ 파일 검증 (크기: 10MB 이하, MIME 타입: image/jpeg, image/png, image/gif, image/webp)
- ✅ UUID 파일명 생성
- ✅ 외부 저장소 저장 (C:/uploads/moc/)
- ✅ 기본 이미지: `http://localhost:18880/images/default/default-profile.png`
- ✅ Placeholder: `https://placehold.co/{width}x{height}/png?text=No+Image`

**핵심 코드:**
```java
@Override
public String getNoImageUrl(int width, int height) {
    return NO_IMAGE_BASE_URL + width + "x" + height + "/png?text=No+Image";
}
```

---

#### 3. ImageVO.java (신규) ✅
**수정일:** 2025-11-12  
**경로:** `backend/pro/src/main/java/com/moc/pro/file/vo/ImageVO.java`

**수정 내용:**
- ✅ 이미지 업로드 결과 VO 생성
- ✅ url (HTTP 접근 경로), path (물리 경로) 포함

---

#### 4. UserVO.java ✅
**수정일:** 2025-11-12  
**경로:** `backend/pro/src/main/java/com/moc/pro/user/vo/UserVO.java`

**수정 내용:**
- ✅ userImageUrl 필드 추가
- ✅ userImagePath 필드 추가

---

#### 5. User.xml ✅
**수정일:** 2025-11-12  
**경로:** `backend/pro/src/main/resources/mybatis/mappers/User.xml`

**수정 내용:**
- ✅ selectUserById, getUserInfo에 LEFT JOIN tb_user_image 추가
- ✅ COALESCE로 이미지 없을 때 기본 이미지 자동 반환
- ✅ updateUserImage MERGE 쿼리 추가 (INSERT or UPDATE)
- ✅ IDENTITY 컬럼 사용 (SEQ_USER_IMAGE 시퀀스 제거)

**핵심 코드:**
```xml
<!-- 기본 이미지 자동 반환 -->
COALESCE(ui.user_image_url, 'http://localhost:18880/images/default/default-profile.png') AS user_image_url

<!-- MERGE: INSERT or UPDATE -->
<update id="updateUserImage">
    MERGE INTO tb_user_image ui
    USING (SELECT #{userId} as user_id FROM DUAL) src
    ON (ui.user_id = src.user_id)
    WHEN MATCHED THEN
        UPDATE SET user_image_url = #{userImageUrl}, ...
    WHEN NOT MATCHED THEN
        INSERT (user_id, user_image_url, ...) VALUES (#{userId}, ...)
</update>
```

---

#### 6. UserDAO, UserService, UserController ✅
**수정일:** 2025-11-12  
**경로:** `backend/pro/src/main/java/com/moc/pro/user/`

**수정 내용:**
- ✅ updateUserImage 메서드 추가
- ✅ FileUploadService 의존성 주입
- ✅ POST /api/user/{userId}/image API 추가
- ✅ getUserInfo 응답에 userImageUrl 추가

---

#### 7. WebConfig.java ✅
**수정일:** 2025-11-12  
**경로:** `backend/pro/src/main/java/com/moc/pro/WebConfig.java`

**수정 내용:**
- ✅ addResourceHandlers: 외부 업로드 폴더 매핑
- ✅ `/uploads/**` → `file:C:/uploads/moc/`

**핵심 코드:**
```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/uploads/**")
            .addResourceLocations("file:C:/uploads/moc/");
}
```

---

#### 8. application.properties ✅
**수정일:** 2025-11-12  
**경로:** `backend/pro/src/main/resources/application.properties`

**수정 내용:**
- ✅ Static Resource 경로 추가
```properties
spring.web.resources.static-locations=classpath:/static/,file:C:/uploads/moc/
```

---

### 프론트엔드 구현

#### 1. HeaderLoginForm.js ✅
**수정일:** 2025-11-12  
**경로:** `frontend/moc-pro/src/components/HeaderLoginForm.js`

**수정 내용:**
- ✅ getUserId() 함수로 서버 세션 기반 userId 가져오기
- ✅ 사용자 정보 조회 API 호출 (async/await)
- ✅ userInfo 상태: userName, userNickname, userImageUrl
- ✅ 기본 이미지 자동 반영

**핵심 코드:**
```javascript
useEffect(() => {
  const fetchUserInfo = async () => {
    const userId = await getUserId();
    if (userId) {
      const response = await axios.get(`http://localhost:18880/api/user/${userId}`);
      const user = response.data.data;
      setUserInfo({
        userName: user.userName || '',
        userNickname: user.userNickname || '',
        userImageUrl: user.userImageUrl || ''
      });
    }
  };
  fetchUserInfo();
}, []);
```

---

#### 2. MyPage.js ✅
**수정일:** 2025-11-12  
**경로:** `frontend/moc-pro/src/components/info/MyPage.js`

**수정 내용:**
- ✅ 이미지 업로드 기능 구현
- ✅ response.data.data로 응답 구조 수정
- ✅ 프로필 이미지 미리보기
- ✅ 업로드 성공 시 헤더 자동 갱신

---

### 추가 설정

#### 1. static/images/default/default-profile.png ✅
**위치:** `backend/pro/src/main/resources/static/images/default/default-profile.png`

**설명:**
- ✅ 사용자 프로필 기본 이미지
- ✅ URL: `http://localhost:18880/images/default/default-profile.png`

---

#### 2. copilot-instructions.md ✅
**수정일:** 2025-11-12  
**경로:** `.github/copilot-instructions.md`

**수정 내용:**
- ✅ FileUploadService 사용법 추가
- ✅ 표준 이미지 크기 정의 (카드: 250x250, 상세: 1000x800)
- ✅ 이미지 저장 구조 설명
- ✅ 사용 예시 코드 추가

---

### 해결한 문제

#### 1. sessionStorage 의존성 제거 ✅
**문제:** HeaderLoginForm이 sessionStorage의 userId에 의존하여 작동 안 함  
**해결:** authUtils의 getUserId() 함수로 서버 세션 기반 처리

#### 2. 시퀀스 부재 오류 해결 ✅
**에러:** `ORA-02289: 시퀀스가 존재하지 않습니다 (SEQ_USER_IMAGE)`  
**해결:** IDENTITY 컬럼 사용으로 user_image_id 자동 생성

#### 3. 응답 구조 불일치 해결 ✅
**문제:** 프론트엔드가 response.data로 직접 접근  
**해결:** response.data.data로 수정 (백엔드 응답: {success, data})

---

### 테스트 완료
- ✅ 프로필 이미지 업로드
- ✅ 헤더에 이미지 표시
- ✅ 마이페이지에 이미지 표시
- ✅ 기본 이미지 자동 반영
- ✅ 이미지 없을 때 Placeholder 표시 (게시판)

---

### 완료
- **모든 기본 기능 구현 완료**
- **표준화 작업 완료**
- **마이페이지 및 신고/저장 기능 완료**
- **파일 업로드 시스템 완료** ✅
- **RSbtn (신고/저장) 모든 상세페이지에서 정상 작동**

---

### 36단계: 실시간 채팅 시스템 구현 (REST API + WebSocket)

#### 개요
**목적:** ShareTool/WithShopping 게시판에서 실시간 채팅 기능 구현

**수정일:** 2025-11-13

**진행 상태:** ✅ 완료

---

#### 1. Backend 구현 (10개 파일)

##### VO (2개)
**파일:** `ChatRoomVO.java`, `ChatMessageVO.java`

**ChatRoomVO 주요 필드:**
```java
private int chatRoomId;
private int postId;
private String postType;  // 'sharetool' or 'shopping'
private String ownerId;   // 게시글 작성자
private String participantId;  // 신청자
private String roomStatus;  // 'OPEN', 'ACCEPTED', 'CLOSED'
private boolean isAccepted;
```

**ChatMessageVO 주요 필드:**
```java
private int chatMessageId;
private int chatRoomId;
private String senderId;
private String senderNickname;
private String messageContent;
private String messageType;  // 'TEXT', 'ACCEPT', 'REJECT', 'EXIT', 'SYSTEM'
private Timestamp createdAt;
```

##### DAO (2개 + XML 1개)
**파일:** `ChatDAO.java`, `ChatDAOImpl.java`, `Chat.xml`

**주요 메서드 (11개):**
```java
// 채팅방
insertChatRoom()           // 채팅방 생성
selectRoomById()           // 채팅방 정보 조회
selectRoomByPostAndUsers() // 중복 채팅방 확인
selectRoomsByUserId()      // 사용자별 채팅방 목록
updateRoomAccepted()       // 수락 처리
updateRoomStatus()         // 상태 변경
checkHiddenRoom()          // 숨김 체크
insertHiddenRoom()         // 채팅방 숨김

// 메시지
selectMessagesByRoomId()   // 메시지 목록 조회
insertMessage()            // 일반 메시지 저장
insertSystemMessage()      // 시스템 메시지 저장
```

**XML 주요 쿼리:**
```xml
<insert id="insertChatRoom"> ... </insert>
<select id="selectRoomById"> ... </select>
<select id="selectRoomByPostAndUsers"> ... </select>
<select id="selectMessagesByRoomId"> 
  LEFT JOIN TB_USER ON ... 
  ORDER BY CREATED_AT ASC
</select>
<insert id="insertMessage"> ... </insert>
```

##### Service (2개)
**파일:** `ChatService.java`, `ChatServiceImpl.java`

**주요 메서드:**
```java
createOrGetRoom()  // 채팅방 생성/조회 (중복 방지)
saveMessage()      // 메시지 저장 (WebSocket에서 호출)
acceptRoom()       // 수락 처리 (@Transactional)
  ├─ updateRoomAccepted()
  ├─ insertSystemMessage()
  └─ ShareToolService.completeShare() 또는
      WithShoppingService.completeShopping()
rejectRoom()       // 거절 처리 (@Transactional)
hideRoom()         // 채팅방 숨김
```

**외부 Service 연동:**
```java
@Autowired
private ShareToolService shareToolService;

@Autowired
private WithShoppingService withShoppingService;

// 수락 시 게시글 상태 자동 완료
if ("sharetool".equals(postType)) {
    shareToolService.completeShare(postId);
} else if ("shopping".equals(postType)) {
    withShoppingService.completeShopping(postId);
}
```

##### Controller (2개)
**파일:** `ChatController.java`, `ChatControllerImpl.java`

**REST API (6개):**
```java
POST   /api/chat/rooms                      // 채팅방 생성/조회
GET    /api/chat/rooms/{roomId}/messages    // 메시지 목록
POST   /api/chat/rooms/{roomId}/accept      // 수락
POST   /api/chat/rooms/{roomId}/reject      // 거절
POST   /api/chat/rooms/{roomId}/hide        // 숨김
GET    /api/chat/rooms                      // 사용자 채팅방 목록
```

**WebSocket (2개) - Controller에 통합:**
```java
@MessageMapping("/chat/{roomId}")
@SendTo("/topic/chat/{roomId}")
public ChatMessageVO sendMessage(
    @DestinationVariable int roomId,
    ChatMessageVO message) {
    
    // DB 저장
    chatService.saveMessage(
        message.getChatRoomId(),
        message.getSenderId(),
        message.getMessageContent()
    );
    
    // 브로드캐스트
    return message;
}
```

##### Config (1개)
**파일:** `WebSocketConfig.java`

**설정:**
```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat")
                .setAllowedOrigins("http://localhost:3000")
                .withSockJS();
    }
}
```

---

#### 2. Frontend 구현 (3개 파일)

##### ChatModal.js (WebSocket + REST API 연동)

**주요 기능:**
```javascript
// 1. WebSocket 연결
const socket = new SockJS('http://localhost:18880/ws/chat');
const client = new Client({
    webSocketFactory: () => socket,
    onConnect: () => {
        client.subscribe(`/topic/chat/${roomId}`, (message) => {
            // 실시간 메시지 수신
        });
    }
});

// 2. 이전 메시지 로드 (REST API)
axios.get(`http://localhost:18880/api/chat/rooms/${roomId}/messages`)

// 3. 메시지 전송 (WebSocket)
stompClientRef.current.publish({
    destination: `/app/chat/${roomId}`,
    body: JSON.stringify({
        senderId: userid,
        messageContent: message,
        messageType: 'TEXT'
    })
});

// 4. 수락 처리 (REST API)
axios.post(`http://localhost:18880/api/chat/rooms/${roomId}/accept`)

// 5. 거절 처리 (REST API)
axios.post(`http://localhost:18880/api/chat/rooms/${roomId}/reject`)

// 6. 채팅방 숨김 (REST API)
axios.post(`http://localhost:18880/api/chat/rooms/${roomId}/hide`)
```

##### ShareToolDetail.js (채팅방 생성)

**수정 내용:**
```javascript
const handleRequest = async () => {
    const response = await axios.post('http://localhost:18880/api/chat/rooms', {
        postId: parseInt(postid),
        postType: 'sharetool',
        ownerId: post.userid
    });
    
    if (response.data.success) {
        const roomId = response.data.data.chatRoomId;
        setChatRoomId(roomId);
        setIsChatModalOpen(true);
    }
};
```

##### ShoppingBoardDetailModal.js (채팅방 생성)

**수정 내용:**
```javascript
const handleApply = async () => {
    const response = await axios.post('http://localhost:18880/api/chat/rooms', {
        postId: data.id,
        postType: 'shopping',
        ownerId: data.userid
    });
    
    if (response.data.success) {
        const roomId = response.data.data.chatRoomId;
        setChatRoomId(roomId);
        setIsChatModalOpen(true);
    }
};
```

---

#### 3. 전체 흐름

**1단계: 신청하기 (ShareTool/Shopping)**
```
사용자 클릭 "신청하기"
→ POST /api/chat/rooms
→ 채팅방 생성/조회 (중복 방지)
→ ChatModal 열기
```

**2단계: 실시간 채팅**
```
메시지 입력 → 전송
→ WebSocket: /app/chat/{roomId}
→ DB 저장 (ChatService.saveMessage)
→ 브로드캐스트: /topic/chat/{roomId}
→ 모든 구독자 수신
```

**3단계: 수락 (작성자만)**
```
"수락" 버튼 클릭
→ POST /api/chat/rooms/{roomId}/accept
→ 채팅방 수락 처리
→ ShareTool/Shopping 상태 "완료" 변경
→ 시스템 메시지 전송
```

**4단계: 거절 (작성자만)**
```
"거절" 버튼 클릭
→ POST /api/chat/rooms/{roomId}/reject
→ 채팅방 상태 "CLOSED"
→ 퇴장 메시지 전송
→ 2초 후 채팅창 닫기
```

**5단계: 채팅방 삭제**
```
휴지통 아이콘 클릭
→ POST /api/chat/rooms/{roomId}/hide
→ 숨김 처리 (TB_CHAT_ROOM_HIDDEN)
→ 목록에서 제거
```

---

#### 4. 지침서 준수 확인

- ✅ **하나의 기능 = 하나의 Controller** (ChatControllerImpl에 REST + WebSocket 통합)
- ✅ **VO만 사용** (DTO 삭제, ChatMessageVO로 통합)
- ✅ **인터페이스 + Impl 구조** (VO 제외)
- ✅ **ResponseEntity는 Controller에서만**
- ✅ **외부 의존성 연동** (ShareToolService, WithShoppingService)
- ✅ **@Transactional 사용** (acceptRoom, rejectRoom)
- ✅ **허용 어노테이션만 사용**

---

#### 5. 필요 라이브러리

**Backend (Spring Boot):**
```gradle
implementation 'org.springframework.boot:spring-boot-starter-websocket'
implementation 'org.springframework.boot:spring-boot-starter-messaging'
```

**Frontend (React):**
```bash
npm install sockjs-client @stomp/stompjs
```

---

#### 6. API 엔드포인트 정리

| 메서드 | 엔드포인트 | 기능 |
|--------|-----------|------|
| POST | /api/chat/rooms | 채팅방 생성/조회 |
| GET | /api/chat/rooms/{roomId}/messages | 메시지 목록 조회 |
| POST | /api/chat/rooms/{roomId}/accept | 수락 (게시글 완료 처리) |
| POST | /api/chat/rooms/{roomId}/reject | 거절 (채팅방 종료) |
| POST | /api/chat/rooms/{roomId}/hide | 채팅방 숨김 |
| GET | /api/chat/rooms | 사용자 채팅방 목록 |
| WS | /ws/chat | WebSocket 연결 |
| WS | /app/chat/{roomId} | 메시지 전송 |
| WS | /topic/chat/{roomId} | 메시지 구독 |

---

#### 7. 핵심 코드 스니펫

**채팅방 중복 방지:**
```java
// Service
ChatRoomVO existingRoom = chatDAO.selectRoomByPostAndUsers(params);
if (existingRoom != null) {
    return existingRoom.getChatRoomId();
}
// 없으면 새로 생성
```

**수락 시 게시글 완료 처리:**
```java
// Service
if ("sharetool".equals(room.getPostType())) {
    shareToolService.completeShare(room.getPostId());
} else if ("shopping".equals(room.getPostType())) {
    withShoppingService.completeShopping(room.getPostId());
}
```

**WebSocket 메시지 브로드캐스트:**
```java
// Controller
@MessageMapping("/chat/{roomId}")
@SendTo("/topic/chat/{roomId}")
public ChatMessageVO sendMessage(
    @DestinationVariable int roomId,
    ChatMessageVO message) {
    
    chatService.saveMessage(...);
    return message;  // 모든 구독자에게 전송
}
```

---

### 테스트 완료
- ✅ 채팅방 생성/조회 (REST API)
- ✅ 실시간 메시지 송수신 (WebSocket)
- ✅ 이전 메시지 로드 (REST API)
- ✅ 수락 처리 및 게시글 완료 (ShareTool/WithShopping)
- ✅ 거절 처리 및 채팅방 종료
- ✅ 채팅방 숨김 처리
- ✅ 중복 채팅방 방지
- ✅ 외부 Service 연동 (ShareToolService, WithShoppingService)

---

### 완료
- **실시간 채팅 시스템 구현 완료** ✅
- **REST API + WebSocket 통합 완료** ✅
- **ShareTool/WithShopping 연동 완료** ✅
- **지침서 100% 준수** ✅

---

**최종 업데이트:** 2025-11-13  
**버전:** 1.0
