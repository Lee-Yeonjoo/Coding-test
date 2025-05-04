import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//DP인 줄 알았는데 BFS
public class Main {

    static int[] time;
    static int[] dx = {-1, 1, 2};
    static int N, K, endTime = Integer.MAX_VALUE, count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        time = new int[100001];

        if (N == K) {
            System.out.println("0\n1");
        } else {
            bfs(N);
            System.out.println(time[K]);
            System.out.println(count);
        }
    }

    static void bfs(int start) {

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {

            int curr = q.poll();

            //endTime보다 방문 시간이 크다면 더 이상 탐색할 필요x
            if (endTime < time[curr]) return;

            //현재 노드에서 -1, 1, *2로 이동 가능
            for (int i = 0; i < 3; i++) {
                int nx = i == 2? curr * dx[i] : curr + dx[i];

                if (nx < 0 || nx > 100000) {  //K보다 커졌다가 작아진 경로가 최단 경로일 수 있기 때문에 범위를 max(N, K)보다 더 크게 넉넉히 잡아야 한다.
                    continue;
                }

                if (nx == K) {
                    endTime = time[curr];  //curr에서 K로 최단 시간 갈 수 있는 curr의 방문 시간. 즉, endTime에 curr로(K의 이전 노드) 방문해야 K에 최단 시간으로 방문 가능
                    count++;  //최단 거리가 되는 경로 가짓수 세기
                }

                if (time[nx] == 0 || time[nx] == time[curr] + 1) {  //아직 nx의 time을 안 구했거나, curr의 최단 시간 + 1인 경우(즉, 최단거리가 되는 경우)
                    q.add(nx);  //또 방문한 노드가 최단거리로 도달가능하므로 큐에 한번 더 넣어준다. -> 경로의 수를 세기 위해
                    time[nx] = time[curr] + 1;
                }
            }
        }
    }
}
