package com.moc.pro.common.controller;

import com.moc.pro.common.service.CommonService;
import com.moc.pro.common.vo.CommonCodeVO;
import com.moc.pro.common.vo.RegionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Common Controller 구현체
 */
@Controller
public class CommonControllerImpl implements CommonController {
    
    @Autowired
    private CommonService commonService;
    
    /**
     * 공통코드 조회
     * GET /api/common/codes/{masterCode}
     */
    @Override
    public ResponseEntity<List<Map<String, String>>> getCodesByMasterCode(@PathVariable String masterCode) {
        List<CommonCodeVO> codes = commonService.getCodesByMasterCode(masterCode);
        
        List<Map<String, String>> result = codes.stream().map(code -> {
            Map<String, String> map = new HashMap<>();
            map.put("code", code.getCode());
            map.put("codeName", code.getCodeName());
            return map;
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(result);
    }
    
    /**
     * 시/도 목록 조회
     * GET /api/common/provinces
     */
    @Override
    public ResponseEntity<List<Map<String, String>>> getProvinces() {
        List<RegionVO> provinces = commonService.getProvinces();
        
        List<Map<String, String>> result = provinces.stream().map(province -> {
            Map<String, String> map = new HashMap<>();
            map.put("regionCode", province.getRegionCode());
            map.put("regionName", province.getRegionName());
            return map;
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(result);
    }
    
    /**
     * 시/군/구 목록 조회
     * GET /api/common/cities/{regionCode}
     */
    @Override
    public ResponseEntity<List<Map<String, String>>> getCitiesByRegion(@PathVariable String regionCode) {
        List<RegionVO> cities = commonService.getCitiesByRegion(regionCode);
        
        List<Map<String, String>> result = cities.stream().map(city -> {
            Map<String, String> map = new HashMap<>();
            map.put("cityCode", city.getCityCode());
            map.put("cityName", city.getCityName());
            return map;
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok(result);
    }
}
