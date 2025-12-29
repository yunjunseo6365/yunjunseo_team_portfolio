package com.moc.pro.common.service;

import com.moc.pro.common.vo.CommonCodeVO;
import com.moc.pro.common.vo.RegionVO;
import java.util.List;

/**
 * Common Service 인터페이스
 */
public interface CommonService {
    
    // ===== 공통코드 =====
    
    /**
     * 마스터코드로 공통코드 목록 조회
     * @param masterCode 마스터코드 (A01, D01, F01 등)
     * @return 공통코드 목록
     */
    List<CommonCodeVO> getCodesByMasterCode(String masterCode);
    
    // ===== 지역 =====
    
    /**
     * 시/도 목록 조회
     * @return 시/도 목록
     */
    List<RegionVO> getProvinces();
    
    /**
     * 시/군/구 목록 조회
     * @param regionCode 시/도 코드 (C0100, C0101 등)
     * @return 시/군/구 목록
     */
    List<RegionVO> getCitiesByRegion(String regionCode);
}
