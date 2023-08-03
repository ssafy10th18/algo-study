import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();
            int[] arr = new int[N];
            String result = "Possible";

            for(int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            Arrays.sort(arr); // 먼저 온 순서로 정렬

            int lastTime = arr[N-1];
            int index = 0;
            int bread = 0;

            if(arr[0] == 0) { // 0초에 손님이 올 경우
                result = "Impossible";
            } else {
                for(int i = 1; i <= lastTime; i++) {
                    if(i % M == 0) bread += K; // M초가 지날 때마다 붕어빵 만들기
                    if(i == arr[index]) {
                        if(bread > 0) bread--; // 붕어빵이 있다면 제공
                        else { // 붕어빵이 없다면 반복문 종료
                            result = "Impossible";
                            break;
                        }
                        index++; // 다음 순서
                    }
                }
            }
            System.out.println("#"+tc+" "+result);
        }
    }
}
