#include <iostream>
#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int N, M;
int seg[1 << 18];

int init(int node, int start, int end) {
    if (start == end) {
        return seg[node] = 1;
    }

    int mid = (start + end) / 2;
    return seg[node] = init(2 * node, start, mid) + init(2 * node + 1, mid + 1, end);
}

int query(int node, int start, int end, int order) {
    int mid = (start + end) / 2;
    if (start == end) return start;

    if (order <= seg[2 * node]) {
        return query(2 * node, start, mid, order);
    } else {
        return query(2 * node + 1, mid + 1, end, order - seg[2 * node]);
    }
}

int update(int node, int start, int end, int del) {
    seg[node]--;
    if (start == end) return 0;

    int mid = (start + end) / 2;

    if (del <= mid) {
        return update(2 * node, start, mid, del);
    } else {
        return update(2 * node + 1, mid + 1, end, del);
    }
}

void input() { cin >> N >> M; }

void sol() {
    init(1, 1, N);
    int c = 1;
    for (int i = 0; i <= 4; i++) {
        while (c < (1 << i)) {
            cout << seg[c] << " ";
            c++;
        }
        cout << "\n";
    }

    cout << "<";
    int index = 1;
    for (int i = 0; i < N; i++) {
        int size = N - i;
        index += M - 1;

        if (index == size)
            index = size;
        else if (index > size)
            index %= size;
        int order = query(1, 1, N, index);
        update(1, 1, N, order);

        if (i == N - 1) {
            cout << order;
        } else {
            cout << order << ", ";
        }
    }
    cout << ">";
}

void run() {
    input();
    sol();
}

int main() {
    fio;
    run();
    return 0;
}