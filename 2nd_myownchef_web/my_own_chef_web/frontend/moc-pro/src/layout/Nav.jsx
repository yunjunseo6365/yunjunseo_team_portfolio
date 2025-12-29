import React, { useState, useEffect } from "react";
import styles from '../css/Nav.module.css';
import logo from '../image/logo.png';
import { Link, useLocation } from 'react-router-dom';
import {FaHome, FaStore, FaShoppingCart, FaClipboardList, FaLightbulb} from 'react-icons/fa';
import { PiForkKnifeFill } from "react-icons/pi";
import { FaKitchenSet } from "react-icons/fa6";
import { AiFillTool } from "react-icons/ai";
import { HiBeaker } from "react-icons/hi";
import { checkAdmin as checkAdminStatus } from '../components/common/authUtils';


function Nav(){
    const [isAdmin, setIsAdmin] = useState(false);
    const location = useLocation();

    useEffect(() => {
        const fetchAdminStatus = async () => {
            const admin = await checkAdminStatus();
            setIsAdmin(admin);
        };
        
        fetchAdminStatus();
    }, [location]);

    return(
        <div className={styles.main_container}>
            <div className={styles.nav_container}>
                <div className={styles.nav_logo}>
                    <img src={logo} alt="My Own Chef Pro 로고"/>
                </div>
                <div className={styles.menu_container}>
                    <div>
                        <FaHome className={styles.icons}/>
                        <Link to="/">홈</Link>
                    </div>
                    <div>
                        <PiForkKnifeFill className={styles.icons}/>
                        <Link to="/recipe">레시피 게시판</Link>
                    </div>
                    <div>
                        <FaKitchenSet className={styles.icons}/>
                        <Link to="/sharetool">요리도구나눔</Link>
                    </div>
                    <div className={styles.store_container}>
                        <FaStore className={styles.icons}/>
                        <div className={styles.storedropmenu}>
                            편의점
                        </div>
                        <div className={styles.storediv}>
                            <div className={styles.storedrop_container}>
                                <div>
                                    <HiBeaker className={styles.icons}/>
                                    <Link to="/conv/review">편의점 - 신제품 리뷰</Link>
                                </div> 
                                <div>
                                    <FaLightbulb className={styles.icons}/>
                                    <Link to="/conv/recipe">편의점 - 파먹기</Link>
                                </div> 
                            </div>
                        </div>
                    </div>
                    <div>
                        <FaShoppingCart className={styles.icons}/>
                        <Link to="/withshopping">같이장보기</Link>
                    </div>
                    <div>
                        <FaClipboardList className={styles.icons}/>
                        <Link to="/freeboard">자유게시판</Link>
                    </div>                                                              
                </div>
                {/* 관리자일때만 표시 */}
                {isAdmin && (
                <div className={styles.admin_container}>
                    <div className={styles.adminhover}>
                        <AiFillTool className={styles.icons}/>
                        <div className={styles.admindropmenu}>
                            관리자 페이지
                        </div>
                        <div className={styles.admindiv}>
                            <div className={styles.admindrop_container}>
                                <div>
                                    <Link to="/manage/user">회원관리</Link>
                                </div> 
                                <div>
                                    <Link to="/manage/board">게시글관리</Link>
                                </div> 
                                <div>
                                    <Link to="/manage/report">신고글관리</Link>
                                </div> 
                            </div>
                        </div>
                    </div>
                </div>
                )}
            </div>
        </div>
    )
}

export default Nav;