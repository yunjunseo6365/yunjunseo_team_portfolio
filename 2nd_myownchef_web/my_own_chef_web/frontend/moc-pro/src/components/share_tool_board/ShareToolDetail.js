import React, { useState, useEffect } from "react";
import styles from '../../css/share_tool_board/ShareToolDetail.module.css';
import RSbtn from "../common/RSbtn";
import { IoIosArrowUp } from "react-icons/io";
import { useNavigate, useParams } from "react-router-dom";
import EDbtn from "../common/EDbtn";
import { ReportModal } from '../info/ReportModal';
import { ChatModal } from '../info/ChatModal';
import Modal from 'react-modal';
import modalStyle from '../../css/Modal.module.css';
import { checkAuthor, getUserId } from '../common/authUtils';
import axios from 'axios';

function ShareToolDetail(){
    const { postid } = useParams();
    const navigate = useNavigate();

    const [post, setPost] = useState({
        title: '',
        product: '',
        usernickname: '',
        createdate: '',
        si: '',
        gu: '',
        status: '',
        content: '',
        images: []
    });
    const [imageindex, setImageindex] = useState(0);
    const [isReportModalOpen, setIsReportModalOpen] = useState(false);
    const [isSaved, setIsSaved] = useState(false);
    const [isChatModalOpen, setIsChatModalOpen] = useState(false);
    const [chatRoomId, setChatRoomId] = useState(null);
    const [deletedRoomIds, setDeletedRoomIds] = useState([]);
    const [isAuthor, setIsAuthor] = useState(false);
    const [existingRoomId, setExistingRoomId] = useState(null);
    const [hasExistingRoom, setHasExistingRoom] = useState(false);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchAuthor = async () => {
            const author = await checkAuthor(postid, 'sharetool');
            setIsAuthor(author);
        };
        fetchAuthor();
    }, [postid]);

    useEffect(() => {
        const fetchDetail = async () => {
            setLoading(true);
            try {
                const response = await axios.get(`http://localhost:18880/api/sharetool/${postid}`);
                
                if (response.data.success) {
                    const { shareTool, images } = response.data.data;
                    
                    setPost({
                        id: shareTool.shareToolId,
                        userid: shareTool.userId,
                        title: shareTool.shareToolTitle,
                        product: shareTool.shareToolProduct,
                        usernickname: shareTool.userNickname,
                        createdate: shareTool.createdAt?.substring(0, 10),
                        si: shareTool.shareToolProvince,
                        gu: shareTool.shareToolCity,
                        status: shareTool.shareToolStatus,
                        content: shareTool.shareToolContent,
                        images: images.map(img => img.shareToolImageUrl)
                    });
                } else {
                    console.error('상세 조회 실패:', response.data.message);
                    alert('게시글을 불러올 수 없습니다.');
                    navigate('/sharetool');
                }
                
                // 저장 여부 확인
                const userId = await getUserId();
                if (userId) {
                    const savedResponse = await axios.get('http://localhost:18880/api/saved-posts/check', {
                        params: {
                            boardType: 'sharetool',
                            postId: parseInt(postid)
                        }
                    });
                    
                    if (savedResponse.data.success) {
                        setIsSaved(savedResponse.data.data.isSaved);
                    }
                }
            } catch (error) {
                console.error('상세 조회 실패:', error);
                alert('게시글을 불러오는 중 오류가 발생했습니다.');
                navigate('/sharetool');
            } finally {
                setLoading(false);
            }
        };
        
        fetchDetail();
    }, [postid, navigate]);

    // 채팅방 존재 여부 확인
    useEffect(() => {
        const checkChatRoom = async () => {
            const userId = await getUserId();
            if (!userId || !post.id) return;
            
            try {
                const response = await axios.get('http://localhost:18880/api/chat/rooms/check', {
                    params: {
                        postId: post.id,
                        postType: 'sharetool'
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
        
        if (post.id) {
            checkChatRoom();
        }
    }, [post.id]);

    const nextChangeImg = () => {
        if (post.images.length > 0) {
            setImageindex(prev => (prev + 1) % post.images.length);
        }
    };

    const lastChangeImg = () => {
        if (post.images.length > 0) {
            setImageindex(prev => (prev - 1 + post.images.length) % post.images.length);
        }
    };

    const handleDelete = async () => {
        if (window.confirm("정말 이 게시물을 삭제하시겠습니까?")) {
            try {
                const response = await axios.delete(`http://localhost:18880/api/sharetool/${postid}`, {
                    withCredentials: true
                });
                
                if (response.data.success) {
                    alert('게시글이 삭제되었습니다.');
                    navigate(`/sharetool`);
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
        navigate(`/sharetool/write/${postid}`);
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
                    boardType: 'sharetool',
                    targetId: parseInt(postid)
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

    const handleSave = async () => {
        const userId = await getUserId();
        if (!userId) {
            alert('로그인이 필요합니다.');
            return;
        }
        
        try {
            if (isSaved) {
                const response = await axios.delete('http://localhost:18880/api/saved-posts', {
                    data: {
                        boardType: 'sharetool',
                        postId: parseInt(postid)
                    }
                });
                
                if (response.data.success) {
                    setIsSaved(false);
                    alert('저장이 취소되었습니다.');
                }
            } else {
                const response = await axios.post('http://localhost:18880/api/saved-posts', {
                    boardType: 'sharetool',
                    postId: parseInt(postid)
                });
                
                if (response.data.success) {
                    setIsSaved(true);
                    alert('저장되었습니다.');
                }
            }
        } catch (error) {
            console.error('저장 처리 실패:', error);
            alert(error.response?.data?.message || '저장 처리 중 오류가 발생했습니다.');
        }
    };

    const handleRequest = async () => {
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
                postId: parseInt(postid),
                postType: 'sharetool',
                ownerId: post.userid
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

    

    return(
        <div className={styles.main_container}>
            {loading ? (
                <div className={styles.loading}>요리도구나눔 게시글을 불러오는 중...</div>
            ) : (
                <>
                    <div className={styles.titlediv} >
                        <div className={styles.title}>{post.title}</div>
                        <div className={styles.subtitle_div}>
                            <div>작성자: {post.usernickname}</div>
                            <div>게시일: {post.createdate}</div>
                        </div>
                    </div>
                    <div className={styles.content_container}>
                        {post.images && post.images.length > 0 && (
                            <div className={styles.img_container}>
                                <IoIosArrowUp className={styles.icon1} onClick={lastChangeImg}/>
                                <div className={styles.image_div}>
                                    <img src={post.images[imageindex]} alt={`이미지 ${imageindex + 1}`}/>
                                </div>
                                <IoIosArrowUp className={styles.icon2} onClick={nextChangeImg}/>
                            </div>
                        )}
                        <div className={styles.content_div}>
                            <div className={styles.content_title}>제품명</div>
                            <div className={styles.content_text}>{post.product}</div>
                        </div>
                        <div className={styles.content_div}>
                            <div className={styles.content_title}>위치</div>
                            <div className={styles.content_text}>{post.si} {post.gu}</div>
                        </div>                   
                        <div className={styles.content_div}>
                            <div className={styles.content_title}>상세내용</div>
                            <div className={styles.content_text}>{post.content}</div>
                        </div>
                        <div className={styles.content_div}>
                            <div className={styles.content_title}>상태</div>
                            <div className={styles.content_text}>{post.status}</div>
                        </div>                                                                                                                                                                                                                                  
                    </div>
                    <div className={styles.btn_div}>
                        {!isAuthor && (
                            <RSbtn onReport={handleReport} onSave={handleSave} isSaved={isSaved}/>
                        )}
                    </div>
                    <div className={styles.EDbtn_div}>
                        {isAuthor && (
                            <EDbtn onDelete={handleDelete} onEdit={handleEdit}/>
                        )}
                        {!isAuthor && post.status !== '나누완료' && (
                            <button type="button" className={styles.requestbtn} onClick={handleRequest}>
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
                    targetId={post.id}
                    type="POST"
                    category="sharetool"
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
                    partnerNickname={post.usernickname}
                    postId={postid}
                    postType="sharetool"
                    roomId={chatRoomId}
                />
            </Modal>
        </div>
    );
}

export default ShareToolDetail;