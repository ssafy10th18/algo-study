package programmers.d2;

import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        
        Arrays.sort(times);
        
        long start = times[0];
        long end = times[times.length - 1] * (long)n;
        
        while(start <= end) {
            //오버플로우 발생 가능(1)
            long mid = start + ((end - start) / 2);
            
            long sum = 0;
            for(int t : times) {
                long numOfPeople = mid / t; //오버플로우 발생 가능(2)
                sum += numOfPeople;
            }

            if(sum >= n) { //lower bound를 찾으며 범위를 좁힌다
                answer = Math.min(answer, mid);
                end = mid - 1;
            }
            else { //(sum < n)
                start = mid + 1;
            }
        }
        
        return answer;
    }
}