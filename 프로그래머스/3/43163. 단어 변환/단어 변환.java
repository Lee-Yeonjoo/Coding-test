import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        //words에 target이 없다면 변환할 수 없으므로 바로 0 리턴
        boolean isExist = false;
        for (String word : words) {
            if (word.equals(target)) isExist = true;
        }
        if(!isExist) return answer;
        
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(begin, 0));
        boolean[] visited = new boolean[words.length];
        
        //bfs
        while(!q.isEmpty()) {
            Node currNode = q.poll();
            String currWord = currNode.word;
            
            //target 노드라면 탐색 중단
            if (currWord.equals(target)) {
                answer = currNode.step;
                break;
            }
            
            for (int i = 0; i < words.length; i++) {
                int count = 0;
                for (int j = 0; j < currWord.length(); j++) {
                    if (currWord.charAt(j) != words[i].charAt(j)) count++;
                }
                //한 글자만 다를 경우 이웃이므로 탐색 -> 아직 방문 안한 것만 
                if (count == 1 && !visited[i]) {
                    visited[i] = true;
                    q.add(new Node(words[i], currNode.step + 1));
                }
            }
        }
        return answer;
    }
}

class Node {
    String word;
    int step;
    Node (String word, int step) {
        this.word = word;
        this.step = step; 
    }
}