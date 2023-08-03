# [SWEA D3] 퍼펙트 셔플 - 3499

### 풀이
- 입력   
    카드를 반으로 나눠 deck1과 deck2에 저장
    ```cpp
    mid = (N + 1) / 2;              // 홀 수인 경우 중앙 카드를 마지막에 넣어야함
    for (int i = 0; i < N; i++) {
        if (i < (N + 1) / 2)        // 0~mid까진 deck1
            cin >> deck1[i];
        else                        // mid~끝까진 deck2
            cin >> deck2[i - mid];
    }
    ```

- solve   
    ```cpp
    for (int i = 0; i < mid; i++) {
        ans[2 * i] = deck1[i];      // deck1에서 하나
        ans[2 * i + 1] = deck2[i];  // deck2에서 하나
    }
    ```