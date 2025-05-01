import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, L, R;
    static int[][] A;
    static boolean[][] visited;
    static boolean isMove = true;  //인구 이동이 발생했는지 여부
    static int dateCount = 0;

    static int[][] updateA;  //갱신하기 위해 구역 번호를 표시하는 용도
    static int[] updateValues;  //구역 번호에 따른 갱신 값 저장
    static int count = 1;  //구역 번호 카운트

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (isMove) {
            isMove = false;
            visited = new boolean[N][N];
            updateA = new int[N][N];
            HashMap<Integer, Integer> updateValues = new HashMap<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (!visited[i][j]) {
                        updateValues.put(count, bfs(i, j));
                    }
                    count ++;
                }
            }
            dateCount++;

            //A 갱신
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (updateA[i][j] == 0) {
                        continue;
                    }

                    A[i][j] = updateValues.get(updateA[i][j]);
                }
            }
        }

        System.out.println(dateCount - 1);
        br.close();
    }

    //bfs로 연합 찾기 -> 평균을 반환
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int bfs(int x, int y) {

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y));
        visited[x][y] = true;

        int sum = A[x][y];  //A값의 합
        int numCountry = 1;  //연합인 나라의 수

        while (!q.isEmpty()) {

            Point currNode = q.poll();
            updateA[currNode.x][currNode.y] = count;  //모든 노드에 대한 bfs가 끝나고 갱신할 때 구역 표시

            for (int i = 0; i < 4; i++) {
                int nx = currNode.x + dx[i];
                int ny = currNode.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                int gap = Math.abs(A[currNode.x][currNode.y] - A[nx][ny]);  //나라 간 인구 수 차이 구하기

                if (gap >= L && gap <= R) {
                    q.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                    sum += A[nx][ny];
                    numCountry++;
                    isMove = true;  //인구 이동 발생
                    updateA[nx][ny] = count;
                }
            }
        }

        return (int) sum / numCountry;
    }
}
