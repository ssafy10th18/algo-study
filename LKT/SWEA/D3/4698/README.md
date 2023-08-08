# [SWEA D3] 테네스의 특별한 소수 - 4698
    
### 풀이
[에라토스테네스의 체](https://ko.wikipedia.org/wiki/%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98_%EC%B2%B4) 이용
- 소수 목록 초기화
    ```cpp
    void PrimeNumberSieve() {
        fill(&arr[0], &arr[MAX], 1);
        for (int i = 2; i < sqrt(MAX); i++) {
            if (arr[i] == 0) continue;

            for (int j = i * i; j <= MAX; j += i) {
                arr[j] = 0;
            }
        }
    }
    ```

- D 포함 여부
    ```cpp
    void sol() {
        if (A < 2) A = 2;   // A가 1인 경우 예외처리
        for (int i = A; i <= B; i++) {
            if (arr[i] == 0) continue;

            int tmp = i;
            while (tmp != 0) {      // 10씩 나누면서 자릿수에 D가 있는지 판별
                if (tmp % 10 == D) {
                    ans++;
                    break;
                }
                tmp /= 10;
            }
        }
    }
    ```

