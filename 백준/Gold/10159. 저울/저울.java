import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//dfs
public class Main {
    static boolean[] visited;
    static int[] count;
    static ArrayList<Integer>[] things;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        things = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            things[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            things[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }

        count = new int[N + 1];  //비교 결과를 알 수 있는 물건의 개수를 카운트
        for (int i = 1; i < N + 1; i++) {
            visited = new boolean[N+1];
            dfs(i, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            sb.append(N - count[i]).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int num, int startNum) {
        visited[num] = true;
        count[num]++;  //방문한 노드에 대해 카운트

        for (int i = 0; i < things[num].size(); i++) {
            int x = things[num].get(i);
            if (!visited[x]) {
                count[startNum]++;  //이웃 노드에 방문할 때마다 시작 노드에 대해 카운트
                dfs(x, startNum);
            }
        }
    }
}
