import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import style from '../../css/info/LoginForm.module.css';
import { login } from '../common/authUtils';


function LoginForm() {

  const [form, setForm] = useState({ userid: '', password: '' });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    const success = await login(form);
    if (success) {
      // 로그인 성공 시 메인으로 이동
      navigate('/');
    } else {
      alert('로그인에 실패했습니다.');
    }
  };


  return (
    <div className={style.login_div}>
      <form className={style.login_form} onSubmit={handleSubmit}>
        <h2>로그인</h2>
          <input
            name="userid"
            placeholder="아이디를 입력해주세요"
            value={form.userid}
            onChange={handleChange}
            maxLength={20}
            required
          />
          <input
            name="password"
            type="password"
            placeholder="비밀번호를 입력해주세요"
            value={form.password}
            onChange={handleChange}
            maxLength={30}
            required
          />
          <button className={style.login_btn} type='submit'>로그인</button>
          <Link to="/find" className={style.id_pwd}>아이디 | 비밀번호 찾기</Link>
          <Link to="/signup" className={style.register}>회원가입</Link>
        </form>
    </div>
  );
}

export default LoginForm;