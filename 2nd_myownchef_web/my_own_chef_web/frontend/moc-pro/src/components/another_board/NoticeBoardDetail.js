import React, { useEffect, useState } from "react";
import styles from '../../css/another_board/NoticeBoardDetail.module.css';
import EDbtn from "../common/EDbtn";
import { useParams, useNavigate } from "react-router-dom";
import { checkAdmin } from '../common/authUtils';
import axios from 'axios';

function NoticeBoardDetail(){
    const { postid } = useParams();
    const navigate = useNavigate();
    const [data, setData] = useState({});
    const [isAdmin, setIsAdmin] = useState(false);
    const [loading, setLoading] = useState(true);

    const handleDelete = async () => {
        if (window.confirm("정말 이 공지사항을 삭제하시겠습니까?")) {
            try {
                const response = await axios.delete(`http://localhost:18880/api/notice/${postid}`);
                
                if (response.data.success) {
                    alert('공지사항이 삭제되었습니다.');
                    navigate('/notice');
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
        navigate(`/notice/write/${postid}`);
    };

    useEffect(() => {
        // 관리자 권한 확인
        const fetchAdmin = async () => {
            const admin = await checkAdmin();
            setIsAdmin(admin);
        };
        fetchAdmin();
    }, []);

    useEffect(() => {
        const fetchDetail = async () => {
            setLoading(true);
            try {
                const response = await axios.get(`http://localhost:18880/api/notice/${postid}`);
                
                if (response.data.success) {
                    const notice = response.data.data;
                    setData({
                        id: notice.noticeId,
                        title: notice.noticeTitle,
                        nickname: notice.userNickname,
                        createdate: notice.createdAt?.substring(0, 10),
                        content: notice.noticeContent
                    });
                } else {
                    alert('공지사항을 불러오는데 실패했습니다.');
                    navigate('/notice');
                }
            } catch (error) {
                console.error('데이터 조회 실패:', error);
                alert('공지사항을 불러오는데 실패했습니다.');
                navigate('/notice');
            } finally {
                setLoading(false);
            }
        };
        
        fetchDetail();
    }, [postid, navigate]);

    return(
        <div className={styles.main_container}>
            {loading ? (
                <div className={styles.loading}>공지사항을 불러오는 중...</div>
            ) : (
                <>
                    <div className={styles.title_container}>
                        <div className={styles.title}>{data.title}</div>
                        <div className={styles.title_content}>
                            <div>작성자: {data.nickname}</div>
                            <div>게시일: {data.createdate} </div>
                        </div>
                    </div>
                    <div className={styles.body_container}>
                        <div className={styles.body_content}>{data.content}</div>
                        <div className={`${styles.btn_div} ${isAdmin ? '' : styles.disable}`}>
                            <EDbtn onDelete={handleDelete} onEdit={handleEdit}/>
                        </div> 
                    </div>
                </>
            )}
        </div>
    );
}

export default NoticeBoardDetail;