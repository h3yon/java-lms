# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)


# Step1
## 요구사항
### 질문 삭제하기 요구사항
- 질문 데이터 soft delete
- 삭제 제약조건
  - 로그인 사용자와 질문한 사람이 같은 경우 삭제가능
  - 답변이 없는 경우 삭제 가능
  - 질문자와 답변글의 모든 답변자가 같은 경우 삭제 가능
    - 질문을 삭제할 때 답변 또한 삭제해야하며, 답변도 soft delete
  - 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
- 질문과 답변 삭제 이력에 대한 정보는 DeleteHistory에 저장
### 리팩토링 요구사항
- QnaService의 deleteQuestion메서드의 단위테스트 가능 코드를 도메인 모델 객체에 구현
- 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현
- deleteQuestion 메서드의 단위테스트는 도메인 모델이 로직을 이동한 후에도 모두 통과해야한다.

# Step2
## 요구사항
- 과정(Course)
  - 기수단위로 운영
  - 여러개의 강의(Session)
- 강의(Session)
  - 시작일과 종료일
  - 강의 커버 이미지
    - 이미지 크기 1MB이하
    - gif, jpg(jpeg포함), png, svg만 허용
    - width는 300px, height 200px 이상, 비율 3:2 
  - 강의는 무료 강의와 유료 강의로 나뉜다
    - 무료강의는 최대 수강인원 제한이 없다.
    - 유료강의는 강의 최대 수강 인원 제한이 있다.
    - 유료강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강신청이 가능
  - 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.
  - 강의 수강신청은 강의 상태가 모집중일때만 가능
  - 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 구현(mock)
    - 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반환된다.