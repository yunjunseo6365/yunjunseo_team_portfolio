import React, {useEffect, useState} from 'react';
import style from '../../css/with_shopping_board/ShoppingBoard.module.css';
import modalStyle from '../../css/with_shopping_board/WithShoppingModal.module.css';
import { useLocationData } from '../common/LocationData';
import {ShoppingBoardModal} from './ShoppingBoardModal';
import {ShoppingBoardDetailModal} from './ShoppingBoardDetailModal';
import Modal from 'react-modal';
import { useNavigate, useSearchParams } from 'react-router-dom';
import PageNation from '../common/PageNation';
import { checkLogin } from '../common/authUtils';
import axios from 'axios';

function ShoppingBoard() {
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const page = searchParams.get('page') || '1';
    const modalType = searchParams.get('modal');
    const postId = searchParams.get('id');

    const { locationData, loading: locationLoading, error: locationError } = useLocationData();
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [totalPage, setTotalPage] = useState(1);
    // 최초 진입시 필터 미적용 (전체 조회)
    const [data, setData] = useState({
        si: '', // 사용자가 선택할 때까지 빈 값 유지
        gu: ''
    });

    const handleOnChange = (e) => {
        const {name, value} = e.target;
        if (name === 'si') {
            setData({
                si: value,
                gu: ''  // gu 초기화
            });
        } else {
            setData(prev => ({
                ...prev,
                [name]: value
            }));
        }
    };

    const loadPosts = async () => {
        setLoading(true);
        try {
            // 빈 값은 파라미터에서 제외해 전체 조회 유도
            const params = { page };
            if (data.si) params.si = data.si;
            if (data.gu) params.gu = data.gu;

            const response = await axios.get('http://localhost:18880/api/withshopping/list', { params });
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

    const handleSearch = () => {
        loadPosts();
    };

    const openWriteModal = async () => {
        const isLoggedIn = await checkLogin();
        if (!isLoggedIn) {
            alert('로그인이 필요합니다.');
            return;
        }
        navigate(`/withshopping?page=${page}&modal=write`);
    };

    const openDetailsModal = (id) => {
        navigate(`/withshopping?page=${page}&modal=detail&id=${id}`);
    };

    const closeModal = (editMode) => {
        loadPosts();
        if(!editMode){
            navigate(`/withshopping?page=1`);
        }else{
            navigate(`/withshopping?page=${page}`);
        }
    };

    const handlePageChange = (newpage) => {
        navigate(`/withshopping?page=${newpage}`);
    };

    useEffect(() => {
        // 페이지 또는 필터 변경 시 목록 갱신
        loadPosts();
    }, [page, data.si, data.gu]);

    if (locationLoading) {
        return <div>지역 데이터 로딩중...</div>;
    }

    if (locationError) {
        return <div>지역 데이터를 불러오는 중 문제가 발생했습니다.</div>;
    }

    return (
        <div className={style.shopping_board_container}>
            <h1>같이 장보기</h1><hr/>
            <div className={style.search_form_container}>
            <div className={style.location_container}>
                <select className={style.sido_button} onChange={handleOnChange} value={data.si} name="si">
                    <option value=''>전체 시/도</option>
                    {Object.keys(locationData).map(sido => (
                        <option key={sido} value={sido}>{sido}</option>
                    ))}
                </select>
                {data.si && (
                    <select className={style.sigungu_button} onChange={handleOnChange} value={data.gu} name="gu">
                        <option value=''>전체 시/군/구</option>
                        {locationData[data.si].map(sigungu => (
                            <option key={sigungu} value={sigungu}>{sigungu}</option>
                        ))}
                    </select>
                )}
            </div>
            <button type='button' className={style.search_bar} onClick={handleSearch}>검색하기</button>
        </div>
        <div className={style.card_div}>
            {loading ? (
                <div className={style.loading}>같이쇼핑 목록을 불러오는 중...</div>
            ) : (
                posts.map((post) => (
                    <div key={post.withShoppingId} className={style.shopping_card} onClick={() => openDetailsModal(post.withShoppingId)}>
                        <h2 className={style.card_title}>{post.withShoppingTitle}</h2>
                        <div>
                            <span className={style.card_author}>작성자: {post.userNickname}</span>
                            <span className={style.card_date}>게시일 : {post.createdAt?.substring(0, 10)}</span>
                        </div>
                        <div>
                            <span className={style.card_place}>장소: {post.withShoppingSi} {post.withShoppingGu}</span>
                            <button type='button' 
                            className={`${style.card_button} ${post.withShoppingStatus === '모집중' ? style.status_button_done : style.status_button_ing}`}>
                            {post.withShoppingStatus}
                            </button>
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
                <button type='button' className={style.write_btn}
                    onClick={openWriteModal}>글 쓰기</button>
            </div>
            <Modal isOpen={modalType !== null} onRequestClose={closeModal}
                overlayClassName={modalStyle.modal_overlay} className={modalStyle.modal_container}>
            {modalType === 'write' && <ShoppingBoardModal onClose={closeModal} postId={postId}/>}
            {modalType === 'detail' && <ShoppingBoardDetailModal onClose={closeModal} postId={postId}/>}
            </Modal>
        </div>
    )
}
export default ShoppingBoard;