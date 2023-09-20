import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, answer;
    static String[] strArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        strArr = new String[N];
        for(int i = 0; i < N; i++){
            strArr[i] = br.readLine();
        }
        
        int flag = 0;
        flag |= 1 << 'a' - 'a';
        flag |= 1 << 'c' - 'a';
        flag |= 1 << 'i' - 'a';
        flag |= 1 << 'n' - 'a';
        flag |= 1 << 't' - 'a';
        recursive(0, 0, flag);

        System.out.println(answer);
    }

    private static void recursive(int start, int cnt, int flag) {
        if(cnt == K - 5){
            int readSentence = 0;
            for(int i = 0; i < N; i++){
                boolean isPossible = true;
                for(int j = 0; j < strArr[i].length(); j++){
                    if((flag & 1 << (strArr[i].charAt(j) - 'a')) == 0) {
                        isPossible = false;
                        break;
                    }
                }
                if(isPossible)readSentence++;
            }
            answer = Math.max(answer, readSentence);
            return;
        }

        for(int i = start; i < 26; i++){
            if((flag & 1 << i) == 0){
                recursive(i, cnt + 1, flag | 1 << i);
            }
        }
    }
}
