import React, { useState, useEffect, useRef } from "react";
import { FaRegTrashCan } from "react-icons/fa6";
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
import styles from '../../css/info/ChatModal.module.css';
import { getUserId } from '../common/authUtils';

export function ChatModal({
    onClose,
    onDelete,
    partnerNickname,
    postId,
    postType,
    roomId,
    isAuthor = false
}){
    const navigate = useNavigate();
    const [message, setMessage] = useState('');
    const [userid, setUserid] = useState('');
    const [messages, setMessages] = useState([]);
    const [isProcessed, setIsProcessed] = useState(false);
    
    // useRef로 상태 관리 (컴포넌트 레벨에서 유지)
    const stompClientRef = useRef(null);
    const isInitializedRef = useRef(false);     // 초기화 완료 플래그
    const subscriptionRef = useRef(null);       // 구독 객체 저장
    const userIdRef = useRef(null);             // userId 캐싱

    // 채팅 초기화 (사용자 ID, 메시지 로드, WebSocket 연결, 읽음 처리)
    useEffect(() => {
        if (!roomId) return;
        
        // 중복 실행 방지
        if (isInitializedRef.current) {
            console.log('⚠️ [useEffect] 이미 초기화됨 - 중복 실행 방지');
            return;
        }
        
        console.log('🔄 [useEffect] 채팅 초기화 시작 - roomId:', roomId);
        isInitializedRef.current = true;
        
        const initChat = async () => {
            // 1. 사용자 ID 가져오기
            const userId = await getUserId();
            if (!userId) {
                isInitializedRef.current = false;
                return;
            }
            
            setUserid(userId);
            userIdRef.current = userId;  // ref에도 저장
            //console.log('👤 [useEffect] 현재 사용자 ID:', userId);
            
            // 2. 메시지 로드
            try {
                const response = await axios.get(
                    `http://localhost:18880/api/chat/rooms/${roomId}/messages`
                );
                
                if (response.data.success) {
                    const loadedMessages = response.data.data.map(msg => ({
                        id: msg.chatMessageId,
                        senderid: msg.senderId,
                        content: msg.messageContent,
                        timestamp: new Date(msg.createdAt).toLocaleTimeString('ko-KR', {
                            hour: '2-digit',
                            minute: '2-digit'
                        }),
                        ismine: msg.senderId === userId,
                        type: msg.messageType === 'TEXT' ? undefined : msg.messageType.toLowerCase()
                    }));
                    
                    setMessages(loadedMessages);
                    console.log('📥 [useEffect] 이전 메시지 로드 완료:', loadedMessages.length + '개');
                }
            } catch (error) {
                console.error('메시지 조회 실패:', error);
            }
            
            // 3. 채팅방 정보 조회 (isAccepted 상태 확인)
            try {
                const roomResponse = await axios.get(
                    `http://localhost:18880/api/chat/rooms/${roomId}/info`
                );
                
                if (roomResponse.data.success) {
                    const roomData = roomResponse.data.data;
                    setIsProcessed(roomData.isAccepted === 'Y');
                }
            } catch (error) {
                console.error('채팅방 정보 조회 실패:', error);
            }
            
            // 4. 읽음 처리 API 호출
            try {
                await axios.put(`http://localhost:18880/api/chat/rooms/${roomId}/read`);
            } catch (error) {
                console.error('읽음 처리 실패:', error);
            }
            
            // 5. WebSocket 연결
            const socket = new SockJS('http://localhost:18880/ws/chat');
            const client = new Client({
                webSocketFactory: () => socket,
                onConnect: () => {
                    console.log('🔌 [WebSocket] 연결 성공');
                    
                    // 이미 구독했으면 중복 구독 방지
                    if (subscriptionRef.current) {
                        console.log('⚠️ [WebSocket] 이미 구독 중 - 중복 구독 방지');
                        return;
                    }
                    
                    // 채팅방 구독
                    const subscription = client.subscribe(`/topic/chat/${roomId}`, (message) => {
                        const receivedMsg = JSON.parse(message.body);
                        console.log('📨 [WebSocket] 메시지 수신:', receivedMsg);
                        //console.log('📌 [WebSocket] 현재 userId:', userId, ', 발신자:', receivedMsg.senderId, ', 메시지 타입:', receivedMsg.messageType);
                        
                        // 시스템 메시지(ACCEPT, EXIT, SYSTEM)는 항상 표시
                        const isSystemMessage = receivedMsg.messageType && 
                                               receivedMsg.messageType !== 'TEXT';
                        
                        if (isSystemMessage) {
                            console.log('✅ [WebSocket] 시스템 메시지 - 모두에게 표시');
                        } else if (receivedMsg.senderId === userId) {
                            // 일반 메시지 중 자신의 메시지는 무시
                            console.log('⚠️ [WebSocket] 자신의 일반 메시지 - 무시');
                            return;
                        } else {
                            console.log('✅ [WebSocket] 상대방 메시지 - 추가');
                        }
                        
                        const newMsg = {
                            id: receivedMsg.chatMessageId || Date.now(),
                            senderid: receivedMsg.senderId,
                            content: receivedMsg.messageContent,
                            timestamp: new Date(receivedMsg.createdAt).toLocaleTimeString('ko-KR', {
                                hour: '2-digit',
                                minute: '2-digit'
                            }),
                            ismine: receivedMsg.senderId === userId,
                            type: receivedMsg.messageType === 'TEXT' ? undefined : receivedMsg.messageType.toLowerCase()
                        };
                        
                        setMessages(prev => {
                            console.log('📝 [WebSocket] 메시지 추가 전 개수:', prev.length);
                            const updated = [...prev, newMsg];
                            console.log('📝 [WebSocket] 메시지 추가 후 개수:', updated.length);
                            return updated;
                        });
                        
                        // 실시간 메시지 수신 시에도 읽음 처리
                        axios.put(`http://localhost:18880/api/chat/rooms/${roomId}/read`).catch(console.error);
                    });
                    
                    // 구독 객체 저장
                    subscriptionRef.current = subscription;
                    console.log('✅ [WebSocket] 구독 완료 및 저장 - /topic/chat/' + roomId);
                },
                onStompError: (frame) => {
                    console.error('❌ [WebSocket] STOMP 에러:', frame);
                }
            });

            client.activate();
            stompClientRef.current = client;
        };
        
        initChat();
        
        // 컴포넌트 언마운트 또는 roomId 변경 시 정리
        return () => {
            console.log('🧹 [cleanup] 정리 시작');
            
            // 1. 구독 해제
            if (subscriptionRef.current) {
                try {
                    subscriptionRef.current.unsubscribe();
                    console.log('✅ [cleanup] 구독 해제 완료');
                } catch (e) {
                    console.error('❌ [cleanup] 구독 해제 실패:', e);
                }
                subscriptionRef.current = null;
            }
            
            // 2. WebSocket 연결 해제
            if (stompClientRef.current?.active) {
                try {
                    stompClientRef.current.deactivate();
                    console.log('✅ [cleanup] WebSocket 연결 해제 완료');
                } catch (e) {
                    console.error('❌ [cleanup] 연결 해제 실패:', e);
                }
            }
            stompClientRef.current = null;
            
            // 3. 플래그 초기화
            isInitializedRef.current = false;
            userIdRef.current = null;
            console.log('✅ [cleanup] 정리 완료');
        };
    }, [roomId]);

    const handleSubmit = (e) => {
        e.preventDefault();
        
        if (!message.trim() || !stompClientRef.current || !stompClientRef.current.connected) {
            return;
        }

        // 1. 로컬에 즉시 메시지 추가 (발신자용)
        const localMsg = {
            id: Date.now(),
            senderid: userid,
            content: message,
            timestamp: new Date().toLocaleTimeString('ko-KR', {
                hour: '2-digit',
                minute: '2-digit'
            }),
            ismine: true,
            type: undefined
        };
        setMessages(prev => [...prev, localMsg]);
        
        // 2. WebSocket으로 메시지 전송 (수신자에게만 전달)
        stompClientRef.current.publish({
            destination: `/app/chat/${roomId}`,
            body: JSON.stringify({
                senderId: userid,
                messageContent: message,
                messageType: 'TEXT'
            })
        });
        
        // 3. 메시지 전송 후 초기화
        setMessage('');
    };

    const handleAccept = async () => {
        if (!window.confirm('수락하시겠습니까? 게시글 상태가 완료로 변경됩니다.')) {
            return;
        }
        
        try {
            const response = await axios.post(
                `http://localhost:18880/api/chat/rooms/${roomId}/accept`
            );
            
            if (response.data.success) {
                alert('수락되었습니다. 게시글 상태가 완료로 변경되었습니다.');
                setIsProcessed(true);
            } else {
                alert(response.data.message || '수락 처리 실패');
            }
        } catch (error) {
            console.error('수락 처리 실패:', error);
            alert('수락 처리 중 오류가 발생했습니다.');
        }
    };

    const handleReject = async () => {
        if (!window.confirm('거절하시겠습니까?')) {
            return;
        }
        
        try {
            const response = await axios.post(
                `http://localhost:18880/api/chat/rooms/${roomId}/reject`
            );
            
            if (response.data.success) {
                alert('거절되었습니다.');
                setIsProcessed(true);
                
                // 채팅방 목록에서 삭제
                if (onDelete) {
                    onDelete(roomId);
                }
                
                // 2초 후 채팅창 닫기
                setTimeout(() => {
                    onClose();
                }, 2000);
            } else {
                alert(response.data.message || '거절 처리 실패');
            }
        } catch (error) {
            console.error('거절 처리 실패:', error);
            alert('거절 처리 중 오류가 발생했습니다.');
        }
    };

    const handleMoveToPost = () => {
        if (postType === 'sharetool') {
            navigate(`/sharetool/detail/${postId}`);
        } else if (postType === 'shopping') {
            navigate(`/withshopping?page=1&modal=detail&id=${postId}`);
        }
        onClose();
    };

    const handleDeleteChat = async () => {
        if (!window.confirm('채팅방을 삭제하시겠습니까?')) {
            return;
        }
        
        try {
            const response = await axios.post(
                `http://localhost:18880/api/chat/rooms/${roomId}/hide`
            );
            
            if (response.data.success) {
                // 상위 컴포넌트에 삭제 알림
                if (onDelete) {
                    onDelete(roomId);
                }
                
                alert('채팅방이 삭제되었습니다.');
                onClose();
            } else {
                alert(response.data.message || '채팅방 삭제 실패');
            }
        } catch (error) {
            console.error('채팅방 삭제 실패:', error);
            alert('채팅방 삭제 중 오류가 발생했습니다.');
        }
    };

    const handleKeyDown = (e) => {
        // 1. 눌린 키가 'Enter' 인지 확인
        if (e.key === 'Enter') {
            // 2. Shift 키가 함께 눌리지 않았는지 확인 (Shift + Enter는 줄 바꿈 허용)
            if (!e.shiftKey) {
                // 3. 엔터 키의 기본 동작(줄 바꿈)을 막음
                e.preventDefault(); 
                
                // 4. 폼 제출 함수 실행
                handleSubmit(e);
            }
        }
    };

    return(
        <div className={styles.chatmodal_container}>
            <div className={styles.chatarea_container}>
                {messages.map(msg => {
                    // 시스템 메시지 (신청 접수)
                    if (msg.type === 'system') {
                        return (
                            <div key={msg.id} className={styles.exitmessage_div}>
                                <div className={styles.exitmessage}>
                                    {msg.content}
                                </div>
                            </div>
                        );
                    }
                    
                    // 퇴장 메시지
                    if (msg.type === 'exit') {
                        return (
                            <div key={msg.id} className={styles.exitmessage_div}>
                                <div className={styles.exitmessage}>
                                    {msg.content}
                                </div>
                            </div>
                        );
                    }
                    
                    // 수락 메시지
                    if (msg.type === 'accept') {
                        return msg.ismine ? (
                            <div key={msg.id} className={styles.mytext_div}>
                                <div className={styles.accepttext_mine}>
                                    {msg.content}
                                </div>
                            </div>
                        ) : (
                            <div key={msg.id} className={styles.othertext_div}>
                                <div className={styles.accepttext_other}>
                                    {msg.content}
                                </div>
                            </div>
                        );
                    }
                    
                    // 일반 메시지
                    return msg.ismine ? (
                        <div key={msg.id} className={styles.mytext_div}>
                            <span className={styles.timestamp}>{msg.timestamp} &nbsp;</span>
                            <div className={styles.mytext}>
                                {msg.content}
                            </div>
                        </div>
                    ) : (
                        <div key={msg.id} className={styles.othertext_div}>
                            <div className={styles.userName}>{partnerNickname}</div>
                            <div className={styles.othertext}>
                                {msg.content}
                            </div>
                            <span className={styles.timestamp}>&nbsp; {msg.timestamp}</span>
                        </div>
                    );
                })}
            </div>
            <div className={styles.chatinput_container}>
                <div>
                    <form onSubmit={handleSubmit}>
                        <textarea rows="1" 
                                    className={styles.chatinput_box} 
                                    value={message}
                                    onChange={(e) => setMessage(e.target.value)}
                                    onKeyDown={handleKeyDown}
                                    placeholder="메시지를 입력하세요..."
                                    maxLength={500}
                                    />
                        <button type="submit" className={styles.chatsubmite_btn}>보내기</button>
                    </form>
                </div>
                <div className={styles.warnning_text}>
                    {isAuthor && !isProcessed && (
                        <>
                            수락시 (구인완료 및 나눔완료)<br/>
                            상태가 변경되니 신중하게 선택해주세요.
                        </>
                    )}
                </div>
                <div className={styles.btn_container}>
                    <div className={styles.movebtn_div}>
                        <button type="button" onClick={onClose}>닫기</button>
                        <button type="button" onClick={handleMoveToPost}>게시글 이동</button>
                    </div>
                    <div className={styles.acceptbtn_div}>
                        {isAuthor && !isProcessed && (
                            <>
                                <button type="button" onClick={handleReject}>거절</button>
                                <button type="button" onClick={handleAccept}>수락</button>
                            </>
                        )}
                        <FaRegTrashCan className={styles.deleteicon} onClick={handleDeleteChat}/>
                    </div>
                </div>
            </div>
        </div>
    )
}
