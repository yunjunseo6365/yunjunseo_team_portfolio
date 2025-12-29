package com.moc.pro.common.vo;

/**
 * 지역 VO
 */
public class RegionVO {
    private String cityCode;
    private String masterCode;
    private String regionCode;
    private String regionName;
    private String cityName;
    
    // Getter/Setter
    public String getCityCode() {
        return cityCode;
    }
    
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    
    public String getMasterCode() {
        return masterCode;
    }
    
    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }
    
    public String getRegionCode() {
        return regionCode;
    }
    
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
    
    public String getRegionName() {
        return regionName;
    }
    
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    
    public String getCityName() {
        return cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
