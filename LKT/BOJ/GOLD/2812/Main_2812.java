import java.io.*;
import java.util.*;

public class Main_2812 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;
    static int[] num;

    public static void main(String[] args) throws Exception {
        input();
        run();
    }

    static void run() throws Exception {
        int max = -1;
        for (int i = 0; i < N; i++) {

        }
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        num = new int[N];
        char[] tmp = br.readLine().toCharArray();
        for (int i = 0; i < N; i++)
            num[i] = tmp[i] - '0';
    }

    static void print() throws Exception {

    }
}
