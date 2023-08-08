# [SWEA D2] 패턴 마디의 길이 - 2007
  
### 풀이
```cpp
string tmp = "";                        // 패턴 마디 tmp
tmp += s[0];
for (int i = 1; i < 10; i++) {
    if (s[i] == tmp[0]) {               // tmp의 첫 글자와 다음 글자가 같으면
        int tidx = 0;
        int sidx = i;
        while (tidx != tmp.size()) {    // 마디 전체가 같은지 비교
            if (tmp[tidx] != s[sidx]) break;
            tidx++;
            sidx++;
        }

        if (tidx == tmp.size()) {       // 끝까지 같은지 체크
            ans = tmp.size();
            break;
        }
    }
    tmp += s[i];                        // 다르면 tmp에 추가
}
```