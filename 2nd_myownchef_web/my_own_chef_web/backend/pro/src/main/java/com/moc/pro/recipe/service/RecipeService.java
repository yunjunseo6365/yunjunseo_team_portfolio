package com.moc.pro.recipe.service;

import com.moc.pro.recipe.vo.RecipeVO;
import com.moc.pro.recipe.vo.RecipeCommentVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 레시피 Service 인터페이스
 */
public interface RecipeService {
    
    /**
     * 레시피 목록 조회 (페이징)
     * @param page 페이지 번호 (1부터 시작)
     * @return Map { posts, totalPage, currentPage, totalCount }
     */
    Map<String, Object> getList(int page);
    
    /**
     * 레시피 검색 (페이징)
     * @param keyword 검색 키워드 (제목/내용)
     * @param ingredient 주재료
     * @param page 페이지 번호
     * @return Map { posts, totalPage, currentPage, totalCount, keyword, ingredient }
     */
    Map<String, Object> searchRecipes(String keyword, String ingredient, int page);
    
    /**
     * 레시피 상세 조회
     * @param recipeId 레시피 ID
     * @return Map { recipe, ingredients, orders, images }
     */
    Map<String, Object> getDetail(int recipeId);
    
    /**
     * 레시피 작성
     * @param recipe 레시피 기본 정보
     * @param ingredients 재료 목록 (JSON 문자열)
     * @param orders 조리 순서 (JSON 문자열)
     * @param images 이미지 파일 배열
     * @return 작성 성공 여부
     */
    boolean createRecipe(RecipeVO recipe, String ingredients, String orders, MultipartFile[] images);
    
    /**
     * 레시피 수정
     * @param recipe 수정할 레시피 정보
     * @param ingredients 재료 목록 (JSON 문자열)
     * @param orders 조리 순서 (JSON 문자열)
     * @param images 이미지 파일 배열 (새로 업로드)
     * @param existingImageUrls 유지할 기존 이미지 URL (JSON)
     * @return 수정 성공 여부
     */
    boolean updateRecipe(RecipeVO recipe, String ingredients, String orders, MultipartFile[] images, String existingImageUrls);
    
    /**
     * 레시피 삭제
     * @param recipeId 레시피 ID
     * @return 삭제 성공 여부
     */
    boolean deleteRecipe(int recipeId);
    
    /**
     * 작성자 확인
     * @param recipeId 레시피 ID
     * @param userId 사용자 ID
     * @return 작성자가 맞으면 true
     */
    boolean checkAuthor(int recipeId, String userId);
    
    /**
     * 댓글 목록 조회
     * @param recipeId 레시피 ID
     * @return 댓글 목록
     */
    List<RecipeCommentVO> getComments(int recipeId);
    
    /**
     * 댓글 작성
     * @param comment 댓글 정보
     * @return 작성 성공 여부
     */
    boolean createComment(RecipeCommentVO comment);
    
    /**
     * 댓글 삭제
     * @param commentId 댓글 ID
     * @param userId 사용자 ID
     * @return 삭제 성공 여부
     */
    boolean deleteComment(int commentId, String userId);
}
