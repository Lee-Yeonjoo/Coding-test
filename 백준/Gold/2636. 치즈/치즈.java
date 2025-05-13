import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//bfs로 구현. 0인 부분을 bfs했을 때 1을 만난다면 그 1은 치즈의 가장자리이다.
public class Main {
    static int N, M;
    static int[][] cheese;
    static int remainCheese;  //마지막에 남은 치즈의 수를 저장
    static boolean isContinue = true;  //bfs를 또 해야하는지
    static int cheeseCount = 0;  //전체 치즈 카운트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cheese = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
                if (cheese[i][j] == 1) {  //전체 치즈의 수 카운트
                    cheeseCount++;
                }
            }
        }

        remainCheese = cheeseCount;  //전체 치즈의 수로 초기화
        int count = 0;  //몇 시간인지
        while (isContinue) {
            bfs();
            count++;
        }

        System.out.println(count);
        System.out.println(remainCheese);
    }

    static void bfs() {

        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        q.add(new Point(0, 0));
        visited[0][0] = true;

        isContinue = false;
        while (!q.isEmpty()) {
            Point curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                //치즈가 아니면 큐에 삽입해서 탐색 진행
                if (cheese[nx][ny] == 0) {
                    q.add(new Point(nx, ny));
                } else {  //0인 부분을 탐색했는데 1을 만난다는건 가장자리이다. 치즈가 녹아야함
                    cheese[nx][ny] = 0;  //치즈가 녹음
                    cheeseCount--;  //치즈의 수 감소
                    isContinue = true;  //치즈가 있었다면 bfs를 또 해야함
                }
            }
        }

        //치즈가 다 녹았다면
        if (cheeseCount == 0) {
            isContinue = false;  //더 이상 bfs를 진행하지x
        } else remainCheese = cheeseCount;  //아직 안녹았을 때만 남은 치즈의 수 갱신
    }
}
