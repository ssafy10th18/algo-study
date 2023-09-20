import java.io.*;
import java.util.*;

public class Main {

    static Queue<Integer[]> Q;
    static int[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double x = Double.parseDouble(st.nextToken());
        double y = Double.parseDouble(st.nextToken());
        double c = Double.parseDouble(st.nextToken());

        double l = 1;
        double r = Math.min(x, y);
        double ans=0, h1=0, h2=0, width=0, m=0;

        while (true){
            if (l+0.001 > r) break;
            width = (l+r)/2;
            h1 = Math.sqrt(Math.pow(x, 2) - Math.pow(width, 2));
            h2 = Math.sqrt(Math.pow(y, 2) - Math.pow(width, 2));
            m = (h1*h2)/(h1+h2);
            if (m >= c){
                l = width;
                ans = width;
            }
            else {
                r = width;
            }
        }
        System.out.println(String.format("%.3f", ans));
    }
}
