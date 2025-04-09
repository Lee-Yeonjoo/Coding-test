import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//다익스트라
public class Main {

    static int N, M;

    static ArrayList<ArrayList<Node>> cities;

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
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        StringTokenizer st;
        cities = new ArrayList<>();
        for (int i = 0; i < N+1; i++) {
            cities.add(new ArrayList<Node>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            cities.get(Integer.parseInt(st.nextToken())).add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);
    }

    static void dijkstra(int start, int end) {
        int[] dist = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {

            Node currNode = pq.poll();

            if (currNode.num == end) {
                System.out.println(dist[currNode.num]);
                return;
            }

            if (dist[currNode.num] < currNode.cost) {
                continue;
            }

            for (int i = 0; i < cities.get(currNode.num).size(); i++) {

                Node adjNode = cities.get(currNode.num).get(i);

                if (dist[adjNode.num] > currNode.cost + adjNode.cost) {
                    dist[adjNode.num] = currNode.cost + adjNode.cost;
                    pq.offer(new Node(adjNode.num, dist[adjNode.num]));
                }
            }
        }
    }
}
