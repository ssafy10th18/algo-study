# [SILVER III] 단어 뒤집기2 - 17413 

### check point
- 단어 단위로 뒤집기 (문장 뒤집기 X)
- <> 태그는 항상 쌍으로 나옴
- 태그는 뒤집지 않음
  
### 풀이
- 문장을 stack에 넣고 꺼내면 알아서 뒤집어 짐
    ```cpp
    for (int i = 0; i < str.size(); i++) {
        if (str[i] == '<') {            // 괄호를 만나면 그 직전까지의 단어를 뒤집어 출력
            sPrint();
            while (str[i] != '>') {     // 이 후 태그 출력
                cout << str[i++];
            }
            cout << str[i];
        } else if (str[i] == ' ') {     // 공백을 만나면 단어가 끝이 난 것이므로 뒤집어 출력
            sPrint();
            cout << " ";
        } else {                        // 단어를 한 글자씩 스택에 삽입
            s.push(str[i]);
        }
    }
    sPrint();                           // 마지막 단어를 뒤집어 출력
    ```