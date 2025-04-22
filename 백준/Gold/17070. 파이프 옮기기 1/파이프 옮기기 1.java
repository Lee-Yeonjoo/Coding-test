import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    //방향 벡터 {가로, 세로, 대각선}
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};

    static boolean[][] visited;

    static  int N;
    static int[][] house;
    static int count = 0;

    static class Node {
        int x;
        int y;
        int status;

        Node(int x, int y, int status) {
            this.x = x;
            this.y = y;
            this.status = status;  //0 = 가로, 1 = 세로, 2 = 대각선
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        house = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //visited = new boolean[N][N]; 진행 방향이 똑같은 위치로 갈 수 없는 방향이라 방문 처리할 필요가 없다.

        dfs(new Node(0, 1, 0));
        System.out.println(count);
    }

    static void dfs(Node node) {

        //visited[node.x][node.y] = true;

        //목적지에 도착
        if (node.x == N - 1 && node.y == N - 1) {
            count++;
            return;
        }

        for (int i = 0; i < 3; i++) {

            //가로, 세로일 때 갈 수 없는 진행 방향 무시
            if ((node.status == 0 && i == 1) || (node.status == 1 && i == 0)) {
                continue;
            }

            //다음 진행의 끝 부분 좌표 구하기
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];

            //끝 부분 좌표가 범위를 벗어남
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }

            //끝 부분 좌표에 벽이 있는 경우 -> 진행 불가능
            if (house[nx][ny] == 1) {
                continue;
            }

            //대각선으로 진행할 때 벽이 있는 경우 -> 진행 불가능
            if (i == 2 && (house[nx - 1][ny] == 1 || house[nx][ny - 1] == 1)) {
                continue;
            }

            dfs(new Node(nx, ny, i));
        }
    }
}
