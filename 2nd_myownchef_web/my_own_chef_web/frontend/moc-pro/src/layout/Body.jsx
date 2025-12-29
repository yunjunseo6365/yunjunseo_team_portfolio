import React from "react";
import { Route, Routes } from "react-router-dom";
import Main from "../components/Main";
import FreeBoard from "../components/another_board/FreeBoard";
import NoticeBoard from "../components/another_board/NoticeBoard";
import FreeBoardWrite from "../components/another_board/FreeBoardWrite";
import FreeBoardDetail from "../components/another_board/FreeBoardDetail";
import NoticeBoardDetail from '../components/another_board/NoticeBoardDetail';
import NoticeBoardWrite from "../components/another_board/NoticeBoardWrite";
import AdminUserBoard from "../components/admin_board/AdminUserBoard";
import AdminPostBoard from "../components/admin_board/AdminPostBoard";
import AdminReportBoard from "../components/admin_board/AdminReportBoard";
import ConvenienceBoard from '../components/conv_board/ConvenienceBoard';
import ConvenienceRecipeBoard from "../components/conv_board/ConvenienceRecipeBoard";
import ConvenienceCombWrite from "../components/conv_board/ConvenienceCombWrite";
import ConvenienceCombDetail from "../components/conv_board/ConvenienceCombDetail";
import ConvenienceReviewWrite from "../components/conv_board/ConvenienceReviewWrite";
import ConvenienceReviewDetail from "../components/conv_board/ConvenienceReviewDetail";
import FindAccount from '../components/info/FindAccount';
import RegisterForm from '../components/info/RegisterForm';
import RecipeBoard from "../components/recipe_board/RecipeBoard";
import RecipeBoardDetail from '../components/recipe_board/RecipeBoardDetail';
import RecipeBoardWrite from '../components/recipe_board/RecipeBoardWrite';
import ShareBoard from "../components/share_tool_board/ShareBoard";
import ShareToolDetail from '../components/share_tool_board/ShareToolDetail';
import ShareToolWrite from '../components/share_tool_board/ShareToolWrite';
import ShoppingBoard from "../components/with_shopping_board/ShoppingBoard";
import { ShoppingBoardDetailModal } from "../components/with_shopping_board/ShoppingBoardDetailModal";
import LoginForm from '../components/info/LoginForm';
import MyPage from '../components/info/MyPage';

function Body() {
    return(
        <div>
            <Routes>
                <Route path="/" element={<Main/>}/>
                
                {/* 레시피 게시판 */}
                <Route path="/recipe" element={<RecipeBoard/>}/>
                <Route path="/recipe/detail/:postid" element={<RecipeBoardDetail/>}/>
                <Route path="/recipe/write" element={<RecipeBoardWrite/>}/>
                <Route path="/recipe/write/:postid" element={<RecipeBoardWrite/>}/>
                
                {/* 도구 공유 게시판 */}
                <Route path="/sharetool" element={<ShareBoard/>}/>
                <Route path="/sharetool/detail/:postid" element={<ShareToolDetail/>}/>
                <Route path="/sharetool/write" element={<ShareToolWrite/>}/>
                <Route path="/sharetool/write/:postid" element={<ShareToolWrite/>}/>
                
                {/* 편의점 리뷰 게시판 */}
                <Route path="/conv/review" element={<ConvenienceBoard/>}/>
                <Route path="/conv/review/detail/:postid" element={<ConvenienceReviewDetail/>}/>
                <Route path="/conv/review/write" element={<ConvenienceReviewWrite/>}/>
                <Route path="/conv/review/write/:postid" element={<ConvenienceReviewWrite/>}/>
                
                {/* 편의점 조합 게시판 */}
                <Route path="/conv/recipe" element={<ConvenienceRecipeBoard/>}/>
                <Route path="/conv/recipe/detail/:postid" element={<ConvenienceCombDetail/>}/>
                <Route path="/conv/recipe/write" element={<ConvenienceCombWrite/>}/>
                <Route path="/conv/recipe/write/:postid" element={<ConvenienceCombWrite/>}/>
                
                {/* 같이 장보기 게시판 */}
                <Route path="/withshopping" element={<ShoppingBoard/>}/>
                
                {/* 자유 게시판 */}
                <Route path="/freeboard" element={<FreeBoard/>}/>
                <Route path="/freeboard/:postid" element={<FreeBoardDetail/>}/>
                <Route path="/freeboard/write" element={<FreeBoardWrite/>}/>
                <Route path="/freeboard/write/:postid" element={<FreeBoardWrite/>}/>
                
                {/* 공지사항 게시판 */}
                <Route path="/notice" element={<NoticeBoard/>}/>
                <Route path="/notice/detail/:postid" element={<NoticeBoardDetail/>}/>
                <Route path="/notice/write" element={<NoticeBoardWrite/>}/>
                <Route path="/notice/write/:postid" element={<NoticeBoardWrite/>}/>
                
                
                {/* 관리자 페이지 */}
                <Route path="/manage/user" element={<AdminUserBoard/>}/>
                <Route path="/manage/board" element={<AdminPostBoard/>}/>
                <Route path="/manage/report" element={<AdminReportBoard/>}/>

                {/* 로그인/회원가입 */}
                <Route path="/login" element={<LoginForm/>}/>
                <Route path="/signup" element={<RegisterForm/>}/>
                <Route path="/find" element={<FindAccount/>}/>
                <Route path="/mypage" element={<MyPage/>}/>

                {/* 소개페이지 */}
                {/* <Route path="/produce" element={</>}/> */}
            </Routes>
        </div>
    )
}

export default Body;