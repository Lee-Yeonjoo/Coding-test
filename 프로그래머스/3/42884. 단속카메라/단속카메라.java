import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        //시작점을 기준으로 오름차순 정렬
        PriorityQueue<Route> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.start, o2.start));
        int endPoint = -30001;  //루트의 진출 지점 저장
        
        for (int i = 0; i<routes.length; i++) {
            pq.add(new Route(routes[i][0], routes[i][1]));
        }
        
        while (!pq.isEmpty()) {
            Route curr = pq.poll();
            
            //이전에 저장한 endPoint보다 시작지점이 크면 겹치치 않으므로 새로운 카메라 설치 -> 진출 지점 갱신 
            if (curr.start > endPoint) {
                endPoint = curr.end;
                answer++;
            } else {
                endPoint = Math.min(endPoint, curr.end);  //겹치는 경우, 더 작은 진출 지점으로 갱신해야 함!! 주의!!
            }
        }
        
        return answer;
    }
}

class Route {
    int start;
    int end;
    Route (int start, int end) {
        this.start = start;
        this.end = end;
    }
}