package com.moc.pro.user.service;

import com.moc.pro.user.vo.UserVO;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

/**
 * 사용자(User) Service 인터페이스
 * 사용자 관련 비즈니스 로직 정의
 */
public interface UserService {
    
    /**
     * 로그인 처리
     * @param userId 사용자 ID
     * @param password 비밀번호 (평문)
     * @param session HttpSession
     * @return 로그인 성공 여부
     */
    boolean login(String userId, String password, HttpSession session);
    
    /**
     * 회원가입 처리
     * @param user 사용자 정보
     * @return Map<String, Object> (success, message, errorField)
     */
    Map<String, Object> signup(UserVO user);
    
    /**
     * 아이디 중복 확인
     * @param userId 사용자 ID
     * @return 중복 개수 (0이면 사용 가능)
     */
    int checkUserIdDuplicate(String userId);
    
    /**
     * 사용자 정보 조회
     * @param userId 사용자 ID
     * @return UserVO 사용자 정보
     */
    UserVO getUserInfo(String userId);
    
    /**
     * 사용자 정보 수정
     * @param userId 사용자 ID
     * @param nickname 닉네임
     * @param email 이메일
     * @return Map<String, Object> (success, message, errorField)
     */
    Map<String, Object> updateUserInfo(String userId, String nickname, String email);
    
    /**
     * 회원 탈퇴
     * @param userId 사용자 ID
     * @param session HttpSession
     * @return Map<String, Object> (success, message)
     */
    Map<String, Object> withdrawUser(String userId, HttpSession session);
    
    /**
     * 아이디 찾기
     * @param userName 이름
     * @param userEmail 이메일
     * @return Map<String, Object> (success, message, maskedUserId)
     */
    Map<String, Object> findUserId(String userName, String userEmail);
    
    /**
     * 비밀번호 재설정 (이메일 발송 문구만 표시)
     * @param userName 이름
     * @param userId 아이디
     * @param userEmail 이메일
     * @return Map<String, Object> (success, message)
     */
    Map<String, Object> resetPassword(String userName, String userId, String userEmail);
    
    /**
     * 사용자 프로필 이미지 업데이트
     * @param userId 사용자 ID
     * @param file 업로드할 이미지 파일
     * @return Map<String, Object> (success, message, imageUrl)
     */
    Map<String, Object> updateUserImage(String userId, org.springframework.web.multipart.MultipartFile file);
    
}
