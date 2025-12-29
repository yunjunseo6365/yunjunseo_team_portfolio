import React, {useState, useEffect} from "react";
import { useNavigate, useParams } from "react-router-dom";
import style from '../../css/share_tool_board/ShareToolWrite.module.css'
import ImageUploadBoxes from '../common/ImageUploadBoxes'
import { useLocationData } from '../common/LocationData';
import { getUserId } from '../common/authUtils';
import axios from 'axios';

function ShareToolWrite() {
    const navigate = useNavigate();
    const { postid } = useParams();
    const { locationData, loading: locationLoading, error: locationError } = useLocationData();
    const [isEditMode, setIsEditMode] = useState(false);
    const [loading, setLoading] = useState(false);
    const [data, setData] = useState({
        userid: '',
        category: '',      // 나눔 제품 카테고리
        productname: '',   // 제품명
        si: '',            // 시/도
        gu: '',            // 구/군
        content: '',       // 상세내용
        status: '나눔중'   // 나눔 상태 (기본값: 나눔중)
    });
    const [images, setImages] = useState([]);
    const [existingImages, setExistingImages] = useState([]);

    // 지역 데이터 로딩 완료 시 초기값 설정
    useEffect(() => {
        if (!locationLoading && Object.keys(locationData).length > 0 && !data.si) {
            const firstSido = Object.keys(locationData)[0];
            setData(prev => ({
                ...prev,
                si: firstSido,
                gu: locationData[firstSido][0]
            }));
        }
    }, [locationLoading, locationData]);

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
                    const response = await axios.get(`http://localhost:18880/api/sharetool/${postid}`);
                    if (response.data.success) {
                        const { shareTool, images: fetchedImages } = response.data.data;
                        setData({
                            userid: shareTool.userId,
                            category: shareTool.shareToolTitle,
                            productname: shareTool.shareToolProduct,
                            si: shareTool.shareToolProvince,
                            gu: shareTool.shareToolCity,
                            content: shareTool.shareToolContent,
                            status: shareTool.shareToolStatus
                        });
                        
                        // 기존 이미지 URL 설정 (noImage 제외)
                        if (fetchedImages && fetchedImages.length > 0) {
                            const imageUrls = fetchedImages
                                .filter(img => !img.shareToolImageUrl.includes('placehold.co'))
                                .map(img => img.shareToolImageUrl);
                            setExistingImages(imageUrls);
                        }
                    } else {
                        console.error('데이터 로드 실패:', response.data.message);
                        alert('게시글을 불러올 수 없습니다.');
                        navigate('/sharetool');
                    }
                } catch (error) {
                    console.error('데이터 로드 실패:', error);
                    alert('게시글을 불러오는 중 오류가 발생했습니다.');
                    navigate('/sharetool');
                } finally {
                    setLoading(false);
                }
            };
            fetchData();
        }
    }, [postid, navigate]);

    const handleSidoChange = (e) => {
        const newSi = e.target.value;
        setData({
            ...data,
            si: newSi,
            gu: locationData[newSi][0]  // 첫 번째 구/군으로 초기화
        });
    }

    const handleBoard = () => {
      navigate('/sharetool');
    }

    // 이미지 변경 핸들러
    const handleImagesChange = (imageData) => {
        setImages(imageData);
    };

    const handleSubmit = async (e) => {
      e.preventDefault();
      
      const formData = new FormData();
      formData.append('userId', data.userid);
      formData.append('title', data.category);
      formData.append('product', data.productname);
      formData.append('content', data.content);
      formData.append('status', data.status);
      formData.append('province', data.si);
      formData.append('city', data.gu);
      
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
          // 수정 API
          const response = await axios.put(`http://localhost:18880/api/sharetool/${postid}`, formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            },
            withCredentials: true
          });
          
          if (response.data.success) {
            alert('게시글이 수정되었습니다.');
            navigate(`/sharetool/detail/${postid}`);
          } else {
            alert('수정 실패: ' + response.data.message);
          }
        } else {
          // 작성 API
          const response = await axios.post('http://localhost:18880/api/sharetool', formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            },
            withCredentials: true
          });
          
          if (response.data.success) {
            alert('게시글이 작성되었습니다.');
            navigate('/sharetool');
          } else {
            alert('작성 실패: ' + response.data.message);
          }
        }
      } catch (error) {
        console.error('제출 실패:', error);
        alert('제출 중 오류가 발생했습니다.');
      }
    }

    return(
        <div className={style.recipe_board_detail}>
          <div className={style.titlediv}>
             {isEditMode ? '요리 도구 나눔 수정' : '요리 도구 나눔 글 작성'}
           </div>
          {loading ? (
              <div className={style.loading}>게시글을 불러오는 중...</div>
          ) : (
              <div className={style.recipe_board_container}>
              <form onSubmit={handleSubmit}>
            <div>
              <div className={style.recipe_title}>나눔 제품 카테고리</div>
              <input 
                className={style.input_title} 
                type="text" 
                placeholder="예) 후라이팬, 전자레인지"
                value={data.category}
                onChange={(e) => setData({...data, category: e.target.value})}
                required
              />
            </div>

            <div>
              <div className={style.recipe_title}>제품명</div>
              <input 
                className={style.input_title} 
                type="text" 
                placeholder="예)LG 디오스 23L 전자레인지"
                value={data.productname}
                onChange={(e) => setData({...data, productname: e.target.value})}
                required
              />
            </div>

        {locationLoading ? (
            <div>지역 데이터 로딩중...</div>
        ) : locationError ? (
            <div>지역 데이터를 불러오는 중 문제가 발생했습니다.</div>
        ) : (
            <div className={style.search_form_container}>
                <div className={style.location_container}>
                    <select className={style.sido_button} onChange={handleSidoChange} value={data.si}>
                        {Object.keys(locationData).map(sido => (
                            <option key={sido} value={sido}>{sido}</option>
                        ))}
                    </select>
                    {data.si && locationData[data.si] && (
                        <select 
                            className={style.sigungu_button}
                            value={data.gu}
                            onChange={(e) => setData({...data, gu: e.target.value})}>
                            {locationData[data.si].map(sigungu => (
                                <option key={sigungu} value={sigungu}>{sigungu}</option>
                            ))}
                        </select>
                    )}
                </div>
            </div>
        )}

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
              <button type="button" onClick={handleBoard}>취소</button>
              <button type="submit">작성 완료</button>
            </div>
          </form>
              </div>
          )}
        </div>
    );
}

export default ShareToolWrite;