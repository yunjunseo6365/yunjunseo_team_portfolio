import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import style from '../../css/info/MyPage.module.css'; // 스타일링을 위한 CSS 파일 임포트
import PageNation from '../common/PageNation';
import { getUserId } from '../common/authUtils';
import axios from 'axios';

// axios 기본 설정
axios.defaults.withCredentials = true;

function MyPage() {
  const navigate = useNavigate();
  
  // 1. 상태(State) 관리: 모든 데이터를 여기서 관리합니다.
  const [userInfo, setUserInfo] = useState({
    userid: '',
    name: '',
    nickname: '',
    email: '',
    profileImage: '', // 백엔드에서 기본값 제공
  });

  const [myPosts, setMyPosts] = useState([]);
  const [interestedPosts, setInterestedPosts] = useState([]);
  const [isActive, setIsActive] = useState(true);
  const [prevUserInfo, setPrevUserInfo] = useState({});
  const [loading, setLoading] = useState(true);
  const [myPostsLoading, setMyPostsLoading] = useState(false);
  const [interestedPostsLoading, setInterestedPostsLoading] = useState(false);

  // 페이지네이션 상태
  const [myPostsPage, setMyPostsPage] = useState(1);
  const [myPostsTotalPage, setMyPostsTotalPage] = useState(1);
  const [interestedPostsPage, setInterestedPostsPage] = useState(1);
  const [interestedPostsTotalPage, setInterestedPostsTotalPage] = useState(1);

  // 사용자 정보 조회 (최초 1회만)
  useEffect(() => {
    const fetchUserInfo = async () => {
      setLoading(true);
      const userId = await getUserId();
      
      if(!userId) {
        alert('로그인이 필요합니다.');
        navigate('/login');
        return;
      }

      // axios 영역 - 사용자 정보 조회
      try {
        const response = await axios.get(`http://localhost:18880/api/user/${userId}`);
        
        const userData = response.data.data; // response.data.data로 수정!
        setUserInfo({
          userid: userData.userId,
          name: userData.userName,
          nickname: userData.userNickname,
          email: userData.userEmail,
          profileImage: userData.userImageUrl || '' // 백엔드에서 기본값 제공
        });
        console.log(response.data);
      } catch (error) {
        console.error('사용자 정보 조회 실패:', error);
        alert('사용자 정보를 불러오는 중 오류가 발생했습니다.');
      } finally {
        setLoading(false);
      }
    };
    fetchUserInfo();
  }, [navigate]);

  // 내가 쓴 글 조회 (myPostsPage 변경마다)
  useEffect(() => {
    const fetchMyPosts = async () => {
      setMyPostsLoading(true);
      try {
        const response = await axios.get('http://localhost:18880/api/mypage/posts', {
          params: { page: myPostsPage }
        });
        
        if (response.data.success) {
          setMyPosts(response.data.data.posts);
          setMyPostsTotalPage(response.data.data.totalPage);
        } else {
          console.error('내가 쓴 글 조회 실패:', response.data.message);
        }
      } catch (error) {
        console.error('내가 쓴 글 조회 실패:', error);
        if (error.response?.status === 401) {
          alert('로그인이 필요합니다.');
          navigate('/login');
        }
      } finally {
        setMyPostsLoading(false);
      }
    };
    setMyPosts([]); // 페이지 변경 시 기존 글 목록 초기화
    fetchMyPosts();
  }, [myPostsPage, navigate]);
  
  // 관심 글 조회 (interestedPostsPage 변경마다)
  useEffect(() => {
    const fetchInterestedPosts = async () => {
      setInterestedPostsLoading(true);
      try {
        const response = await axios.get('http://localhost:18880/api/mypage/saved', {
          params: { page: interestedPostsPage }
        });
        
        if (response.data.success) {
          setInterestedPosts(response.data.data.posts);
          setInterestedPostsTotalPage(response.data.data.totalPage);
        } else {
          console.error('저장한 글 조회 실패:', response.data.message);
        }
      } catch (error) {
        console.error('저장한 글 조회 실패:', error);
        if (error.response?.status === 401) {
          alert('로그인이 필요합니다.');
          navigate('/login');
        }
      } finally {
        setInterestedPostsLoading(false);
      }
    };
    fetchInterestedPosts();
  }, [interestedPostsPage, navigate]);


  const handleUpdate = (e) => {
    const {name, value} = e.target;
    if (name) {
      setUserInfo(prev => ({ ...prev, [name]: value }));
    }
  };

  const handleCancel = () => {
    setIsActive(true);
    setUserInfo(prevUserInfo);
  };

  const handleComplete = async () => {
    if(!userInfo.nickname || !userInfo.email){
      alert('닉네임과 이메일은 필수 입력 사항입니다.');
      return;
    }
    
    // axios 영역 - 사용자 정보 수정
    try {
      const response = await axios.put(`http://localhost:18880/api/user/${userInfo.userid}`, {
        nickname: userInfo.nickname,
        email: userInfo.email
      });
      
      if (response.data.success) {
        alert(response.data.message || '회원 정보가 수정되었습니다.');
        setIsActive(true);
      } else {
        alert(response.data.message || '회원 정보 수정에 실패했습니다.');
        // errorField가 있으면 해당 필드에 포커스
        if (response.data.errorField) {
          const errorInput = document.querySelector(`input[name="${response.data.errorField}"]`);
          if (errorInput) {
            errorInput.focus();
          }
        }
      }
    } catch (error) {
      console.error('회원 정보 수정 실패:', error);
      
      // 백엔드에서 보낸 에러 메시지 표시 (중복 등)
      if (error.response && error.response.data) {
        alert(error.response.data.message || '회원 정보 수정에 실패했습니다.');
        
        // errorField가 있으면 해당 필드에 포커스
        if (error.response.data.errorField) {
          const errorInput = document.querySelector(`input[name="${error.response.data.errorField}"]`);
          if (errorInput) {
            errorInput.focus();
          }
        }
      } else {
        alert('회원 정보 수정 중 오류가 발생했습니다.');
      }
    }
  };

  const handleEdit = () => {
    setPrevUserInfo(userInfo);
    setIsActive(false);
  };

  const handleWithdrawal = async () => {
    if (window.confirm("정말로 회원 탈퇴를 하시겠습니까?")) {
      // axios 영역 - 회원 탈퇴
      try {
        const response = await axios.delete(`http://localhost:18880/api/user/${userInfo.userid}`);
        
        if (response.data.success) {
          alert(response.data.message || '회원 탈퇴가 완료되었습니다.');
          // 탈퇴 성공 시 메인 페이지로 이동 (Session은 백엔드에서 무효화됨)
          navigate('/');
        } else {
          alert(response.data.message || '회원 탈퇴에 실패했습니다.');
        }
      } catch (error) {
        console.error('회원 탈퇴 실패:', error);
        alert('회원 탈퇴 중 오류가 발생했습니다.');
      }
    }
  };

  // 프로필 이미지 업로드
  const handleImageUpload = async (e) => {
    const file = e.target.files[0];
    
    if(!file) return;
    
    // 파일 크기 체크 (10MB 제한)
    if(file.size > 10 * 1024 * 1024) {
      alert('파일 크기는 10MB 이하여야 합니다.');
      return;
    }
    
    // 파일 타입 체크
    if(!file.type.startsWith('image/')) {
      alert('이미지 파일만 업로드 가능합니다.');
      return;
    }
    
    // 미리보기
    const reader = new FileReader();
    reader.onloadend = () => {
      setUserInfo(prev => ({
        ...prev,
        profileImage: reader.result
      }));
    };
    reader.readAsDataURL(file);
    
    // axios 영역 - 이미지 업로드
    try {
      const formData = new FormData();
      formData.append('file', file);
      
      const response = await axios.post(
        `http://localhost:18880/api/user/${userInfo.userid}/image`,
        formData,
        {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
      );
      
      if (response.data.success) {
        alert(response.data.message || '프로필 이미지가 업데이트되었습니다.');
        // 업로드된 이미지 URL로 상태 업데이트
        setUserInfo(prev => ({
          ...prev,
          profileImage: response.data.imageUrl
        }));
      } else {
        alert(response.data.message || '이미지 업로드에 실패했습니다.');
      }
    } catch (error) {
      console.error('이미지 업로드 실패:', error);
      alert('이미지 업로드 중 오류가 발생했습니다.');
    }
  };

  // 게시글 클릭 핸들러
  const handlePostClick = (post) => {
    // boardType에 따라 상세 페이지로 이동
    switch(post.boardType) {
      case 'recipe':
        navigate(`/recipe/detail/${post.id}`);
        break;
      case 'sharetool':
        navigate(`/sharetool/detail/${post.id}`);
        break;
      case 'conv-recipe':
        navigate(`/conv/recipe/detail/${post.id}`);
        break;
      case 'conv-review':
        navigate(`/conv/review/detail/${post.id}`);
        break;
      case 'withshopping':
        // 같이쇼핑은 모달이므로 목록 페이지로 이동 (postid를 query로 전달 가능)
        navigate(`/withshopping?modal=detail&id=${post.id}`);
        break;
      case 'freeboard':
        navigate(`/freeboard/${post.id}`);
        break;
      case 'notice':
        navigate(`/notice/detail/${post.id}`);
        break;
      default:
        console.log('알 수 없는 게시판 타입:', post.boardType);
    }
  };

  // 내가 쓴 글 페이지 변경
  const handleMyPostsPageChange = (newPage) => {
    setMyPostsPage(newPage);
  };

  // 관심 글 페이지 변경
  const handleInterestedPostsPageChange = (newPage) => {
    setInterestedPostsPage(newPage);
  };

  // JSX 렌더링: 실제 화면에 보일 UI 구조
  return (
    <div className={style.myPageContainer} >
      {loading ? (
        <div className={style.loading}>사용자 정보를 불러오는 중...</div>
      ) : (
        <>
          {/* --- 왼쪽 사용자 정보 패널 --- */}
          <div className={style.leftPanel}>
        <div className={style.userProfileBox}>
          <div className={style.profileImageContainer}>
            <img src={userInfo.profileImage} alt="profile" className={style.profileImage} />
            <input 
              type="file" 
              id="profileImageInput" 
              accept="image/*"
              onChange={handleImageUpload}
              style={{display: 'none'}}
            />
            <button 
              className={style.uploadButton}
              onClick={() => document.getElementById('profileImageInput').click()}
            >
              사진업로드
            </button>
          </div>
          <div className={style.userInfoItem}>
            <span className={style.label}>이름</span>
            <span className={style.value}>{userInfo.name}</span>
          </div>

          <div className={style.userInfoItem}>
            <span className={style.label}>닉네임</span>
            <div className={style.valueWithButton}>
              <input 
                type="text" 
                value={userInfo.nickname} 
                name="nickname" 
                className={`${style.nickname_value} ${isActive ? '' : style.active}`} 
                disabled={isActive}
                onChange={handleUpdate}
                maxLength={20}
                required
              />
            </div>
          </div>

          <div className={style.userInfoItem}>
            <span className={style.label}>이메일</span>
            <div className={style.valueWithButton}>
              <input 
                type="email" 
                value={userInfo.email}  
                name='email' 
                className={`${style.email_value} ${isActive ? '' : style.active}`}
                disabled={isActive}
                onChange={handleUpdate}
                maxLength={50}
                required
              />
            </div>
          </div>
          <div className={style.btndiv}>
            <button className={`${style.editButton} ${isActive ? '' : style.edit_deactive}`} onClick={handleEdit}>수정</button>
            <button className={`${style.cancelButton} ${isActive ? '' : style.edit_active}`} 
                    onClick={handleCancel}>
                      취소</button>
            <button className={`${style.completeButton} ${isActive ? '' : style.edit_active}`} 
                    onClick={handleComplete}>
                      완료</button>
          </div>
        </div>
      </div>

      {/* --- 오른쪽 콘텐츠 패널 --- */}
      <div className={style.rightPanel}>
        {/* 내가 쓴 글 섹션 */}
        <div className={style.postListBox}>
          <div className={style.listTitle}>내가 쓴 글</div>
          {myPostsLoading ? (
            <div className={style.listLoading}>내가 쓴 글을 불러오는 중...</div>
          ) : (
            myPosts.map(post => (
              <div 
                key={post.id} 
                className={style.postListItem}
                onClick={() => handlePostClick(post)}
                style={{cursor: 'pointer'}}
              >
                <span>{post.title}</span>
                <span>{post.type}</span>
                <span>{post.date}</span>
              </div>
            ))
          )}
          <div className={style.pagediv}>
            <PageNation 
              size="small"
              currentPage={myPostsPage}
              totalPage={myPostsTotalPage}
              onPageChange={handleMyPostsPageChange}
            />
          </div>
        </div>

        {/* 관심 글 섹션 */}
        <div className={style.postListBox}>
          <div className={style.listTitle}>관심 글</div>
          {interestedPostsLoading ? (
            <div className={style.listLoading}>관심 글을 불러오는 중...</div>
          ) : (
            interestedPosts.map(post => (
              <div 
                key={post.id} 
                className={style.postListItem}
                onClick={() => handlePostClick(post)}
                style={{cursor: 'pointer'}}
              >
                <span>{post.title}</span>
                <span>{post.type}</span>
                <span>{post.date}</span>
              </div>
            ))
          )}
          <div className={style.pagediv}>
            <PageNation 
              size="small"
              currentPage={interestedPostsPage}
              totalPage={interestedPostsTotalPage}
              onPageChange={handleInterestedPostsPageChange}
            />
          </div>
        </div>
      </div>
          <div className={style.user_out_div}>
              <button className={style.withdrawButton} onClick={handleWithdrawal}>회원 탈퇴</button>
          </div>
        </>
      )}
    </div>
  );
}

export default MyPage;