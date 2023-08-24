# [모의 SW 역량테스트] 4013. 특이한 자석

[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH)

### 풀이방법
- 현재 자석의 2번 인덱스와 오른쪽 자석의 6번 인덱스를 비교한다.
  - 자성이 같다면 ```현재 자석의 방향에 -1을 곱하여``` 오른쪽 자석을 반대방향으로 회전시킨다.  
  - 자성이 다르다면 회전시키지 않는다.
- 시계 방향으로 회전시킬 경우에는 끝에 있는 값을 처음으로 넣어주어야 하며, 반시계 방향으로 회전시킬 경우에는 처음 값을 마지막으로 넣어주어야 한다.
- 가장 오른쪽의 마지막 자석까지 위의 과정을 반복한다.
- 왼쪽 자석의 경우에는 현재 자석의 6번 인덱스와 왼쪽 자석의 2번 인덱스를 비교해야 하며, 원리는 오른쪽 자석 회전과 동일하다.

### rotate 함수

```java
    // now : 현재돌리는 자석, dir : 방향, org : 처음시작한자석번호
    public static void rotate(int now, int dir, int org) {
        // now 자석이 org 자석의 왼쪽에 있다면 now 자석의 왼쪽 자석으로 넘어간다 (now-1)
        // now 자석의 6번과 왼쪽 자석의 2번이 서로 다르다면 다음 왼쪽 자석도 회전시킨다
        // 현재자석이 가장왼쪽 마지막 자석인지 확인한다
        if (now - 1 >= 1 && now <= org && magnets[now][6] != magnets[now - 1][2]) {
            rotate(now - 1, dir * -1, org);
        }
        // now 자석이 org 자석의 오른쪽에 있다면 now 자석의 오른쪽 자석으로 넘어간다 (now+1)
        // now 자석의 6번과 왼쪽 자석의 2번이 서로 다르다면 다음 왼쪽 자석도 회전시킨다
        // 현재자석이 가장오른쪽 마지막 자석인지 확인한다
        if (now + 1 <= 4 && now >= org && magnets[now][2] != magnets[now + 1][6]) {
            rotate(now + 1, dir * -1, org);
        }
    
        // 시계 방향으로 회전
        if(dir==1) {
            int tmp = magnets[now][7];
            for (int i = 7; i > 0; i--) {
                magnets[now][i]=magnets[now][i-1];
            }
            magnets[now][0]=tmp;
        }
        // 반시계 방향으로 회전
        else {
            int tmp = magnets[now][0];
            for (int i = 0; i < 7; i++) {
                magnets[now][i]=magnets[now][i+1];
            }
            magnets[now][7]=tmp;
        }
    }
```