import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//플로이드 워셜
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        //인접 행렬을 무한대로 초기화
        final int INF = 100000;
        int[][] dist = new int[N + 1][N + 1];
        for (int i = 1; i < N+1; i++) {
            Arrays.fill(dist[i], INF);
        }

        StringTokenizer st;
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                if (st.nextToken().equals("1")) {
                    dist[i][j] = 1;  //연결된 노드의 최단 거리 갱신
                } else if (i == j) {
                    dist[i][j] = 0;  //자기 자신은 0
                }
            }
        }
        int[] plan = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        //플로이드 워셜
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        //여행 계획을 2개씩 잘라서 경로가 존재하는지 확인
        for (int i = 0; i < M - 1; i++) {
            if (dist[plan[i]][plan[i + 1]] >= INF) {  //INF인 경로라면 여행 계획 불가능
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");
    }
}
