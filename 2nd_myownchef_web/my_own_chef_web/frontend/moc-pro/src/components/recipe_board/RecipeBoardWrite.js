import React, { useState, useEffect } from "react";
import ImageUploadBoxes from "../common/ImageUploadBoxes";
import style from '../../css/recipe_board/RecipeBoardWrite.module.css'
import { useNavigate, useParams } from 'react-router-dom';
import { getUserId } from '../common/authUtils';
import axios from 'axios';


function RecipeBoardWrite() {
    const navigate = useNavigate();
    const { postid } = useParams();
    const [isEditMode, setIsEditMode] = useState(false);
    const [loading, setLoading] = useState(false);
    const [ingredients, setIngredients] = useState(['']); // 상세 준비 재료추가버튼용
    const [recipes, setRecipes] = useState(['']); // 레시피 추가버튼용
    const [text, setText] = useState(''); // 팁 & 소감
    const [images, setImages] = useState([]); // 이미지 파일 배열 (ImageUploadBoxes에서 받음)
    const [existingImages, setExistingImages] = useState([]); // 기존 이미지 URL
    const [data, setData] = useState({
      userid: '',
      cookname: '',
      mainingredients: '',
    });

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
            
            const fetchDetail = async () => {
                setLoading(true);
                try {
                    const response = await axios.get(`http://localhost:18880/api/recipe/${postid}`);
                    
                    if (response.data.success) {
                        const { recipe, ingredients: fetchedIngredients, orders, images: fetchedImages } = response.data.data;
                        
                        setData({
                            userid: recipe.userId,
                            cookname: recipe.recipeTitle,
                            mainingredients: recipe.recipeMainIngredient
                        });
                        
                        setIngredients(
                            fetchedIngredients.length > 0 
                                ? fetchedIngredients.map(item => item.recipeDetailIngredientContent)
                                : ['']
                        );
                        
                        setRecipes(
                            orders.length > 0 
                                ? orders.map(item => item.recipeOrderContent)
                                : ['']
                        );
                        
                        setText(recipe.recipeContent || '');
                        
                        // 기존 이미지 URL 설정 (noImage 제외)
                        if (fetchedImages && fetchedImages.length > 0) {
                            const imageUrls = fetchedImages
                                .filter(img => !img.recipeImageUrl.includes('placehold.co'))
                                .map(img => img.recipeImageUrl);
                            setExistingImages(imageUrls);
                        }
                    }
                } catch (error) {
                    console.error('레시피 조회 실패:', error);
                    alert('레시피를 불러오는데 실패했습니다.');
                    navigate('/recipe');
                } finally {
                    setLoading(false);
                }
            };
            
            fetchDetail();
        }
    }, [postid, navigate])
    
    // 추가 버튼 클릭시 발생하는 이벤트
    const handleAddIngredients = () => {
      if(ingredients.length >= 10){
        alert('최대 10개까지 추가할 수 있습니다.');
        return;
      }
      setIngredients([...ingredients, '']);
    };

    // 추가 버튼 클릭시 발생하는 이벤트
    const handleAddRecipes = () =>{
      if(recipes.length >=10){
        alert('최대 10개까지 추가할 수 있습니다.');
        return;
      }
      setRecipes([...recipes, '']);
    }

    const handleChangeIngredients = (index, value) => {
      const updated = [...ingredients];
      updated[index] = value;
      setIngredients(updated);
    };

    const handleChangeRecipes = (index, value) => {
      const updated = [...recipes];
      updated[index] = value;
      setRecipes(updated);
    };

    const handleCancle = () =>{
      navigate('/recipe');
    }

    // 이미지 변경 핸들러
    const handleImagesChange = (imageData) => {
        setImages(imageData);
    };

    const handleSubmit = async (e) => {
      e.preventDefault();
      
      // 필수 입력 검증
      if (!data.cookname.trim()) {
        alert('요리 이름을 입력해주세요.');
        return;
      }
      
      if (!data.mainingredients.trim()) {
        alert('핵심 재료를 입력해주세요.');
        return;
      }
      
      // FormData 생성
      const formData = new FormData();
      formData.append('userId', data.userid);
      formData.append('title', data.cookname);
      formData.append('mainIngredient', data.mainingredients);
      formData.append('content', text);
      
      // 재료 목록 JSON 문자열로 변환
      const ingredientList = ingredients.filter(item => item.trim() !== '').map(content => ({ content }));
      if (ingredientList.length > 0) {
        formData.append('ingredients', JSON.stringify(ingredientList));
      }
      
      // 조리 순서 JSON 문자열로 변환
      const orderList = recipes.filter(item => item.trim() !== '').map(content => ({ content }));
      if (orderList.length > 0) {
        formData.append('orders', JSON.stringify(orderList));
      }
      
      // 이미지 처리
      const newFiles = [];
      const keepUrls = existingImages;
      
      images.forEach((item) => {
          if (item) {
              if (item.type === 'file') {
                  newFiles.push(item.data);
              } else if (item.type === 'url') {
                  keepUrls.push(item.data);
              }
          }
      });

      console.log('새 파일들:', newFiles);
      console.log('기존 이미지 URL들:', keepUrls);
      
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
          console.log(newFiles);
          // axios 영역 - UPDATE
          const response = await axios.put(`http://localhost:18880/api/recipe/${postid}`, formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          });
          
          if (response.data.success) {
            alert('레시피가 수정되었습니다.');
            navigate(`/recipe/detail/${postid}`);
          } else {
            alert(response.data.message || '수정에 실패했습니다.');
          }
        } else {
          // axios 영역 - INSERT
          const response = await axios.post('http://localhost:18880/api/recipe', formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          });
          
          if (response.data.success) {
            alert('레시피가 작성되었습니다.');
            navigate('/recipe');
          } else {
            alert(response.data.message || '작성에 실패했습니다.');
          }
        }
      } catch (error) {
        console.error('제출 실패:', error);
        alert('레시피 제출 중 오류가 발생했습니다.');
      }
    }

    return(
        <div className={style.recipe_board_detail}>
          <div className={style.titlediv}>
             {isEditMode ? '레시피 수정하기' : '레시피 작성하기'}
           </div>
          {loading ? (
              <div className={style.loading}>레시피를 불러오는 중...</div>
          ) : (
              <div className={style.recipe_board_container}>
              <form onSubmit={handleSubmit}>
            <div>
              <div className={style.recipe_title}>요리이름</div>
              <input 
                className={style.input_title} 
                type="text" 
                placeholder="요리이름을 입력해주세요"
                value={data.cookname}
                onChange={(e) => setData({...data, cookname: e.target.value})}
                required
              />
            </div>
            <div>
              <div className={style.recipe_title}>핵심재료</div>
              <input 
                className={style.input_title} 
                type="text" 
                placeholder="핵심재료를 입력해주세요"
                value={data.mainingredients}
                onChange={(e) => setData({...data, mainingredients: e.target.value})}
                required
              />
            </div>
            <div>
              <div className={style.recipe_title}>상세 준비 재료</div>
               {ingredients.map((value, index) => (
              <input
                className={style.input_title}
                key={index}
                type="text"
                value={value}
                onChange={(e) => handleChangeIngredients(index, e.target.value)}
                placeholder="예)1.떡 100g(최대 150자)"
                maxLength={150}
              />
            ))}
              <div className={style.addbtn}>
               <button type="button" onClick={handleAddIngredients}>추가(최대10개까지)</button>
              </div>
            </div>
            <div>
              <div className={style.recipe_title}>레시피</div>
              {recipes.map((value, index) => (
              <input
                className={style.input_title}
                key={index}
                type="text"
                name={`recipe${index + 1}`}
                value={value}
                onChange={(e) => handleChangeRecipes(index, e.target.value)}
                placeholder="예)1.떡 100g(최대 150자)"
                maxLength={150}
              />
            ))}
             <div className={style.addbtn}>
              <button type="button" onClick={handleAddRecipes}>추가(최대10개까지)</button>
             </div>
            </div>
            <div>
              <ImageUploadBoxes 
                onImagesChange={handleImagesChange}
                initialImages={existingImages}
              />
            </div>
            <div>
              <div className={style.recipe_title}>팁 & 소감</div>
              <textarea 
                className={style.input_content}
                value={text}
                onChange={(e) => setText(e.target.value)}
                maxLength={500}
                rows={5}
                cols={50}
                placeholder="팁이나 소감을 적어주세요.(최대 500자)"/>
            </div>
            <div className={style.formbtn}>
              <button type="button" onClick={handleCancle}>취소</button>
              <button type="submit">작성 완료</button>
            </div>
          </form>
              </div>
          )}
        </div>
    );
}

export default RecipeBoardWrite;