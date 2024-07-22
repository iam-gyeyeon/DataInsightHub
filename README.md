# 사용자 데이터 분석 프로그램

## 1. 프로젝트 주제

사용자가 로그인을 통해 데이터를 수집하고 전처리하여, 필터링된 데이터를 데이터베이스에 저장한 후, 이를 통계화하여 차트로 시각화하는 웹 애플리케이션

## 2. 프로젝트 핵심 기능

1. **사용자 인증 및 로그인**
    - 회원가입 및 로그인 기능
    - JWT 또는 OAuth2를 통한 인증 관리

2. **데이터 수집**
    - AIHub API를 사용하여 원천 데이터 수집
    - JSON 형식의 데이터 파싱

3. **데이터 전처리**
    - 데이터의 정규화 및 정제
    - 텍스트 처리 및 주요 키워드 추출
    - 주제 분류

4. **데이터 저장**
    - 필터링된 데이터를 데이터베이스에 저장

5. **데이터 시각화**
    - 통계 데이터를 차트로 시각화
    - 프론트엔드에서 사용자에게 데이터 제공

## 3. 프로젝트 기술 스택

1. **백엔드**
    - **프레임워크**: Spring Boot
    - **데이터 수집**: Jackson 라이브러리(JSON 파싱 목적)
    - **데이터 전처리**: Apache Commons Lang, OpenNLP, Spring Batch
    - **주요 키워드 추출 및 주제 분류**: Apache OpenNLP, Stanford NLP, TF-IDF, 머신러닝 모델
    - **데이터 저장**: JPA, Hibernate

2. **프론트엔드**
    - **프레임워크**: Vue.js
    - **차트 시각화**: Chart.js

3. **데이터베이스**
    - **DBMS**: MySQL

4. **인증 및 보안**
    - JWT

5. **빌드 및 배포**
    - Maven

## 4. 추가 정리할 내용

1. **프로젝트 아키텍처**
    - 도메인형 아키텍쳐
    - API 설계 및 문서화 (Swagger)

2. **개발 환경 설정**
    - 개발 도구: IntelliJ IDEA, Visual Studio
    - 버전 관리: Git
    - CI/CD 도구: Jenkins

3. **테스트**
    - 단위 테스트: JUnit

4. **성능 최적화**
    - 캐싱: Spring Cache(or EhCache)
    - 비동기 처리: Spring Async

