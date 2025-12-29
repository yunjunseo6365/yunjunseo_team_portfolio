import React, { useEffect, useState } from "react";
import styles from '../../css/another_board/FreeBoard.module.css';
import PageNation from "../common/PageNation";
import { useNavigate, useSearchParams, Link } from "react-router-dom";
import { checkLogin } from '../common/authUtils';
import axios from 'axios';


function FreeBoard(){
    const [posts, setPosts] = useState([]);
    const [totalPage, setTotalPage] = useState(1);
    const [searchKeyword, setSearchKeyword] = useState('');
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const currentPage = searchParams.get('page') || '1';
    const [loading, setLoading] = useState(true);
    
    const handlePageChange = (newPage) => {
        navigate(`/freeboard?page=${newPage}`);
    };

    const moveWrite = async () => {
        const isLoggedIn = await checkLogin();
        if (!isLoggedIn) {
            alert('로그인이 필요합니다.');
            return;
        }
        navigate('/freeboard/write');
    };

    const handleSearch = async (e) => {
        e.preventDefault();
        
        // 검색어가 비어있으면 전체 목록으로 이동
        if (!searchKeyword.trim()) {
            navigate('/freeboard?page=1');
            setSearchKeyword('');
            return;
        }
        
        try {
            // axios 영역
            const response = await axios.get('http://localhost:18880/api/freeboard/search', {
                params: {
                    keyword: searchKeyword,
                    page: 1
                }
            });
            
            if (response.data.success) {
                setPosts(response.data.data.posts);
                setTotalPage(response.data.data.totalPage);
                navigate(`/freeboard?page=1&keyword=${searchKeyword}`);
            } else {
                alert('검색에 실패했습니다.');
            }
        } catch (error) {
            console.error('검색 실패:', error);
            alert('검색 중 오류가 발생했습니다.');
        }
    };

    useEffect(() => {
        const fetchPosts = async () => {
            setLoading(true);
            try {
                const keyword = searchParams.get('keyword');
                
                if (keyword) {
                    // 검색 결과 조회
                    const response = await axios.get('http://localhost:18880/api/freeboard/search', {
                        params: {
                            keyword: keyword,
                            page: currentPage
                        }
                    });
                    
                    if (response.data.success) {
                        setPosts(response.data.data.posts);
                        setTotalPage(response.data.data.totalPage);
                        setSearchKeyword(keyword);
                    }
                } else {
                    // 일반 목록 조회
                    const response = await axios.get('http://localhost:18880/api/freeboard/list', {
                        params: {
                            page: currentPage
                        }
                    });
                    
                    if (response.data.success) {
                        setPosts(response.data.data.posts);
                        setTotalPage(response.data.data.totalPage);
                    }
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
    }, [currentPage, searchParams]);


    return(
        <div className={styles.bodydiv}>
            <div className={styles.titlediv}>
                자유게시판
            </div>
            <div className={styles.searchdiv}>
                <form onSubmit={handleSearch}>
                    <label className={styles.search_text}>검색명</label>
                    <input 
                        type="text" 
                        placeholder="검색어를 입력해주세요."
                        value={searchKeyword}
                        onChange={(e) => setSearchKeyword(e.target.value)}
                        maxLength={50}
                    />
                    <button type="submit">검색</button>
                </form>
            </div>
            <div className={styles.boarddiv}>
                {loading ? (
                    <div className={styles.loading}>목록을 불러오는 중...</div>
                ) : (
                    <div className={styles.board_container}>
                        <div className={styles.boarditems_title}>번호</div>
                        <div className={styles.boarditems_title}>제목</div>
                        <div className={styles.boarditems_title}>작성일</div>
                        <div className={styles.boarditems_title_none}>작성자</div>
                        {posts.map((post, index) => (
                            <>
                                <div className={styles.boarditems}>{index + 1}</div>
                                <div className={styles.boarditems}><Link to={`/freeboard/${post.freeboardId}`}>{post.freeboardTitle}</Link></div>
                                <div className={styles.boarditems}>{post.createdAt?.substring(0, 10)}</div>
                                <div className={styles.boarditems_none}>{post.userNickname}</div>
                            </>
                        ))}
                    </div>
                )}
                <div className={styles.bottomdiv}>
                    <div className={styles.pagenationdiv}>
                        <PageNation
                            currentPage={parseInt(currentPage) || 1}
                            totalPage={totalPage}
                            size="middle"
                            onPageChange={handlePageChange}
                            />
                    </div>
                    <div className={styles.writeBtn}>
                        <button type="button" onClick={moveWrite}>글쓰기</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default FreeBoard;