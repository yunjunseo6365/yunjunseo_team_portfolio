package com.moc.pro.report.dao;

import com.moc.pro.report.vo.AdminReportVO;
import com.moc.pro.report.vo.ReportVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 신고 DAO 구현체
 */
@Repository
public class ReportDAOImpl implements ReportDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.moc.pro.report.";
    
    @Override
    public int insertReport(ReportVO report) {
        return sqlSession.insert(NAMESPACE + "insertReport", report);
    }
    
    @Override
    public int checkReport(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + "checkReport", params);
    }
    
    @Override
    public List<AdminReportVO> selectAdminReports(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + "selectAdminReports", params);
    }
    
    @Override
    public int selectAdminReportsTotalCount() {
        return sqlSession.selectOne(NAMESPACE + "selectAdminReportsTotalCount");
    }
    
    @Override
    public AdminReportVO selectAdminReportById(int reportId) {
        return sqlSession.selectOne(NAMESPACE + "selectAdminReportById", reportId);
    }
    
    @Override
    public int deleteReport(int reportId) {
        return sqlSession.delete(NAMESPACE + "deleteReport", reportId);
    }
}
