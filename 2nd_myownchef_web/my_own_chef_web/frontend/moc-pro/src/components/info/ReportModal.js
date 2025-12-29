import style from '../../css/info/ReportModal.module.css';
import React, { useState, useEffect } from 'react';
import { getUserId } from '../common/authUtils';
import axios from 'axios';

export const ReportModal = ({ onClose, targetId, type, category }) => {
    const [data, setData] = useState({
        userid: '',
        reportType: type,        // 'POST' 또는 'COMMENT'
        boardType: category,
        targetId: targetId,
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

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        if (!data.content.trim()) {
            alert('신고 사유를 입력해주세요.');
            return;
        }

        try {
            const response = await axios.post('http://localhost:18880/api/report', {
                reportType: data.reportType,
                boardType: data.boardType,
                targetId: data.targetId,
                reportContent: data.content
            });

            if (response.data.success) {
                alert('신고가 접수되었습니다.');
                onClose();
            } else {
                alert(response.data.message || '신고 처리에 실패했습니다.');
            }
        } catch (error) {
            console.error('신고 실패:', error);
            alert(error.response?.data?.message || '신고 처리 중 오류가 발생했습니다.');
        }
    };

    return (
        <div className={style.modal_container}>
            <div className={style.modal_header}>
                <h2 className={style.modal_title}>신고하기</h2>
            </div>
            <form onSubmit={handleSubmit}>
                <div className={style.modal_content}>
                    <textarea
                        className={style.report_textarea}
                        placeholder="신고 사유를 입력해주세요."
                        value={data.content}
                        onChange={(e) => setData({...data, content: e.target.value})}
                        rows="8"
                        maxLength={500}
                        required
                    />
                </div>
                <div className={style.button_container}>
                    <button
                        type="button"
                        className={`${style.modal_button} ${style.cancel_button}`}
                        onClick={onClose}
                    >
                        취소
                    </button>
                    <button
                        type="submit"
                        className={`${style.modal_button} ${style.submit_button}`}
                    >
                        신고 하기
                    </button>
                </div>
            </form>
        </div>
    );
};
