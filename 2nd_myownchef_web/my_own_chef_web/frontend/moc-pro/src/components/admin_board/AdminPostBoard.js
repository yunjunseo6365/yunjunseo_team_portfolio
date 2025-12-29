import React, { useEffect, useState } from "react";
import axios from "axios";
import styles from '../../css/admin_board/AdminPostBoard.module.css';
import PageNation from "../common/PageNation";
import { useNavigate, useSearchParams } from "react-router-dom";

function AdminPostBoard(){
    const [posts, setPosts] = useState([]);
    const [totalPage, setTotalPage] = useState(1);
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const currentPage = searchParams.get('page');
    const [searchText, setSearchText] = useState('');
    const [selectedCategories, setSelectedCategories] = useState([]);
    const [loading, setLoading] = useState(true);

    const handlePageChange = (newPage) => {
        navigate(`/manage/board?page=${newPage}`);
    };

    const deletePost = async (post) => {
        const result = window.confirm('정말 삭제하시겠습니까?');

        if(result){
            try {
                const response = await axios.delete('http://localhost:18880/api/admin/posts', {
                    data: {
                        boardType: post.boardType,
                        postId: post.postId
                    }
                });
                
                if (response.data.success) {
                    alert('게시글이 삭제되었습니다.');
                    window.location.reload();
                } else {
                    alert('게시글 삭제 실패: ' + response.data.message);
                }
            } catch (error) {
                console.error('게시글 삭제 실패:', error);
                if (error.response?.status === 403) {
                    alert('관리자 권한이 필요합니다.');
                } else {
                    alert('게시글 삭제 실패');
                }
            }
        }
    };

    const viewDetails = (postid, category) => {
        // 카테고리별로 다른 상세 페이지로 이동
        if(category === '자유게시판') {
            navigate(`/freeboard/detail/${postid}`);
        } else if(category === '공지사항') {
            navigate(`/notice/detail/${postid}`);
        } else if(category === '레시피 게시판') {
            navigate(`/recipe/detail/${postid}`);
        } else if(category === '요리도구 나눔') {
            navigate(`/sharetool/detail/${postid}`);
        } else if(category === '편의점 - 신제품') {
            navigate(`/conv/review/detail/${postid}`);
        } else if(category === '편의점 - 파먹기') {
            navigate(`/conv/recipe/detail/${postid}`);
        } else if(category === '같이장보기') {
            navigate(`/withshopping?modal=detail&id=${postid}`);
        }
    };

    const handleCheckboxChange = (e) => {
        const { value, checked } = e.target;
        if(checked){
            setSelectedCategories(prev => [...prev, value]);
        } else {
            setSelectedCategories(prev => prev.filter(cat => cat !== value));
        }
    };

    const handleOnSubmit = (e) => {
        e.preventDefault();
        // 검색 시 1페이지로 이동 (useEffect에서 자동 호출됨)
        navigate('/manage/board?page=1');
    };

    useEffect(() => {
        const fetchPosts = async () => {
            setLoading(true);
            try {
                const response = await axios.get('http://localhost:18880/api/admin/posts', {
                    params: {
                        page: currentPage || 1,
                        title: searchText || null,
                        categories: selectedCategories.length > 0 ? selectedCategories : null
                    }
                });
                
                if (response.data.success) {
                    setPosts(response.data.data.posts);
                    setTotalPage(response.data.data.totalPage);
                } else {
                    console.error('게시글 목록 조회 실패:', response.data.message);
                    if (response.status === 403) {
                        alert('관리자 권한이 필요합니다.');
                        navigate('/');
                    }
                }
            } catch (error) {
                console.error('게시글 목록 조회 실패:', error);
                if (error.response?.status === 403) {
                    alert('관리자 권한이 필요합니다.');
                    navigate('/');
                }
            } finally {
                setLoading(false);
            }
        };
        fetchPosts();
    }, [currentPage, navigate, searchText, selectedCategories]);

    return(
        <div className={styles.bodydiv}>
            <div className={styles.titlediv}>
                게시글 관리
            </div>
            <div className={styles.searchdiv}>
                <form onSubmit={handleOnSubmit}>
                    <div className={styles.search_checkbox_div}>
                        <span className={styles.search_title_name}>검색옵션</span>
                        <div className={styles.checkbox_container}>
                            <div>
                                <input className={styles.checkbox_input} name="category" type="checkbox" id='checkbox01' value="자유게시판" onChange={handleCheckboxChange}/>
                                <label htmlFor='checkbox01'>자유게시판</label> 
                            </div>
                            <div>
                                <input className={styles.checkbox_input} name="category" type="checkbox" id='checkbox02' value="공지사항" onChange={handleCheckboxChange}/>
                                <label htmlFor='checkbox02'>공지사항</label> 
                            </div>
                            <div>
                                <input className={styles.checkbox_input} name="category" type="checkbox" id='checkbox03' value="레시피 게시판" onChange={handleCheckboxChange}/>
                                <label htmlFor='checkbox03'>레시피 게시판</label> 
                            </div>
                            <div>
                                <input className={styles.checkbox_input} name="category" type="checkbox" id='checkbox04' value="요리도구 나눔" onChange={handleCheckboxChange}/>
                                <label htmlFor='checkbox04'>요리도구 나눔</label> 
                            </div>
                            <div>
                                <input className={styles.checkbox_input} name="category" type="checkbox" id='checkbox05' value="편의점 - 신제품" onChange={handleCheckboxChange}/>
                                <label htmlFor='checkbox05'>편의점 - 신제품</label> 
                            </div>
                            <div>
                                <input className={styles.checkbox_input} name="category" type="checkbox" id='checkbox06' value="편의점 - 파먹기" onChange={handleCheckboxChange}/>
                                <label htmlFor='checkbox06'>편의점 - 파먹기</label> 
                            </div>
                            <div>
                                <input className={styles.checkbox_input} name="category" type="checkbox" id='checkbox07' value="같이장보기" onChange={handleCheckboxChange}/>
                                <label htmlFor='checkbox07'>같이장보기</label> 
                            </div>
                        </div>                                                                                                                        
                    </div>
                    <div className={styles.search_input_div}>
                        <label className={styles.search_title_name}>검색어</label>
                        <input 
                            type="text" 
                            placeholder="제목을 입력해주세요." 
                            value={searchText} 
                            onChange={(e) => setSearchText(e.target.value)}
                            maxLength={100}
                        />
                        <button type="submit">검색</button>
                    </div>
                </form>
            </div>
            <div className={styles.boarddiv}>
                {loading ? (
                    <div className={styles.loading}>게시글 목록을 불러오는 중...</div>
                ) : (
                    <div className={styles.board_container}>
                        <div className={styles.boarditems_title}>번호</div>
                        <div className={styles.boarditems_title}>작성자</div>
                        <div className={styles.boarditems_title}>제목</div>
                        <div className={styles.boarditems_title}>게시일</div>
                        <div className={styles.boarditems_title}>카테고리</div>
                        <div className={styles.boarditems_title_none}></div>
                        {posts.map((post, index) => (
                        <React.Fragment key={post.postId}>
                            <div className={styles.boarditems}>{index + 1}</div>
                            <div className={styles.boarditems}>{post.userNickname}</div>
                            <div className={styles.boarditems_post_title} onClick={() => viewDetails(post.postId, post.category)}>{post.title}</div>
                            <div className={styles.boarditems}>{post.createdAt}</div>
                            <div className={styles.boarditems}>{post.category}</div>
                            <div className={styles.boarditems_none}><button onClick={() => deletePost(post)}>삭제</button></div>
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

export default AdminPostBoard;