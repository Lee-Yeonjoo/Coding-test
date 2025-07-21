import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

//bfs - 다음 노드로 갈 수 있는 조건이 있는 문제
public class Main {
    static boolean[] visited;
    static List<Point> pointList;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            pointList = new ArrayList<>();
            for (int j = 0; j < n + 2; j++) {
                st = new StringTokenizer(br.readLine());
                pointList.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            visited = new boolean[n + 2];  //좌표 입력 순서대로 방문여부 표시
            bfs();
        }
    }

    static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(pointList.get(0));  //상근이 집이 (0,0)이 아닐 수 있다ㅜㅜ 문제 잘 읽기..
        visited[0] = true;

        while (!q.isEmpty()) {
            Point currNode = q.poll();

            //모든 노드가 이웃 -> 리스트 전체 순회
            for (int i=0; i<pointList.size(); i++) {
                Point nextNode = pointList.get(i);
                //이웃과 거리 계산 - 절댓값 빼먹음 주의!!
                int distance = Math.abs(nextNode.x - currNode.x) + Math.abs(nextNode.y - currNode.y);

                //거리가 1000미터 넘으면 방문 불가
                if (distance > 1000) {
                    continue;
                }

                //방문을 안했다면 큐에 넣고, 방문 처리
                if (!visited[i]) {
                    q.add(nextNode);
                    visited[i] = true;
                    //만약 방문 노드가 락 페스티벌인 경우 -> happy 출력 후 종료
                    if (i == n + 1) {
                        System.out.println("happy");
                        return;
                    }
                }
            }
        }
        //락 페스티벌에 도착 못하고 큐가 빔
        System.out.println("sad");
    }
}
