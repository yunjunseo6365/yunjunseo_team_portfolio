import style from '../../css/info/ReportDetailModal.module.css';
import React from 'react';

export const ReportDetailModal = ({ onClose, reportData }) => {
    // reportData 예상 구조: { report_id, target_id, board_type, report_content, reporter_nickname, created_at }
    
    return (
        <div className={style.modal_container}>
            <div className={style.modal_header}>
                <h2 className={style.modal_title}>신고 사유</h2>
            </div>
            <div className={style.modal_content}>
                <div className={style.report_info}>
                    <p className={style.report_text}>
                        {reportData?.reportContent || 
                        `이 게시물 작성자가 댓글에서 저와 다른 의견들에게 "너가 없는 것 같은데"라며 등의 인신공격적 비난 발언을 하고 있습니다. 다수 의견을 묵인함으로 비방 없이 의견의 자유를 부탁합니다.`}
                    </p>
                </div>
            </div>
            <div className={style.button_container}>
                <button
                    type="button"
                    className={`${style.modal_button} ${style.confirm_button}`}
                    onClick={onClose}
                >
                    확인
                </button>
            </div>
        </div>
    );
};
