import React from "react";
import { IoIosArrowForward } from "react-icons/io";
import styles from '../../css/common/PageNation.module.css';

function PageNation({size, currentPage, totalPage, onPageChange}){
    /* 현재 가능한사이즈 : middle, small, 이후 추가할시 */
    const leftpagenationSize = styles[`leftpagenation_${size}`];
    const rightpagenationSize = styles[`rightpagenation_${size}`];
    const pagenationdiv = styles[`pagenationdiv_${size}`];

    const handlePrev = () => {
        if(currentPage > 1){
            onPageChange(currentPage - 1);
        }
    }

    const handleNext = () => {
        if(currentPage < totalPage){
            onPageChange(currentPage + 1);
        }
    }

    return(
        <div className={pagenationdiv}>
            <IoIosArrowForward className={leftpagenationSize}
                                onClick={handlePrev}/>
            {currentPage} / {totalPage === 0 ? 1 : totalPage}
            <IoIosArrowForward className={rightpagenationSize}
                                onClick={handleNext}/>
        </div>
    )
}

export default PageNation;