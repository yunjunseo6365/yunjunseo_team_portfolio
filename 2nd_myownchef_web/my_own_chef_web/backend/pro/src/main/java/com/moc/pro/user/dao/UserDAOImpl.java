package com.moc.pro.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moc.pro.user.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

/**
 * 사용자(User) DAO 구현체
 * MyBatis를 통한 TB_USER 테이블 접근
 */
@Repository
public class UserDAOImpl implements UserDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.moc.pro.user.dao.UserDAO";
    
    /**
     * 로그인 - 아이디와 비밀번호로 사용자 조회
     * @param userId 사용자 ID
     * @param userPwd 비밀번호 (BCrypt 해시)
     * @return UserVO 사용자 정보 (없으면 null)
     */
    @Override
    public UserVO selectUserByIdAndPwd(String userId, String userPwd) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("userPwd", userPwd);
        
        return sqlSession.selectOne(NAMESPACE + ".selectUserByIdAndPwd", params);
    }
    
    /**
     * 아이디로 사용자 조회 (BCrypt 로그인용)
     * @param userId 사용자 ID
     * @return UserVO 사용자 정보 (없으면 null)
     */
    @Override
    public UserVO selectUserById(String userId) {
        return sqlSession.selectOne(NAMESPACE + ".selectUserById", userId);
    }
    
    /**
     * 회원가입 - 사용자 등록
     * @param user 사용자 정보
     * @return 등록된 행 수
     */
    @Override
    public int insertUser(UserVO user) {
        return sqlSession.insert(NAMESPACE + ".insertUser", user);
    }
    
    /**
     * 이메일 중복 확인
     * @param email 이메일
     * @return 중복 개수 (0이면 사용 가능)
     */
    @Override
    public int checkEmailDuplicate(String email) {
        return sqlSession.selectOne(NAMESPACE + ".checkEmailDuplicate", email);
    }
    
    /**
     * 닉네임 중복 확인
     * @param nickname 닉네임
     * @return 중복 개수 (0이면 사용 가능)
     */
    @Override
    public int checkNicknameDuplicate(String nickname) {
        return sqlSession.selectOne(NAMESPACE + ".checkNicknameDuplicate", nickname);
    }
    
    /**
     * 아이디 중복 확인
     * @param userId 사용자 ID
     * @return 중복 개수 (0이면 사용 가능)
     */
    @Override
    public int checkUserIdDuplicate(String userId) {
        return sqlSession.selectOne(NAMESPACE + ".checkUserIdDuplicate", userId);
    }
    
    /**
     * 사용자 정보 조회 (마이페이지용)
     * @param userId 사용자 ID
     * @return UserVO 사용자 정보 (없으면 null)
     */
    @Override
    public UserVO getUserInfo(String userId) {
        return sqlSession.selectOne(NAMESPACE + ".getUserInfo", userId);
    }
    
    /**
     * 사용자 정보 수정 (닉네임, 이메일)
     * @param user 수정할 사용자 정보
     * @return 수정된 행 수
     */
    @Override
    public int updateUserInfo(UserVO user) {
        return sqlSession.update(NAMESPACE + ".updateUserInfo", user);
    }
    
    /**
     * 회원 탈퇴 (논리 삭제)
     * @param userId 사용자 ID
     * @return 수정된 행 수
     */
    @Override
    public int withdrawUser(String userId) {
        return sqlSession.update(NAMESPACE + ".withdrawUser", userId);
    }
    
    /**
     * 아이디 찾기 (이름 + 이메일)
     * @param userName 이름
     * @param userEmail 이메일
     * @return UserVO 사용자 정보 (없으면 null)
     */
    @Override
    public UserVO findUserIdByNameAndEmail(String userName, String userEmail) {
        Map<String, String> params = new HashMap<>();
        params.put("userName", userName);
        params.put("userEmail", userEmail);
        return sqlSession.selectOne(NAMESPACE + ".findUserIdByNameAndEmail", params);
    }
    
    /**
     * 사용자 프로필 이미지 업데이트 (MERGE - INSERT or UPDATE)
     * @param user 사용자 정보 (userId, userImageUrl, userImagePath 필수)
     * @return 처리된 행 수
     */
    @Override
    public int updateUserImage(UserVO user) {
        return sqlSession.update(NAMESPACE + ".updateUserImage", user);
    }
    
}
