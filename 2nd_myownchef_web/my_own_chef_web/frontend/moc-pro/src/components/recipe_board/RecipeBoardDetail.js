import React, { useState, useEffect } from "react";
import styles from '../../css/recipe_board/RecipeBoardDetail.module.css';
import { IoIosArrowUp } from "react-icons/io";
import EDbtn from '../common/EDbtn';
import RSbtn from '../common/RSbtn';
import CommentBoard from '../common/CommentBoard';
import { ReportModal } from '../info/ReportModal';
import Modal from 'react-modal';
import modalStyle from '../../css/Modal.module.css';
import { useNavigate, useParams } from "react-router-dom";
import { getUserId } from '../common/authUtils';
import axios from 'axios';

function RecipeBoardDetail(){
    const { postid } = useParams();
    const navigate = useNavigate();

    const [post, setPost] = useState({
        title: '',
        mainingre: '',
        usernickname: '',
        createdate: '',
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
        const fetchDetail = async () => {
            setLoading(true);
            try {
                // 레시피 상세 조회
                const response = await axios.get(`http://localhost:18880/api/recipe/${postid}`);
                
                if (response.data.success) {
                    const { recipe, ingredients, orders, images } = response.data.data;
                    
                    setPost({
                        id: recipe.recipeId,
                        userid: recipe.userId,
                        title: recipe.recipeTitle,
                        mainingre: recipe.recipeMainIngredient,
                        usernickname: recipe.userNickname,
                        createdate: recipe.createdAt?.substring(0, 10),
                        ingredients: ingredients.map(item => item.recipeDetailIngredientContent),
                        recipes: orders.map(item => item.recipeOrderContent),
                        tip: recipe.recipeContent,
                        images: images.map(item => item.recipeImageUrl),
                        comments: []
                    });
                }

                const commentsResponse = await axios.get(`http://localhost:18880/api/recipe/${postid}/comments`);
                if (commentsResponse.data.success) {
                    const formattedComments = commentsResponse.data.comments.map(comment => ({
                        usernickname: comment.userNickname,
                        content: comment.recipeCommentContent
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
                            boardType: 'recipe',
                            postId: parseInt(postid)
                        }
                    });
                    
                    if (savedResponse.data.success) {
                        setIsSaved(savedResponse.data.data.isSaved);
                    }
                }
            } catch (error) {
                console.error('레시피 조회 실패:', error);
                alert('레시피를 불러오는데 실패했습니다.');
                navigate('/recipe');
            } finally {
                setLoading(false);
            }
        };
        
        const fetchAuthor = async () => {
            try {
                const response = await axios.get(`http://localhost:18880/api/recipe/${postid}/check-author`);
                setIsAuthor(response.data.isAuthor || false);
            } catch (error) {
                console.error('작성자 확인 실패:', error);
                setIsAuthor(false);
            }
        };
        fetchDetail();
        fetchAuthor();
    }, [postid, navigate]);

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
        if (window.confirm("정말 이 레시피를 삭제하시겠습니까?")) {
            try {
                const response = await axios.delete(`http://localhost:18880/api/recipe/${postid}`);
                
                if (response.data.success) {
                    alert('레시피가 삭제되었습니다.');
                    navigate('/recipe');
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
        // axios 영역
        console.log('수정:', postid);
        navigate(`/recipe/write/${postid}`);
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
                    boardType: 'recipe',
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
                        boardType: 'recipe',
                        postId: parseInt(postid)
                    }
                });
                
                if (response.data.success) {
                    setIsSaved(false);
                    alert('저장이 취소되었습니다.');
                }
            } else {
                const response = await axios.post('http://localhost:18880/api/saved-posts', {
                    boardType: 'recipe',
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
                <div className={styles.loading}>레시피를 불러오는 중...</div>
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
                    <div className={styles.content_text}>{post.mainingre}</div>
                </div>
                <div className={styles.content_div}>
                    <div className={styles.content_title}>상세준비재료</div>
                    <div className={styles.content_text}>
                        {post.ingredients && post.ingredients.map((item, index) => (
                            <div key={index}>{item}</div>
                        ))}
                    </div>
                </div>      
                <div className={styles.content_div}>
                    <div className={styles.content_title}>레시피</div>
                    <div className={styles.content_text}>
                        {post.recipes && post.recipes.map((item, index) => (
                            <div key={index}>{item}</div>
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
                        <CommentBoard comments={post.comments || []} postId={postid} boardType="recipe"/>
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
                    category="recipe"
                />
            </Modal>
        </div>
    );
}

export default RecipeBoardDetail;