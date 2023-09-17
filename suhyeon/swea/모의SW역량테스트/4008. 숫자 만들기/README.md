# [모의 SW 역량테스트] 4008. 숫자 만들기

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeRZV6kBUDFAVH&categoryId=AWIeRZV6kBUDFAVH&categoryType=CODE&problemTitle=4008&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1)

### 풀이방법
- 완전탐색문제
- 값을 계산해가면서 dfs를 진행하도록 풀이하였습니다.
- 연산자 카드로 만들 수 있는 모든 순열 만들며 최댓값, 최솟값을 갱신합니다.
- 연산 반복 후 `최대값-최솟값`을 계산하여 출력합니다.

### DFS 함수

```java
    public static void dfs(int step, int val) { // step : 단계, val : 지금까지의 계산결과
        if(step == N) {
            if(max < val) max = val;
            if(min > val) min = val;
            return;
        }

        for(int i = 0; i < op.length; i++) {
            if(op[i] == 0) continue; // 연산자 개수가 남아있지 않으면, 재귀호출 안함
            op[i]--; // 연산 개수 감소

            switch(i) {
                case 0: dfs(step + 1, val + num[step]); break;
                case 1: dfs(step + 1, val - num[step]); break;
                case 2: dfs(step + 1, val * num[step]); break;
                case 3: dfs(step + 1, val / num[step]); break;
            }
            op[i]++;
        }
    }
```