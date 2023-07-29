# [Gold III] 나무 재테크 - 16235 

### check point
- 한 칸에 나무 여러개 => 어린 나무 부터
- 남은 양분 < 나이 => 나무 죽음
- 봄	: 양분 += 나이, 나이++
- 여름	: 양분 += 봄에 죽은 나무 나이 / 2
- 가을	: if 나무 나이 % 5 == 0, 주변에 1살 나무 8개 +
- 겨울	: 양분[r][c] += A[r][c]
  
### 풀이
- 입력   
한 칸에 나무가 여러 개 자랄 수 있으므로 2차원 벡터로 구현.   
    ```cpp
    for (int i = 0; i < M; i++) {
        int x, y, z;
        cin >> x >> y >> z;
        trees[x][y].push_back(z);
    }
    ```

- 봄 & 여름   
봄에 살아남은 나무와 죽은 나무를 판별하고 여름에 계산하면 시간초과 => 둘이 같이 구현
   ```cpp
   for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            if (trees[i][j].size() == 0) continue;          // 칸에 나무가 없으면 스킵

            sort(trees[i][j].begin(), trees[i][j].end());   // 여러개면 어린 나무부터
            int toAdd = 0;
            for (int k = 0; k < trees[i][j].size(); k++) {
                z = trees[i][j][k];                         
                if (map[i][j] < z) {                        // 나이보다 양분이 적으면 죽은 걸로 처리
                    toAdd += z / 2;
                } else {                                    // 살면 alive에 따로 저장
                    map[i][j] -= z;
                    z++;
                    alive.push_back({z, make_pair(i, j)});
                    if (z % 5 == 0) {                       // 가을에 처리할 나무들 저장
                        five.push_back({i, j});
                    }
                }
            }
            trees[i][j].clear();                            // 계산 후 초기화
            map[i][j] += toAdd;                             // 여름에 추가될 양분 같이 계산
        }
    }

    while (!alive.empty()) {                // 살아남은 나무들 다시 추가
        x = alive.back().second.first;
        y = alive.back().second.second;
        z = alive.back().first;
        alive.pop_back();

        trees[x][y].push_back(z);
    }
   ```

- 가을   
봄에 따로 저장한 나이가 5의 배수인 나무들을 처리
    ```cpp
    while (!five.empty()) {
        x = five.back().first;
        y = five.back().second;
        five.pop_back();
        for (int k = 0; k < 8; k++) {           // 팔방탐색으로 주변에 1살짜리 나무들 추가
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
            trees[nx][ny].push_back(1);
        }
    }
    ```

- 겨울   
각 칸마다 할당된 양만큼 양분 추가
    ```cpp
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            map[i][j] += A[i][j];
        }
    }
    ```

### PS.
- vector 대신 deque 사용시 시간 줄일 수 있음
- pq로도 가능할 듯 한데 더 오래 걸릴 듯
- 봄 여름 같이 구현하는게 핵심