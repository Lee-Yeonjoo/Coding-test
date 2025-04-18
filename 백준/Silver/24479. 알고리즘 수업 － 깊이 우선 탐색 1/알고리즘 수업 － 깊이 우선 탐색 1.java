import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int[] visitOrder;
    static int count = 0;

    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        visitOrder = new int[N + 1];
        visited = new boolean[N + 1];

        //인접 리스트 초기화
        graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<Integer>());
        }

        //인접 리스트 생성
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        //각 요소를 오름차순 정렬
        for (int i = 1; i < N + 1; i++) {
            Collections.sort(graph.get(i));
        }

        dfs(R);

        for (int i = 1; i < N + 1; i++) {
            System.out.println(visitOrder[i]);
        }
    }
    
    static void dfs(int x) {

        visited[x] = true;
        visitOrder[x] = ++count;  //count를 증가시키고 방문 순서에 저장 

        for (Integer i : graph.get(x)) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }
}
