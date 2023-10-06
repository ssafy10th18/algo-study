import java.io.*;
import java.util.*;

public class Main {

    static int[] M;
    static int[] ans;
    static List<Integer> answer = new ArrayList<>();
    static int[] tracing;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        M = new int[n];
        ans = new int[n];
        tracing = new int[n];

        st = new StringTokenizer(br.readLine());
        int idx = 0;
        while (st.hasMoreTokens()){
            M[idx++] = Integer.parseInt(st.nextToken());
        }

        ans[0] = M[0];
        tracing[0] = 0;
        int next = 0;
        for (int i=1; i<n; i++){
            int number_idx = Arrays.binarySearch(ans, 0, next, M[i]);
            if (number_idx < 0){
                if (-number_idx-1 == next) {
                    if (ans[-number_idx-1] < M[i]) {
                        tracing[i] = next+1;
                        ans[++next] = M[i];
                    }
                    else {
                        tracing[i] = -number_idx-1;
                        ans[-number_idx-1] = M[i];
                    }
                }
                else {
                    tracing[i] = -number_idx-1;
                    ans[-number_idx-1] = M[i];
                }
            }
        }

        for (int i=n-1; i>-1; i--){
            if (tracing[i] == next) {
                answer.add(M[i]);
                next -= 1;
            }
        }
        answer.sort(Comparator.naturalOrder());
        System.out.println(answer.size());
        for (Integer a : answer) System.out.print(a + " ");
    }
}
