import React, { useState, useEffect } from 'react';
import style from '../../css/common/ImageUploadBoxes.module.css'

function ImageUploadBoxes({ onImagesChange, initialImages = [] }) {  
  // 이미지 추가용 빈탭 생성 useState
  const [images, setImages] = useState([null, null, null, null]);
  const [imageData, setImageData] = useState([]); // { type: 'url'|'file', data: string|File }

  // 초기 이미지 로드
  useEffect(() => {
    if (initialImages && initialImages.length > 0) {
      const newImages = [null, null, null, null];
      const newImageData = [];
      
      initialImages.forEach((url, index) => {
        if (index < 4 && url) {
          newImages[index] = url;
          newImageData[index] = { type: 'url', data: url };
        }
      });
      
      setImages(newImages);
      setImageData(newImageData);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [initialImages]);

  const handleFileChange = (index, event) => {
    const file = event.target.files[0];
    
    if (file && file.type.startsWith('image/')) {
      // 파일 크기 체크 (10MB)
      if (file.size > 10 * 1024 * 1024) {
        alert('파일 크기는 10MB 이하여야 합니다.');
        return;
      }
      
      // 미리보기 업데이트
      const newImages = [...images];
      newImages[index] = URL.createObjectURL(file);
      setImages(newImages);
      
      // 이미지 데이터 업데이트
      const newImageData = [...imageData];
      newImageData[index] = { type: 'file', data: file };
      setImageData(newImageData);
      
      // 부모 컴포넌트로 전달
      if (onImagesChange) {
        onImagesChange(newImageData);
      }
    } else {
      alert('이미지 파일만 업로드 가능합니다.');
    }
  };

  const handleRemove = (index) => {
    const newImages = [...images];
    newImages[index] = null;
    setImages(newImages);
    
    const newImageData = [...imageData];
    newImageData[index] = null;
    setImageData(newImageData);
    
    // 부모 컴포넌트로 전달
    if (onImagesChange) {
      onImagesChange(newImageData);
    }
  };

  // cleanup: 메모리 해제
  useEffect(() => {
    return () => {
      images.forEach(img => {
        if (img && typeof img === 'string' && img.startsWith('blob:')) {
          URL.revokeObjectURL(img);
        }
      });
    };
  }, [images]);

  return (
  <div>
    <div className={style.text_title}>사진 넣기(클릭하여 추가)</div>
    <div className={style.image_upload_div}>
      {images.map((img, index) => (
        <div key={index} className={style.image_area}>
          {img ? (
            <>
              <img src={img} alt={`preview-${index}`} className={style.input_image} />
              <button 
                type="button" 
                onClick={() => handleRemove(index)}
                className={style.remove_btn}
                title="이미지 삭제">
                ✕
              </button>
            </>
          ) : (
            <label className={style.label}>
              사진
              <input
                type="file"
                accept="image/*"
                onChange={(e) => handleFileChange(index, e)} 
                className={style.image} />
            </label>
          )}
        </div>
      ))}
    </div>
  </div>
  );
}

export default ImageUploadBoxes;