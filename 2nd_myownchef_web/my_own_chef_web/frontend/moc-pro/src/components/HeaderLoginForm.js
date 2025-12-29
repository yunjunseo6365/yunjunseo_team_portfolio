import style from '../css/HeaderLogin.module.css';
import React, {useState, useEffect, useRef} from "react";
import { Link, useNavigate } from 'react-router-dom';
import { FaFlag, FaBell, FaRegTrashCan } from "react-icons/fa6";
import { FiMail } from "react-icons/fi";
import Modal from 'react-modal';
import { ChatModal } from './info/ChatModal';
import { logout, getUserId } from './common/authUtils';
import axios from 'axios';

function HeaderLoginForm() {
  const [isCommentVisible, setIsCommentVisible] = useState(false);
  const [isChatVisible, setIsChatVisible] = useState(false);
  const commentRef = useRef(null);
  const chatRef = useRef(null);
  const [modalType, setModalType] = useState(null);
  const [comments, setComments] = useState([]);
  const [chatRooms, setChatRooms] = useState([]);
  const [selectedRoom, setSelectedRoom] = useState(null);
  const [deletedRoomIds, setDeletedRoomIds] = useState([]);
  const [unreadCommentCount, setUnreadCommentCount] = useState(0);
  const [unreadChatCount, setUnreadChatCount] = useState(0);
  const [loading, setLoading] = useState(true);
  
  // 사용자 정보 상태 추가
  const [userInfo, setUserInfo] = useState({
    userName: '',
    userNickname: '',
    userImageUrl: '' // 백엔드에서 기본값 제공
  });
  
  const openChatModal = async (room) => {
    // 현재 사용자 ID 가져오기
    const currentUserId = await getUserId();
    
    // props 매핑: API 응답 필드명 → ChatModal이 기대하는 필드명
    const mappedRoom = {
      ...room,
      roomId: room.chatRoomId,  // chatRoomId → roomId
      isAuthor: currentUserId === room.ownerId,  // ownerId 비교
      partnerNickname: currentUserId === room.ownerId 
        ? room.participantNickname 
        : room.ownerNickname
    };
    
    setSelectedRoom(mappedRoom);
    setModalType('chat');
    setIsChatVisible(false);
    
    // 채팅창 열면서 해당 방의 hasUnread 즉시 false로 변경 (UI 반응성)
    setChatRooms(prev => prev.map(r => 
        r.chatRoomId === room.chatRoomId 
            ? {...r, hasUnread: false} 
            : r
    ));
    
    // unreadChatCount 재계산
    const newUnreadCount = chatRooms.filter(r => 
        r.chatRoomId !== room.chatRoomId && r.hasUnread
    ).length;
    setUnreadChatCount(newUnreadCount);
  };
  
  const closeModal = () => {
    setModalType(null);
    setSelectedRoom(null);
  };

  const handleDeleteRoom = (chatRoomId) => {
    setDeletedRoomIds(prev => [...prev, chatRoomId]);
  };

    const handleDeleteComments = async () => {
        if (window.confirm('모든 댓글 알림을 삭제하시겠습니까?')) {
            try {
                const response = await axios.delete('http://localhost:18880/api/notifications/comments');
                if (response.data.success) {
                    setComments([]);
                    setUnreadCommentCount(0);
                } else {
                    alert(response.data.message || '알림 삭제 실패');
                }
            } catch (error) {
                console.error('댓글 알림 삭제 실패:', error);
                alert('알림 삭제 중 오류가 발생했습니다.');
            }
        }
    };  const handleCommentClick = (comment) => {
    // 게시글 타입별 라우팅
    const routeMap = {
      'recipe': `/recipe/detail/${comment.postId}`,
      'freeboard': `/freeboard/detail/${comment.postId}`,
      'sharetool': `/sharetool/detail/${comment.postId}`,
      'conv-recipe': `/conv/recipe/detail/${comment.postId}`,
      'conv-review': `/conv/review/detail/${comment.postId}`,
    };
    
    if(routeMap[comment.postType]) {
      navigate(routeMap[comment.postType]);
      setIsCommentVisible(false);
    }
  };

    const toggleComment = async () => {
        const willBeVisible = !isCommentVisible;
        setIsCommentVisible(willBeVisible);
        setIsChatVisible(false);
        
        // 드롭다운 열 때 모든 댓글 읽음 처리
        if (willBeVisible && unreadCommentCount > 0) {
            try {
                await axios.put('http://localhost:18880/api/notifications/comments/read-all');
                
                // 로컬 상태 업데이트
                setComments(prev => prev.map(c => ({...c, isRead: true})));
                setUnreadCommentCount(0);
            } catch (error) {
                console.error('댓글 읽음 처리 실패:', error);
            }
        }
    };  const toggleChat = () => {
    const willBeVisible = !isChatVisible;
    setIsChatVisible(willBeVisible);
    setIsCommentVisible(false);
    
    // 읽음 처리는 실제 채팅창을 열 때만 수행 (ChatModal에서 처리)
  };

  const navigate = useNavigate();

  // 삭제되지 않은 채팅방만 필터링
  const visibleChatRooms = chatRooms.filter(room => !deletedRoomIds.includes(room.chatRoomId));

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try {
                const userId = await getUserId();
                
                if (userId) {
                    // 1. 사용자 정보 불러오기
                    const userResponse = await axios.get(`http://localhost:18880/api/user/${userId}`);
                    const user = userResponse.data.data;
                    
                    setUserInfo({
                        userName: user.userName || '',
                        userNickname: user.userNickname || '',
                        userImageUrl: user.userImageUrl || ''
                    });
                    
                    // 2. 댓글 알림 목록 조회
                    const commentResponse = await axios.get('http://localhost:18880/api/notifications/comments');
                    if (commentResponse.data.success) {
                        setComments(commentResponse.data.data);
                    }
                    
                    // 3. 읽지 않은 댓글 알림 개수 조회
                    const countResponse = await axios.get('http://localhost:18880/api/notifications/comments/unread-count');
                    if (countResponse.data.success) {
                        setUnreadCommentCount(countResponse.data.data.count);
                    }
                    
                    // 4. 채팅방 목록 조회
                    const chatResponse = await axios.get('http://localhost:18880/api/chat/rooms');
                    
                    if (chatResponse.data.success) {
                        const rooms = chatResponse.data.data.filter(room => room.roomStatus === 'ACTIVE');
                        setChatRooms(rooms);
                        
                        // 5. 읽지 않은 채팅방 개수 계산
                        const unreadChats = rooms.filter(r => r.hasUnread).length;
                        setUnreadChatCount(unreadChats);
                    }
                }
            } catch (error) {
                console.error('데이터 불러오기 실패:', error);
            } finally {
                setLoading(false);
            }
        };
        
        fetchData();
    }, []);  useEffect(() => {
    const handleClickOutside = (event) => {
        if(commentRef.current && !commentRef.current.contains(event.target)){
            setIsCommentVisible(false);
        }
        if(chatRef.current && !chatRef.current.contains(event.target)){
            setIsChatVisible(false);
        }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);

  const handleLogout = async () => {
    await logout();
    navigate('/');
  };

  const handleMypage = () => {
    navigate('/mypage');
  };

    if (loading) {
        return (
            <div className={style.header_container}>
                <div className={style.left_group}>
                    <h2>
                        <Link className={style.nav_link}>
                            <FaFlag/>
                            <Link to="/notice">공지사항</Link>
                        </Link>
                    </h2>
                </div>
                <div className={style.right_group}>
                    <div className={style.loading}>로딩 중...</div>
                </div>
            </div>
        );
    }

    return(
    <div className={style.header_container}>
        <div className={style.left_group}>
            <h2>
                <Link className={style.nav_link}>
                    <FaFlag/>
                    <Link to="/notice">공지사항</Link>
                </Link>
            </h2>
        </div>
        <div className={style.right_group}>
            <div className={style.panelWrapper}>
                <div className={style.logoutbtn} onClick={handleLogout}>
                    로그아웃
                </div>
                <div ref={commentRef}>
                    {/* 댓글 알림 아이콘 */}
                    <div className={style.icon_wrapper}>
                        <FaBell className={style.fabell}
                        onClick={toggleComment}
                        />
                        {unreadCommentCount > 0 && (
                            <span className={style.badge}></span>
                        )}
                    </div>

                    {isCommentVisible && (
                        <div className={style.commentDrop}>
                        <div className={style.commentContainer}>
                            <div className={style.dropdown_title}>댓글 알림창</div>
                            {comments.map(comment => (
                                <div 
                                    key={comment.id}
                                    className={style.dropdown_comment_item} 
                                    onClick={() => handleCommentClick(comment)}>
                                    {comment.username}님의 댓글이 달렸습니다
                                </div>
                            ))}
                            <div className={style.dropdown_delete_box}>
                                <div className={style.clickarea} onClick={handleDeleteComments}>
                                    <FaRegTrashCan className={style.icon}/>
                                </div>
                            </div>
                        </div>
                        </div>
                    )}
                </div>

                {/* 메일 아이콘 */}
                  <div ref={chatRef}>
                    <div className={style.icon_wrapper}>
                        <FiMail className={style.fimail}
                        onClick={toggleChat}
                        />
                        {unreadChatCount > 0 && (
                            <span className={style.badge}></span>
                        )}
                    </div>
                    {isChatVisible && (
                        <div className={style.chatDrop}>
                          <div className={style.chatContainer}>
                              <div className={style.dropdown_title}>채팅 알림창</div>
                              {visibleChatRooms.map(room => (
                                  <div 
                                      key={room.chatRoomId}
                                      className={style.dropdown_item} 
                                      onClick={() => openChatModal(room)}>
                                      {userInfo.userNickname === room.ownerNickname ? room.participantNickname : room.ownerNickname}님과의 대화
                                  </div>
                              ))}
                          </div>
                        </div>
                      )}
                  </div>
                </div>
                <Modal isOpen={modalType !== null} onRequestClose={closeModal} overlayClassName={style.modal_overlay} className={style.modal_container}>
                            {modalType === 'chat' && selectedRoom && (
                                <ChatModal 
                                    onClose={closeModal} 
                                    onDelete={handleDeleteRoom}
                                    partnerNickname={selectedRoom.partnerNickname}
                                    postId={selectedRoom.postId}
                                    postType={selectedRoom.postType}
                                    roomId={selectedRoom.roomId}
                                    isAuthor={selectedRoom.isAuthor}
                                />
                            )}
                </Modal>

                <div className={style.profile} onClick={handleMypage}>
                    <img src={userInfo.userImageUrl} alt='프로필 이미지' 
                        className={style.profile_img}/>
                <div className={style.text_group} onClick={handleMypage}>
                    <h2 className={style.user_nickname}>{userInfo.userNickname}</h2>
                    <span className={style.user_name}>{userInfo.userName}</span>
                </div>
            </div>
        </div>
    </div>
    );
}

export default HeaderLoginForm;