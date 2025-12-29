import React, { useState, useEffect } from "react";
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import styles from '../../css/conv_board/ConvenienceReviewDetail.module.css';
import EDbtn from "../common/EDbtn";
import RSbtn from "../common/RSbtn";
import CommentBoard from "../common/CommentBoard";
import { IoIosArrowUp } from "react-icons/io";
import { ReportModal } from '../info/ReportModal';
import Modal from 'react-modal';
import modalStyle from '../../css/Modal.module.css';
import { getUserId } from '../common/authUtils';

function ConvenienceReviewDetail(){
    const { postid } = useParams();
    const navigate = useNavigate();

    const [post, setPost] = useState({
        title: '',
        usernickname: '',
        createdate: '',
        price: '',
        content: '',
        images: [],
        comments: []
    });

    const [imageindex, setImageindex] = useState(0);
    const [isReportModalOpen, setIsReportModalOpen] = useState(false);
    const [isSaved, setIsSaved] = useState(false);
    const [isAuthor, setIsAuthor] = useState(false);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try {
                // 상세 정보 조회
                const detailResponse = await axios.get(`http://localhost:18880/api/conv-review/${postid}`);
                if (detailResponse.data.success) {
                    const { convReview, images } = detailResponse.data.data;
                    setPost({
                        ...convReview,
                        images: images.map(img => img.convReviewImageUrl)
                    });
                }
                
                // 작성자 확인
                const authorResponse = await axios.get(`http://localhost:18880/api/conv-review/${postid}/check-author`);
                if (authorResponse.data.success) {
                    setIsAuthor(authorResponse.data.isAuthor);
                }
                
                // 댓글 조회
                const commentsResponse = await axios.get(`http://localhost:18880/api/conv-review/${postid}/comments`);
                if (commentsResponse.data.success) {
                    const formattedComments = commentsResponse.data.comments.map(comment => ({
                        usernickname: comment.userNickname,
                        content: comment.convReviewCommentContent
                    }));

                    setPost(prev => ({
                        ...prev,
                        comments: formattedComments
                    }));
                }
                
                // 저장 여부 확인
                const userId = await getUserId();
                if (userId) {
                    const savedResponse = await axios.get('http://localhost:18880/api/saved-posts/check', {
                        params: {
                            boardType: 'conv-review',
                            postId: parseInt(postid)
                        }
                    });
                    
                    if (savedResponse.data.success) {
                        setIsSaved(savedResponse.data.data.isSaved);
                    }
                }
            } catch (error) {
                console.error('데이터 조회 실패:', error);
            } finally {
                setLoading(false);
            }
        };
        
        fetchData();
    }, [postid], navigate);

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
        if (window.confirm("정말 이 리뷰를 삭제하시겠습니까?")) {
            try {
                const response = await axios.delete(`http://localhost:18880/api/conv-review/${postid}`);
                if (response.data.success) {
                    alert('리뷰가 삭제되었습니다.');
                    navigate('/conv/review');
                } else {
                    alert(response.data.message || '삭제에 실패했습니다.');
                }
            } catch (error) {
                console.error('삭제 실패:', error);
                alert('삭제 중 오류가 발생했습니다.');
            }
        }
    };

    const handleEdit = () => {
        navigate(`/conv/review/write/${postid}`);
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
                    boardType: 'conv-review',
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
                        boardType: 'conv-review',
                        postId: parseInt(postid)
                    }
                });
                
                if (response.data.success) {
                    setIsSaved(false);
                    alert('저장이 취소되었습니다.');
                }
            } else {
                const response = await axios.post('http://localhost:18880/api/saved-posts', {
                    boardType: 'conv-review',
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

    return(
        <div className={styles.main_container}>
            {loading ? (
                <div className={styles.loading}>편의점 리뷰를 불러오는 중...</div>
            ) : (
                <>
                    <div className={styles.titlediv} >
                        <div className={styles.title}>{post.convReviewTitle}</div>
                        <div className={styles.subtitle_div}>
                            <div>작성자: {post.userNickname}</div>
                            <div>게시일: {post.createdAt?.substring(0, 10) || ''}</div>
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
                    <div className={styles.content_title}>가격</div>
                    <div className={styles.content_text}>{post.convReviewPrice ? `${post.convReviewPrice}원` : '가격 정보 없음'}</div>
                </div>
                <div className={styles.content_div}>
                    <div className={styles.content_title}>후기</div>
                    <div className={styles.content_text}>{post.convReviewContent}</div>
                </div>                                                                                                                                                                                                                             
            </div>
            <div className={styles.btn_div}>
                {isAuthor ? (
                    <EDbtn onDelete={handleDelete} onEdit={handleEdit}/>
                ) : (
                    <RSbtn onReport={handleReport} onSave={handleSave} isSaved={isSaved}/>
                )}
            </div>
                    <div>
                        <CommentBoard comments={post.comments || []} postId={postid} boardType="conv-review"/>
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
                    targetId={post.convReviewId}
                    type="POST"
                    category="conv-review"
                />
            </Modal>
        </div>
    );
}

export default ConvenienceReviewDetail;