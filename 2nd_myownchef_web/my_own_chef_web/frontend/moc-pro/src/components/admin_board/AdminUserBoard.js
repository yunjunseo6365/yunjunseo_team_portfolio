import React, { useEffect, useState } from "react";
import axios from "axios";
import styles from '../../css/admin_board/AdminUserBoard.module.css';
import PageNation from "../common/PageNation";
import { useNavigate, useSearchParams } from "react-router-dom";

function AdminUserBoard(){
    const [posts, setPosts] = useState([]);
    const [totalPage, setTotalPage] = useState(1);
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const currentPage = searchParams.get('page');
    const [searchText, setSearchText] = useState('');
    const [loading, setLoading] = useState(true);

    const handlePageChange = (newPage) => {
        navigate(`/manage/user?page=${newPage}`);
    };

    const deleteUser = async (userid) => {
        const result = window.confirm('정말 삭제하시겠습니까?');

        if(result){
            try {
                const response = await axios.delete(`http://localhost:18880/api/admin/users/${userid}`);
                
                if (response.data.success) {
                    alert('회원이 삭제되었습니다.');
                    // 목록 새로고침
                    window.location.reload();
                } else {
                    alert('회원 삭제 실패: ' + response.data.message);
                }
            } catch (error) {
                console.error('회원 삭제 실패:', error);
                if (error.response?.status === 403) {
                    alert('관리자 권한이 필요합니다.');
                } else {
                    alert('회원 삭제 실패');
                }
            }
        }
    };

    const handleOnSubmit = (e) => {
        e.preventDefault();
        fetchUsers();
        // 검색 시 1페이지로 이동 (useEffect에서 자동 호출됨)
        navigate('/manage/user?page=1');
    };

    const fetchUsers = async () => {
        setLoading(true);
        try {
            const response = await axios.get('http://localhost:18880/api/admin/users', {
                params: {
                    page: currentPage || 1,
                    nickname: searchText || null
                }
            });
            
            if (response.data.success) {
                setPosts(response.data.data.users);
                setTotalPage(response.data.data.totalPage);
            } else {
                console.error('회원 목록 조회 실패:', response.data.message);
                if (response.status === 403) {
                    alert('관리자 권한이 필요합니다.');
                    navigate('/');
                }
            }
        } catch (error) {
            console.error('회원 목록 조회 실패:', error);
            if (error.response?.status === 403) {
                alert('관리자 권한이 필요합니다.');
                navigate('/');
            }
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchUsers();
    }, [currentPage, navigate]);

    return(
        <div className={styles.bodydiv}>
            <div className={styles.titlediv}>
                회원관리
            </div>
            <div className={styles.searchdiv}>
                <form onSubmit={handleOnSubmit}>
                    <label>검색명</label>
                    <input 
                        type="text" 
                        placeholder="닉네임을 입력해주세요." 
                        value={searchText} 
                        onChange={(e) => setSearchText(e.target.value)}
                        maxLength={20}
                    />
                    <button type="submit">검색</button>
                </form>
            </div>
            <div className={styles.boarddiv}>
                {loading ? (
                    <div className={styles.loading}>회원 목록을 불러오는 중...</div>
                ) : (
                    <div className={styles.board_container}>
                        <div className={styles.boarditems_title}>번호</div>
                        <div className={styles.boarditems_title}>이름</div>
                        <div className={styles.boarditems_title}>닉네임</div>
                        <div className={styles.boarditems_title}>이메일</div>
                        <div className={styles.boarditems_title}>가입일</div>
                        <div className={styles.boarditems_title_none}></div>
                        {posts.map((post, index) => (
                        <React.Fragment key={post.userId}>
                            <div className={styles.boarditems}>{index + 1}</div>
                            <div className={styles.boarditems}>{post.userName}</div>
                            <div className={styles.boarditems}>{post.userNickname}</div>
                            <div className={styles.boarditems}>{post.userEmail}</div>
                            <div className={styles.boarditems}>{post.createdAt}</div>
                            <div className={styles.boarditems_none}><button onClick={() => deleteUser(post.userId)}>삭제</button></div>
                        </React.Fragment>
                        ))}
                    </div>
                )}
                <div className={styles.bottomdiv}>
                    <div className={styles.pagenationdiv}>
                        <PageNation
                            size="middle"
                            currentPage={parseInt(currentPage) || 1}
                            totalPage={totalPage}
                            onPageChange={handlePageChange}
                            />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AdminUserBoard;