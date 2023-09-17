# [SILVER 5] 11399. ATM

[문제 링크](https://www.acmicpc.net/problem/11399)

### 풀이방법
1. 최소 시간을 구하기 위해 값들을 오름차순으로 정렬한다.
2. 배열을 돌며 현재 인덱스의 사람이 돈을 인출하기 위해 걸리는 시간을 대기시간에 더한 후, 대기 시간을 총 소요시간에 더해준다.

### 구현부
```java
  // 정렬
  Arrays.sort(arr);

  int total = 0; // 총 소요시간
  int wait = 0; // 현재 대기시간

  for(int i = 0; i < N; i++) {
      wait += arr[i];
      total += wait;
  }
```