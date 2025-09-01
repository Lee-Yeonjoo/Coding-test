import java.util.*;
class Solution {
    boolean[] visited;
    int answer = 0;
    int N, M;
    Set<Set<String>> setset = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        N = user_id.length;
        M = banned_id.length;
        visited = new boolean[N];
        
        dfs(0, user_id, banned_id, new ArrayList<>());
        answer = setset.size();
        return answer;
    }
    
    void dfs (int idx, String[] user_id, String[] banned_id, List<String> result) {
        //제재 아이디를 모두 찾은 경우
        if (idx == M) {
            //결과를 Set에 담기 -> 유일한 조합만 담도록!
            setset.add(new HashSet<>(result));
            return;
        }
        
        for (int i=0; i<N; i++) {
            if (!visited[i] && checkId(user_id[i], banned_id[idx])) {
                visited[i] = true;
                result.add(user_id[i]);
                dfs(idx+1, user_id, banned_id, result);
                visited[i] = false;  //백트래킹 
                result.remove(result.size()-1);  //백트래킹 
            }
        }
    }
    
    //아이디 체크
    boolean checkId(String userId, String bannedId) {
        //글자 수 다르면 false
        if (userId.length() != bannedId.length()) return false;
        
        for (int i=0; i<userId.length(); i++) {
            if (userId.charAt(i) != bannedId.charAt(i) && bannedId.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}