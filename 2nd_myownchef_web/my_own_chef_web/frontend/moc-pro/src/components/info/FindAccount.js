import React, { useState, useEffect } from "react";
import style from '../../css/info/FindAccount.module.css';
import axios from 'axios';

// axios 기본 설정
axios.defaults.withCredentials = true;

function FindAccount() {
    const [findId, setFindId] = useState(true);
    const [submitted, setSubmitted] = useState(false);
    const [resultMessage, setResultMessage] = useState(''); // 결과 메시지 저장
    const [activeTab, setActiveTab] = useState("id"); // 버튼 액티브 css스타일 변경용
    const [data, setData] = useState({
        id: '', email: '', name: ''
    });

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        // axios 영역
        if(findId){
            // 아이디 찾기
            try {
                const response = await axios.post('http://localhost:18880/api/auth/find-id', {
                    name: data.name,
                    email: data.email
                });
                
                if (response.data.success) {
                    setResultMessage(response.data.message);
                    setSubmitted(true);
                } else {
                    alert(response.data.message || '일치하는 사용자 정보를 찾을 수 없습니다.');
                }
            } catch (error) {
                console.error('아이디 찾기 실패:', error);
                alert('아이디 찾기 중 오류가 발생했습니다.');
            }
        } else {
            // 비밀번호 찾기
            try {
                const response = await axios.post('http://localhost:18880/api/auth/reset-password', {
                    name: data.name,
                    userid: data.id,
                    email: data.email
                });
                
                if (response.data.success) {
                    setResultMessage(response.data.message);
                    setSubmitted(true);
                } else {
                    alert(response.data.message || '일치하는 사용자 정보를 찾을 수 없습니다.');
                }
            } catch (error) {
                console.error('비밀번호 재설정 실패:', error);
                alert('비밀번호 재설정 중 오류가 발생했습니다.');
            }
        }
    };

    const handleChange = (e) => {
        const {name, value} = e.target;
        setData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    useEffect(() => {
        setData({id: '', email: '', name: ''});
        setSubmitted(false);
    }, [findId]);


    return(
        <div>
            <div className={style.toggle}>
                <button type="button" className={activeTab === "id" ? style.active : ""}
                onClick={() => {setFindId(true); setSubmitted(false); setActiveTab('id');}}>아이디 찾기</button>
                <button type="button" className={activeTab === "pwd" ? style.active : ""} 
                onClick={() => {setFindId(false); setSubmitted(false); setActiveTab('pwd');}}>비밀번호 찾기</button>
            </div>
            <div  className={style.find_div}>
                {findId 
                ? 
                <form onSubmit={handleSubmit} className={style.find_form_id}>
                    <h1>아이디 찾기</h1>
                    <input
                        name="name"
                        placeholder="이름을 입력해주세요"
                        required
                        value={data.name}
                        onChange={handleChange}
                        maxLength={20}
                    />
                    <input
                        name="email"
                        type="email"
                        placeholder="이메일을 입력해주세요"
                        required
                        value={data.email}
                        onChange={handleChange}
                        maxLength={50}
                    />
                    <button type="submit" className={style.find}>아이디 찾기</button>
                     {/* 제출 후 메시지 출력부 */}
                    {submitted && (
                    <div className={style.submit_erea}>
                        {resultMessage}
                    </div>
                    )}
                </form>
                 :
                 <form  onSubmit={handleSubmit} className={style.find_form_pwd}>
                    <h1>비밀번호 찾기</h1>
                    <input
                        name="name"
                        placeholder="이름을 입력해주세요"
                        required
                        value={data.name}
                        onChange={handleChange}
                        maxLength={20}
                    />
                    <input
                        name="id"
                        placeholder="아이디를 입력해주세요"
                        required
                        value={data.id}
                        onChange={handleChange}
                        maxLength={20}
                    />
                    <input
                        name="email"
                        type="email"
                        placeholder="이메일을 입력해주세요"
                        required
                        value={data.email}
                        onChange={handleChange}
                        maxLength={50}
                    />
                    <button type="submit" className={style.find}>비밀번호 찾기</button>
                    {/* 제출 후 메시지 출력부 */}
                    {submitted && (
                    <div className={style.submit_erea}>
                        {resultMessage}
                    </div>
                    )}
                 </form>
                 }
                 
            </div>
        </div>
    );
}

export default FindAccount;