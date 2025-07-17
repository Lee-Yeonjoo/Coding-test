import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

//구현
public class Main {
    static int N;
    static int[][] baskets;
    //방향 벡터 1~8
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        baskets = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                baskets[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //구름이 있는 칸을 저장하는 리스트
        Queue<Point> cloud = new LinkedList<>();
        //초기 구름 저장
        cloud.add(new Point(N - 2, 0));
        cloud.add(new Point(N - 2, 1));
        cloud.add(new Point(N - 1, 0));
        cloud.add(new Point(N - 1, 1));

        boolean[][] isCloud;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            //구름 칸 여부 표시
            isCloud = new boolean[N][N];

            //구름 이동, 물 1씩 증가
            List<Point> npList = new ArrayList<>();
            while (!cloud.isEmpty()) {
                Point p = cloud.poll();

                //구름이 이동할 칸 구하기 - 범위 넘어가는거 처리
                int nx = (p.x + dx[d] * s) % N;
                nx = nx < 0 ? N + nx : nx;
                int ny = (p.y + dy[d] * s) % N;
                ny = ny < 0 ? N + ny : ny;

                isCloud[nx][ny] = true;
                baskets[nx][ny]++;
                npList.add(new Point(nx, ny));
            }

            //구름 이동&물 증가 먼저 다 끝낸 후에 그 결과에 대해 물 복사를 해야함!!
            //물복사 d = 2, 4, 6, 8
            waterCopy(npList);

            //nx, ny였던 칸 제외하고 나머지 칸에 대해 새로운 구름 생성
            createNextCloud(isCloud, cloud);
        }

        //최종 바구니 물의 양의 합
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += baskets[i][j];
            }
        }
        System.out.println(sum);
    }

    //새로운 구름 생성
    private static void createNextCloud(boolean[][] isCloud, Queue<Point> cloud) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                //구름이 있던 칸이 아닌 경우만
                if (!isCloud[j][k] && baskets[j][k] >= 2) {
                    baskets[j][k] -= 2;
                    cloud.add(new Point(j, k));
                }
            }
        }
    }

    //물복사
    private static void waterCopy(List<Point> np) {
        for (Point point : np) {
            for (int i = 2; i <= 8; i += 2) {
                int copyX = point.x + dx[i];
                int copyY = point.y + dy[i];

                if (copyX < 0 || copyX >= N || copyY < 0 || copyY >= N) continue;

                //대각선 바구니에 물이 있다면 증가
                if (baskets[copyX][copyY] > 0) {
                    baskets[point.x][point.y]++;
                }
            }
        }
    }
}