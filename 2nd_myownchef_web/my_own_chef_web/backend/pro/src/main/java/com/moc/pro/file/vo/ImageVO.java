package com.moc.pro.file.vo;

/**
 * 이미지 업로드 결과 VO
 * FileUploadService의 반환 객체
 */
public class ImageVO {
    
    private String url;   // 접근 가능한 URL (http://localhost:18880/uploads/user/xxx.jpg)
    private String path;  // 실제 파일 경로 (C:/uploads/moc/user/xxx.jpg)
    
    /**
     * 생성자
     * @param url 이미지 URL
     * @param path 파일 경로
     */
    public ImageVO(String url, String path) {
        this.url = url;
        this.path = path;
    }
    
    // Getter
    public String getUrl() {
        return url;
    }
    
    public String getPath() {
        return path;
    }
    
    // Setter
    public void setUrl(String url) {
        this.url = url;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    @Override
    public String toString() {
        return "ImageVO{" +
                "url='" + url + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
