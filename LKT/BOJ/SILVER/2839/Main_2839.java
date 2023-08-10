import java.io.*;

public class Main_2839 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int ans = N / 5;

        if (N == 4 || N == 7)
            ans = -1;
        else if (N % 5 == 1 || N % 5 == 3)
            ans += 1;
        else if (N % 5 == 2 || N % 5 == 4)
            ans += 2;
        System.out.println(ans);
    }
}