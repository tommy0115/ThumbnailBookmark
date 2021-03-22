1) 이미지를 검색해서 보관함에 수집하는 안드로이드 앱을 작성해주세요.

2) 검색은 키워드 하나에 이미지 검색과 동영상 검색을 동시에 사용, 두 검색 결과를 합친 리스트를 사용합니다.
  구체적인 사용 필드는 아래와 같습니다.
      • 이미지 검색 API ( https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-image )
          의 thumbnail_url 필드
      • 동영상 검색 API ( https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-video )
          의 thumbnail 필드
      • 두 검색 결과를 datetime 필드를 이용해 정렬하여 출력합니다. (최신부터 나타나도록)

3) UI는 ViewPager와 TabLayout을 활용하여 아래와 같이 구성합니다.
      • 탭1 : 검색 결과
          - 검색된 이미지 리스트가 나타납니다. 리스트에서 특정 이미지를 선택하여 '내 보관함' 으로 저장할 수 있습니다.
      • 탭2 : 내 보관함
          - 검색 결과에서 보관했던 이미지들이 보입니다.
          - 보관한 이미지 리스트는 DB 등으로 보관되지 않아도 됩니다.(앱 종료 시에는 사라져도 무방합니다.)

4) 적혀있지 않은 내용은 자유롭게 작성하시면 됩니다. (요건을 침해하지 않는 범위에서 기능 추가 등)


2. 개발 요건

- 검색 데이터는 https://developers.kakao.com/product/search 의 Open API를 사용합니다.
- 오픈 소스 사용 가능합니다. 참고로 카카오뱅크에서는 retrofit, kotlinx-coroutines-android, rxjava 등을
사용하고 있습니다.