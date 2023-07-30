# [SILVER IV] 설탕 배달 - 2839
   
### 풀이
1. DP X( O(N) )   
    5로 나눠지면 5짜리에 나눠 담고   
    안되면 3으로 하나 빼고 반복
    ```cpp
    void sol() {
        ans = 0;
        while (N >= 0) {
            if (N % 5 == 0) {
                ans += (N / 5);
                cout << ans << "\n";
                return;
            }
            N -= 3;
            ans++;
        }
        cout << -1 << "\n";
    }
    ```

2. DP( O(N) )
    ```cpp
    init DP by -1
    DP[3] = 1, DP[5] = 1;

    for(int i = 6; i <= N; i++) {
        if(DP[i-3] == -1) DP[i] = DP[i-3] + 1;
        if(DP[i - 5] == -1) DP[i] != -1 ? min(DP[i], DP[i - 5] + 1) : DP[i - 5] + 1;
    }
    ```

3. 규칙성( O(1) )
- 증명
    ~~~
    N >= 3에서
    - N % 5 == 0 
        N = 5k
        ==> k개

    - N % 5 == 1
        N = 5k + 1 = 5(k - 1) + 6 = 5(k - 1) + 3 * 2
        ==> k - 1 + 2 = k + 1개

    - N % 5 == 2   
        - k >= 2일 때,   
          N = 5k + 2 = 5(k-2) + 12 = 5(k-2) + 3 * 4
          ==> k - 2 + 4 = k + 2개

        - k < 2일 때,
          N = 7 = 5 + 2 or 3 + 3 + 1
          ==> 불가능

    - N % 5 == 3
        N = 5k + 3
        ==> k + 1개

    - N % 5 == 4
        - k > 0일 때,
          N = 5k + 4 = 5k + 1 + 3 = 5(k - 1) + 6 + 3 = 5(k - 1) + 3 * 3
          ==> k - 1 + 3 = k + 2개

        - k = 0일 때,
          N = 4 = 3 + 1
          ==> 불가능
    ~~~
- 결론
  - N % 5가 0일 때, k개
  - N % 5가 1 or 3일 때, k + 1개
  - N % 5가 2 or 4일 때, k + 2개
  - N = 4 or 7일 때, 불가능
    ```cpp
    void run() {
        int N;
        cin >> N;
        int ans = N / 5;

        if (N == 4 || N == 7)
            cout << -1;
        else if (N % 5 == 0)
            cout << ans;
        else if (N % 5 == 1 || N % 5 == 3)
            cout << ans + 1;
        else if (N % 5 == 2 || N % 5 == 4)
            cout << ans + 2;
    }
    ```


