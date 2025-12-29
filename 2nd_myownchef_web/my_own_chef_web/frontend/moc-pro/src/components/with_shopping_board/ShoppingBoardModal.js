import style from '../../css/with_shopping_board/WithShoppingModal.module.css';
import React, {useState, useEffect} from 'react';
import { useLocationData } from '../common/LocationData';
import { getUserId } from '../common/authUtils';
import axios from 'axios';

export const ShoppingBoardModal = ({onClose, editData, isEditMode}) => {
    const { locationData, loading: locationLoading } = useLocationData();
    const [data, setData] = useState({
        title: '', content: '', status: '모집중', userid: '', si: '', gu: ''
    });

    // 지역 데이터 로딩 완료 시 초기값 설정
    useEffect(() => {
        if (!locationLoading && Object.keys(locationData).length > 0 && !data.si && !isEditMode) {
            const firstSido = Object.keys(locationData)[0];
            setData(prev => ({
                ...prev,
                si: firstSido,
                gu: locationData[firstSido][0]
            }));
        }
    }, [locationLoading, locationData, isEditMode]);    
    const handleOnChange = (e) => {
        const {name, value} = e.target; 
        setData(prev => ({
            ...prev,
            [name] : value
        }));
    }

    const handleOnSubmit = async (e) => {
        e.preventDefault();
        
        try {
            if(isEditMode) {
                // 수정 API
                const response = await axios.put(`http://localhost:18880/api/withshopping/${editData.id}`, {
                    title: data.title,
                    content: data.content,
                    status: data.status,
                    si: data.si,
                    gu: data.gu
                }, {
                    withCredentials: true
                });
                
                if (response.data.success) {
                    alert('게시글이 수정되었습니다.');
                    onClose();
                } else {
                    alert('수정 실패: ' + response.data.message);
                }
            } else {
                // 작성 API
                const response = await axios.post('http://localhost:18880/api/withshopping', {
                    title: data.title,
                    content: data.content,
                    status: data.status,
                    si: data.si,
                    gu: data.gu
                }, {
                    withCredentials: true
                });
                
                if (response.data.success) {
                    alert('게시글이 작성되었습니다.');
                    onClose(false);
                } else {
                    alert('작성 실패: ' + response.data.message);
                }
            }
        } catch (error) {
            console.error('제출 실패:', error);
            alert('제출 중 오류가 발생했습니다.');
        }
    }

    useEffect(() => {
        const fetchUserId = async () => {
            const userId = await getUserId();
            if (userId) {
                setData(prev => ({
                    ...prev,
                    userid: userId
                }));
            }
        };
        fetchUserId();
    }, []);

    // 수정 모드: 기존 데이터 로드
    useEffect(() => {
        const loadEditData = async () => {
            if(isEditMode && editData) {
                const userId = await getUserId();
                setData({
                    title: editData.title || '',
                    content: editData.content || '',
                    status: editData.status || '모집중',
                    userid: editData.userid || userId,
                    si: editData.si || '경기도',
                    gu: editData.gu || '고양시'
                });
            }
        };
        loadEditData();
    }, [isEditMode, editData])

    if (locationLoading) {
        return <div>지역 데이터 로딩중...</div>;
    }

    return (
            <div className={style.modal_container}>
            <div className={style.modal_header}>
                <p>{isEditMode ? '같이 장보기 글 수정' : '같이 장보기 글은 24시간 뒤 자동 삭제됩니다'}</p>
                <button type="button" className={style.close_btn} onClick={onClose}>X</button>
            </div>
            <form onSubmit={handleOnSubmit}>
            <div className={style.location_container}>
                <div className={style.select_wrapper}>
                <select className={style.location_select} name="si" onChange={handleOnChange} value={data.si}>
                    {Object.keys(locationData).map(sido => (
                        <option key={sido} value={sido}>{sido}</option>
                    ))}
                </select>
                </div>
                <div className={style.select_wrapper}>
                {data.si && locationData[data.si] && (
                    <select className={style.location_select} name="gu" onChange={handleOnChange} value={data.gu}>
                        {locationData[data.si].map(sigungu => (
                            <option key={sigungu} value={sigungu}>{sigungu}</option>
                        ))}
                    </select>
                )}
                </div>
            </div>
            <div className={style.form_group}>
                <label>제목</label>
                <input type='text' placeholder='제목을 입력하세요'
                        onChange={handleOnChange}
                        value={data.title}
                        name="title"
                        required
                        maxLength={100}
                        />
            </div>
            <div className={style.form_group}>
                <label>요청글</label>
                <textarea rows="4" cols="50" placeholder='내용 입력'
                            onChange={handleOnChange}
                            value={data.content}
                            name="content"
                            required
                            maxLength={500}
                            />
            </div>
            <div className={style.button_container}>
                <button type='button' className={`${style.modal_button} ${style.cancel_button}`} 
                    onClick={onClose}>취소</button>
                <button type='submit' className={`${style.modal_button} ${style.submit_button}`}>
                    글 쓰기
                </button>
            </div>
            </form>
            </div>
    )
}

