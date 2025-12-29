import React, { useState, useEffect } from "react";
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import ImageUploadBoxes from "../common/ImageUploadBoxes";
import style from '../../css/conv_board/ConvenienceCombWrite.module.css';
import { getUserId } from '../common/authUtils';


function ConvenienceCombWrite() {
    const navigate = useNavigate();
    const { postid } = useParams();
    const [isEditMode, setIsEditMode] = useState(false);
    const [loading, setLoading] = useState(false);
    const [categoryOptions, setCategoryOptions] = useState([]);
    const [products, setProducts] = useState(['']); // 상세 준비 제품
    const [recipes, setRecipes] = useState(['']); // 레시피
    const [images, setImages] = useState([]); // 새 업로드 이미지
    const [existingImages, setExistingImages] = useState([]); // 기존 이미지 URL
    const [data, setData] = useState({
        userid: '',
        cookname: '',
        mainproduct: '',
        category: '',
        tip: ''
    });

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
                console.error('카테고리 로드 실패:', error);
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
                    const response = await axios.get(`http://localhost:18880/api/conv-recipe/${postid}`);
                    if (response.data.success) {
                        const { recipe, products, orders, images: fetchedImages } = response.data.data;
                        
                        setData({
                            userid: recipe.userId,
                            cookname: recipe.convRecipeTitle,
                            mainproduct: recipe.convRecipeMainProduct,
                            category: recipe.convRecipeCategory,
                            tip: recipe.convRecipeTip
                        });
                        
                        setProducts(products.length > 0 
                            ? products.map(p => p.convRecipeProductContent) 
                            : ['']
                        );
                        
                        setRecipes(orders.length > 0 
                            ? orders.map(o => o.convRecipeOrderContent) 
                            : ['']
                        );
                        
                        // 기존 이미지 URL 설정 (noImage 제외)
                        if (fetchedImages && fetchedImages.length > 0) {
                            const imageUrls = fetchedImages
                                .filter(img => !img.convRecipeImageUrl.includes('placehold.co'))
                                .map(img => img.convRecipeImageUrl);
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

    // 추가 버튼 클릭시 발생하는 이벤트
    const handleAddProducts = () => {
      if(products.length >= 10){
        alert('최대 10개까지 추가할 수 있습니다.');
        return;
      }
      setProducts([...products, '']);
    };
    const handleAddRecipes = () =>{
      if(recipes.length >=10){
        alert('최대 10개까지 추가할 수 있습니다.');
        return;
      }
      setRecipes([...recipes, '']);
    }

    const handleChangeProducts = (index, value) => {
      const updated = [...products];
      updated[index] = value;
      setProducts(updated);
    };

    const handleChangeRecipes = (index, value) => {
      const updated = [...recipes];
      updated[index] = value;
      setRecipes(updated);
    };

    const handleCancel = () => {
      navigate(`/conv/recipe`);
    }

    // 이미지 변경 핸들러
    const handleImagesChange = (imageData) => {
        setImages(imageData);
    };

    const handleSubmit = async (e) => {
      e.preventDefault();
      
      const formData = new FormData();
      formData.append('userId', data.userid);
      formData.append('title', data.cookname);
      formData.append('mainProduct', data.mainproduct);
      formData.append('category', data.category);
      formData.append('tip', data.tip);
      
      // 제품 목록 JSON
      const productList = products.filter(item => item.trim() !== '')
                                  .map(content => ({ content }));
      if (productList.length > 0) {
          formData.append('products', JSON.stringify(productList));
      }
      
      // 레시피 목록 JSON
      const orderList = recipes.filter(item => item.trim() !== '')
                               .map(content => ({ content }));
      if (orderList.length > 0) {
          formData.append('orders', JSON.stringify(orderList));
      }
      
      // 이미지 처리
      const newFiles = [];
      const keepUrls = [];
      
      images.forEach((item) => {
          if (item) {
              if (item.type === 'file') {
                  newFiles.push(item.data); // 새 파일
              } else if (item.type === 'url') {
                  keepUrls.push(item.data); // 기존 URL
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
              const response = await axios.put(`http://localhost:18880/api/conv-recipe/${postid}`, formData, {
                  headers: { 'Content-Type': 'multipart/form-data' }
              });
              
              if (response.data.success) {
                  alert('편의점 조합이 수정되었습니다.');
                  navigate(`/conv/recipe/detail/${postid}`);
              } else {
                  alert(response.data.message || '수정에 실패했습니다.');
              }
          } else {
              // 작성
              const response = await axios.post('http://localhost:18880/api/conv-recipe', formData, {
                  headers: { 'Content-Type': 'multipart/form-data' }
              });
              
              if (response.data.success) {
                  alert('편의점 조합이 작성되었습니다.');
                  navigate('/conv/recipe');
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
        <div className={style.recipe_board_detail}>
          <div className={style.titlediv}>
             {isEditMode ? '편의점 파먹기 수정' : '편의점 파먹기 글 작성'}
           </div>
          {loading ? (
              <div className={style.loading}>편의점 조합을 불러오는 중...</div>
          ) : (
              <div className={style.recipe_board_container}>
            <div className={style.recipe_filter_group}>
              {categoryOptions.map(categorysName => (
                  <button type="button" key={categorysName} className={`${style.filter_button} ${data.category === categorysName ? style.active : ''}`} 
                    onClick={() => setData({...data, category: categorysName})}>{categorysName}</button>
              ))}
            </div>
          <form onSubmit={handleSubmit}>
            <div>
              <div className={style.recipe_title}>요리이름</div>
              <input 
                className={style.input_title} 
                type="text" 
                placeholder="요리이름을 입력해주세요 (예: 불닭떡볶이)"
                value={data.cookname}
                onChange={(e) => setData({...data, cookname: e.target.value})}
                required
              />
            </div>
            <div>
              <div className={style.recipe_title}>핵심재품</div>
              <input 
                className={style.input_title} 
                type="text" 
                placeholder="핵심재품명을 입력해주세요 (예: 불닭볶음면)"
                value={data.mainproduct}
                onChange={(e) => setData({...data, mainproduct: e.target.value})}
                required
              />
            </div>
            <div>
              <div className={style.recipe_title}>상세 준비 제품</div>
               {products.map((value, index) => (
              <input
                className={style.input_title}
                key={index}
                type="text"
                value={value}
                onChange={(e) => handleChangeProducts(index, e.target.value)}
                placeholder="예)1.떡볶이 2.불닭볶음면(최대 150자)"
                maxLength={150}
              />
            ))}
              <div className={style.add_btn}>
               <button type="button" className={style.add_button} onClick={handleAddProducts}>추가(최대10개까지)</button>
              </div>
            </div>
            <div>
              <div className={style.recipe_title}>레시피</div>
              {recipes.map((value, index) => (
              <input
                className={style.input_title}
                key={index}
                type="text"
                value={value}
                onChange={(e) => handleChangeRecipes(index, e.target.value)}
                placeholder="예)1.불닭볶음면을 조리해서 준비해주세요(최대 150자)"
                maxLength={150}
              />
            ))}
             <div className={style.add_btn}>
              <button type="button" className={style.add_button} onClick={handleAddRecipes}>추가(최대10개까지)</button>
             </div>
            </div>
            <div>
              <ImageUploadBoxes 
                onImagesChange={handleImagesChange} 
                initialImages={existingImages}
              />
            </div>
            <div>
              <div className={style.recipe_title}>팁 & 후기</div>
              <textarea 
                className={style.input_content}
                value={data.tip}
                onChange={(e) => setData({...data, tip: e.target.value})}
                maxLength={500}
                rows={5}
                cols={50}
                placeholder="팁이나 후기를 적어주세요.(최대 500자)"/>
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

export default ConvenienceCombWrite;