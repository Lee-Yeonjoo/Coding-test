import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Node>> graph;

    //노드 번호와 거리 비용을 저장하는 객체 Node
    static class Node {
        int num;
        int cost;

        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<ArrayList<Node>>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<Node>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            dijkstra(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    //다익스트라 알고리즘
    static void dijkstra(int start, int end) {
        //최소 비용을 저장할 배열
        int[] dist = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            dist[i] = Integer.MAX_VALUE;  //가장 큰 값으로 초기화
        }

        //우선순위 큐를 이용한 다익스트라 - Node클래스의 cost를 기준으로 크기 비교 하도록 Comparator 적용
        PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

        //시작 노드 삽입
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();  //최소 비용인 노드 추출

            //end 노드가 큐에서 꺼내졌다는 것은 최소값 갱신이 완료되었다는 것이므로 함수 종료
            if (curNode.num == end) {
                System.out.println(dist[curNode.num]);
                return;
            }

            //중복 방문 방지
            if (dist[curNode.num] < curNode.cost) {
                continue;
            }

            //인접 노드의 거리 갱신
            for (int i = 0; i < graph.get(curNode.num).size(); i++) {
                Node adjNode = graph.get(curNode.num).get(i);

                if (dist[adjNode.num] > curNode.cost + adjNode.cost) {
                    dist[adjNode.num] = curNode.cost + adjNode.cost;
                    pq.offer(new Node(adjNode.num, dist[adjNode.num]));
                }
            }
        }
    }
}
