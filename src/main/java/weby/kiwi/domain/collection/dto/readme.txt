Data Transfer Object(dto) 데이터 전송 객체
계층간 데이터 교환을 위한 객체정보.클라이언트-서버 간의 데이터 전송
https://gnidinger.tistory.com/469
DTO : Client-Controller-Service 사이 데이터 전송 객체
순수 데이터 저장 역할, getter,setter 메서드만 가짐 -> 저장, 검색, 직렬화(java객체 DTO->JSON), 역직렬화

Controller: 1. 수집 단어 등록
            2. 월별 수집 정보 조회

DTO:
1. collection 정보를 전달받을 DTO 클래스
2. 요청 데이터를 @RequestParam 애너테이션으로 전달받는 핸들러 메서드 찾기
    Request Body 필요 핸들러:  HTTP POST, PATCH, PUT 같은 경우
    HTTP GET은 리소스를 조회만 하는 용도이기 때문에 Request Body가 필요 없음
3. @RequestParam 쪽 코드를 DTO 클래스의 객체로 수정
4. Map 객체로 작성되어 있는 Response Body를 DTO 클래스의 객체로 변경

Validation:
1. CollectPostDto : O