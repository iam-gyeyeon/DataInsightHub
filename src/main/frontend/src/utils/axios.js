// src/utils/axios.js

import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080', // 기본 URL 설정
  timeout: 1000, // 요청 타임아웃 설정 (선택 사항)
});

// 요청 인터셉터 및 응답 인터셉터 설정 (선택 사항)
// axiosInstance.interceptors.request.use(...);
// axiosInstance.interceptors.response.use(...);

export default axiosInstance;
