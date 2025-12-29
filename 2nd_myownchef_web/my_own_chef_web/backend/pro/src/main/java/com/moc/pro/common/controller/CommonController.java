package com.moc.pro.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Common Controller 인터페이스
 */
@RequestMapping("/api/common")
public interface CommonController {
    
    /**
     * 공통코드 조회
     * GET /api/common/codes/{masterCode}
     * @param masterCode 마스터코드 (A01, D01, F01 등)
     * @return 공통코드 목록
     */
    @GetMapping("/codes/{masterCode}")
    @ResponseBody
    ResponseEntity<List<Map<String, String>>> getCodesByMasterCode(@PathVariable String masterCode);
    
    /**
     * 시/도 목록 조회
     * GET /api/common/provinces
     * @return 시/도 목록
     */
    @GetMapping("/provinces")
    @ResponseBody
    ResponseEntity<List<Map<String, String>>> getProvinces();
    
    /**
     * 시/군/구 목록 조회
     * GET /api/common/cities/{regionCode}
     * @param regionCode 시/도 코드 (C0100, C0101 등)
     * @return 시/군/구 목록
     */
    @GetMapping("/cities/{regionCode}")
    @ResponseBody
    ResponseEntity<List<Map<String, String>>> getCitiesByRegion(@PathVariable String regionCode);
}
