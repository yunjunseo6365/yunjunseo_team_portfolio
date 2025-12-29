import React, { useState, useEffect } from "react";
import styles from '../../css/another_board/NoticeBoardWrite.module.css';
import { useNavigate, useParams } from "react-router-dom";
import { getUserId } from '../common/authUtils';
import axios from 'axios';

function NoticeBoardWrite(){
    const navigate = useNavigate();
    const { postid } = useParams();
    const [isEditMode, setIsEditMode] = useState(false);
    const [loading, setLoading] = useState(false);

    const [data, setData] = useState({
        userid: '',
        title: '',
        content: ''
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
            
            const fetchNotice = async () => {
                setLoading(true);
                try {
                    const response = await axios.get(`http://localhost:18880/api/notice/${postid}`);
                    
                    if (response.data.success) {
                        const notice = response.data.data;
                        setData({
                            userid: notice.userId,
                            title: notice.noticeTitle,
                            content: notice.noticeContent
                        });
                    } else {
                        alert('공지사항을 불러오는데 실패했습니다.');
                        navigate('/notice');
                    }
                } catch (error) {
                    console.error('공지사항 조회 실패:', error);
                    alert('공지사항을 불러오는데 실패했습니다.');
                    navigate('/notice');
                } finally {
                    setLoading(false);
                }
            };
            
            fetchNotice();
        }
    }, [postid, navigate]);

    const handleChange = (e) => {
        const {name, value} = e.target;
        setData(prev => ({
            ...prev,
            [name] : value
        }))
    }

    const movePrev = () => {
        navigate('/notice');
    }

    const handelSubmit = async (e) => {
        e.preventDefault();
        
        try {
            if(isEditMode) {
                // 수정
                const response = await axios.put(`http://localhost:18880/api/notice/${postid}`, {
                    title: data.title,
                    content: data.content
                });
                
                if (response.data.success) {
                    alert('공지사항이 수정되었습니다.');
                    navigate(`/notice/detail/${postid}`);
                } else {
                    alert(response.data.message || '수정에 실패했습니다.');
                }
            } else {
                // 작성
                const response = await axios.post('http://localhost:18880/api/notice', {
                    title: data.title,
                    content: data.content
                });
                
                if (response.data.success) {
                    alert('공지사항이 작성되었습니다.');
                    navigate('/notice');
                } else {
                    alert(response.data.message || '작성에 실패했습니다.');
                }
            }
        } catch (error) {
            console.error('작업 실패:', error);
            if (error.response && error.response.status === 403) {
                alert('관리자만 공지사항을 작성/수정할 수 있습니다.');
            } else {
                alert(isEditMode ? '수정 중 오류가 발생했습니다.' : '작성 중 오류가 발생했습니다.');
            }
        }
    }

    return(
        <div className={styles.writediv}>
            <div className={styles.titlediv}>
                {isEditMode ? '공지사항 수정' : '공지사항 글쓰기'}
            </div>
            {loading ? (
                <div className={styles.loading}>공지사항을 불러오는 중...</div>
            ) : (
                <div className={styles.write_container}>
                    <form onSubmit={handelSubmit}>
                    <div>
                        <div className={styles.write_title}>제목</div>
                        <input className={styles.input_title} type="text" placeholder="제목을 입력해주세요."
                                name="title" value={data.title} onChange={handleChange}></input>
                    </div>
                    <div>
                        <div className={styles.write_title}>내용</div>
                        <textarea className={styles.input_content} placeholder="내용을 입력해주세요.(최대 700자)"
                                name="content" value={data.content} onChange={handleChange}></textarea>
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

export default NoticeBoardWrite;

