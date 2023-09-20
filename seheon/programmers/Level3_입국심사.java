import java.io.*;
import java.util.*;

public class Solution {

    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        Arrays.sort(times);
        long l = 1;
        long r = (long)times[0] * n;;
        long m = 0;

        while (true){
            if (l >= r) break;
            m = (l+r)/2;
            long cnt =  count(m, times);
            if (cnt < n) l = m+1;
            else {
                if (answer > m) answer = m;
                r = m;
            }
        }
        return answer;
    }

    static long count(long m, int[] times){
        long cnt = 0;
        for (int t : times) {
            cnt += (m/t);
        }
        return cnt;
    }
}