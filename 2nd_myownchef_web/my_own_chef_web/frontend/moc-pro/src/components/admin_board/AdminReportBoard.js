import React, { useEffect, useState } from "react";
import axios from "axios";
import styles from '../../css/admin_board/AdminReportBoard.module.css';
import PageNation from "../common/PageNation";
import { useNavigate, useSearchParams } from "react-router-dom";
import { ReportDetailModal } from "../info/ReportDetailModal";
import Modal from 'react-modal';

function AdminReportBoard(){
    const [reports, setReports] = useState([]);
    const [totalPage, setTotalPage] = useState(1);
    const navigate = useNavigate();
    const [searchParams] = useSearchParams();
    const currentPage = searchParams.get('page');
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [selectedReport, setSelectedReport] = useState(null);
    const [loading, setLoading] = useState(true);

    const handlePageChange = (newPage) => {
        navigate(`/manage/report?page=${newPage}`);
    };

    const deleteReport = async (report_id) => {
        const result = window.confirm('정말 삭제하시겠습니까?');

        if(result){
            try {
                const response = await axios.delete(`http://localhost:18880/api/report/admin/${report_id}`);
                
                if (response.data.success) {
                    alert('신고가 삭제되었습니다.');
                    window.location.reload();
                } else {
                    alert('신고 삭제 실패: ' + response.data.message);
                }
            } catch (error) {
                console.error('신고 삭제 실패:', error);
                if (error.response?.status === 403) {
                    alert('관리자 권한이 필요합니다.');
                } else {
                    alert('신고 삭제 실패');
                }
            }
        }
    };

    const viewDetails = (report_id) => {
        // axios 영역
        // report_id로 상세 신고 내용 조회
        const report = reports.find(r => r.reportId === report_id);
        setSelectedReport(report);
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
        setSelectedReport(null);
    };

    const moveToPost = (target_id, board_type) => {
        // board_type별로 다른 상세 페이지로 이동
        if(board_type === 'freeboard') {
            navigate(`/freeboard/detail/${target_id}`);
        } else if(board_type === 'notice') {
            navigate(`/notice/detail/${target_id}`);
        } else if(board_type === 'recipe') {
            navigate(`/recipe/detail/${target_id}`);
        } else if(board_type === 'sharetool') {
            navigate(`/sharetool/detail/${target_id}`);
        } else if(board_type === 'conv-review') {
            navigate(`/conv/review/detail/${target_id}`);
        } else if(board_type === 'conv-comb') {
            navigate(`/conv/recipe/detail/${target_id}`);
        } else if(board_type === 'withshopping') {
            navigate(`/withshopping?modal=detail&id=${target_id}`);
        }
    };

    useEffect(() => {
        const fetchReports = async () => {
            setLoading(true);
            try {
                const response = await axios.get('http://localhost:18880/api/report/admin/list', {
                    params: {
                        page: currentPage || 1
                    }
                });
                
                if (response.data.success) {
                    setReports(response.data.data.reports);
                    setTotalPage(response.data.data.totalPage);
                } else {
                    console.error('신고 목록 조회 실패:', response.data.message);
                    if (response.status === 403) {
                        alert('관리자 권한이 필요합니다.');
                        navigate('/');
                    }
                }
            } catch (error) {
                console.error('신고 목록 조회 실패:', error);
                if (error.response?.status === 403) {
                    alert('관리자 권한이 필요합니다.');
                    navigate('/');
                }
            } finally {
                setLoading(false);
            }
        };
        fetchReports();
    }, [currentPage, navigate]);

    return(
        <div className={styles.bodydiv}>
            <div className={styles.titlediv}>
                신고글 관리
            </div>
            <div className={styles.boarddiv}>
                {loading ? (
                    <div className={styles.loading}>신고 목록을 불러오는 중...</div>
                ) : (
                    <div className={styles.board_container}>
                        <div className={styles.boarditems_title}>번호</div>
                        <div className={styles.boarditems_title}>신고자</div>
                        <div className={styles.boarditems_title}>게시글</div>
                        <div className={styles.boarditems_title}>신고일</div>
                        <div className={styles.boarditems_title}>신고사유</div>
                        <div className={styles.boarditems_title_none}></div>
                        {reports.map((report, index) => (
                        <React.Fragment key={report.reportId}>
                            <div className={styles.boarditems}>{index + 1}</div>
                            <div className={styles.boarditems}>{report.reporterNickname}</div>
                            <div className={styles.boarditems_post_title} onClick={() => moveToPost(report.targetId, report.boardType)}>{report.targetTitle}</div>
                            <div className={styles.boarditems}>{report.createdAt}</div>
                            <div className={styles.boarditems}><button className={styles.detailbtn} onClick={() => viewDetails(report.reportId)}>상세보기</button></div>
                            <div className={styles.boarditems_none}><button onClick={() => deleteReport(report.reportId)}>삭제</button></div>
                        </React.Fragment>
                        ))}
                    </div>
                )}
                <div className={styles.bottomdiv}>
                    <div className={styles.pagenationdiv}>
                        <PageNation
                            size="middle"
                            currentPage={parseInt(currentPage) || 1}
                            totalPage={totalPage}
                            onPageChange={handlePageChange}
                        />
                    </div>
                </div>
            </div>
            <Modal 
                isOpen={isModalOpen} 
                onRequestClose={closeModal}
                className={styles.modal_content}
                overlayClassName={styles.modal_overlay}
            >
                <ReportDetailModal 
                    onClose={closeModal} 
                    reportData={selectedReport}
                />
            </Modal>
        </div>
    )
}

export default AdminReportBoard;