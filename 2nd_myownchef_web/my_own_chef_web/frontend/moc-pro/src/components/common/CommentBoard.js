import React, { useState, useEffect } from "react";
import styles from '../../css/common/CommentBoard.module.css';
import { MdPlayArrow } from "react-icons/md";
import { FaFlag } from "react-icons/fa";
import { ReportModal } from '../info/ReportModal';
import Modal from 'react-modal';
import modalStyle from '../../css/Modal.module.css';
import { getUserId, getUserNickname } from './authUtils';
import axios from 'axios';

function CommentBoard({comments, postId, boardType}){
    const [commentList, setCommentList] = useState(comments);
    const [commentData, setCommentData] = useState({
        usernickname: '',
        content: ''
    });
    const [isReportModalOpen, setIsReportModalOpen] = useState(false);
    const [selectedCommentId, setSelectedCommentId] = useState(null);

    // props로 받은 comments가 변경되면 state 업데이트
    useEffect(() => {
        setCommentList(comments);
    }, [comments]);

    useEffect(() => {
        const fetchUserId = async () => {
            const userId = await getUserId();
            const userNickname = await getUserNickname();
            if (userId) {
                setCommentData(prev => ({
                    ...prev,
                    userid: userId
                }));
                setCommentData(prev => ({
                    ...prev,
                    usernickname: userNickname
                }));
            }
        };
        fetchUserId();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        const userId = await getUserId();
        if (!userId) {
            alert('로그인이 필요합니다.');
            return;
        }
        
        if(!commentData.content.trim()){
            alert('댓글 내용을 입력해주세요.');
            return;
        }
        
        try {
            const response = await axios.post(`http://localhost:18880/api/${boardType}/${postId}/comments`, {
                content: commentData.content
            });
            
            if (response.data.success) {
                alert('댓글이 작성되었습니다.');
                
                const newComment = {
                    usernickname: commentData.usernickname,
                    content: commentData.content
                };

                setCommentList(prev => ([
                    ...prev, newComment
                ]));
                // 댓글 작성 후 입력창 초기화
                setCommentData(prev => ({
                    ...prev,
                    content: ''
                }));
            } else {
                alert(response.data.message || '댓글 작성에 실패했습니다.');
            }
        } catch (error) {
            console.error('댓글 작성 실패:', error);
            alert('댓글 작성 중 오류가 발생했습니다.');
        }
    };

    const handleReportComment = async (commentId) => {
        const userId = await getUserId();
        if (!userId) {
            alert('로그인이 필요합니다.');
            return;
        }
        
        try {
            const checkResponse = await axios.get('http://localhost:18880/api/report/check', {
                params: {
                    reportType: 'COMMENT',
                    boardType: boardType,
                    targetId: commentId
                }
            });
            
            if (checkResponse.data.success && checkResponse.data.data.isReported) {
                alert('이미 신고한 댓글입니다.');
                return;
            }
        } catch (error) {
            console.error('신고 확인 실패:', error);
        }
        
        setSelectedCommentId(commentId);
        setIsReportModalOpen(true);
    };

    const closeReportModal = () => {
        setIsReportModalOpen(false);
        setSelectedCommentId(null);
    };

    return(
        <div className={styles.comment_container}>
            <div className={styles.comment_input_container}>
                <form onSubmit={handleSubmit}>
                    <input 
                        className={styles.comment_input_box} 
                        type="text" 
                        placeholder="댓글을 입력해주세요."
                        value={commentData.content}
                        onChange={(e) => setCommentData({...commentData, content: e.target.value})}
                        maxLength={200}
                    />
                    <button className={styles.comment_submit_btn} type="submit">추가</button>
                </form>
            </div>
            <div className={styles.comment_list_container}>
                <div className={styles.comment_list_count}>댓글 {commentList.length}개</div>
                {commentList.map((comment) => (
                    <div key={comment.id} className={styles.comment_list_board}>
                        <div className={styles.comment_user_div}>
                            <MdPlayArrow className={styles.icon}/>
                            {comment.usernickname}
                        </div>
                        <div className={styles.comment_content_div}>
                            {comment.content}
                        </div>
                        <div className={styles.comment_report_btn} onClick={() => handleReportComment(comment.id)}>
                            <div className={styles.report_div}><FaFlag className={styles.report_icon}/>신고하기</div>
                        </div>
                    </div>
                ))}
            </div>

            {/* 댓글 신고 모달 */}
            <Modal 
                isOpen={isReportModalOpen} 
                onRequestClose={closeReportModal}
                overlayClassName={modalStyle.modal_overlay} 
                className={modalStyle.modal_container}>
                <ReportModal 
                    onClose={closeReportModal}
                    targetId={parseInt(postId)}
                    type="COMMENT"
                    category={boardType}
                />
            </Modal>
        </div>
    );
}

export default CommentBoard;