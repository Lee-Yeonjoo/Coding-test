import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//dfs로 탐색하되 답 구하는건 dp로 구해야함. 쿼리마다 일일히 dfs하면 시간초과
public class Main {
    static ArrayList<Integer>[] tree;
    static int[] subtreeCount;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];  //인접리스트
        for (int i = 1; i < N + 1; i++) {
            tree[i] = new ArrayList<>();  //인접리스트 각 요소 리스트 초기화
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            //무방향
            tree[u].add(v);
            tree[v].add(u);
        }

        subtreeCount = new int[N + 1];  //인덱스 번호의 노드를 루트로 가지는 서브트리 요소의 개수를 카운트하는 배열
        Arrays.fill(subtreeCount, 1);  //자기자신도 서브트리에 포함되니까 1로 초기화
        visited = new boolean[N + 1];
        dfs(R);

        //쿼리 답 출력 -> 출력을 최대 10만 줄 해야하니까 sb사용 꼭
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            sb.append(subtreeCount[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int x) {
        visited[x] = true;

        for (Integer nx : tree[x]) {
            if (!visited[nx]) {
                dfs(nx);
                subtreeCount[x] += subtreeCount[nx];  //자식의 개수를 부모의 개수에 더한다. 서브트리에 포함되니까
            }
        }
    }
}
