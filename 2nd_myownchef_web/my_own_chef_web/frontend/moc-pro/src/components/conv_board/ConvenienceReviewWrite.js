import React, { useState, useEffect } from "react";
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import ImageUploadBoxes from "../common/ImageUploadBoxes";
import style from '../../css/conv_board/ConvenienceReviewWrite.module.css';
import { getUserId } from '../common/authUtils';


function ConvenienceReviewWrite() {
    const navigate = useNavigate();
    const { postid } = useParams();
    const [isEditMode, setIsEditMode] = useState(false);
    const [loading, setLoading] = useState(false);
    const [storeOptions, setStoreOptions] = useState([]);
    const [categoryOptions, setCategoryOptions] = useState([]);
    const [data, setData] = useState({
            userid: '',
            title: '',
            content: '',
            price: '',
            conv: '',
            category: ''
        });
    const [images, setImages] = useState([]);
    const [existingImages, setExistingImages] = useState([]);

    // 편의점 옵션 로드
    useEffect(() => {
        const loadStores = async () => {
            try {
                const response = await axios.get('http://localhost:18880/api/common/codes/D01');
                const options = response.data.map(item => item.codeName);
                setStoreOptions(options);
                
                // 신규 작성 시 첫 번째 편의점을 기본값으로 설정
                if (!postid && options.length > 0) {
                    setData(prev => ({...prev, conv: options[0]}));
                }
            } catch (error) {
                console.error('편의점 옵션 로드 실패:', error);
                setStoreOptions([]);
            }
        };
        loadStores();
    }, [postid]);

    // 카테고리 옵션 로드
    useEffect(() => {
        const loadCategories = async () => {
            try {
                const response = await axios.get('http://localhost:18880/api/common/codes/F01');
                const options = response.data.map(item => item.codeName);
                setCategoryOptions(options);
                
                // 신규 작성 시 첫 번째 카테고리를 기본값으로 설정
                if (!postid && options.length > 0) {
                    setData(prev => ({...prev, category: options[0]}));
                }
            } catch (error) {
                console.error('카테고리 옵션 로드 실패:', error);
                setCategoryOptions([]);
            }
        };
        loadCategories();
    }, [postid]);

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

        // 수정 모드: 기존 글 데이터 로드
        useEffect(() => {
            if(postid) {
                setIsEditMode(true);
                const fetchData = async () => {
                    setLoading(true);
                    try {
                        const response = await axios.get(`http://localhost:18880/api/conv-review/${postid}`);
                        if (response.data.success) {
                            const { convReview, images: fetchedImages } = response.data.data;
                            setData({
                                userid: convReview.userId,
                                title: convReview.convReviewTitle,
                                content: convReview.convReviewContent,
                                price: convReview.convReviewPrice || '',
                                conv: convReview.convReviewStore,
                                category: convReview.convReviewCategory
                            });
                            
                            // 기존 이미지 URL 설정 (noImage 제외)
                            if (fetchedImages && fetchedImages.length > 0) {
                                const imageUrls = fetchedImages
                                    .filter(img => !img.convReviewImageUrl.includes('placehold.co'))
                                    .map(img => img.convReviewImageUrl);
                                setExistingImages(imageUrls);
                            }
                        }
                    } catch (error) {
                        console.error('데이터 로드 실패:', error);
                    } finally {
                        setLoading(false);
                    }
                };
                fetchData();
            }
        }, [postid]);
        
        const handleCancel = () => {
          navigate(`/conv/review`);
        }

        // 이미지 변경 핸들러
        const handleImagesChange = (imageData) => {
            setImages(imageData);
        };

        const handleSubmit = async (e) => {
          e.preventDefault();
          
          const formData = new FormData();
          formData.append('userId', data.userid);
          formData.append('title', data.title);
          formData.append('content', data.content);
          formData.append('price', data.price || '');
          formData.append('store', data.conv);
          formData.append('category', data.category);
          
          // 이미지 처리
          const newFiles = [];
          const keepUrls = [];
          
          images.forEach((item) => {
              if (item) {
                  if (item.type === 'file') {
                      newFiles.push(item.data);
                  } else if (item.type === 'url') {
                      keepUrls.push(item.data);
                  }
              }
          });
          
          // 새 파일 추가
          newFiles.forEach(file => {
              formData.append('images', file);
          });
          
          // 기존 이미지 URL 전달
          if (keepUrls.length > 0) {
              formData.append('existingImageUrls', JSON.stringify(keepUrls));
          }
          
          try {
              if(isEditMode) {
                  // 수정
                  const response = await axios.put(`http://localhost:18880/api/conv-review/${postid}`, formData, {
                      headers: { 'Content-Type': 'multipart/form-data' }
                  });
                  
                  if (response.data.success) {
                      alert('리뷰가 수정되었습니다.');
                      navigate(`/conv/review/detail/${postid}`);
                  } else {
                      alert(response.data.message || '수정에 실패했습니다.');
                  }
              } else {
                  // 작성
                  const response = await axios.post('http://localhost:18880/api/conv-review', formData, {
                      headers: { 'Content-Type': 'multipart/form-data' }
                  });
                  
                  if (response.data.success) {
                      alert('리뷰가 작성되었습니다.');
                      navigate('/conv/review');
                  } else {
                      alert(response.data.message || '작성에 실패했습니다.');
                  }
              }
          } catch (error) {
              console.error('제출 실패:', error);
              alert('오류가 발생했습니다.');
          }
        }

    return(
        <div className={style.conv_review_write}>
          <div className={style.titlediv}>
             {isEditMode ? '편의점 신제품 리뷰 수정' : '편의점 신제품 리뷰 글 작성'}
           </div>
          {loading ? (
              <div className={style.loading}>리뷰를 불러오는 중...</div>
          ) : (
              <div className={style.conv_write_container}>
              <form onSubmit={handleSubmit}>
            <div className={style.select}>
            <div className={style.store_filter_group}>
              {/* 드롭 다운 형태로 변경 */}
              <select 
                className={style.filter_dropdown} 
                value={data.conv}
                onChange={(e) => setData({...data, conv: e.target.value})}>
                {storeOptions.map(storesName => (
                    <option key={storesName} value={storesName}>
                        {storesName}
                    </option>
                ))}
                </select>
            </div>
            <div className={style.category_tab_group}>
              <select 
                className={style.category_dropdown}
                value={data.category}
                onChange={(e) => setData({...data, category: e.target.value})}>
                {categoryOptions.map(categorysName => (
                    <option key={categorysName} value={categorysName}>
                        {categorysName}
                    </option>
                  ))}
              </select>
            </div>
            </div>
            <div>
              <div className={style.conv_title}>제품명</div>
              <input 
                className={style.input_title} 
                type="text" 
                placeholder="제품명을 입력해주세요"
                value={data.title}
                onChange={(e) => setData({...data, title: e.target.value})}
                required
              />
            </div>
            <div>
              <div className={style.recipe_title}>가격</div>
              <input 
                className={style.input_price} 
                type="number" 
                placeholder="가격을 입력해주세요"
                value={data.price}
                onChange={(e) => setData({...data, price: e.target.value})}
                required
              />
            </div>
            <div>
              <div className={style.recipe_title}>상세내용</div>
              <textarea 
                className={style.input_content}
                value={data.content}
                onChange={(e) => setData({...data, content: e.target.value})}
                maxLength={500}
                rows={5}
                cols={50}
                placeholder="상세내용을 입력해주세요.(최대 500자)"
                required
              />
            </div>
            <div>
              <ImageUploadBoxes 
                onImagesChange={handleImagesChange}
                initialImages={existingImages}
              />
            </div>
            <div className={style.formbtn}>
              <button type="button" onClick={handleCancel}>취소</button>
              <button type="submit">작성 완료</button>
            </div>
          </form>
              </div>
          )}
        </div>
    );
}

export default ConvenienceReviewWrite;