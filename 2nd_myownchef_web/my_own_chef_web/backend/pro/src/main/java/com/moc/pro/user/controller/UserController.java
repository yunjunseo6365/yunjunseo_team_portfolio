package com.moc.pro.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

/**
 * 사용자(User) Controller 인터페이스
 * 사용자 인증 및 관리 API
 */
public interface UserController {
    
    /**
     * 로그인
     * @param request { userid, password }
     * @param session HttpSession
     * @return ResponseEntity<Map<String, Object>>
     */
    @PostMapping("/api/auth/login")
    @ResponseBody
    ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> request, HttpSession session);
    
    /**
     * 로그인 확인
     * @param session HttpSession
     * @return ResponseEntity<Map<String, Object>>
     */
    @GetMapping("/api/auth/check")
    @ResponseBody
    ResponseEntity<Map<String, Object>> checkLogin(HttpSession session);
    
    /**
     * 로그아웃
     * @param session HttpSession
     * @return ResponseEntity<Map<String, Object>>
     */
    @PostMapping("/api/auth/logout")
    @ResponseBody
    ResponseEntity<Map<String, Object>> logout(HttpSession session);
    
    /**
     * 회원가입
     * @param request { userid, password, username, nickname, email }
     * @return ResponseEntity<Map<String, Object>>
     */
    @PostMapping("/api/auth/signup")
    @ResponseBody
    ResponseEntity<Map<String, Object>> signup(@RequestBody Map<String, String> request);
    
    /**
     * 아이디 중복 확인
     * @param request { userid }
     * @return ResponseEntity<Map<String, Object>>
     */
    @PostMapping("/api/auth/check-id")
    @ResponseBody
    ResponseEntity<Map<String, Object>> checkId(@RequestBody Map<String, String> request);
    
    /**
     * 아이디 찾기
     * @param request { name, email }
     * @return ResponseEntity<Map<String, Object>>
     */
    @PostMapping("/api/auth/find-id")
    @ResponseBody
    ResponseEntity<Map<String, Object>> findUserId(@RequestBody Map<String, String> request);
    
    /**
     * 비밀번호 재설정
     * @param request { name, userid, email }
     * @return ResponseEntity<Map<String, Object>>
     */
    @PostMapping("/api/auth/reset-password")
    @ResponseBody
    ResponseEntity<Map<String, Object>> resetPassword(@RequestBody Map<String, String> request);
    
    /**
     * 사용자 정보 조회
     * @param userId 사용자 ID
     * @return ResponseEntity<Map<String, Object>>
     */
    @GetMapping("/api/user/{userId}")
    @ResponseBody
    ResponseEntity<Map<String, Object>> getUserInfo(@PathVariable String userId);
    
    /**
     * 사용자 정보 수정
     * @param userId 사용자 ID
     * @param request { nickname, email }
     * @param session HttpSession
     * @return ResponseEntity<Map<String, Object>>
     */
    @PutMapping("/api/user/{userId}")
    @ResponseBody
    ResponseEntity<Map<String, Object>> updateUserInfo(@PathVariable String userId, 
                                                        @RequestBody Map<String, String> request,
                                                        HttpSession session);
    
    /**
     * 회원 탈퇴
     * @param userId 사용자 ID
     * @param session HttpSession
     * @return ResponseEntity<Map<String, Object>>
     */
    @DeleteMapping("/api/user/{userId}")
    @ResponseBody
    ResponseEntity<Map<String, Object>> withdrawUser(@PathVariable String userId, HttpSession session);
    
    /**
     * 사용자 프로필 이미지 업로드
     * @param userId 사용자 ID
     * @param file 업로드할 이미지 파일
     * @return ResponseEntity<Map<String, Object>>
     */
    @PostMapping("/api/user/{userId}/image")
    @ResponseBody
    ResponseEntity<Map<String, Object>> uploadUserImage(@PathVariable String userId,
                                                         @RequestParam("file") org.springframework.web.multipart.MultipartFile file);
    
}
