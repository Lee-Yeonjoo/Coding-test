import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//플로이드 워셜(dfs나 bfs도 가능할 것 같은데, 코드가 더 간단해서 플로이드 워셜을 쓰고 싶었다.)
public class Main {

    static final int INF = 1000000000;  //경로가 없을 경우의 초기값

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N+1][N+1];

        //인접행렬 입력받기
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 0) {
                    graph[i][j] = INF;  //도달할 수 없는 경우 무한대로 초기화. 양수인 경로 여부만 구하는 것이므로, 자기자신에서 다시 자기자신으로 돌아올 수 있는지를 알아야 하므로(그래야 0이 아닌 양수 거리) 자기자신일 때도 무한대로 초기화
                } else graph[i][j] = x;
            }
        }

        //최단경로 구하기
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    //if (graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE) {
                        graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                    //}
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (graph[i][j] == INF) {
                    System.out.print("0 ");
                }else System.out.print("1 ");
            }
            System.out.println();
        }

        br.close();
    }
}
