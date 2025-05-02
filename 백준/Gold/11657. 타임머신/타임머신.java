import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//벨만 포드 알고리즘
public class Main {

    static final int INF = Integer.MAX_VALUE;
    static int N, M;
    static ArrayList<Edge> graph;
    static long[] dist;  //dist가 int범위를 넘을 수 있으므로 long타입 선언!! 

    static class Edge {
        int v;  //v->w로 가는 간선 비용이 cost
        int w;
        int cost;

        public Edge(int v, int w, int cost) {
            this.v = v;
            this.w = w;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.add(new Edge(A, B, C));
        }

        if (bellmanFord(1)) {
            System.out.println(-1);
        } else {
            for (int i = 2; i < N+1; i++) {
                System.out.println(dist[i] == INF? -1 : dist[i]);
            }
        }
    }

    static boolean bellmanFord(int start) {

        dist = new long[N+1];  //최단거리를 저장하는 배열
        Arrays.fill(dist, INF);  //무한대로 초기화
        dist[start] = 0;  //시작노드만 0으로

        //N번 반복 -> 음수 간선 순환이 존재하는 지 확인하기 위해 N번 반복. 사이클 없으면 N-1번만 반복해도 됨
        for (int i = 0; i < N; i++) {
            //graph에 저장한 모든 간선에 대해 반복
            for (int j = 0; j < M; j++) {
                Edge edge = graph.get(j);  //j번째 엣지 가져오기

                //v의 dist가 무한대면 어차피 갱신 안되니까 거른다. v를 거쳐서 w로 갔을 때 갱신되는지를 보는 거니까
                if (dist[edge.v] != INF && dist[edge.w] > dist[edge.v] + edge.cost) {
                    dist[edge.w] = dist[edge.v] + edge.cost;
                    if (i == N - 1) { //마지막 반복이라면 음수 간선 순환 체크 가능 - 갱신이 이루어지면 안되는데, 갱신이 되니까 이 조건문 안으로 들어온 것이므로 순환이 존재한다는 뜻.
                        return true;
                    }
                }
            }
        }

        return false;  //음수 간선 순환이 존재하지x
    }
}
