import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        StringTokenizer st;
        int[] answer = new int[2];
        Deque<Integer> dq = new ArrayDeque<>();
        
        for (String operation : operations) {
            st = new StringTokenizer(operation);
            String op = st.nextToken();
            int num = Integer.parseInt(st.nextToken());
            
            if (op.equals("I")) {  //삽입 후, 정렬
                dq.add(num);
                List<Integer> temp = new ArrayList<>(dq);
                Collections.sort(temp);
                dq = new ArrayDeque<>(temp);
            } else if (op.equals("D") && num == -1) {  //정렬된 상태이므로 최솟값은 앞쪽에서 제거
                if (!dq.isEmpty()) dq.pollFirst();
            } else {  //최댓값은 뒷쪽에서 제거
                if (!dq.isEmpty()) dq.pollLast();
            }
        }
        
        if (dq.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = dq.peekLast();  //꺼내지 말고 peek으로 해야함!! 요소가 1개인 경우가 있으니까
            answer[1] = dq.peekFirst();
        }
        return answer;
    }
}