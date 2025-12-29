package com.moc.pro.user.dao;

import com.moc.pro.user.vo.UserVO;

/**
 * 사용자(User) DAO 인터페이스
 * TB_USER 테이블 데이터 접근
 */
public interface UserDAO {
    
    /**
     * 로그인 - 아이디와 비밀번호로 사용자 조회
     * @param userId 사용자 ID
     * @param userPwd 비밀번호 (BCrypt 해시)
     * @return UserVO 사용자 정보 (없으면 null)
     */
    UserVO selectUserByIdAndPwd(String userId, String userPwd);
    
    /**
     * 아이디로 사용자 조회 (BCrypt 로그인용)
     * @param userId 사용자 ID
     * @return UserVO 사용자 정보 (없으면 null)
     */
    UserVO selectUserById(String userId);
    
    /**
     * 회원가입 - 사용자 등록
     * @param user 사용자 정보
     * @return 등록된 행 수
     */
    int insertUser(UserVO user);
    
    /**
     * 이메일 중복 확인
     * @param email 이메일
     * @return 중복 개수 (0이면 사용 가능)
     */
    int checkEmailDuplicate(String email);
    
    /**
     * 닉네임 중복 확인
     * @param nickname 닉네임
     * @return 중복 개수 (0이면 사용 가능)
     */
    int checkNicknameDuplicate(String nickname);
    
    /**
     * 아이디 중복 확인
     * @param userId 사용자 ID
     * @return 중복 개수 (0이면 사용 가능)
     */
    int checkUserIdDuplicate(String userId);
    
    /**
     * 사용자 정보 조회 (마이페이지용)
     * @param userId 사용자 ID
     * @return UserVO 사용자 정보 (없으면 null)
     */
    UserVO getUserInfo(String userId);
    
    /**
     * 사용자 정보 수정 (닉네임, 이메일)
     * @param user 수정할 사용자 정보 (userId, nickname, email, updatedBy)
     * @return 수정된 행 수
     */
    int updateUserInfo(UserVO user);
    
    /**
     * 회원 탈퇴 (논리 삭제)
     * @param userId 사용자 ID
     * @return 수정된 행 수
     */
    int withdrawUser(String userId);
    
    /**
     * 아이디 찾기 (이름 + 이메일)
     * @param userName 이름
     * @param userEmail 이메일
     * @return UserVO 사용자 정보 (없으면 null)
     */
    UserVO findUserIdByNameAndEmail(String userName, String userEmail);
    
    /**
     * 사용자 프로필 이미지 업데이트 (MERGE - INSERT or UPDATE)
     * @param user 사용자 정보 (userId, userImageUrl, userImagePath 필수)
     * @return 처리된 행 수
     */
    int updateUserImage(UserVO user);
    
}
