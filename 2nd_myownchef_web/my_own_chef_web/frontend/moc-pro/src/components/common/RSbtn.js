import React from "react";
import { FaFlag } from "react-icons/fa";
import { BsFillPinFill } from "react-icons/bs";
import styles from '../../css/common/RSbtn.module.css';

function RSbtn({ onReport, onSave, isSaved }){
    return(
        <div className={styles.btn_container}>
            <div className={styles.btn_div} onClick={onReport} style={{ cursor: 'pointer' }}>
                신고하기
                <FaFlag className={styles.icon}/>
            </div>
            <div className={styles.btn_div} onClick={onSave} style={{ cursor: 'pointer' }}>
                {isSaved ? '저장취소' : '저장하기'}
                <BsFillPinFill className={isSaved ? styles.apply : styles.icon2}/>
            </div>
        </div>
    );
}

export default RSbtn;