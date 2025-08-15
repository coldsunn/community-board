# community-board

개인 프로젝트 이름 : 올 게시판

기간 : 2025.08.11 ~ 2025.08.14

## 프로젝트 배경, 소개

배경:

과 홈페이지의 공지사항을 읽다가 우연히 참여했던 프로그램이 좋았던 기억이 있어서 이를 계기로 다시 생각해보니 입학한 이후로 과 공지사항을 읽은 적이 손에 꼽는다는 사실을 깨닫고 학과 별로 소통하고 정보를 얻을 수 있는 커뮤니티가 있으면 좋겠다고 생각했다. 과 단위로 올라오는 공지사항을 얻기에도 좋고 학생회에게는 과 행사를 홍보하는 이점도 있으며 공부하는 학생들에게는 진로 정보를 얻을 수도 있는 등의 이점이 있을 것이다.

소개:

분산되어 있는 학과 정보를 한 곳에 모은 서비스다. 일반과 학생회 유저로 나뉘어 각 학과 별로 공지사항, 학생회 공지사항, 자유 게시판, 진로 게시판, 수업 게시판을 이용할 수 있다. 현재는 게시판 구현에 집중했기 때문에 실제 과 홈페이지 공지사항을 가져오고 로그인에 인증 기능을 추가하는 것은 추후에 추가할 예정이다.

핵심 기능:

- 회원가입/로그인/로그아웃
- 학과 별 게시판 글쓰기, 수정, 삭제(일반 유저의 경우 공지사항과 학생회 공지사항은 글쓰기가 제한)
- 게시글 댓글, 스크랩
- 마이페이지(내가 쓴 글, 내가 쓴 댓글, 좋아요, 스크랩 확인)

## 스택

- 프론트엔드: 부트스트랩
- 템플릿 엔진: thymeleaf
- 프레임워크: 자바 스프링, 스프링 부트
- ORM: JPA
- DB: MYSQLDB
- IDE: IntelliJ

RDB 설계

<img width="1510" height="862" alt="Image" src="https://github.com/user-attachments/assets/7ebeb156-4d24-4230-a256-312ec952f8d4" />

## 주요 기능, 화면 구성

- 회원가입/로그인/로그아웃

<img width="1146" height="816" alt="Image" src="https://github.com/user-attachments/assets/5c9f6e12-b4e5-43e7-883f-860dae124016" />

<img width="982" height="893" alt="Image" src="https://github.com/user-attachments/assets/cbf28c1b-2505-42a9-b7e4-874783e96e6a" />  


- 학과 별 게시판 글쓰기(일반 유저의 경우 공지사항과 학생회 공지사항은 글쓰기가 제한)

<img width="2560" height="1270" alt="Image" src="https://github.com/user-attachments/assets/65ceb9b1-3b97-4b46-90de-d375ead3916e" />

<img width="2560" height="1272" alt="Image" src="https://github.com/user-attachments/assets/4df2030a-9e0e-4c54-bab1-fbdfbaae6446" />

<img width="2560" height="1279" alt="Image" src="https://github.com/user-attachments/assets/24713452-cb0d-4855-9c45-35e885e0d4a2" />  
일반 유저는 글쓰기 버튼이 없음  


- 게시글 수정, 삭제, 댓글, 좋아요, 스크랩

<img width="2560" height="1270" alt="Image" src="https://github.com/user-attachments/assets/f3eb37e3-88dd-428a-87a2-bdb0998291d1" />

<img width="2560" height="1267" alt="Image" src="https://github.com/user-attachments/assets/822449be-faf2-4369-a737-44e48bc524b5" />  
일반 유저는 공지사항 게시글에 좋아요, 스크랩, 댓글만 가능  


- 마이페이지(내가 쓴 글, 내가 쓴 댓글, 좋아요, 스크랩 확인)

<img width="2560" height="1272" alt="Image" src="https://github.com/user-attachments/assets/f7bf0084-8a13-46e8-95ec-74d8e78d78cc" />

<img width="2560" height="1267" alt="Image" src="https://github.com/user-attachments/assets/0f488d67-afd1-4fe9-b617-5faf15c8b936" />

<img width="2560" height="1265" alt="Image" src="https://github.com/user-attachments/assets/47c2256c-25d3-4a08-8311-a25ebbeaaca5" />