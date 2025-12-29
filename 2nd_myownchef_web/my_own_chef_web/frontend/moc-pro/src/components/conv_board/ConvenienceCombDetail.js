import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from 'react-router-dom';
import styles from '../../css/conv_board/ConvenienceCombDetail.module.css';
import EDbtn from "../common/EDbtn";
import RSbtn from "../common/RSbtn";
import CommentBoard from "../common/CommentBoard";
import { IoIosArrowUp } from "react-icons/io";
import { ReportModal } from '../info/ReportModal';
import Modal from 'react-modal';
import modalStyle from '../../css/Modal.module.css';
import { getUserId } from '../common/authUtils';
import axios from 'axios';

function ConvenienceCombDetail(){
    const { postid } = useParams();
    const navigate = useNavigate();

    const [post, setPost] = useState({
        title: '',
        usernickname: '',
        createdate: '',
        mainingredient: '',
        ingredients: [],
        recipes: [],
        tip: '',
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
                const detailResponse = await axios.get(`http://localhost:18880/api/conv-recipe/${postid}`);
                if (detailResponse.data.success) {
                    const { recipe, products, orders, images } = detailResponse.data.data;
                    
                    setPost({
                        ...recipe,
                        mainingredient: recipe.convRecipeMainProduct,
                        ingredients: products.map(item => item.convRecipeProductContent),
                        recipes: orders.map(item => item.convRecipeOrderContent),
                        tip: recipe.convRecipeTip,
                        images: images.map(item => item.convRecipeImageUrl),
                        title: recipe.convRecipeTitle,
                        usernickname: recipe.userNickname,
                        createdate: recipe.createdAt?.substring(0, 10)
                    });
                }
                
                // 작성자 확인
                const authorResponse = await axios.get(`http://localhost:18880/api/conv-recipe/${postid}/check-author`);
                if (authorResponse.data.success) {
                    setIsAuthor(authorResponse.data.isAuthor);
                }
                
                // 댓글 조회
                const commentsResponse = await axios.get(`http://localhost:18880/api/conv-recipe/${postid}/comments`);
                if (commentsResponse.data.success) {
                    const formattedComments = commentsResponse.data.comments.map(comment => ({
                        usernickname: comment.userNickname,
                        content: comment.convRecipeCommentContent
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
                            boardType: 'conv-recipe',
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
    }, [postid],navigate);

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
        if (window.confirm("정말 이 글을 삭제하시겠습니까?")) {
            try {
                const response = await axios.delete(`http://localhost:18880/api/conv-recipe/${postid}`);
                if (response.data.success) {
                    alert('편의점 조합이 삭제되었습니다.');
                    navigate(`/conv/recipe`);
                }
            } catch (error) {
                console.error('삭제 실패:', error);
                alert('삭제에 실패했습니다.');
            }
        }
    };

    const handleEdit = () => {
        // axios 영역
        console.log('수정:', postid);
        navigate(`/conv/recipe/write/${postid}`);
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
                    boardType: 'conv-recipe',
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
                        boardType: 'conv-recipe',
                        postId: parseInt(postid)
                    }
                });
                
                if (response.data.success) {
                    setIsSaved(false);
                    alert('저장이 취소되었습니다.');
                }
            } else {
                const response = await axios.post('http://localhost:18880/api/saved-posts', {
                    boardType: 'conv-recipe',
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
                <div className={styles.loading}>편의점 조합을 불러오는 중...</div>
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
                    <div className={styles.content_title}>핵심재료</div>
                    <div className={styles.content_text}>{post.mainingredient}</div>
                </div>
                <div className={styles.content_div}>
                    <div className={styles.content_title}>상세재료</div>
                    <div className={styles.content_text}>
                        {post.ingredients && post.ingredients.map((item, index) => (
                            <div key={index}> {item}</div>
                        ))}
                    </div>
                </div>    
                <div className={styles.content_div}>
                    <div className={styles.content_title}>레시피</div>
                    <div className={styles.content_text}>
                        {post.recipes && post.recipes.map((item, index) => (
                            <div key={index}> {item}</div>
                        ))}
                    </div>
                </div>       
                <div className={styles.content_div}>
                    <div className={styles.content_title}>팁 & 후기</div>
                    <div className={styles.content_text}>{post.tip}</div>
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
                        <CommentBoard comments={post.comments || []} postId={postid} boardType="conv-recipe"/>
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
                    targetId={post.convRecipeId}
                    type="POST"
                    category="conv-recipe"
                />
            </Modal>
        </div>
    );
}

export default ConvenienceCombDetail;