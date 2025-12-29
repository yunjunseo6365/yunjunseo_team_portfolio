import React from "react";
import styles from '../../css/common/EDbtn.module.css';

function EDbtn({onDelete, onEdit}){
    return(
        <div className={styles.btn_container}>
            <button onClick={onDelete}>삭제하기</button>
            <button onClick={onEdit}>수정하기</button>
        </div>
    );
}

export default EDbtn;