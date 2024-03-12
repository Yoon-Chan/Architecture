# Architecture
안드로이드 아키텍쳐 관련 기능 구현
---

---



## BookSearchApp
 + 카카오 API를 이용하여 도서 검색 기능을 구현하고 원하는 도서를 저장하여 검색을 안해도 Favorite Books 화면에 보이게 만들었다. 또한 검색을 했을 때 최신순, 정확도 순으로 볼 수 있도록 설정 화면에서 선택 가능하도록 구현했습니다.

### search(검색 부분)

+ 사용 기술

  + Retrofit

  + OkHttp

  + Gson

  + repository

  + ViewModel, ViewModelFactory
  + RecyclerView
  + Pager

+ 상단 EditTextView를 사용하여 입력을 받을 때마다 검색한 결과를 보여주는 기능 구현
+ Retrofit을 이용하여 카카오 API와 연결하여 책의 정보를 받아와 해당 책에 맞는 이름을 리사이클러 뷰를 통해 보여주도록 하는 기능
+ 검색 결과가 없을 때는 

__실행 결과__


https://github.com/Yoon-Chan/Architecture/assets/56026214/c74d8645-4620-43a2-bb79-632c8d7ce589



#### 검색 클릭 시 웹 뷰 이동

+ 사용 기술
  + jetpack navigation
  + webView
+ 검색하고 나온 리스트들을 클릭 시 해당 책에 맞는 url로 이동하는 기능 구현
+ 여기서 배운 점
  + 앱바와 jetpack navigation을 연결하는 방법을 알았다. (AppBarConfiguration), 다만 이걸 사용하려면 AppBar가 있어야 사용이 가능
  + 프래그먼트 이동 시 args를 받아 사용하는 방법을 배움(Parcelable을 이용해 데이터 클래스 전달)

__실행 결과__



https://github.com/Yoon-Chan/Architecture/assets/56026214/b773b894-2dcd-4f4d-b5d8-586c286bd1fc



---

## Favorite Books 화면

