# [SWEA D3] 농작물 수확하기 - 2805
  
### 풀이
- 입력   
    입력에 공백 구분이 없으므로 string으로 받고 한글자씩 int로 변환해서 삽입
    ```cpp
    for (int i = 0; i < N; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < N; j++) {
            map[i][j] = s[j] - '0';
        }
    }
    ```

- 수확   
    0행, N-1행 => 양 옆 0칸까지
    1행, N-2행 => 양 옆 1칸까지
    M행, N-(M+1)행 => 양 옆 M칸까지
    ```cpp
    int mid = N / 2;
    for (int i = 0, j = N - 1; i != mid; i++, j--) {
        int isum = map[i][mid], jsum = map[j][mid];     // 중앙칸 +
        for (int k = i; k != 0; k--) {                  // 양 옆으로 k칸씩
            isum += map[i][mid - k] + map[i][mid + k];
            jsum += map[j][mid - k] + map[j][mid + k];
        }
        ans += isum + jsum;
    }
    
    for (int i = 0; i < N; i++) {                       // 중앙 행은 전부 합산
        ans += map[mid][i];
    }
    ```