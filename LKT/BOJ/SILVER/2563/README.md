# [SILVER V] 색종이 - 2563 
  
  
### 풀이
입력 받은 좌표 기준 넓이 100 1로 색칠 후 1의 갯수 카운팅
```cpp
void input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        int x, y;
        cin >> x >> y;
        cover(x, y);            // 10 * 10만큼 색칠
    }
}

void sol() {
    int ni, nj;

    for (int i = 1; i <= 100; i++) {
        for (int j = 1; j <= 100; j++) {
            if (board[i][j] == 1) {     // 색칠된 영역 카운팅
                ans++;
            }
        }
    }

    cout << ans;
}
```