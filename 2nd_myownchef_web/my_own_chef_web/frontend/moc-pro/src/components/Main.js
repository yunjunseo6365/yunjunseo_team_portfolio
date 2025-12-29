import React from "react";
import { useNavigate } from "react-router-dom";
import style from "../css/Main.module.css"

function Main() {
    const navigate = useNavigate();

    const handleRecipeShare = () => {
        navigate('/recipe');
    };

    return(
        <div className={style.body}>
            <main>
              <h1>My Own Chef에 오신것을 환영합니다</h1>
              <p>자신만의 레시피를 </p>
              <p>사람들과 공유해보세요.</p>
              <button type="button" className={style.linked} onClick={handleRecipeShare}>레시피 공유 하러가기</button>
            </main>

            <div className={style.session}> 
                <div className={style.top_content_wrapper}>
                <div className={style.photo_stack_container}>
                    <div className={`${style.pic} ${style.p1}`}></div>
                    <div className={`${style.pic} ${style.p2}`}></div>
                    <div className={`${style.pic} ${style.p3}`}></div>
                    <div className={`${style.pic} ${style.p4}`}></div>
                    <div className={`${style.pic} ${style.p5}`}></div>
                    <div className={`${style.pic} ${style.p6}`}></div>
                </div>
                <div className={style.text_area}>
                    <h3>About 'My Own Chef'</h3>
                            <p>간단하게 My Own Chef!</p>
                            <p>편리하게 My Own Chef!</p>
                            <p>궁금할땐 My Own Chef!</p>
                </div>
                </div> 
                <div className={style.text_area2}>
                        <p>현대인들의 촉박한 시간, 그리고 얇은 지갑을 지키기 위해 하나의 웹 페이지에서</p>
                        <p>1. 간단한 요리 레시피 정보를 공유</p>
                        <p>2. 맛있는 간단한 끼니 정보를 공유(편의점)</p>
                        <p>3. 식비를 절약하기 위해</p>
                        <p>식자재 공동 구매를 제공하자는 취지에서 시작하게 된 프로젝트 입니다.</p>
                </div>
            </div>
        </div>
    );
}

export default Main;