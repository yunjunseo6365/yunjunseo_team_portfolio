package com.moc.pro.mypage.vo;

/**
 * 마이페이지 게시글 VO
 * 내가 쓴 글, 저장한 글 공통 사용
 */
public class MyPagePostVO {
    
    private int id;           // 게시글 ID
    private String title;     // 제목
    private String boardType; // 게시판 타입 (recipe, sharetool, conv-review, conv-recipe, freeboard, withshopping, notice)
    private String typeName;  // 게시판 타입명 한글 (레시피, 요리나눔, 편의점리뷰, 편의점조합, 자유게시판, 같이쇼핑, 공지사항)
    private String createdDate;      // 작성일/저장일 (YYYY-MM-DD 형식)
    
    /**
     * 기본 생성자
     */
    public MyPagePostVO() {}
    
    /**
     * 전체 필드 생성자
     */
    public MyPagePostVO(int id, String title, String boardType, String typeName, String createdDate) {
        this.id = id;
        this.title = title;
        this.boardType = boardType;
        this.typeName = typeName;
        this.createdDate = createdDate;
    }
    
    // Getter/Setter
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getBoardType() {
        return boardType;
    }
    
    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }
    
    public String getTypeName() {
        return typeName;
    }
    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    public String getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    
    @Override
    public String toString() {
        return "MyPagePostVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", boardType='" + boardType + '\'' +
                ", typeName='" + typeName + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
