import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static String[] words;
    static int N, K;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // K가 5보다 작으면 "anta", "tica"를 가르칠 수 없음
        if(K < 5) {
            System.out.println(0);
            return;
        }
        // K가 26이면 모든 단어를 가르칠 수 있음
        else if (K == 26) {
            System.out.println(N);
            return;
        }

        visited = new boolean[26];
        words = new String[N];

        visited['a' - 'a'] = true;
        visited['c' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['n' - 'a'] = true;

        for(int i = 0; i < N; i++) {
            words[i] = br.readLine().replaceAll("anta|tica", "");
        }

        check(0, 0);

        System.out.println(answer);
    }

    private static void check(int start, int cnt) {
        if(cnt == K - 5) {
            int temp = 0;

            for(int i = 0; i < N; i++) {
                boolean flag = true;

                for(int j = 0; j < words[i].length(); j++) {
                    // 가르치지 않은 알파벳이 있는 경우
                    if(!visited[words[i].charAt(j) - 'a']) {
                        flag = false;
                        break;
                    }
                }

                if(flag) {
                    temp++;
                }
            }

            answer = Math.max(temp, answer);
            return;
        }

        for(int i = start; i < 26; i++) {
            if(!visited[i]) {
                visited[i] = true;
                check(i+1, cnt+1);
                visited[i] = false;
            }
        }
    }

}