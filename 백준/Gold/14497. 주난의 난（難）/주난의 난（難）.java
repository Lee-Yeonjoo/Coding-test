import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayDeque;
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
            classInfo[i] = br.readLine().toCharArray();  //간단하게 문자 배열로 변환
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
        Queue<int[]> q = new ArrayDeque<>();  //LinkedList보다 ArrayDeque가 bfs에 더 적합
        q.add(new int[]{junanX, junanY});     //Point클래스는 GUI 관련 클래스라 Node클래스를 따로 만들거나, int배열 쓰는게 더 빠르다
        boolean[][] visited = new boolean[N][M];
        visited[junanX][junanY] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    //0인 경우에만 계속 탐색
                    if (classInfo[nx][ny] == '0') {
                        q.add(new int[]{nx, ny});
                    }
                    //초코 도둑 찾은 경우
                    else if (classInfo[nx][ny] == '#') {
                        isChoco = true;
                        return;
                    } else {  //1인 경우 0으로
                        classInfo[nx][ny] = '0';
                    }
                }
            }
        }
    }
}