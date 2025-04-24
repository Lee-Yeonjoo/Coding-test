import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int num;  //자기 자신의 번호
        int cost;  //자기 자신의 비용

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    static int[] dist;

    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        dist = new int[V + 1];
        for (int i = 0; i < V + 1; i++) {
            dist[i] = Integer.MAX_VALUE;  //큰 수로 초기화
        }

        for (int i = 0; i < V + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            graph.get(Integer.parseInt(st.nextToken())).add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        dijkstra(K);
        for (int i = 1; i < V + 1; i++) {
            if (dist[i] >= Integer.MAX_VALUE) {
                System.out.println("INF");
            }
            else System.out.println(dist[i]);
        }
    }

    static void dijkstra(int start) {

        PriorityQueue<Node> pq = new PriorityQueue<>((o1 , o2) -> Integer.compare(o1.cost, o2.cost));
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {

            Node currNode = pq.poll();

            //중복 방문 방지. 이미 currNode의 이웃 노드의 최단 경로를 구했음.
            if (currNode.cost > dist[currNode.num]) {
                continue;
            }

            for (Node adjNode : graph.get(currNode.num)) {

                if (dist[adjNode.num] > dist[currNode.num] + adjNode.cost) {
                    dist[adjNode.num] = dist[currNode.num] + adjNode.cost;
                    pq.offer(new Node(adjNode.num, dist[adjNode.num]));
                }
            }
        }
    }
}
