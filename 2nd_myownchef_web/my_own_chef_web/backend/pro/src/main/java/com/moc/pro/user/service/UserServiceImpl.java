package com.moc.pro.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.moc.pro.file.service.FileUploadService;
import com.moc.pro.file.vo.ImageVO;
import com.moc.pro.user.dao.UserDAO;
import com.moc.pro.user.vo.UserVO;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 사용자(User) Service 구현체
 * 사용자 관련 비즈니스 로직 처리
 */
@Service
public class UserServiceImpl implements UserService {
    
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_ERROR_FIELD = "errorField";
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    private FileUploadService fileUploadService;
    
    @Autowired
    private org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder passwordEncoder;
    
    /**
     * 로그인 처리
     * @param userId 사용자 ID
     * @param password 비밀번호 (평문)
     * @param session HttpSession
     * @return 로그인 성공 여부
     */
    @Override
    public boolean login(String userId, String password, HttpSession session) {
        // 1. DB에서 사용자 조회 (아이디로만 조회)
        UserVO user = userDAO.selectUserById(userId);
        
        // 2. 사용자가 없으면 로그인 실패
        if (user == null) {
            return false;
        }
        
        // 3. BCrypt로 비밀번호 검증
        if (!passwordEncoder.matches(password, user.getUserPwd())) {
            return false;  // 비밀번호 불일치
        }
        
        // 4. Session에 사용자 정보 저장
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("userNickname", user.getUserNickname());
        session.setAttribute("isAdmin", "Y".equals(user.getUserIsadmin()));
        
        return true;
    }
    
    /**
     * 회원가입 처리
     * @param user 사용자 정보
     * @return Map<String, Object> (success, message, errorField)
     */
    @Override
    public Map<String, Object> signup(UserVO user) {
        Map<String, Object> result = new HashMap<>();
        
        // 1. 이메일 중복 확인
        int emailCount = userDAO.checkEmailDuplicate(user.getUserEmail());
        if (emailCount > 0) {
            result.put(KEY_SUCCESS, false);
            result.put(KEY_MESSAGE, "이미 사용 중인 이메일입니다.");
            result.put(KEY_ERROR_FIELD, "email");
            return result;
        }
        
        // 2. 닉네임 중복 확인
        int nicknameCount = userDAO.checkNicknameDuplicate(user.getUserNickname());
        if (nicknameCount > 0) {
            result.put(KEY_SUCCESS, false);
            result.put(KEY_MESSAGE, "이미 사용 중인 닉네임입니다.");
            result.put(KEY_ERROR_FIELD, "nickname");
            return result;
        }
        
        // 3. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getUserPwd());
        user.setUserPwd(encodedPassword);
        
        // 4. DB에 사용자 등록
        int insertCount = userDAO.insertUser(user);
        
        if (insertCount > 0) {
            result.put(KEY_SUCCESS, true);
            result.put(KEY_MESSAGE, "회원가입이 완료되었습니다.");
            return result;
        } else {
            result.put(KEY_SUCCESS, false);
            result.put(KEY_MESSAGE, "회원가입 중 오류가 발생했습니다.");
            return result;
        }
    }
    
    /**
     * 아이디 중복 확인
     * @param userId 사용자 ID
     * @return 중복 개수 (0이면 사용 가능)
     */
    @Override
    public int checkUserIdDuplicate(String userId) {
        return userDAO.checkUserIdDuplicate(userId);
    }
    
    /**
     * 사용자 정보 조회
     * @param userId 사용자 ID
     * @return UserVO 사용자 정보
     */
    @Override
    public UserVO getUserInfo(String userId) {
        return userDAO.getUserInfo(userId);
    }
    
    /**
     * 사용자 정보 수정
     * @param userId 사용자 ID
     * @param nickname 닉네임
     * @param email 이메일
     * @return Map<String, Object> (success, message, errorField)
     */
    @Override
    public Map<String, Object> updateUserInfo(String userId, String nickname, String email) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 1. 닉네임 중복 확인 (본인 제외)
            UserVO currentUser = userDAO.getUserInfo(userId);
            if (currentUser == null) {
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "사용자를 찾을 수 없습니다.");
                return response;
            }
            
            // 닉네임이 변경되었고, 다른 사용자가 이미 사용 중인지 확인
            if (!nickname.equals(currentUser.getUserNickname())) {
                int nicknameCount = userDAO.checkNicknameDuplicate(nickname);
                if (nicknameCount > 0) {
                    response.put(KEY_SUCCESS, false);
                    response.put(KEY_MESSAGE, "이미 사용 중인 닉네임입니다.");
                    response.put(KEY_ERROR_FIELD, "nickname");
                    return response;
                }
            }
            
            // 2. 이메일 중복 확인 (본인 제외)
            if (!email.equals(currentUser.getUserEmail())) {
                int emailCount = userDAO.checkEmailDuplicate(email);
                if (emailCount > 0) {
                    response.put(KEY_SUCCESS, false);
                    response.put(KEY_MESSAGE, "이미 사용 중인 이메일입니다.");
                    response.put(KEY_ERROR_FIELD, "email");
                    return response;
                }
            }
            
            // 3. 정보 수정
            UserVO user = new UserVO();
            user.setUserId(userId);
            user.setUserNickname(nickname);
            user.setUserEmail(email);
            user.setUpdatedBy(userId);
            
            int result = userDAO.updateUserInfo(user);
            
            if (result > 0) {
                response.put(KEY_SUCCESS, true);
                response.put(KEY_MESSAGE, "회원 정보가 수정되었습니다.");
            } else {
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "회원 정보 수정에 실패했습니다.");
            }
            
            return response;
            
        } catch (Exception e) {
            response.put(KEY_SUCCESS, false);
            response.put(KEY_MESSAGE, "회원 정보 수정 중 오류가 발생했습니다: " + e.getMessage());
            return response;
        }
    }
    
    /**
     * 회원 탈퇴
     * @param userId 사용자 ID
     * @param session HttpSession
     * @return Map<String, Object> (success, message)
     */
    @Override
    public Map<String, Object> withdrawUser(String userId, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 1. 사용자 존재 확인
            UserVO user = userDAO.getUserInfo(userId);
            if (user == null) {
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "사용자를 찾을 수 없습니다.");
                return response;
            }
            
            // 2. 회원 탈퇴 처리 (논리 삭제)
            int result = userDAO.withdrawUser(userId);
            
            if (result > 0) {
                // 3. Session 무효화 (로그아웃)
                if (session != null) {
                    session.invalidate();
                }
                
                response.put(KEY_SUCCESS, true);
                response.put(KEY_MESSAGE, "회원 탈퇴가 완료되었습니다.");
            } else {
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "회원 탈퇴에 실패했습니다.");
            }
            
            return response;
            
        } catch (Exception e) {
            response.put(KEY_SUCCESS, false);
            response.put(KEY_MESSAGE, "회원 탈퇴 중 오류가 발생했습니다: " + e.getMessage());
            return response;
        }
    }
    
    /**
     * 아이디 찾기 (앞4 + **** + 뒤3 마스킹)
     * @param userName 이름
     * @param userEmail 이메일
     * @return Map<String, Object> (success, message, maskedUserId)
     */
    @Override
    public Map<String, Object> findUserId(String userName, String userEmail) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 1. 이름 + 이메일로 사용자 조회
            UserVO user = userDAO.findUserIdByNameAndEmail(userName, userEmail);
            
            if (user == null) {
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "일치하는 사용자 정보를 찾을 수 없습니다.");
                return response;
            }
            
            // 2. 아이디 마스킹 처리 (앞4 + **** + 뒤3)
            String userId = user.getUserId();
            String maskedUserId = maskUserId(userId);
            
            response.put(KEY_SUCCESS, true);
            response.put(KEY_MESSAGE, userName + "님의 아이디는 " + maskedUserId + "입니다.");
            response.put("maskedUserId", maskedUserId);
            
            return response;
            
        } catch (Exception e) {
            response.put(KEY_SUCCESS, false);
            response.put(KEY_MESSAGE, "아이디 찾기 중 오류가 발생했습니다: " + e.getMessage());
            return response;
        }
    }
    
    /**
     * 비밀번호 재설정 (이메일 발송 문구만 표시)
     * @param userName 이름
     * @param userId 아이디
     * @param userEmail 이메일
     * @return Map<String, Object> (success, message)
     */
    @Override
    public Map<String, Object> resetPassword(String userName, String userId, String userEmail) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 1. 이름 + 아이디 + 이메일 일치 확인
            UserVO user = userDAO.selectUserById(userId);
            
            if (user == null || 
                !user.getUserName().equals(userName) || 
                !user.getUserEmail().equals(userEmail)) {
                response.put(KEY_SUCCESS, false);
                response.put(KEY_MESSAGE, "일치하는 사용자 정보를 찾을 수 없습니다.");
                return response;
            }
            
            // 2. 이메일 발송 문구만 표시 (실제 발송 안 함)
            response.put(KEY_SUCCESS, true);
            response.put(KEY_MESSAGE, "임시 비밀번호가 " + userEmail + "로 발송되었습니다.");
            
            return response;
            
        } catch (Exception e) {
            response.put(KEY_SUCCESS, false);
            response.put(KEY_MESSAGE, "비밀번호 재설정 중 오류가 발생했습니다: " + e.getMessage());
            return response;
        }
    }
    
    /**
     * 아이디 마스킹 처리 (앞4 + **** + 뒤3)
     * @param userId 원본 아이디
     * @return 마스킹된 아이디
     */
    private String maskUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            return "";
        }
        
        int length = userId.length();
        
        // 4자 이하면 뒤 2자만 마스킹
        if (length <= 4) {
            int visibleChars = Math.max(1, length - 2);
            return userId.substring(0, visibleChars) + "**";
        }
        
        // 7자 이하면 앞2 + ** + 뒤2
        if (length <= 7) {
            return userId.substring(0, 2) + "**" + userId.substring(length - 2);
        }
        
        // 8자 이상: 앞4 + **** + 뒤3
        return userId.substring(0, 4) + "****" + userId.substring(length - 3);
    }
    
    /**
     * 사용자 프로필 이미지 업데이트
     * @param userId 사용자 ID
     * @param file 업로드할 이미지 파일
     * @return Map<String, Object> (success, message, imageUrl)
     */
    @Override
    public Map<String, Object> updateUserImage(String userId, MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // 1. 사용자 존재 확인
            UserVO user = userDAO.getUserInfo(userId);
            if (user == null) {
                result.put(KEY_SUCCESS, false);
                result.put(KEY_MESSAGE, "존재하지 않는 사용자입니다.");
                return result;
            }
            
            // 2. 기존 이미지 있으면 삭제
            if (user.getUserImagePath() != null && !user.getUserImagePath().isEmpty()) {
                fileUploadService.deleteImage(user.getUserImagePath());
            }
            
            // 3. 새 이미지 업로드
            ImageVO imageVO = fileUploadService.uploadImage(file, "user");
            
            // 4. DB 업데이트 (MERGE)
            user.setUserImageUrl(imageVO.getUrl());
            user.setUserImagePath(imageVO.getPath());
            
            int updated = userDAO.updateUserImage(user);
            
            if (updated > 0) {
                result.put(KEY_SUCCESS, true);
                result.put(KEY_MESSAGE, "프로필 이미지가 업데이트되었습니다.");
                result.put("imageUrl", imageVO.getUrl());
            } else {
                result.put(KEY_SUCCESS, false);
                result.put(KEY_MESSAGE, "이미지 업데이트에 실패했습니다.");
            }
            
        } catch (IllegalArgumentException e) {
            result.put(KEY_SUCCESS, false);
            result.put(KEY_MESSAGE, "잘못된 이미지 파일입니다: " + e.getMessage());
        } catch (Exception e) {
            result.put(KEY_SUCCESS, false);
            result.put(KEY_MESSAGE, "이미지 업로드 중 오류가 발생했습니다: " + e.getMessage());
        }
        
        return result;
    }
    
}
