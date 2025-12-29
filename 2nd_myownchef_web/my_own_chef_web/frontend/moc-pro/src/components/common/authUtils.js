/**
 * 인증 관련 유틸리티 함수
 * Java Session 기반 인증 시스템
 */

import axios from 'axios';

// API Base URL
const API_BASE_URL = 'http://localhost:18880/api/auth';

// axios 기본 설정 (Session 쿠키 전송)
axios.defaults.withCredentials = true;

/**
 * 로그인 여부 확인
 * @returns {Promise<boolean>}
 */
export const checkLogin = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/check`);
        return response.data.isLoggedIn;
    } catch (error) {
        console.error('로그인 확인 실패:', error);
        return false;
    }
};

/**
 * 현재 사용자 ID 가져오기
 * @returns {Promise<string|null>}
 */
export const getUserId = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/check`);
        return response.data.userId;
    } catch (error) {
        console.error('사용자 ID 가져오기 실패:', error);
        return null;
    }
};

export const getUserNickname = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/check`);
        return response.data.userNickname;
    } catch (error) {
        console.error('사용자 닉네임 가져오기 실패:', error);
        return null;
    }
};

/**
 * 관리자 여부 확인
 * @returns {Promise<boolean>}
 */
export const checkAdmin = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/check`);
        return response.data.isAdmin;
    } catch (error) {
        console.error('관리자 확인 실패:', error);
        return false;
    }
};

/**
 * 작성자 여부 확인
 * @param {number|string} postId - 게시물 ID
 * @param {string} boardType - 게시판 타입 ('sharetool', 'withshopping', 'recipe', 'freeboard' 등)
 * @returns {Promise<boolean>}
 */
export const checkAuthor = async (postId, boardType) => {
    try {
        const response = await axios.get(`http://localhost:18880/api/${boardType}/${postId}/check-author`);
        return response.data.isAuthor;
    } catch (error) {
        console.error('작성자 확인 실패:', error);
        return false;
    }
};

/**
 * 로그인
 * @param {Object} credentials - { userid, password }
 * @returns {Promise<boolean>}
 */
export const login = async (credentials) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/login`, credentials);
        return response.data.success;
    } catch (error) {
        console.error('로그인 실패:', error);
        return false;
    }
};

/**
 * 로그아웃
 * @returns {Promise<boolean>}
 */
export const logout = async () => {
    try {
        const response = await axios.post(`${API_BASE_URL}/logout`);
        return response.data.success;
    } catch (error) {
        console.error('로그아웃 실패:', error);
        return false;
    }
};

/**
 * 회원가입
 * @param {Object} signupData - { userid, password, username, nickname, email }
 * @returns {Promise<Object>} { success, message, errorField }
 */
export const signup = async (signupData) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/signup`, signupData);
        return response.data;
    } catch (error) {
        console.error('회원가입 실패:', error);
        if (error.response && error.response.data) {
            return error.response.data;
        }
        return {
            success: false,
            message: '회원가입 중 오류가 발생했습니다.'
        };
    }
};

/**
 * 아이디 중복 확인
 * @param {string} userid - 사용자 ID
 * @returns {Promise<Object>} { success, message, available }
 */
export const checkIdDuplicate = async (userid) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/check-id`, { userid });
        return response.data;
    } catch (error) {
        console.error('아이디 중복 확인 실패:', error);
        if (error.response && error.response.data) {
            return error.response.data;
        }
        return {
            success: false,
            message: '중복 확인 중 오류가 발생했습니다.',
            available: false
        };
    }
};

