# [SILVER IV] 색종이2 - 2567 
  
### 풀이
입력 기준 1로 영역 색칠 후 테두리 1 주변 0의 갯수를 세면 둘레
```cpp
void cover(int x, int y) {
    for (int i = y; i < y + 10; i++) {
        for (int j = x; j < x + 10; j++) {  // 10*10 색칠
            board[i][j] = 1;
        }
    }
}

void input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        int x, y;
        cin >> x >> y;
        cover(x, y);
    }
}

void sol() {
    int ni, nj;
    int di[4] = {0, 0, 1, -1};
    int dj[4] = {1, -1, 0, 0};
    for (int i = 1; i <= 100; i++) {
        for (int j = 1; j <= 100; j++) {
            if (board[i][j] == 1) {
                for (int k = 0; k < 4; k++) {
                    ni = i + di[k];
                    nj = j + dj[k];
                    if (board[ni][nj] == 0) ans++;
                }
            }
        }
    }

    cout << ans;
}
```