import React, { useEffect, useState } from "react";
import styles from '../../css/another_board/FreeBoardWrite.module.css';
import { useNavigate, useParams } from "react-router-dom";
import { getUserId } from '../common/authUtils';
import axios from 'axios';

function FreeBoardWrite(){
    const navigate = useNavigate();
    const { postid } = useParams();
    const [isEditMode, setIsEditMode] = useState(false);
    const [loading, setLoading] = useState(false);

    const movePrev = () => {
        navigate('/freeboard');
    }

    const [data, setData] = useState({
        title: '',
        content: '',
        userid: ''
    });

    useEffect(() => {
        const fetchUserId = async () => {
            const userId = await getUserId();
            if (userId) {
                setData(prev => ({
                    ...prev,  
                    userid: userId
                }));
            }
        };
        fetchUserId();
    }, []);

    // 수정 모드: 기존 글 데이터 로드
    useEffect(() => {
        if(postid) {
            setIsEditMode(true);
            
            const fetchPost = async () => {
                setLoading(true);
                try {
                    const response = await axios.get(`http://localhost:18880/api/freeboard/${postid}`);
                    
                    if (response.data.success) {
                        const post = response.data.data;
                        setData({
                            title: post.freeboardTitle,
                            content: post.freeboardContent,
                            userid: post.userId
                        });
                    } else {
                        alert('게시글을 불러오는데 실패했습니다.');
                        navigate('/freeboard');
                    }
                } catch (error) {
                    console.error('게시글 조회 실패:', error);
                    alert('게시글을 불러오는데 실패했습니다.');
                    navigate('/freeboard');
                } finally {
                    setLoading(false);
                }
            };
            
            fetchPost();
        }
    }, [postid, navigate]);

    const handleOnSubmit = async (e) => {
        e.preventDefault();
        
        try {
            if(isEditMode) {
                // 수정
                const response = await axios.put(`http://localhost:18880/api/freeboard/${postid}`, {
                    title: data.title,
                    content: data.content
                });
                
                if (response.data.success) {
                    alert('게시글이 수정되었습니다.');
                    navigate(`/freeboard/detail/${postid}`);
                } else {
                    alert(response.data.message || '수정에 실패했습니다.');
                }
            } else {
                // 작성
                const response = await axios.post('http://localhost:18880/api/freeboard', {
                    title: data.title,
                    content: data.content
                });
                
                if (response.data.success) {
                    alert('게시글이 작성되었습니다.');
                    navigate('/freeboard');
                } else {
                    alert(response.data.message || '작성에 실패했습니다.');
                }
            }
        } catch (error) {
            console.error('작업 실패:', error);
            alert(isEditMode ? '수정 중 오류가 발생했습니다.' : '작성 중 오류가 발생했습니다.');
        }
    }

    return(
        <div className={styles.writediv}>
            <div className={styles.titlediv}>
                {isEditMode ? '자유게시판 글 수정' : '자유게시판 글쓰기'}
            </div>
            {loading ? (
                <div className={styles.loading}>게시글을 불러오는 중...</div>
            ) : (
                <div className={styles.write_container}>
                    <form onSubmit={handleOnSubmit}>
                    <div>
                        <div className={styles.write_title}>제목</div>
                        <input className={styles.input_title} type="text" 
                                placeholder="제목을 입력해주세요."
                                value={data.title}
                                name="title"
                                onChange={(e) => setData({...data, title: e.target.value})}
                                required/>
                    </div>
                    <div>
                        <div className={styles.write_title}>내용</div>
                        <textarea className={styles.input_content} placeholder="내용을 입력해주세요.(최대 700자)"
                                    value={data.content}
                                    name="content"
                                    onChange={(e) => setData({...data, content: e.target.value})}
                                    maxLength={700}
                                    required/>
                    </div>
                    <div className={styles.formbtn}>
                        <button type="button" onClick={movePrev}>취소</button>
                        <button type="submit">작성완료</button>
                    </div>
                </form>
                </div>
            )}
        </div>
    )
}

export default FreeBoardWrite;