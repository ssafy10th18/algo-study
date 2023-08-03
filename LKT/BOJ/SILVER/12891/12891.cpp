#include <iostream>
#include <string>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

const int A = 0, C = 1, G = 2, T = 3;
int S, P, ans;
string s;
int count[4], cnt[4];

void init() { ans = 0; }

void input() {
    cin >> S >> P >> s;
    for (int i = 0; i < 4; i++) {
        cin >> count[i];
    }
}

bool check() {
    for (int i = 0; i < 4; i++) {
        if (count[i] > cnt[i]) return 0;
    }

    return 1;
}

void sol() {
    for (int i = 0; i < P; i++) {
        if (s[i] == 'A')
            cnt[A]++;
        else if (s[i] == 'C')
            cnt[C]++;
        else if (s[i] == 'G')
            cnt[G]++;
        else
            cnt[T]++;
    }

    if (check()) ans++;

    int start = -1;
    for (int e = P; e < S; e++) {
        start++;
        if (s[start] == 'A')
            cnt[A]--;
        else if (s[start] == 'C')
            cnt[C]--;
        else if (s[start] == 'G')
            cnt[G]--;
        else
            cnt[T]--;

        if (s[e] == 'A')
            cnt[A]++;
        else if (s[e] == 'C')
            cnt[C]++;
        else if (s[e] == 'G')
            cnt[G]++;
        else
            cnt[T]++;
        if (check()) ans++;
    }

    cout << ans;
}

void run() {
    init();
    input();
    sol();
}

int main() {
    fio;
    run();
    return 0;
}