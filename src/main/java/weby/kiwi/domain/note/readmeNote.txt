스프링 부트와 JPA, Hibernate를 활용하여 글 작성 기능(Note)을 구현한다.

요약 설명
1. <CLASS> Note : DB의 note table과 mapping되는 JPA Entity
2. <INTERFACE> NoteRepository : DB와의 CRUD 작업 기능
3. <CLASS> NoteController : HTTP 요청을 처리하는 스프렝 MVC 컨트롤러
4. <CLASS> NoteRequest : 프론트에서 백으로 전송되는 JSON 데이터를 mapping하는 DTO

(+ In Detail)

1. Note
Note 클래스는 데이터베이스의 note 테이블과 매핑되는 JPA Entity 이다.
@Entity 어노테이션으로 엔티티임을 나타내고, 필드와 데이터베이스 컬럼 간의 매핑.
@Id, @GeneratedValue, @Column 등의 어노테이션을 사용하여 엔티티의 주요 정보와 속성을 정의.
Getter와 Setter 메서드로 필드에 접근하고 값을 설정할 수 있음.


2. NoteRepository
NoteRepository는 스프링 데이터 JPA에서 제공하는 JpaRepository 인터페이스를 확장.
JPA를 사용하여 데이터베이스와 상호작용하는 메소드를 선언함.
기본적인 CRUD(Create, Read, Update, Delete) 작업을 위한 메소드들을 자동으로 제공받음.


3. NoteController
NoteController 클래스는 웹 요청을 받아 처리하는 스프링 MVC 컨트롤러.
@RestController 어노테이션을 통해 RESTful 웹 서비스를 제공하는 컨트롤러임을 나타냄.
@PostMapping 어노테이션으로 HTTP POST 요청을 처리하고,
@RequestBody 어노테이션을 사용하여 JSON 데이터를 NoteRequest 객체로 매핑.
전달받은 데이터를 이용하여 Note 객체를 생성하고 데이터베이스에 저장하는 기능을 담당.


4. NoteRequest
NoteRequest 클래스는 프론트엔드에서 백엔드로 전송되는 JSON 데이터를 매핑하기 위한 DTO(Data Transfer Object).
title과 content 필드를 가지고, Getter와 Setter 메서드를 통해 프론트엔드에서 전송한 데이터를 받음.

