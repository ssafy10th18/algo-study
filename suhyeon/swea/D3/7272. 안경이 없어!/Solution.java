import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String result = "";

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            String text1 = st.nextToken();
            String text2 = st.nextToken();

            if(text1.length() != text2.length()) {
                result = "DIFF";
            } else {
                text1 = transferText(text1);
                text2 = transferText(text2);

                if(text1.equals(text2)) {
                    result = "SAME";
                } else {
                    result = "DIFF";
                }
            }
            System.out.println("#"+tc+" "+result);
        }
    }

    public static String transferText(String text) {
        String zero = "CEFGHIJKLMNSTUVWXYZ";
        String one = "ADOPQR";
        String two = "B";
        StringBuilder sb = new StringBuilder("");

        for(int i = 0; i < text.length(); i++) {
            String s = String.valueOf(text.charAt(i));
            if(zero.contains(s)) {
                sb.append("0");
            } else if(one.contains(s)) {
                sb.append("1");
            } else if(two.contains(s)) {
                sb.append("2");
            }
        }
        return sb.toString();
    }
}
