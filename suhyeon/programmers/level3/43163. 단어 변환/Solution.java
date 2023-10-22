import java.util.Arrays;

class Solution {
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public int solution(String begin, String target, String[] words) {

        visited = new boolean[words.length];

        if(!Arrays.asList(words).contains(target)) {
            return 0;
        }

        for(int i = 0; i < words.length; i++) {
            dfs(0, i, begin, target, words);
        }

        return answer;

    }

    public static void dfs(int depth, int n, String nowWord, String target, String[] words) {
        if(depth == n) {
            if(nowWord.equals(target)) {
                answer = Math.min(answer, n);
            }
            return;
        }

        for(int i = 0; i < words.length; i++) {
            if(!visited[i] && compareAlpa(nowWord, words[i])) {
                visited[i] = true;
                dfs(depth+1, n, words[i], target, words);
                visited[i] = false;
            }
        }
    }

    public static boolean compareAlpa(String a, String b) {
        int cnt = 0;

        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) == b.charAt(i)) cnt++;
        }

        if(cnt == a.length() - 1) return true;
        return false;
    }
}
