import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BFS
public class Main {
    static int N, M, junanX, junanY, chocoX, chocoY;
    static char[][] classInfo;
    static boolean isChoco = false;
    static int jumpCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        junanX = Integer.parseInt(st.nextToken()) - 1;
        junanY = Integer.parseInt(st.nextToken()) - 1;
        chocoX = Integer.parseInt(st.nextToken());
        chocoY = Integer.parseInt(st.nextToken());
        classInfo = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                classInfo[i][j] = input.charAt(j);
            }
        }

        //초콜릿 도둑을 찾을 때까지 bfs반복
        while (!isChoco) {
            jumpCount++;
            bfs();
        }
        System.out.println(jumpCount);
    }

    static void bfs() {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(junanX, junanY));
        boolean[][] visited = new boolean[N][M];
        visited[junanX][junanY] = true;

        while (!q.isEmpty()) {
            Point curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    //0인 경우에만 계속 탐색
                    if (classInfo[nx][ny] == '0') {
                        q.add(new Point(nx, ny));
                    }
                    //초코 도둑 찾은 경우
                    else if (classInfo[nx][ny] == '#') {
                        isChoco = true;
                    } else {  //1인 경우 0으로
                        classInfo[nx][ny] = '0';
                    }
                }
            }
        }
    }
}