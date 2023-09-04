# chat GPT (Game Play Time)
Console project : 채팅을 이용한 마피아 게임 서비스

## 멤버 구성
구민규([@9min9](https://github.com/9min9)) : Player, GIT 관리, 발표

김준회([@categorySet](https://github.com/categorySet)) : Game Server, Mafia

류가희([@justlikeryu](https://github.com/justlikeryu)) : Manager, Black List

김수연([@yeonann](https://github.com/yeonann)) : Shop, Item

## 구현 기능
### Player
- 회원가입, 로그인, 로그아웃, 정보 수정, 회원 탈퇴 등 기본 CRUD 기능
- 게임의 승/패를 통해 Credit을 얻음
- Credit을 사용하여 상점에서 아이템을 구매 및 사용하는 기능

### Game
- 서버 소켓을 열어 게임 요청을 받는 기능
- 포트 번호를 UDP를 이용해 받는 기능
- 게임을 시작하고 게임을 관리하는 기능
- 낮과 밤 관리
- 각 역할들의 능력 관리
- 게임을 결과를 판정하는 기능

### Manager
- Player를 조회하여 블랙리스트 등록 및 삭제 
- Player에게 Credit을 부여 및 삭감
- 아이템 등록 및 삭제

### Item
- 아이템 CRUD
- 아이템을 통해 player의 닉네임에 칭호를 붙이거나 색을 변경하여 꾸밀 수 있음
- limited edition으로 수량 제한이 있는 아이템 추가

### Shop
- 아이템 목록을 조회하여 선택 구매
- 구매 내역 조회
- 닉네임 칭호, 스킨 구매 후 바로 적용

## 사용기술 및 개발환경
- Language: Java 17
- Database: Oracle DB, SQL Developer
