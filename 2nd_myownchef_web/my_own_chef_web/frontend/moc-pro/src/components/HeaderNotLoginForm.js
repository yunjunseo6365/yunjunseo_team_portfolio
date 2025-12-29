import style from '../css/HeaderNotLogin.module.css';
import React from "react";
import { Link } from 'react-router-dom';
import { FaFlag } from "react-icons/fa6";
import { FiLogIn } from "react-icons/fi";
import { BsFillPersonPlusFill } from "react-icons/bs";

function HeaderNotLoginForm() {
    return(
    <div className={style.header_container}>
        <div className={style.left_group}>
            <h2>
            <Link className={style.nav_link}>
                <FaFlag className={style.icons}/>
                <Link to="/notice">공지사항</Link>
            </Link>
            </h2>
        </div>
        <div className={style.right_group}>
            <h2>
            <Link className={style.nav_link}>
                <FiLogIn className={style.icons}/>
                <Link to="/login">로그인</Link>
            </Link>
            </h2>
            <h2>
            <Link className={style.nav_link}>
                <BsFillPersonPlusFill className={style.icons}/>
                <Link to="/signup">회원가입</Link>
            </Link>
            </h2>
        </div>
        </div>
    );
}

export default HeaderNotLoginForm;