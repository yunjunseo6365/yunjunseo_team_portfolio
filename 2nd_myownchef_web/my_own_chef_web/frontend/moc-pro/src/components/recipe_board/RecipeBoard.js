import React, {useState, useEffect} from 'react';
import style from '../../css/recipe_board/RecipeBoard.module.css'
import { FaRegCommentAlt } from "react-icons/fa";
import { useNavigate, useSearchParams } from 'react-router-dom';
import PageNation from '../common/PageNation';
import { checkLogin } from '../common/authUtils';
import axios from 'axios';


function RecipeBoard() {
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const page = searchParams.get('page') || '1';
    
    const [posts, setPosts] = useState([]);
    const [totalPage, setTotalPage] = useState(1);
    const [searchKeyword, setSearchKeyword] = useState('');
    const [searchIngredient, setSearchIngredient] = useState('');
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchPosts = async () => {
            setLoading(true);
            try {
                const keyword = searchParams.get('keyword');
                const ingredient = searchParams.get('ingredient');
                
                if (keyword || ingredient) {
                    // 검색 결과 조회
                    const response = await axios.get('http://localhost:18880/api/recipe/search', {
                        params: {
                            keyword: keyword || '',
                            ingredient: ingredient || '',
                            page: page
                        }
                    });
                    
                    if (response.data.success) {
                        setPosts(response.data.data.posts);
                        setTotalPage(response.data.data.totalPage);
                        setSearchKeyword(keyword || '');
                        setSearchIngredient(ingredient || '');
                    }
                } else {
                    // 일반 목록 조회
                    const response = await axios.get('http://localhost:18880/api/recipe/list', {
                        params: {
                            page: page
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
    }, [page, searchParams]);

    const handlePageChange = (newpage) => {
        navigate(`/recipe?page=${newpage}`);
    };

    const handleDetail = (id) => {
        navigate(`/recipe/detail/${id}`);
    };

    const handleWrite = async () => {
        const isLoggedIn = await checkLogin();
        if (!isLoggedIn) {
            alert('로그인이 필요합니다.');
            return;
        }
        navigate('/recipe/write');
    };

    const handleSearch = async (e) => {
        e.preventDefault();
        
        // 둘 다 비어있으면 전체 목록으로 이동
        if (!searchKeyword.trim() && !searchIngredient.trim()) {
            navigate('/recipe?page=1');
            setSearchKeyword('');
            setSearchIngredient('');
            return;
        }
        
        try {
            // axios 영역
            const response = await axios.get('http://localhost:18880/api/recipe/search', {
                params: {
                    keyword: searchKeyword,
                    ingredient: searchIngredient,
                    page: 1
                }
            });
            
            if (response.data.success) {
                setPosts(response.data.data.posts);
                setTotalPage(response.data.data.totalPage);
                
                const params = new URLSearchParams();
                params.set('page', '1');
                if (searchKeyword) params.set('keyword', searchKeyword);
                if (searchIngredient) params.set('ingredient', searchIngredient);
                navigate(`/recipe?${params.toString()}`);
            } else {
                alert('검색에 실패했습니다.');
            }
        } catch (error) {
            console.error('검색 실패:', error);
            alert('검색 중 오류가 발생했습니다.');
        }
    };

    

    return (
        <div className={style.recipe_board_container}>
                <h1>레시피 게시판</h1>
                <hr/>
            <div className={style.search_form_container}>
                <input 
                    type='text' 
                    placeholder='키워드 입력 예) 제육볶음, 닭갈비'
                    value={searchKeyword}
                    onChange={(e) => setSearchKeyword(e.target.value)}
                    maxLength={50}
                />
                <input 
                    type='text' 
                    placeholder='메인재료를 입력해주세요 중복X'
                    value={searchIngredient}
                    onChange={(e) => setSearchIngredient(e.target.value)}
                    maxLength={30}
                />
                <button type='button' className={style.search_bar} onClick={handleSearch}>검색하기</button>
            </div>
        <div className={style.card_list_container}>
            {loading ? (
                <div className={style.loading}>레시피를 불러오는 중...</div>
            ) : (
                posts.map((post) => (
                <div key={post.recipeId} className={style.shopping_card} onClick={() => handleDetail(post.recipeId)}>
                <div className={style.card_img}>
                    <img src={post.recipeContent} alt={post.recipeTitle}/>
                </div>
                <div className={style.card_content}>
                    <div className={style.card_text_row}>
                        <h2 className={style.card_title}>[{post.recipeTitle}]</h2>
                        <div className={style.card_meta_info}>
                            <span className={style.card_author}>작성자: {post.userNickname}</span>
                            <span className={style.card_date}>게시일 : {post.createdAt?.substring(0, 10)}</span>
                        </div>
                    </div>
                    <span className={style.card_place}>핵심재료: {post.recipeMainIngredient}</span>
                </div>
            </div>
                ))
            )}
        </div>
        <div className={style.bottom_container}>
            <div className={style.page_change_btn}>
                <PageNation
                    onPageChange={handlePageChange}
                    currentPage={Number.parseInt(page) || 1}
                    totalPage={totalPage}
                    size="middle"/>
            </div>
                <button type='button' className={style.write_btn} onClick={handleWrite}>글 쓰기</button>
            </div>
        </div>
    )
}
export default RecipeBoard;