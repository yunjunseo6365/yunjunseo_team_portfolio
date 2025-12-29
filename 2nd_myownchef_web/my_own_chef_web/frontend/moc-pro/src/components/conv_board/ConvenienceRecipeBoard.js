import React, {useState, useEffect} from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import style from '../../css/conv_board/ConvenienceRecipeBoard.module.css';
import PageNation from '../common/PageNation';
import { BsCaretLeftSquareFill } from "react-icons/bs";
import { BsCaretRightSquareFill } from "react-icons/bs";
import { checkLogin } from '../common/authUtils';
import axios from 'axios';

function ConvenienceRecipeBoard() {
    const [activeCategory, setActiveCategory] = useState('');
    const [categoryOptions, setCategoryOptions] = useState([]);
    const [posts, setPosts] = useState([]);
    const [searchKeyword, setSearchKeyword] = useState('');
    const [searchProduct, setSearchProduct] = useState('');
    const [totalPage, setTotalPage] = useState(1);
    const [loading, setLoading] = useState(true);
    
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const page = searchParams.get('page') || '1';

    // 카테고리 옵션 로드
    useEffect(() => {
        const loadCategories = async () => {
            try {
                const response = await axios.get('http://localhost:18880/api/common/codes/F01');
                const options = response.data.map(item => item.codeName);
                setCategoryOptions(options);
                
                // 첫 번째 카테고리를 기본값으로 설정
                if (options.length > 0 && !activeCategory) {
                    setActiveCategory(options[0]);
                }
            } catch (error) {
                console.error('카테고리 로드 실패:', error);
                setCategoryOptions([]);
            }
        };
        loadCategories();
    }, []);

    const handlePageChange = (newpage) => {
        navigate(`/conv/recipe?page=${newpage}`);
    };

    const openRecipeDetails = (id) => {
        navigate(`/conv/recipe/detail/${id}`);
    };

    const openRecipeWrite = async () => {
        const isLoggedIn = await checkLogin();
        if (!isLoggedIn) {
            alert('로그인이 필요합니다.');
            return;
        }
        navigate(`/conv/recipe/write`);
    };

    const handleSearch = async () => {
        try {
            const response = await axios.get('http://localhost:18880/api/conv-recipe/search', {
                params: {
                    keyword: searchKeyword,
                    product: searchProduct,
                    category: activeCategory,
                    page: 1
                }
            });
            
            if (response.data.success) {
                setPosts(response.data.data.posts);
                setTotalPage(response.data.data.totalPage);
                
                // URL 파라미터 업데이트
                const params = new URLSearchParams();
                params.set('page', '1');
                if (searchKeyword) params.set('keyword', searchKeyword);
                if (searchProduct) params.set('product', searchProduct);
                if (activeCategory) params.set('category', activeCategory);
                navigate(`/conv/recipe?${params.toString()}`);
            }
        } catch (error) {
            console.error('검색 실패:', error);
            alert('검색에 실패했습니다.');
        }
    };

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try {
                const keyword = searchParams.get('keyword');
                const product = searchParams.get('product');
                const category = searchParams.get('category');
                
                if (keyword || product) {
                    // 검색 모드
                    const response = await axios.get('http://localhost:18880/api/conv-recipe/search', {
                        params: {
                            keyword: keyword || '',
                            product: product || '',
                            category: category || activeCategory,
                            page: page
                        }
                    });
                    
                    if (response.data.success) {
                        setPosts(response.data.data.posts);
                        setTotalPage(response.data.data.totalPage);
                    }
                } else {
                    // 목록 모드
                    const response = await axios.get('http://localhost:18880/api/conv-recipe/list', {
                        params: {
                            category: category || activeCategory,
                            page: page
                        }
                    });
                    
                    if (response.data.success) {
                        setPosts(response.data.data.posts);
                        setTotalPage(response.data.data.totalPage);
                    }
                }
            } catch (error) {
                console.error('데이터 로드 실패:', error);
            } finally {
                setLoading(false);
            }
        };
        
        fetchData();
    }, [page, activeCategory, searchParams, navigate]);

    return (
        <div className={style.conv_board_container}>
            <h1>편의점 - 파먹기(레시피)</h1>
            <hr/>
            <div className={style.header_container}>
                <input 
                    type='text' 
                    placeholder='레시피 키워드를 입력해주세요'
                    value={searchKeyword}
                    onChange={(e) => setSearchKeyword(e.target.value)}
                    maxLength={50}
                />
                <input 
                    type='text' 
                    placeholder='핵심 제품명을 입력해주세요'
                    value={searchProduct}
                    onChange={(e) => setSearchProduct(e.target.value)}
                    maxLength={50}
                />
                <button type="button" className={style.search_bar} onClick={handleSearch}>검색하기</button>
            </div>
        <div className={style.category_tab_group}>
            {categoryOptions.map(categorysName => (
                <button type="button" key={categorysName} className={`${style.tab_button} ${activeCategory === categorysName ? style.active : ''}`} 
            onClick={() => {
                setActiveCategory(categorysName);
                // 카테고리 변경 시 검색 상태 유지하면서 필터링
                const params = new URLSearchParams();
                params.set('page', '1');
                if (searchKeyword) params.set('keyword', searchKeyword);
                if (searchProduct) params.set('product', searchProduct);
                params.set('category', categorysName);
                navigate(`/conv/recipe?${params.toString()}`);
            }}>{categorysName}</button>
            ))}
        </div>
        <div className={style.card_list_container}>
            {loading ? (
                <div className={style.loading}>편의점 레시피를 불러오는 중...</div>
            ) : (
                posts.map(post => (
                <div key={post.convRecipeId} className={style.shopping_card} onClick={() => openRecipeDetails(post.convRecipeId)}>
                    <div className={style.card_img}>
                        <img src={post.firstImageUrl} 
                            alt={post.convRecipeTitle}/>
                    </div>
                    <div className={style.card_content}>
                        <div className={style.card_text_row}>
                            <h2 className={style.card_title}>{post.convRecipeTitle}</h2>
                            <div className={style.card_meta_info}>
                                <span className={style.card_author}>작성자: {post.userNickname}</span>
                                <span className={style.card_date}>게시일 : {post.createdAt?.substring(0, 10)}</span>
                            </div>
                        </div>
                        <span className={style.card_place}>핵심제품 : {post.convRecipeMainProduct}</span>
                    </div>
                </div>
                ))
            )}
            <div className={style.bottom_container}>
            <div className={style.page_change_btn}>
                <PageNation
                    onPageChange={handlePageChange}
                    currentPage={parseInt(page) || 1}
                    totalPage={totalPage}
                    size="middle"/>
            </div>
                <button type='button' onClick={() => openRecipeWrite()} className={style.write_btn}>글 쓰기</button>
            </div>
        </div>
        </div>
    )
}
export default ConvenienceRecipeBoard;