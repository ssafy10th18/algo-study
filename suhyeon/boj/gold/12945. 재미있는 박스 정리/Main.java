import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Integer[] arr = new Integer[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = N/2;
        int cnt = 0;

        while(left < N/2 && right < N) {
            if(arr[left] * 2 <= arr[right]) {
                left++;
                cnt++;
            }
            right++;
        }

        System.out.println(N - cnt);
    }
}
