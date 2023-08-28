# [D2] 백만 장자 프로젝트 - 1859 

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LrsUaDxcDFAXc) 

### 성능 요약

메모리: 138,296 KB, 시간: 467 ms, 코드길이: 894 Bytes



> 출처: SW Expert Academy, https://swexpertacademy.com/main/code/problem/problemList.do

 
 ### 문제해결전략
초기 풀이에서는 날짜순으로 매매가를 받는 배열과 내림차순으로 정렬한 배열을 이용하여 가장비싼날을 만나면 판매를 하도록 풀이하였습니다.
풀이가 완료된 후 다른 분들의 코드를 참고하였고 더 나은 알고리즘을 발견하여 수정하였습니다.
1. 배열의 뒤에서부터 탐색
2. 큰 값을 만나면 max 세팅
3. max보다 작은 값이면 총 판매가 + max - 오늘의 판매가
