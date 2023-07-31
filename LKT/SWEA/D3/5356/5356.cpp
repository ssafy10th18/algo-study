#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0);

using namespace std;

void init() {}

void input() {}

void sol() {}

void run() {
    init();
    input();
    sol();
}
int main(int argc, char** argv) {
    fio;
    int T, test_case;

    freopen("input.txt", "r", stdin);
    cin >> T;
    for (test_case = 1; test_case <= T; ++test_case) {
        run();
        cout << "#" << test_case << " " << ans << "\n";
    }
    return 0;
}