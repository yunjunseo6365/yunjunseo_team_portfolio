import React, {useState, useEffect} from 'react';
import style from '../../css/share_tool_board/ShareBoard.module.css';
import { useLocationData } from '../common/LocationData';
import { useNavigate, useSearchParams } from 'react-router-dom';
import PageNation from '../common/PageNation';
import { checkLogin } from '../common/authUtils';
import axios from 'axios';

function ShareBoard() {
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const page = searchParams.get('page') || '1';
    
    const { locationData, loading: locationLoading, error: locationError } = useLocationData();
    const [posts, setPosts] = useState([]);
    const [totalPage, setTotalPage] = useState(1);
    const [searchKeyword, setSearchKeyword] = useState('');
    // 최초 진입 시 자동 지역 지정하지 않고 전체 조회
    const [selectedSido, setSelectedSido] = useState('');
    const [selectedGu, setSelectedGu] = useState('');
    const [loading, setLoading] = useState(true);

    const handleSidoChange = (e) => {
        const newSido = e.target.value;
        setSelectedSido(newSido);
        // 시/도 선택 해제 또는 변경 시 구 초기화
        setSelectedGu('');
    };

    const handleGuChange = (e) => {
        setSelectedGu(e.target.value);
    };

    const handlePageChange = (newpage) => {
        navigate(`/sharetool?page=${newpage}`);
    };

    const handleDetail = (id) => {
        navigate(`/sharetool/detail/${id}`);
    };

    const handleWrite = async () => {
        const isLoggedIn = await checkLogin();
        if (!isLoggedIn) {
            alert('로그인이 필요합니다.');
            return;
        }
        navigate('/sharetool/write');
    };

    const handleSearch = async () => {
        try {
            // 검색어가 비어있으면 전체(필터 적용 여부만) 목록 조회
            if (!searchKeyword.trim()) {
                const listParams = { page: 1 };
                if (selectedSido) listParams.province = selectedSido;
                if (selectedGu) listParams.city = selectedGu;
                const listResp = await axios.get('http://localhost:18880/api/sharetool/list', { params: listParams });
                if (listResp.data.success) {
                    const { posts: fetchedPosts, totalPage: fetchedTotalPage } = listResp.data.data;
                    setPosts(fetchedPosts);
                    setTotalPage(fetchedTotalPage);
                    navigate('/sharetool?page=1');
                } else {
                    console.error('전체 조회 실패:', listResp.data.message);
                }
                return;
            }

            // 검색어가 있을 때만 검색 API 사용
            const response = await axios.get('http://localhost:18880/api/sharetool/search', {
                params: {
                    keyword: searchKeyword,
                    province: selectedSido,
                    city: selectedGu,
                    page: 1
                }
            });
            if (response.data.success) {
                const { posts: searchPosts, totalPage: searchTotalPage } = response.data.data;
                setPosts(searchPosts);
                setTotalPage(searchTotalPage);
                navigate('/sharetool?page=1');
            } else {
                alert('검색 실패: ' + response.data.message);
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
                const params = { page };
                // 선택된 값이 있을 때만 파라미터 추가
                if (selectedSido) params.province = selectedSido;
                if (selectedGu) params.city = selectedGu;
                const response = await axios.get('http://localhost:18880/api/sharetool/list', { params });
                if (response.data.success) {
                    const { posts: fetchedPosts, totalPage: fetchedTotalPage } = response.data.data;
                    setPosts(fetchedPosts);
                    setTotalPage(fetchedTotalPage);
                } else {
                    console.error('목록 조회 실패:', response.data.message);
                }
            } catch (error) {
                console.error('목록 조회 실패:', error);
            } finally {
                setLoading(false);
            }
        };
        fetchPosts();
    }, [page, selectedSido, selectedGu]);

    if (locationLoading) {
        return <div>지역 데이터 로딩중...</div>;
    }

    if (locationError) {
        return <div>지역 데이터를 불러오는 중 문제가 발생했습니다.</div>;
    }

    return (
        <div className={style.shopping_board_container}>
            <h1>요리 도구 나눔</h1>
            <hr/>
            <div className={style.search_form_container}>
                <input 
                    type='text' 
                    placeholder='키워드 입력 예) 후라이팬, 뒤집개'
                    value={searchKeyword}
                    onChange={(e) => setSearchKeyword(e.target.value)}
                    maxLength={50}
                />
            <div className={style.location_container}>
                <select className={style.sido_button} onChange={handleSidoChange} value={selectedSido}>
                    <option value=''>전체 시/도</option>
                    {Object.keys(locationData).map(sido => (
                        <option key={sido} value={sido}>{sido}</option>
                    ))}
                </select>
                <select className={style.sigungu_button} onChange={handleGuChange} value={selectedGu} disabled={!selectedSido}>
                    <option value=''>전체 시/군/구</option>
                    {selectedSido && locationData[selectedSido]?.map(sigungu => (
                        <option key={sigungu} value={sigungu}>{sigungu}</option>
                    ))}
                </select>
            </div>
            <button type='button' className={style.search_bar} onClick={handleSearch}>검색하기</button>
        </div>

        <div className={style.card_list_container}>
            {loading ? (
                <div className={style.loading}>요리도구나눔 목록을 불러오는 중...</div>
            ) : (
                posts.map((post) => (
                <div key={post.shareToolId}  className={style.shopping_card} onClick={() => handleDetail(post.shareToolId)}>
                    <div className={style.card_img}>
                        <img src={post.shareToolContent || 'https://placehold.co/250x250/png?text=No+Image'} alt='게시글 이미지'/>
                    </div>
                    <div className={style.card_content}>
                        <div className={style.card_text_row}>
                            <h2 className={style.card_title}>[{post.shareToolTitle}] {post.shareToolProvince} {post.shareToolCity}</h2>
                            <div className={style.card_meta_info}>
                                <span className={style.card_author}>작성자: {post.userNickname}</span>
                                <span className={style.card_date}>게시일 : {post.createdAt?.substring(0, 10)}</span>
                            </div>
                        </div>
                        <div className={style.status_div}>
                            <span className={style.card_place}>제품명: {post.shareToolProduct}</span>
                            <div className={post.shareToolStatus==='나눔중' ? `${style.card_button} ${style.status_button}`: `${style.card_button} ${style.status_button_done}`}>{post.shareToolStatus}</div>
                        </div>
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
export default ShareBoard;