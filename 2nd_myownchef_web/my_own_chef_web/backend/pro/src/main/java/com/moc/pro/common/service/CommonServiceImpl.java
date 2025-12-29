package com.moc.pro.common.service;

import com.moc.pro.common.dao.CommonDAO;
import com.moc.pro.common.vo.CommonCodeVO;
import com.moc.pro.common.vo.RegionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Common Service 구현체
 */
@Service
public class CommonServiceImpl implements CommonService {
    
    @Autowired
    private CommonDAO commonDAO;
    
    // ===== 공통코드 =====
    
    @Override
    public List<CommonCodeVO> getCodesByMasterCode(String masterCode) {
        return commonDAO.selectByMasterCode(masterCode);
    }
    
    // ===== 지역 =====
    
    @Override
    public List<RegionVO> getProvinces() {
        return commonDAO.selectProvinces();
    }
    
    @Override
    public List<RegionVO> getCitiesByRegion(String regionCode) {
        return commonDAO.selectCitiesByRegion(regionCode);
    }
}
