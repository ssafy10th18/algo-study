# [SWEA D3] 성공적인 공연기획 - 4789

### 풀이
```cpp
// toAdd : 동원할 인원
// 
void sol() {
    for (int i = 0; i < need.length(); i++) {
        if (need[i] == '0') continue;

        int toAdd = 0;                  
        if (clap < i) {                     // 인원 부족하면 고용
            toAdd = i - clap;               // toAdd : 고용할 인원
            ans += toAdd;                   // ans : 총 고용 인원
        }
        clap += toAdd + (need[i] - '0');    // clap : 고용한 인원 + 조건 충족돼서 일어난 인원
    }
}
```