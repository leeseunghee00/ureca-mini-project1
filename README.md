# 미니프로젝트1: 영화 평가 플랫폼
LG U+ 유레카 교육과정에서 미니 프로젝트로 진행한 영화 프로젝트입니다.

### _Document._
- [요구사항 설계 및 데이터 모델링](https://leeseunghee00.notion.site/ac654987befa470caaf2d81effd97cc7?pvs=4)
- [SQL DB](https://github.com/leeseunghee00/ureka-mini-project1/blob/main/moviedb.sql)

#### ER Diagram
<p align="center">
  <img src="https://github.com/user-attachments/assets/560872e9-46f4-4e00-b814-4da664803e9a">
</p>

<br />

### _Stacks._
- Java 17, JDBC
- Docker MySQL
- Swing

<br />

### _Action._
**1. 메인화면**
- eventListener 를 사용해 조회 하고자 하는 영화를 더블클릭 시 해당 영화 조회 페이지로 넘어갑니다. 

<p align="center">
  <img src="https://github.com/user-attachments/assets/47eaf30b-2438-44c7-b099-0358558ea7ff">
</p>

**2. 영화 조회**
- JTable 을 영화와 리뷰를 따로 정의하여 각 정보를 조회하도록 하였습니다.
- JSplitPane 를 사용해 영화, 리뷰가 상하에 정렬되도록 컴포넌트를 분리했습니다.

<p align="center">
  <img src="https://github.com/user-attachments/assets/c1130a0b-35da-46c3-9e05-f9b583843c20">
</p>

**3. 리뷰 수정/삭제**
- JSlider 를 사용해 1점 ~ 5점 사이의 평점을 남길 수 있도록 구현했습니다.

<p align="center">
  <img src="https://github.com/user-attachments/assets/7fa28bf5-0ca7-483c-8eda-ad40b69bf34c">
</p>

### _Setting._
DBManager 는 애플리케이션에 DB 를 연결해 주는 역할을 합니다. <br />
아래 설정을 자신의 환경에 맞게 설정해서 진행합니다.

```java
public class DBManager {
	static final String url = "jdbc:mysql://localhost:3306/moviedb";
	static final String user = "${USER}";
	static final String pwd = "${PASSWORD}";
}
```
