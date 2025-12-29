import { useState, useEffect } from 'react';
import axios from 'axios';

/**
 * 전국 지역 데이터를 가져오는 커스텀 훅 (TB_REGION 사용)
 * @returns {Object} locationData - 지역 데이터 객체 { "서울특별시": ["종로구", "중구", ...], ... }
 * @returns {boolean} loading - 로딩 상태
 * @returns {Error|null} error - 에러 객체
 */
export const useLocationData = () => {
    const [locationData, setLocationData] = useState({});
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchLocationData = async () => {
            setLoading(true);
            try {
                // 1. 시/도 목록 조회
                const provincesResponse = await axios.get('http://localhost:18880/api/common/provinces');
                const provinces = provincesResponse.data;
                
                // 2. 각 시/도별 시/군/구 조회
                const data = {};
                for (const province of provinces) {
                    try {
                        const citiesResponse = await axios.get(
                            `http://localhost:18880/api/common/cities/${province.regionCode}`
                        );
                        data[province.regionName] = citiesResponse.data.map(city => city.cityName);
                    } catch (cityError) {
                        console.error(`${province.regionName} 시/군/구 로드 실패:`, cityError);
                        data[province.regionName] = [];
                    }
                }
                
                setLocationData(data);
                setError(null);
            } catch (err) {
                console.error('지역 데이터 로드 실패:', err);
                setError(err);
                setLocationData({});
            } finally {
                setLoading(false);
            }
        };

        fetchLocationData();
    }, []);

    return { locationData, loading, error };
};