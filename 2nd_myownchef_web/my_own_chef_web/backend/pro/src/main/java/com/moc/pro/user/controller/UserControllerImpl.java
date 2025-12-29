package com.moc.pro.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.moc.pro.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import com.moc.pro.user.vo.UserVO;

/**
 * 사용자(User) Controller 구현체
 * 사용자 인증 관련 API 처리
 */
@Controller
public class UserControllerImpl implements UserController {
    
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_IS_ADMIN = "isAdmin";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_PARAM_USERID = "userid";
    private static final String KEY_PARAM_EMAIL = "email";
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    /**
     * 로그인
     * @param request { userid, password }
     * @param session HttpSession
     * @return ResponseEntity<Map<String, Object>>
     */
    @Override
    @PostMapping("/api/auth/login")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userid = request.get(KEY_PARAM_USERID);
            String password = request.get("password");
            
            // 입력 검증
            if (userid == null || userid.trim().isEmpty() || 
                password == null || password.trim().isEmpty()) {
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "아이디와 비밀번호를 입력해주세요.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // 로그인 처리
            boolean success = userService.login(userid, password, session);
            
            if (success) {
                response.put(KEY_SUCCESS, true);
                response.put(KEY_USER_ID, session.getAttribute(KEY_USER_ID));
                response.put(KEY_IS_ADMIN, session.getAttribute(KEY_IS_ADMIN));
                response.put(KEY_MESSAGE, "로그인 성공");
                return ResponseEntity.ok(response);
            } else {
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "아이디 또는 비밀번호가 올바르지 않습니다.");
                return ResponseEntity.ok(response);
            }
            
        } catch (Exception e) {
            response.put(KEY_SUCCESS, false);
            response.put(KEY_MESSAGE, "로그인 처리 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 로그인 확인
     * @param session HttpSession
     * @return ResponseEntity<Map<String, Object>>
     */
    @Override
    @GetMapping("/api/auth/check")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkLogin(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Object userId = session.getAttribute(KEY_USER_ID);
            Object isAdmin = session.getAttribute(KEY_IS_ADMIN);
            Object userNickname = session.getAttribute("userNickname");
            
            if (userId != null) {
                // 로그인 상태
                response.put(KEY_IS_LOGGED_IN, true);
                response.put(KEY_USER_ID, userId);
                response.put(KEY_IS_ADMIN, Boolean.TRUE.equals(isAdmin));
                response.put("userNickname", userNickname);
            } else {
                // 로그아웃 상태
                response.put(KEY_IS_LOGGED_IN, false);
                response.put(KEY_USER_ID, null);
                response.put(KEY_IS_ADMIN, false);
                response.put("userNickname", null);
            }
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put(KEY_IS_LOGGED_IN, false);
            response.put(KEY_MESSAGE, "로그인 확인 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 로그아웃
     * @param session HttpSession
     * @return ResponseEntity<Map<String, Object>>
     */
    @Override
    @PostMapping("/api/auth/logout")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Session 무효화
            session.invalidate();
            
            response.put(KEY_SUCCESS, true);
            response.put(KEY_MESSAGE, "로그아웃 성공");
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put(KEY_SUCCESS, false);
            response.put(KEY_MESSAGE, "로그아웃 처리 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 회원가입
     * @param request { userid, password, username, nickname, email }
     * @return ResponseEntity<Map<String, Object>>
     */
    @Override
    @PostMapping("/api/auth/signup")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> signup(@RequestBody Map<String, String> request) {
        try {
            // 입력 검증
            String userid = request.get("userid");
            String password = request.get("password");
            String username = request.get("username");
            String nickname = request.get("nickname");
            String email = request.get("email");
            
            if (userid == null || userid.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                username == null || username.trim().isEmpty() ||
                nickname == null || nickname.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
                
                Map<String, Object> response = new HashMap<>();
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "모든 필드를 입력해주세요.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // UserVO 생성
            UserVO user = new UserVO();
            user.setUserId(userid);
            user.setUserPwd(password);
            user.setUserName(username);
            user.setUserNickname(nickname);
            user.setUserEmail(email);
            
            // 회원가입 처리
            Map<String, Object> result = userService.signup(user);
            
            boolean success = Boolean.TRUE.equals(result.get(KEY_SUCCESS));
            if (success) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().body(result);
            }
            
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put(KEY_SUCCESS, false);
            response.put(KEY_MESSAGE, "회원가입 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 아이디 중복 확인
     * @param request { userid }
     * @return ResponseEntity<Map<String, Object>>
     */
    @Override
    @PostMapping("/api/auth/check-id")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkId(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userid = request.get("userid");
            
            if (userid == null || userid.trim().isEmpty()) {
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "아이디를 입력해주세요.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // 아이디 중복 확인
            int count = userService.checkUserIdDuplicate(userid);
            
            if (count > 0) {
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "이미 사용 중인 아이디입니다.");
                response.put("available", false);
            } else {
                response.put(KEY_SUCCESS, true);
                response.put(KEY_MESSAGE, "사용 가능한 아이디입니다.");
                response.put("available", true);
            }
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put(KEY_SUCCESS, false);
            response.put(KEY_MESSAGE, "중복 확인 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 아이디 찾기
     * @param request { name, email }
     * @return ResponseEntity<Map<String, Object>>
     */
    @Override
    @PostMapping("/api/auth/find-id")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> findUserId(@RequestBody Map<String, String> request) {
        try {
            String name = request.get("name");
            String email = request.get("email");
            
            // 입력 검증
            if (name == null || name.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "이름과 이메일을 입력해주세요.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // 아이디 찾기 처리
            Map<String, Object> result = userService.findUserId(name, email);
            
            boolean success = Boolean.TRUE.equals(result.get(KEY_SUCCESS));
            if (success) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.ok(result);
            }
            
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put(KEY_SUCCESS, false);
            response.put(KEY_MESSAGE, "아이디 찾기 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 비밀번호 재설정
     * @param request { name, userid, email }
     * @return ResponseEntity<Map<String, Object>>
     */
    @Override
    @PostMapping("/api/auth/reset-password")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody Map<String, String> request) {
        try {
            String name = request.get("name");
            String userid = request.get("userid");
            String email = request.get("email");
            
            // 입력 검증
            if (name == null || name.trim().isEmpty() ||
                userid == null || userid.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "모든 정보를 입력해주세요.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // 비밀번호 재설정 처리
            Map<String, Object> result = userService.resetPassword(name, userid, email);
            
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put(KEY_SUCCESS, false);
            response.put(KEY_MESSAGE, "비밀번호 재설정 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 사용자 정보 조회
     * @param userId 사용자 ID
     * @return ResponseEntity<Map<String, Object>>
     */
    @Override
    @GetMapping("/api/user/{userId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUserInfo(@PathVariable String userId) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 사용자 정보 조회
            UserVO user = userService.getUserInfo(userId);
            
            if (user == null) {
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "사용자를 찾을 수 없습니다.");
                return ResponseEntity.ok(response);
            }
            
            // 응답 데이터 구성
            Map<String, Object> userData = new HashMap<>();
            userData.put("userId", user.getUserId());
            userData.put("userName", user.getUserName());
            userData.put("userNickname", user.getUserNickname());
            userData.put("userEmail", user.getUserEmail());
            userData.put("userImageUrl", user.getUserImageUrl());  // 이미지 URL 추가
            userData.put("isAdmin", "Y".equals(user.getUserIsadmin()));
            
            response.put(KEY_SUCCESS, true);
            response.put("data", userData);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put(KEY_SUCCESS, false);
            response.put(KEY_MESSAGE, "사용자 정보 조회 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 사용자 정보 수정
     * @param userId 사용자 ID
     * @param request { nickname, email }
     * @param session HttpSession
     * @return ResponseEntity<Map<String, Object>>
     */
    @Override
    @PutMapping("/api/user/{userId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateUserInfo(@PathVariable String userId,
                                                               @RequestBody Map<String, String> request,
                                                               HttpSession session) {
        try {
            // 로그인 확인
            String sessionUserId = (String) session.getAttribute(KEY_USER_ID);
            if (sessionUserId == null || !sessionUserId.equals(userId)) {
                Map<String, Object> response = new HashMap<>();
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            String nickname = request.get("nickname");
            String email = request.get("email");
            
            // 입력 검증
            if (nickname == null || nickname.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "닉네임과 이메일을 입력해주세요.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // 사용자 정보 수정
            Map<String, Object> result = userService.updateUserInfo(userId, nickname, email);
            
            boolean success = Boolean.TRUE.equals(result.get(KEY_SUCCESS));
            if (success) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().body(result);
            }
            
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put(KEY_SUCCESS, false);
            response.put(KEY_MESSAGE, "회원 정보 수정 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 회원 탈퇴
     * @param userId 사용자 ID
     * @param session HttpSession
     * @return ResponseEntity<Map<String, Object>>
     */
    @Override
    @DeleteMapping("/api/user/{userId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> withdrawUser(@PathVariable String userId, 
                                                             HttpSession session) {
        try {
            // 로그인 확인
            String sessionUserId = (String) session.getAttribute(KEY_USER_ID);
            if (sessionUserId == null || !sessionUserId.equals(userId)) {
                Map<String, Object> response = new HashMap<>();
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 회원 탈퇴 처리 (Session도 무효화)
            Map<String, Object> result = userService.withdrawUser(userId, session);
            
            return ResponseEntity.ok(result);
            
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put(KEY_SUCCESS, false);
            response.put(KEY_MESSAGE, "회원 탈퇴 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 사용자 프로필 이미지 업로드
     * @param userId 사용자 ID
     * @param file 업로드할 이미지 파일
     * @return ResponseEntity<Map<String, Object>>
     */
    @Override
    public ResponseEntity<Map<String, Object>> uploadUserImage(String userId, 
                                                                org.springframework.web.multipart.MultipartFile file) {
        try {
            // 파일 유효성 검사
            if (file == null || file.isEmpty()) {
                Map<String, Object> response = new HashMap<>();
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "이미지 파일을 선택해주세요.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // 이미지 업로드 처리
            Map<String, Object> result = userService.updateUserImage(userId, file);
            
            boolean success = Boolean.TRUE.equals(result.get(KEY_SUCCESS));
            if (success) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().body(result);
            }
            
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put(KEY_SUCCESS, false);
            response.put(KEY_MESSAGE, "이미지 업로드 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
