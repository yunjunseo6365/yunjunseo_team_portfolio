import style from '../../css/Modal.module.css';
import React from 'react';


// 매우중요!!!! 사용전 필독★ yarn add react-modal로 라이브러리 설치 후
// 모달 팝업창을 띄울 js 파일에 추가해야할 코드가 있습니다.
// 아래 예시처럼 글 쓰기 와 같은 팝업을 띄울 수 있는 버튼이 있는 부모 js 파일에
// (예시: ShoppingBoard.js 에서 ShoppingBoardModal.js 를 띄우는것처럼)

// 부모 js 파일에 임포트 먼저 (여기엔 import Modal 안씁니다)
// import {ShoppingBoardModal} from './ShoppingBoardModal';
// import Modal from 'react-modal';
// import modalStyle from '../../css/Modal.module.css';

// 그뒤에는

// const [isModalOpen, setIsModalOpen] = useState(false);

// const openModal = () => setIsModalOpen(true);
// const closeModal = () => setIsModalOpen(false);

// 와 같은 함수를 사용해야하고,

// 마지막으로 글 쓰기 버튼을 예시로 든다면 그아래에 <Modal>이라는 태그를 사용해야 합니다

// </div>
//     <button type='button' className={style.write_btn}
//         onClick={openModal}>글 쓰기</button>
// </div>
// <Modal isOpen={isModalOpen} onRequestClose={closeModal}
//     overlayClassName={modalStyle.modal_overlay} className={modalStyle.modal_container}>
// <ShoppingBoardModal onClose={closeModal}/>
// </Modal>


export const ModalContent = ({onClose}) => {
    // const [selectedProvince, setSelectedProvince] = useState('경기도');
    
    //원하는 함수명으로 바꿔서 로직 추가하시면 댑니다
    const handleSidoChange = (e) => {
        // setSelectedProvince(e.target.value);
    }

    return (
            <div className={style.modal_container}>
            <div className={style.modal_header}>
                {/* 창닫기 X 버튼입니다 */}
                <button className={style.close_btn} onClick={onClose}>X</button>
            <div className={style.form_group}>
                <label>제목</label>
                <input type='text' placeholder='제목을 입력하세요'/>
            </div>
            <div className={style.form_group}>
                <label>요청글</label>
                <textarea rows="4" cols="50" placeholder='내용 입력'/>
            </div>
            <div className={style.button_container}>
                <button type='button' className={`${style.modal_button} ${style.cancel_button}`} 
                    onClick={onClose}>취소</button>
                <button type='button' className={`${style.modal_button} ${style.submit_button}`}
                    >글 쓰기</button>
            </div>
            </div>
            </div>
    )
}

