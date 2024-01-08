#include <cstring>
#include <iostream>
#include <queue>
#include <vector>

#define fio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MAX_N 200001
#define INF 2000000001

using namespace std;

struct Edge {
    int e, c, cnt, prev;
};

struct comp {
    bool operator()(Edge e1, Edge e2) { return e1.c < e2.c; }
};

int N, M, K;
int dist[MAX_N];
vector<Edge> edges[MAX_N];
vector<int> infos[MAX_N];
priority_queue<Edge, vector<Edge>, comp> pq;

void input() {
    cin >> N >> M >> K;
    for (int i = 0; i < M; i++) {
        int s, e, c;
        cin >> s >> e >> c;

        edges[s].push_back({e, c, -2, 0});
    }

    for (int i = 0; i < K; i++) {
        int cnt, start;
        cin >> cnt >> start;
        for (int j = 1; j < cnt; j++) {
            int next;
            cin >> next;
            infos[start].push_back(next);
        }
    }

    for (int i = 1; i <= N; i++) {
        dist[i] = INF;
    }
}

void sol() {
    pq.push({1, 0, -2, 0});
    dist[1] = 0;

    while (!pq.empty()) {
        Edge cur = pq.top();
        pq.pop();

        if (dist[cur.e] < cur.c || infos[cur.prev].size() - 1 == cur.cnt) continue;

        for (Edge next : edges[cur.e]) {
            int nextDist = dist[cur.e] + next.c;

            if (dist[next.e] > nextDist) {
                dist[next.e] = nextDist;
                next.c = nextDist;

                if (cur.cnt + 1 < infos[cur.prev].size()) {
                    if(next.e == infos[cur.prev][cur.cnt + 1]) {
                        next.cnt = cur.cnt + 1;
                        next.prev = cur.prev;
                    }
                }

                if (!infos[next.e].empty()) {
                    next.cnt = 0;
                    next.prev = next.e;
                }

                pq.push(next);
            }
        }
    }

    for (int i = 1; i <= N; i++) {
        cout << dist[i] << " ";
    }
    cout << "\n";
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