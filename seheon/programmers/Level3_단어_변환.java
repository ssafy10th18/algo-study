class Solution {
    
    static String[] W;
    static int ans=51;
    static int targetIdx=-1;
    static int[] visited;
    
    public int solution(String begin, String target, String[] words) {
        visited = new int[words.length];
        for (int i=0; i<words.length; i++){
            if (words[i].equals(target)) {
                targetIdx=i;
                break;
            }
        }
        if (targetIdx == -1) return 0;
        W = words;

        for (int i=0; i<words.length; i++){
            int temp = 0;
            String nextWords = W[i];
            for (int k=0; k<begin.length(); k++){
                if (begin.charAt(k) != nextWords.charAt(k)) temp+=1;
            }
            if (temp > 1) continue;
            visited[i] = 1;
            dfs(i, 1);
            visited[i] = 0;
        }

        return ans;
    }
    
    static void dfs(int idx, int cnt){
        if (idx == targetIdx) {
            ans = Math.min(ans, cnt);
            return;
        }
        for (int i=0; i<W.length; i++) {
            if (visited[i] == 1) continue;
            int temp=0;
            String curWords = W[idx];
            String nextWords = W[i];
            for (int k=0; k<curWords.length(); k++){
                if (curWords.charAt(k) != nextWords.charAt(k)) temp+=1;
            }
            if (temp > 1) continue;
            visited[i] = 1;
            dfs(i, cnt+1);
            visited[i] = 0;
        }
    }
}