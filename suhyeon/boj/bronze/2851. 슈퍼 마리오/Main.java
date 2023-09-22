import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[10];

        // 10개의 숫자의 합이 100을 넘지 않을 경우에는 마지막 인덱스의 값(누적합)이 100과 가장 가까운 수 이기 때문에
        // idx를 arr.length-1로 초기화 한다.
        int idx = arr.length-1;

        arr[0] = Integer.parseInt(br.readLine());

        for(int i = 1; i < 10; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = arr[i-1] + num;

            // 누적합이 100을 넘으면 해당 idx를 저장한다.
            if(arr[i] > 100) {
                idx = i;
                break;
            }
        }

        int result = arr[idx];

        // idx의 누적합과 idx-1의 누적합 중 100과 더 가까운 값을 출력한다.
        if(Math.abs(100-arr[idx-1]) < Math.abs(100-arr[idx]))  {
            result = arr[idx-1];
        }
        System.out.println(result);
    }
}
