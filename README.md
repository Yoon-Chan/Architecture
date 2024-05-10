# Architecture


## 카카오 API를 이용한 Modern Android Jetpack 라이브러리 사용하기

### :question: 프로젝트 설명

카카오 API를 이용해 책 검색 앱 프로젝트 만들기

Jetpack 라이브러리들을 이용하여 손쉬운 라이브러리들을 이용해 Modern Android 개발하기

#### 노션
https://www.notion.so/4adee46d8d38441182a1e37213ec5bf2

## 🛠 기술 스택 및 도구

## <img src="https://img.shields.io/badge/android-34A853?style=for-the-badge&logo=android&logoColor=white"><img src="https://img.shields.io/badge/androidstudio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white"><img src="https://img.shields.io/badge/kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"><img src="https://img.shields.io/badge/githubactions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"><img src="https://img.shields.io/badge/Kakao-FFCD00?style=for-the-badge&logo=Kakao&logoColor=white">

| 기술 스택             |                                                              |
| --------------------- | ------------------------------------------------------------ |
| 안드로이드 라이브러리 | Modern Android, Jetpack Library, Hilt, Room, Datastore, Paging3, Flow, RxJava |
| 아키텍처              | MVVM                                                         |
| CI/CD                 | Github Actions                                               |



## :pushpin: 구현 내용

## 1. 책 검색 화면

| 검색 화면 | 웹뷰 이동 |
| --------- | --------- |
|     ![search](https://github.com/Yoon-Chan/Architecture/assets/56026214/5f5ff633-3cc0-4dc3-be63-b68f426b9d92)|     ![webView](https://github.com/Yoon-Chan/Architecture/assets/56026214/7d326114-04e8-46b7-af88-c12728e4f4a2)|



## 2. 책 저장 및 삭제

| 책 저장하기 | 저장된 책 정보 삭제하기 | 삭제된 정보 복구 |
| ----------- | ----------------------- | ---------------- |
|      ![save](https://github.com/Yoon-Chan/Architecture/assets/56026214/ad7e2e25-6474-4343-b893-44f9a4ec6f5c) |       ![delete](https://github.com/Yoon-Chan/Architecture/assets/56026214/bd1fd74a-01eb-404d-9518-0c69ec9129ae)     |        ![restore](https://github.com/Yoon-Chan/Architecture/assets/56026214/94655ca6-c2c9-4d36-a072-c445bd4dd3e1) |



## 3. 인터넷 연결 끊길 때 리사이클러 뷰

![internet](https://github.com/Yoon-Chan/Architecture/assets/56026214/8eaaacbf-cf4f-4eef-8c43-29f191674959)

## 4. 기타 기능
+ 슬라이드를 이용하여 저장된 좋아요 책 정보 삭제
+ 스낵바를 이용하여 삭제 취소 기능 구현
+ 페이징을 이용하여 데이터 효율적으로 가져오기
