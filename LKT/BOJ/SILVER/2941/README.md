# [SILVER V] 크로아티아 알파벳 - 2941
  
### 풀이
```cpp
void sol() {
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == 'c' && (s[i + 1] == '=' || s[i + 1] == '-')) {
            i++;
        } else if (s[i] == 'd') {
            if (s[i + 1] == 'z' && s[i + 2] == '=')
                i += 2;
            else if (s[i + 1] == '-')
                i++;
        } else if (s[i] == 'l' && s[i + 1] == 'j') {
            i++;
        } else if (s[i] == 'n' && s[i + 1] == 'j') {
            i++;
        } else if (s[i] == 's' && s[i + 1] == '=') {
            i++;
        } else if (s[i] == 'z' && s[i + 1] == '=') {
            i++;
        }
        ans++;
    }
    cout << ans;
}
```