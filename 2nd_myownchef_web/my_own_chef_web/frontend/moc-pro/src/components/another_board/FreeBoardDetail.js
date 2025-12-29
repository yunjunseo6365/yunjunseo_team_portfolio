import React, { useEffect, useState } from "react";
import styles from '../../css/another_board/FreeBoardDetail.module.css';
import CommentBoard from "../common/CommentBoard";
import RSbtn from "../common/RSbtn";
import EDbtn from "../common/EDbtn";
import { ReportModal } from "../info/ReportModal";
import Modal from 'react-modal';
import { useParams, useNavigate } from "react-router-dom";
import { getUserId } from '../common/authUtils';
import axios from 'axios';


function FreeBoardDetail(){
    const { postid } = useParams();
    const navigate = useNavigate();
    const [data, setData] = useState({});
    const [comments, setComments] = useState([]);
    const [isReportModalOpen, setIsReportModalOpen] = useState(false);
    const [isSaved, setIsSaved] = useState(false);
    const [isAuthor, setIsAuthor] = useState(false);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchAuthor = async () => {
            try {
                const response = await axios.get(`http://localhost:18880/api/freeboard/${postid}/check-author`);
                if (response.data.success) {
                    setIsAuthor(response.data.isAuthor);
                }
            } catch (error) {
                console.error('작성자 확인 실패:', error);
                setIsAuthor(false);
            }
        };
        fetchAuthor();
    }, [postid]);

    const handleDelete = async () => {
        if (window.confirm("정말 이 글을 삭제하시겠습니까?")) {
            try {
                const response = await axios.delete(`http://localhost:18880/api/freeboard/${postid}`);
                
                if (response.data.success) {
                    alert('게시글이 삭제되었습니다.');
                    navigate('/freeboard');
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
        navigate(`/freeboard/write/${postid}`);
    };

    const handleReport = async () => {
        const userId = await getUserId();
        if (!userId) {
            alert('로그인이 필요합니다.');
            return;
        }
        
        try {
            // 중복 신고 확인
            const checkResponse = await axios.get('http://localhost:18880/api/report/check', {
                params: {
                    reportType: 'POST',
                    boardType: 'freeboard',
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

    const handleSave = async () => {
        const userId = await getUserId();
        if (!userId) {
            alert('로그인이 필요합니다.');
            return;
        }
        
        try {
            if (isSaved) {
                // 저장 취소
                const response = await axios.delete('http://localhost:18880/api/saved-posts', {
                    data: {
                        boardType: 'freeboard',
                        postId: parseInt(postid)
                    }
                });
                
                if (response.data.success) {
                    setIsSaved(false);
                    alert('저장이 취소되었습니다.');
                }
            } else {
                // 저장
                const response = await axios.post('http://localhost:18880/api/saved-posts', {
                    boardType: 'freeboard',
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

    const closeReportModal = () => {
        setIsReportModalOpen(false);
    };

    useEffect(() => {
        const fetchDetail = async () => {
            setLoading(true);
            try {
                // 게시글 상세 조회
                const postResponse = await axios.get(`http://localhost:18880/api/freeboard/${postid}`);
                
                if (postResponse.data.success) {
                    const post = postResponse.data.data;
                    setData({
                        id: post.freeboardId,
                        userid: post.userId,
                        title: post.freeboardTitle,
                        createdate: post.createdAt?.substring(0, 10),
                        usernickname: post.userNickname,
                        content: post.freeboardContent
                    });
                }
                
                // 댓글 목록 조회
                const commentsResponse = await axios.get(`http://localhost:18880/api/freeboard/${postid}/comments`);
                
                if (commentsResponse.data.success) {
                    setComments(commentsResponse.data.data.map(comment => ({
                        id: comment.freeboardCommentId,
                        usernickname: comment.userNickname,
                        content: comment.freeboardCommentContent,
                        userid: comment.userId,
                        createdate: comment.createdAt?.substring(0, 10)
                    })));
                }
                
                // 저장 여부 확인
                const userId = await getUserId();
                if (userId) {
                    const savedResponse = await axios.get('http://localhost:18880/api/saved-posts/check', {
                        params: {
                            boardType: 'freeboard',
                            postId: parseInt(postid)
                        }
                    });
                    
                    if (savedResponse.data.success) {
                        setIsSaved(savedResponse.data.data.isSaved);
                    }
                }
            } catch (error) {
                console.error('데이터 조회 실패:', error);
                alert('게시글을 불러오는데 실패했습니다.');
                navigate('/freeboard');
            } finally {
                setLoading(false);
            }
        };
        
        fetchDetail();
    }, [postid, navigate]);

    return(
        <div className={styles.main_container}>
            {loading ? (
                <div className={styles.loading}>게시글을 불러오는 중...</div>
            ) : (
                <>
                    <div className={styles.title_container}>
                        <div className={styles.title}>{data.title}</div>
                        <div className={styles.title_content}>
                            <div>작성자: {data.usernickname}</div>
                            <div>게시일: {data.createdate}</div>
                        </div>
                    </div>
                    <div className={styles.body_container}>
                        <div className={styles.body_content}>{data.content}</div>
                        <div className={styles.btn_div}>
                            {isAuthor ? (
                                <EDbtn onDelete={handleDelete} onEdit={handleEdit}/>
                            ) : (
                                <RSbtn onReport={handleReport} onSave={handleSave} isSaved={isSaved}/>
                            )}
                        </div>
                    </div>
                    <CommentBoard comments={comments || []} postId={postid} boardType="freeboard"/>
                </>
            )}
            <Modal 
                isOpen={isReportModalOpen} 
                onRequestClose={closeReportModal}
                className={styles.modal_content}
                overlayClassName={styles.modal_overlay}
            >
                <ReportModal 
                    onClose={closeReportModal} 
                    targetId={data.id}
                    type="POST"
                    category="freeboard"
                />
            </Modal>
        </div>
    )
}

export default FreeBoardDetail;