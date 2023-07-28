# [D3] 원재의 메모리 복구하기 - 1289 

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV19AcoKI9sCFAZN) 

### 성능 요약

메모리: 18,396 KB, 시간: 98 ms, 코드길이: 744 Bytes



> 출처: SW Expert Academy, https://swexpertacademy.com/main/code/problem/problemList.do


### 문제해결전략
설명이 이상? 해서 문제 이해가 어려웠습니다
0과 1로 이루어진 배열을 모두 첫 인덱스의 있는 값으로 만드는 문제로 한번의 전환으로 하나의 뭉텅이를 변환할 수 있습니다.
예를 들면 01101을 복구한다고 가정하면 아래와 같은 순서로 복구가 완료됩니다
  0. 01101
  1. 11101
  2. 00001
  3. 11111
  4. 00000
따라서 각 인덱스를 검사하며 0에서 1로 1에서 0으로 바뀌는 구간을 카운트하여 풀이하였습니다.
