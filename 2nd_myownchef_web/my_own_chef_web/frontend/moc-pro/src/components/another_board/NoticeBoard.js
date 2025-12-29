import React, { useEffect, useState } from "react";
import PageNation from "../common/PageNation";
import styles from '../../css/another_board/NoticeBoard.module.css';
import { useNavigate, useSearchParams } from "react-router-dom";
import { checkLogin, checkAdmin } from '../common/authUtils';
import axios from 'axios';

function NoticeBoard(){
    const [posts, setPosts] = useState([]);
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const [totalPage, setTotalPage] = useState(1);
    const currentPage = searchParams.get('page');
    const [isAdmin, setIsAdmin] = useState(false);
    const [loading, setLoading] = useState(true);

    const moveDetail = (id) => {
        navigate(`/notice/detail/${id}?page=${currentPage}`);
    };

    const handlePageChange = (newpage) => {
        navigate(`/notice?page=${newpage}`);
    };

    const moveWrite = async () => {
        const isLoggedIn = await checkLogin();
        if (!isLoggedIn) {
            alert('로그인이 필요합니다.');
            return;
        }
        
        const admin = await checkAdmin();
        if (!admin) {
            alert('관리자만 작성할 수 있습니다.');
            return;
        }
        
        navigate('/notice/write');
    };
    
    useEffect(() => {
        // axios 영역 - 관리자 권한 확인
        // GET /api/users/${userId}/role
        // Response: { role: 'admin' | 'user' }
        const fetchAdmin = async () => {
            const admin = await checkAdmin();
            setIsAdmin(admin);
        };
        fetchAdmin();
    }, []);

    useEffect(() => {
        const fetchPosts = async () => {
            setLoading(true);
            try {
                const response = await axios.get('http://localhost:18880/api/notice/list', {
                    params: {
                        page: currentPage || 1
                    }
                });
                
                if (response.data.success) {
                    setPosts(response.data.data.posts);
                    setTotalPage(response.data.data.totalPage);
                }
            } catch (error) {
                console.error('목록 조회 실패:', error);
                setPosts([]);
                setTotalPage(1);
            } finally {
                setLoading(false);
            }
        };
        
        fetchPosts();
    }, [currentPage]);

    return(
        <div className={styles.bodydiv}>
            <div className={styles.titlediv}>
                공지사항
            </div>
            <div className={styles.boarddiv}>
                {loading ? (
                    <div className={styles.loading}>공지사항을 불러오는 중...</div>
                ) : (
                    <div className={styles.board_container}>
                        <div className={styles.boarditems_title}>번호</div>
                        <div className={styles.boarditems_title}>제목</div>
                        <div className={styles.boarditems_title}>작성일</div>
                        {posts.map((post, index) => (
                            <>
                                <div className={styles.boarditems}>{index + 1}</div>
                                <div className={`${styles.boarditems} ${styles.cursor}`} onClick={() => moveDetail(post.noticeId)}>{post.noticeTitle}</div>
                                <div className={styles.boarditems}>{post.createdAt?.substring(0, 10)}</div>
                            </>
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
                    {/* 관리자인경우에만 보이게 */}
                    <div className={`${styles.writeBtn} ${!isAdmin ? styles.hidden : ''}`}>
                        <button type="button" onClick={moveWrite}>글쓰기</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default NoticeBoard;