# [SILVER 3] 17413. 단어 뒤집기 2

[문제 링크](https://www.acmicpc.net/problem/17413)

### 풀이방법
- 문자가 ```<```인 경우
  - stack에 있는 모든 문자를 pop하고 StringBuilder에 추가
  - stack.push(```<```)
  - StringBuilder에 ```<``` 추가
- 문자가 ```>```인 경우
  - stack.pop()
  - StringBuilder에 ```>``` 추가
- ```<```와 ```>```를 제외한 모든 문자의 경우
  - stack의 top이 ```<```인 경우 (태그안의 문자)
    - StringBuilder에 문자 추가
  - 문자가 ```공백```인 경우
    - stack에 있는 모든 문자를 pop하고 StringBuilder에 추가
    - StringBuilder에 공백 추가
  - 그 외의 경우
    - stack.push(문자)
- 입력된 문자열의 길이만큼 위의 경우를 반복 체크하고, 마지막에 stack에 남아있는 모든 문자를 pop하여 StringBuilder에 추가한다.