import React, { useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import style from '../../css/info/RegisterForm.module.css';
import Modal from 'react-modal';
import { AgreeModal } from './AgreeModal';
import { signup, checkIdDuplicate } from '../common/authUtils';


function RegisterForm() {
  const [data, setData] = useState({
    userid: '', pwd: '', pwd2: '', username: '', nickname: '', email: ''
  });

  const [acceptId, setAcceptId] = useState(false);
  const [agree, setAgree] = useState(false);
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const modalType = searchParams.get('modal');

  const handleChange = (e) => {
    const {name, value} = e.target;
    setData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleCheckId = async () => {
    if (!data.userid || data.userid.trim() === '') {
      alert('아이디를 입력해주세요.');
      return;
    }
    
    // 아이디 중복 확인 API 호출
    const result = await checkIdDuplicate(data.userid);
    
    if (result.success && result.available) {
      alert(result.message); // "사용 가능한 아이디입니다."
      setAcceptId(true);
    } else {
      alert(result.message); // "이미 사용 중인 아이디입니다."
      setAcceptId(false);
    }
  };

  const handleModal = () => {
    navigate('/signup?modal=modal');
  };

  const handleAgree = (agree) => {
    setAgree(agree);
  };

  const closeModal = () => {
    navigate('/signup');
  };

  const handleOnSubmit = async (e) => {
    e.preventDefault();
    
    if(!agree){
      alert('이용약관에 동의해주세요.');
      return;
    }
    
    if(!acceptId){
      alert('아이디 중복 확인을 해주세요.');
      return;
    }
    
    if(data.pwd !== data.pwd2){
      alert('비밀번호가 일치하지 않습니다.');
      return;
    }
    
    // 회원가입 API 호출
    const signupData = {
      userid: data.userid,
      password: data.pwd,
      username: data.username,
      nickname: data.nickname,
      email: data.email
    };
    
    const result = await signup(signupData);
    
    if (result.success) {
      alert(result.message);
      navigate('/login');
    } else {
      alert(result.message);
      // errorField가 있으면 해당 필드에 포커스
      if (result.errorField) {
        const errorInput = document.querySelector(`input[name="${result.errorField}"]`);
        if (errorInput) {
          errorInput.focus();
        }
      }
    }
  };

  return (
    <div className={style.register_div}>
      <form className={style.register_form} onSubmit={handleOnSubmit}>
        <h1>회원가입</h1>
        <div className={style.input_group}>
            <input
                name="userid"
                placeholder="아이디를 입력해주세요"
                value={data.userid}
                onChange={handleChange}
                maxLength={20}
                required
            />
            <button type="button" onClick={handleCheckId}>
                아이디 중복 확인
            </button>
        </div>
            <input
                name="pwd"
                type="password"
                placeholder="비밀번호를 입력해주세요"
                value={data.pwd}
                onChange={handleChange}
                maxLength={30}
                required
            />
            <input
              name="pwd2"
              type="password"
              placeholder="비밀번호를 재입력해주세요"
              value={data.pwd2}
              onChange={handleChange}
              maxLength={30}
              required
            />
            <input
              name="username"
              placeholder="이름"
              value={data.username}
              onChange={handleChange}
              maxLength={20}
              required
            />
            <input
              name="nickname"
              placeholder="닉네임"
              value={data.nickname}
              onChange={handleChange}
              maxLength={20}
              required
            />
            <input
              name="email"
              type="email"
              placeholder="이메일"
              value={data.email}
              onChange={handleChange}
              maxLength={50}
              required
            />
            <div className={style.input_agree}>
                <input
                  type="checkbox"
                  name="agree" 
                  checked={agree}
                  disabled
                  />
                <p onClick={handleModal}>이용약관에 동의합니다</p>
            </div>
          <button type='submit' className={style.register}>회원가입</button>
        </form>
        <Modal isOpen={modalType === 'modal'} onRequestClose={closeModal}
                overlayClassName={style.modal} className={style.modal_container}>
                  <AgreeModal onClose={closeModal} handleAgree={handleAgree}/>
        </Modal>
    </div>
  );
}

export default RegisterForm;