# [SWEA D3] 영준이의 카드 카운팅 - 4047

### 풀이
- 카드 카운팅   
    각 카드는 [모양, 10의자리, 1의자리]로 이루어져있음   
    각 모양별 배열을 만들고 숫자 인덱스에 보유 여부를 표시   
    이미 있으면 ERROR 출력 << 판별을 위해 flag 사용
    ```cpp
    for (int i = 0; i < info.size(); i += 3) {
        flag = 0;   // 중복 여부 판별
        int num = 10 * (info[i + 1] - '0') + (info[i + 2] - '0');   // 카드 숫자
        // 각각 모양 별로 해당 숫자에 보유여부 표시
        // 이미 있으면 중간에 끝나 flag가 0이므로 ERROR 출력
        if (info[i] == 'S') {
            if (S[num] == 1) {
                return;
            }
            S[num] = 1;
            sCnt++;
        } else if (info[i] == 'C') {
            if (C[num] == 1) {
                return;
            }
            C[num] = 1;
            cCnt++;
        } else if (info[i] == 'H') {
            if (H[num] == 1) {
                return;
            }
            H[num] = 1;
            hCnt++;
        } else {
            if (D[num] == 1) {
                return;
            }
            D[num] = 1;
            dCnt++;
        }
        flag = 1;                   // 중복 카드 없으면 다시 1로
    }
    ```

- 출력   
    flag에 따라 출력
    ```cpp
    void print() {
        if (flag) {
            cout << 13 - sCnt << " " << 13 - dCnt << " " << 13 - hCnt << " " << 13 - cCnt << "\n";
        } else
            cout << "ERROR\n";
    }
    ```