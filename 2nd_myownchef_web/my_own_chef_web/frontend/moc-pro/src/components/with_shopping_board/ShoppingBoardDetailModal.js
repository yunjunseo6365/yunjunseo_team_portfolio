import style from '../../css/with_shopping_board/WithShoppingDetailModal.module.css';
import React, { useEffect, useState } from 'react';
import {FaFlag} from "react-icons/fa6";
import Modal from 'react-modal';
import modalStyle from '../../css/Modal.module.css';
import { ShoppingBoardModal } from './ShoppingBoardModal';
import { ReportModal } from '../info/ReportModal';
import { ChatModal } from '../info/ChatModal';
import { checkAuthor, getUserId } from '../common/authUtils';
import axios from 'axios';


// 매우중요!!!! 사용전 필독★ yarn add react-modal로 라이브러리 설치 후
// 모달 팝업창을 띄울 js 파일에 추가해야할 코드가 있습니다.
// 아래 예시처럼 글 쓰기 와 같은 팝업을 띄울 수 있는 버튼이 있는 부모 js 파일에
// (예시: ShoppingBoard.js 에서 ShoppingBoardModal.js 를 띄우는것처럼)

// 부모 js 파일에 임포트 먼저 (여기엔 import Modal 안씁니다)
// import {ShoppingBoardModal} from './ShoppingBoardModal';
// import Modal from 'react-modal';
// import modalStyle from '../../css/Modal.module.css';

// 그뒤에는

// const [isModalOpen, setIsModalOpen] = useState(false);

// const openModal = () => setIsModalOpen(true);
// const closeModal = () => setIsModalOpen(false);

// 와 같은 함수를 사용해야하고,

// 마지막으로 글 쓰기 버튼을 예시로 든다면 그아래에 <Modal>이라는 태그를 사용해야 합니다

// </div>
//     <button type='button' className={style.write_btn}
//         onClick={openModal}>글 쓰기</button>
// </div>
// <Modal isOpen={isModalOpen} onRequestClose={closeModal}
//     overlayClassName={modalStyle.modal_overlay} className={modalStyle.modal_container}>
// <ShoppingBoardModal onClose={closeModal}/>
// </Modal>


export const ShoppingBoardDetailModal = ({onClose, postId}) => {

    const [data, setData] = useState({});
    const [isEditModalOpen, setIsEditModalOpen] = useState(false);
    const [isReportModalOpen, setIsReportModalOpen] = useState(false);
    const [isChatModalOpen, setIsChatModalOpen] = useState(false);
    const [chatRoomId, setChatRoomId] = useState(null);
    const [deletedRoomIds, setDeletedRoomIds] = useState([]);
    const [isAuthor, setIsAuthor] = useState(false);
    const [existingRoomId, setExistingRoomId] = useState(null);
    const [hasExistingRoom, setHasExistingRoom] = useState(false);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchAuthor = async (postId) => {
            console.log(postId);
            const author = await checkAuthor(postId, 'withshopping');
            setIsAuthor(author);
        };
        fetchAuthor(postId);
    }, [postId]);

    useEffect(() => {
        const fetchDetail = async () => {
            setLoading(true);
            try {
                const response = await axios.get(`http://localhost:18880/api/withshopping/${postId}`);
                
                if (response.data.success) {
                    const withShopping = response.data.data;
                    
                    setData({
                        id: withShopping.withShoppingId,
                        userid: withShopping.userId,
                        title: withShopping.withShoppingTitle,
                        usernickname: withShopping.userNickname,
                        createdate: withShopping.createdAt?.substring(0, 10),
                        si: withShopping.withShoppingSi, 
                        gu: withShopping.withShoppingGu, 
                        status: withShopping.withShoppingStatus, 
                        content: withShopping.withShoppingContent
                    });
                } else {
                    console.error('상세 조회 실패:', response.data.message);
                    alert('게시글을 불러올 수 없습니다.');
                    onClose();
                }
            } catch (error) {
                console.error('상세 조회 실패:', error);
                alert('게시글을 불러오는 중 오류가 발생했습니다.');
                onClose();
            } finally {
                setLoading(false);
            }
        };
        
        if (postId) {
            fetchDetail();
        }
    }, [postId, onClose]);

    // 채팅방 존재 여부 확인
    useEffect(() => {
        const checkChatRoom = async () => {
            const userId = await getUserId();
            if (!userId || !data.id) return;
            
            try {
                const response = await axios.get('http://localhost:18880/api/chat/rooms/check', {
                    params: {
                        postId: data.id,
                        postType: 'shopping'
                    }
                });
                
                if (response.data.success && response.data.data.exists) {
                    setHasExistingRoom(true);
                    setExistingRoomId(response.data.data.roomId);
                }
            } catch (error) {
                console.error('채팅방 확인 실패:', error);
            }
        };
        
        if (data.id) {
            checkChatRoom();
        }
    }, [data.id]);

    const handleDelete = async () => {
        if(window.confirm('정말 삭제하시겠습니까?')){
            try {
                const response = await axios.delete(`http://localhost:18880/api/withshopping/${data.id}`, {
                    withCredentials: true
                });
                
                if (response.data.success) {
                    alert('게시글이 삭제되었습니다.');
                    onClose();
                    window.location.reload(); // 목록 새로고침
                } else {
                    alert('삭제 실패: ' + response.data.message);
                }
            } catch (error) {
                console.error('삭제 실패:', error);
                alert('삭제 중 오류가 발생했습니다.');
            }
        }
    };

    const handleEdit = () => {
        setIsEditModalOpen(true);
    };

    const closeEditModal = () => {
        setIsEditModalOpen(false);
        // 수정 완료 후 상세 데이터 다시 로드
        if (postId) {
            window.location.reload(); // 전체 새로고침
        }
    };

    const handleApply = async () => {
        const userId = await getUserId();
        if (!userId) {
            alert('로그인이 필요합니다.');
            return;
        }
        
        // 이미 채팅방이 있으면 바로 열기
        if (hasExistingRoom && existingRoomId) {
            setChatRoomId(existingRoomId);
            setIsChatModalOpen(true);
            return;
        }
        
        // 없으면 새로 생성
        try {
            const response = await axios.post('http://localhost:18880/api/chat/rooms', {
                postId: data.id,
                postType: 'shopping',
                ownerId: data.userid
            });
            
            if (response.data.success) {
                const roomId = response.data.data.chatRoomId;
                setChatRoomId(roomId);
                setExistingRoomId(roomId);
                setHasExistingRoom(true);
                setIsChatModalOpen(true);
            } else {
                alert(response.data.message || '채팅방 생성 실패');
            }
        } catch (error) {
            console.error('채팅방 생성 실패:', error);
            alert('채팅방 생성 중 오류가 발생했습니다.');
        }
    };

    const closeChatModal = () => {
        setIsChatModalOpen(false);
    };

    const handleDeleteRoom = (roomId) => {
        setDeletedRoomIds(prev => [...prev, roomId]);
    };

    const handleReport = async () => {
        const userId = await getUserId();
        if (!userId) {
            alert('로그인이 필요합니다.');
            return;
        }
        
        try {
            const checkResponse = await axios.get('http://localhost:18880/api/report/check', {
                params: {
                    reportType: 'POST',
                    boardType: 'withshopping',
                    targetId: parseInt(postId)
                }
            });
            
            if (checkResponse.data.success && checkResponse.data.data.isReported) {
                alert('이미 신고한 게시글입니다.');
                return;
            }
        } catch (error) {
            console.error('신고 확인 실패:', error);
        }
        
        setIsReportModalOpen(true);
    };

    const closeReportModal = () => {
        setIsReportModalOpen(false);
    };

    return (
        <div className={style.modal_container}>
            {loading ? (
                <div className={style.loading}>게시글을 불러오는 중...</div>
            ) : (
                <>
                    <div className={style.modal_header}>
                        {/* 제목과 작성자 정보를 묶어줄 div 추가 */}
                        <h2 className={style.post_title}>{data.title}</h2>
                        <div className={style.header_right_group}>
                            <div className={style.post_meta}>
                                <span>작성자: {data.usernickname}</span>
                                <span>게시일: {data.createdate}</span>
                            </div>
                            <button className={style.close_btn} onClick={onClose}>&times;</button>
                        </div>
                    </div>
                    <div className={style.main_content}>
                        <div className={style.content_header}>
                            <span className={style.post_place}>장소: {data.si} {data.gu}</span>
                            <span className={`${data.status === '모집중' ? style.post_status_done : style.post_status_ing}`}>{data.status}</span>
                        </div>
                        <div className={style.content_text}>
                            {data.content}
                        </div>
                        {!isAuthor && (
                            <button type="button" className={style.report_btn} onClick={handleReport}>
                                <span>신고하기</span>
                                <FaFlag /> 
                            </button>
                        )}
                    </div>
                    <div className={style.button_container}>
                        <button type='button' className={`${style.modal_button} ${style.close_button_bottom}`} onClick={onClose}>닫기</button>
                        {isAuthor && (
                            <>
                                <button type='button' className={`${style.modal_button} ${style.delete_button}`} onClick={handleDelete}>삭제하기</button>
                                <button type='button' className={`${style.modal_button} ${style.edit_button}`} onClick={handleEdit}>수정하기</button>
                            </>
                        )}
                        {!isAuthor && data.status !== '모집완료' && (
                            <button type='button' className={`${style.modal_button} ${style.apply_button}`} onClick={handleApply}>
                                {hasExistingRoom ? '채팅하기' : '신청하기'}
                            </button>
                        )}
                    </div>
                </>
            )}

            {/* 신고 모달 */}
            <Modal 
                isOpen={isReportModalOpen} 
                onRequestClose={closeReportModal}
                overlayClassName={modalStyle.modal_overlay} 
                className={modalStyle.modal_container}>
                <ReportModal 
                    onClose={closeReportModal}
                    targetId={data.id}
                    type="POST"
                    category="withshopping"
                />
            </Modal>

            {/* 수정 모달 */}
            <Modal 
                isOpen={isEditModalOpen} 
                onRequestClose={closeEditModal}
                overlayClassName={modalStyle.modal_overlay} 
                className={modalStyle.modal_container}>
                <ShoppingBoardModal 
                    onClose={closeEditModal}
                    isEditMode={true}
                    postId={data.id}
                    editData={data}
                />
            </Modal>

            {/* 채팅 모달 */}
            <Modal 
                isOpen={isChatModalOpen} 
                onRequestClose={closeChatModal}
                overlayClassName={modalStyle.modal_overlay} 
                className={modalStyle.modal_container}>
                <ChatModal 
                    onClose={closeChatModal}
                    onDelete={handleDeleteRoom}
                    partnerNickname={data.usernickname}
                    postId={data.id}
                    postType="shopping"
                    roomId={chatRoomId}
                />
            </Modal>
        </div>
    )
}

