import React, {useState, useEffect} from 'react';
import axios from 'axios';
import style from '../../css/conv_board/ConvenienceBoard.module.css';
import { useNavigate, useSearchParams } from 'react-router-dom';
import PageNation from '../common/PageNation';
import { checkLogin } from '../common/authUtils';

function ConvenienceBoard() {
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const page = searchParams.get('page') || '1';

    const [storeOptions, setStoreOptions] = useState([]);
    const [categoryOptions, setCategoryOptions] = useState([]);

    const [posts, setPosts] = useState([]);
    const [totalPage, setTotalPage] = useState(1);
    const [activeStore, setActiveStore] = useState('');
    const [activeCategory, setActiveCategory] = useState('');
    const [loading, setLoading] = useState(true);
    const [storeLogos, setStoreLogos] = useState({});

    // 편의점 옵션 로드
    useEffect(() => {
        const loadStores = async () => {
            try {
                const response = await axios.get('http://localhost:18880/api/common/codes/D01');
                const options = response.data.map(item => item.codeName);
                setStoreOptions(options);
                
                // ✅ 로고 이미지 미리 로드
                const logos = {};
                options.forEach(storeName => {
                    try {
                        logos[storeName] = require(`../../image/${storeName}.png`);
                    } catch (error) {
                        console.warn(`로고 이미지 없음: ${storeName}`);
                        logos[storeName] = null;
                    }
                });
                setStoreLogos(logos);

                if (options.length > 0 && !activeStore) {
                    setActiveStore(options[0]);
                }
            } catch (error) {
                console.error('편의점 옵션 로드 실패:', error);
                setStoreOptions([]);
            }
        };
        loadStores();
    }, []);

    // 카테고리 옵션 로드
    useEffect(() => {
        const loadCategories = async () => {
            try {
                const response = await axios.get('http://localhost:18880/api/common/codes/F01');
                const options = response.data.map(item => item.codeName);
                setCategoryOptions(options);
                
                if (options.length > 0 && !activeCategory) {
                    setActiveCategory(options[0]);
                }
            } catch (error) {
                console.error('카테고리 옵션 로드 실패:', error);
                setCategoryOptions([]);
            }
        };
        loadCategories();
    }, []);

    const loadPosts = async () => {
        setLoading(true);
        try {
            const response = await axios.get('http://localhost:18880/api/conv-review/list', {
                params: {
                    store: activeStore,
                    category: activeCategory,
                    page: page
                }
            });
            
            if (response.data.success) {
                setPosts(response.data.data.posts);
                setTotalPage(response.data.data.totalPage);
            } else {
                alert(response.data.message || '목록 조회에 실패했습니다.');
            }
        } catch (error) {
            console.error('목록 조회 실패:', error);
            if (error.response && error.response.data && error.response.data.message) {
                alert(error.response.data.message);
            } else if (error.request) {
                alert('서버와의 통신에 실패했습니다. 네트워크 연결을 확인해주세요.');
            } else {
                alert('목록 조회 중 오류가 발생했습니다.');
            }
        } finally {
            setLoading(false);
        }
    };

    const handleStoreClick = (storeName) => {
        setActiveStore(storeName);
        loadPosts();
    };

    const handleCategoryClick = (categoryName) => {
        setActiveCategory(categoryName);
        loadPosts();
    };

    const handlePageChange = (newpage) => {
        navigate(`/conv/review?page=${newpage}`);
    };

    const openReviewDetails = (id) => {
        navigate(`/conv/review/detail/${id}`);
    };

    const openReviewWrite = async () => {
        const isLoggedIn = await checkLogin();
        if (!isLoggedIn) {
            alert('로그인이 필요합니다.');
            return;
        }
        navigate(`/conv/review/write`);
    };

    useEffect(() => {
        loadPosts();
    }, [page, activeStore, activeCategory]);

    return (
        <div className={style.conv_board_container}>
            <h1>편의점 - 신제품 리뷰</h1>
            <hr/>
        <div className={style.store_filter_group}>
            {storeOptions.map(storesName => (
                <button 
                    type="button" 
                    key={storesName} 
                    className={`${style.filter_button} ${activeStore === storesName ? style.active : ''}`} 
                    onClick={() => handleStoreClick(storesName)}
                >
                    {storeLogos[storesName] && (
                        <img 
                            src={storeLogos[storesName]} 
                            alt={storesName} 
                            className={style.store_logo}
                        />
                    )}
                </button>
            ))}
        </div>
        <div className={style.category_tab_group}>
            {categoryOptions.map(categorysName => (
                <button 
                    type="button" 
                    key={categorysName} 
                    className={`${style.tab_button} ${activeCategory === categorysName ? style.active : ''}`} 
                    onClick={() => handleCategoryClick(categorysName)}
                >
                    {categorysName}
                </button>
            ))}
        </div>
        <div className={style.card_list_container}>
            {loading ? (
                <div className={style.loading}>편의점 리뷰를 불러오는 중...</div>
            ) : (
                posts.map((post) => (
                <div key={post.convReviewId} className={style.shopping_card} onClick={() => openReviewDetails(post.convReviewId)}>
                    <div className={style.card_img}>
                        <img src={post.firstImageUrl} alt={post.convReviewTitle}/>
                    </div>
                    <div className={style.card_content}>
                        <div className={style.card_text_row}>
                            <h2 className={style.card_title}>{post.convReviewTitle}</h2>
                            <div className={style.card_meta_info}>
                                <span className={style.card_author}>작성자: {post.userNickname}</span>
                                <span className={style.card_date}>게시일: {post.createdAt?.substring(0, 10)}</span>
                            </div>
                        </div>
                        <span className={style.card_place}>가격: {post.convReviewPrice ? `${post.convReviewPrice}원` : '가격 정보 없음'}</span>
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
                <button type='button' onClick={() => openReviewWrite()} className={style.write_btn}>글 쓰기</button>
            </div>
        </div>
        </div>
    )
}
export default ConvenienceBoard;