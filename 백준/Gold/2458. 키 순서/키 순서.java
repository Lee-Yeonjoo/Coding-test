import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//dfs
public class Main {
    static int[] count;
    static boolean[] visited;
    static ArrayList<Integer>[] heightInfo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        heightInfo = new ArrayList[N + 1];
        //배열 초기화
        for (int i = 0; i < N + 1; i++) {
            heightInfo[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            heightInfo[a].add(b);
        }

        count = new int[N + 1];
        //모든 노드를 각 시작점으로 dfs 반복
        for (int i = 1; i < N + 1; i++) {
            visited = new boolean[N + 1];
            dfs(i, i);
        }

        //카운트가 N-1개인 경우 세기
        int answer = 0;
        for (int i = 1; i < N + 1; i++) {
            if (count[i] == N - 1) answer++;
        }
        System.out.println(answer);
    }

    static void dfs(int x, int start) {
        visited[x] = true;  //방문 여부 꼭 표시해야함!!

        //방문하는 노드와 방문당하는 노드 카운트
        for (int adj : heightInfo[x]) {
            if (visited[adj]) continue;
            count[start]++;
            count[adj]++;
            dfs(adj, start);
        }
    }
}
